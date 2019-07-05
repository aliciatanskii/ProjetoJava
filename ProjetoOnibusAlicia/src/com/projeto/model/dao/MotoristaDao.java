/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.projeto.model.dao;

import com.projeto.model.domain.Motorista;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author Alícia Tanski
 */

public class MotoristaDao {
    
    private Connection connection;

    public Connection getConnection() {
        return connection;
    }

    public void setConnection(Connection connection) {
        this.connection = connection;
    }
    
    public List<Motorista> listar() {
        String sql = "SELECT * FROM motorista";
        List<Motorista> retorno = new ArrayList<>();
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            ResultSet resultado = stmt.executeQuery();
            while (resultado.next()) {
                Motorista motorista = new Motorista();
                motorista.setCpf(resultado.getString("cpf_motorista"));
                motorista.setNome(resultado.getString("nome"));
                motorista.setCnh(resultado.getInt("cnh"));
                retorno.add(motorista);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return retorno;
    }
    
    public boolean inserir(Motorista motorista) {
        String sql = "INSERT INTO motorista(cpf_motorista, nome, cnh) VALUES(?,?,?)";
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1, motorista.getCpf());
            stmt.setString(2, motorista.getNome());
            stmt.setInt(3, motorista.getCnh());
            stmt.execute();
            return true;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    public boolean alterar(Motorista motorista) {
        String sql = "UPDATE motorista SET nome=?, cnh=? WHERE cpf_motorista=?";
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1, motorista.getNome());
            stmt.setInt(2, motorista.getCnh());
            stmt.setString(3, motorista.getCpf());
            stmt.execute();
            return true;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    public boolean remover(Motorista motorista) {
        String sql = "DELETE FROM motorista WHERE cpf_motorista=?";
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1, motorista.getCpf());
            stmt.execute();
            return true;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }
}
