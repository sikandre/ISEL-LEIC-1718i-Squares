package view;

import console.View;
import isel.leic.pg.Console;
import model.Position;
import model.Squares;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class TilePanel extends View  {
    private final int tileSide;  // Each side of the tile dimension

    private Tile[][] tiles;

    public TilePanel(int tilesHeight, int tilesWidth, int tileSide) {
        super(1,1,Console.BLACK);
        tiles = new Tile[tilesHeight][tilesWidth];
        height = tiles.length * tileSide;
        width = tiles[0].length * tileSide;
        this.tileSide = tileSide;
    }

    public void paint() {
        for (int l = 0; l < Squares.HEIGHT; l++)
            for (int c = 0; c < Squares.WIDTH; c++) {
                Tile t = tiles[c][c];
                if (t!=null) t.paint();
                else paintEmptyTile(l,c);
            }
    }

    public Position getModelPosition(int line, int col) {
        assert(parent==null);
        int l = (line-top) / tileSide;
        int c = (col-left) / tileSide;
        return  (l<0 || c<0 || l >= tiles.length || c>= tiles[0].length) ? null : new Position(l , c);
    }

    public void paintEmptyTile(int l, int c) {
        l *= tileSide;
        c *= tileSide;
        printBox(l,c,tileSide,tileSide,Console.GRAY);
    }

    public void setTile(int l, int c, Tile t) {
        tiles[l][c] = t;
        if (t==null) {
            paintEmptyTile(l,c);
        } else {
            addTile(l, c, t);
            t.paint();
        }
    }

    private void addTile(int l, int c, Tile t) {
        super.addView(t);
        t.setOrig(l * tileSide, c * tileSide);
    }

    public Tile getTile(int l, int c) {
        return tiles[l][c];
    }

    // ------------------------- Animation of tiles -----------------------
    private class Animation {
        Tile tile;
        int l, c;
        int topTo;
        Animation(int l, int c, Tile t) {
            this.l = l; this.c = c; tile = t; topTo = l * tileSide;
        }
        boolean doAction() {
            int top = tile.getTop();
            if (top == topTo) {
                tiles[l][c] = tile;
                return true;
            }
            tile.setTop(top+1);
            tile.printBox(-1,0,1,tileSide,Console.GRAY);
            tile.paint();
            paintOverTiles(c,tile);
            return false;
        }
        @Override
        public String toString() { return String.format("Anim(%d,%d,topTo=%d,top=%d)",l,c,topTo,tile.getTop()); }
    }

    private void paintOverTiles(int c, Tile t) {
        for (int l = 0; l < Squares.HEIGHT; l++) {
            Tile o = tiles[l][c];
            if (o!=null && o.isOver(t)) o.paint();
        }
    }

    private LinkedList<Animation> moves = new LinkedList<>();
    private LinkedList<Animation> news = new LinkedList<>();

    public void moveTile(int lFrom, int lTo, int c) {
        Tile t = tiles[lFrom][c];
        if (t==null) return;
        setTile(lFrom,c,null);
        moves.addLast( new Animation(lTo, c, t ) );
    }
    public void newTile(int l, int c, Tile t) {
        addTile(l-1,c,t);
        news.addLast( new Animation(l,c,t) );
    }

    public boolean stepAnimations() {
        if (moves.isEmpty()) {
            if (news.isEmpty()) return false;
            stepAnims(news);
        }
        else stepAnims(moves);
        return true;
    }

    private void stepAnims(List<Animation> list) {
        Iterator<Animation> it = list.iterator();
        while (it.hasNext()) {
           Animation a = it.next();
           if (a.doAction()) it.remove();
        }
    }
}
