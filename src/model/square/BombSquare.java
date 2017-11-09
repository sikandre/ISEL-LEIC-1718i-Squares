package model.square;

import model.Squares;

public class BombSquare extends Square {
    protected char type;
    protected BombSquare(char type) {
        this.type = type;
    }

    @Override
    public boolean isMovable() {
        return true;
    }

    @Override
    public boolean isSelected() {
        return selected;
    }

    @Override
    public boolean touch(int line, int col) {
        this.selected=true;
        checkAroundSquares(line,col);
        return true;
    }

    public void checkAroundSquares(int l, int c){
        int rowLimit = Squares.HEIGHT -1;
        int columnLimit = Squares.WIDTH -1;
        for(int x = Math.max(0, l-1); x <= Math.min(l+1, rowLimit); x++) {
            for(int y = Math.max(0, c-1); y <= Math.min(c+1, columnLimit); y++) {
                Square sq = model.getSquare(x,y);
                if (sq != null && sq.isMovable()) {
                    sq.selected = true;
                }
            }
        }
    }

    @Override
    public int getColor() {
        return NO_COLOR;
    }

    @Override
    public boolean isSpecial(){ return special=true; }
}
