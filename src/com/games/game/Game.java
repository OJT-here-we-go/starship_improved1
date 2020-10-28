package com.games.game;

import com.games.maps.MapPanelGenerator;
import com.games.pieces.*;
//import com.games.pieces.Planet;
//import com.games.pieces.Player;
//import com.games.pieces.Starship;

import javax.sound.sampled.LineUnavailableException;
import javax.swing.*;
import java.awt.*;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;


import java.io.FileNotFoundException;
import java.util.*;
import java.util.Random;

public class Game {

    // Member Variables
    Player player1;
    Planet earth;
    Planet moon;
    Planet venus;
    Planet mercury;
    Planet mars;
    Planet obstacle1;
    Planet obstacle2;
    ArrayList<Planet> planets = new ArrayList<>();
    ArrayList<Asteroid> asteroids;
    ArrayList<Alien> aliens;
    Starship starship;
    HUDGui hud;
    OutputGui output;
    Level level1;
    TextParser parser;
    private JFrame parentWindow;
    public static HashMap<String, HashMap<String, String>> space = new HashMap<>();

    //private Rectangle gameScreenRec;
    public MapPanelGenerator currentPanel;
    public GameArea gameArea;
    private boolean isRunning;
    private static final int mapWidth = 100;
    private static final int mapHeight = 100;
    private int framesPerSecond = 60;
    private int timePerLoop = 1000000000 / framesPerSecond;

    //private static final int HEIGHT = 10;
//   private static final int WIDTH = 10;
    public Game() {
        this.timePerLoop = 1000000000 / this.framesPerSecond;
        this.parentWindow = new JFrame();
    }

    public HashMap<String, HashMap<String, String>> drawGame() {
// Earths neighbors
        HashMap<String, String> earthNeighbors = new HashMap<>();

        earthNeighbors.put("right", "Moon");
        space.put("Earth", earthNeighbors);
// Moon neighbors
        HashMap<String, String> moonNeighbors = new HashMap<>();

        moonNeighbors.put("left", "Earth");
        moonNeighbors.put("up", "Venus");
        space.put("Moon", moonNeighbors);
// Venus
        HashMap<String, String> venusNeighbors = new HashMap<>();

        venusNeighbors.put("down", "Moon");
        venusNeighbors.put("up", "Mercury");
        space.put("Venus", venusNeighbors);
// Mercury neighbors
        HashMap<String, String> mercuryNeighbors = new HashMap<>();

        mercuryNeighbors.put("down", "Venus");
        mercuryNeighbors.put("left", "Asteroids1");
        space.put("Mercury", mercuryNeighbors);
// Asteroids1 neighbors
        HashMap<String, String> asteroid1Neighbors = new HashMap<>();

        asteroid1Neighbors.put("right", "Mercury");
        asteroid1Neighbors.put("up", "Aliens1");
        space.put("Asteroids1", asteroid1Neighbors);
// aliens
        HashMap<String, String> alien1Neighbors = new HashMap<>();

        alien1Neighbors.put("down", "Asteroids1");
        alien1Neighbors.put("up", "Mars");
        space.put("Aliens1", alien1Neighbors);
// Mars
        HashMap<String, String> marsNeighbors = new HashMap<>();

        marsNeighbors.put("down", "Aliens1");
        space.put("Mars", marsNeighbors);

        return space;
    }

//  Business Methods


    //init the HUD as a panel that can be added to stuff
//    public JPanel hudInit() {
//        JPanel hudDisplay = new JPanel();
//        hudDisplay.setBounds(100, 100, 600, 150);
//
//        JLabel titleLabel = new JLabel("Current Planet");
//
//
//
//    }



