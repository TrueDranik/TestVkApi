package api.vk.service.vk;

import api.vk.model.entity.UserInfo;

public interface UserService {
    UserInfo findById(Long userId);
}
