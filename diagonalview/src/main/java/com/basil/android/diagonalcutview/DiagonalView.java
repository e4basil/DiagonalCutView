package com.basil.android.diagonalcutview;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.widget.ImageView;

/**
 * Created by basi on 6/10/16.
 */

public class DiagonalView extends ImageView {

    private Context mContext;

    // RIGHT and LEFT would be the gravity of diagonal
    //  if diagonalGravity is LEFT then diagonal will start from left
    //  and start increasing to RIGHT and reverse if gravity is RIGHT

    public static String RIGHT = "right";
    public static String LEFT = "left";

    private String mDiagonalGravity = DiagonalView.LEFT;
    // mHeight is the height of view
    private int mHeight = 0;

    // mAngle is the angle at which the diagonal
    private float mAngle = 15;

    // mWidth is the width of view
    private int mWidth = 0;

    Path mPath;
    Paint mPaint;

    // optional values
    // diagonalColor is the color of diagonal color
    // backgroundColor is the color of tint on ImageView

    private int mDiagonalColor;
    private int mBackgroundColor;

    public DiagonalView(Context context) {
        super(context);
        init(context, null);
    }


    public DiagonalView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }


    public DiagonalView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }


    // initialization
    private void init(Context context, AttributeSet attrs) {

        mPath = new Path();
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);

        TypedArray mTypedArray = getContext().obtainStyledAttributes(attrs, R.styleable.diagonalcutview, 0, 0);

        mAngle = mTypedArray.getInt(R.styleable.diagonalcutview_angle, 0);
        mDiagonalColor = mTypedArray.getColor(R.styleable.diagonalcutview_diagonal_color, Color.WHITE);
        if (mTypedArray.hasValue(R.styleable.diagonalcutview_diagonal_gravity)) {
            mDiagonalGravity = mTypedArray.getString(R.styleable.diagonalcutview_diagonal_gravity);
        }
        mBackgroundColor = mTypedArray.getColor(R.styleable.diagonalcutview_background_color, Color.TRANSPARENT);

        mTypedArray.recycle();
        mPaint.setColor(mDiagonalColor);
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        canvas.drawColor(mBackgroundColor);

        mHeight = getMeasuredHeight();
        mWidth = getMeasuredWidth();

        float perpendicularHeight = (float) (mWidth * Math.tan(Math.toRadians(mAngle)));

        if (mDiagonalGravity.equals("right")) {
            mPath.moveTo(mWidth, mHeight);
            mPath.lineTo(0, mHeight - perpendicularHeight);
            mPath.lineTo(0, mHeight + 1);
        } else {
            mPath.moveTo(0, mHeight);
            mPath.lineTo(mWidth, mHeight - perpendicularHeight);
            mPath.lineTo(mWidth, mHeight + 1);
        }

        canvas.drawPath(mPath, mPaint);
    }


    public void setAngle(float angle) {
        mPath.reset();
        this.mAngle = angle;
        invalidate();
    }

    public void setDiagonalGravity(String gravity) {
        mPath.reset();
        this.mDiagonalGravity = gravity;
        invalidate();
    }

    public void setBackgroundColor(int color) {
        mBackgroundColor = color;
        invalidate();
    }

    public void setDiagonalColor(int color) {
        mDiagonalColor = color;
        invalidate();
    }
}
