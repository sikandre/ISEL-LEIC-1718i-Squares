package model.square;

import model.Squares;


public abstract class Square {
    public static final int NO_COLOR = -1;
    public static Squares model;  // The model of game.
    public boolean isSelected = false;


    public abstract boolean touch(int line, int col);
    public int getColor(){
        return NO_COLOR;
    }
    
    public static Square newInstance(char type) {
        if(type=='V') return new SpaceSquare(type);
        if(type=='H') return new LineSquare(type);
        if(type=='B') return new BombSquare(type);
        if(type=='X') return new EmptySquare(type);
        return new ColorSquare(type);
    }
    public Square getSquare() {
        return this;
    }

    public boolean isSelected() {
        return isSelected=false;
    }
}
