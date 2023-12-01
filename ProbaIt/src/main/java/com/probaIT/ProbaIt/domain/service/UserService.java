package com.probaIT.ProbaIt.domain.service;

import com.probaIT.ProbaIt.domain.entities.User;
import com.probaIT.ProbaIt.domain.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import static com.probaIT.ProbaIt.domain.util.UserValidator.*;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository repository;

    public User createUser(User user) {
        if (!isEmailValid(user.getEmail())) throw new RuntimeException("INVALID EMAIL");
        if (!isPasswordValid(user.getPassword())) throw new RuntimeException("INVALID PASSWORD");

        if (findByEmail(user.getEmail()) != null) throw new RuntimeException("Email already in use.");
        if (findByUsername(user.getUsername()) != null) throw new RuntimeException("Username already in use.");

        return repository.save(user);
    }

    public User findByEmail(String email) {
        return repository.findByEmail(email);
    }

    public User findByUsername(String username) {
        return repository.findByUsername(username);
    }

    public User login(String identifier, String password) {
        User check;
        if (isEmailValid(identifier)) {
            if (repository.findByEmail(identifier) != null) {
                check = repository.findByEmail(identifier);
                if (check.getPassword().equals(password)) return check;
            } else {
                return new User(0L);
            }
        } else {
            if(repository.findByUsername(identifier) != null){
                check = repository.findByUsername(identifier);
                if(check.getPassword().equals(password)) return check;
            } else {
                return new User(0L);
            }
        }
        return new User(0L);
    }

    @Cacheable("users")
    public User getUserById(Long id) {
        return repository.findById(id).orElseGet(() -> {
            log.warn("user not found for id {}", id);
            throw new RuntimeException("Not Found");
        });
    }

    public void removeUser(Long id) {
        User user = getUserById(id);
        repository.delete(user);
    }

    public User updateUser(User user) {
        return repository.save(user);
    }

}
