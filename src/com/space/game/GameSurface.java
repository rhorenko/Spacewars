package com.space.game;

import java.lang.reflect.Array;
import java.util.Date;
import java.util.Random;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.media.MediaPlayer;
import android.os.Handler;
import android.os.SystemClock;
import android.os.Vibrator;
import android.util.Log;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.View;
import android.widget.RelativeLayout;

import com.space.levels.LevelInterface;

public abstract class GameSurface implements LevelInterface {

	public final static String TAG = "StarWars->GameSurface";

	public static final int NUMBER_OF_INSECT_FRAMES = 5;
	public static final int TYPES_OF_shipS = 9;
	private static Bitmap[][] shipFrames;
	private static Bitmap[][] shipFramesFriendSmasher;
	private static Bitmap[][] shipFramesOriginal;

	public static int shipHeight;
	public static int shipWidth;

	private static Bitmap[] beeFrames;
	private static Bitmap bloodFrame;
	static Bitmap[] bonusFrames;
	static Bitmap dyingbee;
	private static Bitmap mBackgroundImage;
	static SurfaceBitmap ouchBmp;
	public static final Random rand = new Random();
	public static float ratio;
	public static float ratioX;
	public static float ratioY;
	Bitmap.Config CONF;
	float accel;
	int alphaDegree;
	public int shipCounter;

	public int[][] shipAngle;
	public int[][] shipDirection;
	public int[][] shipLife;
	public int[][] shipOrder;
	public boolean[][] smashed;// indicates that ship is destroyed
	public SurfaceBitmap[][] ships;

	int[][] shipOrdinator;
	final int shipSizeX;
	final int shipSizeY;
	int shipSmashedCounter;

	public int[] beeAngle;
	public int[] beeDirection;
	boolean[] beeInScreen;
	public int[] beeOrder;
	int[] beeOrdinator;
	final int beeSizeX;
	final int beeSizeY;
	public SurfaceBitmap[] bees;
	int bigshipAlphaControl;
	SurfaceBitmap bonus;
	int bonusAngle;
	int bonusDir;
	int bonusType;
	int counter;
	SharedPreferences.Editor editor;
	int gameMode;
	boolean[][] inScreen;
	boolean isAlphaing;
	public boolean isBonus;
	boolean isCircleing;
	boolean isKillingBee;
	public boolean isSound;
	int killingBeeCounter;
	int killingBeeIndex;
	public int mCanvasHeight;
	public int mCanvasWidth;
	Context mContext;
	int maxLifes;
	public MediaPlayer mp;
	public int[] numberOfshipsWithType;
	public int numberOfBees;
	public int numberOfObjects;
	SurfaceBitmap[] numbers;
	public int objectPadding;
	int ordinatorCounter;
	public int tempX;
	public int tempY;
	boolean passAheadTouchEvents;
	public boolean passed;
	public boolean paused;
	public int proceed;
	boolean protection;
	public float scale;
	SharedPreferences settings;

	private long startDiscount;
	private GameSurfaceView surfaceView;
	GameSurfaceThread thread;
	int totalOfships;
	public final float touchMargin = 0.23999999463558197F;
	boolean youch;

	static {
		ouchBmp = null;
		bloodFrame = null;
	}

	void killingBee(int paramInt) {
		killingBeeCounter = (1 + killingBeeCounter);
		if (beeAngle[paramInt] % 360 > 0) {
			int[] arrayOfInt = beeAngle;
			arrayOfInt[paramInt] = (4 + arrayOfInt[paramInt]);
		}
		if (killingBeeCounter > 40) {
			isKillingBee = false;
			doEndGame();
		}
	}

