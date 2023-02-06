package tn.vk.mapper;

import com.vk.api.sdk.objects.wall.WallpostFull;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import tn.vk.model.dto.WallpostFullDto;

@Mapper(componentModel = "spring")
public interface WallpostFullMapper extends BaseMapper<WallpostFull, WallpostFullDto> {
    @Override
    @Mapping(source = "id", target = "postId")
    WallpostFullDto domainToDto(WallpostFull domain);
}
