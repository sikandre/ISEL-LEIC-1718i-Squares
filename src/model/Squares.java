package model;

import model.square.*;

import java.util.ArrayList;

public class Squares {
    public static final int MAX_COLORS = 6;
    public static final int MAX_GOALS = 6;
    public static final int HEIGHT=10, WIDTH=10;

    private Square[][] grid = new Square[HEIGHT][WIDTH];

    //TODO
    //rever
    public Squares(int moves) {
        totalMoves = moves;
        Square.model = this;
    }

    public Square getSquare(int l, int c) {
        return (l<0 || l>=HEIGHT || c<0 || c>=WIDTH) ? null : grid[l][c];
    }


    void putSquare(Square cell, int l, int c) { grid[l][c] = cell; }

    private int totalMoves;

    public boolean isOver() { return totalMoves == 0 || isWinner(); }

    public boolean isWinner() {
        for (Goal goal : goals)
            if (goal.number >0) return false;
        return true;
    }




    public static class Goal {
        public Square square;
        public int number;
        Goal(Square s, int num) { square=s; number=num; }
        boolean equals(Goal g) { return square.equals(g); }
    }

    private ArrayList<Goal> goals = new ArrayList<>(MAX_GOALS);

    public interface Listener {
        void notifyDelete(Square s, int l, int c);
        void notifyMove(Square s, int lFrom, int c, int lTo);
        void notifyNew(Square s, int l, int c);
        void notifyPut(Square s, int l, int c);
    }

    private Listener listener = null;
    public void setListener(Listener l) { listener = l; }

    //TODO
    public int getNumGoals() {
        return goals.size();
    }

    public Goal getGoal(int i) {
        return goals.get(i);
    }
    //REVER
    public boolean addGoal(Goal goal) {
        goals.add(goal);
        return true;
    }


    public void moveSquare(){
        Square s;
        for (int l = HEIGHT-1; l > 1 ; l--) {
            for (int c = WIDTH-1; c > 0; c--) {
                s=grid[l][c];
                if(s==null) {
                    s = grid[l - 1][c];
                    grid[l - 1][c] = null;
                    if (listener != null)
                        listener.notifyMove(s,l,c,l-1);
                    putSquare(s,l,c);
                }

            }
        }
    }

    public void newSquare(Square square, int line, int col) {
        grid[line][col]= square;
        if (listener != null)
            listener.notifyPut(square,line,col);
        putSquare(square, line,col);
    }

    public void destroySquare(Square square, int line, int col) {
        grid[line][col] = null;
        if (listener != null)
            listener.notifyDelete(square,line,col);
    }
    public boolean touch(int line, int col) {
        Square s = grid[line][col];
        if (s == null)return false;
        return s.touch(line, col);
        //polimorfismo com o quadrado
        //return false;
    }

}
