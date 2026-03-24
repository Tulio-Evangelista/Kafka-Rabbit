package dev.java10x.user.controller;


import dev.java10x.user.domain.UserModel;
import dev.java10x.user.dto.UserDto;
import dev.java10x.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.beans.BeanUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.UUID;

@RestController
public class UserController {

    @Autowired
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/users")
    public ResponseEntity<UserModel> createUser(@RequestBody UserDto userDto) {
        var userModel = new UserModel();
        BeanUtils.copyProperties(userDto, userModel);
        userModel = userService.saveAndSend(userModel);
        return ResponseEntity.status(HttpStatus.CREATED).body(userModel);
    }

    // rota de exclusão
    @DeleteMapping("/users/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable UUID id) {
        // userService.deleteById deve ser implementado para remover ou lançar exceção se não encontrado
        userService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    // rota de atualização completa (substitui dados do usuário)
    @PutMapping("/users/{id}")
    public ResponseEntity<UserModel> updateUser(@PathVariable UUID id, @RequestBody UserDto userDto) {
        var userModel = new UserModel();
        BeanUtils.copyProperties(userDto, userModel);
        // userService.update deve aplicar as mudanças e retornar o usuário atualizado
        var updated = userService.update(id, userModel);
        return ResponseEntity.ok(updated);
    }

    // rota de busca / filtro: aceita name e/ou email como query params
    @GetMapping("/users")
    public ResponseEntity<List<UserModel>> searchUsers(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String email
    ) {
        // userService.findByFilter deve retornar lista filtrada (ou todos se ambos nulos)
        List<UserModel> results = userService.findByFilter(name, email);
        return ResponseEntity.ok(results);
    }

}