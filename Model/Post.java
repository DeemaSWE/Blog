package com.example.blogsystem.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "post_id")
    private Integer postId;
    @NotNull(message = "Category ID cannot be empty")
    @Column(columnDefinition = "int not null", name = "category_id")
    private Integer categoryId;
    @NotEmpty(message = "Title cannot be empty")
    @Column(columnDefinition = "varchar(50) not null")
    private String title;
    @NotEmpty(message = "Content cannot be empty")
    @Column(columnDefinition = "varchar(250) not null")
    private String content;
    @NotNull(message = "User ID cannot be empty")
    @Column(columnDefinition = "int not null", name = "user_id")
    private Integer userId;
    @NotNull(message = "Publish date cannot be empty")
    @Column(columnDefinition = "date not null", name = "publish_date")
    private LocalDate publishDate;
}
