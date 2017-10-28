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

        /*selectGroup(line, col);
        model.destroySquare(this, line, col);
        //TODO*/

        selectGroup(line, col);
        if(count>1)
        model.destroySquare(this, line, col);


        return true;
    }


    private void selectGroup(int l, int c){
        count = 1;
        model.getSquare(l,c).isSelected = true; //coloca o square inicial a isSelected

        System.out.println("count = "+count); //para debug

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
        System.out.println("Total iguais= " + count);//para debug

        checkAround(model.getSquare(l-1,c),l-1,c);
        checkAround(model.getSquare(l + 1, c), l+1,c);
        checkAround(model.getSquare(l, c - 1),l, c-1);
        checkAround(model.getSquare(l, c + 1),l, c+1);
    }





}
