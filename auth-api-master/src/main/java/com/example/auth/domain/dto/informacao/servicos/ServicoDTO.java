package com.example.auth.domain.dto.informacao.servicos;

import com.example.auth.domain.dto.UserDTO;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "servicosFeitos")
public class ServicoDTO {
    
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    private String servico;
    private String descricao;

    @ManyToOne
    @JoinColumn(nullable = false)
    private UserDTO usuario;


    public ServicoDTO() {
    }


    public ServicoDTO(String id, String servico, String descricao, UserDTO usuario) {
        this.id = id;
        this.servico = servico;
        this.descricao = descricao;
        this.usuario = usuario;
    }


    public String getId() {
        return id;
    }


    public void setId(String id) {
        this.id = id;
    }


    public String getServico() {
        return servico;
    }


    public void setServico(String servico) {
        this.servico = servico;
    }


    public String getDescricao() {
        return descricao;
    }


    public void setDescricao(String descricao) {
        this.descricao = descricao;
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
        result = prime * result + ((servico == null) ? 0 : servico.hashCode());
        result = prime * result + ((descricao == null) ? 0 : descricao.hashCode());
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
        ServicoDTO other = (ServicoDTO) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        if (servico == null) {
            if (other.servico != null)
                return false;
        } else if (!servico.equals(other.servico))
            return false;
        if (descricao == null) {
            if (other.descricao != null)
                return false;
        } else if (!descricao.equals(other.descricao))
            return false;
        if (usuario == null) {
            if (other.usuario != null)
                return false;
        } else if (!usuario.equals(other.usuario))
            return false;
        return true;
    }

}
