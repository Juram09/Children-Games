/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logic;

import games.tetris.logic.Board;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author Juan
 */
public class Proyecto extends Application{

    static Board board;
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/ui/MainMenu.fxml"));
        Stage stage = new Stage();
        Scene scene=new Scene((Parent) fxmlLoader.load(),790,590);
        stage.setScene(scene); 
        stage.setTitle ( "Children Games" );
        stage.setResizable(false);
        stage.showAndWait();
    }

    public static Board getBoard() {
        return board;
    } 
    
    public static void setBoard(Board board) {
        Proyecto.board = board;
    }
    
    
}
