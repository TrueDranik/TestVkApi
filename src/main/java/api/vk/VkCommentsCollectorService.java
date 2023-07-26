package api.vk;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;

@SpringBootApplication
@ConfigurationPropertiesScan("api.vk.config.properties")
public class VkCommentsCollectorService {
    public static void main(String[] args) {
        SpringApplication.run(VkCommentsCollectorService.class, args);
    }
}
