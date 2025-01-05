package com.myjavacrudapp.controller;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.myjavacrudapp.exceptions.CustomException;
import com.myjavacrudapp.model.user.User;
import com.myjavacrudapp.model.user.UserDto;
import com.myjavacrudapp.service.UserService;

@RestController
@RequestMapping("/api/usuarios")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping
    public ResponseEntity<UserDto> create(@RequestBody UserDto userDto) {
        if (userDto.getNome() == null || userDto.getEmail() == null) {
            throw new CustomException("Nome e Email são obrigatórios");
        }
        User user = convertToEntity(userDto);
        User createdUser = userService.save(user);
        UserDto createdUserDto = convertToDto(createdUser);
        return ResponseEntity.ok(createdUserDto);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDto> read(@PathVariable Long id) {
        Optional<User> user = userService.findById(id);
        if (user.isEmpty()) {
            throw new CustomException("Usuário não encontrado com o ID: " + id);
        }
        return ResponseEntity.ok(convertToDto(user.get()));
    }

    @GetMapping
    public ResponseEntity<List<UserDto>> readAll() {
        List<User> users = userService.findAll();
        List<UserDto> userDtos = users.stream()
                                      .map(this::convertToDto)
                                      .collect(Collectors.toList());
        return ResponseEntity.ok(userDtos);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserDto> update(@PathVariable Long id, @RequestBody UserDto userDto) {
        if (!userService.findById(id).isPresent()) {
            throw new CustomException("Usuário não encontrado com o ID: " + id);
        }
        User user = convertToEntity(userDto);
        user.setId(id);
        User updatedUser = userService.save(user);
        UserDto updatedUserDto = convertToDto(updatedUser);
        return ResponseEntity.ok(updatedUserDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        if (!userService.findById(id).isPresent()) {
            throw new CustomException("Usuário não encontrado com o ID: " + id);
        }
        userService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    private User convertToEntity(UserDto userDto) {
        User user = new User();
        user.setNome(userDto.getNome());
        user.setEmail(userDto.getEmail());
        return user;
    }

    private UserDto convertToDto(User user) {
        UserDto userDto = new UserDto();
        userDto.setId(user.getId());
        userDto.setNome(user.getNome());
        userDto.setEmail(user.getEmail());
        return userDto;
    }
}