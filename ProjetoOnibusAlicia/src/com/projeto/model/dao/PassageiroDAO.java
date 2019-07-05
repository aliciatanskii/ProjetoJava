/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.projeto.model.dao;

import com.projeto.model.domain.Passageiro;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author Alícia Tanski
 */

public class PassageiroDAO {
    
    private Connection connection;

    public Connection getConnection() {
        return connection;
    }

    public void setConnection(Connection connection) {
        this.connection = connection;
    }
    
    public List<Passageiro> listar() {
        String sql = "SELECT * FROM passageiro";
        List<Passageiro> retorno = new ArrayList<>();
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            ResultSet resultado = stmt.executeQuery();
            while (resultado.next()) {
                Passageiro passageiro = new Passageiro();
                passageiro.setCpf(resultado.getString("cpf_passageiro"));
                passageiro.setNome(resultado.getString("nome"));
                passageiro.setPossuiSeguro(resultado.getBoolean("possuiSeguro"));
                retorno.add(passageiro);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return retorno;
    }
    
    public boolean verificaCPF(String cpf) {
        String sql = "SELECT * FROM passageiro WHERE cpf_passageiro=?";
        boolean exists = false;
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1, cpf);
            ResultSet resultado = stmt.executeQuery();
            if (resultado.next()) {
                exists = true;
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return exists;
    }
    
    public boolean inserir(Passageiro passageiro) {
        String sql = "INSERT INTO passageiro(cpf_passageiro, nome, possuiSeguro) VALUES(?,?,?)";
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1, passageiro.getCpf());
            stmt.setString(2, passageiro.getNome());
            stmt.setBoolean(3, passageiro.isPossuiSeguro());
            stmt.execute();
            return true;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    public boolean alterar(Passageiro passageiro) {
        String sql = "UPDATE passageiro SET nome=?, possuiSeguro=? WHERE cpf_passageiro=?";
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1, passageiro.getNome());
            stmt.setBoolean(2, passageiro.isPossuiSeguro());
            stmt.setString(3, passageiro.getCpf());
            stmt.execute();
            return true;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    public boolean remover(Passageiro passageiro) {
        String sql = "DELETE FROM passageiro WHERE cpf_passageiro=?";
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1, passageiro.getCpf());
            stmt.execute();
            return true;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }
}
