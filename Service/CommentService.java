package com.example.blogsystem.Service;

import com.example.blogsystem.Api.ApiException;
import com.example.blogsystem.Model.Comment;
import com.example.blogsystem.Repository.CommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentService {

    private final CommentRepository commentRepository;
    private final UserService userService;
    private final PostService postService;

    public List<Comment> getAllComments() {
        return commentRepository.findAll();
    }

    public void addComment(Comment comment){
        userService.findUserById(comment.getUserId());
        postService.findPostById(comment.getPostId());
        commentRepository.save(comment);
    }

    public void updateComment(Comment updatedComment, Integer id){
        Comment comment = findCommentById(id);

        userService.findUserById(updatedComment.getUserId());
        postService.findPostById(updatedComment.getPostId());

        comment.setContent(updatedComment.getContent());
        comment.setCommentDate(updatedComment.getCommentDate());
        comment.setUserId(updatedComment.getUserId());
        comment.setPostId(updatedComment.getPostId());

        commentRepository.save(comment);
    }

    public void deleteComment(Integer id){
        commentRepository.delete(findCommentById(id));
    }

    // 5 - Get post comments
    public List<Comment> getPostComments(Integer postId) {
        return commentRepository.findCommentsByPostId(postId);
    }

    // 6 - Search comments
    public List<Comment> searchComments(String keyword) {
        return commentRepository.searchComments(keyword);
    }

    // 7 - Get comments before a certain date
    public List<Comment> getCommentsBeforeDate(LocalDate date) {
        return commentRepository.getCommentsBeforeDate(date);
    }

    // 8 - Get user comments
    public List<Comment> getCommentsByUserId(Integer userId) {
        return commentRepository.getCommentsByUserId(userId);
    }

    public Comment findCommentById(Integer id) {
        return commentRepository.findById(id)
                .orElseThrow(() -> new ApiException("Comment with id " + id + " not found"));
    }
}
