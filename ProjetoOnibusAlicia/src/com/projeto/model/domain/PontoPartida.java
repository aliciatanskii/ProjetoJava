/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.projeto.model.domain;

import java.util.Objects;

/**
 *
 * @author Alícia Tanski
 */

public class PontoPartida {
    
    private int id;
    private String nome;
    private int horarioPartida;

    public PontoPartida() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getHorarioPartida() {
        return horarioPartida;
    }

    public void setHorarioPartida(int horarioPartida) {
        this.horarioPartida = horarioPartida;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 43 * hash + this.id;
        hash = 43 * hash + Objects.hashCode(this.nome);
        hash = 43 * hash + this.horarioPartida;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final PontoPartida other = (PontoPartida) obj;
        if (this.id != other.id) {
            return false;
        }
        if (this.horarioPartida != other.horarioPartida) {
            return false;
        }
        if (!Objects.equals(this.nome, other.nome)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "PontoPartida{" + "id=" + id + ", nome=" + nome + ", horarioPartida=" + horarioPartida + '}';
    }
    
    
}
