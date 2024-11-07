package com.unisim.game;

public class Building {
    private String filepath;
    private int size;

    public Building(String filepath, int size) {
        this.filepath = filepath;
        if (size == 2 || size == 3) this.size = size;
        else this.size = 2;
    }

    public String getName() {
        return filepath;
    }

    public int getSize() {
        return size;
    }

    public Building deepCopy() {
        return new Building(getName(), getSize());
    }
}
