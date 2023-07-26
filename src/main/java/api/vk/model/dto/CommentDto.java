package api.vk.model.dto;

import api.vk.model.enums.CommentStatusEnum;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class CommentDto {
    private long id;
    private int vkId;
    private String text;
    private CommentStatusEnum status;
    private LocalDateTime date;
    private long userId;
    private long postId;
}
