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
    public boolean isSelected() {
        return selected;
    }

    @Override
    public boolean touch(int line, int col) {
        checkAroundSquares(line,col);
        return true;
    }

    public void checkAroundSquares(int l, int c){
        int rowLimit = model.HEIGHT-1;
        int columnLimit = model.WIDTH-1;
        for(int x = Math.max(0, l-1); x <= Math.min(l+1, rowLimit); x++) {
            for(int y = Math.max(0, c-1); y <= Math.min(c+1, columnLimit); y++) {
                Square sq = model.getSquare(x,y);
                if (!(sq instanceof EmptySquare)) {
                    //TODO criar um metodo para
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
    public boolean isSpecial(){ return true; }
}
