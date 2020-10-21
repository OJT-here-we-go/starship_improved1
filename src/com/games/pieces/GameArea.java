package com.games.pieces;

import asciiPanel.AsciiPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.lang.reflect.Array;
import java.util.*;
import java.util.List;

public class GameArea extends JFrame implements KeyListener, MouseListener{
    private Rectangle gameScreenRec;
    private AsciiPanel panel;
    //private AsciiCamera camera;
    private Queue<InputEvent> inputQueue;
    private ArrayList<Asteroid> asteroids = new ArrayList<>();
    private ArrayList<Alien> aliens = new ArrayList<>();
    private List<Weapon> bullets = new ArrayList<>();
    private List<Weapon> alienBullets = new ArrayList<>();
    private ArrayList<Planet> bodies = new ArrayList<>();
    private int updateMonsters;
    private int updateAttacks;
    private int hitsIndicator;
    public GameArea(Rectangle gameAreaRec, Rectangle mapAreaRec) {
        gameScreenRec = gameAreaRec;
        inputQueue = new LinkedList<>();
        panel = new AsciiPanel(this.gameScreenRec.width, this.gameScreenRec.height);
        super.setLayout(new BorderLayout());
        super.getContentPane().add(panel,BorderLayout.LINE_START);
        super.addKeyListener(this);
        super.addMouseListener(this);
        super.setSize(this.gameScreenRec.width*12, this.gameScreenRec.height*17);
        super.setVisible(true);
        super.setResizable(false);

        super.setTitle("Starship");
        super.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //instantiate asteroids through method call
        drawAsteroids();
        // instantiate aliens through method call
        drawAliens();
        drawPlanets();
        super.repaint();
        super.setFocusable(true);
        super.requestFocus();

        //put another panel in here in the constructor and take inputs from the HUD


    }

    // distance from x and y to begin writing/printing from
    public Point GetCameraOrigin(int xfocus, int yfocus) {
        int spx = Math.max(0, Math.min(xfocus - gameScreenRec.width / 2, 0));
        int spy = Math.max(0, Math.min(yfocus - gameScreenRec.height / 2, 0));
        return new Point(spx, spy);
    }

