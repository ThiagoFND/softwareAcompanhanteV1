package com.example.auth.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.auth.entities.informacao.pessoal.Dados;

public interface DadosRepository extends JpaRepository<Dados, String>{
    
}
