package com.example.auth.domain.dto.informacao.valores;

import com.example.auth.domain.dto.UserDTO;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "valores")
public class ValorDTO {
    
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    private String numero;
    private String tempo;
    private double valor;
    private String realiza;

    @ManyToOne
    @JoinColumn(nullable = false)
    private UserDTO usuario;

    public ValorDTO() {
    }

    public ValorDTO(String id, String numero, String tempo, double valor, String realiza, UserDTO usuario) {
        this.id = id;
        this.numero = numero;
        this.tempo = tempo;
        this.valor = valor;
        this.realiza = realiza;
        this.usuario = usuario;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getTempo() {
        return tempo;
    }

    public void setTempo(String tempo) {
        this.tempo = tempo;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public String getRealiza() {
        return realiza;
    }

    public void setRealiza(String realiza) {
        this.realiza = realiza;
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
        result = prime * result + ((numero == null) ? 0 : numero.hashCode());
        result = prime * result + ((tempo == null) ? 0 : tempo.hashCode());
        long temp;
        temp = Double.doubleToLongBits(valor);
        result = prime * result + (int) (temp ^ (temp >>> 32));
        result = prime * result + ((realiza == null) ? 0 : realiza.hashCode());
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
        ValorDTO other = (ValorDTO) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        if (numero == null) {
            if (other.numero != null)
                return false;
        } else if (!numero.equals(other.numero))
            return false;
        if (tempo == null) {
            if (other.tempo != null)
                return false;
        } else if (!tempo.equals(other.tempo))
            return false;
        if (Double.doubleToLongBits(valor) != Double.doubleToLongBits(other.valor))
            return false;
        if (realiza == null) {
            if (other.realiza != null)
                return false;
        } else if (!realiza.equals(other.realiza))
            return false;
        if (usuario == null) {
            if (other.usuario != null)
                return false;
        } else if (!usuario.equals(other.usuario))
            return false;
        return true;
    }

    
}
