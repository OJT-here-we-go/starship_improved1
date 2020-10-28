package com.games.game;

import com.games.maps.MapPanelGenerator;
import com.games.maps.Tile;
import com.games.pieces.Starship;

public final class InteractionsUtil {
    //private constructor to prevent instantiation
    private InteractionsUtil(){

    }

    public static boolean checkRunIntoObjectUp(Starship starship, OutputGui output) {
        char northTile = starship.getCurrentLocation().getTileChar(starship.getPlanetXPos(), starship.getPlanetYPos()-1);
        output.setPlayerMessage();
        switch (northTile) {
            case '#':
                System.out.println("Thats a wall, you shall not pass!");
                return false;
            case '.':
                return true;
            default:
                for (Tile tile : Tile.values()) {
                    if (tile.symbol() == northTile) {
                        System.out.println("It's a " + tile.name() + "! Press x to interact!");
                    }
                }
                return false;
        }
    }

    public static boolean checkRunIntoObjectDown(Starship starship, OutputGui output) {
        char northTile = starship.getCurrentLocation().getTileChar(starship.getPlanetXPos(), starship.getPlanetYPos()+1);
        output.setPlayerMessage();
        switch (northTile) {
            case '#':
                System.out.println("Thats a wall, you shall not pass!");
                return false;

        }
        output.setDefaultSysOut();
        return true;
    }

    public static boolean checkRunIntoObjectLeft(Starship starship, OutputGui output) {
        char northTile = starship.getCurrentLocation().getTileChar(starship.getPlanetXPos()-1, starship.getPlanetYPos());
        output.setPlayerMessage();
        switch (northTile) {
            case '#':
                System.out.println("Thats a wall, you shall not pass!");
                return false;

        }
        output.setDefaultSysOut();
        return true;
    }

    public static boolean checkRunIntoObjectRight(Starship starship, OutputGui output) {
        char northTile = starship.getCurrentLocation().getTileChar(starship.getPlanetXPos()+1, starship.getPlanetYPos());
        output.setPlayerMessage();
        switch (northTile) {
            case '#':
                System.out.println("Thats a wall, you shall not pass!");
                return false;

        }
        output.setDefaultSysOut();
        return true;
    }
}
