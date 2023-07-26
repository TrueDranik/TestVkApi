package api.vk.mapper;

import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.springframework.beans.factory.annotation.Autowired;
import api.vk.model.dto.CommentDto;
import api.vk.model.entity.Comment;
import api.vk.repository.UserInfoRepository;
import api.vk.repository.PostRepository;

import javax.persistence.EntityNotFoundException;

@Mapper(componentModel = "spring")
public abstract class CommentMapper implements BaseMapper<Comment, CommentDto> {
    @Autowired
    PostRepository postRepository;
    @Autowired
    UserInfoRepository userInfoRepository;

    @Mapping(target = "status", ignore = true)
    @Override
    public abstract Comment dtoToDomain(CommentDto commentDto);

    @AfterMapping
    public void setRepoValue(CommentDto dto, @MappingTarget Comment comment) {
        comment.setPost(postRepository.findById(dto.getId())
                .orElseThrow(() -> new EntityNotFoundException("PostId not found")));
        comment.setUserInfo(userInfoRepository.findById(dto.getId())
                .orElseThrow(() -> new EntityNotFoundException("UserId not found")));
    }

    @AfterMapping
    public void setDtoValue(@MappingTarget CommentDto dto, Comment comment) {
        dto.setUserId(comment.getUserInfo().getId());
        dto.setPostId(comment.getPost().getId());
    }
}
