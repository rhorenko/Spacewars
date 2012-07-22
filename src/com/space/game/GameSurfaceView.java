package com.space.game;

import android.content.Context;
import android.util.Log;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

public class GameSurfaceView extends SurfaceView implements
		SurfaceHolder.Callback {
	public final static String TAG = "SpaceWars->GameSurfaceView";

	private GameSurface gameSurface;

	public GameSurfaceView(Context paramContext) {
		super(paramContext);
		
	}

	public boolean onTouchEvent(MotionEvent paramMotionEvent) {
		return gameSurface.thread.onTouchEvent(paramMotionEvent);
	}

	public void setGameSurface(GameSurface paramGameSurface) {
		gameSurface = paramGameSurface;
	}

	public void setSurfaceSize(int paramInt1, int paramInt2) {
		gameSurface.getThread().setSurfaceSize(paramInt1, paramInt2);
	}

	public void stop() {
		try {
			if (gameSurface != null)
				gameSurface.thread.join();
			return;
		} catch (InterruptedException localInterruptedException) {
			Log.i(TAG, localInterruptedException.getMessage());
		}
	}

	public void surfaceChanged(SurfaceHolder paramSurfaceHolder, int paramInt1,
			int paramInt2, int paramInt3) {
		if (this.gameSurface != null)
			gameSurface.thread.setSurfaceSize(paramInt2, paramInt3);
	}

	public void surfaceCreated(SurfaceHolder paramSurfaceHolder) {
		try {
			if (!(gameSurface.thread.isAlive()))
				gameSurface.thread.start();
			return;
		} catch (IllegalThreadStateException localIllegalThreadStateException) {
			Log.i(TAG, localIllegalThreadStateException.getMessage());
		}
	}

	public void surfaceDestroyed(SurfaceHolder paramSurfaceHolder) {
	}
}