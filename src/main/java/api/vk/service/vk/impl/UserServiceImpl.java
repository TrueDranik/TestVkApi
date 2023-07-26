package api.vk.service.vk.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import api.vk.model.entity.UserInfo;
import api.vk.repository.UserInfoRepository;
import api.vk.service.vk.UserService;

import javax.persistence.EntityNotFoundException;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserInfoRepository userInfoRepository;

    @Override
    public UserInfo findById(Long userId) {
        return userInfoRepository.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException("User with [%s] not found".formatted(userId)));
    }
}
