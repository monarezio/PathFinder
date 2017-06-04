package net.zdendukmonarezio.pathfinder.presentation.game;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
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
        this.fieldWidth = fieldWidth;
        setClickable(true);

        gridPaint = new Paint();
        gridPaint.setColor(gameField == Field.SOLID ? ContextCompat.getColor(getContext(), R.color.anonFieldColor) : gameField == Field.AIR ?
                ContextCompat.getColor(getContext(), R.color.blueFieldColor) : gameField == Field.PLAYER ? ContextCompat.getColor(getContext(), R.color.colorPrimaryDark) :
                gameField == Field.ENEMY ? ContextCompat.getColor(getContext(), R.color.redFieldColor)
                        : gameField == Field.FINISH ? ContextCompat.getColor(getContext(), R.color.colorAccent) : ContextCompat.getColor(getContext(), R.color.white));
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

        /*int width = getWidth();
        int height = getHeight();*/

        Rect imageBounds = canvas.getClipBounds();
        if (gameField == Field.ENEMY) {
            drawImageField(canvas, imageBounds, fieldWidth, gridPaint, R.drawable.ic_bug_report_black_48dp);
        } else if (gameField == Field.PLAYER) {
            drawImageField(canvas, imageBounds, fieldWidth, gridPaint, R.drawable.ic_face_black_48dp);
        } else if (gameField == Field.FINISH) {
            drawImageField(canvas, imageBounds, fieldWidth, gridPaint, R.drawable.ic_room_black_48dp);
        } else {
            canvas.drawRect(0, 0, fieldWidth, fieldWidth, gridPaint);
            /*canvas.drawRoundRect(new RectF(10, 10, width, height), 50, 50, gridPaint);*/
        }
    }

    private void drawImageField(Canvas canvas, Rect imageBounds, int fieldWidth, Paint gridPaint, int imageId) {
        gridPaint.setColor(ContextCompat.getColor(getContext(), R.color.blueFieldColor));
        canvas.drawRect(0, 0, fieldWidth, fieldWidth, gridPaint);
        Drawable image = getResources().getDrawable(imageId);
        image.setBounds(imageBounds);
        image.setColorFilter(getResources().getColor(R.color.redFieldColor), PorterDuff.Mode.SRC_IN);
        image.draw(canvas);
    }

    public int getRow() {
        return row;
    }

    public int getColumn() {
        return column;
    }
}
