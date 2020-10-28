package com.games.client;

import com.games.game.Game;

//import com.games.maps.Earth;
//import com.games.maps.Moon;

import javax.sound.sampled.LineUnavailableException;
import javax.swing.*;
import java.io.FileNotFoundException;
import java.io.IOException;

public class Main {

    public static void main(String[] args) throws InterruptedException, FileNotFoundException, LineUnavailableException {
//        start the game here

        System.out.println("Welcome to Starship");
        //LandingPage menu = new LandingPage();
        Game game = new Game();
        game.begin(80,24);
//        Earth.draw();
//        Moon.draw();

    }
}
