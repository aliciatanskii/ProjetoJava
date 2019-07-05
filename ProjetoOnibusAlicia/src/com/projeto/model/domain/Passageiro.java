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

public class Passageiro extends Pessoa{
    
    private boolean possuiSeguro;

    public Passageiro() {
    }

    public boolean isPossuiSeguro() {
        return possuiSeguro;
    }

    public void setPossuiSeguro(boolean possuiSeguro) {
        this.possuiSeguro = possuiSeguro;
    }

    @Override
    public String toString() {
        return "Passageiro{" + "possuiSeguro=" + possuiSeguro + '}';
    }
          
}
