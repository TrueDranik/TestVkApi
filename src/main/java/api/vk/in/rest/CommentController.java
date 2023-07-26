package api.vk.in.rest;

import api.vk.mapper.CommentMapper;
import api.vk.model.dto.CommentDto;
import api.vk.model.enums.CommentStatusEnum;
import lombok.RequiredArgsConstructor;
import org.springdoc.api.annotations.ParameterObject;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;
import api.vk.model.entity.Comment;
import api.vk.service.vk.CommentService;

import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
@RestController
@RequestMapping("/comments")
public class CommentController {
    private final CommentService commentService;
    private final CommentMapper commentMapper;

    /**
     * Возвращает статусы оплаты (константы).
     */
    @GetMapping("/status")
    public Map<String, String> getCommentStatus() {
        return CommentStatusEnum.getTitles();
    }

    /**
     * Возвращает список комментариев согласно переданному статусу.
     */
    @GetMapping("/{commentStatus}")
    public List<CommentDto> getCommentsByStatus(@PathVariable("commentStatus") String commentStatus,
                                                @ParameterObject Pageable pageable) {
        List<Comment> wallCommentsByStatus = commentService.getCommentsByStatus(CommentStatusEnum.convertToEnum(commentStatus), pageable);
        return commentMapper.domainsToDtos(wallCommentsByStatus);
    }

    /**
     * Обновить статус комментария
     */
    @PutMapping("/{commentId}")
    public void updateCommentStatus(@PathVariable Long commentId,
                                    @RequestParam String commentStatus) {
        commentService.updateCommentStatus(commentId, CommentStatusEnum.convertToEnum(commentStatus));
    }
}
