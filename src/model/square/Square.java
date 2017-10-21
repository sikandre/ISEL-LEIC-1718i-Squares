package model.square;

import model.Squares;

/**
 * The base classe of Squares in model.
 */
public abstract class Square {
    public static final int NO_COLOR = -1;

    public static Squares model;  // The model of game.

    //TODO
    public int getColor() {
        return 0;
    }

    //rever
    public static Square newInstance(char type) {
        return new ColorSquare();
    }

    public abstract boolean touch(int line, int col);

}
