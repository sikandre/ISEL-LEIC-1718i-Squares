package view.square;

import isel.leic.pg.Console;
import model.square.*;
import view.Tile;

/**
 * Base class of Square Viewer.
 * To display in TilePanel should extend Tile.
 * Each Tile is a console.View
 */
public abstract class SquareTile extends Tile {
    public static final int SIDE = 3;

    // Matches between the color number in the model and the color displayed on the console
    static final int[] COLORS = {
            Console.RED, Console.GREEN, Console.BLUE,
            Console.BROWN, Console.MAGENTA, Console.ORANGE
    };

    protected final Square square; // Reference to the model square

    SquareTile(Square square) {
        this.square = square;
        setSize(SIDE,SIDE);       // Set dimension of View
    }

    /**
     * The color used to show the square
     * @return The console color to use in that square
     */
    int getColor() {
        int color = square.getColor();
        return color==Square.NO_COLOR ? Console.BLACK : COLORS[ color ];
    }

    /**
     * Creates the appropriate view of the square type of the model
     * @param square The model square
     * @return The view for the square
     */
    public static SquareTile newInstance(Square square) {
        /*
        This method should be improved by using reflection
        */
        //creation of all types of squares
        if (square instanceof HorizotalSquare) return new SpaceTile(square);
        if (square instanceof VerticalSquare) return new LineTile(square);
        if (square instanceof BombSquare) return new BombTile(square);
        if (square instanceof ColorSquare) return new ColorTile(square);
        if (square instanceof EmptySquare) return new EmptyTile(square);
        if (square instanceof jokerSquare) return new jokerTile(square);
        return null;

    }

    void paintByStrings(String... lines) {
        Console.setBackground( getColor() );
        Console.setForeground( Console.WHITE );
        for (int l = 0; l < SIDE; l++) print(l,0,lines[l]);
    }
}
