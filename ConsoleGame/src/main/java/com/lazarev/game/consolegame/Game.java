/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.lazarev.game.consolegame;

/**
 *
 * @author ArtemLazarev
 */
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Game {

    private  int rows;
    private int columns;
    private int amountOfEnemies;
    private int pointsNeeded;
    private int pointsGathered;
    private int turnsLeft;
    private Field field;
    private boolean isGameFinished=false;
    private int amountOfFlowers;
    private List<CheckPoint> checkPointList=new ArrayList<>();
    private List<Enemy> enemyList=new ArrayList<>();
    private Random randomNumber=new Random();
    private Player player;
    private Scanner scanner=new Scanner(System.in);
    private boolean isIncorrectCommand=true;
    private int triesToRegenerate=10;

    public Game(int rows, int columns, int amountOfEnemies, int transistorsNeeded,
                int movesLeft,int amountOfFlowers) {
        this.rows = rows;
        this.columns = columns;
        this.amountOfEnemies = amountOfEnemies;
        this.pointsNeeded = transistorsNeeded;
        this.turnsLeft = movesLeft;
        this.amountOfFlowers=amountOfFlowers;
        field=new Field(rows,columns);
    }

    public void fillFieldWithEmptyObject(){
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                field.setFieldable(i,j,new Empty());
            }
        }
    }
    public void startGame(){
        possesPlayer();
        possesFlowers();
        possesEnemies();

        while(!isGameFinished){

            showField();
            playerTurn();
            if(isIncorrectCommand){
                inCorrectCommand();
                continue;
            }
            computerTurn();
            checkIfGameNotFinished();

        }
    }

    private void inCorrectCommand() {
        System.out.println("You have entered an incorrect command, please " +
                "verify try again!");
    }

    private void possesPlayer() {
        int playerRowPosition=randomNumber.nextInt(rows);
        int playerColumnPosition=randomNumber.nextInt(columns);
        player=new Player(playerRowPosition,playerColumnPosition,this);

    }

    private void possesEnemies() {
        generateEnemies();
    }

    private void possesFlowers() {
        generateFlowers();
    }

    private void playerTurn() {
        System.out.println("Please enter your command and press Enter:");
        String command=scanner.nextLine();
        isIncorrectCommand=player.makeMove(command);
    }

    private void showField() {
        System.out.println("\n\nTurns left: "+turnsLeft+
                ", points gathered: "+pointsGathered+"/"+pointsNeeded);
        field.showField();
    }

    private void checkIfGameNotFinished() {
        if(turnsLeft==0){
            System.out.println("No more turns left, you lost!");
            isGameFinished=true;
        }
        if(pointsGathered>=100){
            System.out.println("You have gathered the required " +
                    "number of points,you won");
            isGameFinished=true;
        }
    }

    private void computerTurn() {
        enemyMove();
        generateFlowers();
        turnsLeft--;
    }
    
    private void generateEnemies(){
        for (int i = amountOfEnemies-enemyList.size(); i >0;) {

            int enemyRowPosition=randomNumber.nextInt(rows);
            int enemyColumnPosition=randomNumber.nextInt(columns);

            if(field.getFieldable(enemyRowPosition,enemyColumnPosition)instanceof Empty){
                Enemy enemy=new Enemy(enemyRowPosition,enemyColumnPosition);
                field.setFieldable(enemyRowPosition,enemyColumnPosition,enemy);
                enemyList.add(enemy);
                i--;
            }

        }
    }


    private void generateFlowers(){
        for (int i = amountOfFlowers-checkPointList.size(); i >0;) {

          int  flowerAmountOfTransition=randomNumber.nextInt(9)+1;
          int flowerRowPosition=randomNumber.nextInt(rows);
          int flowerColumnPosition=randomNumber.nextInt(columns);

//          if(field.getFieldable(flowerRowPosition,flowerColumnPosition)instanceof Player){
//              pointsGathered+=flowerAmountOfTransition;
//              i--;
//          }
            if(field.getFieldable(flowerRowPosition,flowerColumnPosition)instanceof Empty){
                CheckPoint flower=new CheckPoint(flowerAmountOfTransition,flowerRowPosition,flowerColumnPosition);
                field.setFieldable(flowerRowPosition,flowerColumnPosition,flower);
                checkPointList.add(flower);
                i--;
            }
        }
    }

    public Field getField() {
        return field;
    }

    public List<CheckPoint> getCheckPointList() {
        return checkPointList;
    }

    public void setPointsGathered(int transistorsToAdd){
        this.pointsGathered+=transistorsToAdd;
    }

    private void enemyMove() {

        int rowIndex = 0;
        int columnIndex = 0;
        int newRowIndex = 0;
        int newColumnIndex = 0;
        int regenerateIndex = 0;
        boolean isNeededToRegenerate = true;

        for (Enemy enemy : enemyList) {

            rowIndex=enemy.getRowIndex();
            columnIndex=enemy.getColumnIndex();

            do {
               int  deltaRow=randomNumber.nextInt(3)-1;
                int  deltaColumn=randomNumber.nextInt(3)-1;

                newRowIndex=rowIndex+deltaRow;
                newColumnIndex=columnIndex+deltaColumn;

                if((newRowIndex<0)||(newColumnIndex<0)||(newRowIndex>=field.getRows())||
                        (newColumnIndex>=field.getColumns())||field.getFieldable(newRowIndex,newColumnIndex)
                        instanceof Player||
                field.getFieldable(newRowIndex,newColumnIndex) instanceof Enemy){
                    regenerateIndex ++;
                    isNeededToRegenerate=true;
                }
                else{
                    if(field.getFieldable(newRowIndex,newColumnIndex) instanceof CheckPoint){
                        CheckPoint flower=(CheckPoint) field.getFieldable(newRowIndex,newColumnIndex);
                        checkPointList.remove(flower);

                        isNeededToRegenerate=swapEnemy(rowIndex,columnIndex,newRowIndex,newColumnIndex,enemy);
                    }
                    else {

                        isNeededToRegenerate=swapEnemy(rowIndex,columnIndex,newRowIndex,newColumnIndex,enemy);

                    }
                }
            } while (isNeededToRegenerate && regenerateIndex <= triesToRegenerate);
        }
    }
    private boolean swapEnemy(int rowIndex,int columnIndex,int newRowIndex,int newColumnIndex,Enemy enemy){
        field.setFieldable(newRowIndex,newColumnIndex,enemy);
        field.setFieldable(rowIndex,columnIndex,new Empty());
        enemy.setRowIndex(newRowIndex);
        enemy.setColumnIndex(newColumnIndex);
         return false;
    }
}
