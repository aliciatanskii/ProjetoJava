/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.projeto.model.dao;

import com.projeto.model.domain.Fiscal;
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

public class FiscalDAO {
    private Connection connection;

    public Connection getConnection() {
        return connection;
    }

    public void setConnection(Connection connection) {
        this.connection = connection;
    }
    
    public List<Fiscal> listar() {
        String sql = "SELECT * FROM fiscal";
        List<Fiscal> retorno = new ArrayList<>();
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            ResultSet resultado = stmt.executeQuery();
            while (resultado.next()) {
                Fiscal fiscal = new Fiscal();
                fiscal.setCpf(resultado.getString("cpf"));
                fiscal.setNome(resultado.getString("nome"));
                fiscal.setSalario(resultado.getDouble("salario"));
                retorno.add(fiscal);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return retorno;
    }
    
    public boolean verificaCPF(String cpf) {
        String sql = "SELECT * FROM fiscal WHERE cpf=?";
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
    
    public boolean inserir(Fiscal fiscal) {
        String sql = "INSERT INTO fiscal(cpf, nome, salario) VALUES(?,?,?)";
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1, fiscal.getCpf());
            stmt.setString(2, fiscal.getNome());
            stmt.setDouble(3, fiscal.getSalario());
            stmt.execute();
            return true;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    public boolean alterar(Fiscal fiscal) {
        String sql = "UPDATE fiscal SET nome=?, salario=? WHERE cpf=?";
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1, fiscal.getNome());
            stmt.setDouble(2, fiscal.getSalario());
            stmt.setString(3, fiscal.getCpf());
            stmt.execute();
            return true;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    public boolean remover(Fiscal fiscal) {
        String sql = "DELETE FROM fiscal WHERE cpf=?";
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1, fiscal.getCpf());
            stmt.execute();
            return true;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }
}
