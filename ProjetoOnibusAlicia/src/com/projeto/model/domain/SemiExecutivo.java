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

public class SemiExecutivo extends Frota{
    
    private int qntTV;

    public SemiExecutivo() {
    }

    public int getQntTV() {
        return qntTV;
    }

    public void setQntTV(int qntTV) {
        this.qntTV = qntTV;
    }

    @Override
    public String toString() {
        return "SemiExecutivo{" + "qntTV=" + qntTV + '}';
    }
    
}