	public static void setFixedBitmap(Context context, Bitmap bitmap) {
		Log.d(TAG, "we not set the new theBmps");
		int i = (int) (1.4624999761581421F * 50.0F * 1.1000000238418579F);
		int j = (int) (1.25F * 70.0F * 1.1000000238418579F);
		Bitmap bitmap1 = Bitmap.createScaledBitmap(bitmap, i, j, true);
		System.gc();
		Matrix matrix = new Matrix();
		matrix.postRotate(180.0F);
		Bitmap bitmap2 = Bitmap.createBitmap(bitmap1, 0, 0, i, j, matrix, true);
		System.gc();
		if (bloodFrame == null) {
			bloodFrame = BitmapFactory.decodeResource(context.getResources(),
					2130837563);
			if (bloodFrame == null)
				Log.i("TAG", "Error: bloodFrame is null");
		}
		Bitmap bitmap3 = mergeBitmaps(bitmap2, bloodFrame);
		System.gc();
		if (shipFramesFriendSmasher == null) {
			int[] arrayOfInt = new int[2];
			arrayOfInt[0] = 9;
			arrayOfInt[1] = 5;
			shipFramesFriendSmasher = (Bitmap[][]) Array.newInstance(
					Bitmap.class, arrayOfInt);
		}
		byte b1 = 0;
		if (b1 >= 9) {
			shipFrames = shipFramesFriendSmasher;
			return;
		}
		byte b2 = 0;
		while (true) {
			while (b2 >= 4) {
				shipFramesFriendSmasher[b1][4] = bitmap3;
				++b1;
			}
			shipFramesFriendSmasher[b1][b2] = bitmap2;
			++b2;
		}
	}

	public void beeWasSmashed(int paramInt) {
		if (isCircleing)
			;
		while (true) {
			while (true) {
				label238: do
					while (true) {
						while (true) {

							int i = 2131034127 + rand.nextInt(1);

							killingBeeCounter = 0;
							if (ouchBmp == null)
								ouchBmp = new SurfaceBitmap(
										BitmapFactory.decodeResource(
												getResources(), 2130837577));
							ouchBmp.setPosition(bees[paramInt].getLeft(), -35
									+ bees[paramInt].getTop());
							if (gameMode != 0)
								break label238;
							if (!(protection))
								break;
							isCircleing = true;
							protection = false;
							editor.putBoolean("Prot", false);
							editor.commit();
						}
						paused = true;
						killingBeeIndex = paramInt;
						bees[paramInt].setBitmap(BitmapFactory.decodeResource(
								getResources(), 2130837556));
						beeAngle[paramInt] = 180;
						killingBee(paramInt);
						isKillingBee = true;
					}
				while (gameMode == 3);
				int j = settings.getInt("Lifes", 0);
				if (j > 1) {
					isCircleing = true;
					if (GameActivity.lifeFlag == -1)
						setLifes(-2);
					while (true) {
						while (true) {
							editor.putInt("Lifes", j + -1);
							editor.commit();
							break;
						}
						setLifes(-1);
					}
				}
				if (!(protection))
					break;
				isCircleing = true;
				protection = false;
				editor.putBoolean("Prot", false);
				editor.commit();
			}
			paused = true;
			killingBeeIndex = paramInt;
			bees[paramInt].setBitmap(BitmapFactory.decodeResource(
					getResources(), 2130837556));
			beeAngle[paramInt] = 180;
			killingBee(paramInt);
			isKillingBee = true;
		}
	}

