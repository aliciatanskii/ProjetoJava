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

public class Comum extends Frota{
    
    private boolean temArCondicionado;

    public Comum() {
    }

    public boolean isTemArCondicionado() {
        return temArCondicionado;
    }

    public void setTemArCondicionado(boolean temArCondicionado) {
        this.temArCondicionado = temArCondicionado;
    }

    @Override
    public String toString() {
        return "Comum{" + "temArCondicionado=" + temArCondicionado + '}';
    }
    
}
