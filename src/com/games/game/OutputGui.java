package com.games.game;

import com.games.pieces.Alien;
import com.games.pieces.Asteroid;
import com.games.pieces.Planet;
import com.games.pieces.Player;

import javax.swing.*;
import java.awt.*;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class OutputGui extends JPanel {
    // member variables
    private JPanel messagesPanel;
    

    private JTextArea playerMessage = new JTextArea();
    private JScrollPane playerMessagePane = new JScrollPane(playerMessage,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
    private JLabel playerMessageLabel = new JLabel("Player messages: ");

    private JTextArea hitsMessage = new JTextArea();
    private JScrollPane hitsMessagePane = new JScrollPane(hitsMessage,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
    private JLabel hitsMessageLabel = new JLabel("Player combat messages: ");

    private PrintStream playerMessageOutput = new PrintStream(new CustomOutputStream(playerMessage));
    private PrintStream hitsMessageOutput = new PrintStream(new CustomOutputStream(hitsMessage));
    
    //ctor
    public OutputGui(){
        hitsMessage.setEditable(false);
        playerMessage.setEditable(false);
        this.messagesPanel = new JPanel();
        this.messagesPanel.setLayout(new BoxLayout(messagesPanel, BoxLayout.X_AXIS));
//        playerMessagePane.add(playerMessageLabel);
        playerMessagePane.setPreferredSize(new Dimension(100, 120));
//        hitsMessagePane.add(hitsMessageLabel);
        hitsMessagePane.setPreferredSize(new Dimension(100, 120));
//        this.messagesPanel.add(playerMessageLabel, BorderLayout.LINE_START);
        this.messagesPanel.add(playerMessagePane, BorderLayout.LINE_START);
        this.messagesPanel.add(hitsMessagePane, BorderLayout.LINE_END);
        displayInstructions();

//        parentWindow.getContentPane().add(messagesPanel, BorderLayout.SOUTH);
//        parentWindow.revalidate();
//        parentWindow.repaint();
    }
    
    //getter
    public JPanel getOutputPanel(){
        return messagesPanel;
    }

    // business methods

    public void displayInstructions() {
        setPlayerMessage();
        System.out.println("Your spaceship is the Cyan '@'.");
        System.out.println("Press the arrow keys to navigate. Z to shoot, X to interact.");
        System.out.println("Avoid aliens, asteroids, and enemy projectiles!");
        System.out.println("Gather items from planets and make it to Mars to colonize it in the name of Amazon");
        setDefaultSysOut();
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
        System.out.println("There is an asteroid headed straight for us. \nMars is the next frontier for humanity.");
        TimeUnit.SECONDS.sleep(2);
        //System.out.println("The world needs you, " + player.getName() + ", to be \nthe one who " +
                //"will fly the Starship to Mars, \nand plant the first seed. ");
        TimeUnit.SECONDS.sleep(2);
        System.out.println("Wow, but why me? I\'m not rea...");
        TimeUnit.SECONDS.sleep(1);
        System.out.println("We haven\'t a minute to waste!");
        TimeUnit.SECONDS.sleep(1);
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
    public void setPlayerMessage(){
        System.setOut(playerMessageOutput);
    }

    public void setHitsMessage(){
        System.setOut(hitsMessageOutput);
    }

    public void setDefaultSysOut() {
        System.setOut(System.out);
    }


}