	void bonusWasSmashed() {
		isBonus = false;
		switch (bonusType) {
		default:
		case 0:
		case 1:
		case 2:
		case 3:
		case 4:
		case 5:
		}
		while (true) {
			while (true) {
				while (true) {
					while (true) {
						while (true) {
							while (true) {
								editor.putBoolean("Bonus", false);
								editor.commit();
								shipSmashedCounter = (1 + shipSmashedCounter);
								int i = 2131034119;
								if (bonusType == 0)
									i = 2131034123;
								mp = MediaPlayer.create(mContext, i);
								mp.setAudioStreamType(3);
								if (isSound)
									mp.start();
								mp.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
									public void onCompletion(
											MediaPlayer paramMediaPlayer) {
										if (paramMediaPlayer != null) {
											if (paramMediaPlayer.isPlaying())
												paramMediaPlayer.stop();
											paramMediaPlayer.release();
										}
									}
								});

								editor.putInt("Lifes",
										1 + settings.getInt("Lifes", 0));
								editor.commit();
								setLifes(1);
								break;
							}
							editor.putInt("Points",
									5 + settings.getInt("Points", 0));
							editor.commit();
							break;
						}
						editor.putInt("Points",
								10 + settings.getInt("Points", 0));
						editor.commit();
						break;
					}
					editor.putInt("Points", 25 + settings.getInt("Points", 0));
					editor.commit();
					break;
				}
				editor.putInt("Points", 50 + settings.getInt("Points", 0));
				editor.commit();
				break;
			}
			editor.putInt("Points", 100 + settings.getInt("Points", 0));
			editor.commit();
			break;
		}
	}

	public void sheWasSmashed(int paramInt1, int paramInt2) {
		int i;
		if (shipLife[paramInt1][paramInt2] <= 1) {
			smashed[paramInt1][paramInt2] = true;
			if (paramInt1 != 7) {
				ships[paramInt1][paramInt2].setBitmap(shipFrames[paramInt1][4]);
				shipSmashedCounter = (1 + shipSmashedCounter);
				if (paramInt1 != 3)
					return;
				editor.putInt("Points", 10 + settings.getInt("Points", 0));
				editor.commit();
				i = 2131034123;
				if (paramInt1 != 0)
					return;
				i = 2131034112 + rand.nextInt(1);
				if (paramInt1 >= 4)
					i = 2131034116;

				mp = MediaPlayer.create(mContext, i);
				if (mp != null) {
					mp.setAudioStreamType(3);
					if ((isSound) && (mp != null)) {
						mp.start();
						mp.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
							public void onCompletion(
									MediaPlayer paramMediaPlayer) {
								if (paramMediaPlayer != null) {
									if (paramMediaPlayer.isPlaying())
										paramMediaPlayer.stop();
									paramMediaPlayer.release();
								}
							}
						});
					}
				}

			}
		}
		while (true) {
			do {
				do {
					while (true) {
						do
							while (true) {
								while (true) {
									while (true) {
										while (true) {
											while (true) {
												while (true) {

													ships[paramInt1][paramInt2]
															.setBitmap(shipFrames[(paramInt1 + 1)][4]);
													break;
												}
												if ((paramInt1 != 4)
														&& (paramInt1 != 5))
													break;
												editor.putInt("Points",
														50 + settings.getInt(
																"Points", 0));
											}
											if (paramInt1 != 6)
												break;
											editor.putInt("Points",
													100 + settings.getInt(
															"Points", 0));
										}
										editor.putInt("Points", 1 + settings
												.getInt("Points", 0));
										break;
									}
									if (paramInt1 != 1)
										break;
									i = 2131034114;
								}
								if (paramInt1 != 2)
									break;
								i = 2131034115;
							}
						while (paramInt1 != 3);
						i = 2131034120;
						break;
					}
					int[] arrayOfInt = shipLife[paramInt1];
					arrayOfInt[paramInt2] = (-1 + arrayOfInt[paramInt2]);

					mp = MediaPlayer.create(mContext, 2131034117);
				} while (mp == null);
				mp.setAudioStreamType(3);
				if (isSound)
					mp.start();
				mp.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
					public void onCompletion(MediaPlayer paramMediaPlayer) {
						if (paramMediaPlayer != null) {
							if (paramMediaPlayer.isPlaying())
								paramMediaPlayer.stop();
							paramMediaPlayer.release();
						}
					}
				});

			} while (paramInt1 < 4);
			bigshipAlphaControl = 1;
		}
	}

	public void simpleDraw() {
		thread.simpleDraw();
	}

	public void start() {
	}

	/**
	 * @author Master
	 *
	 */
	class GameSurfaceThread extends Thread {
		Bitmap.Config CONF;
		private boolean mRun = true;
		private SurfaceHolder mGameSurfaceHolder;
		Paint p;
		private Boolean refreshScreenFlag = false;

		/**
		 * @param paramGameSurface
		 * @param paramSurfaceHolder
		 * @param paramContext
		 * @param paramHandler
		 */
		public GameSurfaceThread(GameSurface paramGameSurface,
				SurfaceHolder paramSurfaceHolder, Context paramContext,
				Handler paramHandler) {
			CONF = Bitmap.Config.ARGB_4444;
			paramGameSurface.maxLifes = paramContext.getSharedPreferences(
					"shipSmasherShop", 0).getInt("MaxLifes", 3);
			GameSurface.ratioX = 1.4624999761581421F;
			GameSurface.ratioY = 1.25F;
			GameSurface.ratio = 1.1000000238418579F;
			GameSurface.shipWidth = (int) (50.0F * GameSurface.ratio * GameSurface.ratioX);
			GameSurface.shipHeight = (int) (70.0F * GameSurface.ratio * GameSurface.ratioY);
			paramGameSurface.isCircleing = false;
			paramGameSurface.isAlphaing = false;
			paramGameSurface.isKillingBee = false;
			paramGameSurface.bigshipAlphaControl = 0;
			paramGameSurface.killingBeeCounter = 0;
			paramGameSurface.alphaDegree = 255;
			paramGameSurface.scale = paramGameSurface.mContext.getResources()
					.getDisplayMetrics().density;
			paramGameSurface.paused = false;
			paramGameSurface.settings = paramGameSurface.mContext
					.getSharedPreferences("AppData", 0);
			paramGameSurface.editor = paramGameSurface.settings.edit();
			GameActivity.lifeFlag = 0;
			paramGameSurface.isSound = paramGameSurface.settings.getBoolean(
					"Sound", true);
			paramGameSurface.accel = paramGameSurface.settings.getFloat(
					"Acceleration", 0F);
			paramGameSurface.protection = paramGameSurface.settings.getBoolean(
					"Prot", false);
			paramGameSurface.gameMode = paramGameSurface.settings.getInt(
					"GameMode", 0);
			mSurfaceHolder = paramSurfaceHolder;
			paramGameSurface.mContext = paramContext;
			Resources localResources = paramContext.getResources();
			if (GameSurface.access$0() == null)
				GameSurface.access$1(BitmapFactory.decodeResource(
						localResources, 2130837558));
			paramGameSurface.numberOfshipsWithType = new int[9];
			int[] arrayOfInt1 = new int[2];
			arrayOfInt1[0] = 9;
			arrayOfInt1[1] = 10;
			paramGameSurface.shipAngle = ((int[][]) Array.newInstance(
					Integer.TYPE, arrayOfInt1));
			int[] arrayOfInt2 = new int[2];
			arrayOfInt2[0] = 9;
			arrayOfInt2[1] = 10;
			paramGameSurface.shipOrder = ((int[][]) Array.newInstance(
					Integer.TYPE, arrayOfInt2));
			int[] arrayOfInt3 = new int[2];
			arrayOfInt3[0] = 9;
			arrayOfInt3[1] = 10;
			paramGameSurface.shipDirection = ((int[][]) Array.newInstance(
					Integer.TYPE, arrayOfInt3));
			int[] arrayOfInt4 = new int[2];
			arrayOfInt4[0] = 9;
			arrayOfInt4[1] = 10;
			paramGameSurface.inScreen = ((boolean[][]) Array.newInstance(
					Boolean.TYPE, arrayOfInt4));
			int[] arrayOfInt5 = new int[2];
			arrayOfInt5[0] = 9;
			arrayOfInt5[1] = 10;
			paramGameSurface.ships = ((SurfaceBitmap[][]) Array.newInstance(
					SurfaceBitmap.class, arrayOfInt5));
			paramGameSurface.bees = new SurfaceBitmap[5];
			paramGameSurface.beeInScreen = new boolean[5];
			paramGameSurface.ordinatorCounter = -1;
			int[] arrayOfInt6 = new int[2];
			arrayOfInt6[0] = 9;
			arrayOfInt6[1] = 10;
			paramGameSurface.shipOrdinator = ((int[][]) Array.newInstance(
					Integer.TYPE, arrayOfInt6));
			paramGameSurface.beeOrdinator = new int[5];
			int[] arrayOfInt7 = new int[2];
			arrayOfInt7[0] = 9;
			arrayOfInt7[1] = 10;
			paramGameSurface.smashed = ((boolean[][]) Array.newInstance(
					Boolean.TYPE, arrayOfInt7));
			byte b1 = 0;
			if (b1 >= 5) {
				b2 = 0;
				if (b2 < 9)
					break label1050;
				int[] arrayOfInt8 = new int[2];
				arrayOfInt8[0] = 9;
				arrayOfInt8[1] = 10;
				paramGameSurface.shipLife = ((int[][]) Array.newInstance(
						Integer.TYPE, arrayOfInt8));
				if (GameSurface.access$2() == null) {
					int[] arrayOfInt9 = new int[2];
					arrayOfInt9[0] = 9;
					arrayOfInt9[1] = 5;
					GameSurface.access$3((Bitmap[][]) Array.newInstance(
							Bitmap.class, arrayOfInt9));
					if (GameSurface.access$4() == null)
						GameSurface.access$5(GameSurface.access$2());
					Log.i("BMP LEAK", "BMP LEAK");
					b7 = 0;
					if (b7 < 9)
						break label1094;
				}
				if (GameSurface.access$6() == null) {
					GameSurface.access$7(new Bitmap[5]);
					Log.i("BMP LEAK", "BMP BEE LEAK");
					j = 0;
					if (j < 5)
						break label1262;
				}
				if (GameSurface.dyingbee == null)
					GameSurface.dyingbee = BitmapFactory.decodeResource(
							paramGameSurface.mContext.getResources(),
							2130837577);
				paramGameSurface.shipSmashedCounter = 0;
				paramGameSurface.shipCounter = 0;
				byte b4 = 0;
				byte b6 = 0;
				if (b4 < 9)
					break label1330;
				paramGameSurface.isBonus = false;
				if (GameSurface.bonusFrames == null) {
					GameSurface.bonusFrames = new Bitmap[6];
					GameSurface.bonusFrames[0] = BitmapFactory.decodeResource(
							paramGameSurface.mContext.getResources(),
							2130837663);
					GameSurface.bonusFrames[1] = BitmapFactory.decodeResource(
							paramGameSurface.mContext.getResources(),
							2130837567);
					GameSurface.bonusFrames[2] = BitmapFactory.decodeResource(
							paramGameSurface.mContext.getResources(),
							2130837564);
					GameSurface.bonusFrames[3] = BitmapFactory.decodeResource(
							paramGameSurface.mContext.getResources(),
							2130837566);
					GameSurface.bonusFrames[4] = BitmapFactory.decodeResource(
							paramGameSurface.mContext.getResources(),
							2130837568);
					GameSurface.bonusFrames[5] = BitmapFactory.decodeResource(
							paramGameSurface.mContext.getResources(),
							2130837565);
				}
				if (paramGameSurface.settings.getBoolean("Bonus", false)) {
					paramGameSurface.isBonus = true;
					paramGameSurface.bonus = new SurfaceBitmap();
					if (GameSurface.rand.nextInt(2) != 1)
						return;
					b6 = 1;
					paramGameSurface.bonusDir = b6;
					paramGameSurface.bonusAngle = 180;
					paramGameSurface.shipCounter = 1;
					if ((paramGameSurface.settings.getInt("Lifes", 0) >= paramGameSurface.maxLifes)
							|| (!(paramGameSurface.settings.getBoolean(
									"Bonus-Life", false))))
						break label1425;
					paramGameSurface.bonusType = 0;
				}
			}
			while (true) {
				while (true) {
					int i;
					while (true) {
						while (true) {
							while (true) {
								while (true) {
									while (true) {
										while (true) {
											paramGameSurface.bonus.setPosition(
													100, -300);
											paramGameSurface.bonus
													.setBitmap(GameSurface.bonusFrames[paramGameSurface.bonusType]);
											paramGameSurface.bonus
													.setBitmap(Bitmap
															.createScaledBitmap(
																	paramGameSurface.bonus
																			.bitmap(),
																	(int) (1.1000000238418579F * paramGameSurface.bonus
																			.bitmap()
																			.getWidth()),
																	(int) (1.1000000238418579F * paramGameSurface.bonus
																			.bitmap()
																			.getHeight()),
																	true));
											paramGameSurface.startPositions();
											return;
											paramGameSurface.beeInScreen[b1] = true;
											++b1;
										}
										byte b3 = 0;
										while (true) {
											while (b3 >= 10)
												++b2;
											paramGameSurface.inScreen[b2][b3] = 1;
											paramGameSurface.smashed[b2][b3] = 0;
											++b3;
										}
										System.gc();
										int k = 0;
										while (true) {
											while (k >= 5)
												++b7;
											GameSurface.access$2()[b7][k] = BitmapFactory
													.decodeResource(
															paramGameSurface.mContext
																	.getResources(),
															k + 2130837505 + b7
																	* 5);
											GameSurface.access$2()[b7][k] = GameSurface
													.access$2()[b7][k].copy(
													CONF, false);
											if ((b7 != 4) && (b7 != 6))
												GameSurface.access$2()[b7][k] = Bitmap
														.createScaledBitmap(
																GameSurface
																		.access$2()[b7][k],
																GameSurface.shipWidth,
																GameSurface.shipHeight,
																true);
											if (b7 == 6)
												GameSurface.access$2()[b7][k] = Bitmap
														.createScaledBitmap(
																GameSurface
																		.access$2()[b7][k],
																(int) (2.5F * GameSurface.shipWidth),
																(int) (2.5F * GameSurface.shipHeight),
																true);
											++k;
										}
										GameSurface.access$6()[j] = BitmapFactory
												.decodeResource(
														paramGameSurface.mContext
																.getResources(),
														2130837552 + j);
										GameSurface.access$6()[j] = GameSurface
												.access$6()[j]
												.copy(CONF, false);
										GameSurface.access$6()[j] = Bitmap
												.createScaledBitmap(
														GameSurface.access$6()[j],
														84, 112, true);
										++j;
									}
									label1330: System.gc();
									byte b5 = 0;
									while (true) {
										while (b5 >= 5)
											++b4;
										paramGameSurface.shipLife[b4][b5] = 1;
										if (b4 == 4)
											paramGameSurface.shipLife[b4][b5] = 3;
										if (b4 == 6)
											paramGameSurface.shipLife[b4][b5] = 5;
										if (b4 == 7)
											paramGameSurface.shipLife[b4][b5] = 2;
										++b5;
									}
									label1418: b6 = -1;
								}
								label1425: i = GameSurface.rand.nextInt(100);
								if (i >= 21)
									break;
								paramGameSurface.bonusType = 1;
							}
							if (i >= 49)
								break;
							paramGameSurface.bonusType = 2;
						}
						if (i >= 72)
							break;
						paramGameSurface.bonusType = 3;
					}
					if (i >= 87)
						break;
					paramGameSurface.bonusType = 4;
				}
				paramGameSurface.bonusType = 5;
			}
		}

		/**
		 * Callback invoked when the surface dimensions change.
		 */
		public void setSurfaceSize(int width, int height)
		{
			// synchronized to make sure these all change atomically
			synchronized (mGameSurfaceHolder)
			{
				mBackgroundImage = Bitmap.createScaledBitmap(mBackgroundImage,
						width, height, true);
			}
		}
		
		public void doDraw(Canvas canvas) {//?

		
		}

		/**
		 * Pauses the game.
		 */
		public void pause()
		{
			synchronized (mGameSurfaceHolder)
			{
				if (paused == false)
				{
					paused=true;
				}
			}
		}
		
		/**
		 * Unpauses the game.
		 */
		public void unpause()
		{
			synchronized (mGameSurfaceHolder)
			{
				if (paused != false)
				{
					paused=false;
				}
			}
		}
		

		public void doStop() {
			CONF = false;
		}

		boolean onTouchEvent(MotionEvent paramMotionEvent) {
			float f;
			byte b1;
			int j;
			int k;
			byte b2;
			if (GameSurface.this.paused) {
				b2 = 0;
				return b2;
			}
			if (GameSurface.this.acceleration() < 26.0D) {
				f = 26.0F;
				b1 = 0;
				if (b1 < 9)
					break;
				j = (int) (13.0F * GameSurface.this.scale);
				k = 0;
			}
			while (true) {
				while (true) {
					while (true) {
						if (k < GameSurface.this.numberOfBees)
							break label648;
						if ((GameSurface.this.isBonus)
								&& (GameSurface.this.bonus != null)
								&& (!(GameSurface.this.isKillingBee))
								&& ((int) paramMotionEvent.getX() > GameSurface.this.bonus
										.getLeft()
										- (int) (0.23999999463558197F * GameSurface.this.bonus
												.getWidth() * GameSurface.this.scale))
								&& ((int) paramMotionEvent.getX() < GameSurface.this.bonus
										.getLeft()
										+ GameSurface.this.bonus.getWidth()
										+ (int) (0.23999999463558197F * GameSurface.this.bonus
												.getWidth() * GameSurface.this.scale))
								&& ((int) paramMotionEvent.getY() > GameSurface.this.bonus
										.getTop()
										- (int) (0.23999999463558197F * GameSurface.this.bonus
												.getHeight() * GameSurface.this.scale))
								&& ((int) paramMotionEvent.getY() < GameSurface.this.bonus
										.getTop()
										+ GameSurface.this.bonus.getHeight()
										+ (int) (0.23999999463558197F * GameSurface.this.bonus
												.getHeight() * GameSurface.this.scale)))
							GameSurface.this.bonusWasSmashed();
						b2 = 1;

					}
					f = GameSurface.this.acceleration();
					break;
				}
				;
				int i = 0;
				while (true) {
					while (i >= GameSurface.this.numberOfshipsWithType[b1])
						++b1;
					if ((!(GameSurface.this.isKillingBee))
							&& (GameSurface.this.smashed[b1][i] == false)
							&& ((int) paramMotionEvent.getX() > GameSurface.this.ships[b1][i]
									.getLeft()
									- (int) (0.23999999463558197F * GameSurface.this.ships[b1][i]
											.getWidth())
									- (int) (0.69999998807907104F * f))
							&& ((int) paramMotionEvent.getX() < GameSurface.this.ships[b1][i]
									.getLeft()
									+ GameSurface.this.ships[b1][i].getWidth()
									+ (int) (0.23999999463558197F * GameSurface.this.ships[b1][i]
											.getWidth())
									+ (int) (0.69999998807907104F * f))
							&& ((int) paramMotionEvent.getY() > GameSurface.this.ships[b1][i]
									.getTop()
									- (int) (0.23999999463558197F * GameSurface.this.ships[b1][i]
											.getHeight())
									- (int) (0.69999998807907104F * f))
							&& ((int) paramMotionEvent.getY() < GameSurface.this.ships[b1][i]
									.getTop()
									+ GameSurface.this.ships[b1][i].getHeight()
									+ (int) (0.23999999463558197F * GameSurface.this.ships[b1][i]
											.getHeight())
									+ (int) (0.69999998807907104F * f))
							&& ((((b1 != 4) && (b1 != 6) && (b1 != 7)) || (paramMotionEvent
									.getAction() == 0))))
						GameSurface.this.sheWasSmashed(b1, i);
					++i;
				}
				if ((!(GameSurface.this.isKillingBee))
						&& (!(GameSurface.this.isAlphaing))
						&& ((int) paramMotionEvent.getX() > j
								+ GameSurface.this.bees[k].getLeft())
						&& ((int) paramMotionEvent.getX() < GameSurface.this.bees[k]
								.getLeft()
								+ GameSurface.this.bees[k].getWidth() - j)
						&& ((int) paramMotionEvent.getY() > j
								+ GameSurface.this.bees[k].getTop())
						&& ((int) paramMotionEvent.getY() < GameSurface.this.bees[k]
								.getTop()
								+ GameSurface.this.bees[k].getHeight() - j))
					label648: GameSurface.this.beeWasSmashed(k);
				++k;
			}
		}

		public void refreshScreen() {
			refreshScreenFlag = true;
		}

		public void resetVars() {
			GameSurface.this.numberOfshipsWithType = new int[7];
			GameSurface.this.numberOfBees = 0;
			GameSurface.this.numberOfObjects = 0;
		}

		public void run() {
			

			while (mGameRun)
			{
				Canvas c = null;
				try
				{
					c = mGameSurfaceHolder.lockCanvas(null);
					synchronized (mGameSurfaceHolder)
					{
						if (mGameState == STATE_RUNNING)
						{
							updatePlayerUnit();
						}

						doDraw(c);
					}
				} finally
				{
					if (c != null)
					{
						mGameSurfaceHolder.unlockCanvasAndPost(c);
					}
				}
			}

			return;
		
		}

		

		
	}

	//--------------------------------------Start------------------------------------
		
	public GameSurface() {// Constructor
		shipSizeX = 50;
		shipSizeY = 70;
		beeSizeX = 60;
		beeSizeY = 80;
		CONF = Bitmap.Config.ARGB_4444;
		startDiscount = 0L;
		counter = 0;
	}

	public void playMusic(MediaPlayer mp, int resId) {
		mp = MediaPlayer.create(mContext, resId);
		mp.setAudioStreamType(3);
		if (settings.getBoolean("Sound", true)) {
			mp.start();
		} else {
			return;
		}
		;
		mp.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
			public void onCompletion(MediaPlayer paramMediaPlayer) {
				if (paramMediaPlayer != null) {
					if (paramMediaPlayer.isPlaying())
						paramMediaPlayer.stop();
					paramMediaPlayer.release();
				}
			}
		});
	}

	public float getAccel() {
		return accel;
	}

	public void acceleration(float acceleration) {
		editor.putFloat("Acceleration", acceleration);
		editor.commit();
	}

	private Resources getResources() {
		return surfaceView.getResources();
	}

	public static Bitmap mergeBitmaps(Bitmap bitmap1, Bitmap bitmap2) {
		Bitmap bitmap = Bitmap.createBitmap(bitmap1);
		new Canvas(bitmap).drawBitmap(bitmap2, 0F, 0F, null);
		return bitmap;
	}

	void doEndGame() {
		if (settings.getBoolean("Vibration", true))
			((Vibrator) mContext.getSystemService("vibrator")).vibrate(200);
		GameActivity.surfaceAction = 3;// ?
		SharedPreferences.Editor localEditor = mContext.getSharedPreferences(
				"AppData", 0).edit();
		localEditor.putBoolean("ShouldContinue", false);
		localEditor.commit();
		passed = true;
	}

	public void doPause() {
		thread.pause();
	}

	public void doResume() {
		thread.unpause();
	}

	public void doStop() {
		passAheadTouchEvents = false;
		thread.doStop();
	}

	public View getSurfaceView() {
		return surfaceView;
	}
	/**
	 * Gets the game thread.
	 * @return GameThread
	 */
	public GameSurfaceThread getThread() {
		return thread;
	}

	void killingBeeDraw(Canvas paramCanvas) {
		ouchBmp.draw(paramCanvas, 0);
	}

	public void refreshScreen() {
		thread.refreshScreen();
	}

	public void resetVars() {
		thread.resetVars();
	}

	public void setLayoutParams(RelativeLayout.LayoutParams paramLayoutParams) {
		surfaceView.setLayoutParams(paramLayoutParams);
	}

	public void setLifes(int lifes) {
		GameActivity.lifeFlag = lifes;// ?
	}

	public static void restoreOriginalships() {
		shipFrames = shipFramesOriginal;
	}

	public void setup(Context context, GameSurfaceView gameSurfaceView) { // ?
		surfaceView = gameSurfaceView;
		surfaceView.setGameSurface(this);
		mContext = context;
		SurfaceHolder surfaceHolder = gameSurfaceView.getHolder();
		surfaceHolder.addCallback(gameSurfaceView);
		passAheadTouchEvents = true;
		thread = new GameSurfaceThread(this, surfaceHolder, mContext,
				new Handler() {
					public void handleMessage() {
					}
				});
		gameSurfaceView.setFocusable(true);
	}
	
	//--------------------------------------End--------------------------------------

}