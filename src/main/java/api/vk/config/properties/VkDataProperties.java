package api.vk.config.properties;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Setter
@Getter
@ConfigurationProperties(prefix = "vk.data")
public class VkDataProperties {
    private int groupId;
    private int numberOfPosts;
}
