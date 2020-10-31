package com.games.game;

import com.games.maps.MapPanelGenerator;
import com.games.maps.Tile;
import com.games.pieces.Direction;
import com.games.pieces.Starship;

import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public final class InteractionsUtil {
    private static Tile interactable;
    private static List<String> dialogFriendly = new ArrayList<>();
    private static List<String> astroFriendly = new ArrayList<>();
    private static Random rand = new Random();
    static {
        dialogFriendly.add("Patron: Where is your mask? PREVENT THE SPREAD OF CVD-19!");
        dialogFriendly.add("Patron: Stay 6 feet back you heathen!");
        dialogFriendly.add("Patron: I miss the days we didn't have to social distance...");
        dialogFriendly.add("Patron: I'm supporting this Cafe by eating here.");
        dialogFriendly.add("Patron: Man... the tables are only at half capacity... CVD-19 sucks.");
        dialogFriendly.add("Patron: WHY AM I GREEN. ARE WE IN A GAME? AHHHHHHHHHHHHHHHHHHHHH!");
        astroFriendly.add("Astronaut: Man... Earth has really gone to **** with CVD-19 hun...?");
        astroFriendly.add("Astronaut: GLORY TO 'MURICA. I'M NOT DRUNK, YOU'RE DRUNK!");
        astroFriendly.add("Astronaut: All the liquids taste like urine up here... :(");
        astroFriendly.add("Astronaut: 2 more years before I can go back home... *hic*...");
        astroFriendly.add("Astronaut: ALIENS! *JAZZ HANDS*");
        astroFriendly.add("Astronaut: I can bench press 415LBs up here, it's dope!");
    }

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
                interactable = Tile.NOTHING;
                break;
            case WATER:
                output.setHitsMessage();
                System.out.println("That's water! You can't swim!");
                output.setPlayerMessage();
                if (!starship.getInventory().contains("Fishing Rod")) {
                    System.out.println("---------------------------------------------------------");
                    System.out.println("Maybe I can fish here if I have a fishing rod?");
                }
                else {
                    System.out.println("---------------------------------------------------------");
                    System.out.println("Do you want to fish with your fishing rod?");
                    System.out.println("   [Y] Yes     [N] No");
                }
                interactable = tile;
                break;
            case SPACEDOCK:
                System.out.println("---------------------------------------------------------");
                System.out.println("Do you want to return to your Ship?");
                System.out.println("   [Y] Yes     [N] No");
                interactable = tile;
                break; //return to space
            case FRIENDLY:
                System.out.println("---------------------------------------------------------");
                System.out.println(dialogFriendly.get(rand.nextInt(dialogFriendly.size())));
                interactable = Tile.NOTHING;
                break; //random dialog for friendlies
            case PLUS:
                output.setHitsMessage();
                System.out.println("That's a table. Please stop running into it.");
                System.out.println("You take 5 damage in embarrassment.");
                starship.takenDamage(5);
                interactable = Tile.NOTHING;
                break;
            case ART:
                System.out.println("---------------------------------------------------------");
                System.out.println("Some fine art! Looks super expensive... Better not brea-");
                output.setHitsMessage();
                System.out.println("You broke the art... Grimes slaps you in the face.");
                System.out.println("You take 20 damage rather than paying for it.");
                starship.getCurrentLocation().setTileWithFacing(Tile.NOTHING);
                starship.takenDamage(20);
                interactable = Tile.NOTHING;
                break;
            case ROD:
                System.out.println("---------------------------------------------------------");
                starship.movePlanet(d);
                starship.addItem("Fishing Rod");
                starship.getCurrentLocation().posUpdate();
                System.out.println("You picked up a fishing rod! Time to find some water!");
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
            case CHEF:
                System.out.println("---------------------------------------------------------");
                System.out.println("Chef: Tough times eh friend?");
                System.out.println("Chef: I'll trade you some food if you got any fish");
                System.out.println("Trade your fish for some food rations?");
                System.out.println("   [Y] Yes     [N] No");
                interactable = tile;
                break;
            case GRIMES:
                System.out.println("---------------------------------------------------------");
                System.out.println("Grimes: WHAT ARE YOU DOING IN MY HOUSE?");
                System.out.println("Grimes: Hun? Looking for my sweet baby Elon? He's on the moon.");
                System.out.println("Grimes: Your princess is in another castle");
                interactable = Tile.NOTHING;
                break;
            case ORE:
                System.out.println("---------------------------------------------------------");
                System.out.println("Looks like it's some moon ore?");
                System.out.println("Maybe the miner will give me a pickaxe!");
                interactable = Tile.NOTHING;
                break;

            case MINER:
                System.out.println("---------------------------------------------------------");
                System.out.println("Miner: Do you see how glorious my mined ore are?");
                System.out.println("Miner: Take my pickaxe Bethany and give it a whirl!");
                System.out.println("You received a pickaxe (by force)!");
                starship.addItem("Pickaxe");
                starship.getCurrentLocation().setTileWithFacing(Tile.MINED);
                interactable = Tile.NOTHING;
                break;
            case MINED:
                System.out.println("---------------------------------------------------------");
                System.out.println("Miner: Treat Bethany well...");
                System.out.println("He seems a little defeated after giving you his prized pickaxe");
                interactable = Tile.NOTHING;
                break;
            case FLAG:
                System.out.println("---------------------------------------------------------");
                System.out.println("IT'S THE AMERICAN FLAG!");
                System.out.println("We need this for when we colonize Mars!");
                System.out.println("The nearby astronauts are eyeing you...");
                System.out.println("Take the Flag and risk getting beaten up by Astronauts?");
                System.out.println("   [Y] Yes     [N] No");
                interactable = tile;
                break;
            case ASTRO:
                System.out.println("---------------------------------------------------------");
                System.out.println(astroFriendly.get(rand.nextInt(astroFriendly.size())));
                interactable = Tile.NOTHING;
                break;
            case ELON:
                System.out.println("---------------------------------------------------------");
                System.out.println("Elon: Hey there, I'm Elon Musk... We should Colonize Mars to extend humanity into a multi-planetary species!");
                System.out.println("Elon: This way... we'll drastically reduce the chances of extinction if something catastrophic happens...");
                System.out.println("Do you want to Kidna- Take Elon Musk with you?");
                System.out.println("   [Y] Yes     [N] No");
                interactable = tile;
                break;
            case ENGINEER:
                System.out.println("---------------------------------------------------------");
                System.out.println("Engineer: Hun? I'm not Elon! I'm a lady! What the heck!");
                System.out.println("Engineer: I do have his room key though... don't ask why.");
                System.out.println("Engineer: I'll give it to you if you water this withered tree!");
                interactable = Tile.NOTHING;
                break;
            case ICE:
                System.out.println("---------------------------------------------------------");
                System.out.println("Looks like it's ice! I could mine this for some water!");
                System.out.println("Use pickaxe to mine some ice for water?");
                System.out.println("   [Y] Yes     [N] No");
                interactable = tile;
                break;
            case TREE:
                System.out.println("---------------------------------------------------------");
                System.out.println("This tree looks like it's healthy!");
                interactable = Tile.NOTHING;
                break;
            case WITHERED:
                System.out.println("---------------------------------------------------------");
                System.out.println("This tree looks a little withered... it could use some water!");
                System.out.println("Use water on the tree?");
                System.out.println("   [Y] Yes     [N] No");
                interactable = tile;
                break;
            case A12:
                System.out.println("---------------------------------------------------------");
                System.out.println("X Æ A-12: Where did daddy go... :'<");
                interactable = Tile.NOTHING;
                break;
            case FUEL:
                System.out.println("---------------------------------------------------------");
                System.out.println("It's a fuel depot, do you want to refuel? (Recover to max health)");
                System.out.println("   [Y] Yes     [N] No");
                interactable = tile;
                break;
            case LAVARIVER:
                System.out.println("---------------------------------------------------------");
                System.out.println("It's a river... of lava. Better not get too close to it.");
                output.setHitsMessage();
                System.out.println("Being near the heat hurts... you take 1 damage.");
                starship.takenDamage(1);
                interactable = Tile.NOTHING;
                break;

            case VENUS9:
                System.out.println("---------------------------------------------------------");
                System.out.println("Venusian: Careful of the beautiful stars over there, it's a sparking hot lava river");
                interactable = Tile.NOTHING;
                break;
            case VENUS8:
                System.out.println("---------------------------------------------------------");
                System.out.println("Venusian: answer the riddle to ");
                System.out.println("Venusian: Men are from Mars but Women are from _____\"?");
                interactable = Tile.NOTHING;
                break;


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
                    starship.removeItem("Key");
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

        else if (interactable == Tile.FUEL) {
            if (yesNo) {
                System.out.println("You refuel from the depot and recover to 100 health!");
                starship.setHealth(100);
            }
            else {
                System.out.println("Ok... you don't have to play this game on hard mode...");
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

        else if (interactable == Tile.CHEF){
            if (yesNo){
                if (starship.getInventory().contains("Fish")) {
                    System.out.println("You trade your fish for some preserved food rations!");
                    System.out.println("Food has been added to your inventory!");
                    starship.removeItem("Fish");
                    starship.addItem("Food");
                }
                else {
                    System.out.println("Chef: You don't have any fish... Who're you trying to scam?");
                }
            }
            else {
                System.out.println("Yeah, seemed like a bad deal anyways. Moving on.");
            }
        }

        else if (interactable == Tile.WATER && starship.getInventory().contains("Fishing Rod")){
            if (yesNo){
                System.out.println("You cast out your rod.");
                System.out.println("..... 20 minutes later....");
                System.out.println("A BIG BITE! You reel in a huge fish! But your rod broke...");
                System.out.println("Fish has been added to your inventory!");
                starship.removeItem("Fishing Rod");
                starship.addItem("Fish");
            }
            else {
                System.out.println("You probably don't know how to fish anyways...");
            }
        }

        else if (interactable == Tile.WITHERED){
            if (yesNo){
                if (starship.getInventory().contains("Water")) {
                    System.out.println("You use the water on the tree!");
                    System.out.println("By some black magic, the tree makes a full recovery!");
                    starship.getCurrentLocation().setTileWithFacing(Tile.TREE);
                    System.out.println("The sus female engineer gives you a key!");
                    starship.removeItem("Water");
                    starship.addItem("Key");
                }
                else {
                    System.out.println("You don't have any water in your inventory!");
                }
            }
            else {
                System.out.println("Better save the water for later... right? (wrong)");
            }
        }

        else if (interactable == Tile.ICE){
            if (yesNo){
                if (starship.getInventory().contains("Pickaxe")) {
                    System.out.println("You take Bethany in your firm hands... and swing at the ice!");
                    System.out.println("The ice is much harder than you thought!");
                    starship.getCurrentLocation().setTileWithFacing(Tile.NOTHING);
                    System.out.println("You get enough to melt into water... but Bethany broke...");
                    System.out.println("Why does everything break these days?");
                    starship.removeItem("Pickaxe");
                    starship.addItem("Water");
                }
                else {
                    System.out.println("You don't have a pickaxe in your inventory!");
                }
            }
            else {
                System.out.println("What else are you going to use a pickaxe for? MURDER?");
            }
        }

        else if (interactable == Tile.FLAG){
            if (yesNo){
                starship.getCurrentLocation().clearAstronauts();
                starship.getCurrentLocation().setTileWithFacing(Tile.NOTHING);
                starship.getCurrentLocation().setTileLeft(Tile.ASTRO);
                starship.getCurrentLocation().setTileRight(Tile.ASTRO);
                starship.getCurrentLocation().setTileDown(Tile.ASTRO);
                starship.getCurrentLocation().posUpdate();
                System.out.println("Astronaut: HEY! WHAT ARE YOU DOING TO THE FLAG!");
                System.out.println("You grab the Flag, but the nearby astronauts surround you!");
                System.out.println("You get thrown to the floor and they start kicking you.");
                System.out.println("You finally manage to eek out an explanation... and they stop.");
                starship.addItem("Flag");
                output.setHitsMessage();
                System.out.println("The astronauts pummel you for 30 damage.");
                System.out.println("Those guys were pretty ripped, gotta preserve muscle density!");
                starship.takenDamage(30);
            }
            else {
                System.out.println("The astronauts go back to drinking...");
            }
        }


        output.setDefaultSysOut();
        return false;
    }

}
