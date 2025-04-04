package com.example.project;

//only needs a constructor
public class Treasure extends Sprite { //child of Sprite
    public Treasure(int x, int y) {
        super(x,y);
    }

    // overridden method to get emoji that represents treasure on grid
    public String emoji() {
        return "🌈";
    }
}