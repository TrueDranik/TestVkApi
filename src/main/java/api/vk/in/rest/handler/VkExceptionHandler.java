package api.vk.in.rest.handler;

import api.vk.exception.VkException;
import api.vk.exception.VkExceptionResponseBody;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;

@Slf4j
@ControllerAdvice
public class VkExceptionHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler(VkException.class)
    public VkExceptionResponseBody handleVkException(VkException e) {
        log.error("Ошибка выполнения запроса", e);
        return VkExceptionResponseBody.builder()
                .message(e.getMessage())
                .status(HttpStatus.BAD_REQUEST)
                .dateTime(LocalDateTime.now())
                .build();
    }
}
