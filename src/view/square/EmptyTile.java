package view.square;

import isel.leic.pg.Console;
import model.square.Square;

public class EmptyTile extends SquareTile {
    public EmptyTile(Square square) {super(square);}

    @Override
    public void paint() {paintByStrings("   ","   ","   " );}

    @Override
    int getColor() {return Console.BLACK;}
}