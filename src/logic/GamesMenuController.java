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
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import games.tetris.logic.Board;

/**
 * FXML Controller class
 *
 * @author Juan
 */
public class GamesMenuController implements Initializable {

    @FXML
    private Button matematicas;
    @FXML
    private Button lenguaje;
    @FXML
    private Button artes;
    @FXML
    private Button musica;
    @FXML
    private Button tetris;

    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void goMatematicas(ActionEvent event) throws IOException {
        ((Node)(event.getSource())).getScene().getWindow().hide();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/games/multiplicator/ui/Instructions.fxml"));
        Parent root = (Parent) fxmlLoader.load();
        Stage stage = new Stage();
        Scene scene=new Scene(root,790,590);
        stage.setScene(scene);  
        stage.setTitle ( "Children Games" );
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

    @FXML
    private void goLenguaje(ActionEvent event) throws IOException {
        games.ahorcado.logic.WordsHandler.Load();
        ((Node)(event.getSource())).getScene().getWindow().hide();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/games/ahorcado/ui/Instruccions.fxml"));
        Parent root = (Parent) fxmlLoader.load();
        Stage stage = new Stage();
        Scene scene=new Scene(root,790,590);
        stage.setScene(scene);  
        stage.setTitle ( "Children Games" );
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

    @FXML
    private void goArtes(ActionEvent event) throws IOException {
        ((Node)(event.getSource())).getScene().getWindow().hide();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/games/pixelArt/ui/pixelArt.fxml"));
        Parent root = (Parent) fxmlLoader.load();
        Stage stage = new Stage();
        Scene scene=new Scene(root);
        stage.setScene(scene);  
        stage.setTitle ( "Children Games" );
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

    @FXML
    private void goMusica(ActionEvent event) throws IOException {
        ((Node)(event.getSource())).getScene().getWindow().hide();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/games/pairs/ui/Instruccions.fxml"));
        Parent root = (Parent) fxmlLoader.load();
        Stage stage = new Stage();
        Scene scene=new Scene(root,790,590);
        stage.setScene(scene);  
        stage.setTitle ( "Children Games" );
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

    @FXML
    private void goTetris(ActionEvent event) throws IOException, Exception {
        ((Node)(event.getSource())).getScene().getWindow().hide();
        Proyecto.setBoard(new Board());
        Proyecto.getBoard().start();
    }
        
    private void openGamesMenu() throws IOException{
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/ui/gamesMenu.fxml"));
        Parent root = (Parent) fxmlLoader.load();
        Stage stage = new Stage();
        Scene scene=new Scene(root,790,590);
        stage.setScene(scene);  
        stage.setTitle ( "Children Games" );
        stage.setResizable(false);
        stage.show(); 
        stage.setOnCloseRequest((WindowEvent we) -> {
                   System.exit(0);
        });
    }

}
