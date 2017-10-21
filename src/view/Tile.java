package view;

import console.View;

/**
 * Base class hierarchy of Tiles
 */
public abstract class Tile extends View {

    public abstract void paint();

    int getTop() { return top; }
    void setTop(int y) { top=y; }
}
