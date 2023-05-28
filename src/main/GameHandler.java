package main;

import main.Player;
import main.map.GameMap;
import main.tools.Tool;
import main.tools.ToolType;
import main.units.UnitHandler;


import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class GameHandler {
    public GameMap gm;
    public Player player;
    private static Scanner scanner = new Scanner(System.in);
    public UnitHandler unitHandler;

    public GameHandler() {


    }
    public String loopUntilMultipleAnswers(String request , List<String> answers){
        System.out.println(request);
        Scanner scanner = this.scanner;


        String input = scanner.nextLine();
        while (true){
            if (answers.contains(input)){
                break;
            }
            System.out.println("Invalid input, try again");
            input = scanner.nextLine();
        }


        return input;
    }
    public String loopUntilAnswer(String request , String answer){
        System.out.println(request);

        Scanner scanner = this.scanner;
        String input = scanner.nextLine();
        while (true){
            if (answer.equals(input)){
                break;
            }
            System.out.println("Invalid input, try again");
            input = scanner.nextLine();
        }




        return input;
    }

    public void welcome(){
        this.loopUntilAnswer("Welcome to this strategy game, type 1 to continue","1");
        System.out.println("Initializing...");
        //sleep for 1 second
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


    }

    public void requestAction(){
        Scanner scanner = new Scanner(System.in);
        ArrayList<String> answers = new ArrayList<>();
        for(int i = 0; i < 5; i++){
            answers.add(Integer.toString(i+1));
        }
        String input = this.loopUntilMultipleAnswers("What do you want to do?\n1. Create\n2. Destroy(NOT WORKING)\n3. Group(NOT WORKING)\n4. Display\n5. Run turn",answers);

        switch (input){
            case "1":
                create_choice();
                break;
            case "2":
                System.out.println("Destroying");
                requestAction();
                break;
            case "3":
                System.out.println("Grouping");
                requestAction();
                break;
            case "4":
                display_choice();
                requestAction();
                break;
            case "5":
                System.out.println("Running turn");
                break;
        }
    }
    public void create_choice(){
        // choices are Unit , Unit Factory ,Tool Factory
        ArrayList<String> answers = new ArrayList<>();
        for(int i = 0; i < 3; i++){
            answers.add(Integer.toString(i+1));
        }
        String input = this.loopUntilMultipleAnswers("What do you want to create?\n1. Unit\n2. Unit Factory\n3. Tool Factory",answers);
        switch (input){
            case "1":
                create_unit_choice();
                break;
            case "2":
                create_unit_factory();

                break;
            case "3":
                create_tool_factory();

                break;
        }


    }
    public void create_unit_choice(){
        ArrayList<String> answers = new ArrayList<>();
        String question = "What unit do you want to create?\n";
        for (int i = 0; i < ToolType.values().length; i++){
            answers.add(Integer.toString(i+1));
            question += (i+1)+". "+ ToolType.values()[i].getJobname()+"\n";
        }

        String input = this.loopUntilMultipleAnswers(question,answers);
        int toolindex = Integer.parseInt(input)-1;
        create_unit(ToolType.values()[toolindex]);
    }
    public void create_unit_factory(){
        System.out.println("Creating unit factory");
    }
    public void create_tool_factory(){
        System.out.println("Creating tool factory");
    }
    public void create_unit(ToolType toolType){
        //check if player has enough resources
        for ( Resource resource : Resource.values()){
            if (this.player.Inventory.get(resource) < resource.unitcost){
                System.out.println("Not enough "+resource.toString());
                return;
            }
        }
        //remove resources
        for ( Resource resource : Resource.values()){
            this.player.Inventory.put(resource,this.player.Inventory.get(resource)-resource.unitcost);
        }
        //create unit

        Tool tool = new Tool(toolType,Tool.getlowestlevel(toolType)+1);
        this.unitHandler.createUnit(this.gm,tool);

    }

    public void clearConsole(){
        for (int i = 0; i < 50; i++) {
            System.out.println();
        }
    }
    public void startGame(){
        this.gm = new GameMap(10,10);
        this.gm.generate();
        this.player = new Player();
        this.unitHandler = new UnitHandler(this.player,this.gm);
        this.player.printstats();
    }
    public void display_choice(){
        ArrayList<String> answers = new ArrayList<>();
        for(int i = 0; i < 2; i++){
            answers.add(Integer.toString(i+1));
        }
        String input = this.loopUntilMultipleAnswers("What do you want to display?\n1. Map\n2. Inventory\n3. Units",answers);
        switch (input){
            case "1":
                this.printMap();
                break;
            case "2":
                this.player.printstats();
                break;

        }
    }
    public boolean noMoreFood(){
        return this.player.Inventory.get(Resource.FOOD) <= 0;
    }
    public void printMap(){
        this.gm.printMap();
    }



}
