package com.example.auth.domain.dto;

import com.example.auth.entities.UserRole;

public record RegisterDTO(String nome, String email, String login, String password, UserRole role) {
}
