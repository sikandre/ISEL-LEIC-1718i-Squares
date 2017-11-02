package model.square;

import model.Squares;

public class ColorSquare extends Square {

    private static int count = 0;
    private int color;

    ColorSquare(char type){
        if(type=='.')
            color = (int) (Math.random() * Squares.MAX_COLORS);
        else
            color = type - '1';
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
    public int getColor() {
        return color;
    }

    @Override
    public boolean touch(int line, int col) {
        checkAroundSquares(line, col);
        if(count>1) {
            selected = true;
            if (count >= 2) {
                Square s = new BombSquare('B');
                model.changeSquare(s,line,col);
            }
            if (count >= 3) {
                int random = (int) (Math.random() * 2);
                Square s = random == 1 ? new HorizotalSquare('V') : new VerticalSquare('H');
                model.changeSquare(s, line, col);
            }
            return true;
        }

        return false;
    }


    @Override
    public void checkAroundSquares(int l, int c){
        count = 1;


        //TODO #código repetido
        checkAround(model.getSquare(l-1,c),l-1,c);
        checkAround(model.getSquare(l + 1, c), l+1,c);
        checkAround(model.getSquare(l, c - 1),l, c-1);
        checkAround(model.getSquare(l, c + 1),l, c+1);
    }

    private  void checkAround(Square square, int l,int c) {
        //TODO modifica instanceof por getcolor
        if (!(square instanceof ColorSquare))
            return;
        if(((ColorSquare) square).color!= color || square.selected)
            return;

        count++;
        square.selected =true;

        //TODO #código repetido
        checkAround(model.getSquare(l-1,c),l-1,c);
        checkAround(model.getSquare(l + 1, c), l+1,c);
        checkAround(model.getSquare(l, c - 1),l, c-1);
        checkAround(model.getSquare(l, c + 1),l, c+1);
    }

    @Override
    public boolean isSpecial(){ return false; }



}
