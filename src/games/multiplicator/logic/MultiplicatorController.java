/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package games.multiplicator.logic;

import games.multiplicator.data.Board;
import games.multiplicator.data.Player;
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
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import logic.GamesMenuController;

/**
 * FXML Controller class
 *
 * @author Juan
 */
public class MultiplicatorController implements Initializable {

    @FXML
    private ColorPicker playerColor;
    @FXML
    private ColorPicker pcColor;
    @FXML
    private Button playButton;
    @FXML
    private Label playerLast;
    @FXML
    private Label pcLast;
    @FXML
    private Button dadosButton;
    @FXML
    private GridPane grid;
    @FXML
    private Label warning;
    @FXML
    private ImageView dice1;
    @FXML
    private ImageView dice2;
    @FXML
    private ImageView firstDice1;
    @FXML
    private ImageView firstDice2;
    @FXML
    private ImageView secondDice2;
    @FXML
    private ImageView secondDice1;
    
    private int player1=0;
    private int player2=0;
    
    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Font.loadFont(getClass().getResource("/fonts/Homework.ttf").toExternalForm(),10);  
        // TODO
    }    
    
    @FXML
    private void play(ActionEvent event) throws IOException {
        if(playerColor.isDisable()){
            games.multiplicator.logic.GameEngine.startGame();
            ((Node)(event.getSource())).getScene().getWindow().hide();
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/games/multiplicator/ui/multiplicator.fxml"));
            Parent root = (Parent) fxmlLoader.load();
            Stage stage = new Stage();
            Scene scene=new Scene(root);
            scene.getStylesheets().add(getClass().getResource("/fonts/styles.css").toExternalForm());
            stage.setScene(scene);  
            stage.setTitle ( "Children Games" );
            stage.setResizable(false);
            stage.show(); 
            stage.setOnCloseRequest((WindowEvent we) -> {
            try {
                openGamesMenu();
            } catch (IOException ex) {
                Logger.getLogger(GamesMenuController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        }else if(toHexString(playerColor.getValue()).equals("#FFFFFFFF") || toHexString(pcColor.getValue()).equals("#FFFFFFFF") || toHexString(playerColor.getValue()).equals(toHexString(pcColor.getValue())) ){
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setHeaderText(null);
            alert.setTitle("Error");
            alert.setContentText("Ningun jugador puede jugar con blanco y/o no se puede jugar con el mismo color");
            alert.showAndWait();
        }else{
            playerColor.setDisable(true);
            pcColor.setDisable(true);
            dadosButton.setDisable(false);
        }
    }
    
    @FXML
    private void lanzarDados(ActionEvent event) throws IOException{
        if(player1==player2){
            GameEngine.moveGUI(this,new Player("1player",true),((Node)(event.getSource())).getScene());
            player1++;
        }else{
            GameEngine.moveGUI(this,new Player("2player",false),((Node)(event.getSource())).getScene());
            player2++;
        }
        
    }

    public void update(int i, int j, Player player) {
        if(player.isHumanPlayer()){
            TextField Area = new TextField();
            grid.add(Area,j,i,1,1);
            Area.setDisable(true);
            Area.setStyle("-fx-background-color: "+toHexString(playerColor.getValue())+";");
        }else{
            TextField Area = new TextField();
            grid.add(Area, j,i); 
            Area.setDisable(true);
            Area.setStyle("-fx-background-color: "+toHexString(pcColor.getValue())+";");
        }
    }
    public void lastPlayer(String dados){
        playerLast.setVisible(true);
        firstDice1.setImage(new Image(getClass().getResourceAsStream("/games/multiplicator/imgs/"+dados.charAt(0)+".jpg")));
        firstDice2.setImage(new Image(getClass().getResourceAsStream("/games/multiplicator/imgs/"+dados.charAt(2)+".jpg")));
    }
    public void lastPc(String dados){
        pcLast.setVisible(true);
        secondDice1.setImage(new Image(getClass().getResourceAsStream("/games/multiplicator/imgs/"+dados.charAt(0)+".jpg")));
        secondDice2.setImage(new Image(getClass().getResourceAsStream("/games/multiplicator/imgs/"+dados.charAt(2)+".jpg")));
    }

    public void setDados(String dados){ 
        dice1.setImage(new Image(getClass().getResourceAsStream("/games/multiplicator/imgs/"+dados.charAt(0)+".jpg")));
        dice2.setImage(new Image(getClass().getResourceAsStream("/games/multiplicator/imgs/"+dados.charAt(2)+".jpg")));
    }
    
    public void printError(String dados) {
        warning.setText("No hay donde poner un cuadrilatero "+dados.charAt(0)+"x"+dados.charAt(2)+" para este jugador");
    }
    
    public void noError(String dados) {
        warning.setText("");
    }  
    
    private String format(double val) {
    String in = Integer.toHexString((int) Math.round(val * 255));
    return in.length() == 1 ? "0" + in : in;
}

    public String toHexString(Color value) {
        return "#" + (format(value.getRed()) + format(value.getGreen()) + format(value.getBlue()) + format(value.getOpacity()))
                .toUpperCase();
    }
    
    private void openGamesMenu() throws IOException{
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/ui/gamesMenu.fxml"));
        Parent root = (Parent) fxmlLoader.load();
        Stage stage = new Stage();
        Scene scene=new Scene(root,790,590);
        stage.setScene(scene);  
        stage.setTitle("Children Games");
        stage.setResizable(false);
        stage.show(); 
        stage.setOnCloseRequest((WindowEvent we) -> {
                   System.exit(0);
        });
    }

    public int getPlayer1() {
        return player1;
    }

    public void setPlayer1(int player1) {
        this.player1 = player1;
    }
    public void gameOver(Scene scene, Board board) throws IOException{
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText(null);
        alert.setTitle("Game Over");
        alert.setContentText(MovementHandler.checkWin(board));
        alert.showAndWait();
        scene.getWindow().hide();
        openGamesMenu();
    }
}
