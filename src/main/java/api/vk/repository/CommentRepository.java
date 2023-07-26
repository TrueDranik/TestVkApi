package api.vk.repository;

import api.vk.model.enums.CommentStatusEnum;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import api.vk.model.entity.Comment;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {
    boolean existsByVkId(Integer commentId);

    Page<Comment> findByStatus(CommentStatusEnum commentStatus, Pageable pageable);

    @Modifying
    @Query("update Comment c set c.status=:status where c.id=:id")
    void updateStatusById(@Param("status") CommentStatusEnum status, @Param("id") Long id);
}
