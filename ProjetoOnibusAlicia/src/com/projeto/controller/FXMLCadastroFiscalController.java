/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.projeto.controller;

import com.projeto.model.dao.FiscalDAO;
import com.projeto.model.database.Database;
import com.projeto.model.domain.Fiscal;
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
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;


/**
 * FXML Controller class
 *
 * @author Alícia Tanski
 */
public class FXMLCadastroFiscalController extends FXMLTelaPrincipalController implements Initializable {

    @FXML
    private TableView<Fiscal> tableViewFiscal;
    @FXML
    private TableColumn<Fiscal, String> tableColumnCpf;
    @FXML
    private TableColumn<Fiscal, String> tableColumnNome;
    @FXML
    private TableColumn<Fiscal, Double> tableColumnSalario;
    @FXML
    private TextField textFieldFiscalCpf;
    @FXML
    private TextField textFieldFiscalNome;
    @FXML
    private TextField textFieldFiscalSalario;
    @FXML
    private Button btnFiscalNovo;
    @FXML
    private Button btnFiscalSalvar;
    @FXML
    private Button btnFiscalRemover;
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
    
    private Fiscal fiscal;
    private List<Fiscal> listFiscal;
    private ObservableList<Fiscal> observableListFiscal;
    
    //Banco de Dados
    private final Database database = new Database();
    private final Connection connection = database.getConexao();
    private final FiscalDAO fiscalDAO = new FiscalDAO();
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        fiscalDAO.setConnection(connection);
        loadTableViewFiscal();
        
        tableViewFiscal.getSelectionModel().selectedItemProperty().addListener(
        (observable, oldValue, newValue) -> selecionarItemTableViewFiscal(newValue));
    }    

    public Fiscal getFiscal() {
        return fiscal;
    }

    public void setFiscal(Fiscal fiscal) {
        this.fiscal = fiscal;
        this.textFieldFiscalCpf.setText(fiscal.getCpf());
        this.textFieldFiscalNome.setText(fiscal.getNome());
        this.textFieldFiscalSalario.setText(String.valueOf(fiscal.getSalario()));
    }
    
    
    public void loadTableViewFiscal() {
        tableColumnCpf.setCellValueFactory(new PropertyValueFactory<>("cpf"));
        tableColumnNome.setCellValueFactory(new PropertyValueFactory<>("nome"));
        tableColumnSalario.setCellValueFactory(new PropertyValueFactory<>("salario"));
    
        listFiscal = fiscalDAO.listar();
        
        observableListFiscal = FXCollections.observableArrayList(listFiscal);
        tableViewFiscal.setItems(observableListFiscal);
    }
    
    private void selecionarItemTableViewFiscal(Fiscal fiscal) {
        if(fiscal != null) {
            textFieldFiscalCpf.setText(fiscal.getCpf());
            textFieldFiscalNome.setText(fiscal.getNome());
            textFieldFiscalSalario.setText(String.valueOf(fiscal.getSalario()));
        } else {
            textFieldFiscalCpf.setText("");
            textFieldFiscalNome.setText("");
            textFieldFiscalSalario.setText(""
            );
        }
    }
    
    @FXML
    private void handleBtnNovo() {
        Fiscal f = new Fiscal();
        f.setCpf(textFieldFiscalCpf.getText());
        f.setNome(textFieldFiscalNome.getText());
        f.setSalario(Double.valueOf(textFieldFiscalSalario.getText()));
        if(!fiscalDAO.verificaCPF(f.getCpf())){
            fiscalDAO.inserir(f);
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("CPF já existe");
            alert.show();
        }
        loadTableViewFiscal();
    }
    
    @FXML
    private void handleBtnAlterar() {
        Fiscal f = tableViewFiscal.getSelectionModel().getSelectedItem();
        if(f != null && fiscalDAO.verificaCPF(f.getCpf())){
            f.setNome(textFieldFiscalNome.getText());
            f.setSalario(Double.valueOf(textFieldFiscalSalario.getText()));
            fiscalDAO.alterar(f);
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("CPF não existe");
            alert.show();
        }
        loadTableViewFiscal();
    }
    
    @FXML
    private void handleBtnDelete(){
        Fiscal f = tableViewFiscal.getSelectionModel().getSelectedItem();
        if (f != null) {
            fiscalDAO.remover(f);
            loadTableViewFiscal();
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Selecione um Fiscal");
            alert.show();
        }
    }

}
