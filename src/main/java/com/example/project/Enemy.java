package com.example.project;

//Enemy only need constructor and getCoords() getRowCol()
public class Enemy extends Sprite { //child  of Sprite
    
    public Enemy(int x, int y) {
        super(x,y);
    }


    //the methods below should override the super class 

    @Override
    // returns "Enemy:" + coordinates
    // uses the parent class method in overriden method
    public String getCoords(){ 
        return "Enemy:" + super.getCoords();
    }

    @Override
    // returns "Enemy:" + row col
    // uses the parent class method in overriden method
    public String getRowCol(int size){ 
        return "Enemy:" + super.getRowCol(size);
    }

    @Override
    // overridden method to get emoji that represents enemy on grid
    public String emoji() {
        return "🐗";
    }

    // interact with an object in the position you are moving to 
    // numTreasures is the total treasures at the beginning of the game
    public void interact(String direction, Object obj) { 
        // checks for what class the object in new position is
        if (obj instanceof Player) {
            // if a player, and enemy is moving to a player's new location
            // player will lose a life 
            Player player = (Player) obj;
            player.loseLife();
        }
    }

    // move based on the player's movements
    public String move(Sprite player, int size) {
        // stores player coords
        int playerX = player.getX();
        int playerY = player.getY();
        // stores differences between enemy coords and player coords
        int xDiff = playerX - getX();
        int yDiff = playerY - getY();
        // incr is the increment/decrement of either x or y coords
        int incr = 0;
        // if the diff in x is greater than the diff in y
        if (Math.abs(xDiff) > Math.abs(yDiff)){
            // x will change
            // if diff is 0, no movement happens
            if (xDiff == 0) {
                return "";
            }
            // incr can only be -1 or 1 thus dividing by the 
            // abs value keeps the sign while making it 1
            incr = (xDiff)/Math.abs(xDiff);
            if (incr > 0) {
                // if incr is greater than 0 and the move is valid
                if (isValid(size, "d")) {
                    // move is made and right movement is returned
                    incrementX(incr);
                    return "d";
                }
            }
            else {
                // else less than or equal to 0 and the move is valid
                if (isValid(size, "a")) {
                    // move is made and left movement is returned 
                    incrementX(incr);
                    return "a";
                }
            }
        }
        else {
            // otherwise y will change
            // if diff is 0, no movement happens
            if (yDiff == 0) {
                return "";
            }
            // incr can only be -1 or 1 thus dividing by the 
            // abs value keeps the sign while making it 1
            incr = (yDiff)/Math.abs(yDiff);
            if (incr > 0) {
                // if incr is greater than 0 and the move is valid
                if (isValid(size, "w")) {
                    // move is made and forward movement is returned
                    incrementY(incr);
                    return "w";
                }
            }
            else {
                // if incr is greater than 0 and the move is valid
                if (isValid(size, "s")) {
                    // move is made and down movement is returned
                    incrementY(incr);
                    return "s";
                }
            }
        }
        // returned in the case of no movements
        return "";
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
}