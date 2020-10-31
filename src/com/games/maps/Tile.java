package com.games.maps;


public enum Tile {
    NOTHING (' '),
    WALL('#'),
    PLAYER('@'),
    SPACEDOCK('^'),
    FLAG('F'),
    LAND('L'),
    WATER('~'),
    KEY('!'),
    DOOR('/'),
    FUEL('D'),


    //venus
    LAVARIVER('*'),
    VENUS9('9'),
    VENUS8('8'),
    VENUS8DONE('8'),
    BUCKET('u'),
    FORGEDOOR('\\'),
    FORGE('A'),

    //earth
    CHEF('C'),
    ROD('r'),
    GRIMES('G'),
    ART('$'),
    FRIENDLY('f'),
    GOLD('g'),

    //moon
    ELON('e'),
    A12('X'),
    MINED('m'),
    MINER('M'),
    ENGINEER('E'),
    TREE('T'),
    WITHERED('t'),
    ICE('i'),
    ORE('o'),
    ASTRO('a'),

    //mars

    MATERIALS('='),
    WATERGEN('W'),
    STOCKPILE('S'),
    RELIC('R'),
    FOODPILE('s'),



    PLUS('+'),


    B('B'),
    H('H'),

    J('J'),
    K('K'),
    I('I'),
    N('N'),
    O('O'),
    P('P'),
    Q('Q'),
    T('T'),
    U('U'),
    V('V'),
    X('X'),
    Y('Y'),
    Z('Z');



    private char symbol;

    Tile(char symbol){
        this.symbol=symbol;
    }

    public char symbol(){
        return symbol;
    }
}
