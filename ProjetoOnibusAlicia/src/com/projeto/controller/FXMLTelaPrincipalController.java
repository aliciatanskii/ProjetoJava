/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.projeto.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author Alícia Tanski
 */
public class FXMLTelaPrincipalController implements Initializable {

    @FXML
    MenuItem menuItemMotorista;
    @FXML
    AnchorPane anchorPane;
    @FXML
    MenuItem menuItemFiscal;
    @FXML
    MenuItem menuItemPassageiro;
    @FXML
    MenuItem menuItemEmpresa;
    @FXML
    MenuItem menuItemPassagem;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    public void handleMenuItemMotorista() throws IOException {
        AnchorPane a = FXMLLoader.load(getClass().getResource("/com/projeto/view/FXMLCadastroMotorista.fxml"));
        anchorPane.getChildren().setAll(a);
    }
    public void handleMenuItemPassageiro() throws IOException {
        AnchorPane a = FXMLLoader.load(getClass().getResource("/com/projeto/view/FXMLCadastroPassageiro.fxml"));
        anchorPane.getChildren().setAll(a);
    }
    public void handleMenuItemFiscal() throws IOException {
        AnchorPane a = FXMLLoader.load(getClass().getResource("/com/projeto/view/FXMLCadastroFiscal.fxml"));
        anchorPane.getChildren().setAll(a);
    }
    public void handleMenuItemEmpresa() throws IOException {
        AnchorPane a = FXMLLoader.load(getClass().getResource("/com/projeto/view/FXMLCadastroEmpresa.fxml"));
        anchorPane.getChildren().setAll(a);
    }
    public void handleMenuConsultaPassagem() throws IOException {
        AnchorPane a = FXMLLoader.load(getClass().getResource("/com/projeto/view/FXMLVizualizacaoPassagem.fxml"));
        anchorPane.getChildren().setAll(a);
    }
}
