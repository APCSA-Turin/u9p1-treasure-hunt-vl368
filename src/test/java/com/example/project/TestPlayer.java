package com.example.project;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;


public class TestPlayer {
    @Test
    void testplayerInitialization() {
        Player player = new Player(0, 0);
        assertEquals(0, player.getX(), "Player's initial X should be 0");
        assertEquals(0, player.getY(), "Player's initial Y should be 0");
        assertEquals("Player:[9][0]",player.getRowCol(10), "Player should be at [9][0] bottom left");
        assertEquals(0, player.getTreasureCount(), "Player's initial treasure count should be 0");
        assertEquals(2, player.getLives(), "Player's initial lives should be 2");
        assertFalse(player.getWin(), "Player's initial win state should be false");
        
    }


    @Test
    void testPlayerMoveUp() {
        Player player = new Player(1, 1);
        player.move("w");
        assertEquals(1, player.getX(), "Player's X should remain 1 after moving up");
        assertEquals(2, player.getY(), "Player's Y should be 2 after moving up");
    }

    @Test
    void testPlayerMoveDown() {
        Player player = new Player(1, 1);
        player.move("s");
        assertEquals(1, player.getX(), "Player's X should remain 1 after moving down");
        assertEquals(0, player.getY(), "Player's Y should be 0 after moving down");
    }

    @Test
    void testPlayerMoveLeft() {
        Player player = new Player(1, 1);
        player.move("a");
        assertEquals(0, player.getX(), "Player's X should be 0 after moving left");
        assertEquals(1, player.getY(), "Player's Y should remain 1 after moving left");
    }

    @Test
    void testPlayerMoveRight() {
        Player player = new Player(1, 1);
        player.move("d");
        assertEquals(2, player.getX(), "Player's X should be 2 after moving right");
        assertEquals(1, player.getY(), "Player's Y should remain 1 after moving right");
    }

    @Test
    void testPlayerInteractWithTreasure() {
        Player player = new Player(0, 0);
        Treasure treasure = new Treasure(1, 0);
        player.interact(10, "d", 1, treasure);
        assertEquals(1, player.getTreasureCount(), "Player's treasure count should increase after interacting with treasure");
    }

    @Test
    void testPlayerInteractWithEnemy() {
        Player player = new Player(0, 0);
        Enemy enemy = new Enemy(1, 0);
        player.interact(10, "d", 1, enemy);
        assertEquals(1, player.getLives(), "Player's lives should decrease after interacting with enemy");
    }

    @Test
    void testPlayerInteractWithTrophyWithoutEnoughTreasures() {
        Player player = new Player(0, 0);
        Trophy trophy = new Trophy(1, 0);
        player.interact(10, "d", 1, trophy);
        assertEquals(0, player.getTreasureCount(), "Player's treasure count should remain 0");
        assertFalse(player.getWin(), "Player should not win without enough treasures");
    }

    @Test
    void testPlayerInteractWithTrophyWithEnoughTreasures() {
        Player player = new Player(0, 0);
        Trophy trophy = new Trophy(1, 0);
        player.interact(10, "d", 0, trophy); // Player has enough treasures (0 required)
        assertTrue(player.getWin(), "Player should win with enough treasures");
    }

    @Test
    void testPlayerIsValidMoveUp() {
        Player player = new Player(1, 1);
        assertTrue(player.isValid(10, "w"), "Player should be able to move up");
    }

    @Test
    void testPlayerIsValidMoveDown() {
        Player player = new Player(1, 1);
        assertTrue(player.isValid(10, "s"), "Player should be able to move down");
    }

    @Test
    void testPlayerIsValidMoveLeft() {
        Player player = new Player(1, 1);
        assertTrue(player.isValid(10, "a"), "Player should be able to move left");
    }

    @Test
    void testPlayerIsValidMoveRight() {
        Player player = new Player(1, 1);
        assertTrue(player.isValid(10, "d"), "Player should be able to move right");
    }

    @Test
    void testPlayerIsInvalidMoveUp() {
        Player player = new Player(1, 9); // At the top edge
        assertFalse(player.isValid(10, "w"), "Player should not be able to move up at the top edge");
    }

    @Test
    void testPlayerIsInvalidMoveDown() {
        Player player = new Player(1, 0); // At the bottom edge
        assertFalse(player.isValid(10, "s"), "Player should not be able to move down at the bottom edge");
    }

    @Test
    void testPlayerIsInvalidMoveLeft() {
        Player player = new Player(0, 1); // At the left edge
        assertFalse(player.isValid(10, "a"), "Player should not be able to move left at the left edge");
    }

    @Test
    void testPlayerIsInvalidMoveRight() {
        Player player = new Player(9, 1); // At the right edge
        assertFalse(player.isValid(10, "d"), "Player should not be able to move right at the right edge");
    }
}
