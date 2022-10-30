/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.lazarev.game.consolegame;

/**
 *
 * @author ArtemLazarev
 */
import java.util.Objects;

public class CheckPoint implements Fieldable{

    private int points;
    private int rowIndex;
    private int columnIndex;

    public CheckPoint(int points,int rowIndex,int columnIndex) {
        this.points = points;
        this.rowIndex=rowIndex;
        this.columnIndex=columnIndex;
    }

    @Override
    public String getSymbol() {
        return String.valueOf(" "+points+" ");
    }

    public int getPoints() {
        return points;
    }

    public int getRowIndex() {
        return rowIndex;
    }

    public void setRowIndex(int rowIndex) {
        this.rowIndex = rowIndex;
    }

    public int getColumnIndex() {
        return columnIndex;
    }

    public void setColumnIndex(int columnIndex) {
        this.columnIndex = columnIndex;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CheckPoint flower = (CheckPoint) o;
        return rowIndex == flower.rowIndex && columnIndex == flower.columnIndex;
    }

    @Override
    public int hashCode() {
        return Objects.hash(rowIndex, columnIndex);
    }
}
