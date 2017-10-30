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
    public int getColor() {
        return color;
    }

    @Override
    public boolean touch(int line, int col) {
        selectGroup(line, col);
        if(count>1) {
            if (count >= 6) {
                Square s = new BombSquare('B');
                model.changeSquare(s,line,col);
            }
            else if (count == 10) {
                int random = (int) (Math.random() * 2);
                Square s = random == 1 ? new SpaceSquare('V') : new LineSquare('H');
                model.changeSquare(s, line, col);
            }
            else
                model.destroySquare(this, line, col);
            return true;
        }
        return false;
    }

    private void selectGroup(int l, int c){
        count = 1;
        isSelected = true; //coloca o square inicial a isSelected

        checkAround(model.getSquare(l-1,c),l-1,c);
        checkAround(model.getSquare(l + 1, c), l+1,c);
        checkAround(model.getSquare(l, c - 1),l, c-1);
        checkAround(model.getSquare(l, c + 1),l, c+1);
    }

    private  void checkAround(Square square, int l,int c) {
        if (!(square instanceof ColorSquare))
            return;
        if(((ColorSquare) square).color!= color || square.isSelected)
            return;

        count++;
        square.isSelected=true;
        model.destroySquare(square, l, c);

        checkAround(model.getSquare(l-1,c),l-1,c);
        checkAround(model.getSquare(l + 1, c), l+1,c);
        checkAround(model.getSquare(l, c - 1),l, c-1);
        checkAround(model.getSquare(l, c + 1),l, c+1);
    }





}