    public void begin(int screenWidth, int screenHeight) throws InterruptedException, FileNotFoundException, LineUnavailableException {

        this.player1 = new Player('@', Color.cyan, 8, 16);
        this.starship = new Starship(earth, 8, 16);
//        this is where they set positions for all the planets... hmmm but its not really used?
        this.earth = new Planet("Earth", new ArrayList<>(Arrays.asList("water", "food")), 10, 16,
                Color.blue, 'E',starship);
        this.moon = new Planet("Moon", new ArrayList<>(Arrays.asList("fuel", "Elon Musk", "weapon")), 13, 11, Color.LIGHT_GRAY, 'm',starship);
        this.venus = new Planet("Venus", new ArrayList<>(Arrays.asList("fuel", "scrap metal")), 6
                , 20, Color.magenta, 'V',starship);
        this.mercury = new Planet("Mercury", new ArrayList<>(Arrays.asList("super laser", "shield")), 4, 22, Color.yellow, 'M',starship);
        this.obstacle1 = new Planet("Asteroids1", new ArrayList<>(Arrays.asList("speed booster")),
                starship);
        this.obstacle2 = new Planet("Aliens1", new ArrayList<>(Arrays.asList("bb gun")),starship);
        this.mars = new Planet("Mars", new ArrayList<>(), 70, 3, Color.orange, 'M',starship);
        this.planets.add(earth);
        this.planets.add(moon);
        this.planets.add(venus);
        this.planets.add(mercury);
        this.planets.add(mars);
        this.planets.add(obstacle1);
        this.planets.add(obstacle2);
        this.asteroids = createAsteroids(3, "large");
        this.aliens = createAliens(3);

        this.level1 = new Level();
        this.parser = new TextParser();

        //
        this.hud = new HUDGui(starship,player1);
        this.output = new OutputGui();
        //space = drawGame();
        //System.out.println(player1.getName());
//        this.parentWindow = new JFrame();
//        this.parentWindow.setBackground(Color.BLUE);
//        this.parentWindow.setLayout(new BorderLayout());
//        this.parentWindow.setSize(new Dimension(800, 545));
//        this.parentWindow.setLocationRelativeTo((Component)null);
//        this.parentWindow.setTitle("Starship Game");
//        this.parentWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JFrame menu = new JFrame();
        JButton playButton = new JButton(new ImageIcon("src/images/Starship.png"));
        JButton quitButton = new JButton("Quit Game");
        playButton.setPreferredSize(new Dimension(100, 80));
        quitButton.setSize(new Dimension(60, 60));
        playButton.setContentAreaFilled(false);
        playButton.setBorderPainted(false);
        playButton.setActionCommand("play");
        quitButton.setActionCommand("quit");
        menu.setLayout(new BorderLayout());
        menu.add(playButton, "Center");
        menu.add(quitButton, "Last");
        menu.setLocationRelativeTo(null);
        menu.setSize(new Dimension(825, 650));
        this.gameArea = new GameArea(new Rectangle(screenWidth, screenHeight), this.starship, this.player1, this.hud, this.output);
//        this.parentWindow.getContentPane().add(this.gameArea.getAsciiPanel(), BorderLayout.PAGE_START);
//        this.parentWindow.getContentPane().add(this.hud.getHudPanel(), BorderLayout.LINE_END);
//        this.parentWindow.getContentPane().add(this.output.getOutputPanel(), "South");
        playButton.addActionListener((e) -> {
            if (e.getActionCommand().equals("play")) {
                menu.setVisible(false);
                this.gameArea.setVisible(true);
                gameArea.requestFocus();
            } else {
                menu.setVisible(true);
            }

        });
        menu.setVisible(true);
        this.parentWindow.revalidate();
        this.parentWindow.repaint();


        //this starts the space game area jframe
        //gameArea = new GameArea(new Rectangle(screenWidth, screenHeight),this.starship,
                //this.player1,this.hud,this.output);

//        gameArea = new GameArea(new Rectangle(screenWidth, screenHeight), new Rectangle(mapWidth, mapHeight));
//        OutputGui outputGui = new OutputGui(gameArea);
//        HUDGui hudGui = new HUDGui(gameArea,starship,player1);

        //if we wanted a title screen or something like that, we should put it after the game area get initialized (so like, here)




        this.play(this.player1, this.planets, this.asteroids, this.aliens, this.starship,
                this.level1);
    }

