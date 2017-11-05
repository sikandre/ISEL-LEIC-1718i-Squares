package model;

import model.square.*;
import view.StatusPanel;

import java.util.ArrayList;

public class Squares {
    public static final int MAX_COLORS = 6;
    public static final int MAX_GOALS = 6;
    public static final int HEIGHT = 10, WIDTH = 10;
    private int totalMoves;

    private Square[][] grid = new Square[HEIGHT][WIDTH];

    public Squares(int moves) {
        totalMoves = moves;
        Square.model = this;
    }

    public Square getSquare(int l, int c) {return (l < 0 || l >= HEIGHT || c < 0 || c >= WIDTH) ? null : grid[l][c];}

    void putSquare(Square cell, int l, int c) {grid[l][c] = cell;}

    public int getTotalMoves() {return totalMoves;}

    public boolean isOver() {return totalMoves == 0 || isWinner();}

    public boolean isWinner() {
        for (Goal goal : goals)
            if (goal.number > 0) return false;
        return true;
    }

    public static class Goal {
        public Square square;
        public int number;

        Goal(Square s, int num) {
            square = s;
            number = num;
        }

        boolean equals(Goal g) {return square.equals(g);}
    }



    private ArrayList<Goal> goals = new ArrayList<>(MAX_GOALS);

    public interface Listener {
        void notifyDelete(Square s, int l, int c);

        void notifyMove(Square s, int lFrom, int c, int lTo);

        void notifyNew(Square s, int l, int c);

        void notifyPut(Square s, int l, int c);
    }

    private Listener listener = null;

    public void setListener(Listener l) {listener = l;}

    public int getNumGoals() {return goals.size();}

    public Goal getGoal(int i) {return goals.get(i);}

    public boolean addGoal(Goal goal) {
        goals.add(goal);
        return true;
    }

    public void moveSquare() {
        for (int l = HEIGHT - 1; l > 0; l--){//leitura do array para encontar o primeiro null
            for (int c = WIDTH - 1; c >= 0; c--) {
                if (grid[l][c] == null) {
                    for (int line = l-1; line >= 0; line--)
                        if (grid[line][c]!=null && grid[line][c].isMoveble()) {
                            grid[l][c] = grid[line][c];
                            grid[line][c] = null;
                            if (listener != null)
                                listener.notifyMove(grid[l][c],line,c,l);
                            break;

                        }
                }
            }
        }
    }

    public void changeSquare(Square square, int line, int col) {
        grid[line][col]= square;
        if (listener != null)
            listener.notifyPut(square,line,col);
        putSquare(square, line,col);
    }

    public boolean touch(int line, int col) {
        Square s = grid[line][col];
        if(s.touch(line,col)) {
            destroySquare(line, col);
            moveSquare();
            createNewSquare();
            return true;
        }
        return  false;
    }

    public boolean updateGoalNumber(Square s){
        Goal g;
        for (int i = 0; i < getNumGoals(); ++i) {
            g = goals.get(i);
            if (s.getColor() == g.square.getColor()){
                goals.set(i, g);
                g.number--;
                return true;
            }
        }
        return false;
    }


    public void destroySquare(int line, int col) {
        specialSquareIsSelected();
        Square s = grid[line][col];
        for (int i = grid.length - 1; i >= 0; i--) {
            for (int j = grid[i].length - 1; j >= 0; j--) {
                if (grid[i][j] != null && grid[i][j].isSelected()) {
                    updateGoalNumber(grid[i][j]);
                    grid[i][j] = null;
                    if (listener != null)
                        listener.notifyDelete(s,i,j);
                }
            }
        }
        --totalMoves;
    }


    private void specialSquareIsSelected() {
        for (int l = 0; l < HEIGHT; ++l)
            for (int c = 0; c < WIDTH; ++c) {
                Square s = grid[l][c];
                if(s.isSelected() && s.isSpecial())
                    s.checkAroundSquares(l, c);// polimorfismo

            }
    }

    public void createNewSquare(){
        for (int i = grid.length - 1; i >= 0; i--) {
            for (int j = grid[i].length - 1; j >= 0; j--) {
                Square s = grid[j][i];
                if(s == null){
                    grid[j][i] = Square.newInstance('.');
                    s = grid[j][i];
                    if (listener != null)
                        listener.notifyNew(s,j,i);
                }
            }
        }
    }
}