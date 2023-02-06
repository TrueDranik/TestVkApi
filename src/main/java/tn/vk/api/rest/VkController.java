package tn.vk.api.rest;

import com.vk.api.sdk.exceptions.ApiException;
import com.vk.api.sdk.exceptions.ClientException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tn.vk.model.dto.UserDto;
import tn.vk.model.dto.WallCommentDto;
import tn.vk.model.dto.WallpostFullDto;
import tn.vk.service.VkService;

import java.util.List;

@RestController
@RequestMapping(value = "vk")
public class VkController {
    private final VkService vkService;

    @Autowired
    public VkController(VkService vkService) {
        this.vkService = vkService;
    }

    @GetMapping("wall")
    public List<WallpostFullDto> getWall() throws ClientException, ApiException {
        return vkService.wallGet();
    }

    @GetMapping("wall-comments/{postId}")
    public List<WallCommentDto> getWallComments(@PathVariable Integer postId) throws ClientException, ApiException {
        return vkService.wallGetComments(postId);
    }

    @GetMapping("users/{usersIds}")
    public List<UserDto> getUsers(@PathVariable(name = "usersIds") String usersIds) throws ClientException, ApiException {
        return vkService.usersGet(usersIds);
    }
}