    public void pointCameraAt(Starship player1, int xfocus, int yfocus) {
        int spx;
        int spy;

        Point origin = GetCameraOrigin(xfocus, yfocus);

        spx = player1.getxPos() - origin.x;
        spy = player1.getyPos() - origin.y;

        // paint the board with '.' to show where the player can move to
        for (int x = 0; x < gameScreenRec.width; x++){
            for (int y = 0; y < gameScreenRec.height; y++){
                panel.write('.', x, y, Color.white, Color.black);
            }
        }

        // draw the asteroids on the panel
        for(Asteroid asteroid: asteroids) {
            panel.write('A', asteroid.getX(), asteroid.getY(), Color.lightGray, Color.black);
        }

        //move asteroids every 25 refresh()es
        int asteroidSpd = 25;
        if(updateMonsters >= asteroidSpd) {
            floatAsteroids();
        }

        // check for starship/asteroid collision "Ascii A"  (Hits 1 HP X 25 times for some reason)
        for(Asteroid asteroid: asteroids) {
            if (player1.getxPos() == (asteroid.getX()) && player1.getyPos() == (asteroid.getY())) {
                System.out.println("Your Starship smashed into an Asteroid! When will the damage stop!!??");
                panel.write('@', spx, spy, Color.red, Color.black);
                hitsIndicator = 25;
                player1.takenDamage(1);
                System.out.println("Health: " + player1.getHealth());
            } else if (player1.getHealth() <= -1) {
                System.out.printf("Your ship sustained total damage and you died in the crash");
                System.exit(0);
            }
        }

        // draw the aliens on the panel
        for(Alien alien: aliens) {
            panel.write('X', alien.getX(), alien.getY(), Color.green, Color.black);
        }

        // move each alien every 20 refresh() calls
        int alienSpd = 22;
        if(updateMonsters >= alienSpd) {
            floatAliens();
        }

        //check for starship/alien collision  "Ascii X"  (Hits 1 HP)
        for (Alien alien : aliens) {
            if (player1.getxPos() == alien.getX() && player1.getyPos() == alien.getY()) {
                System.out.println("The Starship crashed into alien Ship!");
                panel.write('@', spx, spy, Color.red, Color.black);
                hitsIndicator = 10;
                player1.takenDamage(1);
                System.out.println("Health: " + player1.getHealth());
            } else if (player1.getHealth() <= -1) {
                System.out.printf("Your ship sustained total damage and you died in the crash");
                System.exit(0);
            }
        }

        // check for starship/bullets collision  "Ascii *" (Hits 1 HP)
        for (Weapon ablt : alienBullets) {
            if (player1.getxPos() == ablt.getX() && player1.getyPos() == ablt.getY()) {
                System.out.println("Starship has cruised into an alien Bullet!");
                panel.write('@', spx, spy, Color.red, Color.black);
                hitsIndicator = 10;
                player1.takenDamage(1);
                System.out.println("Health: " + player1.getHealth());
            } else if (player1.getHealth() <= 0) {
                System.out.printf("Your ship sustained total damage and you died in the crash");
                System.exit(0);
            }
        }

        // draw the bullets array
        if(bullets.size() > 0) {
            for(Weapon bullet: bullets) {
                panel.write('=', bullet.getX(), bullet.getY(), Color.green, Color.black);
            }
        }

        // move each bullet faster than alienSpd
        int bulletSpd = 18;
        if(updateMonsters >= bulletSpd) {
            floatMyBullets();
            removeMyBullets();
        }

        // draw planets that were passed in from game class
        for(Planet planet: bodies) {
            panel.write(planet.getSymbol(), planet.getX(), planet.getY(), planet.getColor(), Color.black);
        }

        // instantiate enemy bullets every time duration
        int delayEnemyAttack = 50;
        if(updateAttacks >= delayEnemyAttack) {
            attack();
            updateAttacks = 0;
        }

        // move each alien shot
        int alienBulletSpd = 18;
        if(updateMonsters >= alienBulletSpd) {
            floatTheirBullets();
            removeTheirBullets();
        }

        // draw the alienBullets
        for(Weapon ablt: alienBullets) {
            panel.write('+', ablt.getX()-1, ablt.getY(), Color.magenta, Color.black);

        }

        // check to see if the player is directly on a planet
        for(Planet planet: bodies) {
            if(player1.getxPos() == planet.getX() && player1.getyPos() == planet.getY()) {
                player1.setCurrentLocation(planet);
                player1.setInSpace(false);
            } else {
                player1.setInSpace(true);
            }
        }

        //the distance from the left(x) and top(y) to begin writing from






        // draw the starship
        if ((spx >= 0 && spx < gameScreenRec.width) && (spy >= 0 && spy < gameScreenRec.height) && hitsIndicator <= 0) {
            panel.write('@', spx, spy, Color.cyan, Color.black);
        }
        else if ((spx >= 0 && spx < gameScreenRec.width) && (spy >= 0 && spy < gameScreenRec.height) && hitsIndicator > 0) {
            panel.write('@', spx, spy, Color.red, Color.black);
        }
    }

    public void drawAsteroids() {
        int x = 79;
        for(int i = 23; i > 0; i = i - 3) {
            asteroids.add(new Asteroid("large", x, i));
            x -= 4;
        }
    }

    public void floatAsteroids() {
            for(Asteroid asteroid: asteroids) {
                asteroid.setX(asteroid.getX()-1);
                if(asteroid.getX() == (0)) {
                    asteroid.setX(79);
                }
            }
            updateMonsters = 0;
    }

