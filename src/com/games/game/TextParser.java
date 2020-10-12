package com.games.game;

import com.games.pieces.Player;
import com.games.pieces.*;
import com.games.game.Game;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;

public class TextParser {
    // create a list of familiar key verbs
    private Collection<String> verbs = new ArrayList<>(Arrays.asList("go", "use", "take"));
    // do the same for familiar key directional nouns to be able to consult with functions that are looking for nav. input
    private Collection<String> goNouns = new ArrayList<>(Arrays.asList("moon", "up", "right", "left", "down", "straight", "back", "mercury", "mars"));
    // certain functions will need to parse for familiar items to use
    private Collection<String> useNouns = new ArrayList<>(Arrays.asList("laser", "shield"));

    // this function can do all the scanning for input once the game play begins. i.e. After the start menu and username entry.
    public static void gamePlayScanner(String input, Player player, ArrayList<Planet> planets, ArrayList<Asteroid> asteroids, ArrayList<Alien> aliens, Starship starship, HUD display, Level level, HashMap<String, HashMap<String, String>> space) {
        try {
            String[] inputSplit = input.split(" ", 2); // "go up" -> ['go', 'up']
            String verbCommand = inputSplit[0];
            // parse for the verb the user has chosen
            switch(verbCommand) {
                case "go":
                    scanGoNouns(inputSplit[1], planets, asteroids, aliens, starship, space, player);
                    break;
                case "use":
                    scanUseNouns(inputSplit[1], verbCommand, player);
                    break;
                case "take":
                    scanUseNouns(inputSplit[1], verbCommand, player);
                    break;
                case "show":
                    showStatus(starship, player);
                    break;
                default:
                    System.out.println("What exactly are you saying? ");
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("ArrayOutOfBoundsException: ");
            System.out.println(e.getMessage());
            System.out.println("Try again");
        }
    }

    // print the user health, fuel, inventory, location
    public static void showStatus(Starship starship, Player player) {
        HUD hud = new HUD(starship, player);
        hud.display();
    }

    public static void scanGoNouns(String noun, ArrayList<Planet> planets,ArrayList<Asteroid> asteroids, ArrayList<Alien> aliens, Starship starship, HashMap<String, HashMap<String, String>> space, Player player) {
// if the argument is a valid goNoun, then call the correct function to output the correct message
        // search the hashmap for VALID destinations
        HashMap<String, String> neighbors= space.get(starship.getCurrentLocation().getName());
        if (neighbors.containsKey(noun)) {
            for(Planet planet : planets){
                if(planet.getName().equals(neighbors.get(noun))){
                    starship.setCurrentLocation(planet);
                    System.out.println("You have arrived --> " + starship.getCurrentLocation().getName());
                    // if (we dealing with a planet and not an asteroid nor alien) {
                    Output.uponArrivingOnPlanet(planet);
                    break;
                }
            }
            // if asteroids, make asteroid list, tell pleyer to WATCH OUT!, and allow player to try to dodge out of harms way
            // if player hits asteroid, deduct health. set current location to asteroids
            if(neighbors.get(noun).substring(0, neighbors.get(noun).length()-1).equals("Asteroids")){
                System.out.println("Boom! Its an asteroid field!");
                starship.setCurrentAsteroids(neighbors.get(noun));
                for(Asteroid asteroid : asteroids) {
                    boolean dodged = Output.dodgeAsteroid(asteroid);
                    // take damage or not based on a boolean if they dodged correctly
                    if(dodged) {
                        System.out.println("Dodged asteroid! Good job");
                    } else {
                        System.out.println("!!!@!%! Ouch!* Starship hit.");
                        starship.setHealth(starship.getHealth() - 20);
                    }
                }
            } else if (neighbors.get(noun).substring(0, neighbors.get(noun).length()-1).equals("Aliens")) {
                System.out.println("Boom! Its an alien ambush!");
                starship.setInSpace(true);
                for(Alien alien : aliens) {
                    int count = 1;
                    while(alien.getHealth() > 0) {
                        System.out.println("Alien ship health: " + alien.getHealth());
                        boolean shot = Output.shotAlien(alien);
                        if (shot && player.playerHasWeapon()) {
                            alien.setHealth(alien.getHealth() - 50);
                            System.out.println("Direct hit! You hit the target!");
                            // while (alien.getHealth > 0) stay with it and kill him. change aliens position
                            //until it dies, see if you can remove it from the list once dead
                        }
                        else if(shot && !player.playerHasWeapon()){
                            System.out.println("Should've grabbed that weapon! Alien ship fires right at you! Starship health: \'-20\'");
                            starship.setHealth(starship.getHealth() - 20);
                        }
                        else {
                            System.out.println("Missed! Alien ship fired! Starship health: \'-20\'");
                            starship.setHealth(starship.getHealth() - 20);
                        }
                        count++;
                    }
                    System.out.println("You killed an alien ship! Success!");
                }
            }

        }
        else {
            System.out.println("You can\'t go that way.");
        }
    }

    public static void scanUseNouns(String noun, String verb, Player player) {
// if the argument is valid useNoun, " "
        if (verb.equals("use")) {
            System.out.println("you want to use " + noun + " ?");
        }
        else if (verb.equals("take")){
            System.out.println("you want to take " + noun + " ?");
            player.setInventory(noun);
        }
    }
}
