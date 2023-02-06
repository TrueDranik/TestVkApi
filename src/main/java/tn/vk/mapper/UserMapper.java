package tn.vk.mapper;

import com.vk.api.sdk.objects.users.responses.GetResponse;
import org.mapstruct.Mapper;
import tn.vk.model.dto.UserDto;

@Mapper(componentModel = "spring")
public interface UserMapper extends BaseMapper<GetResponse, UserDto> {
}
