package model.square;

public class EmptySquare extends Square {

    private char type;

    protected EmptySquare(char type) {this.type = type;}

    @Override
    public boolean touch(int line, int col) {return false;}

    @Override
    public boolean isMoveble() {return false;}

    @Override
    public boolean isSelected() {return selected;}

    @Override
    public void checkAroundSquares(int line, int col){}

    @Override
    public boolean isSpecial() {return special=true;}


}
