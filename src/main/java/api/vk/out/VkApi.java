package api.vk.out;

import com.vk.api.sdk.objects.users.responses.GetResponse;
import com.vk.api.sdk.objects.wall.WallComment;

import java.util.List;
import java.util.Optional;

public interface VkApi {
    List<WallComment> getComments(Integer postId);

    Optional<GetResponse> getUserInfo(Integer usersIds);

    com.vk.api.sdk.objects.wall.responses.GetResponse getPost();
}
