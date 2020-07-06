/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package games.pixelArt.logic;

import java.awt.Label;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.ObservableList;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.image.WritableImage;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import javax.imageio.ImageIO;
import javax.swing.JFileChooser;
import logic.GamesMenuController;

/**
 * FXML Controller class
 *
 * @author Juan
 */
public class Drawing implements Initializable {

    @FXML
    private GridPane grid;
    @FXML
    private ColorPicker colour;
    @FXML
    private Button btnSave;
    @FXML
    private RadioButton move;
    @FXML
    private RadioButton click;
    @FXML
    private Button btnReiniciar;
    
    /**
     * Initializes the controller class.
     * @param url
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Font.loadFont(getClass().getResource("/fonts/Homework.ttf").toExternalForm(),10);
        for(int i=0;i<24;i++){
            for(int j=0;j<19;j++){
                TextField area = new TextField();
                grid.add(area,i,j,1,1);
                area.setStyle("-fx-background-radius: 0");
                area.setEditable(false);
                area.setOnMouseClicked(new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent event) {
                        paint(area);
                    }
                    });
            }
        }
    }    

    @FXML
    private void save(ActionEvent event) {
        File file=guardarComo();
        String newPath="";
        if(file!=null){     
            if(file.getName().contains(".")){
                for(int i=0;i<file.getName().length();i++){
                    if(file.getName().charAt(i)!='.'){
                        newPath+=file.getName().charAt(i);
                    }else{
                        i=file.getPath().length();
                    }
                }
                newPath+=".png";
            }else{
                newPath=file.getName()+".png";
            }
            File actualFile=new File(file.getParent(), newPath);
            grid.setGridLinesVisible(false);
            checkUnable();
            WritableImage snapshot = grid.snapshot(null,null);
            try {
                ImageIO.write(SwingFXUtils.fromFXImage(snapshot, null), "png", actualFile);
            } catch (IOException e) {
            }
            checkUnable();
        }
        
    }
    private void paint(TextField area) {
        area.setStyle("-fx-background-color: "+toHexString(colour.getValue())+"; -fx-background-radius: 0");
    }

    @FXML
    private void clickable(ActionEvent event) {
        move.setSelected(false);
        click.setSelected(true);
        ObservableList<Node> children = grid.getChildren();
        for (Node child : children) {
            if (child instanceof TextField) {
                child.setOnMouseClicked(new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent event) {
                        paint((TextField)child);
                    }
                });
                child.setOnMouseMoved(new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent event) {
                        
                    }
                });
            }
        }
    }

    @FXML
    private void moveable(ActionEvent event) {
        move.setSelected(true);
        click.setSelected(false);
        ObservableList<Node> children = grid.getChildren();
        for (Node child : children) {
            if (child instanceof TextField) {
                child.setOnMouseMoved(new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent event) {
                        paint((TextField)child);
                    }
                });
                child.setOnMouseClicked(new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent event) {
                    }
                });
            }
        }
    }
    private String format(double val) {
    String in = Integer.toHexString((int) Math.round(val * 255));
    return in.length() == 1 ? "0" + in : in;
}

    public String toHexString(Color value) {
        return "#" + (format(value.getRed()) + format(value.getGreen()) + format(value.getBlue()) + format(value.getOpacity()))
                .toUpperCase();
    }

    private void checkUnable() {
        ObservableList<Node> children = grid.getChildren();
        for (Node child : children) {
            if (child instanceof TextField) {
                if(child.getStyle().equals("-fx-background-radius: 0")){
                    child.setVisible(!child.isVisible());
                }
            }
        }
    }
    
    public File guardarComo(){
    JFileChooser guardar = new JFileChooser();
    guardar.showSaveDialog(null);
    guardar.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
    return guardar.getSelectedFile();
}

    @FXML
    private void reload(ActionEvent event) throws IOException {
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
