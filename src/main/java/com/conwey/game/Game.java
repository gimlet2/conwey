package com.conwey.game;

import java.io.Serializable;

public class Game implements Serializable {

    private Integer id;
    private int width;
    private int height;
    private boolean[][] field;
    private int generation;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public boolean[][] getField() {
        return field;
    }

    public void init() {
        field = new boolean[width][height];
    }

    public void nextGeneration() {
        
    }

}
