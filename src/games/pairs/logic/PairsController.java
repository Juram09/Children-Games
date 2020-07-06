/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package games.pairs.logic;

import games.pairs.data.Note;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Random;
import java.util.ResourceBundle;
import javafx.animation.PauseTransition;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.media.AudioClip;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.text.Font;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Juan
 */
public class PairsController implements Initializable {

    @FXML
    private AnchorPane pane;
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
    private ImageView img8;
    @FXML
    private ImageView img9;
    @FXML
    private ImageView img10;
    @FXML
    private ImageView img11;
    @FXML
    private ImageView img12;
    @FXML
    private ImageView img13;
    @FXML
    private ImageView img14;
    @FXML
    private Button b1;
    @FXML
    private Button b2;
    @FXML
    private Button b3;
    @FXML
    private Button b4;
    @FXML
    private Button b5;
    @FXML
    private Button b6;
    @FXML
    private Button b7;
    @FXML
    private Button b8;
    @FXML
    private Button b9;
    @FXML
    private Button b10;
    @FXML
    private Button b11;
    @FXML
    private Button b12;
    @FXML
    private Button b13; 
    @FXML
    private Button b14;
    
    private String array[]=new String[14];
    private Note xd[]={new Note("do"),new Note("re"),new Note("mi"),new Note("fa"),new Note("sol"),new Note("la"),new Note("si")};
    private ImageView imgs[]={img1,img2,img3,img4,img5,img6,img7,img8,img9,img10,img11,img12,img13,img14};
    private boolean activate[]=new boolean[14];
    PauseTransition wait = new PauseTransition(javafx.util.Duration.seconds(0.3));
    long initTime=System.currentTimeMillis();
    boolean active=true;
    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Font.loadFont(getClass().getResource("/fonts/Homework.ttf").toExternalForm(),10); 
        organizeArray();
    }    

    @FXML
    private void show(ActionEvent event) throws IOException {
        if(active){
            Button btn=((Button)event.getSource());
            ImageView img=checkImage(((Button)event.getSource()).getId());
            btn.setVisible(false);
            img.setImage(new Image(getClass().getResourceAsStream("/games/pairs/imgs/"+array[Integer.parseInt(img.getId().substring(3))-1]+".jpg")));
            activate[Integer.parseInt(img.getId().substring(3))-1]=true;
            AudioClip sounds=new AudioClip(getClass().getResource("/games/pairs/sounds/"+array[Integer.parseInt(img.getId().substring(3))-1].substring(0, 2)+".wav").toString());
            sounds.play();
            if(checkImages()){
                active=false;
                wait.setOnFinished((e) -> {
                    if(checkEqual(img)){
                        checkInequal(img).setVisible(false);
                        img.setVisible(false);
                    }else{
                        checkInequal(img).setImage(new Image(getClass().getResourceAsStream("/games/pairs/imgs/QuestionMark.jpg")));
                        checkInequalButton(img).setVisible(true);
                        img.setImage(new Image(getClass().getResourceAsStream("/games/pairs/imgs/QuestionMark.jpg")));
                        btn.setVisible(true);
                    } 
                       activateArray();
                });
                wait.play();
            }
        }
        if(checkWin()){
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setHeaderText(null);
            alert.setTitle("GANASTE");
            alert.setContentText(this.getActualTime());
            alert.showAndWait();
            ((Node)(event.getSource())).getScene().getWindow().hide();
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/ui/gamesMenu.fxml"));
            Parent root = (Parent) fxmlLoader.load();
            Stage stage = new Stage();
            Scene scene=new Scene(root,790,590);
            stage.setScene(scene);  
            stage.setTitle ( "Children Games" );
            stage.setResizable(false);
            stage.show();
        }
    }
    
    private void organizeArray() {
        for(int i=0;i<7;i++){
            int x=1;
            while(x<=2){
              int random=new Random().nextInt(array.length);
              if(array[random]==null){
                array[random]=xd[i].getNombre().substring(0, 2)+x;
                x++;
                
              }
            }
        }
        activateArray();
    }    

    private ImageView checkImage(String button) {
        int number=Integer.parseInt(button.substring(1));
        ObservableList<Node> children = pane.getChildren();
        for (Node child : children) {
            if (child instanceof ImageView) {
                if(child.getId().equals("img"+number)){
                    return (ImageView) child;
                }
            }
        }
        return null;
    }

    private boolean checkImages() {
        int a=0;
        for(int i=0;i<activate.length;i++){
            if(activate[i]){
                a++;
            }
        }
        if(a==2){
            return true;
        }
        return false;
    }

    private boolean checkEqual(ImageView img) {
        ObservableList<Node> children = pane.getChildren();
        for (Node child : children) {
            if (child instanceof ImageView) {
                if(!child.equals(img)){
                    if(activate[Integer.parseInt(child.getId().substring(3))-1]&& array[Integer.parseInt(child.getId().substring(3))-1].substring(0, 2).equals(array[Integer.parseInt(img.getId().substring(3))-1].substring(0, 2))){
                        return true;
                    }
                }
            }
        }
        return false;
    }

    private void activateArray() {
        for(int i=0;i<activate.length;i++){
            activate[i]=false;
        }
        active=true;
    }
    
    private ImageView checkInequal(ImageView img) {
        ObservableList<Node> children = pane.getChildren();
        for (Node child : children) {
            if (child instanceof ImageView) {
                if(child!=img && activate[Integer.parseInt(child.getId().substring(3))-1]){
                    return (ImageView) child;
                }
            }
        }
        return null;
    }
    
    private Button checkInequalButton(ImageView img) {
        int number=Integer.parseInt(img.getId().substring(3));
        ObservableList<Node> children = pane.getChildren();
        for (Node child : children) {
            if (child instanceof Button) {
                if((!child.getId().equals("b"+number)) && (activate[Integer.parseInt(child.getId().substring(1))-1])){
                    return (Button) child;
                }
            }
        }
        return null;
    }

    private boolean checkWin() {
        ObservableList<Node> children = pane.getChildren();
        for (Node child : children) {
            if (child instanceof Button) {
                if(child.isVisible()){
                    return false;
                }
            }
        }
        return true;
    } 
    
    private String getActualTime(){
        long seconds=((System.currentTimeMillis()-initTime)/1000);
        if(seconds>60){
            return ("Demoraste "+(int)(seconds/60)+"minutos y "+seconds%60+" segundos");
        }else{
           return ("Demoraste "+seconds+" segundos");
        }
    }
}
