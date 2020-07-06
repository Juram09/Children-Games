package games.multiplicator.logic;

import games.multiplicator.data.Board;
import games.multiplicator.data.Player;
import games.multiplicator.data.Square;

public class TurnController {
    public static Board playTurn(Board board, Player player,MultiplicatorController board2) {
        String dados;
        boolean validMovenment = false;
        String movenment="";
        while (!validMovenment) {
            if (player.isHumanPlayer()) {
                dados = lanzarDados();
                board2.lastPlayer(dados);
            } else {
                dados = lanzarDados();
                board2.lastPc(dados);
            }
            board2.setDados(dados);
            movenment = MovementHandler.isValid(board, player, dados);
            if (movenment.equals("0")) {
                board2.printError(dados);
            } else {
                markBoard(board, movenment, player,dados,board2);    
                board2.noError(dados);
            }
            validMovenment=true;
        }
        return board;
    }

    private static Board markBoard(Board board, String movenment, Player player,String dados, MultiplicatorController board2) {
        int dado1=Character.getNumericValue(dados.charAt(0));
        int dado2=Character.getNumericValue(dados.charAt(2));
        String[] array = movenment.split("-");
        if(player.isHumanPlayer()){
            if(movenment.charAt(0)=='1'){
                for(int i=Integer.parseInt(array[1]);i<Integer.parseInt(array[1])+dado1;i++){
                    for(int j=Integer.parseInt(array[2]);j<Integer.parseInt(array[2])+dado2;j++){
                        Square square = new Square(i,j, player.getPlayer().charAt(0));
                        board.updateSquare(square);
                        board2.update(i,j,player);
                        
                    }
                }
            }else{
                for(int i=Integer.parseInt(array[1]);i<Integer.parseInt(array[1])+dado2;i++){
                    for(int j=Integer.parseInt(array[2]);j<Integer.parseInt(array[2])+dado1;j++){
                        Square square = new Square(i,j, player.getPlayer().charAt(0));
                        board.updateSquare(square);
                        board2.update(i,j,player);
                    }
                }
            }
        }else{
            if(movenment.charAt(0)=='1'){
                for(int i=Integer.parseInt(array[1]);i>Integer.parseInt(array[1])-dado1;i--){
                    for(int j=Integer.parseInt(array[2]);j>Integer.parseInt(array[2])-dado2;j--){
                        Square square = new Square(i,j, player.getPlayer().charAt(0));
                        board.updateSquare(square);
                        board2.update(i,j,player);
                    }
                }
            }else{
                for(int i=Integer.parseInt(array[1]);i>Integer.parseInt(array[1])-dado2;i--){
                    for(int j=Integer.parseInt(array[2]);j>Integer.parseInt(array[2])-dado1;j--){
                        Square square = new Square(i,j, player.getPlayer().charAt(0));
                        board.updateSquare(square);
                        board2.update(i,j,player);
                    }
                }
            }
        }
        return board;
    }

    public static String lanzarDados() {
        int dado1 = (int) Math.floor(Math.random()*6+1);
        int dado2 = (int) Math.floor(Math.random()*6+1);
        return dado1+"-"+dado2;
        
    }
}

