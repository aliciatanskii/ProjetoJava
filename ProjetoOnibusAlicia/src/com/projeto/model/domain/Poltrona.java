/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.projeto.model.domain;

/**
 *
 * @author Alícia Tanski
 */

public class Poltrona {

    private int numeroPoltrona;

    public Poltrona() {
    }

    public int getNumeroPoltrona() {
        return numeroPoltrona;
    }

    public void setNumeroPoltrona(int numeroPoltrona) {
        this.numeroPoltrona = numeroPoltrona;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 59 * hash + this.numeroPoltrona;
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
        final Poltrona other = (Poltrona) obj;
        if (this.numeroPoltrona != other.numeroPoltrona) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Poltrona{" + "numeroPoltrona=" + numeroPoltrona + '}';
    }
    
}
