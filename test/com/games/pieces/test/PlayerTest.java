package com.games.pieces.test;

import com.games.pieces.Player;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;


public class PlayerTest {
    Player player;

    @Before
    public void init() {
        player = new Player('x', null, 0,0);
    }

    //Positive Test
    @Test
    public void testPlayerHasWeapon(){
        player.setInventory("weapon");
        String weapon = player.getInventory().get(0);
        assertEquals(weapon, "weapon");
    }

    //Negative test
//    @Test
//    public void testPlayerHas_InvalidWeapon(){
//        player.setInventory("weapon");
//        String weapon = player.getInventory().get(0);
//        assertNotEquals(weapon, "weapon");
//    }
}