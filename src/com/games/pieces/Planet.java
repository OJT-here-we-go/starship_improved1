package com.games.pieces;

import com.games.client.FileReader;
import com.games.maps.Tile;

import java.awt.*;
import java.nio.file.Path;
import java.util.ArrayList;

public class Planet {

    private String name;
    private ArrayList<String> resources;
    private int x, y;
    private Color color;
    private Character symbol;
    private Starship starship;
    private String variationName;
//    private Tile previousTile = Tile.NOTHING;
//    private Tile nextTile;
//    private int nextX,nextY;

    private ArrayList<ArrayList<Tile>> tiles;


    public Planet(String name, ArrayList<String> resources, Starship starship) {
        setName(name);
        setResources(resources);
        this.starship = starship;
    }

    public Planet(String name, ArrayList<String> resources, int x, int y, Color color, Character symbol,Starship starship, String variation) {
        this(name,resources,starship);
        setX(x);
        setY(y);
        setColor(color);
        setSymbol(symbol);
        this.variationName = name + variation;

        tiles = new ArrayList<ArrayList<Tile>>();

        ArrayList<String> strs = FileReader.readMapFile(String.valueOf(Path.of("src/maps",variationName + ".txt")));

        //"src/"
        //+planetNumber+".txt");

        for(int i = 0; i < strs.size()-1 ; i++) {
            char[] charray = strs.get(i).toCharArray();
            tiles.add(new ArrayList<Tile>());
            for (char c : charray) {
                switch (c) {
                    case '~':
                        tiles.get(i).add(Tile.WATER);
                        break;
                    case '=':
                        tiles.get(i).add(Tile.MATERIALS);
                        break;
                    case 'F':
                        tiles.get(i).add(Tile.FLAG);
                        break;
                    case 'L':
                        tiles.get(i).add(Tile.LAND);
                        break;
                    case ' ':
                        tiles.get(i).add(Tile.NOTHING);
                        break;
                    case '#':
                        tiles.get(i).add(Tile.WALL);
                        break;
                    case '@':
                        tiles.get(i).add(Tile.PLAYER);
                        break;
                    case '^':
                        tiles.get(i).add(Tile.SPACEDOCK);
                        break;
                    case 'e':
                        tiles.get(i).add(Tile.ELON);
                        break;
                    case 'f':
                        tiles.get(i).add(Tile.FRIENDLY);
                        break;
                    case 'r':
                        tiles.get(i).add(Tile.ROD);
                        break;
                    case '$':
                        tiles.get(i).add(Tile.ART);
                        break;
                    case 'D':
                        tiles.get(i).add(Tile.FUEL);
                        break;
                    case '!':
                        tiles.get(i).add(Tile.KEY);
                        break;
                    case '/':
                        tiles.get(i).add(Tile.DOOR);
                        break;
                    case 'X':
                        tiles.get(i).add(Tile.A12);
                        break;
                    case '*':
                        tiles.get(i).add(Tile.LAVARIVER);
                        break;
                    case 'C':
                        tiles.get(i).add(Tile.CHEF);
                        break;
                    case 'G':
                        tiles.get(i).add(Tile.GRIMES);
                        break;
                    case 'M':
                        tiles.get(i).add(Tile.MINER);
                        break;
                    case 'm':
                        tiles.get(i).add(Tile.MINED);
                        break;
                    case 't':
                        tiles.get(i).add(Tile.WITHERED);
                        break;
                    case 'T':
                        tiles.get(i).add(Tile.TREE);
                        break;
                    case 'i':
                        tiles.get(i).add(Tile.ICE);
                        break;
                    case 'E':
                        tiles.get(i).add(Tile.ENGINEER);
                        break;
                    case 'a':
                        tiles.get(i).add(Tile.ASTRO);
                        break;
                    case 'o':
                        tiles.get(i).add(Tile.ORE);
                        break;
                    case '9':
                        tiles.get(i).add(Tile.VENUS9);
                        break;
                    case '8':
                        tiles.get(i).add(Tile.VENUS8);
                        break;
                    case 'u':
                        tiles.get(i).add(Tile.BUCKET);
                        break;
                    case '\\':
                        tiles.get(i).add(Tile.FORGEDOOR);
                        break;
                    case 'A':
                        tiles.get(i).add(Tile.FORGE);
                        break;
                    case '+':
                        tiles.get(i).add(Tile.PLUS);
                        break;
                    case 'R':
                        tiles.get(i).add(Tile.RELIC);
                        break;
                    case 'S':
                        tiles.get(i).add(Tile.STOCKPILE);
                        break;
                    case 'W':
                        tiles.get(i).add(Tile.WATERGEN);
                        break;
                    case 's':
                        tiles.get(i).add(Tile.FOODPILE);
                        break;


                    //Alphabet characters

                    case 'B':
                        tiles.get(i).add(Tile.B);
                        break;
                    case 'I':
                        tiles.get(i).add(Tile.I);
                        break;

                    case 'V':
                        tiles.get(i).add(Tile.V);
                        break;

                    case 'H':
                        tiles.get(i).add(Tile.H);
                        break;

                    case 'J':
                        tiles.get(i).add(Tile.J);
                        break;
                    case 'K':
                        tiles.get(i).add(Tile.K);
                        break;

                    case 'N':
                        tiles.get(i).add(Tile.N);
                        break;
                    case 'O':
                        tiles.get(i).add(Tile.O);
                        break;
                    case 'P':
                        tiles.get(i).add(Tile.P);
                        break;
                    case 'Q':
                        tiles.get(i).add(Tile.Q);
                        break;

                     case 'U':
                        tiles.get(i).add(Tile.U);
                        break;

                    case 'Y':
                        tiles.get(i).add(Tile.Y);
                        break;
                    case 'Z':
                        tiles.get(i).add(Tile.Z);
                        break;
                }
            }
        }

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<String> getResources() {
        return resources;
    }

    public void setResources(ArrayList<String> resources) {
        this.resources = resources;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public Character getSymbol() {
        return symbol;
    }

    public void setSymbol(Character symbol) {
        this.symbol = symbol;
    }


    //Gets the size of the floor on the y coordinate
    public int getHeight() {
        return tiles.size();
    }

    //Gets the size of the floor on the x coordinate
    public int getWidth() {
        return tiles.get(0).size();
    }

    //Returns one tile of the floor
    public Tile getTile(int x, int y) {
        return tiles.get(y).get(x);
    }

    public void setTileWithFacing(Tile tile) {
        Direction facing = starship.getFacing();
        switch (facing) {
            case UP:
                tiles.get(starship.getPlanetYPos()-1).set(starship.getPlanetXPos(), tile);
                break;
            case DOWN:
                tiles.get(starship.getPlanetYPos()+1).set(starship.getPlanetXPos(), tile);
                break;
            case LEFT:
                tiles.get(starship.getPlanetYPos()).set(starship.getPlanetXPos()-1, tile);
                break;
            case RIGHT:
                tiles.get(starship.getPlanetYPos()).set(starship.getPlanetXPos()+1, tile);
                break;
        }
    }

    public void setTileLeft(Tile tile) {
        tiles.get(starship.getPlanetYPos()).set(starship.getPlanetXPos()-1, tile);
    }
    public void setTileRight(Tile tile) {
        tiles.get(starship.getPlanetYPos()).set(starship.getPlanetXPos()+1, tile);
    }
    public void setTileUp(Tile tile) {
        tiles.get(starship.getPlanetYPos()-1).set(starship.getPlanetXPos(), tile);
    }
    public void setTileDown(Tile tile) {
        tiles.get(starship.getPlanetYPos()+1).set(starship.getPlanetXPos(), tile);
    }

    public void clearAstronauts() {
        for(int i=0;i<this.getHeight();i++) {
            for(int j=0;j<this.getWidth();j++) {
                if(tiles.get(i).get(j) == Tile.ASTRO)
                    tiles.get(i).set(j, Tile.NOTHING);
            }
        }
    }

    //Returns one tile of the floor
    public char getTileChar(int x, int y) {
        return tiles.get(y).get(x).symbol();
    }

    public void posUpdate(){
//        tiles.get(starship.getPlanetYPos()).set(starship.getPlanetXPos(),previousTile);
//
//        previousTile = starship.getCurrentLocation().getTile(starship.getPlanetXPos(), starship.getPlanetYPos());
//        nextX = starship.getPlanetXPos();
//        nextY = starship.getPlanetYPos();

        for(int i=0;i<this.getHeight();i++) {
            for(int j=0;j<this.getWidth();j++) {
                if(tiles.get(i).get(j) == Tile.PLAYER)
                    tiles.get(i).set(j, Tile.NOTHING);

            }
        }
        //DELETES
//        if (nextTile.equals('.')) {
//            for(int i=0;i<this.getHeight();i++) {
////                for(int j=0;j<this.getWidth();j++) {
////                    if(tiles.get(i).get(j) == Tile.PLAYER)
////                        tiles.get(i).set(j, previousTile);
////                        previousTile = Tile.NOTHING;
////                }
////            }
//        }
//        if (!nextTile.equals('.')) {
//            for(int i=0;i<this.getHeight();i++) {
//                for(int j=0;j<this.getWidth();j++) {
//                    if(tiles.get(i).get(j) == Tile.PLAYER)
//                        tiles.get(i).set(j, Tile.NOTHING);
//                        previousTile = nextTile;
//                }
//            }
//
//        }


        //Sets new pos
        tiles.get(starship.getPlanetYPos()).set(starship.getPlanetXPos(), Tile.PLAYER);
    }

}
