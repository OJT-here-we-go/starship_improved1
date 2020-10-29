package com.games.game;

import com.games.maps.MapPanelGenerator;
import com.games.maps.Tile;
import com.games.pieces.Direction;
import com.games.pieces.Starship;

import java.sql.SQLOutput;

public final class InteractionsUtil {
    private static Tile interactable;

    //private constructor to prevent instantiation
    private InteractionsUtil(){

    }
//
//    public static boolean checkRunIntoObjectUp(Starship starship, OutputGui output) {
//        char northTile = starship.getCurrentLocation().getTileChar(starship.getPlanetXPos(), starship.getPlanetYPos()-1);
//        output.setPlayerMessage();
//        switch (northTile) {
//            case '#':
//                System.out.println("Thats a wall, you shall not pass!");
//                return false;
//            case '.':
//                return true;
//            default:
//                for (Tile tile : Tile.values()) {
//                    if (tile.symbol() == northTile) {
//                        System.out.println("It's a " + tile.name() + "! Press x to interact!");
//                    }
//                }
//                return false;
//        }
//    }
//
//    public static boolean checkRunIntoObjectDown(Starship starship, OutputGui output) {
//        char northTile = starship.getCurrentLocation().getTileChar(starship.getPlanetXPos(), starship.getPlanetYPos()+1);
//        output.setPlayerMessage();
//        switch (northTile) {
//            case '#':
//                System.out.println("Thats a wall, you shall not pass!");
//                return false;
//
//        }
//        output.setDefaultSysOut();
//        return true;
//    }
//
//    public static boolean checkRunIntoObjectLeft(Starship starship, OutputGui output) {
//        char northTile = starship.getCurrentLocation().getTileChar(starship.getPlanetXPos()-1, starship.getPlanetYPos());
//        output.setPlayerMessage();
//        switch (northTile) {
//            case '#':
//                System.out.println("Thats a wall, you shall not pass!");
//                return false;
//
//        }
//        output.setDefaultSysOut();
//        return true;
//    }
//
//    public static boolean checkRunIntoObjectRight(Starship starship, OutputGui output) {
//        char northTile = starship.getCurrentLocation().getTileChar(starship.getPlanetXPos()+1, starship.getPlanetYPos());
//        output.setPlayerMessage();
//        switch (northTile) {
//            case '#':
//                System.out.println("Thats a wall, you shall not pass!");
//                return false;
//
//        }
//        output.setDefaultSysOut();
//        return true;
//    }

    public static void playerHandler(OutputGui output, Starship starship, Direction d) {

        Tile tile = null;

        switch(d) {
            case UP:
                tile = starship.getCurrentLocation().getTile(starship.getPlanetXPos(), starship.getPlanetYPos()-1);
                break;
            case LEFT:
                tile = starship.getCurrentLocation().getTile(starship.getPlanetXPos()-1, starship.getPlanetYPos());
                break;
            case DOWN:
                tile = starship.getCurrentLocation().getTile(starship.getPlanetXPos(), starship.getPlanetYPos()+1);
                break;
            case RIGHT:
                tile = starship.getCurrentLocation().getTile(starship.getPlanetXPos()+1, starship.getPlanetYPos());
                break;
        }

        output.setPlayerMessage();

        //Handles the player movement
        switch(tile){
            case NOTHING:

                starship.movePlanet(d);
                interactable = Tile.NOTHING;
                break; //Move the player if it is in front of one of these tiles
            case WALL:
                output.setHitsMessage();
                System.out.println("That's a wall, you shall not pass!");
                interactable = Tile.WALL;
                output.setDefaultSysOut();
                break;
            case SPACEDOCK:
                System.out.println("---------------------------------------------------------");
                System.out.println("Do you want to return to your Ship?");
                System.out.println("   [Y] Yes     [N] No");
                interactable = tile;
                break; //return to space
            case FOOD:
                System.out.println("---------------------------------------------------------");
                starship.movePlanet(d);
                starship.addItem("Food");
                starship.getCurrentLocation().posUpdate();
                System.out.println("You picked some food! You'd think it's for eating right?");
                break; //Adds a key
            case KEY:
                System.out.println("---------------------------------------------------------");
                starship.movePlanet(d);
                starship.addItem("Key");
                starship.getCurrentLocation().posUpdate();
                System.out.println("You picked up a Key! Maybe it unlocks a door?");
                break; //Adds a key
            case DOOR:
                System.out.println("---------------------------------------------------------");
                System.out.println("This door is locked. Do you want to use a key and open it?");
                System.out.println("   [Y] Yes     [N] No");
                interactable = tile;
                break; //Ask to open door
            case ELON:
                System.out.println("---------------------------------------------------------");
                System.out.println("Elon: Hey there, I'm Elon Musk... We should Colonize Mars to extend humanity into a multi-planetary species!");
                System.out.println("Elon: This way... we'll drastically reduce the chances of extinction if something catastrophic happens...");
                System.out.println("Do you want to Kidna- Take Elon Musk with you?");
                System.out.println("   [Y] Yes     [N] No");
                interactable = tile;
                break;
            case A12:
                System.out.println("---------------------------------------------------------");
                System.out.println("X Æ A-12: Where did daddy go... :'<");

            default:
                output.setHitsMessage();
                System.out.println("---------------------------------------------------------");
                System.out.println("That's a " + tile + "... it hasn't been implemented yet :<");
                output.setDefaultSysOut();
        }
        output.setDefaultSysOut();
    }

    public static boolean decisionTree(Starship starship, OutputGui output, boolean yesNo) {
        output.setPlayerMessage();
        System.out.println("---------------------------------------------------------");
        if (interactable == Tile.SPACEDOCK) {
            if (yesNo) {
                System.out.println("Returning to Space!!");
                output.setDefaultSysOut();
                return true;
            }
            else {
                System.out.println("Alright... Staying here, just keep moving!");
            }
        }

        else if (interactable == Tile.DOOR) {
            if (yesNo) {
                System.out.println("You try to use a key to open the door!");
                if (starship.getInventory().contains("Key")) {
                    starship.getInventory().remove("Key");
                    System.out.println("You used the key in your inventory to unlock the door!");
                    starship.getCurrentLocation().setTileWithFacing(Tile.NOTHING); // sets the door to Tile.Nothing
                }
                else {
                    System.out.println("You don't have a key in your inventory!");
                }
            }
            else {
                System.out.println("Ok then... carry on.");
            }
        }

        else if (interactable == Tile.ELON){
            if (yesNo){
                System.out.println("Elon: Wait... whats that rope for... Wait! Wai-");
                System.out.println("Elon has been added to your party!");
                starship.addItem("Elon Musk");
                starship.getCurrentLocation().setTileWithFacing(Tile.A12);
            }
            else {
                System.out.println("Ok, I guess humanity will perish after the next asteroid or alien attack.");
            }
        }


        output.setDefaultSysOut();
        return false;
    }

}
