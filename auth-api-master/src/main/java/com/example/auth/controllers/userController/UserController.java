package com.example.auth.controllers.userController;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.auth.services.acompanhanteService.*;
import com.example.auth.domain.dto.informacao.pessoal.DadosDTO;
import com.example.auth.domain.dto.informacao.pessoal.EnderecoDTO;
import com.example.auth.domain.dto.informacao.pessoal.InformacoesDTO;
import com.example.auth.domain.dto.informacao.pessoal.VidFotosDTO;
import com.example.auth.domain.dto.informacao.servicos.ServicoDTO;
import com.example.auth.domain.dto.informacao.valores.ValorDTO;

@RestController
@RequestMapping("api/usuario")
public class UserController {
    
    @Autowired
    private DadosService dadosService;
    
    @Autowired
    private EnderecoService enderecoService;

    @Autowired
    private InformacoesService infomarcoesService;

    @Autowired
    private ServicoService servicoService;

    @Autowired
    private ValorService valorService;

    @Autowired
    private VidFotosService vidfotosService;


    // Endpoint para obter todos os dados
	@GetMapping(value = "/list/dados",
		produces = MediaType.APPLICATION_JSON_VALUE)
        public List<DadosDTO> findAllDadosDTOs() {
		return dadosService.findAll();
	}

    // Endpoint para obter todos os enderecos
	@GetMapping(value = "/list/endereco",
		produces = MediaType.APPLICATION_JSON_VALUE)
	public List<EnderecoDTO> findAllEnderecoDTOs() {
		return enderecoService.findAll();
	}

    // Endpoint para obter todas as informacoes
	@GetMapping(value = "/list/informacoes",
		produces = MediaType.APPLICATION_JSON_VALUE)
	public List<InformacoesDTO> findAllInformacoesDTOs() {
		return infomarcoesService.findAll();
	}

    // Endpoint para obter todos os serviços
	@GetMapping(value = "/list/servico",
		produces = MediaType.APPLICATION_JSON_VALUE)
	public List<ServicoDTO> findAllServicoDTOs() {
		return servicoService.findAll();
	}

    // Endpoint para obter todos os valores
	@GetMapping(value = "/list/valor",
		produces = MediaType.APPLICATION_JSON_VALUE)
	public List<ValorDTO> findAllValorDTOs() {
		return valorService.findAll();
	}

    // Endpoint para obter todos os videos e fotos
	@GetMapping(value = "/list/vidfotos",
		produces = MediaType.APPLICATION_JSON_VALUE)
	public List<VidFotosDTO> findAllVidFotosDTOs() {
		return vidfotosService.findAll();
	}
}
