package view;

import console.*;
import isel.leic.pg.Console;
import model.Squares;
import model.square.Square;
import view.square.SquareTile;

import java.util.ArrayList;

public class StatusPanel extends View {
    private FieldView moves = new FieldView("Moves",1,0,"--");

    private static class GoalView extends View {
        SquareTile square;
        FieldView number = new FieldView(1,3,"00");
        GoalView(int top, Square s) {
            super(top,0,Console.BLACK);
            setSize(SquareTile.SIDE,SquareTile.SIDE+3);
            square = SquareTile.newInstance(s);
            addView(square);
            addView(number);
        }
        public void paint() {
            clear();
            square.paint();
            number.paint();
        }
    }
    private ArrayList<GoalView> goals = new ArrayList<>(Squares.MAX_GOALS);

    public StatusPanel(int left) {
        super(0,left, Console.BLACK);
        setSize(Squares.MAX_GOALS*(SquareTile.SIDE+1)+4, SquareTile.SIDE+2);
        addView(moves);
    }
    public void paint() {
        clear();
        moves.paint();
        for (GoalView goal : goals) goal.paint();
    }
    public void addGoal(Square square) {
        GoalView gv = new GoalView(goals.size()*(SquareTile.SIDE+1)+4,square);
        goals.add(gv);
        addView(gv);
    }
    public void setGoal(int idx, int number) { goals.get(idx).number.setValue(number); }
    public void setMoves(int moves) { this.moves.setValue(moves); }

    public void clearGoals() { goals.clear(); }
}
