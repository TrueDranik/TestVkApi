package tn.vk.service;

import com.vk.api.sdk.client.Lang;
import com.vk.api.sdk.exceptions.ApiException;
import com.vk.api.sdk.exceptions.ClientException;
import com.vk.api.sdk.objects.users.Fields;
import com.vk.api.sdk.objects.users.responses.GetResponse;
import com.vk.api.sdk.objects.wall.GetFilter;
import com.vk.api.sdk.objects.wall.WallComment;
import com.vk.api.sdk.objects.wall.WallpostFull;
import com.vk.api.sdk.objects.wall.responses.GetCommentsResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import tn.vk.UserActorUtil;
import tn.vk.VkApiClientBuilder;
import tn.vk.mapper.UserMapper;
import tn.vk.mapper.WallCommentMapper;
import tn.vk.mapper.WallpostFullMapper;
import tn.vk.model.dto.UserDto;
import tn.vk.model.dto.WallCommentDto;
import tn.vk.model.dto.WallpostFullDto;

import java.util.List;

@Service
@RequiredArgsConstructor
public class VkService {
    private final UserMapper userMapper;
    private final WallpostFullMapper wallpostFullMapper;
    private final WallCommentMapper wallCommentMapper;
    private final UserActorUtil userActorUtil;

    private static final Integer OWNER_ID = -211236262;

    /**
     * Получить все посты со стены, содержащие комментарии
     *
     * @return List WallpostFullDto
     */
    public List<WallpostFullDto> wallGet() throws ClientException, ApiException {
        com.vk.api.sdk.objects.wall.responses.GetResponse execute = VkApiClientBuilder.vk().wall()
                .get(userActorUtil.getUserActor())
                .ownerId(OWNER_ID)
                .count(100)
                .extended(true)
                .filter(GetFilter.ALL)
                .execute();
        List<WallpostFull> items = execute.getItems().stream()
                .filter(i -> i.getComments().getCount() > 0)
                .toList();

        return wallpostFullMapper.domainsToDtos(items);
    }


    /**
     * Получить комментарии под конкретным постом
     * (за исключением ответов на комментарии)
     *
     * @param postId
     * @return List WallCommentDto
     */
    public List<WallCommentDto> wallGetComments(Integer postId) throws ClientException, ApiException {
        GetCommentsResponse execute = VkApiClientBuilder.vk().wall()
                .getComments(userActorUtil.getUserActor())
                .ownerId(OWNER_ID)
                .postId(postId)
                .execute();
        List<WallComment> items = execute.getItems();

        return wallCommentMapper.domainsToDtos(items);
    }

    /**
     * Получить информацию о конкретном пользователе
     *
     * @param usersIds
     * @return List UserDto
     */
    public List<UserDto> usersGet(String usersIds) throws ClientException, ApiException {
        List<GetResponse> execute = VkApiClientBuilder.vk().users()
                .get(userActorUtil.getUserActor())
                .userIds(usersIds)
                .fields(Fields.PHOTO_50)
                .lang(Lang.RU)
                .execute();

        return userMapper.domainsToDtos(execute);
    }
}
