/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.multitoolv2;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.embed.swing.SwingFXUtils;
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
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javax.imageio.ImageIO;

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
    @FXML
    private ImageView loginScreenIW;
    @FXML
    private Pane testi;
    
    public static Stage stage;
    
    
    public FXMLDocumentController() {
        loginScreenIW = new ImageView();
        
         
    
        
       
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
                stage.getIcons().add(new javafx.scene.image.Image(Multitool.class.getResourceAsStream("/images/youtube.png")));
                stage.setResizable(false);
                stage.sizeToScene();
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
    
    private static BufferedImage resize(BufferedImage img, int height, int width) {
        Image tmp = img.getScaledInstance(width, height, Image.SCALE_SMOOTH);
        BufferedImage resized = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = resized.createGraphics();
        g2d.drawImage(tmp, 0, 0, null);
        g2d.dispose();
        return resized;
    }

    
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        
       
        try {
            BufferedImage image =  ImageIO.read(getClass().getResourceAsStream("/images/clouds.jpg"));
           
            BufferedImage resized = resize(image, 385, 296);
        
        loginScreenIW.setImage(SwingFXUtils.toFXImage(resized,null));
        
       
        
        
        } catch (Exception e) {
        }
       
        }
    }    
    

