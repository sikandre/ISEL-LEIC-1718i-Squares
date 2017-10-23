package view.square;

import model.square.Square;
import isel.leic.pg.Console;


public class BombTile extends SquareTile {
    public BombTile(Square square) {
        super(square);
    }


    @Override
    public void paint() {paintByStrings("\\|/","=o=","/|\\");
    }

    @Override
    int getColor() {
        return Console.GRAY;
    }
}