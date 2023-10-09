package com.example.auth.domain.dto.informacao.pessoal;

import com.example.auth.domain.dto.UserDTO;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Table(name = "endereco")
public class EnderecoDTO {
    
    private String id;
    private String cpf;
    private String logradouro;
    private String complemento;
    private String uf;
    private String numero;
    private String bairro;
    private String localidade;
    
    @ManyToOne
    @JoinColumn(nullable = false)
    private UserDTO usuario;

    public EnderecoDTO() {
    }

    public EnderecoDTO(String cpf, String logradouro, String complemento, String uf, String numero,
            String bairro, String localidade, UserDTO usuario) {
        this.cpf = cpf;
        this.logradouro = logradouro;
        this.complemento = complemento;
        this.uf = uf;
        this.numero = numero;
        this.bairro = bairro;
        this.localidade = localidade;
        this.usuario = usuario;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getLogradouro() {
        return logradouro;
    }

    public void setLogradouro(String logradouro) {
        this.logradouro = logradouro;
    }

    public String getComplemento() {
        return complemento;
    }

    public void setComplemento(String complemento) {
        this.complemento = complemento;
    }

    public String getUf() {
        return uf;
    }

    public void setUf(String uf) {
        this.uf = uf;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getLocalidade() {
        return localidade;
    }

    public void setLocalidade(String localidade) {
        this.localidade = localidade;
    }

    public UserDTO getUsuario() {
        return usuario;
    }

    public void setUsuario(UserDTO usuario) {
        this.usuario = usuario;
    }
}
