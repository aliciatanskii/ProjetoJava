/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.projeto.controller;

import com.projeto.model.dao.EmpresaDAO;
import com.projeto.model.database.Database;
import com.projeto.model.domain.Empresa;
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
public class FXMLCadastroEmpresaController extends FXMLTelaPrincipalController implements Initializable {

    @FXML
    private TableView<Empresa> tableViewEmpresa;
    @FXML
    private TableColumn<Empresa, String> tableColumnCnpj;
    @FXML
    private TableColumn<Empresa, String> tableColumnNome;
    @FXML
    private Label labelEmpresaCnpj;
    @FXML
    private Label labelEmpresaNome;
    @FXML
    private Button btnEmpresaInserir;
    @FXML
    private Button btnEmpresaAlterar;
    @FXML
    private Button btnEmpresaRemover;
    
    private List<Empresa> listEmpresa;
    private ObservableList<Empresa> observableEmpresa;
    
    //Banco de Dados
    private final Database database = new Database();
    private final Connection connection = database.getConexao();
    private final EmpresaDAO empresaDAO = new EmpresaDAO();
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        empresaDAO.setConnection(connection);
        loadTableViewEmpresa();
        
             //Listen TableViewEmpresa
        tableViewEmpresa.getSelectionModel().selectedItemProperty().addListener(
            (observable, oldValue, newValue) -> selecionarItemTableViewEmpresa(newValue));
    
    }    
    
    public void loadTableViewEmpresa() {
        tableColumnCnpj.setCellValueFactory(new PropertyValueFactory<>("cnpj"));
        tableColumnNome.setCellValueFactory(new PropertyValueFactory<>("nome"));
    
        listEmpresa = empresaDAO.listar();
        
        observableEmpresa = FXCollections.observableArrayList(listEmpresa);
        tableViewEmpresa.setItems(observableEmpresa);
    }
    
     private void selecionarItemTableViewEmpresa(Empresa empresa) {
        if(empresa != null) {
            labelEmpresaCnpj.setText(empresa.getCnpj());
            labelEmpresaNome.setText(empresa.getNome());
        } else {
            labelEmpresaCnpj.setText("");
            labelEmpresaNome.setText("");
        }
    }
    
    @FXML
    private void handleBtnEmpresaInserir() throws IOException {
        Empresa empresa = new Empresa();
        boolean btnSalvarClicked = showCadastroEmpresaDialog(empresa, false);
        if(btnSalvarClicked) {
            empresaDAO.inserir(empresa);
            loadTableViewEmpresa();
        }
        
    }
    
    @FXML
    private void handleBtnEmpresaAlterar() throws IOException {
        Empresa empresa = tableViewEmpresa.getSelectionModel().getSelectedItem();
        if (empresa != null) {
            boolean btnSalverClicked = showCadastroEmpresaDialog(empresa, true);
            if(btnSalverClicked) {
                empresaDAO.alterar(empresa);
                loadTableViewEmpresa();
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Selecione uma Empresa");
            alert.show();
        }
    }
    
    @FXML
    private void handleBtnEmpresaDelete(){
        Empresa empresa = tableViewEmpresa.getSelectionModel().getSelectedItem();
        if (empresa != null) {
            empresaDAO.remover(empresa);
            loadTableViewEmpresa();
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Selecione uma Empresa");
            alert.show();
        }
    }
    
    private boolean showCadastroEmpresaDialog(Empresa empresa, boolean isEdit) throws IOException {
        FXMLLoader loader = new  FXMLLoader();
        loader.setLocation(FXMLCadastroEmpresaDialogController.class.getResource("/com/projeto/view/FXMLCadastroEmpresaDialog.fxml"));
        AnchorPane page = loader.load();
        
        // Create Stage Dialog
        Stage dialogStage = new Stage();
        dialogStage.setTitle("Cadastro empresa");
        Scene scene = new Scene(page);
        dialogStage.setScene(scene);
        
        //Set empresa in controller
        FXMLCadastroEmpresaDialogController controller = loader.getController();
        controller.setDialogStage(dialogStage);
        controller.setEmpresa(empresa);
        controller.isEdit(isEdit);
        
        //Show Dialog
        dialogStage.showAndWait();
        
        return controller.isBtnSalvarEmpresaClicked();
    }
    
}
