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

import com.example.auth.domain.dto.informacao.pessoal.InformacoesDTO;
import com.example.auth.services.acompanhanteService.InformacoesService;

// endpoints permitidos somente pra acompanhante
@RestController
@RequestMapping("/api/acompanhante/informacoes")
public class InformacoesController {
    
    @Autowired
    private InformacoesService informacoesService;
    
    // endpoint adicionar informacoes
    @PostMapping(value = "/create",
			consumes = MediaType.APPLICATION_JSON_VALUE,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public InformacoesDTO create(@RequestBody InformacoesDTO informacoes) {
		return informacoesService.create(informacoes);
	}

    // endpoint pra listar informacoes
	@GetMapping(value = "/list",
		produces = MediaType.APPLICATION_JSON_VALUE)
	public List<InformacoesDTO> findAll() {
		return informacoesService.findAll();
	}

    // endpoint pra atualizar informacoes
	@PutMapping(value = "/update/{id}",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
	public InformacoesDTO update(@PathVariable String id, @RequestBody InformacoesDTO informacoes) {
		// Verifique se o ID é válido (não nulo)
		if (id == null) {
			throw new IllegalArgumentException("O ID não pode ser nulo.");
		}
		// Configure o ID no objeto 'dados'
		informacoes.setId(id);

		// Chame o serviço 'dadosService' para atualizar o exame
		InformacoesDTO updatedInformacoes = informacoesService.update(informacoes);
		return updatedInformacoes;
	}

    // endpoint pra deletar informacoes
    @DeleteMapping(value = "/delete/{id}")
	public ResponseEntity<?> delete(@PathVariable(value = "id") String id) {
		informacoesService.delete(id);
		return ResponseEntity.noContent().build();
	}
}
