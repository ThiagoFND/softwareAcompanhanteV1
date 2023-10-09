package com.example.auth.controllers.adminController;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.auth.domain.dto.UserDTO;
import com.example.auth.exceptions.UserAccessNegativeException;
import com.example.auth.services.adminService.AdminService;

@RestController
@RequestMapping("/api/admin")
public class AdminController {

    @Autowired
    private AdminService adminService;

    // Endpoint para obter todos os usuários
	@PostMapping(value = "/list/users",
        produces = MediaType.APPLICATION_JSON_VALUE)
        @PreAuthorize("hasRole('ADMIN')")
    public List<UserDTO> findAllUsers(@RequestParam String id) throws UserAccessNegativeException {
        return adminService.findById(id);
    }
}
