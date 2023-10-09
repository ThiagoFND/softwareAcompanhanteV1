package com.example.auth.services.acompanhanteService;

import java.util.List;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.auth.domain.dto.UserDTO;
import com.example.auth.domain.dto.informacao.pessoal.EnderecoDTO;
import com.example.auth.entities.User;
import com.example.auth.entities.informacao.pessoal.Endereco;
import com.example.auth.exceptions.ResourceNotFoundException;
import com.example.auth.mapper.DozerMapper;
import com.example.auth.repositories.EnderecoRepository;
import com.example.auth.repositories.UserRepository;

@Service
public class EnderecoService {
    
    private Logger logger = Logger.getLogger(EnderecoService.class.getName());

    @Autowired
	private EnderecoRepository enderecoRepository;

    @Autowired
    private UserRepository userRepository;

	public List<EnderecoDTO> findAll() {

		logger.info("Finding all endereco!");

		return DozerMapper.parseListObjects(enderecoRepository.findAll(), EnderecoDTO.class);
	}

	public EnderecoDTO findById(String id) {

		logger.info("Finding one endereco!");

		var entity = enderecoRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("No records found for this ID!"));
		return DozerMapper.parseObject(entity, EnderecoDTO.class);
	}

	public EnderecoDTO create(EnderecoDTO endereco) {

        logger.info("Creating one endereco!");

        // Verifique se o UserDTO tem um ID válido e corresponde a um usuário existente
        UserDTO userDTO = endereco.getUsuario();
        if (userDTO == null || userDTO.getId() == null) {
            throw new ResourceNotFoundException("UserDTO ID is required.");
        }
        
        // Consulte o usuário correspondente no banco de dados usando o ID do UserDTO
        User user = userRepository.findById(userDTO.getId())
                .orElseThrow(() -> new ResourceNotFoundException("User not found for the given ID."));
        
        // Crie o objeto Endereco a partir do EnderecoDTO
        Endereco entity = DozerMapper.parseObject(endereco, Endereco.class);
        entity.setUsuario(user); // Associe o usuário ao endereço
        
        // Salve o Endereco no banco de dados
        var vo = DozerMapper.parseObject(enderecoRepository.save(entity), EnderecoDTO.class);
        return vo;
    }


	public EnderecoDTO update(EnderecoDTO endereco) {

		logger.info("Updating one endereco!");

		var entity = enderecoRepository.findById(endereco.getId())
				.orElseThrow(() -> new ResourceNotFoundException("No records found for this ID!"));

		entity.setId(endereco.getId());
        entity.setBairro(endereco.getBairro());
        entity.setComplemento(endereco.getBairro());
        entity.setCpf(endereco.getCpf());
        entity.setLocalidade(endereco.getLocalidade());
        entity.setLogradouro(endereco.getLogradouro());
        entity.setNumero(endereco.getNumero());
        entity.setUf(endereco.getUf());

		var vo = DozerMapper.parseObject(enderecoRepository.save(entity), EnderecoDTO.class);
		return vo;
	}

	public void delete(String id) {

		logger.info("Deleting one endereco!");

		var entity = enderecoRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("No records found for this ID!"));
		enderecoRepository.delete(entity);
	}
}
