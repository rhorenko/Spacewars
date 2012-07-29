/**
 * 
 */
package com.space.wars;

import java.util.ArrayList;
import java.util.Random;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.Menu;
import android.webkit.CookieManager;
import android.webkit.CookieSyncManager;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.space.game.InitialView;
import com.space.game.R;
import com.space.menu.CustomMenu;
import com.space.menu.CustomMenu.OnMenuItemSelectedListener;
import com.space.menu.CustomMenuItem;

/**
 * @author Roman Gorenko It's main game window
 * 
 */
public class MainActivity extends Activity implements
		OnMenuItemSelectedListener {	
	/**
	 * Some global variables.
	 */
	public final static String TAG = "StarWars->GameActivity";
	private CustomMenu mMenu;
	public static final int MENU_ITEM_1 = 1;
	public static final int MENU_ITEM_2 = 2;
	public static final int MENU_ITEM_3 = 3;
	public static final int MENU_ITEM_4 = 4;
	public static final int MENU_ITEM_5 = 5;
	public static final int MENU_ITEM_6 = 6;
	public static final int MENU_ITEM_7 = 7;

	public static MediaPlayer backgroundMp;
	public static Boolean initialViewCreated = false;

	public static int levelCount;

	public final int shipSizeX = 50;
	public final int shipSizeY = 70;

	int counter;
	public final int delay = 30;
	public boolean gamePaused = false;
	
	ImageView initialShip;
	int initialShipAngle;
	boolean initialShipControl;
	int initialShipDir;
	Bitmap[] initialShipFrames;
	public boolean isRunning;
	private Context mContext;
	
	/**
	 * Always called when an Android activity launches.
	 */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		// create the view
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		// initialize the menu
		mMenu = new CustomMenu(this, this, getLayoutInflater());
		mMenu.setHideOnSelect(true);
		mMenu.setItemsPerLineInPortraitOrientation(4);
		mMenu.setItemsPerLineInLandscapeOrientation(8);
		//load the menu items
        loadMenuItems();
        
		SharedPreferences.Editor mEditor;

		initialViewCreated = true;		

		SharedPreferences mSharedPreferences = getSharedPreferences("AppData",	0);
		mEditor = mSharedPreferences.edit();
		mEditor.putInt("State", 0);
		mEditor.putBoolean("ShouldContinue", false);
		mEditor.putBoolean("GamePaused", false);
		mEditor.commit();

		mContext = getApplicationContext();
		createBackgroundMusic(mContext);
		if (!(mSharedPreferences.contains("Vibration"))) {
			mEditor.putBoolean("Vibration", true);
			mEditor.commit();
		}
		levelCount = 1;		
		
	}

	@Override
	protected void onPause() {
		super.onPause();
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
	}

	@Override
	protected void onResume() {
		super.onResume();
	}
	/**
	 * Catch the menu key.
	 */
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_MENU) {
			doMenu();
			return true; // always eat it!
		}
		return super.onKeyDown(keyCode, event);
	}

	/**
	 * Load up our menu.
	 */
	private void loadMenuItems() {
		// This is kind of a tedious way to load up the menu items.
		// Am sure there is room for improvement.
		ArrayList<CustomMenuItem> menuItems = new ArrayList<CustomMenuItem>();
		CustomMenuItem cmi = new CustomMenuItem();
		cmi.setCaption("First");
		cmi.setImageResourceId(R.drawable.icon1);
		cmi.setId(MENU_ITEM_1);
		menuItems.add(cmi);
		cmi = new CustomMenuItem();
		cmi.setCaption("Second");
		cmi.setImageResourceId(R.drawable.icon2);
		cmi.setId(MENU_ITEM_2);
		menuItems.add(cmi);
		cmi = new CustomMenuItem();
		cmi.setCaption("Third");
		cmi.setImageResourceId(R.drawable.icon3);
		cmi.setId(MENU_ITEM_3);
		menuItems.add(cmi);
		cmi = new CustomMenuItem();
		cmi.setCaption("Fourth");
		cmi.setImageResourceId(R.drawable.icon4);
		cmi.setId(MENU_ITEM_4);
		menuItems.add(cmi);
		if (!mMenu.isShowing())
			try {
				mMenu.setMenuItems(menuItems);
			} catch (Exception e) {
				AlertDialog.Builder alert = new AlertDialog.Builder(this);
				alert.setTitle("Egads!");
				alert.setMessage(e.getMessage());
				alert.show();
			}
	}

	/**
	 * Toggle our menu on user pressing the menu key.
	 */
	private void doMenu() {
		if (mMenu.isShowing()) {
			mMenu.hide();
		} else {
			// Note it doesn't matter what widget you send the menu as long as
			// it gets view.
			mMenu.show(findViewById(R.id.any_old_widget));
		}
	}

	/**
	 * For the demo just toast the item selected.
	 */

	public void MenuItemSelectedEvent(CustomMenuItem selection) {
		Toast t = Toast.makeText(this,
				"You selected item #" + Integer.toString(selection.getId()),
				Toast.LENGTH_SHORT);
		t.setGravity(Gravity.CENTER, 0, 0);
		t.show();
	}

	/**
	 * Plays background music from resource 
	 * @param montext
	 */
	public static void createBackgroundMusic(Context context) {
		boolean bool = context.getSharedPreferences("AppData", 0)
				.getBoolean("Sound", true);
		if (backgroundMp == null)
			if (bool) {
				
				backgroundMp = MediaPlayer.create(context, R.raw.intro);
				if (backgroundMp != null) {
					backgroundMp.setAudioStreamType(3);
					backgroundMp.setLooping(true);
					backgroundMp.start();
					backgroundMp
							.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
								public void onCompletion(
										MediaPlayer paramMediaPlayer) {
									if (paramMediaPlayer != null) {
										if ((!(InitialView.backgroundMpErrored
												.booleanValue()))
												&& (paramMediaPlayer
														.isPlaying()))
											paramMediaPlayer.stop();
										paramMediaPlayer.release();
									}
								}
							});

				}
			}

		backgroundMp.release();
		backgroundMp = null;
		createBackgroundMusic(context);

	}
}
