package com.example.blogsystem.Service;

import com.example.blogsystem.Api.ApiException;
import com.example.blogsystem.Model.Post;
import com.example.blogsystem.Repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;
    private final UserService userService;
    private final CategoryService categoryService;

    public List<Post> getAllPosts() {
        return postRepository.findAll();
    }

    public void addPost(Post post){
        userService.findUserById(post.getUserId());
        categoryService.findCategoryById(post.getCategoryId());
        postRepository.save(post);
    }

    public void updatePost(Post updatedPost, Integer id){
        Post post = findPostById(id);

        userService.findUserById(updatedPost.getUserId());
        categoryService.findCategoryById(updatedPost.getCategoryId());

        post.setTitle(updatedPost.getTitle());
        post.setContent(updatedPost.getContent());
        post.setPublishDate(updatedPost.getPublishDate());
        post.setUserId(updatedPost.getUserId());
        post.setCategoryId(updatedPost.getCategoryId());

        postRepository.save(post);
    }

    public void deletePost(Integer id){
        postRepository.delete(findPostById(id));
    }

    // 1 - Get user posts
    public List<Post> getUserPosts(Integer userId){
        return postRepository.getPostsByUserId(userId);
    }

    // 2 - Search by title
    public List<Post> searchByTitle(String title){
        return postRepository.getPostsByTitle(title);
    }

    // 3 - Get posts between start and end date
    public List<Post> getPostsByPublishDateBetween(LocalDate startDate, LocalDate endDate){
        return postRepository.getPostsByPublishDateBetween(startDate, endDate);
    }

    // 4 - Get posts by category
    public List<Post> getPostsByCategoryId(Integer categoryId){
        return postRepository.getPostsByCategoryId(categoryId);
    }

    public Post findPostById(Integer id) {
        return postRepository.findById(id)
                .orElseThrow(() -> new ApiException("Post with id " + id + " not found"));
    }

}
