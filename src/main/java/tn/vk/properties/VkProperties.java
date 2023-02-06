package tn.vk.properties;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Setter
@Getter
@ConfigurationProperties(prefix = "vk")
public class VkProperties {
    private Integer userId;
    private String accessToken;
}
