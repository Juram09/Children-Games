/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package games.ahorcado.logic;

import data.textSubType;
import java.io.IOException;
import java.net.URL;
import java.util.Optional;
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
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import logic.GamesMenuController;


/**
 * FXML Controller class
 *
 * @author Juan
 */
public class AhorcadoController implements Initializable{

    
    @FXML
    private ImageView img1;
    @FXML
    private ImageView img2;
    @FXML
    private ImageView img3;
    @FXML
    private ImageView img4;
    @FXML
    private ImageView img5;
    @FXML
    private ImageView img6;
    @FXML
    private ImageView img7;
    @FXML
    private Label word;
    @FXML
    private TextField text;
    @FXML
    private Button playButton;
    @FXML
    private GridPane grid;
    @FXML
    private Label LblName;
    @FXML
    private Label LblDescription1;
    @FXML
    private Label LblType;
    @FXML
    private Label LblGenre;
    @FXML
    private Label LblDescription2;
    @FXML
    private Label win;
    
    private textSubType secretWord=WordsHandler.getPalabra(this);
    private int oportunidades=6;
    private boolean ready=false;
    
    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        text.requestFocus();
        Font.loadFont(getClass().getResource("/fonts/Homework.ttf").toExternalForm(),10);     
        // TODO
    }    
    
    @FXML
    private void search(KeyEvent event) {
        if(ready){
            if(this.text.getText().length()>0 && (((int) this.text.getText().charAt(0)<91 && (int) this.text.getText().charAt(0)>64) || ((int) this.text.getText().charAt(0)<123 && (int) this.text.getText().charAt(0)>96))){
                String numero=String.valueOf(this.text.getText().toUpperCase().charAt(0));
                if(oportunidades>0){
                    if(secretWord.getSubType().toUpperCase().contains(numero)){
                        String aux="";
                        for(int i=0;i<secretWord.getSubType().length();i++){
                            if(word.getText().charAt(i)=='_'){
                                if(secretWord.getSubType().toUpperCase().charAt(i)==numero.charAt(0)){
                                    aux+=numero.charAt(0);
                                }else{
                                    aux+='_';
                                }
                            }else{
                                aux+=word.getText().charAt(i);
                            }
                        }
                        this.word.setText(aux);
                        if(this.word.getText().equals(this.secretWord.getSubType().toUpperCase())){
                            win.setText("¡GANASTE!");
                            grid.setVisible(true);
                            LblName.setText(secretWord.getSubType());
                            LblDescription1.setText(secretWord.getSubDescription());
                            LblType.setText(secretWord.getType());
                            LblGenre.setText(secretWord.getNombre());
                            LblDescription2.setText(secretWord.getDescripcion());
                            this.playButton.setDisable(false);
                            this.playButton.setText("JUGAR DE NUEVO");
                        }
                    }else{
                        oportunidades--;
                        switch(oportunidades){
                            case 5:
                                img2.setVisible(true);
                                break;
                            case 4:
                                img3.setVisible(true);
                                break;
                            case 3:
                                img4.setVisible(true);
                                break;
                            case 2:
                                img5.setVisible(true);
                                break;
                            case 1:
                                img6.setVisible(true);
                                break;
                            case 0:
                                img7.setVisible(true);
                                break;
                        }
                        if(oportunidades<1){
                            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                            alert.setHeaderText(null);
                            alert.setTitle("Confirmacion");
                            alert.setContentText("Perdiste, ¿quieres jugar de nuevo?");
                            Optional<ButtonType>result=alert.showAndWait();
                            if(result.get()==ButtonType.OK){
                                try{       
                                    FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/games/ahorcado/ui/ahorcado.fxml"));
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
                                            Logger.getLogger(AhorcadoController.class.getName()).log(Level.SEVERE, null, ex);
                                        }
                                    });  
                                    ((Node)(event.getSource())).getScene().getWindow().hide();
                                }catch(IOException e){
                                }
                            }else{
                                win.setText("PERDISTE :(");
                                grid.setVisible(true);
                                LblName.setText(secretWord.getSubType());
                                LblDescription1.setText(secretWord.getSubDescription());
                                LblType.setText(secretWord.getType());
                                LblGenre.setText(secretWord.getNombre());
                                LblDescription2.setText(secretWord.getDescripcion());
                                this.playButton.setDisable(false);
                                this.playButton.setText("JUGAR DE NUEVO");
                            }
                        }
                    }
                }
            }
        }
        else{
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText(null);
            alert.setTitle("Se necesita iniciar el juego");
            alert.setContentText("Para poder jugar debes oprimir en el boton de jugar");
            alert.showAndWait(); 
        }
        this.text.setText("");
        text.requestFocus();
    }
    
    public Label getWord() {
        return word;
    }

    public void setWord(Label word) {
        this.word = word;
    }    

    @FXML
    private void play(ActionEvent event) {
        String secret="";
        for(int i=0;i<secretWord.getSubType().length();i++){
            if(secretWord.getSubType().charAt(i)!=' '){
                secret+="_";
            }else{
                secret+=" ";
            }
        }
        if(playButton.getText().equals("JUGAR DE NUEVO")){
            try{       
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/games/ahorcado/ui/ahorcado.fxml"));
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
                    Logger.getLogger(AhorcadoController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }); 
            ((Node)(event.getSource())).getScene().getWindow().hide();
        }catch(IOException e){
        }
        }else{
            word.setText(secret);
            ready=true;
            this.text.setText("");
            this.playButton.setDisable(true);  
        }
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
