package com.example.blogsystem.Controller;

import com.example.blogsystem.Api.ApiResponse;
import com.example.blogsystem.Model.Comment;
import com.example.blogsystem.Model.Post;
import com.example.blogsystem.Service.PostService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/v1/post")
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;

    @GetMapping("/get-all")
    public ResponseEntity getAllPosts(){
        return ResponseEntity.status(200).body(postService.getAllPosts());
    }

    @PostMapping("/add")
    public ResponseEntity addPost(@RequestBody @Valid Post post, Errors errors){
        if(errors.hasErrors())
            return ResponseEntity.status(400).body(errors.getFieldError().getDefaultMessage());

        postService.addPost(post);
        return ResponseEntity.status(200).body(new ApiResponse("Post added successfully"));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity updatePost(@PathVariable Integer id, @RequestBody @Valid Post post, Errors errors){
        if(errors.hasErrors())
            return ResponseEntity.status(400).body(errors.getFieldError().getDefaultMessage());

        postService.updatePost(post, id);
        return ResponseEntity.status(200).body(new ApiResponse("Post updated successfully"));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity deletePost(@PathVariable Integer id){
        postService.deletePost(id);
        return ResponseEntity.status(200).body(new ApiResponse("Post deleted successfully"));
    }

    // 1 - Get user posts
    @GetMapping("/user/{userId}")
    public ResponseEntity getUserPosts(@PathVariable Integer userId){
        List<Post> posts = postService.getUserPosts(userId);

        if(posts.isEmpty())
            return ResponseEntity.status(400).body(new ApiResponse("No posts found for user with id " + userId));

        return ResponseEntity.status(200).body(posts);
    }

    // 2 - Get posts by title
    @GetMapping("/title/{title}")
    public ResponseEntity searchByTitle(@PathVariable String title){
        List<Post> posts = postService.searchByTitle(title);

        if(posts.isEmpty())
            return ResponseEntity.status(400).body(new ApiResponse("No posts found with title " + title));

        return ResponseEntity.status(200).body(posts);
    }

    // 3 - Get posts between start and end date
    @GetMapping("/between/{startDate}/{endDate}")
    public ResponseEntity getPostsByPublishDateBetween(@PathVariable LocalDate startDate, @PathVariable LocalDate endDate){
        List<Post> posts = postService.getPostsByPublishDateBetween(startDate, endDate);

        if(posts.isEmpty())
            return ResponseEntity.status(400).body(new ApiResponse("No posts found between " + startDate + " and " + endDate));

        return ResponseEntity.status(200).body(posts);
    }

    // 4 - Get Posts by category
    @GetMapping("/category/{categoryId}")
    public ResponseEntity getPostsByCategoryId(@PathVariable Integer categoryId){
        List<Post> posts = postService.getPostsByCategoryId(categoryId);

        if(posts.isEmpty())
            return ResponseEntity.status(400).body(new ApiResponse("No posts found for category with id " + categoryId));

        return ResponseEntity.status(200).body(posts);
    }
}
