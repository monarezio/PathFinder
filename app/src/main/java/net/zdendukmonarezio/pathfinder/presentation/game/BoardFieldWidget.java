package net.zdendukmonarezio.pathfinder.presentation.game;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.support.v4.content.ContextCompat;
import android.view.Gravity;
import android.view.View;
import android.widget.GridLayout;

import net.zdendukmonarezio.pathfinder.R;
import net.zdendukmonarezio.pathfinder.domain.game.model.utils.Field;

public class BoardFieldWidget extends View {

    private Paint gridPaint;
    private Field gameField;
    private int row;
    private int column;
    private int fieldWidth;


    public BoardFieldWidget(Context context, Field gameField, int row, int column, int fieldWidth) {
        super(context);
        init(gameField, row, column, fieldWidth);
    }


    private void init(Field gameField, int row, int column, int fieldWidth) {
        this.gameField = gameField;
        this.row = row;
        this.column = column;
        setClickable(true);

        gridPaint = new Paint();
        gridPaint.setColor(gameField == Field.SOLID ? ContextCompat.getColor(getContext(), R.color.anonFieldColor) : gameField == Field.AIR ?
                ContextCompat.getColor(getContext(), R.color.blueFieldColor) : gameField == Field.PLAYER ? ContextCompat.getColor(getContext(), R.color.colorPrimaryDark) :
                ContextCompat.getColor(getContext(), R.color.colorAccent));

        int[] attrs = new int[]{R.attr.selectableItemBackground};
        TypedArray typedArray = getContext().obtainStyledAttributes(attrs);
        int backgroundResource = typedArray.getResourceId(0, 0);
        setBackgroundResource(backgroundResource);
        typedArray.recycle();

        float fieldSize = fieldWidth;
        GridLayout.LayoutParams param = new GridLayout.LayoutParams();
        param.height = (int) fieldSize;
        param.width = (int) fieldSize;
        param.rightMargin = (int) getResources().getDimension(R.dimen.field_margin);
        param.topMargin = (int) getResources().getDimension(R.dimen.field_margin);
        param.setGravity(Gravity.CENTER);
        param.rowSpec = GridLayout.spec(row);
        param.columnSpec = GridLayout.spec(column);
        setLayoutParams(param);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        int width = getWidth();
        int height = getHeight();

        canvas.drawRoundRect(new RectF(10, 10, width, height), 50, 50, gridPaint);

    }

    public int getRow() {
        return row;
    }

    public int getColumn() {
        return column;
    }
}
