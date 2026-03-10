package dev.java10x.user.service;


import dev.java10x.user.domain.UserModel;
import dev.java10x.user.dto.UserDto;
import dev.java10x.user.repositories.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Transactional
    public UserModel save(UserModel userModel) {
        return userModel = userRepository.save(userModel);

    }

}
