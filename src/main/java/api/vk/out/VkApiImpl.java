package api.vk.out;

import api.vk.config.properties.VkDataProperties;
import com.vk.api.sdk.client.Lang;
import com.vk.api.sdk.client.VkApiClient;
import com.vk.api.sdk.client.actors.UserActor;
import com.vk.api.sdk.exceptions.ApiException;
import com.vk.api.sdk.exceptions.ClientException;
import com.vk.api.sdk.objects.users.Fields;
import com.vk.api.sdk.objects.users.responses.GetResponse;
import com.vk.api.sdk.objects.wall.GetFilter;
import com.vk.api.sdk.objects.wall.WallComment;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import api.vk.exception.VkException;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class VkApiImpl implements VkApi {
    private final VkDataProperties vkDataProperties;
    private final VkApiClient vkApiClient;
    private final UserActor userActor;

    public static final int SINGLE_AVAILABLE_INFO_INDEX = 0;

    /**
     * Получить комментарии под конкретным постом
     * (за исключением ответов на комментарии)
     */
    @Override
    public List<WallComment> getComments(Integer postId) {
        try {
            return vkApiClient.wall()
                    .getComments(userActor)
                    .ownerId(vkDataProperties.getGroupId())
                    .postId(postId)
                    .execute().getItems();
        } catch (ApiException | ClientException e) {
            log.error("Ошибка получения комментариев!", e);
            throw new VkException(e.getMessage());
        }
    }

    @Override
    public Optional<GetResponse> getUserInfo(Integer usersIds) {
        try {
            List<GetResponse> usersInfos = vkApiClient.users()
                    .get(userActor)
                    .userIds(String.valueOf(usersIds))
                    .fields(Fields.PHOTO_50)
                    .lang(Lang.RU)
                    .execute();
            if (usersInfos.isEmpty()) {
                return Optional.empty();
            }

            return Optional.ofNullable(usersInfos.get(SINGLE_AVAILABLE_INFO_INDEX));
        } catch (ApiException | ClientException e) {
            log.error("Ошибка получения информации о пользователе!", e);
            throw new VkException(e.getMessage());
        }
    }

    @Override
    public com.vk.api.sdk.objects.wall.responses.GetResponse getPost() {
        try {
            return vkApiClient.wall()
                    .get(userActor)
                    .ownerId(vkDataProperties.getGroupId())
                    .count(vkDataProperties.getNumberOfPosts())
                    .extended(false)
                    .filter(GetFilter.ALL)
                    .execute();
        } catch (ApiException | ClientException e) {
            log.error("Ошибка при получении постов", e);
            throw new VkException(e.getMessage());
        }
    }
}
