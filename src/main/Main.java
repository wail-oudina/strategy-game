package main;

import java.util.Scanner;


public class Main {

    public static void main(String[] args) {

        GameHandler gameHandler = new GameHandler();
        gameHandler.welcome();
        gameHandler.startGame();
        gameHandler.clearConsole();

        int turn = 0;
        while ( !gameHandler.gm.ResourcesDepleted() ){
            System.out.println("Turn number : "+turn);
            gameHandler.requestAction();
            gameHandler.unitHandler.RunUnits();
            if ( gameHandler.noMoreFood()){
                System.out.println("You ran out of food, you lose");
                break;}
            turn++;

        }

        /*




        while ( true ){
            System.out.println("Press enter to continue");
            scanner.nextLine();
            unitHandler.RunUnits();
            gm.printMap();
            player.printstats();
        }*/










    }

}