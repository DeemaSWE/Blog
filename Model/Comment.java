package com.example.blogsystem.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "comment_id")
    private Integer commentId;
    @NotNull(message = "User ID cannot be empty")
    @Column(columnDefinition = "int not null", name = "user_id")
    private Integer userId;
    @NotNull(message = "Post ID cannot be empty")
    @Column(columnDefinition = "int not null", name = "post_id")
    private Integer postId;
    @NotNull(message = "Content cannot be empty")
    @Column(columnDefinition = "varchar(150) not null")
    private String content;
    @NotNull(message = "Comment date cannot be empty")
    @Column(columnDefinition = "date not null", name = "comment_date")
    private LocalDate commentDate;
}
