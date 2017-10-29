package model.square;

import model.Squares;

public class ColorSquare extends Square {

    private char type;
    private int count = 0;
    private int color;

    ColorSquare(char type){
        this.type = type;
    }

    @Override
    public int getColor() {
        if(type=='1')
            return color=0;
        if(type=='2')
            return color=1;
        if(type=='3')
            return color=2;
        if(type=='4')
            return color=3;
        if(type=='5')
            return color=4;
        if(type=='.')
            return color = (int) (Math.random() * Squares.MAX_GOALS);
        return NO_COLOR;


    }

    @Override
    public boolean touch(int line, int col) {

        selectGroup(line, col);
        if(count>1) {
            if (count >= 6) {
                Square s = new BombSquare('B');
                model.newSquare(s,line,col);
            }
            else if (count == 5) {
                int random = (int) (Math.random() * 2);
                Square s = random == 1 ? new SpaceSquare('V') : new LineSquare('H');
                model.newSquare(s, line, col);
            }
            else
                model.destroySquare(this, line, col);
            model.moveSquare();
        }

        return false;
    }

    private void selectGroup(int l, int c){
        count = 1;
        model.getSquare(l,c).isSelected = true; //coloca o square inicial a isSelected

        checkAround(model.getSquare(l-1,c),l-1,c);
        checkAround(model.getSquare(l + 1, c), l+1,c);
        checkAround(model.getSquare(l, c - 1),l, c-1);
        checkAround(model.getSquare(l, c + 1),l, c+1);
    }

    private void checkAround(Square square, int l,int c) {
        if (!(square instanceof ColorSquare))
            return;
        if(((ColorSquare) square).color!= color || square.isSelected)
            return;

        count++;
        square.isSelected=true;
        model.destroySquare(this, l, c);

        checkAround(model.getSquare(l-1,c),l-1,c);
        checkAround(model.getSquare(l + 1, c), l+1,c);
        checkAround(model.getSquare(l, c - 1),l, c-1);
        checkAround(model.getSquare(l, c + 1),l, c+1);
    }





}
