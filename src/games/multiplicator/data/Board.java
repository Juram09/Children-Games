/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package games.multiplicator.data;

/**
 *
 * @author Juan
 */
public class Board {
    
    private Square[][] board;

    public Board() {

        char value = 'X';
        board = new Square[15][15];

        for (int row = 0; row < board.length; row++) {
            for (int col = 0; col < board.length; col++) {
                Square square = new Square(row, col, value);
                board[row][col] = square;
            }
        }
    }

    public Square[][] getBoard() {
        return board;
    }

    public void updateSquare(Square square) {
        board[square.getRow()][square.getCol()] = square;
    }

    @Override
    public String toString() {
        String printBoard = "\n";

        for (int row = 0; row < board.length; row++) {
            printBoard = printBoard.concat("\t|");
            for (int col = 0; col < board.length; col++) {
                printBoard = printBoard.concat(
                        String.valueOf(board[row][col]).concat("|"));
            }
            printBoard = printBoard.concat("\n");
        }
        return printBoard;
    }
}
