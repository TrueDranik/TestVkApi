package api.vk.scheduled;

import api.vk.mapper.VkMapper;
import api.vk.model.entity.Comment;
import api.vk.model.entity.Post;
import api.vk.out.VkApi;
import api.vk.service.RateLimiter;
import api.vk.service.vk.CommentService;
import com.vk.api.sdk.objects.users.responses.GetResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.javacrumbs.shedlock.spring.annotation.SchedulerLock;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Service
@RequiredArgsConstructor
public class ScheduledProcessor {
    private final CommentService commentService;
    private final VkApi vkApi;
    private final VkMapper vkMapper;
    private final RateLimiter rateLimiter;

    /**
     * Каждый день в 4:30 метод собирает новые комментарии из группы ВК и сохраняет в БД
     */
    @Scheduled(cron = "${scheduled.cron}")
    @SchedulerLock(name = "ScheduledProcessor_collectAndSaveComment", lockAtLeastFor = "5m", lockAtMostFor = "14m")
    public void collectAndSaveWall() {
        log.info("[SCHEDULED]: Запущен сбор комментариев.");

        var posts = getPosts();
        for (var post : posts) {
            rateLimiter.waitRandomTimeout();

            var newComments = getNewComments(post);
            var idToUserInfo = fillCommentsCreators(newComments);
            commentService.mapAndSaveAllComments(newComments, idToUserInfo, post);
        }

        log.info("[SCHEDULED]: Сбор комментариев завершен успешно!");
    }

    private List<Post> getPosts() {
        var wall = vkApi.getPost();
        return wall.getItems().stream()
                .filter(post -> post.getComments().getCount() > 0)
                .map(vkMapper::dtoToDomain)
                .toList();
    }

    private List<Comment> getNewComments(Post post) {
        int postId = post.getVkId();
        return vkApi.getComments(postId).stream()
                .filter(comment -> !commentService.existsByCommentId(comment.getId()))
                .map(vkMapper::dtoToDomain)
                .toList();
    }

    private Map<Integer, GetResponse> fillCommentsCreators(List<Comment> comments) {
        var idToUserInfo = new HashMap<Integer, GetResponse>();
        comments.forEach(comment -> {
            rateLimiter.waitRandomTimeout();
            int userId = comment.getUserInfo().getVkId();
            vkApi.getUserInfo(userId)
                    .ifPresent(userInfo -> idToUserInfo.put(userId, userInfo));
        });
        return idToUserInfo;
    }
}
