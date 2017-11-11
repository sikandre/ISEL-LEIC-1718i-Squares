package view.square;

import isel.leic.pg.Console;
import model.square.Square;

public class LineTile extends SquareTile {
    public LineTile(Square square) {
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