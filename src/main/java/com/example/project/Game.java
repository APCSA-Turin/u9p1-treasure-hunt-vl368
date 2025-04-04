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


        while(!(input == "q" || player.getWin() || player.getLives() == 0)){
            try {
                Thread.sleep(100); // Wait for 1/10 seconds
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            clearScreen(); // Clear the screen at the beggining of the while loop

            // actual game code
            grid.display();
            System.out.println(player.getCoords());
            System.out.println(player.getRowCol(size));
            System.out.println("Treasure Collected:" + (treasures.length-player.getTreasureCount()));
            System.out.println("Lives remaining:" + player.getLives());
            System.out.print("Enter a direction (w,a,s,d) or 'q' to exit: ");
            input = scanner.nextLine();
            if (player.isValid(size, input) && input != "q") {
                player.move(input);
                Object obj = grid.placeSprite(player, input);
                player.interact(size, input, treasures.length, obj);
            }
        }
        clearScreen();
        if (player.getWin()) {
            grid.win();
            System.out.println("YOU WIN");
        }
        else if (player.getLives() == 0) {
            grid.gameover();
            System.out.println("GAMEOVER");
        }
            
        scanner.close();
    }

    public void initialize() {

        //to test, create a player, trophy, grid, treasure, and enemies. Then call placeSprite() to put them on the grid
        player = new Player(0,0);
        trophy = new Trophy(0,9);
        treasures = new Treasure[2];
        treasures[0] = new Treasure(2,2);
        treasures[1] = new Treasure(8,8);
        enemies = new Enemy[3];
        enemies[0] = new Enemy(7,2);
        enemies[1] = new Enemy(5,5);
        enemies[2] = new Enemy(3,8);
        grid = new Grid(size);
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