package com.example.auth.controllers.acompanhanteController;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.auth.domain.dto.informacao.pessoal.VidFotosDTO;
import com.example.auth.services.acompanhanteService.VidFotosService;

@RestController
@RequestMapping("/api/acompanhante/vidfotos")
public class VidFotosController {
    
    @Autowired
    private VidFotosService vidFotosService;
    
    // endpoint adicionar video ou foto
    @PostMapping(value = "/create",
			consumes = MediaType.APPLICATION_JSON_VALUE,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public VidFotosDTO create(@RequestBody VidFotosDTO vidfoto) {
		return vidFotosService.create(vidfoto);
	}

    // endpoint pra listar valor
	@GetMapping(value = "/list",
		produces = MediaType.APPLICATION_JSON_VALUE)
	public List<VidFotosDTO> findAll() {
		return vidFotosService.findAll();
	}

    // endpoint pra atualizar valor
	@PutMapping(value = "/update/{id}",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
	public VidFotosDTO update(@PathVariable String id, @RequestBody VidFotosDTO vidfoto) {
		// Verifique se o ID é válido (não nulo)
		if (id == null) {
			throw new IllegalArgumentException("O ID não pode ser nulo.");
		}
		// Configure o ID no objeto 'valor'
		vidfoto.setId(id);

		// Chame o serviço 'valorService' para atualizar o servico
		VidFotosDTO updatedVidFoto = vidFotosService.update(vidfoto);
		return updatedVidFoto;
	}

    // endpoint pra deletar valor
    @DeleteMapping(value = "/delete/{id}")
	public ResponseEntity<?> delete(@PathVariable(value = "id") String id) {
		vidFotosService.delete(id);
		return ResponseEntity.noContent().build();
	}
}
