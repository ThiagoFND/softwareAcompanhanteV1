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

import com.example.auth.domain.dto.informacao.pessoal.EnderecoDTO;
import com.example.auth.services.acompanhanteService.EnderecoService;

// endpoints permitidos somente pra acompanhante
@RestController
@RequestMapping("/api/acompanhante/endereco")
public class EnderecoController {
    
    @Autowired
    private EnderecoService enderecoService;
    
    // endpoint adicionar endereço
    @PostMapping(value = "/create",
			consumes = MediaType.APPLICATION_JSON_VALUE,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public EnderecoDTO create(@RequestBody EnderecoDTO endereco) {
		return enderecoService.create(endereco);
	}

    // endpoint pra listar endereco
	@GetMapping(value = "/list",
		produces = MediaType.APPLICATION_JSON_VALUE)
	public List<EnderecoDTO> findAll() {
		return enderecoService.findAll();
	}

    // endpoint pra atualizar endereco
	@PutMapping(value = "/update/{id}",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
	public EnderecoDTO update(@PathVariable String id, @RequestBody EnderecoDTO endereco) {
		// Verifique se o ID é válido (não nulo)
		if (id == null) {
			throw new IllegalArgumentException("O ID não pode ser nulo.");
		}
		// Configure o ID no objeto 'endereco'
		endereco.setId(id);

		// Chame o serviço 'enderecoService' para atualizar o exame
		EnderecoDTO updatedEndereco = enderecoService.update(endereco);
		return updatedEndereco;
	}

    // endpoint pra deletar endereco
    @DeleteMapping(value = "/delete/{id}")
	public ResponseEntity<?> delete(@PathVariable(value = "id") String id) {
		enderecoService.delete(id);
		return ResponseEntity.noContent().build();
	}
}
