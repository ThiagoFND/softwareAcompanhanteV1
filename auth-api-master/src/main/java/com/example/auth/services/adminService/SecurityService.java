package com.example.auth.services.adminService;

import com.example.auth.entities.User;
import com.example.auth.entities.UserRole;

public class SecurityService {
    
    // Método para verificar se um usuário possui uma função específica (UserRole)
    public boolean hasRole(User user, UserRole role) {
    if (user == null || role == null) {
        return false; // Retornar false se o usuário ou a role forem nulos
    }

    // Obtenha a role do usuário diretamente
    UserRole userRole = user.getRole();

    // Verifique se a role do usuário é igual à role específica
    return userRole == role;
}

}
