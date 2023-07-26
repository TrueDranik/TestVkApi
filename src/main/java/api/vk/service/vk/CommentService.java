package api.vk.service.vk;

import api.vk.model.entity.Comment;
import api.vk.model.entity.Post;
import api.vk.model.enums.CommentStatusEnum;
import com.vk.api.sdk.objects.users.responses.GetResponse;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Map;

public interface CommentService {
    void mapAndSaveAllComments(List<Comment> comment, Map<Integer, GetResponse> vkUsers, Post posts);

    boolean existsByCommentId(Integer commentId);

    List<Comment> getCommentsByStatus(CommentStatusEnum commentStatus, Pageable pageable);

    void updateCommentStatus(Long commentId, CommentStatusEnum commentStatus);

    Comment findById(Long commentId);
}
