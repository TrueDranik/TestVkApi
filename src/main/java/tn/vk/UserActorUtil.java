package tn.vk;

import com.vk.api.sdk.client.actors.UserActor;
import org.springframework.stereotype.Component;
import tn.vk.properties.VkProperties;

@Component
public class UserActorUtil {
    private final VkProperties properties;

    public UserActorUtil(VkProperties properties) {
        this.properties = properties;
    }

    public UserActor getUserActor() {
        return new UserActor(properties.getUserId(), properties.getAccessToken());
    }
}
