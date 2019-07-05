/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.projeto.controller;

import com.projeto.model.domain.Empresa;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;


/**
 * FXML Controller class
 *
 * @author Alícia Tanski
 */
public class FXMLCadastroEmpresaDialogController implements Initializable {

    @FXML
    private TextField textFieldEmpresaCnpj;
    @FXML
    private TextField textFieldEmpresaNome;
    @FXML
    private Button btnEmpresaSalvar;
    @FXML
    private Button btnEmpresaCancelar;

    private Stage dialogStage;
    private boolean btnSalvarEmpresaClicked = false;
    private Empresa empresa;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }    

    public Stage getDialogStage() {
        return dialogStage;
    }

    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }

    public boolean isBtnSalvarEmpresaClicked() {
        return btnSalvarEmpresaClicked;
    }

    public void setBtnSalvarEmpresaClicked(boolean btnSalvarPessoaCLicked) {
        this.btnSalvarEmpresaClicked = btnSalvarPessoaCLicked;
    }

    public Empresa getEmpresa() {
        return empresa;
    }

    public void setEmpresa(Empresa empresa) {
        this.empresa = empresa;
        this.textFieldEmpresaCnpj.setText(empresa.getCnpj());
        this.textFieldEmpresaNome.setText(empresa.getNome());
    }

    public void isEdit(boolean isEdit) {
        textFieldEmpresaCnpj.setEditable(!isEdit); 
    }
    
    @FXML
    public void handleButtonSalvarEmpresa() {
        empresa.setCnpj(textFieldEmpresaCnpj.getText());
        empresa.setNome(textFieldEmpresaNome.getText());
        
        btnSalvarEmpresaClicked = true;
        dialogStage.close();
    }
    
    @FXML
    public void handleButtonCancelarEmpresa() {
        dialogStage.close();
    }
    
}
