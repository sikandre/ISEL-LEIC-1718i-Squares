package model.square;

/**
 * Created by fabio on 10/16/2017.
 */
public class ColorSquare extends Square {
    @Override
    public boolean touch(int line, int col) {
        model.destroySquare(this, line, col);
        return true;
    }


    //TODO
}
