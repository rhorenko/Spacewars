package com.space.wars;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;

public class Constants
{
  public static String BCFADS_ALTERNATIVE_ID;
  public static String BCFADS_NEW_FULLSCREEN_AD_ID;
  public static String BCFAdsID;
  public static String RELEASE_TYPE;
  public static String RELEASE_TYPE_FREE;
  public static String RELEASE_TYPE_PAID;
  public static int initial_speed_increment = 0;
  public static final String soundIn = "In";
  public static final String soundOut = "Out";

  static
  	{
    BCFAdsID = "4ed91bd9a6260c000300003a";
    BCFADS_NEW_FULLSCREEN_AD_ID = "4fcfb152a0ef3000080000fc";
    BCFADS_ALTERNATIVE_ID = "4f99c1a48efc15000c000017";
    RELEASE_TYPE_PAID = "Paid";
    RELEASE_TYPE_FREE = "Free";
    RELEASE_TYPE = RELEASE_TYPE_FREE;
  	}

  public static class key
  	{
    private static String aBDifficultyLabel;
    public static final String abTiozaoAd = "AB_TiozaoAd";
    public static final String abTiozaoAdDone = "AB_TiozaoAdDone";
    public static final String acceleration = "Acceleration";
    public static final String alreadyOfferedInitial = "InitialProductAlreadyOffered";
    public static final String appData = "AppData";
    public static final String asShop = "AntSmasherShop";
    public static final String askedUserCounter = "AskedUserInfoCounter";
    public static final String askedUserInfo = "AlreadyAskedUserInfo";
    public static final String baby = "BabyMode";
    public static final String bonus = "Bonus";
    public static final String bonusLife = "Bonus-Life";
    public static final float difficulty = 3.5F;
    public static final String firstLevel = "FirstLevel";
    public static final String fun = "FunMode";
    public static final String gameMode = "GameMode";
    public static final String gamePaused = "GamePaused";
    public static final String gameState = "State";
    public static final String gameoverprotection = "Protection";
    public static final String googleAnalyticsId = "UA-21142514-5";
    public static final String kids = "KidsMode";
    public static final String lives = "Lifes";
    public static final String maxlives = "MaxLifes";
    public static final String noads = "NoAds";
    public static final long notifyPeriod = 86400000;
    public static final String numberOfPlayedGames = "NumberOfPlayedGames";
    public static final String numberOfPlayedGamesSinceV29 = "NumberOfPlayedGamesSinceV29";
    public static final String paused = "Paused";
    public static final String points = "Points";
    public static final String productFromPopup = "ProductOfferedFromPopup";
    public static final String prot = "Prot";
    public static final String protectionEnabled = "ProtectionEnabled";
    private static String ratingLabel = null;
    public static final String shouldContinue = "ShouldContinue";
    public static final String showmoreapps = "ShowMoreApps";
    public static final String sound = "Sound";
    public static final String startGameDate = "StartGameDate";
    public static final String surfaceAction = "SurfaceAction";
    public static final String tapJoyControl = "TapJoyControl";
    public static final String tapJoyExplained = "TapJoyExplained";
    public static final String tapJoyPoints = "TapJoyPoints";
    public static final String tapJoyProduct = "TapJoyProduct";
    public static final String tapJoySpentPoints = "TapJoySpentPoints";
    public static final String vibration = "Vibration";

    static
    {
      aBDifficultyLabel = null;
    }

    public static String getABDifficultyLabel(Context paramContext)
    {
      if (aBDifficultyLabel == null);
      try
      {
        aBDifficultyLabel = "AB_" + paramContext.getPackageManager().getPackageInfo(paramContext.getPackageName(), 0).versionName;
        return aBDifficultyLabel;
      }
      catch (Exception localException)
      {
        while (true)
          aBDifficultyLabel = "AB";
      }
    }

    public static final String getRatingLabel(Context paramContext)
    {
      if (ratingLabel == null);
      try
      {
        ratingLabel = "Rating_" + paramContext.getPackageManager().getPackageInfo(paramContext.getPackageName(), 0).versionName;
        return ratingLabel;
      }
      catch (Exception localException)
      {
        while (true)
          ratingLabel = "Rating";
      }
    }
  }

  public class state
  {
    public static final int base = 0;
    public static final int facebook = 10;
    public static final int form = 13;
    public static final int gameover = 7;
    public static final int gameoverlv0 = 3;
    public static final int highscores = 8;
    public static final int menu = 5;
    public static final int moregames = 14;
    public static final int options = 9;
    public static final int playing = 4;
    public static final int playmenu = 12;
    public static final int store = 11;
    public static final int tutorial = 15;
    public static final int twitter = 6;
  }
}