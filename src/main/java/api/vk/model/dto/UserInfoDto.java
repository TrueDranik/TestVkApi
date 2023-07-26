package api.vk.model.dto;

import lombok.Getter;
import lombok.Setter;

import java.net.URI;

@Getter
@Setter
public class UserInfoDto {
    private long id;
    private int vkId;
    private String firstName;
    private String lastName;
    private URI photoSize50;
}
