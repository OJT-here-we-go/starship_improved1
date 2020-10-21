package com.game.spacemap;

//import javafx.scene.shape.Circle;
//import org.w3c.dom.css.Rect;
import java.awt.Graphics2D;
import java.awt.Color;

import java.awt.*;

public class Wall {

    int x;
    int y;
    int width;
    int height;

    int startX;
    //overall shape of the wall
    Rectangle hitBox;
    public Wall(int x, int y, int width, int height){

        this.x = x;
        startX = x;
        this.y = y;
        this.width= width;
        this.height = height;

        hitBox = new Rectangle(x, y, width, height);
    }

    public void draw(Graphics2D gtd) {
        gtd.setColor(Color.BLUE);
        gtd.drawRect(x, y, width, height);
        gtd.setColor(Color.GREEN);
        gtd.fillRect(x + 1, y + 1, width - 2, height - 2);
    }

    // move the walls and not the rocket
    public int set(int cameraX){
        x = startX + cameraX;
        hitBox.x = x;

        return x;
    }
}
