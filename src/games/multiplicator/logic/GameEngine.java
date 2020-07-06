/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package games.multiplicator.logic;

import games.multiplicator.data.Board;
import games.multiplicator.data.Player;
import java.io.IOException;
import javafx.scene.Scene;
import javafx.scene.control.Alert;

public class GameEngine {

    private static Board board;
    private static boolean isOver = false;
    

    public static void startGame() {
        board = new Board();
    }

    public static void moveGUI(MultiplicatorController board2, Player current, Scene scene) throws IOException{ 
        if (!isOver) {
            board = TurnController.playTurn(board, current,board2);
            if (MovementHandler.isWinningMovement(board) || board2.getPlayer1()==10){
                isOver = true;
            }
        }else{
            board2.gameOver(scene,board);
        }
    }
}