    public void play(Player player, ArrayList<Planet> planets, ArrayList<Asteroid> asteroids, ArrayList<Alien> aliens, Starship starship, Level level) throws InterruptedException, FileNotFoundException, LineUnavailableException {
//        output.introNarrative(player);
        String initialThoughts = "Welcome to Starship.";
        HUDGui var10000 = this.hud;
        hud.prompt1(initialThoughts);
        this.run(); //This is the problem, run gets executed forever, until window is closed...
        // code below never gets executed
//        while(starship.getFuel() > 0 && starship.getHealth() > 0){
//            this.hud.display(starship.getCurrentLocation());
//            // keep accepting commands from player and playing
//            System.out.print("|| Input: ");
//            Scanner input = new Scanner(System.in);
//            String command = input.nextLine();
//            parser.gamePlayScanner(command, player, planets, asteroids, aliens, starship, hud, space);
//        }
//         // else, loop breaks, ask the player if they'd like to start over
//        if(starship.getFuel() <= 0 || starship.getHealth() <= 0) {
//            if(starship.getCurrentLocation() == mars){
//                System.out.println("You made it to Mars! Congratulations.");
//            }
//            else{
//                System.out.println("Game over. Enter \'y\' to play again or \'n\' to exit.");
//            }
//            restartOrClose();
//        }
    }

    public void restart() throws InterruptedException, FileNotFoundException, LineUnavailableException {
        player1.clearInventory();
        starship.setHealth(starship.getHealth() + (100 - starship.getHealth()));
        starship.setFuel(starship.getFuel() + (100 - starship.getFuel()));
        starship.setCurrentLocation(earth);
        parser = new TextParser();
        output = new OutputGui();
        hud = new HUDGui(starship, player1);
        play(player1, planets, asteroids, aliens, starship, level1);
    }

    public void restartOrClose() throws InterruptedException, FileNotFoundException, LineUnavailableException {
        if(startOverPrompt()){
            this.restart();
        }
        else{
            System.exit(0);
        }
    }
    public boolean startOverPrompt(){
        Scanner input = new Scanner(System.in);
        String command = input.nextLine().toLowerCase();
        while(!command.equals("y") && !command.equals("n")){
            System.out.println("Invalid choice. Enter y or n. \n Do you want to try again?");
            command = input.nextLine().toLowerCase();
        }
        if(command.equals("y")){
            System.out.println("You entered play again");
            return true;
        }
        else {
            System.out.println("Game exiting.");
            return false;
        }
    }
    public ArrayList<Asteroid> createAsteroids(int numOfRocks, String size){
        ArrayList<Asteroid> asteroids = new ArrayList<>();
        for(int i = 0; i < numOfRocks; i++){
            String position = "left";
            // randomly pick a position i.e. "left", "down"
            // TODO: give the player more or less options for dodging to make difficulty variable
            // large - player has 25% chance of dodging. options: up, down, left, right
            // medium - player has 33% chance of dodging. options: up, left, right
            // small - player has 50% chance of dodging. options: left, right
            Random rand = new Random();
            int random = rand.nextInt(12);
            if(random < 6 && random % 2 == 0) {
                position = "right";
            } else if (random < 6 && random % 2 != 0) {
                position = "left";
            } else if (random >= 6 && random % 2 == 0) {
                position = "up";
            } else if (random >= 6 && random % 2 != 0) {
                position = "down";
            }
            asteroids.add(new Asteroid(size, position));
        }
        return asteroids;
    }

