package com.example.auth.services.acompanhanteService;

import java.util.List;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.auth.domain.dto.UserDTO;
import com.example.auth.domain.dto.informacao.valores.ValorDTO;
import com.example.auth.entities.User;
import com.example.auth.entities.informacao.valores.Valor;
import com.example.auth.exceptions.ResourceNotFoundException;
import com.example.auth.mapper.DozerMapper;
import com.example.auth.repositories.UserRepository;
import com.example.auth.repositories.ValorRepository;

@Service
public class ValorService {
    
    private Logger logger = Logger.getLogger(ValorService.class.getName());

    @Autowired
	private ValorRepository valorRepository;

    @Autowired
    private UserRepository userRepository;

	public List<ValorDTO> findAll() {

		logger.info("Finding all valores!");

		return DozerMapper.parseListObjects(valorRepository.findAll(), ValorDTO.class);
	}

	public ValorDTO findById(String id) {

		logger.info("Finding one valores!");

		var entity = valorRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("No records found for this ID!"));
		return DozerMapper.parseObject(entity, ValorDTO.class);
	}

	public ValorDTO create(ValorDTO valor) {

        logger.info("Creating one servicos!");

        // Verifique se o UserDTO tem um ID válido e corresponde a um usuário existente
        UserDTO userDTO = valor.getUsuario();
        if (userDTO == null || userDTO.getId() == null) {
            throw new ResourceNotFoundException("UserDTO ID is required.");
        }
        
        // Consulte o usuário correspondente no banco de dados usando o ID do UserDTO
        User user = userRepository.findById(userDTO.getId())
                .orElseThrow(() -> new ResourceNotFoundException("User not found for the given ID."));
        
        // Crie o objeto Endereco a partir do EnderecoDTO
        Valor entity = DozerMapper.parseObject(valor, Valor.class);
        entity.setUsuario(user); // Associe o usuário ao endereço
        
        // Salve o Endereco no banco de dados
        var vo = DozerMapper.parseObject(valorRepository.save(entity), ValorDTO.class);
        return vo;
    }

	public ValorDTO update(ValorDTO valor) {

		logger.info("Updating one valores!");

		var entity = valorRepository.findById(valor.getId())
				.orElseThrow(() -> new ResourceNotFoundException("No records found for this ID!"));

		entity.setId(valor.getId());


		var vo = DozerMapper.parseObject(valorRepository.save(entity), ValorDTO.class);
		return vo;
	}

	public void delete(String id) {

		logger.info("Deleting one valores!");

		var entity = valorRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("No records found for this ID!"));
		valorRepository.delete(entity);
	}
}
