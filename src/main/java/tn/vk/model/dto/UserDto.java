package tn.vk.model.dto;

import lombok.Getter;
import lombok.Setter;

import java.net.URI;

@Getter
@Setter
public class UserDto {
    private String firstName;
    private String lastName;
    private URI photo50;
}
