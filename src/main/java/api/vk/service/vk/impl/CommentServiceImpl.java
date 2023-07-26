package api.vk.service.vk.impl;

import api.vk.mapper.VkMapper;
import api.vk.model.enums.CommentStatusEnum;
import api.vk.repository.CommentRepository;
import api.vk.repository.PostRepository;
import api.vk.repository.UserInfoRepository;
import com.vk.api.sdk.objects.users.responses.GetResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import api.vk.model.entity.Comment;
import api.vk.model.entity.Post;
import api.vk.service.vk.CommentService;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Map;

@Slf4j
@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {
    private final CommentRepository commentRepository;
    private final UserInfoRepository userRepository;
    private final PostRepository postRepository;
    private final VkMapper vkMapper;

    @Override
    @Transactional
    public void mapAndSaveAllComments(List<Comment> comments, Map<Integer, GetResponse> idToUserInfo, Post post) {
        comments.forEach(comment -> {
            int userId = comment.getUserInfo().getVkId();
            var userInfo = idToUserInfo.get(userId);
            if (userInfo != null) {
                var user = userRepository.findByVkId(userInfo.getId())
                        .orElseGet(() -> vkMapper.dtoToDomain(userInfo));
                var findedPost = postRepository.findByVkId(comment.getVkId()).orElse(post);
                comment.setUserInfo(user);
                comment.setPost(findedPost);
            }
        });

        commentRepository.saveAll(comments);
    }

    @Override
    public boolean existsByCommentId(Integer commentId) {
        return commentRepository.existsByVkId(commentId);
    }

    @Override
    public List<Comment> getCommentsByStatus(CommentStatusEnum commentStatus, Pageable pageable) {
        return commentRepository.findByStatus(commentStatus, pageable).getContent();
    }

    @Override
    @Transactional
    public void updateCommentStatus(Long commentId, CommentStatusEnum commentStatus) {
        commentRepository.updateStatusById(commentStatus, commentId);
    }

    @Override
    @Transactional(readOnly = true)
    public Comment findById(Long commentId) {
        return commentRepository.findById(commentId)
                .orElseThrow(() -> new EntityNotFoundException("Comment with id[%s] not found".formatted(commentId)));
    }
}
