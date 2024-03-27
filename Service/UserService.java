package com.example.blogsystem.Service;

import com.example.blogsystem.Api.ApiException;
import com.example.blogsystem.Model.User;
import com.example.blogsystem.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public List<User> getAllUsers(){
        return userRepository.findAll();
    }

    public void addUser(User user){
        String email = user.getEmail();
        if(userRepository.existsUserByEmail(email))
            throw new ApiException("User with email " + email + " already exists");

        String username = user.getUsername();
        if(userRepository.existsUserByUsername(username))
            throw new ApiException("User with username " + username + " already exists");

        userRepository.save(user);
    }

    public void updateUser(User updatedUser, Integer id){
        User user = findUserById(id);

        String updatedEmail = updatedUser.getEmail();
        if(userRepository.existsUserByEmail(updatedEmail) && !user.getEmail().equals(updatedEmail))
            throw new ApiException("User with email " + updatedEmail + " already exists");

        String updatedUsername = updatedUser.getUsername();
        if(userRepository.existsUserByUsername(updatedUsername) && !user.getUsername().equals(updatedUsername))
            throw new ApiException("User with username " + updatedUsername + " already exists");

        user.setUsername(updatedUser.getUsername());
        user.setPassword(updatedUser.getPassword());
        user.setEmail(updatedUser.getEmail());
        user.setRegistrationDate(updatedUser.getRegistrationDate());

        userRepository.save(user);
    }

    public void deleteUser(Integer id){
        userRepository.delete(findUserById(id));
    }

    public User findUserById(Integer id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new ApiException("User with id " + id + " not found"));
    }
}
