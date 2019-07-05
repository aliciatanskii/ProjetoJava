/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.projeto.model.dao;

import com.projeto.model.domain.Empresa;
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
public class EmpresaDAO {
    
    private Connection connection;

    public Connection getConnection() {
        return connection;
    }

    public void setConnection(Connection connection) {
        this.connection = connection;
    }
    
    public List<Empresa> listar() {
        String sql = "SELECT * FROM empresa";
        List<Empresa> retorno = new ArrayList<>();
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            ResultSet resultado = stmt.executeQuery();
            while (resultado.next()) {
                Empresa empresa = new Empresa();
                empresa.setCnpj(resultado.getString("cnpj_empresa"));
                empresa.setNome(resultado.getString("nome"));
                retorno.add(empresa);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return retorno;
    }
    public boolean inserir(Empresa empresa) {
        String sql = "INSERT INTO empresa (cnpj_empresa, nome) VALUES(?,?)";
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1, empresa.getCnpj());
            stmt.setString(2, empresa.getNome());
            stmt.execute();
            return true;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    public boolean alterar(Empresa empresa) {
        String sql = "UPDATE empresa SET nome=? WHERE cnpj_empresa=?";
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1, empresa.getNome());
            stmt.setString(2, empresa.getCnpj());
            stmt.execute();
            return true;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    public boolean remover(Empresa empresa) {
        String sql = "DELETE FROM empresa WHERE cnpj_empresa=?";
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1, empresa.getCnpj());
            stmt.execute();
            return true;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }
}
