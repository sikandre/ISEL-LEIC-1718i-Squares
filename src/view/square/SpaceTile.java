package view.square;

import model.square.Square;

public class SpaceTile extends SquareTile {
    public SpaceTile(Square square) {
        super(square);
    }

    @Override
    public void paint() {paintByStrings("","<->","" );

    }
}
