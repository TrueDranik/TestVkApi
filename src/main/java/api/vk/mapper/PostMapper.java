package api.vk.mapper;

import api.vk.model.dto.PostDto;
import org.mapstruct.Mapper;
import api.vk.model.entity.Post;

@Mapper(componentModel = "spring")
public interface PostMapper extends BaseMapper<Post, PostDto> {
}
