package com.games.game;

import com.games.pieces.Player;
import com.games.pieces.*;

import javax.sound.sampled.LineUnavailableException;
import java.io.FileNotFoundException;
import java.util.*;

public class TextParser {
    private boolean usedShield;
    private static boolean inAsteroidBattle = false;
    private static boolean inAlienBattle = false;

    // create a list of familiar key verbs
    private Collection<String> verbs = new ArrayList<>(Arrays.asList("go", "use", "take"));
    // do the same for familiar key directional nouns to be able to consult with functions that are looking for nav. input
    private Collection<String> goNouns = new ArrayList<>(Arrays.asList("moon", "up", "right", "left", "down", "straight", "back", "mercury", "mars"));
    // certain functions will need to parse for familiar items to use
    private Collection<String> useNouns = new ArrayList<>(Arrays.asList("laser", "shield", "fuel", "weapon", "food", "super laser", "scrap metal", "Elon Musk"));

    // this function can do all the scanning for input once the game play begins. i.e. After the start menu and username entry.
    public void gamePlayScanner(String input, Player player, ArrayList<Planet> planets, ArrayList<Asteroid> asteroids, ArrayList<Alien> aliens, Starship starship, HUD hud, HashMap<String, HashMap<String, String>> space) {
        try {
            if(inAsteroidBattle) {
                // check if dodge input matches
                asteroidCollision(input, asteroids, hud, starship);
            } else if (inAlienBattle) {
                // check if shoot input matches
                alienFighting(input, aliens, player, hud, starship);
            }
            else {
                String[] inputSplit = input.split(" ", 2); // "go up" -> ['go', 'up']
                String verbCommand = inputSplit[0];
                // parse for the verb the user has chosen
                switch(verbCommand) {
                    case "go":
                        scanGoNouns(inputSplit[1], planets, asteroids, aliens, starship, space,hud, player);
                        break;
                    case "use" :
                    case "take":
                        takeUseDelegator(inputSplit[1], verbCommand, player, starship, hud);
                        break;
                    case "show":
                        showStatus(hud, starship);
                        break;
                    default:
                        hud.prompt1("What exactly are you saying?");
                }
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            hud.prompt1("ArrayOutOfBoundsException: ");
            hud.prompt2(e.getMessage() + ". Try again.");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (LineUnavailableException e) {
            e.printStackTrace();
        }
    }

    // print the user health, fuel, inventory, location
    public static void showStatus(HUD hud,Starship starship) {
        Planet currentLocation = starship.getCurrentLocation();
        hud.display(currentLocation);
    }

    public void scanGoNouns(String noun, ArrayList<Planet> planets,ArrayList<Asteroid> asteroids, ArrayList<Alien> aliens, Starship starship, HashMap<String, HashMap<String, String>> space, HUD hud,Player player) {
// if the argument is a valid goNoun, then call the correct function to output the correct message
        // search the hashmap for VALID destinations
        HashMap<String, String> neighbors= space.get(starship.getCurrentLocation().getName());
        if (neighbors.containsKey(noun)) {
            for(Planet planet : planets){
                if(planet.getName().equals(neighbors.get(noun))){
                    starship.burnFuel();
                    starship.setCurrentLocation(planet);
                    hud.prompt1("You have arrived at " + starship.getCurrentLocation().getName());
                    break;
                }
            }
            // if asteroids, make asteroid list, tell player to WATCH OUT!, and allow player to try to dodge out of harms way
            // if player hits asteroid, deduct health. set current location to asteroids
            if(neighbors.get(noun).substring(0, neighbors.get(noun).length()-1).equals("Asteroids")){
                // pass a boolean to let hal know that you are in an asteroid battle
                setInAsteroidBattle(true);
                hud.prompt1("Boom! Its an asteroid field!");
                hud.prompt2("Type left, right, up, or down to dodge a randomly placed asteroid.");

            } else if (neighbors.get(noun).substring(0, neighbors.get(noun).length()-1).equals("Aliens")) {
//                starship.setInSpace(true);
                setInAlienBattle(true);
                hud.prompt1("Alien attack!");
                hud.prompt2("Type left, right, up, or, down to shoot a randomly placed alien ship.");
            }
        }
        else {
            hud.prompt1("You can\'t go that way.");
        }
    }

    public void asteroidCollision(String input, ArrayList<Asteroid> asteroids, HUD hud, Starship starship) throws FileNotFoundException, LineUnavailableException {
        String astLocation = asteroids.get(0).getPosition();
        // check if user's input
        if (input.equals(astLocation)) {
            hud.prompt1("Phwew dodged it");
            asteroids.remove(0);
        } else {
            if(usedShield) {
                starship.takenDamage(5);
            } else {
                starship.takenDamage(10);
            }
            asteroids.remove(0);
            hud.prompt1("Ouch! Starship hit! Health decreased!");
        }
        if (asteroids.size() <= 0) {
            inAsteroidBattle = false;
            hud.prompt2("You made it passed the asteroids.");
        } else {
            hud.prompt2("There\'s another asteroid, which way do you want to steer?");
        }
    }

    public void alienFighting(String input, ArrayList<Alien> aliens, Player player, HUD hud, Starship starship) throws FileNotFoundException, LineUnavailableException {
        String alienLocation = aliens.get(0).getPosition();
        if (input.equals(alienLocation) && player.playerHasWeapon()) {
            aliens.get(0).setHealth(aliens.get(0).getHealth() - 50);
            hud.prompt1("Target hit! Alien ship health: " + aliens.get(0).getHealth() + "%");
            if (aliens.get(0).getHealth() <=0 ) {
                hud.prompt1("Alien destroyed!");
                aliens.remove(0);
            }
        } else {
            if(usedShield) {
                starship.takenDamage(10);
            } else {
                starship.takenDamage(20);
            }
            hud.prompt1("Starship hit! Health decreased!");
        }
        if (aliens.size() <= 0) {
            inAlienBattle = false;
            hud.prompt2("You made it passed the aliens.");
        } else {
            hud.prompt2("There\'s another alien, which way do you want to steer?");
        }
    }

    public void takeUseDelegator(String noun, String verb, Player player, Starship starship, HUD hud) {
// if the argument is valid useNoun, " "
        if (verb.equals("use")) {
            hud.prompt1("you want to use " + noun + " ?");
            whichItemToCallUseWith(noun, player, hud, starship);
        }
        else if (verb.equals("take")){
          take(noun, player, hud);
        }
    }

    // scan the planet's resources and make sure you can take/use whatever noun passed in
    // pop that resource out of the Planet's collection
    public void whichItemToCallUseWith(String noun, Player player, HUD hud, Starship starship){
        for(String word : useNouns){
            if(word.equals(noun)){
                use(noun, player, hud, starship);
            }
        }
    }
    public void use(String noun, Player player, HUD hud, Starship starship){
        switch(noun){
            case "shield":
                usedShield = true;
                break;
            case "fuel":
                starship.refuel();
                // remove fuel from player's inventory
                break;
            case "Elon Musk":
                hud.prompt1("Elon says he doesn't like feeling used.");
                break;
            default:
                usedShield = false;
                hud.prompt1("Looks like you don't have one of those.");
        }
    }
    public void take(String noun, Player player, HUD hud){
        hud.prompt1("You want to take " +noun);
        player.setInventory(noun);
    }

    // getters and setters
    public static boolean isInAsteroidBattle() {
        return inAsteroidBattle;
    }

    public static void setInAsteroidBattle(boolean inAsteroidBattle) {
        TextParser.inAsteroidBattle = inAsteroidBattle;
    }

    public static boolean isInAlienBattle() {
        return inAlienBattle;
    }

    public static void setInAlienBattle(boolean inAlienBattle) {
        TextParser.inAlienBattle = inAlienBattle;
    }
}