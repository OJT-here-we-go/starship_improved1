package com.games.maps;


public enum Tile {
    NOTHING (' '),
    WALL('#'),
    PLAYER('@'),
    SPACEDOCK('^'),
    MATERIALS('='),
    FLAG('F'),
    SEEDS('o'),
    LAND('L'),
    WATER('~'),
    KEY('!'),
    DOOR('/'),
    FUEL('D'),


    //venus
    LAVARIVER('*'),
    VENUS9('9'),
    VENUS8('8'),

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
    ICE('I'),
    ORE('o'),


    PLUS('+'),

    SPACE(' '),
    POI('?'),
    CLUE('`'),
    COINTOSS('c'),
    VENDOR('V'),
    BLACKJACK('&'),
    ASTRO('a'),

    A('A'),
    B('B'),
    H('H'),

    J('J'),
    K('K'),

    N('N'),
    O('O'),
    P('P'),
    Q('Q'),
    R('R'),
    S('S'),
    T('T'),
    U('U'),
    W('W'),
    X('X'),
    Y('Y'),
    Z('Z'),

    //STORE ITEMS
    CORN('['),
    WHISKEY(';'),
    APPLE(','),
    BOW('}'),
    SWORD('|'),
    XP('"'),
    SOAP('='),

    // NUMBERS
    ONE('1'),
    TWO('2'),
    THREE('3'),
    FOUR('4'),
    FIVE('5'),
    SIX('6'),
    SEVEN('7'),
    EIGHT('8'),
    NINE('9'),
    ZERO('0');


    private char symbol;

    Tile(char symbol){
        this.symbol=symbol;
    }

    public char symbol(){
        return symbol;
    }
}
