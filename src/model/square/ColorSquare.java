package model.square;

import model.Squares;

public class ColorSquare extends Square {


    private char type;
    private int count = 0;
    private boolean isSelected;




    ColorSquare(char type){
        this.type = type;
    }

    @Override
    public int getColor() {
        if(type=='1')
            return 0;
        if(type=='2')
            return 1;
        if(type=='3')
            return 2;
        if(type=='4')
            return 3;
        if(type=='5')
            return 4;
        if(type=='.')
            return (int) (Math.random() * Squares.MAX_GOALS);
        return NO_COLOR;

    }

    @Override
    public boolean isSelected() {
        return isSelected;
    }

    @Override
    public boolean touch(int line, int col) {


        selectGroup(line, col);

        //model.destroySquare(this, line, col);

        return true;
    }


    private void selectGroup(int l, int c){
        isSelected=true;

        checkAround(l-1, c);
        checkAround(l + 1, c);
        checkAround(l, c - 1);
        checkAround(l, c + 1);

    }

    private void checkAround(int l, int c) {
        if (l<0 || l>=model.HEIGHT || c<0 || c>=model.WIDTH)
            return;

        System.out.println("count= "+count);
        boolean selecao = isSelected;
        boolean check = getSquare().isSelected;

        if(!sameColor(l,c) || getSquare().isSelected)
            return;

        System.out.println("count23= "+count);

        model.getSquare(l,c).isSelected=true;

        count++;
        checkAround(l - 1, c);
        checkAround(l + 1, c);
        checkAround(l, c - 1);
        checkAround(l, c + 1);
    }

    private boolean sameColor(int l, int c) {
        int color = model.getSquare(l, c).getColor();
        int localcolor = getColor();
        return model.getSquare(l,c).getColor() == getColor();
    }


    //TODO

}
