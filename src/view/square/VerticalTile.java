package view.square;

import isel.leic.pg.Console;
import model.square.Square;

public class VerticalTile extends SquareTile {
    public VerticalTile(Square square) {
        super(square);
    }

    @Override
    public void paint() {paintByStrings(" ^ "," | "," v " );
    }
    @Override
    int getColor() {
        return Console.GRAY;
    }
}