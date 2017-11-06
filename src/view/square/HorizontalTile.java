package view.square;

import isel.leic.pg.Console;
import model.square.Square;

public class HorizontalTile extends SquareTile {
    public HorizontalTile(Square square) {
        super(square);
    }

    @Override
    public void paint() {paintByStrings("   ","<->","   " );
    }
    @Override
    int getColor() {
        return Console.GRAY;
    }
}
