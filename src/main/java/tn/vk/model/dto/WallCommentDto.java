package tn.vk.model.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class WallCommentDto {
    private Integer commentId;
    private Integer userId;
    private String commentText;
}
