package com.conwey.game;

import java.io.Serializable;
import java.util.Arrays;

public class Game implements Serializable {

    private Integer id;
    private int width;
    private int height;
    private boolean[][] field;

    public Game() {
    }

    public Game(Integer id, int width, int height) {
        this.id = id;
        this.width = width;
        this.height = height;
    }

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

    public void setField(boolean[][] field) {
        this.field = field;
    }

    public void init() {
        if (field == null) {
            field = randomField();
        }
    }

    private boolean[][] randomField() {
        boolean[][] field = new boolean[width][height];
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                field[i][j] = Math.random() > 0.7;
            }
        }
        return field;
    }

    public Game generation(int generation) {
        Game clone = this.clone();
        boolean[][] newField = copy(field);
        for (int g = 0; g < generation; g++) {
            boolean[][] newGen = new boolean[width][height];
            for (int i = 0; i < width; i++) {
                for (int j = 0; j < height; j++) {
                    int neighbors = countNeighbors(newField, i, j);
                    if (newField[i][j]) {
                        newGen[i][j] = !(neighbors < 2 || neighbors > 3);
                    }
                    if (!newField[i][j] && neighbors == 3) {
                        newGen[i][j] = true;
                    }
                }
            }
            newField = copy(newGen);
        }
        clone.setField(newField);
        return clone;
    }

    private int countNeighbors(boolean[][] newField, int i, int j) {
        int result = 0;
        if (i > 0) {
            result += newField[i - 1][j] ? 1 : 0;
            if (j > 0) {
                result += newField[i - 1][j - 1] ? 1 : 0;
            }
            if (j + 1 < newField[i - 1].length) {
                result += newField[i - 1][j + 1] ? 1 : 0;
            }
        }
        if (i + 1 < newField.length) {
            result += newField[i + 1][j] ? 1 : 0;
            if (j > 0) {
                result += newField[i + 1][j - 1] ? 1 : 0;
            }
            if (j + 1 < newField[i + 1].length) {
                result += newField[i + 1][j + 1] ? 1 : 0;
            }
        }
        if (j > 0) {
            result += newField[i][j - 1] ? 1 : 0;
        }
        if (j + 1 < newField[i].length) {
            result += newField[i][j + 1] ? 1 : 0;
        }
        return result;
    }

    @Override
    protected Game clone() {
        return new Game(this.id, this.width, this.height);
    }

    private boolean[][] copy(boolean[][] toCopy) {
        boolean[][] result = new boolean[toCopy.length][];
        for (int i = 0; i < toCopy.length; i++) {
            result[i] = Arrays.copyOf(toCopy[i], toCopy[i].length);
        }
        return result;
    }
}
