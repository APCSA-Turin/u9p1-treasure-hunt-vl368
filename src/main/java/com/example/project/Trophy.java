package com.example.project;

//only needs a constructor
public class Trophy extends Treasure { //child of trophy
    public Trophy(int x, int y){
        super(x,y);
    }

    // overridden method to get emoji that represents trophy on grid
    public String emoji() {
        return "🏆";
    }
}
