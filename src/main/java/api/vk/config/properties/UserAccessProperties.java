package api.vk.config.properties;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Setter
@Getter
@ConfigurationProperties(prefix = "vk.access")
public class UserAccessProperties {
    private int userId;
    private String accessToken;
}
