package com.example.auth.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.auth.entities.informacao.pessoal.VidFotos;

public interface VidFotosRepository extends JpaRepository<VidFotos, String> {
    
}
