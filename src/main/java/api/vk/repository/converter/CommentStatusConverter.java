package api.vk.repository.converter;

import api.vk.model.enums.CommentStatusEnum;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter(autoApply = true)
public class CommentStatusConverter implements AttributeConverter<CommentStatusEnum, String> {
    @Override
    public String convertToDatabaseColumn(CommentStatusEnum attribute) {
        return attribute.getTitleEng();
    }

    @Override
    public CommentStatusEnum convertToEntityAttribute(String dbData) {
        return CommentStatusEnum.convertToEnum(dbData);
    }
}
