package com.techentrance.techentrance.service;


import com.techentrance.techentrance.model.User;
import com.techentrance.techentrance.repo.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service @RequiredArgsConstructor @Transactional @Slf4j
public class LoginService {
    private final UserRepository userRepository;
    public User saveUser(User user) {
        return userRepository.save(user);
    }
    public User getUser(String email) {
        return userRepository.findByEmail(email);
    }
}
