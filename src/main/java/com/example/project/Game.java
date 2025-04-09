package com.example.project;
import java.util.Scanner;

public class Game{
    private Grid grid;
    private Player player;
    private Enemy[] enemies;
    private Treasure[] treasures;
    private Trophy trophy;
    private int size; 

    public Game(int size){ //the constructor should call initialize() and play()
        this.size = size;
        initialize();
        play();
    }

    public static void clearScreen() { //do not modify
        try {
            final String os = System.getProperty("os.name").toLowerCase();
            if (os.contains("win")) {
                // Windows
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            } else {
                // Unix-based (Linux, macOS)
                System.out.print("\033[H\033[2J");
                System.out.flush();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void play(){ //write your game logic here
        Scanner scanner = new Scanner(System.in);
        String input = "";

        while(!input.equals("q") &&  !player.getWin() &&  player.getLives() > 0){
            try {
                Thread.sleep(100); // Wait for 1/10 seconds
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            clearScreen(); // Clear the screen at the beggining of the while loop



            // actual game code
            // prints out grid
            grid.display();

            // text under grid 
            System.out.println(player.getCoords());
            System.out.println(player.getRowCol(size));
            System.out.println("Treasure Collected:" + (treasures.length-player.getTreasureCount()));
            System.out.println("Lives remaining:" + player.getLives());
            System.out.print("Enter a direction (w,a,s,d) or 'q' to exit: ");
            // get player input
            input = scanner.nextLine();



            // if the move is valid and they didn't quit, player is moved and interacts
            if (player.isValid(size, input) && input != "q") {
                // moves their coords to new location
                player.move(input);
                // places player in the new location 
                // stores the object replaced
                Object obj = grid.placeSprite(player, input);
                // determines the interaction and action
                // between player and object
                player.interact(size, input, treasures.length, obj);
                // if interacted object is a treasure, it is 
                // removed from the treasure array so it can't
                // be placed again
                if (obj instanceof Treasure && !(obj instanceof Trophy)) {
                    // traverses through the whole array
                    for(int i = 0; i < treasures.length; i++) {
                        // if element matches, replace with null pointer
                        if (treasures[i].equals(obj)) {
                            treasures[i] = null;
                        }
                    }
                }
                // if interacted object is an enemy, it is
                // removed from the enemy array so it can't
                // be placed again and a duplicate interaction
                // does not happen
                else if (obj instanceof Enemy) {
                    // traverses through the whole array
                    for (int i = 0; i < enemies.length; i++) {
                        // if element matches, replace with null pointer
                        if (enemies[i].equals(obj)) {
                            enemies[i] = null;
                        }
                    }
                }



                // all enemies will move closer to player
                for (Enemy enemy : enemies) {
                    // if array element is not null - to avoid nullpointerexception
                    if (enemy != null) {
                        // moves them(determined where in the method) and stores the direction they moved
                        String direction = enemy.move(player, size);
                        // if it did move
                        if (direction != "") {
                        // places enemies in new position on grid
                            Object obj2 = grid.placeSprite(enemy, direction);
                            if (!(obj instanceof Enemy)) {
                                // interacts with player - lives
                                enemy.interact(direction, obj2);
                            }
                        }
                    }
                }
                // places every treasure on grid again
                // traverses through whole grid
                for (Treasure treasure : treasures) {
                    // checks for null for nullpointerexception
                    // if grid space is empty with dot
                    if (treasure != null && grid.getGrid()[treasure.getRow(size)][treasure.getCol()] instanceof Dot) {
                        // places treasure
                        grid.placeSprite(treasure);
                    }
                }
            }
        }
        // clears screen because no longer in while loop
        clearScreen();
        if (player.getWin()) {
            // if player wins, grid prints out win screen
            grid.win();
            System.out.println("YOU WIN");
        }
        else if (player.getLives() <= 0) {
            grid.placeSprite(player);
            // if player loses all lives, they lose
            // grid prints out lose screen
            grid.gameover();
            System.out.println("GAMEOVER");
        }
            
        scanner.close();
    }

    public void initialize() {

        //to test, create a player, trophy, grid, treasure, and enemies. Then call placeSprite() to put them on the grid
        // creates sprites
        player = new Player(0,0);
        trophy = new Trophy(0,9);
        treasures = new Treasure[2];
        treasures[0] = new Treasure(2,2);
        treasures[1] = new Treasure(8,8);
        enemies = new Enemy[3];
        enemies[0] = new Enemy(7,2);
        enemies[1] = new Enemy(5,5);
        enemies[2] = new Enemy(3,8);
        // initalizes grid
        grid = new Grid(size);
        // places sprites
        grid.placeSprite(player);
        grid.placeSprite(trophy);
        for (Treasure sprite : treasures) {
            grid.placeSprite(sprite);
        }
        for (Enemy sprite : enemies) {
            grid.placeSprite(sprite);
        }
    }

    public static void main(String[] args) {
        Game game = new Game(10);
    }
}