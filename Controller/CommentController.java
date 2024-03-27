package com.example.blogsystem.Controller;

import com.example.blogsystem.Api.ApiResponse;
import com.example.blogsystem.Model.Comment;
import com.example.blogsystem.Service.CommentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/v1/comment")
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;

    @GetMapping("/get-all")
    public ResponseEntity getAllComments(){
        return ResponseEntity.status(200).body(commentService.getAllComments());
    }

    @PostMapping("/add")
    public ResponseEntity addComment(@RequestBody @Valid Comment comment, Errors errors){
        if(errors.hasErrors())
            return ResponseEntity.status(400).body(errors.getFieldError().getDefaultMessage());

        commentService.addComment(comment);
        return ResponseEntity.status(200).body(new ApiResponse("Comment added successfully"));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity updateComment(@PathVariable Integer id, @RequestBody @Valid Comment comment, Errors errors){
        if(errors.hasErrors())
            return ResponseEntity.status(400).body(errors.getFieldError().getDefaultMessage());

        commentService.updateComment(comment, id);
        return ResponseEntity.status(200).body(new ApiResponse("Comment updated successfully"));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteComment(@PathVariable Integer id){
        commentService.deleteComment(id);
        return ResponseEntity.status(200).body(new ApiResponse("Comment deleted successfully"));
    }

    // 5 - Get post comments
    @GetMapping("/post/{postId}")
    public ResponseEntity getCommentsByPostId(@PathVariable Integer postId){
        List<Comment> comments = commentService.getPostComments(postId);

        if(comments.isEmpty())
            return ResponseEntity.status(400).body(new ApiResponse("No comments found for post with id " + postId));

        return ResponseEntity.status(200).body(comments);
    }

    // 6 - Search comments
    @GetMapping("/search/{keyword}")
    public ResponseEntity searchComments(@PathVariable String keyword){
        List<Comment> comments = commentService.searchComments(keyword);

        if(comments.isEmpty())
            return ResponseEntity.status(400).body(new ApiResponse("No comments found for keyword " + keyword));

        return ResponseEntity.status(200).body(comments);
    }

    // 7 - Get comments before a certain date
    @GetMapping("/before-date/{date}")
    public ResponseEntity getCommentsBeforeDate(@PathVariable LocalDate date){
        List<Comment> comments = commentService.getCommentsBeforeDate(date);

        if(comments.isEmpty())
            return ResponseEntity.status(400).body(new ApiResponse("No comments found before date " + date));

        return ResponseEntity.status(200).body(comments);
    }

    // 8 - Get user comments
    @GetMapping("/user/{userId}")
    public ResponseEntity getCommentsByUserId(@PathVariable Integer userId){
        List<Comment> comments = commentService.getCommentsByUserId(userId);

        if(comments.isEmpty())
            return ResponseEntity.status(400).body(new ApiResponse("No comments found for user with id " + userId));

        return ResponseEntity.status(200).body(comments);
    }
}
