package com.example.auth.services.acompanhanteService;

import java.util.List;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.auth.domain.dto.UserDTO;
import com.example.auth.domain.dto.informacao.pessoal.DadosDTO;
import com.example.auth.mapper.DozerMapper;
import com.example.auth.repositories.DadosRepository;
import com.example.auth.repositories.UserRepository;
import com.example.auth.entities.User;
import com.example.auth.entities.informacao.pessoal.Dados;
import com.example.auth.exceptions.ResourceNotFoundException;

@Service
public class DadosService {
    
    private Logger logger = Logger.getLogger(DadosService.class.getName());

    @Autowired
	private DadosRepository dadosRepository;

	@Autowired
	private UserRepository userRepository;

	public List<DadosDTO> findAll() {

		logger.info("Finding all dados!");

		return DozerMapper.parseListObjects(dadosRepository.findAll(), DadosDTO.class);
	}

	public DadosDTO findById(String id) {

		logger.info("Finding one dados!");

		var entity = dadosRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("No records found for this ID!"));
		return DozerMapper.parseObject(entity, DadosDTO.class);
	}

	public DadosDTO create(DadosDTO dados) {
        logger.info("Creating one dados!");

        // Verifique se o UserDTO tem um ID válido e corresponde a um usuário existente
        UserDTO userDTO = dados.getUsuario();
        if (userDTO == null || userDTO.getId() == null) {
            throw new ResourceNotFoundException("UserDTO ID is required.");
        }
        
        // Consulte o usuário correspondente no banco de dados usando o ID do UserDTO
        User user = userRepository.findById(userDTO.getId())
                .orElseThrow(() -> new ResourceNotFoundException("User not found for the given ID."));
        
        // Crie o objeto Endereco a partir do EnderecoDTO
        Dados entity = DozerMapper.parseObject(dados, Dados.class);
        entity.setUsuario(user); // Associe o usuário ao endereço
        
        // Salve o Endereco no banco de dados
        var vo = DozerMapper.parseObject(dadosRepository.save(entity), DadosDTO.class);
        return vo;
    }

	public DadosDTO update(DadosDTO dados) {

		logger.info("Updating one dados!");

		var entity = dadosRepository.findById(dados.getId())
				.orElseThrow(() -> new ResourceNotFoundException("No records found for this ID!"));

		entity.setId(dados.getId());
		entity.setDescricao(dados.getDescricao());
		entity.setPeso(dados.getPeso());

		var vo = DozerMapper.parseObject(dadosRepository.save(entity), DadosDTO.class);
		return vo;
	}

	public void delete(String id) {

		logger.info("Deleting one person!");

		var entity = dadosRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("No records found for this ID!"));
		dadosRepository.delete(entity);
	}
}
