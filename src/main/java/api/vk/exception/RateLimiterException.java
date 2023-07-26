package api.vk.exception;

public class RateLimiterException extends RuntimeException {
    public RateLimiterException(String message, Throwable cause) {
        super(message, cause);
    }
}
