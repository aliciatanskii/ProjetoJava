/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.projeto.model.dao;

import com.projeto.model.domain.Destino;
import com.projeto.model.domain.Frota;
import com.projeto.model.domain.Passageiro;
import com.projeto.model.domain.Passagem;
import com.projeto.model.domain.Poltrona;
import com.projeto.model.domain.PontoPartida;
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

public class PassagemDAO {
    
    private Connection connection;

    public Connection getConnection() {
        return connection;
    }

    public void setConnection(Connection connection) {
        this.connection = connection;
    }
    public List<Passagem> listar(){
        String sql = "SELECT * FROM  passagem";
        List<Passagem> retorno = new ArrayList<>();        
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            ResultSet resultado = stmt.executeQuery();
            while (resultado.next()) {
                Passagem p = new Passagem();
                p.setCodBarras(resultado.getInt("codBarras"));
                p.setPassageiro(resultado.getString("passageiro"));
                p.setFrota(resultado.getString("frota"));
                p.setPoltrona(resultado.getInt("poltrona"));
                p.setPontoPartida(resultado.getInt("pontoPartida"));
                p.setDestino(resultado.getInt("destino"));
                retorno.add(p);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return retorno;
    }
}
