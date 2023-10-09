package com.example.auth.services.adminService;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.example.auth.entities.User;
import com.example.auth.entities.UserRole;

@Service
public class UserService {

    public boolean hasRole(User user, UserRole role) {
        if (user == null || role == null) {
            return false; // Retornar false se o usuário ou a role forem nulos
        }

        // Verifique se a role do usuário é igual à role especificada
        return user.getRole() == role;
    }

    public User getAuthenticatedUser() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof UserDetails) {
            return (User) principal;
        }
        return null; // Retorna null se o usuário não estiver autenticado ou não for do tipo User
    }
}
