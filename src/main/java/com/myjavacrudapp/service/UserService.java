package com.myjavacrudapp.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.myjavacrudapp.model.user.User;
import com.myjavacrudapp.repository.UserRepository;

@Service
@Transactional
public class UserService {

    private final UserRepository usuarioRepository;

    @Autowired
    public UserService(UserRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    public List<User> findAll() {
        return usuarioRepository.findAll();
    }

    public Optional<User> findById(Long id) {
        return usuarioRepository.findById(id);
    }

    public User save(User usuario) {
        return usuarioRepository.save(usuario);
    }

    public void deleteById(Long id) {
        usuarioRepository.deleteById(id);
    }
}