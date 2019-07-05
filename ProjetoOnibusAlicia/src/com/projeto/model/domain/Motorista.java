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

public class Motorista extends Pessoa {
    
    private int cnh;

    public Motorista() {
    }

    public int getCnh() {
        return cnh;
    }

    public void setCnh(int cnh) {
        this.cnh = cnh;
    }

    @Override
    public String toString() {
        return "Motorista{" + "cnh=" + cnh + '}';
    }
         
}
