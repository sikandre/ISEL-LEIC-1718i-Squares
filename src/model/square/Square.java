package model.square;

import isel.leic.pg.Console;
import model.Squares;
import view.square.SquareTile;

/**
 * The base classe of Squares in model.
 */
public abstract class Square {
    public static final int NO_COLOR = -1;

    public static Squares model;  // The model of game.
/*
    private char type;
*/

    //TODO

    //rever


    public abstract boolean touch(int line, int col);
    public abstract int getColor();

    public static Square newInstance(char type) {
        if(type=='V') return new SpaceSquare(type);
        if(type=='H') return new LineSquare(type);
        if(type=='B') return new BombSquare(type);
        if(type=='X') return new EmptySquare(type);
        return new ColorSquare(type);
    }
}
