package console;

import isel.leic.pg.Console;
import view.Tile;

public class View {
    protected int top, left, height, width;
    protected View parent = null;
    protected int bkColor;

    protected View(int top, int left, int height, int width, int bkColor) {
        this(top,left,bkColor);
        setSize(height,width);
    }
    protected View(int top, int left, int bkColor) {
        setOrig(top,left);
        this.bkColor = bkColor;
    }
    protected View() {
        this(0,0,Console.GRAY);
    }
    public void setOrig(int top, int left) { this.top=top; this.left=left; }
    public void setSize(int height, int width) { this.height=height; this.width=width; }
    public void addView(View v) { v.parent=this; }

    public int getHeight() { return height; }
    public int getWidth() { return width; }

    public void paint() { clear(); }

    public void clear() { printBox(0,0,height,width,bkColor); }

    public void printBox(int top, int left, int height, int width, int bkColor) {
        Console.setBackground(bkColor);
        for (int l = 0; l < height ; l++)
            for (int c = 0; c < width; c++) print(top+l,left+c,' ');
    }

    protected boolean cursor(int lin, int col) {
        int l = lin+top, c = col+left;
        if (l<0 || l>=top+height || c<0 || c>=left+width) return false;
        if (parent!=null) parent.cursor(l,c);
        else Console.cursor(l,c);
        return true;
    }

    public void print(int lin, int col, char c) {
        if (cursor(lin, col))
            Console.print(c);
    }
    public void print(int lin, int col, String s) {
        if (cursor(lin, col))
            Console.print(s);
    }

    public boolean isOver(Tile other) {
        return (top >= other.top && top <= other.top + other.height || other.top >= top && other.top <= top + height)
          && (left >= other.left && left <= other.left + other.width || other.left >= left && other.left <= left + width);
    }
}
