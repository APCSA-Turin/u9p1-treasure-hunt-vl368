package com.example.project;

//DO NOT DELETE ANY METHODS BELOW
public class Grid {
    // instance variables
    private Sprite[][] grid;
    private int size;

    // constructor
    // initializes and creates a grid with all DOT objects
    public Grid(int size) { 
        this.size = size;
        // initalizes grid
        grid = new Sprite[size][size];
        // places a dot in every grid space
        for (int row = 0; row < size; row++) {
            for (int col = 0; col < size; col++) {
                grid[row][col] = new Dot(size - 1 - row, col);
            }
        }
    }

    // getter method
    public Sprite[][] getGrid(){return grid;}

    // places the sprite according to its x, y coords
    public void placeSprite(Sprite s){ 
        grid[s.getRow(size)][s.getCol()] = s;
    }

    // place sprite in a new spot based on direction
    public Object placeSprite(Sprite s, String direction) { 
        // current stores the new location of sprite
        int x = s.getX();
        int y = s.getY();
        // change row, col variables to match old location
        switch (direction) { // takes the argument of the direction
            // checks which case (which direction)
            case "w": // fowards movement
                y--; // go forward one row
                break;
            case "a": // left movement
                x++; // go back one col
                break;
            case "s": // down movement
                y++; // go back one row
                break;
            case "d": // right movement
                x--; // go forward one col
                break;
        }
        // stores object in new location of sprite
        Object obj = grid[s.getRow(size)][s.getCol()];
        // moves sprite to new location
        placeSprite(s);
        // places dot in old location
        placeSprite(new Dot(x,y));
        return obj;
    }


    public void display() { //print out the current grid to the screen 
        for (Sprite[] row : grid) {
            for (Sprite sprite : row) {
                System.out.print(sprite.emoji());
            }
            System.out.println();
        }
    }
    
    public void gameover() { //use this method to display a loss
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                // all grid spaces except the one with player 
                // will print a skull
                if (!(grid[i][j] instanceof Player)) {
                    System.out.print("💀");
                }
                else {
                    // player's space will print unicorn
                    System.out.print("🦄");
                }
            }
            System.out.println();
        }
    }

    public void win(){ //use this method to display a win 
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                // every grid space except the player's
                // will be replaced with a treasure object
                if (!(grid[i][j] instanceof Player)) {
                    grid[i][j] = new Treasure(size - 1 - i, j);
                }
            }
        }
        // displays changed grid which will be filled with treasure
        display();
    }


}