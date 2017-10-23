package view.square;

import model.square.Square;

public class EmptyTile extends SquareTile {
    public EmptyTile(Square square) {
        super(square);
    }

    @Override
    public void paint() {paintByStrings("","","" );

    }
}