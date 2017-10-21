package ctrl;

import console.Window;
import isel.leic.pg.Console;
import isel.leic.pg.MouseEvent;
import model.*;
import model.square.Square;
import view.*;
import view.square.SquareTile;

import java.awt.event.KeyEvent;
import java.io.*;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * Main class in console mode for the "Squares" game.
 * Performs interaction with the user.<br/>
 * Implements the Controller in the MVC model,
 * making the connection between the model and the viewer specific to the console mode.
 */
public class Game {
    public static void main(String[] args) {
        Game game = new Game();
        game.run();
    }

    private static final String LEVELS_FILE = "Levels.txt"; // Name of levels file
    private Squares model;              // Model of current level
    private TilePanel view = new TilePanel(Squares.HEIGHT,Squares.WIDTH, SquareTile.SIDE);  // View of squares
    private StatusPanel status = new StatusPanel(Squares.WIDTH*SquareTile.SIDE+2); // View of moves and goals
    private Window win = new Window("Squares POO");


    /**
     * Main game loop.
     * Returns when there are no more levels or the player gives up.
     */
    private void run() {
        int num = 0;    // Current level number
        win.open( view.getHeight()+3, view.getWidth()+status.getWidth()+3);
        while (loadLevel(++num))
            if (!playLevel() || !win.question("Next level")) {
                exit("Bye.");
                return;
            }
        exit("No more Levels");
    }

    /**
     * Displays the message and closes the console window.
     * @param msg the message to show
     */
    private void exit(String msg) {
        win.message(msg);
        win.close();
    }

    /**
     * Main loop of each level.
     * @return true - the level has been completed. false - the player has given up.
     */
    private boolean playLevel() {
        // Opens the console window with dimensions appropriate to the current level.
        // Starts the viewer for each model square.
        // Shows the initial state of all cells in the model.
        for (int l = 0; l < Squares.HEIGHT; l++)
            for (int c = 0; c < Squares.WIDTH; c++)
                view.setTile(l,c, SquareTile.newInstance( model.getSquare(l,c) ));
        status.clearGoals();
        for (int i = 0; i < model.getNumGoals(); i++)
            status.addGoal(model.getGoal(i).square);
        status.paint();

        while ( play() ) ;      // Process one input event (mouse or keyboard)
        return winGame();       // Verify win conditions; false: finished without complete
    }

    /**
     * Verify win conditions and print winner message.
     * @return true - if level is completed
     */
    private boolean winGame() {
        if (model.isWinner()) {
            win.message("Winner");
            return true;
        }
        return false;
    }

    /**
     * Load the model of the indicated level from the LEVELS_FILE file
     * @param n The level to load (1..MAX)
     * @return true if the level is loaded
     */
    private boolean loadLevel(int n) {
        Scanner in = null;
        try {
            in = new Scanner(new FileInputStream(LEVELS_FILE)); // Scanner to read the file
            model = new Loader(in).load(n);                     // Load level from scanner
            model.setListener( listener );                      // Set the listener of modifications
            return true;
        } catch (FileNotFoundException | InputMismatchException e) {
            System.out.println("Error loading file \""+LEVELS_FILE+"\":\n"+e.getMessage());
            return false;
        } catch (Loader.LevelFormatException e) {
            System.out.println(e.getMessage()+" in file \""+LEVELS_FILE+"\"");
            System.out.println(" "+e.getLineNumber()+": "+e.getLine());
            return false;
        } finally {
            if (in!=null) in.close();   // Close the file
        }
    }

    private class ModelListener implements Squares.Listener {
        private boolean delete;
        private void waitToShow() { if (delete) { Console.sleep(250); delete=false; } }
        @Override
        public void notifyDelete(Square s, int l, int c) { view.setTile(l,c,null); delete=true; }
        @Override
        public void notifyMove(Square s, int lFrom, int c, int lTo) { waitToShow(); view.moveTile(lFrom, lTo, c); }
        @Override
        public void notifyNew(Square s, int l, int c) { waitToShow(); view.newTile(l, c, SquareTile.newInstance(s)); }
        @Override
        public void notifyPut(Square s, int l, int c) { view.setTile(l,c, SquareTile.newInstance(s)); }
    }
    private ModelListener listener = new ModelListener();

    /**
     * Makes the move corresponding to the mouse event.
     * @return false - If user game aborted (escape key) or terminate the level
     */
    private boolean play() {
        int key = Console.waitKeyPressed(250); // Wait for mouse event or a key
        if (key == KeyEvent.VK_ESCAPE)         // Escape key -> abort game
            return false;
        if (key == Console.MOUSE_EVENT) {      // Is a mouse event
            MouseEvent me = Console.getMouseEvent();
            if (me!=null && me.type==MouseEvent.DOWN) {
                Position pos = view.getModelPosition(me.line, me.col); // Convert mouse position to square coordinates
                if (pos!=null && model.touch(pos.line, pos.col)) {
                    //teste
                    //System.out.println(pos.line+","+pos.col);
                    //-----
                    while( view.stepAnimations() )
                        Console.sleep(50);
                    return ! model.isOver();
                }
            }
        }
        return true;
    }
}
