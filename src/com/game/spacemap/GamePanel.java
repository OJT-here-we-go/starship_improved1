package com.game.spacemap;

import com.games.game.Game;
import com.games.pieces.Player;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;
import java.awt.Graphics;

public class GamePanel extends javax.swing.JPanel implements ActionListener {

    Rocket player;
    ArrayList<Wall> walls = new ArrayList<>();
    ArrayList<Obstacle1> asteroids = new ArrayList<>();

    int cameraX;
    int offset;

    Timer gameTimer;

    public GamePanel() {
        player = new Rocket(60, 250, this);
        //start the game, or restart it
        reset();

        gameTimer = new Timer();
        gameTimer.schedule(new TimerTask() {
            @Override
            public void run() {
                //check if rocket has moved +y direction to spawn the path


                player.set();
                for(Wall wall: walls) wall.set(cameraX);
                // remove walls as they move left offscreen
                for(int i = 0; i < walls.size(); i++) {
                    if(walls.get(i).x < -800) walls.remove(i);
                }
                for(int i = 0; i < asteroids.size(); i++) {

                }

                // should have repaint at end of this run method
                repaint();
            }
        }, 0, 17);
    }
    // restart the game and position all the pieces
    public void reset() {
        player.x = 70;
        player.y = 250;
        cameraX = 0;
        player.xspeed = 0;
        player.yspeed = 0;
        walls.clear();
        int offset = -100;
        makeWalls(offset);
    }
    // create earth wall
    public void makeWalls(int offset) {
        //width of walls are normally s
        int s = 50;

        // create one big wall instead of pixelized variably-positioned walls
//        for(int i = 50; i < 650; i += 50) {
//            walls.add(new Wall(i, 600, 50, 50));
//        }
        // Earth wall
        walls.add(new Wall(-25, 0, 60, 1000));
        Random rand = new Random();
        int index = rand.nextInt(1);


    }
    // place all the pieces on the game panel
    public void paint(Graphics g) {
        super.paint(g);

        Graphics2D gtd = (Graphics2D) g;

        player.draw(gtd);
        for(Wall wall : walls) wall.draw(gtd);
    }



    // if char key is pressed, set corresponding direction to true
    void keyPressed(KeyEvent e) {
        if(e.getKeyChar() == 'a') player.keyLeft = true;
        if(e.getKeyChar() == 'w') player.keyUp = true;
        if(e.getKeyChar() == 's') player.keyDown = true;
        if(e.getKeyChar() == 'd') player.keyRight = true;
    }
    // if char key is released, set corresponding direction to false
    void keyReleased(KeyEvent e) {
        if(e.getKeyChar() == 'a') player.keyLeft = false;
        if(e.getKeyChar() == 'w') player.keyUp = false;
        if(e.getKeyChar() == 's') player.keyDown = false;
        if(e.getKeyChar() == 'd') player.keyRight = false;
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {

    }
}
