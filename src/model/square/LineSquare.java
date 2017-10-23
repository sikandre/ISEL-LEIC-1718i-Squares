package model.square;

public class LineSquare extends Square{

    protected char type;

    public LineSquare(char type) {
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
