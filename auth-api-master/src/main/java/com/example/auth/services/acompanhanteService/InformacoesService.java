package com.example.auth.services.acompanhanteService;

import java.util.List;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.auth.domain.dto.UserDTO;
import com.example.auth.domain.dto.informacao.pessoal.InformacoesDTO;
import com.example.auth.entities.User;
import com.example.auth.entities.informacao.pessoal.Informacoes;
import com.example.auth.exceptions.ResourceNotFoundException;
import com.example.auth.mapper.DozerMapper;
import com.example.auth.repositories.InformacoesRepository;
import com.example.auth.repositories.UserRepository;

@Service
public class InformacoesService {
    

    private Logger logger = Logger.getLogger(InformacoesService.class.getName());

    @Autowired
	private InformacoesRepository informacoesRepository;

    @Autowired
    private UserRepository userRepository;

	public List<InformacoesDTO> findAll() {

		logger.info("Finding all informacoes!");

		return DozerMapper.parseListObjects(informacoesRepository.findAll(), InformacoesDTO.class);
	}

	public InformacoesDTO findById(String id) {

		logger.info("Finding one informacoes!");

		var entity = informacoesRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("No records found for this ID!"));
		return DozerMapper.parseObject(entity, InformacoesDTO.class);
	}

	public InformacoesDTO create(InformacoesDTO informacoes) {

        logger.info("Creating one informacoes!");

        // Verifique se o UserDTO tem um ID válido e corresponde a um usuário existente
        UserDTO userDTO = informacoes.getUsuario();
        if (userDTO == null || userDTO.getId() == null) {
            throw new ResourceNotFoundException("UserDTO ID is required.");
        }
        
        // Consulte o usuário correspondente no banco de dados usando o ID do UserDTO
        User user = userRepository.findById(userDTO.getId())
                .orElseThrow(() -> new ResourceNotFoundException("User not found for the given ID."));
        
        // Crie o objeto Endereco a partir do EnderecoDTO
        Informacoes entity = DozerMapper.parseObject(informacoes, Informacoes.class);
        entity.setUsuario(user); // Associe o usuário ao endereço
        
        // Salve o Endereco no banco de dados
        var vo = DozerMapper.parseObject(informacoesRepository.save(entity), InformacoesDTO.class);
        return vo;
    }

	public InformacoesDTO update(InformacoesDTO informacoes) {

		logger.info("Updating one informacoes!");

		var entity = informacoesRepository.findById(informacoes.getId())
				.orElseThrow(() -> new ResourceNotFoundException("No records found for this ID!"));

		entity.setId(informacoes.getId());
        entity.setDescricao(informacoes.getDescricao());
        entity.setInformacao(informacoes.getInformacao());
        entity.setTipo(informacoes.getTipo());


		var vo = DozerMapper.parseObject(informacoesRepository.save(entity), InformacoesDTO.class);
		return vo;
	}

	public void delete(String id) {

		logger.info("Deleting one person!");

		var entity = informacoesRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("No records found for this ID!"));
		informacoesRepository.delete(entity);
	}
}
