package tn.vk;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import tn.vk.properties.VkProperties;

@SpringBootApplication
@EnableConfigurationProperties({VkProperties.class})
public class TestVkApiApplication {
    public static void main(String[] args) {
        SpringApplication.run(TestVkApiApplication.class, args);
    }
}
