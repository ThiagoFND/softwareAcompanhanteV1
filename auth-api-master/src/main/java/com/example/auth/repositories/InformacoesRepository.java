package com.example.auth.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.auth.entities.informacao.pessoal.Informacoes;

public interface InformacoesRepository extends JpaRepository<Informacoes, String>{
    
}
