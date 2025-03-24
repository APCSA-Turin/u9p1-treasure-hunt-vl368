package com.example.project;

public class Sprite {
    private int x, y;

    public Sprite(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX(){return 1;}//placeholder
    public int getY(){return 1;}

    public void setX(){}
    public void setY(){}

    public String getCoords(){ //returns the coordinates of the sprite ->"(x,y)"
        return "";
    }

    public String getRowCol(int size){ //returns the row and column of the sprite -> "[row][col]"
        return "[][]";
    }
    

    public void move(String direction) { //you can leave this empty
        // Default behavior (can be overridden by subclasses)
    }

    public void interact() { //you can leave this empty
        // Default behavior (can be overridden by subclasses)
    }



}
