package com.probaIT.ProbaIt.domain.service;

import com.probaIT.ProbaIt.domain.entities.User;
import com.probaIT.ProbaIt.domain.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import static com.probaIT.ProbaIt.domain.util.UserValidator.*;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository repository;

    private final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public User createUser(User user) {
        if (!isEmailValid(user.getEmail())) throw new RuntimeException("INVALID EMAIL");
        if (!isPasswordValid(user.getPassword())) throw new RuntimeException("INVALID PASSWORD");

        if (findByEmail(user.getEmail()) != null) throw new RuntimeException("Email already in use.");
        if (findByUsername(user.getUsername()) != null) throw new RuntimeException("Username already in use.");

        String encodedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);

        return repository.save(user);
    }

    public User findByEmail(String email) {
        return repository.findByEmail(email);
    }

    public User findByUsername(String username) {
        return repository.findByUsername(username);
    }

    public User login(String username, String email, String password) {
        User check;
        if (isEmailValid(email)) {
            if (repository.findByEmail(email) != null) {
                check = repository.findByEmail(email);
                if (passwordEncoder.matches(password, check.getEmail())) {
                    return check;
                } else {
                    throw new RuntimeException("Incorrect password");
                }
            } else {
                throw new RuntimeException("Could not find user with this email");
            }
        } else {
            if (repository.findByUsername(username) != null) {
                check = repository.findByUsername(username);
                if (passwordEncoder.matches(password, check.getPassword())) {
                    return check;
                } else {
                    throw new RuntimeException("Incorrect password");
                }
            } else {
                throw new RuntimeException("Could not find user with this username");
            }
        }
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
