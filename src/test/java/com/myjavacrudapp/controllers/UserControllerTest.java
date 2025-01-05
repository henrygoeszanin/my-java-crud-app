package com.myjavacrudapp.controllers;

import java.util.Arrays;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.myjavacrudapp.controller.UserController;
import com.myjavacrudapp.model.user.User;
import com.myjavacrudapp.model.user.UserDto;
import com.myjavacrudapp.service.UserService;

@WebMvcTest(UserController.class)
public class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService userService;

    @Autowired
    private ObjectMapper objectMapper;

    private User user;
    private UserDto userDto;

    @BeforeEach
    public void setUp() {
        user = new User();
        user.setId(1L);
        user.setNome("John Doe");
        user.setEmail("john.doe@example.com");

        userDto = new UserDto();
        userDto.setId(1L);
        userDto.setNome("John Doe");
        userDto.setEmail("john.doe@example.com");
    }

    @Test
    public void testCreateUser() throws Exception {
        Mockito.when(userService.save(Mockito.any(User.class))).thenReturn(user);

        mockMvc.perform(post("/api/usuarios")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(userDto)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(user.getId()))
                .andExpect(jsonPath("$.nome").value(user.getNome()))
                .andExpect(jsonPath("$.email").value(user.getEmail()));
    }

    @Test
    public void testReadUser() throws Exception {
        Mockito.when(userService.findById(Mockito.anyLong())).thenReturn(Optional.of(user));

        mockMvc.perform(get("/api/usuarios/{id}", 1L))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(user.getId()))
                .andExpect(jsonPath("$.nome").value(user.getNome()))
                .andExpect(jsonPath("$.email").value(user.getEmail()));
    }

    @Test
    public void testReadAllUsers() throws Exception {
        Mockito.when(userService.findAll()).thenReturn(Arrays.asList(user));

        mockMvc.perform(get("/api/usuarios"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(user.getId()))
                .andExpect(jsonPath("$[0].nome").value(user.getNome()))
                .andExpect(jsonPath("$[0].email").value(user.getEmail()));
    }

    @Test
    public void testUpdateUser() throws Exception {
        Mockito.when(userService.findById(Mockito.anyLong())).thenReturn(Optional.of(user));
        Mockito.when(userService.save(Mockito.any(User.class))).thenReturn(user);

        mockMvc.perform(put("/api/usuarios/{id}", 1L)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(userDto)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(user.getId()))
                .andExpect(jsonPath("$.nome").value(user.getNome()))
                .andExpect(jsonPath("$.email").value(user.getEmail()));
    }

    @Test
    public void testDeleteUser() throws Exception {
        Mockito.when(userService.findById(Mockito.anyLong())).thenReturn(Optional.of(user));

        mockMvc.perform(delete("/api/usuarios/{id}", 1L))
                .andExpect(status().isNoContent());
    }
}