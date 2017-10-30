package model.square;

public class EmptySquare extends Square {
    protected char type;

    public EmptySquare(char type) {
        this.type = type;
    }

    @Override
    public boolean touch(int line, int col) {
        return false;
    }

    @Override
    public boolean isMoveble() {
        return false;
    }

   /* @Override
    public int getColor() {
        return NO_COLOR;
    }*/
}
