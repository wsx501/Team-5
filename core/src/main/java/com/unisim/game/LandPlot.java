package com.unisim.game;

import java.awt.*;
import java.awt.event.ActionListener;

public class LandPlot extends Button {
    private Building buildingPlaced;
    private int maxSize;
    private int x, y, width, height;
    private boolean occupied;

    public LandPlot(int maxSize, int x, int y, int width, int height) {
        this.maxSize = maxSize;
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        buildingPlaced = null;
        occupied = false;
    }

    public void setBuilding(Building buildingPlaced) {
        this.buildingPlaced = buildingPlaced;
    }

    public void setOccupied() {
        occupied = true;
    }

    public boolean isOccupied() {
        return occupied;
    }

//    public int getMaxSize() {
//        return maxSize;
//    }
//
//    public int getX() {
//        return x;
//    }
//
//    public int getY() {
//        return y;
//    }
//
//    public int getWidth() {
//        return width;
//    }
//
//    public int getHeight() {
//        return height;
//    }


}
