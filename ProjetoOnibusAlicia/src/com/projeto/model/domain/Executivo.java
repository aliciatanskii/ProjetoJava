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

public class Executivo extends Frota{
    
    private int qntAndares;

    public Executivo() {
    }

    public int getQntAndares() {
        return qntAndares;
    }

    public void setQntAndares(int qntAndares) {
        this.qntAndares = qntAndares;
    }

    @Override
    public String toString() {
        return "Executivo{" + "qntAndares=" + qntAndares + '}';
    }
    
}
