package com.example.auth.services.acompanhanteService;

import java.util.List;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.auth.domain.dto.UserDTO;
import com.example.auth.domain.dto.informacao.servicos.ServicoDTO;
import com.example.auth.entities.User;
import com.example.auth.entities.informacao.servicos.Servico;
import com.example.auth.exceptions.ResourceNotFoundException;
import com.example.auth.mapper.DozerMapper;
import com.example.auth.repositories.ServicoRepository;
import com.example.auth.repositories.UserRepository;

@Service
public class ServicoService {
    
    private Logger logger = Logger.getLogger(ServicoService.class.getName());

    @Autowired
	private ServicoRepository servicoRepository;

    @Autowired
    private UserRepository userRepository;

	public List<ServicoDTO> findAll() {

		logger.info("Finding all servicos!");

		return DozerMapper.parseListObjects(servicoRepository.findAll(), ServicoDTO.class);
	}

	public ServicoDTO findById(String id) {

		logger.info("Finding one servicos!");

		var entity = servicoRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("No records found for this ID!"));
		return DozerMapper.parseObject(entity, ServicoDTO.class);
	}

	public ServicoDTO create(ServicoDTO servico) {

        logger.info("Creating one servicos!");

        // Verifique se o UserDTO tem um ID válido e corresponde a um usuário existente
        UserDTO userDTO = servico.getUsuario();
        if (userDTO == null || userDTO.getId() == null) {
            throw new ResourceNotFoundException("UserDTO ID is required.");
        }
        
        // Consulte o usuário correspondente no banco de dados usando o ID do UserDTO
        User user = userRepository.findById(userDTO.getId())
                .orElseThrow(() -> new ResourceNotFoundException("User not found for the given ID."));
        
        // Crie o objeto Endereco a partir do EnderecoDTO
        Servico entity = DozerMapper.parseObject(servico, Servico.class);
        entity.setUsuario(user); // Associe o usuário ao endereço
        
        // Salve o Endereco no banco de dados
        var vo = DozerMapper.parseObject(servicoRepository.save(entity), ServicoDTO.class);
        return vo;
    }

	public ServicoDTO update(ServicoDTO servico) {

		logger.info("Updating one servicos!");

		var entity = servicoRepository.findById(servico.getId())
				.orElseThrow(() -> new ResourceNotFoundException("No records found for this ID!"));

		entity.setId(servico.getId());
        entity.setDescricao(servico.getDescricao());
        entity.setServico(servico.getServico());

		var vo = DozerMapper.parseObject(servicoRepository.save(entity), ServicoDTO.class);
		return vo;
	}

	public void delete(String id) {

		logger.info("Deleting one servicos!");

		var entity = servicoRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("No records found for this ID!"));
		servicoRepository.delete(entity);
	}
}
