package model.square;


import isel.leic.pg.Console;

public class SpaceSquare extends Square{
    protected char type;

    public SpaceSquare(char type) {
        this.type = type;
    }

    @Override
    public boolean touch(int line, int col) {
        return false;
    }

    public int getColor() {
        return NO_COLOR;
    }

}
