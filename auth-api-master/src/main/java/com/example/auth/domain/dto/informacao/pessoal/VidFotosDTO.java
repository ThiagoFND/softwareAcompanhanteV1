package com.example.auth.domain.dto.informacao.pessoal;

import java.util.Arrays;

import com.example.auth.domain.dto.UserDTO;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "VideoFoto")
public class VidFotosDTO {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    private byte[] video;
    private byte[] fotos;
    
    @ManyToOne
    @JoinColumn
    private UserDTO usuario;

    public VidFotosDTO() {
    }

    public VidFotosDTO(String id, byte[] video, byte[] fotos, UserDTO usuario) {
        this.id = id;
        this.video = video;
        this.fotos = fotos;
        this.usuario = usuario;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public byte[] getVideo() {
        return video;
    }

    public void setVideo(byte[] video) {
        this.video = video;
    }

    public byte[] getFotos() {
        return fotos;
    }

    public void setFotos(byte[] fotos) {
        this.fotos = fotos;
    }

    public UserDTO getUsuario() {
        return usuario;
    }

    public void setUsuario(UserDTO usuario) {
        this.usuario = usuario;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        result = prime * result + Arrays.hashCode(video);
        result = prime * result + Arrays.hashCode(fotos);
        result = prime * result + ((usuario == null) ? 0 : usuario.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        VidFotosDTO other = (VidFotosDTO) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        if (!Arrays.equals(video, other.video))
            return false;
        if (!Arrays.equals(fotos, other.fotos))
            return false;
        if (usuario == null) {
            if (other.usuario != null)
                return false;
        } else if (!usuario.equals(other.usuario))
            return false;
        return true;
    }

    
    
}
