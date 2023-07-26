package api.vk.mapper;

import com.vk.api.sdk.objects.users.responses.GetResponse;
import com.vk.api.sdk.objects.wall.WallComment;
import com.vk.api.sdk.objects.wall.WallpostFull;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import api.vk.model.entity.Post;
import api.vk.model.enums.CommentStatusEnum;
import api.vk.model.entity.Comment;
import api.vk.model.entity.UserInfo;

@Mapper(componentModel = "spring", uses = DateTimeMapper.class)
public interface VkMapper {

    @Mapping(source = "id", target = "vkId")
    @Mapping(source = "fromId", target = "userInfo.vkId")
    @Mapping(source = "postId", target = "post.vkId")
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "date")
    Comment dtoToDomain(WallComment domain);

    @Mapping(source = "id", target = "vkId")
    @Mapping(source = "photo50", target = "photoSize50")
    @Mapping(target = "id", ignore = true)
    UserInfo dtoToDomain(GetResponse domain);

    @Mapping(source = "id", target = "vkId")
    @Mapping(target = "id", ignore = true)
    Post dtoToDomain(WallpostFull domain);

    @AfterMapping
    default void setRepoValue(@MappingTarget Comment entity) {
        entity.setStatus(CommentStatusEnum.NOT_VIEWED);
    }
}
