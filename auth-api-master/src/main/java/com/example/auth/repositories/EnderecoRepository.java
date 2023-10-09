package com.example.auth.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.auth.entities.informacao.pessoal.Endereco;

public interface EnderecoRepository extends JpaRepository<Endereco, String>{
    
}
