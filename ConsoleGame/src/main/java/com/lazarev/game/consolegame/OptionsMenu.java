/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.lazarev.game.consolegame;

/**
 *
 * @author ArtemLazarev
 */
import java.util.Scanner;

public class OptionsMenu {
    static Scanner  scanner=new Scanner(System.in);
    static int command;
    public static void showOptionsMenu(){
        do{
            System.out.println("Select make your choice and press Enter\n" +
                    "1: Show current settings\n" +
                    "2: Change settings\n" +
                    "3: Reset settings\n" +
                    "4: Control\n"+
                    "5: Exit");

            command=scanner.nextInt();

            switch (command){
                case 1:
                    System.out.println("\nCurrent settings:" +
                            "\nrows: "+Main.getRows() +
                            "\ncolumns: "+Main.getColums() +
                            "\nenemies: "+Main.getAmountOfEnemies()+
                            "\ntransistors: "+Main.getPointsNeeded()+
                            "\nmovies: "+Main.getMoves()+
                            "\ncheckpoints: "+Main.getAmountOfCheckPoints());

                    break;
                case 2:
                    String value;

                    System.out.println("Enter a new value for rows, leave blank to keep current value ["+Main.getRows()+"]:");
                    scanner.nextLine();
                    value=scanner.nextLine();
                    if(!value.isBlank()){
                        Main.setRows(Integer.parseInt(value));
                    }

                    System.out.println("Enter a new value for columns, leave blank to keep current value ["+Main.getColums()+"]:");
                    value=scanner.nextLine();
                    if(!value.isBlank()){
                        Main.setColums(Integer.parseInt(value));
                    }

                    System.out.println("Enter a new value for enemies, leave blank to keep current value ["+Main.getAmountOfEnemies()+"]:");
                    value=scanner.nextLine();
                    if(!value.isBlank()){
                        Main.setAmountOfEnemies(Integer.parseInt(value));
                    }

                    System.out.println("Enter a new value for points, leave blank to keep current value ["+Main.getPointsNeeded()+"]:");
                    value=scanner.nextLine();
                    if(!value.isBlank()){
                        Main.setPointsNeeded(Integer.parseInt(value));
                    }

                    System.out.println("Enter a new value for moves, leave blank to keep current value ["+Main.getMoves()+"]:");
                    value=scanner.nextLine();
                    if(!value.isBlank()){
                        Main.setMoves(Integer.parseInt(value));
                    }

                    System.out.println("Enter a new value for checkpoints, leave blank to keep current value ["+Main.getAmountOfCheckPoints()+"]:");
                    value=scanner.nextLine();
                    if(!value.isBlank()){
                        Main.setAmountOfCheckPoints(Integer.parseInt(value));
                    }
                    if(!isValuePlayable()){
                        System.out.println("Values you entered are not playable, please verify and try again!");
                        System.out.println("Settings changed to default.");
                        defaultSettings();
                    }
                        break;
                        
                case 3:{
                    defaultSettings();
                    System.out.println("Settings changed to default.");
                    break;
                }
                case 4:{
                    System.out.println("\nControl:"
                            + "\n Click 'w' -> Move up."
                            + "\n Click 's' -> Move down."
                            + "\n Click 'a' -> Move left."
                            + "\n Click 'd' -> Move right."
                            + "\n Click 'z' -> Skip a turn.\n");
                    break;
                }
                
               


                case 5:
                    break;
                default:
                    System.out.println("Command not recognized, please try again");
                    break;
            }
        }
        while(command!=5);
    }

    private static boolean isValuePlayable(){
        int fieldSize=Main.getRows()* Main.getColums();
        int allGameObject=Main.getAmountOfEnemies()+Main.getAmountOfCheckPoints()+1;
        if(allGameObject/fieldSize<0.50)
        return true;
        else
            return false;
    }
    
    private static void defaultSettings(){
        Main.setRows(10);
        Main.setColums(15);
        Main.setAmountOfEnemies(15);
        Main.setPointsNeeded(100);
        Main.setAmountOfCheckPoints(10);
        Main.setMoves(50);
    }
    
}
