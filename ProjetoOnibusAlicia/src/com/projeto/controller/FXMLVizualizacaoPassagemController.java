/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.projeto.controller;

import com.projeto.model.dao.PassagemDAO;
import com.projeto.model.database.Database;
import com.projeto.model.domain.Frota;
import com.projeto.model.domain.Passageiro;
import com.projeto.model.domain.Passagem;
import com.projeto.model.domain.Poltrona;
import java.net.URL;
import java.sql.Connection;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * FXML Controller class
 *
 * @author Alícia Tanski
 */
public class FXMLVizualizacaoPassagemController  extends FXMLTelaPrincipalController implements Initializable {

    @FXML
    private TableView<Passagem> tableViewPassagem;
    @FXML
    private TableColumn<Passagem, Passageiro> tableColumnCpf;
    @FXML
    private TableColumn<Passagem, Frota> tableColumnFrota;
    @FXML
    private TableColumn<Passagem, Passageiro> tableColumnPassagem;
    @FXML
    private TableColumn<Passagem, Poltrona> tableColumnPoltrona;
    @FXML
    private TableColumn<Passagem, Integer> tableColumnPontoPartida;
    @FXML
    private TableColumn<Passagem, Integer> tableColumnDestino;
    @FXML
    private TableColumn<Passagem, Integer> tableColumnCodBarras;
    
    private Passagem passagem;
    private List<Passagem> listPassagem;
    private ObservableList<Passagem> observableListPassagem;
    
    //Banco de Dados
    private final Database database = new Database();
    private final Connection connection = database.getConexao();
    private final PassagemDAO passagemDAO = new PassagemDAO();
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        passagemDAO.setConnection(connection);
        loadTableViewPassagem();
     }    

    public Passagem getPassagem() {
        return passagem;
    }

    public void setPassagem(Passagem passagem) {
        this.passagem = passagem;
//        this.textFieldPassagem.setText(passagem.getPassageiro());
//        this.textFieldPassagemNome.setText(passagem.get());
    }
    
    
    public void loadTableViewPassagem() {
        tableColumnCpf.setCellValueFactory(new PropertyValueFactory<>("passageiro"));
        tableColumnFrota.setCellValueFactory(new PropertyValueFactory<>("frota"));
        tableColumnCodBarras.setCellValueFactory(new PropertyValueFactory<>("codBarras"));
        tableColumnPoltrona.setCellValueFactory(new PropertyValueFactory<>("poltrona"));
        tableColumnPontoPartida.setCellValueFactory(new PropertyValueFactory<>("pontoPartida"));
        tableColumnDestino.setCellValueFactory(new PropertyValueFactory<>("destino"));
    
        listPassagem = passagemDAO.listar();
        
        observableListPassagem = FXCollections.observableArrayList(listPassagem);
        tableViewPassagem.setItems(observableListPassagem);
    }
    
}
