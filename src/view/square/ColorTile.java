package view.square;

import model.square.Square;

public class ColorTile extends SquareTile {
    private static final String[] CHARS = { "\u250F\u2501\u2513","\u2503 \u2503","\u2517\u2501\u251B"};

    public ColorTile(Square square) {
        super(square);
    }

    @Override
    public void paint() { paintByStrings(CHARS /*"+-+","| |","+-+"*/ ); }
}
