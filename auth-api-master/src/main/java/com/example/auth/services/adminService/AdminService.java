package com.example.auth.services.adminService;

import java.util.List;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.auth.domain.dto.UserDTO;
import com.example.auth.entities.User;
import com.example.auth.entities.UserRole;
import com.example.auth.exceptions.UserAccessNegativeException;
import com.example.auth.mapper.DozerMapper;
import com.example.auth.repositories.UserRepository;
import com.example.auth.services.acompanhanteService.ValorService;


@Service
public class AdminService {
    
    private Logger logger = Logger.getLogger(ValorService.class.getName());

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserService userService;

    @Transactional(readOnly = true)
    public List<UserDTO> findById(String id) throws UserAccessNegativeException {
        
        // Verifique se o usuário está autenticado
        User user = userService.getAuthenticatedUser();
        if (user == null || !userService.hasRole(user, UserRole.ADMIN) && !id.equals(user.getId())) {
            logger.info("Access recused!");
            throw new UserAccessNegativeException("Acesso negado");
        }

        // Continue com a operação, pois o usuário está autenticado e autorizado
        return DozerMapper.parseListObjects(userRepository.findAll(), UserDTO.class);
    
    }
}
