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

import com.example.auth.domain.dto.informacao.servicos.ServicoDTO;
import com.example.auth.services.acompanhanteService.ServicoService;

// endpoints permitidos somente pra acompanhante
@RestController
@RequestMapping("/api/acompanhante/servico")
public class ServicoController {
    
    @Autowired
    private ServicoService servicoService;
    
    // endpoint adicionar servico
    @PostMapping(value = "/create",
			consumes = MediaType.APPLICATION_JSON_VALUE,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ServicoDTO create(@RequestBody ServicoDTO servico) {
		return servicoService.create(servico);
	}

    // endpoint pra listar servico
	@GetMapping(value = "/list",
		produces = MediaType.APPLICATION_JSON_VALUE)
	public List<ServicoDTO> findAll() {
		return servicoService.findAll();
	}

    // endpoint pra atualizar servico
	@PutMapping(value = "/update/{id}",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
	public ServicoDTO update(@PathVariable String id, @RequestBody ServicoDTO servico) {
		// Verifique se o ID é válido (não nulo)
		if (id == null) {
			throw new IllegalArgumentException("O ID não pode ser nulo.");
		}
		// Configure o ID no objeto 'servicos'
		servico.setId(id);

		// Chame o serviço 'servicoService' para atualizar o servico
		ServicoDTO updatedServico = servicoService.update(servico);
		return updatedServico;
	}

    // endpoint pra deletar servico
    @DeleteMapping(value = "/delete/{id}")
	public ResponseEntity<?> delete(@PathVariable(value = "id") String id) {
		servicoService.delete(id);
		return ResponseEntity.noContent().build();
	}
}
