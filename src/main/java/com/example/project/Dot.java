package com.example.project;

//Dot only needs a constructor
public class Dot extends Sprite{
    // constructor
    public Dot(int x, int y) {
        super(x,y);
    }

    // overridden method to get emoji that represents dot on grid
    public String emoji() {
        return "⬜";
    }
}
