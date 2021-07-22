import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Game {
    static String[] GameArray = new String[9];
    static String turn = "X";

    //this method prints out the gae board
    static void PrintGrid() {
        System.out.println("\t|-----|" + "" +
                "\n\t|" + GameArray[0] + "|" + GameArray[1] + "|" + GameArray[2] + "|"
                //+"\n \t|-----|"
                + "\n\t|" + GameArray[3] + "|" + GameArray[4] + "|" + GameArray[5] + "|"
                // +"\n \t|-----|"
                + "\n\t|" + GameArray[6] + "|" + GameArray[7] + "|" + GameArray[8] + "|"
                + "\n \t|-----|");


    }

    static String GameWinner() {
        for (int a = 0; a < 8; a++) {
            String GameLine = null;
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
        }return null;
    }


    public static void main(String[] args) {

        Scanner scann = new Scanner(System.in);
        System.out.println(" first player Enter your name");
        String nameOfPlayer1 = scann.next();
        System.out.println(" second player Enter your name");
        String nameOfPlayer2 = scann.next();
        for (int a = 0; a < 9; a++) {
           GameArray[a] = String.valueOf(a + 1);
    }
        while (GameWinner() == null) {
            int play1;
            PrintGrid();

            try {
                String player;
                if (turn =="X"){
                    System.out.println("  enter number" + nameOfPlayer1 );
                }else {System.out.println(" enter number"+nameOfPlayer2 );}

                play1 = scann.nextInt();
                if (!(play1 > 0 && play1 <= 9)) {
                    System.out.println(
                            "Invalid input; re-enter slot number:");
                    continue;
                }
            } catch (InputMismatchException e) {
                System.out.println(
                        "Invalid input; re-enter slot number:");
                continue;
            }
            // this line makes decision for  players turn
            if (GameArray[play1 - 1].equals(
                    String.valueOf(play1))) {
                GameArray[play1 - 1] = turn;

                if (turn.equals("X")) {
                    turn = "O";

                } else {
                    turn = "X";
                }
                //PrintGrid();
                GameWinner();
            } else {
                   System.out.println(
                            "Slot already taken; re-enter slot number:");
              }
            // then here is the logic to print "draw".
            if (GameWinner()=="draw") {
                System.out.println(
                        "It's a draw! Thanks for playing.");
            }

            // For winner -to display Congratulations! message.
            else if(GameWinner()== "X" ){
                System.out.println(
                        "Congratulations! " + nameOfPlayer1
                                + "'s have won! Thanks for playing.");
            }else if(GameWinner()=="O"){
                System.out.println(
                        "Congratulations! " + nameOfPlayer2
                                + "'s have won! Thanks for playing.");
            }
        }
    }
}
