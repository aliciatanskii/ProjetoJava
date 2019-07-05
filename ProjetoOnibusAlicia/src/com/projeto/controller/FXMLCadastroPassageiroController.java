/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.projeto.controller;

import com.projeto.model.dao.PassageiroDAO;
import com.projeto.model.database.Database;
import com.projeto.model.domain.Passageiro;
import java.net.URL;
import java.sql.Connection;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * FXML Controller class
 *
 * @author Alícia Tanski
 */
public class FXMLCadastroPassageiroController extends FXMLTelaPrincipalController implements Initializable {

    @FXML
    private TableView<Passageiro> tableViewPassageiro;
    @FXML
    private TableColumn<Passageiro, String> tableColumnCpf;
    @FXML
    private TableColumn<Passageiro, String> tableColumnNome;
    @FXML
    private TableColumn<Passageiro, Boolean> tableColumnPossuiSeguro;
    @FXML
    private TextField textFieldPassageiroCpf;
    @FXML
    private TextField textFieldPassageiroNome;
    @FXML
    private RadioButton radioBtnPassageiroSeguro;
    @FXML
    private Button btnPassageiroNovo;
    @FXML
    private Button btnPassageiroSalvar;
    @FXML
    private Button btnMotoristaRemover;
    
    private Passageiro passageiro;
    private List<Passageiro> listPassageiro;
    private ObservableList<Passageiro> observableListPassageiro;
    
    //Banco de Dados
    private final Database database = new Database();
    private final Connection connection = database.getConexao();
    private final PassageiroDAO passageiroDAO = new PassageiroDAO();
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        passageiroDAO.setConnection(connection);
        loadTableViewPassageiro();
        
        tableViewPassageiro.getSelectionModel().selectedItemProperty().addListener(
        (observable, oldValue, newValue) -> selecionarItemTableViewPassageiro(newValue));
    }    

    public Passageiro getPassageiro() {
        return passageiro;
    }

    public void setPassageiro(Passageiro passageiro) {
        this.passageiro = passageiro;
        this.textFieldPassageiroCpf.setText(passageiro.getCpf());
        this.textFieldPassageiroNome.setText(passageiro.getNome());
        this.radioBtnPassageiroSeguro.setSelected(passageiro.isPossuiSeguro());
    }
    
    
    public void loadTableViewPassageiro() {
        tableColumnCpf.setCellValueFactory(new PropertyValueFactory<>("cpf"));
        tableColumnNome.setCellValueFactory(new PropertyValueFactory<>("nome"));
        tableColumnPossuiSeguro.setCellValueFactory(new PropertyValueFactory<>("possuiSeguro"));
    
        listPassageiro = passageiroDAO.listar();
        
        observableListPassageiro = FXCollections.observableArrayList(listPassageiro);
        tableViewPassageiro.setItems(observableListPassageiro);
    }
    
    private void selecionarItemTableViewPassageiro(Passageiro passageiro) {
        if(passageiro != null) {
            textFieldPassageiroCpf.setText(passageiro.getCpf());
            textFieldPassageiroNome.setText(passageiro.getNome());
            radioBtnPassageiroSeguro.setSelected(passageiro.isPossuiSeguro());
        } else {
            textFieldPassageiroCpf.setText("");
            textFieldPassageiroNome.setText("");
            radioBtnPassageiroSeguro.setSelected(false);
        }
    }
    
    @FXML
    private void handleBtnNovo() {
        Passageiro p = new Passageiro();
        p.setCpf(textFieldPassageiroCpf.getText());
        p.setNome(textFieldPassageiroNome.getText());
        p.setPossuiSeguro(radioBtnPassageiroSeguro.selectedProperty().getValue());
        if(!passageiroDAO.verificaCPF(p.getCpf())){
            passageiroDAO.inserir(p);
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("CPF já existe");
            alert.show();
        }
        loadTableViewPassageiro();
    }
    
    @FXML
    private void handleBtnAlterar() {
        Passageiro p = tableViewPassageiro.getSelectionModel().getSelectedItem();
        if(p != null && passageiroDAO.verificaCPF(p.getCpf())){
            p.setNome(textFieldPassageiroNome.getText());
            p.setPossuiSeguro(radioBtnPassageiroSeguro.selectedProperty().getValue());
            passageiroDAO.alterar(p);
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("CPF não existe");
            alert.show();
        }
        loadTableViewPassageiro();
    }
    
    @FXML
    private void handleBtnDelete(){
        Passageiro p = tableViewPassageiro.getSelectionModel().getSelectedItem();
        if (p != null) {
            passageiroDAO.remover(p);
            loadTableViewPassageiro();
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Selecione um passageiro");
            alert.show();
        }
    }
}
