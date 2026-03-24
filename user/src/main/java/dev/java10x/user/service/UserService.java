package dev.java10x.user.service;


import dev.java10x.user.domain.UserModel;
import dev.java10x.user.dto.UserDto;
import dev.java10x.user.producer.UserProducer;
import dev.java10x.user.repositories.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.beans.BeanUtils;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class UserService {

    @Autowired
    private final UserRepository userRepository;

    private final UserProducer userProducer;

    public UserService(UserRepository userRepository, UserProducer userProducer) {
        this.userRepository = userRepository;
        this.userProducer = userProducer;
    }

    @Transactional
    public UserModel saveAndSend(UserModel userModel) {
        UserModel saved = userRepository.save(userModel);
        userProducer.sendMessage(saved);
        return saved;
    }

    @Transactional
    public void deleteById(UUID id) {
        if (!userRepository.existsById(id)) {
            throw new RuntimeException("Usuário não encontrado com id: " + id);
        }
        userRepository.deleteById(id);
    }

    @Transactional
    public UserModel update(UUID id, UserModel userModel) {
        var existingOpt = userRepository.findById(id);
        if (existingOpt.isEmpty()) {
            throw new RuntimeException("Usuário não encontrado com id: " + id);
        }
        var existing = existingOpt.get();
        // copia propriedades do DTO/model recebido para a entidade existente, ignorando o id
        BeanUtils.copyProperties(userModel, existing, "id");
        return userRepository.save(existing);
    }

    public List<UserModel> findByFilter(String name, String email) {
        List<UserModel> all = userRepository.findAll();
        return all.stream()
                .filter(u -> {
                    boolean matches = true;
                    if (name != null && !name.isBlank()) {
                        var n = u.getName();
                        matches = matches && n != null && n.toLowerCase().contains(name.toLowerCase());
                    }
                    if (email != null && !email.isBlank()) {
                        var e = u.getEmail();
                        matches = matches && e != null && e.toLowerCase().contains(email.toLowerCase());
                    }
                    return matches;
                })
                .collect(Collectors.toList());
    }
}
