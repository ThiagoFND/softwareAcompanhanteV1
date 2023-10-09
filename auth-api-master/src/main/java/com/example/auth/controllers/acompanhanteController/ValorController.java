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

import com.example.auth.domain.dto.informacao.valores.ValorDTO;
import com.example.auth.services.acompanhanteService.ValorService;

// endpoints permitidos somente pra acompanhante
@RestController
@RequestMapping("/api/acompanhante/valor")
public class ValorController {
    
    @Autowired
    private ValorService valorService;
    
    // endpoint adicionar valor
    @PostMapping(value = "/create",
			consumes = MediaType.APPLICATION_JSON_VALUE,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ValorDTO create(@RequestBody ValorDTO valor) {
		return valorService.create(valor);
	}

    // endpoint pra listar valor
	@GetMapping(value = "/list",
		produces = MediaType.APPLICATION_JSON_VALUE)
	public List<ValorDTO> findAll() {
		return valorService.findAll();
	}

    // endpoint pra atualizar valor
	@PutMapping(value = "/update/{id}",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
	public ValorDTO update(@PathVariable String id, @RequestBody ValorDTO valor) {
		// Verifique se o ID é válido (não nulo)
		if (id == null) {
			throw new IllegalArgumentException("O ID não pode ser nulo.");
		}
		// Configure o ID no objeto 'valor'
		valor.setId(id);

		// Chame o serviço 'valorService' para atualizar o servico
		ValorDTO updatedServico = valorService.update(valor);
		return updatedServico;
	}

    // endpoint pra deletar valor
    @DeleteMapping(value = "/delete/{id}")
	public ResponseEntity<?> delete(@PathVariable(value = "id") String id) {
		valorService.delete(id);
		return ResponseEntity.noContent().build();
	}
}
