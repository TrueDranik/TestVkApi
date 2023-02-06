package tn.vk.mapper;

import com.vk.api.sdk.objects.wall.WallComment;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import tn.vk.model.dto.WallCommentDto;

@Mapper(componentModel = "spring")
public interface WallCommentMapper extends BaseMapper<WallComment, WallCommentDto> {
    @Override
    @Mapping(source = "id", target = "commentId")
    @Mapping(source = "fromId", target = "userId")
    @Mapping(source = "text", target = "commentText")
    WallCommentDto domainToDto(WallComment domain);
}