    public void drawAliens() {
        int x = 75;
        for(int i = 3; i < 24; i = i + 3) {
            aliens.add(new Alien("right", x, i));
            x -= 3;
        }
    }

    public void floatAliens() {
        for(Alien alien: aliens) {
            alien.setX(alien.getX()-1);
            if(alien.getX() <= (0)) {
                alien.setX(79);
            }
        }
    }

    // put new Weapon in a list
    public void drawMyBullets(int x, int y){
        bullets.add(new Weapon(x, y));
    }

    public void floatMyBullets() {
        for(Weapon bullet : bullets) {
            bullet.setX(bullet.getX()+1);
        }
    }

    public void removeMyBullets(){
        Iterator<Weapon> i = bullets.iterator();
        while(i.hasNext()) {
            Weapon bullet = i.next();

            //check for if a monster was shot by bullet
            monsterShot(bullet);

            // remove bullet once its offscreen
            if(bullet.getX() >= (79)) {
                i.remove();
            }
        }
    }

    // place the planets on the board here
    public void drawPlanets() {
        bodies.add(new Planet("Earth", new ArrayList<>(Arrays.asList("water", "food")), 10, 16, Color.cyan, 'E'));
        bodies.add(new Planet("Moon", new ArrayList<>(Arrays.asList("fuel", "Elon Musk", "weapon")), 13, 11, Color.LIGHT_GRAY, 'm'));
        bodies.add(new Planet("Venus", new ArrayList<>(Arrays.asList("fuel", "scrap metal")), 6, 20, Color.pink, 'V'));
        bodies.add(new Planet("Mercury", new ArrayList<>(Arrays.asList("super laser", "shield")), 4, 22, Color.yellow, 'M'));
        bodies.add(new Planet("Mars", new ArrayList<>(), 70, 3, Color.orange, 'M'));
    }

    // remove monster if they were shot by me
    public void monsterShot(Weapon bullet){
        aliens.removeIf(alien -> alien.getX() == bullet.getX() && alien.getY() == bullet.getY());
        asteroids.removeIf(asteroid -> asteroid.getX() == bullet.getX() && asteroid.getY() == bullet.getY());
    }

    public void attack() {
        for(Alien alien: aliens) {
            alienBullets.add(new Weapon(alien.getX(), alien.getY()));
        }
    }

    public void floatTheirBullets(){
        for(Weapon ablt: alienBullets) {
            ablt.setX(ablt.getX() - 1);
        }
    }

    public void removeTheirBullets(){
        //check for if starship was shot by alien bullet
        //            starshipShot(ablt);
        // remove bullet once its offscreen
        alienBullets.removeIf(ablt -> ablt.getX() <= (0));
    }

//    public void pickUp(Starship starship){
//        Planet p = starship.getCurrentLocation();
//        String r = p.getResources().get(0);
//        System.out.println("x");
//        if(!starship.inSpace) {
//            System.out.println("You are here: " + p.getName());
//            starship.getInventory().addAll(p.getResources());
//            System.out.println("You picked up: " + starship.getInventory());
//        }
//    }

    @Override
    public void keyPressed(KeyEvent e) {
        inputQueue.add(e);
    }


    public InputEvent getNextInput() {
        if(!inputQueue.isEmpty())
            return inputQueue.poll();
        else
            return null;
    }

    public void refresh() {
        updateMonsters++;
        updateAttacks++;
        hitsIndicator--;
        panel.repaint();
    }

    @Override
    public void keyReleased(KeyEvent e) {}

    @Override
    public void keyTyped(KeyEvent e) {}

    @Override
    public void mouseClicked(MouseEvent e) {
        inputQueue.add(e);
    }

    @Override
    public void mouseEntered(MouseEvent e) {}

    @Override
    public void mouseExited(MouseEvent e) {}

    @Override
    public void mousePressed(MouseEvent e) {}

    @Override
    public void mouseReleased(MouseEvent e) {}
}