package com.games.game;

import com.games.pieces.Alien;
import com.games.pieces.Asteroid;
import com.games.pieces.Planet;
import com.games.pieces.Player;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class Output {
    // member variables
    private static String prompt1 = "";
    private static String prompt2 = "What\'s your next command?";

    // business methods
    public static void pressAnyKeyToContinue(String message) {
        System.out.println(message);
        try {
            System.in.read();
        } catch(Exception e) {
            System.out.println(e);
        }
    }
    public static void pressAnyKeyToContinue() {
        System.out.println("Press ENTER key to continue...");
        try {
            System.in.read();
        } catch(Exception e) {
            System.out.println(e);
        }
    }

    public static void introNarrative(Player player) throws InterruptedException {
        // The story begins, fill the user in with their mission
        TimeUnit.SECONDS.sleep(1);
        System.out.println("ring ring...");
        TimeUnit.SECONDS.sleep(1);
        System.out.println("Hello...");
        TimeUnit.SECONDS.sleep(1);
        //System.out.println("Hi " + player.getName() + ". ");
        TimeUnit.SECONDS.sleep(1);
        System.out.println("This is Elon Musk calling.");
        TimeUnit.SECONDS.sleep(1);
        System.out.println("I need your help...");
        TimeUnit.SECONDS.sleep(1);
        pressAnyKeyToContinue();
        System.out.println("There is an asteroid headed straight for us. \nMars is the next frontier for humanity.");
        TimeUnit.SECONDS.sleep(2);
        pressAnyKeyToContinue();
        //System.out.println("The world needs you, " + player.getName() + ", to be \nthe one who " +
                //"will fly the Starship to Mars, \nand plant the first seed. ");
        TimeUnit.SECONDS.sleep(2);
        pressAnyKeyToContinue();
        System.out.println("Wow, but why me? I\'m not rea...");
        TimeUnit.SECONDS.sleep(1);
        System.out.println("We haven\'t a minute to waste!");
        TimeUnit.SECONDS.sleep(1);
        pressAnyKeyToContinue("Hit ENTER to blast off from Earth");
    }

    public static void askName() {
        System.out.println("Whats your name: ");
    }

    public static void printAsteroidObstacle(Asteroid asteroid){
        System.out.println("A " + asteroid.getSize() + " asteroid just appeared in front of you, \n" +
                "type \'right\' or \'left\' to try to dodge it.");
    }

//    different functions will store sections of the narrative.

//    also, other functions will store responses to certain inputs guided from the text parser
    // resources should be picked up by the HUD scanner, not the output
    public static void uponArrivingOnPlanet(Planet planet) {
        String name = planet.getName();
        ArrayList<String> resources = planet.getResources();
        System.out.println(name + " has: " + resources);
    }

//    public boolean dodgeAsteroid(Asteroid asteroid) {
//        boolean result = false;
//        String location = asteroid.getPosition();
////        Scanner scan = new Scanner(System.in);
//        HUD.prompt1("An asteroid has encountered you.. dodge right or left?");
////        printAsteroidObstacle(asteroid);
////        String input = scan.nextLine();
////        return input.equals(location);
//        return result;
//    }

    public static boolean shotAlien(Alien alien) {
        String location = alien.getPosition();
        Scanner scan = new Scanner(System.in);
        System.out.println("An alien is threatening you, \n" +
                "type \'left\', \'right\', \'bottom\', or \'up\' to try to shoot them.");
        String input = scan.nextLine();
        return input.equals(location);
    }

    // getters and setters
    public String getPrompt1() {
        return prompt1;
    }

    public static void setPrompt1(String prompt1) {
        Output.prompt1 = prompt1;
    }

    public static String getprompt2() {
        return prompt2;
    }

    public static void setPrompt2(String prompt2) {
        if (prompt2.length() < 1) {
            Output.prompt1 = "What\'s your next command?";
        } else {
            Output.prompt2 = prompt2;
        }
    }
}