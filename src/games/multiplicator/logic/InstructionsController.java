/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package games.multiplicator.logic;

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
import logic.GamesMenuController;

/**
 * FXML Controller class
 *
 * @author Juan
 */
public class InstructionsController implements Initializable {

    @FXML
    private Button btnPlay;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void play(ActionEvent event) throws IOException {
        games.multiplicator.logic.GameEngine.startGame();
        ((Node)(event.getSource())).getScene().getWindow().hide();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/games/multiplicator/ui/multiplicator.fxml"));
        Parent root = (Parent) fxmlLoader.load();
        Stage stage = new Stage();
        Scene scene=new Scene(root,790,590);
        stage.setScene(scene);  
        stage.setResizable(false);
        scene.getStylesheets().add(getClass().getResource("/fonts/styles.css").toExternalForm());
        stage.show(); 
        stage.setOnCloseRequest((WindowEvent we) -> {
            try {
                openGamesMenu();
            } catch (IOException ex) {
                Logger.getLogger(GamesMenuController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
    }
    
    private void openGamesMenu() throws IOException{
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/ui/gamesMenu.fxml"));
        Parent root = (Parent) fxmlLoader.load();
        Stage stage = new Stage();
        Scene scene=new Scene(root,790,590);
        stage.setScene(scene);  
        stage.setResizable(false);
        stage.show(); 
    }
    
}
