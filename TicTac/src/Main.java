import java.util.*;
public class Main {
    static void PrintGrid(){
        System.out.println("\t|-----------|"+"\n\t|   |   |   |"
                            +"\n \t|-----------|" +"\n\t|   |   |   |"
                            +"\n \t|-----------|" +"\n\t|   |   |   |" +"\n\t|-----------|");

    }
    // GameWinner methode will check and decide the  of the game
    static  String[] GameArray;
    static String player = "X";

    static String GameWinner() {
        for (int a = 0; a < 8; a++) {
            String GameLine = "j";
            switch (a) {
                case 0:
                    GameLine = GameArray[0] + GameArray[1] + GameArray[2];
                    break;
                case 1:
                    GameLine = GameArray[3] + GameArray[4] + GameArray[5];
                    break;
                case 2:
                    GameLine = GameArray[6] + GameArray[7] + GameArray[8];
                    break;
                case 3:
                    GameLine = GameArray[0] + GameArray[3] + GameArray[6];
                    break;
                case 4:
                    GameLine = GameArray[1] + GameArray[4] + GameArray[7];
                    break;
                case 5:
                    GameLine = GameArray[2] + GameArray[5] + GameArray[8];
                    break;
                case 6:
                    GameLine = GameArray[0] + GameArray[4] + GameArray[8];
                    break;
                case 7:
                    GameLine = GameArray[2] + GameArray[4] + GameArray[8];
                    break;
            }
            // check for "X" winner
            if (GameLine.equals("XXX")) {
                return "X";
            } else if (GameLine.equals("OOO")) {
                return "O";
            }
        }

        for (int a = 0; a < 9; a++) {
            if (Arrays.asList(GameArray).contains(
                    String.valueOf(a + 1))) {
                break;
            }
            else if (a == 8) {
                return "draw";
            }
             }
        System.out.println( player+"'s turn to play, pls Enter the slot to place X in");
        return "X";

    }

    static void PrintGridB(){
        System.out.println("\t|-----------|"+"" +
                "\n\t|"+GameArray[0]+ "|"+ GameArray[1] +"|" +GameArray[2]+ "|"
                +"\n \t|-----------|"
                +"\n\t|"+GameArray[3]+ "|"+ GameArray[4] +"|" +GameArray[5]+ "|"
                +"\n \t|-----------|"
                +"\n\t|"+GameArray[6]+ "|"+ GameArray[7] +"|" +GameArray[8]+ "|");

    }




    public static void main(String[] args){
        for (int a = 0; a < 9; a++) {
            GameArray[a] = String.valueOf(a + 1);
        }
        for (int i = 0; i<9; i++){
            GameArray[i] = String.valueOf(i+1);
        }
        PrintGrid();
        PrintGridB();

        System.out.println(GameWinner()) ;
    }

}
