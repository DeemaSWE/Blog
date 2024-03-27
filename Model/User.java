package com.example.blogsystem.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Integer userId;
    @NotEmpty(message = "Username cannot be empty")
    @Size(min = 4, message = "Username must be at least 4 characters")
    @Column(columnDefinition = "varchar(20) not null unique check(length(username)>=4)")
    private String username;
    @NotEmpty(message = "Password cannot be empty")
    @Column(columnDefinition = "varchar(20) not null")
    private String password;
    @NotEmpty(message = "Email cannot be empty")
    @Email(message = "Email should be valid")
    @Column(columnDefinition = "varchar(50) not null unique")
    private String email;
    @NotNull(message = "Registration date cannot be empty")
    @Column(columnDefinition = "date default current_date", name = "registration_date")
    private LocalDate registrationDate;
}
