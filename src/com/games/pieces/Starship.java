package com.games.pieces;

import java.util.ArrayList;

public class Starship {
    private ArrayList<String> inventory = new ArrayList<>();
    private int health = 100;
    private int fuel = 100;
    private int damage = 20;
    private int fuelUsed = 10;
    public boolean inSpace = true;
    public Planet currentLocation;
    public String currentAsteroids;
    private boolean playerCanUseShield = false;
    public int xPos, yPos;
    public int planetXPos, planetYPos;
    private int enemiesDefeated;

    public Starship(Planet currentLocation, int xPos, int yPos){
        setCurrentLocation(currentLocation);
        setxPos(xPos);
        setyPos(yPos);
    }

    // Business methods
    public void moveSpace(int dx, int dy)
    {
        xPos += dx;
        yPos += dy;
    }

    public void movePlanet(int dx, int dy)
    {
        planetXPos += dx;
        planetYPos += dy;
    }

    public void takenDamage(int damage){
        setHealth(getHealth() - damage);
    }

    public void burnFuel(){
        setFuel(getFuel() - fuelUsed);
    }

    public void refuel(){
        setFuel(getFuel() + (100-getFuel()));
    }

    public boolean pickUp(GameArea gameArea, ArrayList<Planet> planets) {
        for(Planet planet : planets) {
            if(getxPos() == planet.getX() && getyPos() == planet.getY() && planet.getResources().size()>0) {
                gameArea.getOutput().setPlayerMessage();
                System.out.println("You made it to " + planet.getName() + "!");
                ArrayList<String> planetsResources = planet.getResources();
                inventory.add(planetsResources.get(0));
                planetsResources.remove(0);
                System.out.println("Inventory: " + inventory);
                gameArea.getOutput().setDefaultSysOut();
                return true;
            }
        }
        return false;
    }


    public void addDefeated() {
        this.enemiesDefeated++;
    }

    public int getEnemiesDefeated(){
        return enemiesDefeated;
    }
    //Getters and Setters
    public int getxPos() {
        return xPos;
    }

    public void setxPos(int xPos) {
        this.xPos = xPos;
    }

    public int getyPos() {
        return yPos;
    }

    public void setyPos(int yPos) {
        this.yPos = yPos;
    }

    public int getPlanetXPos() {
        return planetXPos;
    }

    public void setPlanetXPos(int planetXPos) {
        this.planetXPos = planetXPos;
    }

    public int getPlanetYPos() {
        return planetYPos;
    }

    public void setPlanetYPos(int planetYPos) {
        this.planetYPos = planetYPos;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public int getFuel() {
        return fuel;
    }

    public void setFuel(int fuel) {
        this.fuel = fuel;
    }

    public int getDamage(){
        return damage;
    }

    public void setInSpace(boolean inSpace) {
        this.inSpace = inSpace;
    }

    public Planet getCurrentLocation() {
        return currentLocation;
    }

    public void setCurrentLocation(Planet location) {
        this.currentLocation = location;
    }

    public ArrayList<String> getInventory() {
        return inventory;
    }

    public void setInventory(ArrayList<String> inventory) {
        this.inventory = inventory;
    }

    public boolean getPlayerCanUseShield() {
        return playerCanUseShield;
    }

    public void setPlayerCanUseShield(boolean playerCanUseShield) {
        this.playerCanUseShield = playerCanUseShield;
    }

}
