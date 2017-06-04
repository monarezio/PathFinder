package net.zdendukmonarezio.pathfinder.presentation.game;

import android.content.Context;
import android.graphics.Point;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
import android.view.Display;
import android.view.WindowManager;
import android.widget.GridLayout;

import net.zdendukmonarezio.pathfinder.R;
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
                BoardFieldWidget field = new BoardFieldWidget(getContext(), gameBoard.getFields().get(r).get(c), r, c, getFieldWidth(gameBoard.getColumns()));
                addView(field);
            }
        }
    }
    
    private int getFieldWidth(int gameSize) {
        WindowManager wm = (WindowManager) getContext().getSystemService(Context.WINDOW_SERVICE);
        Display display = wm.getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        int wWidth = size.x;
        return ((wWidth - getResources().getDimensionPixelSize(R.dimen.gameBoard_horizontal_margin) * 2) - (gameSize - 1) * getResources().getDimensionPixelSize(R.dimen.field_margin)) / gameSize;
    }
}
