package com.basil.android.diagonalcutview;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.widget.FrameLayout;
import android.widget.ImageView;

/**
 * Created by basi on 6/10/16.
 */

public class DiagonalView extends FrameLayout {

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

        mPaint.setColor(mDiagonalColor);
        mTypedArray.recycle();

        setWillNotDraw(false);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        calculateLayout();
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        if(changed)calculateLayout();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        canvas.clipPath(mPath);
        super.onDraw(canvas);

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
        this.mBackgroundColor = color;
        invalidate();
    }

    public void setDiagonalColor(int color) {
        this.mDiagonalColor = color;
        invalidate();
    }




    private void calculateLayout(){
        mHeight = getMeasuredHeight();
        mWidth = getMeasuredWidth();
        if(mWidth > 0 && mHeight > 0) {

            float perpendicularHeight = (float) (mWidth * Math.tan(Math.toRadians(mAngle)));

            if (mDiagonalGravity == RIGHT) {
                mPath.moveTo(0, 0);
                mPath.lineTo(mWidth, 0);
                mPath.lineTo(mWidth, mHeight);
                mPath.lineTo(0, mHeight - perpendicularHeight);
                mPath.lineTo(0, 0);
            } else {
                mPath.moveTo(0, 0);
                mPath.lineTo(mWidth, 0);
                mPath.lineTo(mWidth, mHeight - perpendicularHeight);
                mPath.lineTo(0, mHeight);
                mPath.lineTo(0, 0);
            }

            //Bitmap bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
            //Canvas mCanvas = new Canvas(bitmap);
            //mCanvas.drawPath(path, paint);
            //setBackgroundDrawable(new BitmapDrawable(bitmap));
        }
    }
}
