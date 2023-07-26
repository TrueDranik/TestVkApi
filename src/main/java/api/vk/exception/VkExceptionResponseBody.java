package api.vk.exception;

import lombok.Builder;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@Builder
public class VkExceptionResponseBody {
    private HttpStatus status;
    private String message;
    private LocalDateTime dateTime;
}
