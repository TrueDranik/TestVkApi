package api.vk.repository.converter;

import api.vk.model.enums.CommentStatusEnum;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@DisplayName("Конвертер для CommentStatus должен")
@RunWith(MockitoJUnitRunner.class)
class CommentStatusConverterTest {

    private final CommentStatusConverter converter = new CommentStatusConverter();

    @Test
    @DisplayName("возвращать titlteEng константы ")
    void convertToDatabaseColumn() {
        CommentStatusEnum notViewed = CommentStatusEnum.NOT_VIEWED;

        String notViewedInDataBase = converter.convertToDatabaseColumn(notViewed);

        assertEquals(notViewed.getTitleEng(), notViewedInDataBase);
    }

    @Test
    @DisplayName("возвращать константу по titleEng")
    void convertToEntity() {
        CommentStatusEnum notViewed = CommentStatusEnum.NOT_VIEWED;
        String notViewedTitleEng = notViewed.getTitleEng();

        CommentStatusEnum commentStatusEnum = converter.convertToEntityAttribute(notViewedTitleEng);

        assertEquals(notViewed, commentStatusEnum);
    }

    @Test
    @DisplayName("возвращать исключение при переданном несуществующем значении или null")
    void getIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () -> converter.convertToEntityAttribute("empty"));
        assertThrows(IllegalArgumentException.class, () -> converter.convertToEntityAttribute(null));
    }
}