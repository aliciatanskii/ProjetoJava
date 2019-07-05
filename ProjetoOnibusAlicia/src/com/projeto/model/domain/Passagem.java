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

public class Passagem {
    
    private String passageiro;
    private String frota;
    private int codBarras;
    private int poltrona;
    private int pontoPartida;
    private int destino;

    public Passagem() {
        
    }

    public String getPassageiro() {
        return passageiro;
    }

    public void setPassageiro(String passageiro) {
        this.passageiro = passageiro;
    }

    public String getFrota() {
        return frota;
    }

    public void setFrota(String frota) {
        this.frota = frota;
    }

    public int getCodBarras() {
        return codBarras;
    }

    public void setCodBarras(int codBarras) {
        this.codBarras = codBarras;
    }

    public int getPoltrona() {
        return poltrona;
    }

    public void setPoltrona(int poltrona) {
        this.poltrona = poltrona;
    }

    public int getPontoPartida() {
        return pontoPartida;
    }

    public void setPontoPartida(int pontoPartida) {
        this.pontoPartida = pontoPartida;
    }

    public int getDestino() {
        return destino;
    }

    public void setDestino(int destino) {
        this.destino = destino;
    }

    @Override
    public String toString() {
        return "Passagem{" + "passageiro=" + passageiro + ", frota=" + frota + ", codBarras=" + codBarras + ", poltrona=" + poltrona + ", pontoPartida=" + pontoPartida + ", destino=" + destino + '}';
    }
}