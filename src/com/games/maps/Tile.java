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
    ELON('e'),
    //FOOD('f'),
    A12('X'),


    LAVARIVER('*'),

    //earth
    CHEF('C'),
    ROD('r'),
    GRIMES('G'),
    ART('$'),

    FRIENDLY('f'),
    GOLD('g'),
    TREASURE('m'),


    PLUS('+'),

    SPACE(' '),
    POI('?'),
    CLUE('`'),
    COINTOSS('c'),
    VENDOR('V'),
    BLACKJACK('&'),

    A('A'),
    B('B'),
    D('D'),
    E('E'),
    G('G'),
    H('H'),
    I('I'),
    J('J'),
    K('K'),
    M('M'),
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
