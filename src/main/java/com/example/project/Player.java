package com.example.project;

//DO NOT DELETE ANY METHODS BELOW
public class Player extends Sprite {
    // instance variables
    private int treasureCount;
    private int numLives;
    private boolean win;

    // constructor
    // sets the starting lives + treasures
    public Player(int x, int y) {
        super(x,y); 
        treasureCount = 0;
        numLives = 2;
    }

    // getter methods
    public int getTreasureCount(){return treasureCount;}
    public int getLives(){return numLives;}
    public boolean getWin(){return win;}

    // returns the coordinates of the sprite ->"Player:(x,y)"
    public String getCoords(){ 
        return "Player:" + super.getCoords();
    }

    // returns the row and column of the player -> "Player:[row][col]"
    public String getRowCol(int size){ 
        return "Player:" + super.getRowCol(size);
    }

    // decrements lives counter
    public void loseLife() {
        numLives--;
    }

    @ Override
    // moves the (x,y) coordinates of the player
    public void move(String direction) { 
        switch (direction) { // takes the argument of the direction
            // checks which case (which direction)
            case "w": // fowards movement
                incrementY(1); // go forward one row
                break;
            case "a": // left movement
                incrementX(-1); // go back one col
                break;
            case "s": // down movement
                incrementY(-1); // go back one row
                break;
            case "d": // right movement
                incrementX(1); // go forward one col
                break;
        }
    }

    // interact with an object in the position you are moving to 
    // numTreasures is the total treasures at the beginning of the game
    public void interact(int size, String direction, int numTreasures, Object obj) { 
        // checks for what class the object in new position is
        if (obj instanceof Trophy) {
            // if a trophy and player collected all treasures, they win
            if (numTreasures == treasureCount) {
                win = true;
            }
        }
        else if (obj instanceof Treasure) {
            // if a treasure, the player collects the treasure
            treasureCount++;
        }
        else if (obj instanceof Enemy) {
            // if an enemy, they lose a life
            numLives--;
        }
    }

    // check grid boundaries
    // returns true if within bounds, false otherwise
    public boolean isValid(int size, String direction){ 
        // initalizes variable to store next row (col) indexes
        int row = -1;
        int col = -1;
        switch (direction) { // takes the argument of the direction
            // checks which case (which direction)
            case "w": // fowards movement
                row = getRow(size) - 1; // stores next row index
                return row >= 0 && row < size; // returns if next row index is within bounds
            case "a": // left movement
                col = getCol() - 1; // stores next col index
                return col >= 0 && col < size; // returns if next col index is within bounds
            case "s": // down movement
                row = getRow(size) + 1; // stores next row index
                return row >= 0 && row < size; // returns if next row index is within bounds
            case "d": // right movement
                col = getCol() + 1; // stores next col index
            return col >= 0 && col < size; // returns if next col index is within bounds
        }
        return false;
    }

    // overridden method to get emoji that represents player on grid
    public String emoji() {
        return "🦄";
    }
}



