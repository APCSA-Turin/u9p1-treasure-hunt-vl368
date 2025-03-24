package com.example.project;

//Enemy only need constructor and getCoords() getRowCol()
public class Enemy  { //child  of Sprite
    
    public Enemy(int x, int y) {
    }


    //the methods below should override the super class 


    public String getCoords(){ //returns "Enemy:"+coordinates
        return "";
    }


    public String getRowCol(int size){ //return "Enemy:"+row col
    }
}