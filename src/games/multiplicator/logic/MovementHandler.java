package games.multiplicator.logic;

import games.multiplicator.data.Board;
import games.multiplicator.data.Player;
import games.multiplicator.data.Square;

public class MovementHandler {

    public static String isValid(Board board, Player player,String dados) {
        int dado1=Character.getNumericValue(dados.charAt(0));
        int dado2=Character.getNumericValue(dados.charAt(2));
        if(player.isHumanPlayer()){
            for(int i=0;i<15;i++){
                for(int j=0;j<15;j++){ 
                    if (board.getBoard()[i][j].getValue() == 'X') {
                        if(dado1+i<=15 && dado2+j<=15){
                            int a=0;
                            boolean connected=false;
                            for(int k=i;k<i+dado1;k++){
                                for(int l=j;l<j+dado2;l++){                 
                                    if (board.getBoard()[k][l].getValue() == 'X') {
                                        a++;
                                        if(((i==0 && j==0) || ((i!=0 && board.getBoard()[i-1][j].getValue() == '1') || ((j!=0) && (board.getBoard()[i][j-1].getValue() == '1'))))){
                                           connected=true; 
                                        }
                                    }
                                }
                            }
                            if(a==dado1*dado2 && connected){
                                return "1-"+i+"-"+j;
                            }
                        }
                        if(dado1+j<=15 && dado2+i<=15){
                            int a=0;
                            boolean connected=false;
                            for(int k=i;k<i+dado2;k++){
                                for(int l=j;l<j+dado1;l++){                 
                                    if (board.getBoard()[k][l].getValue() == 'X') {
                                        a++;   
                                        if(((i==0 && j==0) || ((i!=0 && board.getBoard()[i-1][j].getValue() == '1') || ((j!=0) && (board.getBoard()[i][j-1].getValue() == '1'))))){
                                           connected=true; 
                                        }
                                    }
                                }
                            }
                            if(a==dado1*dado2){
                                return "2-"+i+"-"+j;
                            }
                        }
                    }
                }
            }
        }else{
            for(int i=14;i>-1;i--){
                for(int j=14;j>-1;j--){
                    if (board.getBoard()[i][j].getValue() == 'X'){
                        if(i-dado1>=-1 && j-dado2>=-1){  
                            int a=0;
                            boolean connected=false;
                            for(int k=i;k>i-dado1;k--){
                                for(int l=j;l>j-dado2;l--){ 
                                    if (board.getBoard()[k][l].getValue() == 'X') {
                                        a++;
                                        if((i==14 && j==14)|| ((i!=14 && board.getBoard()[i+1][j].getValue() == '2') || ((j!=14) && (board.getBoard()[i][j+1].getValue() == '2')))){
                                            connected=true;
                                        }
                                    }
                                }
                            }
                            if(a==dado1*dado2 && connected){
                                return "1-"+i+"-"+j;
                            }
                        }
                        if(j-dado1>=-1 && i-dado2>=-1){
                            int a=0;
                            boolean connected=false;
                            for(int k=i;k>i-dado2;k--){
                                for(int l=j;l>j-dado1;l--){
                                    if (board.getBoard()[k][l].getValue() == 'X') {
                                        a++; 
                                        if((i==14 && j==14)|| ((i!=14 && board.getBoard()[i+1][j].getValue() == '2') || ((j!=14) && (board.getBoard()[i][j+1].getValue() == '2')))){
                                            connected=true;
                                        }
                                    }
                                }
                            }
                            if(a==dado1*dado2 && connected){
                                return "2-"+i+"-"+j;
                            }
                        }
                    }
                }
            }
        }
        return "0";
    }

    public static boolean isWinningMovement(Board board) {

        Square[][] squares = board.getBoard();
        boolean gameOver=true;
        for (int i=0;i<squares.length;i++) {
            for (int j=0;j<squares.length;j++) {
               if(board.getBoard()[i][j].getValue()=='X'){
                   gameOver=false;
               }
            }
        }
        return gameOver;
    }
    public static String checkWin(Board board){
        String winner=null;
        int p1=0;
        int p2=0;
        int no=0;
        for(int i=0;i<15;i++){
            for(int j=0;j<15;j++){
                if(board.getBoard()[i][j].getValue() == '1'){
                    p1++;
                }else if(board.getBoard()[i][j].getValue() == '2'){
                    p2++;
                }else{
                    no++;
                }
            }
        }
        if(p1>p2){
            winner="El jugador 1 ha ganado con "+p1+" bloques\n"+"El jugador 2 ha conseguido "+p2+" bloques\n"+"Hay "+no+" bloques sin color";
        }else if(p2>p1){
            winner="El jugador 2 ha ganado con "+p2+" bloques\n"+"El jugador 1 ha conseguido "+p1+" bloques\n"+"Hay "+no+" bloques sin color";
        }else if(p1==p2){
            winner="El jugador 1 y el jugador dos han empatado con "+p1+" bloques\n"+"Hay "+no+" bloques sin color";
        }
        return winner;
    }
}
