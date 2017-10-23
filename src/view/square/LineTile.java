package view.square;

import model.square.Square;

public class LineTile extends SquareTile {
    public LineTile(Square square) {
        super(square);
    }

    @Override
    public void paint() {paintByStrings(" ^ "," | "," v " );

    }
}