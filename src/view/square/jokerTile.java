package view.square;

import isel.leic.pg.Console;
import model.square.Square;

public class jokerTile extends SquareTile {
    public jokerTile(Square square) {
        super(square);
    }

    @Override
    public void paint() {
        paintByStrings("   ","(.)","   ");

    }

    @Override
    int getColor() {
        return Console.GRAY;
    }
}
