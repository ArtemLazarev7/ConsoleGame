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

public class Main {

    private static int rows =10;

    private static int colums =15;

    private static int amountOfEnemies=15;

    private static int pointsNeeded=100;

    private static int moves=50;

    private static int amountOfCheckPoints=10;

    public static void main(String[] args) {
        Scanner scanner=new Scanner(System.in);
        String command;

        do{
            System.out.println("Welcome to BigJavaGame v 1.0. Please make your choice");
            System.out.println("1: Start new game");
            System.out.println("2: Options");
            System.out.println("3: Credits");
            System.out.println("4: Exit");

            command=scanner.nextLine();
            switch(command){
                case "1":
                    startNewGame();
                    break;
                case "2":
                    OptionsMenu.showOptionsMenu();
                    break;
                case "3":
                    showCredits();
                    break;
                case "4":
                    break;

                default:
                    System.out.println("Command not recognized! Please try again");

            }

        }while(!command.equals("4"));
    }

    private static void startNewGame() {
        Game game=new Game(rows,colums,amountOfEnemies,pointsNeeded,moves,amountOfCheckPoints);

        game.fillFieldWithEmptyObject();

        game.startGame();
    }

    private static void showCredits(){
        System.out.println("\nCreated by Artem Lazarev\nversion 1.0, last modifed on 05.09.2022.\n");

    }

    public static int getRows() {
        return rows;
    }

    public static void setRows(int rows) {
        Main.rows = rows;
    }

    public static int getColums() {
        return colums;
    }

    public static void setColums(int colums) {
        Main.colums = colums;
    }

    public static int getAmountOfEnemies() {
        return amountOfEnemies;
    }

    public static void setAmountOfEnemies(int amountOfEnemies) {
        Main.amountOfEnemies = amountOfEnemies;
    }

    public static int getPointsNeeded() {
        return pointsNeeded;
    }

    public static void setPointsNeeded(int pointsNeeded) {
        Main.pointsNeeded = pointsNeeded;
    }

    public static int getMoves() {
        return moves;
    }

    public static void setMoves(int moves) {
        Main.moves = moves;
    }

    public static int getAmountOfCheckPoints() {
        return amountOfCheckPoints;
    }

    public static void setAmountOfCheckPoints(int amountOfCheckPoints) {
        Main.amountOfCheckPoints = amountOfCheckPoints;
    }
    
}
