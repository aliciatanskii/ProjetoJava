/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.projeto.controller;

import com.projeto.model.domain.Motorista;
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
public class FXMLCadastroMotoristaDialogController implements Initializable {
    
    @FXML
    TextField textFieldMotoristaCpf;
    @FXML
    TextField textFieldMotoristaNome;
    @FXML
    TextField textFieldMotoristaCnh;
    @FXML
    Button btnMotoristaSalvar;
    @FXML
    Button btnMotoristaCancelar;

    private Stage dialogStage;
    private boolean btnSalvarMotoristaCLicked = false;
    private Motorista motorista;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }    

    public Stage getDialogStage() {
        return dialogStage;
    }

    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }

    public boolean isBtnSalvarPessoaCLicked() {
        return btnSalvarMotoristaCLicked;
    }

    public void setBtnSalvarPessoaCLicked(boolean btnSalvarPessoaCLicked) {
        this.btnSalvarMotoristaCLicked = btnSalvarPessoaCLicked;
    }

    public Motorista getMotorista() {
        return motorista;
    }

    public void setMotorista(Motorista motorista) {
        this.motorista = motorista;
        this.textFieldMotoristaCpf.setText(motorista.getCpf());
        this.textFieldMotoristaNome.setText(motorista.getNome());
        this.textFieldMotoristaCnh.setText(String.valueOf(motorista.getCnh()));
    }

    public void isEdit(boolean isEdit) {
        textFieldMotoristaCpf.setEditable(!isEdit); 
    }
    
    @FXML
    public void handleButtonSalvarPessoa() {
        motorista.setCpf(textFieldMotoristaCpf.getText());
        motorista.setNome(textFieldMotoristaNome.getText());
        motorista.setCnh(Integer.valueOf(textFieldMotoristaCnh.getText()));
        
        btnSalvarMotoristaCLicked = true;
        dialogStage.close();
    }
    
    @FXML
    public void handleButtonCancelar() {
        dialogStage.close();
    }
}
