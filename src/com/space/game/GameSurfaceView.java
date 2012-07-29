package com.space.game;

import android.content.Context;
import android.util.Log;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

public class GameSurfaceView extends SurfaceView implements
		SurfaceHolder.Callback {
	public final static String TAG = "SpaceWars->GameSurfaceView";

	private GameSurface mGameSurface;

	public GameSurfaceView(Context context) {
		super(context);
		
	}

	public boolean onTouchEvent(MotionEvent motionEvent) {
		return mGameSurface.thread.onTouchEvent(motionEvent);
	}

	public void setGameSurface(GameSurface gameSurface) {
		mGameSurface = gameSurface;
	}

	public void setSurfaceSize(int paramInt1, int paramInt2) {
		mGameSurface.getThread().setSurfaceSize(paramInt1, paramInt2);
	}

	public void stop() {
		try {
			if (mGameSurface != null)
				mGameSurface.thread.join();
			return;
		} catch (InterruptedException localInterruptedException) {
			Log.i(TAG, localInterruptedException.getMessage());
		}
	}

	public void surfaceChanged(SurfaceHolder paramSurfaceHolder, int paramInt1,
			int paramInt2, int paramInt3) {
		if (this.mGameSurface != null)
			mGameSurface.thread.setSurfaceSize(paramInt2, paramInt3);
	}

	public void surfaceCreated(SurfaceHolder paramSurfaceHolder) {
		try {
			if (!(mGameSurface.thread.isAlive()))
				mGameSurface.thread.start();
			return;
		} catch (IllegalThreadStateException localIllegalThreadStateException) {
			Log.i(TAG, localIllegalThreadStateException.getMessage());
		}
	}

	public void surfaceDestroyed(SurfaceHolder paramSurfaceHolder) {
	}
}