    public ArrayList<Alien> createAliens(int numOfAliens){
        ArrayList<Alien> aliens = new ArrayList<>();
        for(int i = 0; i < numOfAliens; i++){
            // randomly pick left or right or up or down
            //for now, i will hard code it to down
            String position = "left";
            Random rand = new Random();
            int random = rand.nextInt(12);
            if(random < 6 && random % 2 == 0) {
                position = "right";
            } else if (random < 6 && random % 2 != 0) {
                position = "left";
            } else if (random >= 6 && random % 2 == 0) {
                position = "up";
            } else if (random >= 6 && random % 2 != 0) {
                position = "down";
            }
            aliens.add(new Alien(position));
        }
        return aliens;
    }
    // handle user input, such as KeyEvents
    //THIS IS THE MEAT AND POTATOES
    public void processInputSpace() {
        InputEvent event = gameArea.getNextInput();
        if (event instanceof KeyEvent) {
            KeyEvent keyPress = (KeyEvent)event;
            // check if user is pressing the arrow keys
            switch (keyPress.getKeyCode()){
                case KeyEvent.VK_LEFT:
                    starship.moveSpace(-1, 0);
                    break;
                case KeyEvent.VK_RIGHT:
                    starship.moveSpace(1, 0);
                    break;
                case KeyEvent.VK_UP:
                    starship.moveSpace(0, -1);
                    break;
                case KeyEvent.VK_DOWN:
                    starship.moveSpace(0, 1);
                    break;
                case KeyEvent.VK_Z:
                    gameArea.drawMyBullets(starship.getxPos(), starship.getyPos());
                    break;
                case KeyEvent.VK_X:
                    if (starship.pickUp(gameArea, planets)) {
                        gameArea.getContentPane().remove(gameArea.getAsciiPanel());
                        currentPanel = new MapPanelGenerator(starship);
                        gameArea.add(currentPanel);
                        starship.inSpace = false;
                        gameArea.requestFocus();
                        gameArea.revalidate();
                        gameArea.repaint();
                    }
                    break;
            }
        } else if (event instanceof MouseEvent) {
            // possibly do things if the user clicks the mouse
        }
    }

    public void renderGameAreaSpace() throws FileNotFoundException, LineUnavailableException {
        gameArea.pointCameraAt(starship, starship.getxPos(), starship.getyPos());
        gameArea.refresh();
    }

    public void processInputPlanet() {
        InputEvent event = gameArea.getNextInput();
        if (event instanceof KeyEvent) {
            KeyEvent keyPress = (KeyEvent)event;
            // check if user is pressing the arrow keys
            switch (keyPress.getKeyCode()){
                case KeyEvent.VK_LEFT:
                    if (InteractionsUtil.checkRunIntoObjectLeft(starship,output)) {
                        starship.movePlanet(-1, 0);
                    }
                    break;
                case KeyEvent.VK_RIGHT:
                    if (InteractionsUtil.checkRunIntoObjectRight(starship,output)) {
                        starship.movePlanet(1, 0);
                    }
                    break;
                case KeyEvent.VK_UP:
                    if (InteractionsUtil.checkRunIntoObjectUp(starship,output)) {
                        starship.movePlanet(0, -1);
                    }
                    break;
                case KeyEvent.VK_DOWN:
                    if (InteractionsUtil.checkRunIntoObjectDown(starship,output)) {
                        starship.movePlanet(0, 1);
                    }
                    break;
//                case KeyEvent.VK_Z:
//                    gameArea.drawMyBullets(starship.getxPos(), starship.getyPos());
//                    break;
                case KeyEvent.VK_X:
                    if (starship.pickUp(gameArea, planets)) {


                    }
                    break;
            }
        } else if (event instanceof MouseEvent) {
            // possibly do things if the user clicks the mouse
        }
    }

    public void renderGameAreaPlanet() throws FileNotFoundException, LineUnavailableException {
        starship.getCurrentLocation().posUpdate();
        parentWindow.repaint();
        parentWindow.revalidate();
    }


    // load the JFrame window

    // this can be put in the main to load windows on same process rather than what first group did
    public void run() throws FileNotFoundException, LineUnavailableException {
        isRunning = true;
        String filepath = "./Sound/StarshipBGM16.wav"; //LOAD DEFAULT BGM
        BackgroundMusic.playBGM(filepath); //CALL BGM WITH DEFAULT

        while(isRunning) {
            long startTime = System.nanoTime();

            if (starship.inSpace) {
                processInputSpace();
                renderGameAreaSpace();
            }
            else {
                processInputPlanet();
                renderGameAreaPlanet();
            }

            long endTime = System.nanoTime();

            long sleepTime = timePerLoop - (endTime-startTime);

            if (sleepTime > 0) {
                try {
                    Thread.sleep(sleepTime/1000000);
                } catch (InterruptedException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        }
    }
}
