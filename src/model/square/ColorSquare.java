package model.square;

public class ColorSquare extends Square {


    private char type;

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
            return (int)(Math.random()*6)+0;
        return NO_COLOR;

    }

    @Override
    public boolean touch(int line, int col) {
        model.destroySquare(this, line, col);
        System.out.println(getColor());
        return true;
    }


    //TODO

}
