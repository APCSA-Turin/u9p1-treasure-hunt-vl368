package com.example.project;

public class Sprite {
    // instance variables
    private int x, y;

    // constructor
    public Sprite(int x, int y) {
        this.x = x;
        this.y = y;
    }

    // getter methods
    public int getX(){return x;}
    public int getY(){return y;}

    // setter methods
    public void setX(int newX){x = newX;}
    public void setY(int newY){y = newY;}

    // getter methods pt. 2
    //returns the coordinates of the sprite ->"(x,y)"
    public String getCoords(){ 
        return "(" + x + "," + y + ")";
    }

    // returns the row and column of the sprite -> "[row][col]"
    public String getRowCol(int size){ 
        return "[" + getRow(size) + "][" + x + "]";
    }

    // row = size - 1 bc last index is one less than size
    // row -= y because y is the index of how far sprite is 
    // into the rows
    public int getRow(int size) {return (size - 1 - y);}
    public int getCol() {return x;}

    // public methods
    public void incrementX(int incr) {x += incr;}
    public void incrementY(int incr) {y += incr;}
    
    public void move(String direction) { //you can leave this empty
        // Default behavior (can be overridden by subclasses)
    }

    public void interact() { //you can leave this empty
        // Default behavior (can be overridden by subclasses)
    }

    // to be overridden method to get emoji that represents sprite on grid
    public String emoji() {
        return "";
    }
}
