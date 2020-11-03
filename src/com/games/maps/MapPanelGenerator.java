package com.games.maps;

import com.games.pieces.Starship;

import javax.swing.*;
import java.awt.*;

public class MapPanelGenerator extends JPanel {
    public Starship starship;

    public MapPanelGenerator(Starship starship) {
        this.starship = starship;
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
                        g.setColor(Color.CYAN);
                        g.drawString("" + starship.getCurrentLocation().getTileChar(j, i), x, y);
                        starship.setPlanetXPos(j);
                        starship.setPlanetYPos(i);
                        g.setColor(Color.orange);
                    } else if (starship.getCurrentLocation().getTileChar(j, i) == '*') {
                        g.setColor(Color.red);
                        g.drawString("" + starship.getCurrentLocation().getTileChar(j, i), x, y);
                        g.setColor(Color.orange);
                    }else if (starship.getCurrentLocation().getTileChar(j, i) == 'i') {
                        g.setColor(Color.white);
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
            System.out.println("Something went wrong while we were painting the planet");
        }
    }
}
