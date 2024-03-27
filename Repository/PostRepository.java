package com.example.blogsystem.Repository;

import com.example.blogsystem.Api.ApiException;
import com.example.blogsystem.Api.ApiResponse;
import com.example.blogsystem.Model.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface PostRepository extends JpaRepository<Post, Integer> {

    List<Post> getPostsByUserId(Integer id);

    List<Post> getPostsByTitle(String title);

    List<Post> getPostsByPublishDateBetween(LocalDate startDate, LocalDate endDate);

    @Query("select p from Post p where p.categoryId = ?1")
    List<Post> getPostsByCategoryId(Integer categoryId);

}
