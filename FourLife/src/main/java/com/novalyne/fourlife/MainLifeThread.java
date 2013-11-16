package com.novalyne.fourlife;

import android.graphics.Canvas;
import android.view.SurfaceHolder;
import java.lang.Thread;

public class MainLifeThread extends Thread {
    private SurfaceHolder holder;
    private LifeView view;
    private boolean running = false;

    public MainLifeThread(SurfaceHolder holder, LifeView view) {
        this.holder = holder;
        this.view = view;
    }

    public void setRunning(boolean b) {
        this.running = b;
    }

    @Override
    public void run() {
        while (running) {
            view.onUpdate();

            Canvas c = null;
            try {
                c = holder.lockCanvas();
                synchronized (holder) {
                    view.onDraw(c);
                }

                sleep(500);
            }
            catch (InterruptedException e) {
                e.printStackTrace();
            }
            finally {
                if (c != null) {
                    holder.unlockCanvasAndPost(c);
                }
            }
        }
    }
}
