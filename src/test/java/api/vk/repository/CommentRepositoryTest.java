package api.vk.repository;

import api.vk.model.enums.CommentStatusEnum;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import api.vk.model.entity.Comment;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DisplayName("Репозиторий для VkWallComment должен")
@DataJpaTest
@ActiveProfiles("test")
class CommentRepositoryTest {
    public static final String TEST_COMMENT_TEXT = "Тестирование 1";
    public static final CommentStatusEnum PUBLISHED = CommentStatusEnum.PUBLISHED;
    public static final CommentStatusEnum NOT_VIEWED = CommentStatusEnum.NOT_VIEWED;

    @Autowired
    private CommentRepository commentRepository;

    @DisplayName("сохранять и получать по id сущность")
    @Test
    void saveAndFindById() {
        Comment firstComment = new Comment();
        firstComment.setText(TEST_COMMENT_TEXT);
        firstComment.setStatus(CommentStatusEnum.NOT_VIEWED);

        Comment savedComment = commentRepository.saveAndFlush(firstComment);
        Optional<Comment> findEntity = commentRepository.findById(savedComment.getId());

        assertTrue(findEntity.isPresent());
        Comment comment = findEntity.get();
        assertEquals(TEST_COMMENT_TEXT, comment.getText());
        assertEquals(NOT_VIEWED, comment.getStatus());
    }

    @DisplayName("получать сущность по CommentStatus")
    @Test
    void findByCommentStatus() {
        Comment firstComment = new Comment();
        firstComment.setText(TEST_COMMENT_TEXT);
        firstComment.setStatus(NOT_VIEWED);

        List<Comment> content = commentRepository.findByStatus(NOT_VIEWED, null).getContent();

        assertEquals(1, content.size());
        assertEquals(firstComment, content.get(0));
    }

    @Test
    @Sql("/sql/create_comment.sql")
    @Sql(value = "/sql/delete_from_comment.sql", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
    @DisplayName("изменять статус комментария")
    void updateStatusById() {
        commentRepository.updateStatusById(PUBLISHED, 1L);

        Optional<Comment> findComment = commentRepository.findById(1L);

        assertEquals(CommentStatusEnum.PUBLISHED, findComment.get().getStatus());
    }
}


