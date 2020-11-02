package com.games.pieces.test;
import com.games.pieces.Starship;

import static org.junit.Assert.*;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class StarshipTest {
    Starship starship;

    @BeforeClass
    public static void before() {
        System.out.println("Before Test Setup");
    }

    @AfterClass
    public static void after() {
        System.out.println("After Test Cleanup");
    }

//    @Before
////    public void init() {
////        starship = new Starship(null, 8, 16);
////    }

    //Positive Tests
    @Test
    public void testSetHealth_whenValid() {
        starship.setHealth(50);
        assertEquals(50, starship.getHealth());
    }

    //Negative Forced Error
    @Test(expected=IllegalArgumentException.class)
    public void testSetHealth_upperBound() {
        starship.setHealth(101);
    }

    //Negative Forced Error Lower Boundary Test
    @Test(expected=IllegalArgumentException.class)
    public void testSetHealth_lowerBound() {
        starship.setHealth(-1);
    }


}
