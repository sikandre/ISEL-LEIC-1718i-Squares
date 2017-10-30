package model.square;

public class BombSquare extends Square {
    protected char type;


    public BombSquare(char type) {
        this.type = type;
    }

    @Override
    public boolean isMoveble() {
        return true;
    }

    @Override
    public boolean touch(int line, int col) {
        return false;
    }

    @Override
    public int getColor() {
        return NO_COLOR;
    }
}
