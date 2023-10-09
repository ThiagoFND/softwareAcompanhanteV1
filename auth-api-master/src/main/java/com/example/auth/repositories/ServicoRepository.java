package com.example.auth.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.auth.entities.informacao.servicos.Servico;

public interface ServicoRepository extends JpaRepository<Servico, String> {
    
}
