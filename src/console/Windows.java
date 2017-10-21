package console;

import isel.leic.pg.Console;

import static isel.leic.pg.Console.*;
import static isel.leic.pg.Console.cursor;

public class Windows {
    private String title;
    private boolean consoleOpen;
    private int height, width;

    public Windows(String title) {
        this.title = title;
    }

    public void write(int line, int col, String text, int foreground, int background) {
        cursor(line,col);
        color(foreground,background);
        print(text);
    }

    public void clear(int line, int background, int size) {
        cursor(line, 0);
        setBackground(background);
        for (int i = 0; i < size; i++) print(' ');
    }

    public void open(int height, int width) {
        close();
        this.height = height;
        this.width = width;
        Console.open(title,height, width);
        consoleOpen = true;
        Console.exit(true);
        clear(height-1, GRAY, width);
        Console.enableMouseEvents(false);
    }

    public void close() {
        if (consoleOpen) Console.close();
        consoleOpen = false;
    }

    public void message(String txt) {
        if (! consoleOpen) {
            System.out.println(txt);
            return;
        }
        clear(height-1, GRAY, width);
        write(height-1,0,txt,YELLOW,GRAY);
        disableMouseEvents();
        clearAllChars();
        int key = waitKeyPressed(3000);
        clear(height-1, GRAY,txt.length());
        if (key>=0) waitKeyReleased(key);
        enableMouseEvents(false);
    }

    public boolean question(String txt) {
        clear(height-1, GRAY, width);
        write(height-1,0,txt+"? (Y/N)",YELLOW,GRAY);
        disableMouseEvents();
        clearAllChars();
        cursor(true);
        int key;
        do {
            while ((key = waitKeyPressed(0)) < 0) ;
            waitKeyReleased(key);
            if (key>='a' && key<='z') key += 'A'-'a';
        } while(key!='S' && key!='Y' & key!='N');
        clear(height - 1, GRAY, width);
        cursor(false);
        enableMouseEvents(false);
        return key!='N';
    }

    public static void main(String[] args) {
        Window win = new Window("Test Window");
        win.open(30,50);
        win.message("Bye.");
        win.close();
    }
}
