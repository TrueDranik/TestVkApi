package api.vk.in.rest;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import api.vk.mapper.UserInfoMapper;
import api.vk.model.dto.UserInfoDto;
import api.vk.model.entity.UserInfo;
import api.vk.service.vk.UserService;

@RequiredArgsConstructor
@RestController
@RequestMapping("/users")
public class UserController {
    private final UserService userService;
    private final UserInfoMapper userInfoMapper;

    /**
     * Получить User по Id
     */
    @GetMapping("/{userId}")
    public UserInfoDto getUser(@PathVariable Long userId) {
        UserInfo userInfo = userService.findById(userId);
        return userInfoMapper.domainToDto(userInfo);
    }
}
