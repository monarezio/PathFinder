package net.zdendukmonarezio.pathfinder.presentation.game;

import android.content.Context;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
import android.widget.GridLayout;

import net.zdendukmonarezio.pathfinder.domain.game.model.board.Board;

public class GameBoardLayout extends GridLayout {

    public GameBoardLayout(Context context) {
        super(context);
    }

    public GameBoardLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public GameBoardLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public GameBoardLayout(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }


    public void setBoard(Board gameBoard) {
        removeAllViews();
        setRowCount(gameBoard.getRows());
        setColumnCount(gameBoard.getColumns());
        for (int r = 0; r < gameBoard.getRows(); r++) {
            for (int c = 0; c < gameBoard.getColumns(); c++) {
                BoardFieldWidget field = new BoardFieldWidget(getContext(), gameBoard.getFields().get(r).get(c), r, c, 200);
                addView(field);
            }
        }
    }
}
