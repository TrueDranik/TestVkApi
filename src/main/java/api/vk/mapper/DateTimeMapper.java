package api.vk.mapper;

import org.mapstruct.Mapper;

import java.sql.Timestamp;
import java.time.Instant;
import java.time.LocalDateTime;
import java.util.TimeZone;

@Mapper(componentModel = "spring")
public interface DateTimeMapper {
    default LocalDateTime convertTimestampToLocalDateTime(Integer timestamp) {
        Instant instant = Instant.ofEpochSecond(timestamp);
        TimeZone timeZone = TimeZone.getDefault();

        return LocalDateTime.ofInstant(instant, timeZone.toZoneId());
    }

    default Integer convertLocalDateTimeToTimestamp(LocalDateTime localDateTime) {
        Timestamp timestamp = Timestamp.valueOf(localDateTime);
        String string = timestamp.toString();

        return Integer.valueOf(string);
    }
}
