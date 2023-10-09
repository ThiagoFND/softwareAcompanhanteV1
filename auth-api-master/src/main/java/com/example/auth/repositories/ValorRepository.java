package com.example.auth.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.auth.entities.informacao.valores.Valor;

public interface ValorRepository extends JpaRepository<Valor, String> {
    
}
