package api.vk.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import api.vk.exception.RateLimiterException;

import java.util.Random;
import java.util.concurrent.TimeUnit;

@Slf4j
@Component
public class RateLimiter {
    private final Random randomTimeout = new Random();

    public void waitRandomTimeout() {
        try {
            TimeUnit.MILLISECONDS.sleep(randomTimeout.nextLong(50L, 600L));
        } catch (InterruptedException e) {
            log.error("Ошибка ожидания!", e);
            throw new RateLimiterException(e.getMessage(), e);
        }
    }
}
