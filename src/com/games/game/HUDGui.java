package com.games.game;

import com.games.pieces.GameArea;
import com.games.pieces.Planet;
import com.games.pieces.Player;
import com.games.pieces.Starship;

import javax.swing.*;
import java.awt.*;

public class HUDGui extends JPanel {
    private Starship starship;
    private Player player;
    private Output output;
    private JPanel hudPanel;
    private JLabel currentMap = new JLabel("Current Map:");
    private JLabel map;
    private JLabel currentHealth = new JLabel("Current health:");
    private JLabel health;
    private JLabel currentPowerUps = new JLabel("Current Power Ups:");
    private JLabel powerUps;
    private JLabel enemiesDefeated = new JLabel("Enemies Defeated:");
    private JLabel defeated;
    // initialize table


    public HUDGui(GameArea parentWindow, Starship starship, Player player, Output output) {
        this.starship = starship;
        this.player = player;
        this.output = output;
        this.hudPanel = new JPanel();

        this.hudPanel.setLayout(new BoxLayout(hudPanel, BoxLayout.PAGE_AXIS));
        //map
        hudPanel.add(currentMap);
        this.map = new JLabel(starship.getCurrentLocation().getName());
        hudPanel.add(this.map);
        //health
        hudPanel.add(currentHealth);
        this.health = new JLabel(Integer.toString(starship.getHealth()));
        hudPanel.add(this.health);
        //power ups
        hudPanel.add(currentPowerUps);
        this.powerUps = new JLabel(player.getInventory().toString()); //NEED TO MAKE THE TO STRING
        hudPanel.add(this.powerUps);
        //enemies defeated
        hudPanel.add(enemiesDefeated);
        this.defeated = new JLabel("placeholder, need to fill");
        hudPanel.add(defeated);

        //jscroll panel
        JScrollPane msgDisplay = new JScrollPane();


        parentWindow.getContentPane().add(hudPanel, BorderLayout.LINE_END);
        parentWindow.revalidate();
        parentWindow.repaint();
    }

    public void display(Planet currentLocation) {
        System.out.println("++---------------------------++-----------------------------++-------------------------------------++");
        System.out.println("||  Player inventory: " + player.getInventory() +"     ||    Starship health: " + starship.getHealth() + "     ||    Fuel level: " + starship.getFuel() + "                  ||");
        System.out.println("++---------------------------++-----------------------------++-------------------------------------++");
        System.out.println("+----------------------------++-----------------------------++-------------------------------------++");
        System.out.println("||  Current location: " + currentLocation.getName() + "  ||          HAL 9000           ||  Directions: left, right, up, down  ||" );
        System.out.println("||                           ||            ((0))            ++-------------------------------------||");
        System.out.print("||                           ||     ");
        System.out.print(output.getPrompt1());
        System.out.println("  ||      "+ output.getprompt2() + "     ||");
        System.out.println("||---------------------------++-----------------------------++-------------------------------------++");
        System.out.println("++---------------------------++--------------------------------------------------------------------++");
        System.out.println("||  Controls: go, take, use  ||  Resources found:" + currentLocation.getResources() + "                                     ||");
        System.out.println("++---------------------------++--------------------------------------------------------------------++");
    }

    public static void prompt1(String prompt1) {
        Output.setPrompt1(prompt1);
    }

    public static void prompt2(String prompt2) {
        Output.setPrompt2(prompt2);
    }

    public Starship getStarship() {
        return starship;
    }

    public void setStarship(Starship starship) {
        this.starship = starship;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public Output getOutput() {
        return output;
    }

    public void setOutput(Output output) {
        this.output = output;
    }
}
