package api.vk.mapper;

import org.mapstruct.Mapper;
import api.vk.model.dto.UserInfoDto;
import api.vk.model.entity.UserInfo;

@Mapper(componentModel = "spring")
public interface UserInfoMapper extends BaseMapper<UserInfo, UserInfoDto> {
}
