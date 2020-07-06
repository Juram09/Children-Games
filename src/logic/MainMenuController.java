/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logic;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

/**
 * FXML Controller class
 *
 * @author Juan
 */
public class MainMenuController implements Initializable {

    @FXML
    private Button gamesButton;
    @FXML
    private Button creditsButton;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void goGames(ActionEvent event) throws IOException {
        ((Node)(event.getSource())).getScene().getWindow().hide();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/ui/gamesMenu.fxml"));
        Parent root = (Parent) fxmlLoader.load();
        Stage stage = new Stage();
        Scene scene=new Scene(root,790,590);
        stage.setScene(scene);  
        stage.setResizable(false);
        stage.show();
        stage.setOnCloseRequest((WindowEvent we) -> {
            try {
                openMainMenu();
            } catch (IOException ex) {
                Logger.getLogger(GamesMenuController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
    }

    @FXML
    private void goCredits(ActionEvent event) throws IOException {
        ((Node)(event.getSource())).getScene().getWindow().hide();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/ui/credits.fxml"));
        Parent root = (Parent) fxmlLoader.load();
        Stage stage = new Stage();
        Scene scene=new Scene(root,783,590);
        stage.setScene(scene);  
        stage.setResizable(false);
        stage.show();
        stage.setOnCloseRequest((WindowEvent we) -> {
            try {
                openMainMenu();
            } catch (IOException ex) {
                Logger.getLogger(GamesMenuController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
    }
    private void openMainMenu() throws IOException{
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/ui/mainMenu.fxml"));
        Parent root = (Parent) fxmlLoader.load();
        Stage stage = new Stage();
        Scene scene=new Scene(root,790,590);
        stage.setScene(scene);  
        stage.setResizable(false);
        stage.show(); 
        stage.setOnCloseRequest((WindowEvent we) -> {
                   System.exit(0);
        });
    }
    
}
