package com.example.project;

//Enemy only need constructor and getCoords() getRowCol()
public class Enemy extends Sprite { //child  of Sprite
    
    public Enemy(int x, int y) {
        super(x,y);
    }


    //the methods below should override the super class 


    // returns "Enemy:" + coordinates
    // uses the parent class method in overriden method
    public String getCoords(){ 
        return "Enemy:" + super.getCoords();
    }

    // returns "Enemy:" + row col
    // uses the parent class method in overriden method
    public String getRowCol(int size){ 
        return "Enemy:" + super.getRowCol(size);
    }

    // overridden method to get emoji that represents enemy on grid
    public String emoji() {
        return "🐗";
    }
}