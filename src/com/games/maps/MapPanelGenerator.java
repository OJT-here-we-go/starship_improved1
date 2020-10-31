package com.games.maps;

import com.games.pieces.Starship;


import javax.sound.sampled.LineUnavailableException;
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.FileNotFoundException;

/**
 * Created by bradsmialek on Sun - 8/9/20 @ 2:40 PM
 */
public class MapPanelGenerator extends JPanel
//        implements KeyListener
{
    public Starship starship;
    private Rectangle gameScreenRec;

    public MapPanelGenerator(Starship starship) {
        this.starship = starship;
//        addKeyListener(this);
        this.setFocusable(true);

    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        repaint();
        revalidate();

        //BACKGROUND
        g.fillRect(0, 0, 700, 500);
        g.setColor(Color.orange);
        //planet
        try {

            int x = 15, y = 20;
            for (int i = 0; i < starship.getCurrentLocation().getHeight(); i++) {
                for (int j = 0; j < starship.getCurrentLocation().getWidth(); j++) {

                    if (starship.getCurrentLocation().getTileChar(j, i) == '@') {
                        g.setColor(Color.MAGENTA);
                        g.drawString("" + starship.getCurrentLocation().getTileChar(j, i), x, y);
                        starship.setPlanetXPos(j);
                        starship.setPlanetYPos(i);
                        g.setColor(Color.orange);
                    }else if (starship.getCurrentLocation().getTileChar(j, i) == '+') {
                        g.setColor(Color.lightGray);
                        g.drawString("" + starship.getCurrentLocation().getTileChar(j, i), x, y);
                        g.setColor(Color.orange);
                    } else if (starship.getCurrentLocation().getTileChar(j, i) == 'p') {
                        g.setColor(Color.red);
                        g.drawString("" + starship.getCurrentLocation().getTileChar(j, i), x, y);
                        g.setColor(Color.orange);
                    }else if (starship.getCurrentLocation().getTileChar(j, i) == 'f') {
                        g.setColor(Color.green);
                        g.drawString("" + starship.getCurrentLocation().getTileChar(j, i), x, y);
                        g.setColor(Color.orange);
                    }else if (starship.getCurrentLocation().getTileChar(j, i) == '.') {
                        g.setColor(Color.darkGray);
                        g.drawString("" + starship.getCurrentLocation().getTileChar(j, i), x, y);
                        g.setColor(Color.orange);
                    } else if (starship.getCurrentLocation().getTileChar(j, i) == '~') {
                        g.setColor(Color.blue);
                        g.drawString("" + starship.getCurrentLocation().getTileChar(j, i), x, y);
                        g.setColor(Color.orange);
                    } else if (starship.getCurrentLocation().getTileChar(j, i) == '#') {
                        g.setColor(Color.pink);
                        g.drawString("" + starship.getCurrentLocation().getTileChar(j, i), x, y);
                        g.setColor(Color.orange);
                    }else {
                        g.setColor(Color.orange);
                        g.drawString("" + starship.getCurrentLocation().getTileChar(j, i), x, y);
                    }

                    x += 10;
                }
                y += 15;
                x = 15;
            }
        } catch (Exception paintIslandError) {
            System.out.println("Something went wrong while we were painting the island");
        }


//        //STATS
//        try {
//            g.setFont(new Font("arial", Font.PLAIN, 20));
//            g.setColor(Color.cyan);
//            g.drawString("Player: ", 800, 50); //TODO  ask user name and use it    +Attributes.player.getName()
//            g.setFont(new Font("arial", Font.PLAIN, 20));
//            g.drawString("HP: " + starship.player.getHealth() + "/" + starship.player.getMaxHealth(), 800, 80);
//            g.drawString("XP: " + starship.player.getXP() + "/" + starship.player.getNextLevel(), 800, 110);
//            g.drawString("Gold: " + starship.player.getsGold(), 800, 140);
//            g.drawString("Keys: " + starship.player.getsKeys(), 800, 170);
//
//            g.drawString("Char Level: " + starship.player.getsLevel(), 800, 225);
//            g.drawString("Current Location: " + Island.getIslandName(Island.getIslandNumber()), 800, 285);
//
//            g.drawString("Special Items: ", 1200, 50);
//            g.drawString("Keys: "+ starship.player.getSpecialKeys()+"/3", 1200, 90);
//            g.drawString("Emerald: "+starship.player.getEmerald()+"/1", 1200, 120);
//            g.drawString("Maps: "+starship.player.getSpecialMaps()+"/4", 1200, 150);
//
//
//        } catch (Exception drawStatsError) {
//            System.out.println("Something went wrong while we were drawing Player stats");
//        }
//
//        //Message
//        try {
//
//            g.setColor(Color.lightGray);
//            g.setFont(new Font("arial", Font.PLAIN, 35));
//            g.drawString(Methods.getMessage(), 15, 700);
//            g.drawString(Methods.getMessage2(), 15, 750);
//            g.drawString(Methods.getMessage3(), 15, 800);
//            g.drawString(Methods.getMessage4(), 15, 850);
//            g.drawString(Methods.getMessage5(), 15, 900);
//            g.drawString(Methods.getMessage6(), 15, 950);
//
//        } catch (Exception drawMessageException) {
//            System.out.println("Something went wrong while we were drawing your message board");
//        }
    }


    public void pointCameraAt(Starship player1) throws FileNotFoundException, LineUnavailableException {

        int spx = player1.getxPos();
        int spy = player1.getyPos();
        //panel.write('@', spx, spy, Color.cyan, Color.black);
    }


//
//    @Override
//    public void keyPressed(KeyEvent arg0) {
//        if (starship.getHealth()>0) {
//
//        try {
//            switch (arg0.getKeyCode()) {
//                case KeyEvent.VK_W:
//                case KeyEvent.VK_UP:
//                    starship.movePlanet(0, -1);
//                    break;
//                case KeyEvent.VK_A:
//                case KeyEvent.VK_LEFT:
//                    starship.movePlanet(-1, 0);
//                    break;
//                case KeyEvent.VK_S:
//                case KeyEvent.VK_DOWN:
//                    starship.movePlanet(0, 1);
//
//                    break;
//                case KeyEvent.VK_D:
//                case KeyEvent.VK_RIGHT:
//                    starship.movePlanet(1, 0);
//                    break;
//            }
//        }
//        catch (Exception e) {
//            e.printStackTrace();
//            System.out.println("Player input error");
//        }
//        } else {
//        }
//    }


//
//    @Override
//    public void keyReleased(KeyEvent arg0) {
//    }
//
//    @Override
//    public void keyTyped(KeyEvent arg0) {
//    }

}
