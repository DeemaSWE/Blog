package com.example.blogsystem.Repository;

import com.example.blogsystem.Model.Comment;
import com.example.blogsystem.Model.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Integer> {
    List<Comment> findCommentsByPostId(Integer postId);

    @Query("select c from Comment c where c.content LIKE %:keyword%")
    List<Comment> searchComments(String keyword);

    @Query("select c from Comment c where c.commentDate < ?1")
    List<Comment> getCommentsBeforeDate(LocalDate date);

    @Query("select c from Comment c where c.userId = ?1")
    List<Comment> getCommentsByUserId(Integer userId);

}
