package com.example.auth.services.acompanhanteService;

import java.util.List;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.auth.domain.dto.UserDTO;
import com.example.auth.domain.dto.informacao.pessoal.VidFotosDTO;
import com.example.auth.entities.User;
import com.example.auth.entities.informacao.pessoal.VidFotos;
import com.example.auth.exceptions.ResourceNotFoundException;
import com.example.auth.mapper.DozerMapper;
import com.example.auth.repositories.UserRepository;
import com.example.auth.repositories.VidFotosRepository;

@Service
public class VidFotosService {
    
    private Logger logger = Logger.getLogger(VidFotosService.class.getName());

    @Autowired
	private VidFotosRepository vidFotosRepository;

    @Autowired
    private UserRepository userRepository;

	public List<VidFotosDTO> findAll() {

		logger.info("Finding all video or photo!");

		return DozerMapper.parseListObjects(vidFotosRepository.findAll(), VidFotosDTO.class);
	}

	public VidFotosDTO findById(String id) {

		logger.info("Finding one video or photo!");

		var entity = vidFotosRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("No records found for this ID!"));
		return DozerMapper.parseObject(entity, VidFotosDTO.class);
	}

	public VidFotosDTO create(VidFotosDTO vidfotos) {

        logger.info("Creating one servicos!");

        // Verifique se o UserDTO tem um ID válido e corresponde a um usuário existente
        UserDTO userDTO = vidfotos.getUsuario();
        if (userDTO == null || userDTO.getId() == null) {
            throw new ResourceNotFoundException("UserDTO ID is required.");
        }
        
        // Consulte o usuário correspondente no banco de dados usando o ID do UserDTO
        User user = userRepository.findById(userDTO.getId())
                .orElseThrow(() -> new ResourceNotFoundException("User not found for the given ID."));
        
        // Crie o objeto Endereco a partir do EnderecoDTO
        VidFotos entity = DozerMapper.parseObject(vidfotos, VidFotos.class);
        entity.setUsuario(user); // Associe o usuário ao endereço
        
        // Salve o Endereco no banco de dados
        var vo = DozerMapper.parseObject(vidFotosRepository.save(entity), VidFotosDTO.class);
        return vo;
    }

	public VidFotosDTO update(VidFotosDTO vidfotos) {

		logger.info("Updating one video or photo!");

		var entity = vidFotosRepository.findById(vidfotos.getId())
				.orElseThrow(() -> new ResourceNotFoundException("No records found for this ID!"));

		entity.setId(vidfotos.getId());
        entity.setFotos(vidfotos.getFotos());
        entity.setVideo(vidfotos.getVideo());

		var vo = DozerMapper.parseObject(vidFotosRepository.save(entity), VidFotosDTO.class);
		return vo;
	}

	public void delete(String id) {

		logger.info("Deleting one video or photo!");

		var entity = vidFotosRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("No records found for this ID!"));
		vidFotosRepository.delete(entity);
	}
}
