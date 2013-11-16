package com.novalyne.fourlife;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

public class LifeView extends SurfaceView implements SurfaceHolder.Callback {
    private MainLifeThread thread;
    private Paint paint;

    private static final int SPOT_SIZE = 4;
    private static final int GRID_W = 1920 / SPOT_SIZE;
    private static final int GRID_H = 1080 / SPOT_SIZE;

    private boolean grid[][];

    public LifeView(Context context) {
        super(context);
        init();
    }

    public LifeView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public LifeView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();
    }

    public void init() {
        getHolder().addCallback(this);
        setFocusable(true);

        this.paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        this.paint.setStyle(Paint.Style.FILL);
        this.paint.setColor(Color.WHITE);

        grid = new boolean[GRID_W][GRID_H];
        thread = new MainLifeThread(getHolder(), this);
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        thread.setRunning(true);
        thread.start();
    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        boolean retry = true;
        thread.setRunning(false);
        while (retry) {
            try {
                thread.join();
                retry = false;
            }
            catch (InterruptedException e) {
            }
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return super.onTouchEvent(event);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        for(int y=0; y<GRID_H; y++) {
            for(int x=0; x<GRID_W; x++) {
                if (grid[x][y] || true) {
                    canvas.drawCircle(x*2*SPOT_SIZE, y*2*SPOT_SIZE, SPOT_SIZE, this.paint);
                }
            }
        }
    }

    protected void onUpdate() {
        for(int y=0; y<GRID_H; y++) {
            for(int x=0; x<GRID_W; x++) {

            }
        }
    }

    /**
     *
     * (x-1, y-1) (x, y-1) (x+1, y-1)
     *  (x-1, y)   (x, y)   (x+1, y)
     * (x-1, y+1) (x, y+1) (x+1, y+1)
     *
     * @param x
     * @param y
     * @return the neighbor count of the passed-in cell
     */
    private int neighbors(int x, int y) {
        int count = 0;


    }
}
