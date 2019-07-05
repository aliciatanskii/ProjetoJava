/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.projeto.controller;

import com.projeto.model.dao.MotoristaDao;
import com.projeto.model.database.Database;
import com.projeto.model.domain.Motorista;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Alícia Tanski
 */
public class FXMLCadastroMotoristaController extends FXMLTelaPrincipalController implements Initializable {

    @FXML
    private TableView<Motorista> tableViewMotorista;
    @FXML
    private TableColumn<Motorista, String> tableColumnCpf;
    @FXML
    private TableColumn<Motorista, String> tableColumnNome;
    @FXML
    private TableColumn<Motorista, String> tableColumnCnh;
    @FXML
    private Label labelMotoristaCpf;
    @FXML
    private Label labelMotoristaNome;
    @FXML
    private Label labelMotoristaCnh;
    @FXML
    private Button btnMotoristaInserir;
    @FXML
    private Button btnMotoristaAlterar;
    @FXML
    private Button btnMotoristaRemover;
        @FXML
    MenuItem menuItemMotorista;
    @FXML
    AnchorPane anchorPane;
    @FXML
    MenuItem menuItemPassageiro;
    @FXML
    MenuItem menuItemEmpresa;
    @FXML
    MenuItem menuItemPassagem;
    private List<Motorista> listMotorista;
    private ObservableList<Motorista> observableListMotorista;
    
    //Banco de Dados
    private final Database database = new Database();
    private final Connection connection = database.getConexao();
    private final MotoristaDao motoristaDao = new MotoristaDao();
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        motoristaDao.setConnection(connection);;
        loadTableViewMotorista();
        
        //Listen TableViewMotorista
        tableViewMotorista.getSelectionModel().selectedItemProperty().addListener(
            (observable, oldValue, newValue) -> selecionarItemTableViewPessoa(newValue));
    }    
    
    public void loadTableViewMotorista() {
        tableColumnCpf.setCellValueFactory(new PropertyValueFactory<>("cpf"));
        tableColumnNome.setCellValueFactory(new PropertyValueFactory<>("nome"));
        tableColumnCnh.setCellValueFactory(new PropertyValueFactory<>("cnh"));
    
        listMotorista = motoristaDao.listar();
        
        observableListMotorista = FXCollections.observableArrayList(listMotorista);
        tableViewMotorista.setItems(observableListMotorista);
    }
    
    private void selecionarItemTableViewPessoa(Motorista motorista) {
        if(motorista != null) {
            labelMotoristaCpf.setText(motorista.getCpf());
            labelMotoristaNome.setText(motorista.getNome());
            labelMotoristaCnh.setText(String.valueOf(motorista.getCnh()));
        } else {
            labelMotoristaCpf.setText("");
            labelMotoristaNome.setText("");
            labelMotoristaCnh.setText("");
        }
    }
    
    @FXML
    private void handleBtnInserir() throws IOException {
        Motorista motorista = new Motorista();
        boolean btnSalvarClicked = showCadastroMotoristaDialog(motorista, false);
        if(btnSalvarClicked) {
            motoristaDao.inserir(motorista);
            loadTableViewMotorista();
        }
        
    }
    
    @FXML
    private void handleBtnAlterar() throws IOException {
        Motorista motorista = tableViewMotorista.getSelectionModel().getSelectedItem();
        if (motorista != null) {
            boolean btnSalverClicked = showCadastroMotoristaDialog(motorista, true);
            if(btnSalverClicked) {
                motoristaDao.alterar(motorista);
                loadTableViewMotorista();
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Selecione uma pessoa");
            alert.show();
        }
    }
    
    @FXML
    private void handleBtnDelete(){
        Motorista motorista = tableViewMotorista.getSelectionModel().getSelectedItem();
        if (motorista != null) {
            motoristaDao.remover(motorista);
            loadTableViewMotorista();
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Selecione uma pessoa");
            alert.show();
        }
    }
    
    private boolean showCadastroMotoristaDialog(Motorista motorista, boolean isEdit) throws IOException {
        FXMLLoader loader = new  FXMLLoader();
        loader.setLocation(FXMLCadastroMotoristaDialogController.class.getResource("/com/projeto/view/FXMLCadastroMotoristaDialog.fxml"));
        AnchorPane page = loader.load();
        
        // Create Stage Dialog
        Stage dialogStage = new Stage();
        dialogStage.setTitle("Cadastro Motorista");
        Scene scene = new Scene(page);
        dialogStage.setScene(scene);
        
        //Set motorista in controller
        FXMLCadastroMotoristaDialogController controller = loader.getController();
        controller.setDialogStage(dialogStage);
        controller.setMotorista(motorista);
        controller.isEdit(isEdit);
        
        //Show Dialog
        dialogStage.showAndWait();
        
        return controller.isBtnSalvarPessoaCLicked();
    }

}
