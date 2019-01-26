/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.multitoolv2;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 *
 * @author harju
 */



public class FXMLDocumentController implements Initializable {
    
    static String adminUsername = ""; //static variable for login process  
    static String adminPassword = "";
    
    @FXML
    private Label loginLabel;
    @FXML
    private Label passwordLabel;
    @FXML
    private Label statusLabel;
    @FXML
    private TextField loginTextField;
    @FXML
    private PasswordField passwordTextField;
    
    public FXMLDocumentController() {
       
    }
    
    
    @FXML
    private void loginAction(ActionEvent event) {
        
        if (loginTextField.getText().equals(adminUsername) && passwordTextField.getText().equals(adminPassword)) {
           statusLabel.setText("Kisu");
            try {
               
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/Main_Panel.fxml")); // To specify what fxml-document we want to.
                Parent root = (Parent) loader.load();
                Stage stage = new Stage(); // Creating new Stage
                Stage current = (Stage) ((Node) event.getSource()).getScene().getWindow(); // Get the current window
                stage.setTitle("YoutubeDownloader");
                current.hide(); // Hide the curren stage window
                stage.setScene(new Scene(root)); // Scene will become Main_Panel
                stage.show(); // Open new stage window
                
                
                
            } catch (Exception e) {
                
                System.out.println("Cant open Youtubedownloader" + e.getMessage()); 
                
            }
            
        } else {
            statusLabel.setText("Wrong username or password");
        }
        
    }
    
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
