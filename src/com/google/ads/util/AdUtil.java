package com.google.ads.util;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.pm.ResolveInfo;
import android.location.Location;
import android.media.AudioManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Build;
import android.os.Build.VERSION;
import android.provider.Settings.Secure;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.WindowManager;
import android.webkit.WebSettings;
import android.webkit.WebView;
import com.google.ads.AdActivity;
import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.net.HttpURLConnection;
import java.net.URLDecoder;
import java.nio.charset.UnsupportedCharsetException;
import java.security.GeneralSecurityException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public final class AdUtil
{
  public static final int a;
  private static Boolean b;
  private static String c;
  private static String d;
  private static String e;
  private static AudioManager f;
  private static boolean g;
  private static boolean h;
  private static String i;

  static
  {
    int j;
    int k;
    try
    {
      k = Integer.parseInt(Build.VERSION.SDK);
      j = k;
      a = j;
      b = null;
      c = null;
      e = null;
      g = true;
      h = false;
      i = null;
      return;
    }
    catch (NumberFormatException localNumberFormatException)
    {
      while (true)
      {
        a.e("The Android SDK version couldn't be parsed to an int: " + Build.VERSION.SDK);
        a.e("Defaulting to Android SDK version 3.");
        j = 3;
      }
    }
  }

  public static int a()
  {
    byte b1;
    if (a >= 9)
      b1 = 6;
    while (true)
    {
      return b1;
      b1 = 0;
    }
  }

  public static int a(Context paramContext, DisplayMetrics paramDisplayMetrics)
  {
    int j;
    if (a >= 4)
      j = c.a(paramContext, paramDisplayMetrics);
    while (true)
    {
      return j;
      j = paramDisplayMetrics.heightPixels;
    }
  }

  public static DisplayMetrics a(Activity paramActivity)
  {
    DisplayMetrics localDisplayMetrics = new DisplayMetrics();
    paramActivity.getWindowManager().getDefaultDisplay().getMetrics(localDisplayMetrics);
    return localDisplayMetrics;
  }

  public static String a(Context paramContext)
  {
    String str1;
    String str2;
    String str3;
    if (c == null)
    {
      str2 = Settings.Secure.getString(paramContext.getContentResolver(), "android_id");
      if ((str2 == null) || (c()))
      {
        str3 = a("emulator");
        if (str3 != null)
          break label48;
        str1 = null;
      }
    }
    while (true)
    {
      while (true)
      {
        return str1;
        str3 = a(str2);
      }
      label48: c = str3.toUpperCase(Locale.US);
      str1 = c;
    }
  }

  public static String a(Location paramLocation)
  {
    String str2;
    if (paramLocation == null)
      str2 = null;
    while (true)
    {
      return str2;
      Object[] arrayOfObject = new Object[4];
      arrayOfObject[0] = Long.valueOf(1000 * paramLocation.getTime());
      arrayOfObject[1] = Long.valueOf(()(10000000.0D * paramLocation.getLatitude()));
      arrayOfObject[2] = Long.valueOf(()(10000000.0D * paramLocation.getLongitude()));
      arrayOfObject[3] = Long.valueOf(()(1000.0F * paramLocation.getAccuracy()));
      String str1 = b(String.format("role: 6 producer: 24 historical_role: 1 historical_producer: 12 timestamp: %d latlng < latitude_e7: %d longitude_e7: %d> radius: %d", arrayOfObject));
      str2 = "e1+" + str1;
    }
  }

  public static String a(String paramString)
  {
    Object localObject = null;
    if ((paramString != null) && (paramString.length() > 0));
    try
    {
      MessageDigest localMessageDigest = MessageDigest.getInstance("MD5");
      localMessageDigest.update(paramString.getBytes(), 0, paramString.length());
      Object[] arrayOfObject = new Object[1];
      arrayOfObject[0] = new BigInteger(1, localMessageDigest.digest());
      String str = String.format("%032X", arrayOfObject);
      localObject = str;
      return localObject;
    }
    catch (NoSuchAlgorithmException localNoSuchAlgorithmException)
    {
      while (true)
        localObject = paramString.substring(0, 32);
    }
  }

  public static String a(Map<String, Object> paramMap)
  {
    Object localObject = null;
    try
    {
      String str = b(paramMap).toString();
      localObject = str;
      return localObject;
    }
    catch (JSONException localJSONException)
    {
      while (true)
        a.b("JsonException in serialization: ", localJSONException);
    }
  }

  private static JSONArray a(Set<Object> paramSet)
    throws JSONException
  {
    JSONArray localJSONArray2;
    Object localObject;
    JSONArray localJSONArray1 = new JSONArray();
    if ((paramSet == null) || (paramSet.isEmpty()))
    {
      localJSONArray2 = localJSONArray1;
      return localJSONArray2;
    }
    Iterator localIterator = paramSet.iterator();
    while (true)
    {
      if (!(localIterator.hasNext()))
        break label200;
      localObject = localIterator.next();
      if ((!(localObject instanceof String)) && (!(localObject instanceof Integer)) && (!(localObject instanceof Double)) && (!(localObject instanceof Long)) && (!(localObject instanceof Float)))
        break;
      localJSONArray1.put(localObject);
    }
    if (localObject instanceof Map);
    try
    {
      localJSONArray1.put(b((Map)localObject));
    }
    catch (ClassCastException localClassCastException2)
    {
      while (true)
        a.b("Unknown map type in json serialization: ", localClassCastException2);
      if (localObject instanceof Set);
      try
      {
        localJSONArray1.put(a((Set)localObject));
      }
      catch (ClassCastException localClassCastException1)
      {
        while (true)
        {
          while (true)
          {
            while (true)
              a.b("Unknown map type in json serialization: ", localClassCastException1);
            a.e("Unknown value in json serialization: " + localObject);
          }
          label200: localJSONArray2 = localJSONArray1;
        }
      }
    }
  }

  public static void a(WebView paramWebView)
  {
    String str = i(paramWebView.getContext().getApplicationContext());
    paramWebView.getSettings().setUserAgentString(str);
  }

  public static void a(HttpURLConnection paramHttpURLConnection, Context paramContext)
  {
    paramHttpURLConnection.setRequestProperty("User-Agent", i(paramContext));
  }

  public static void a(boolean paramBoolean)
  {
    g = paramBoolean;
  }

  private static boolean a(int paramInt1, int paramInt2, String paramString)
  {
    byte b1;
    if ((paramInt1 & paramInt2) == 0)
    {
      a.b("The android:configChanges value of the com.google.ads.AdActivity must include " + paramString + ".");
      b1 = 0;
    }
    while (true)
    {
      return b1;
      b1 = 1;
    }
  }

  public static boolean a(Uri paramUri)
  {
    byte b1;
    String str = paramUri.getScheme();
    if (("http".equalsIgnoreCase(str)) || ("https".equalsIgnoreCase(str)))
      b1 = 1;
    while (true)
    {
      return b1;
      b1 = 0;
    }
  }

  public static int b()
  {
    byte b1;
    if (a >= 9)
      b1 = 7;
    while (true)
    {
      return b1;
      b1 = 1;
    }
  }

  public static int b(Context paramContext, DisplayMetrics paramDisplayMetrics)
  {
    int j;
    if (a >= 4)
      j = c.b(paramContext, paramDisplayMetrics);
    while (true)
    {
      return j;
      j = paramDisplayMetrics.widthPixels;
    }
  }

  private static String b(String paramString)
  {
    Cipher localCipher;
    try
    {
      localCipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
      byte[] arrayOfByte1 = new byte[16];
      arrayOfByte1[0] = 10;
      arrayOfByte1[1] = 55;
      arrayOfByte1[2] = -112;
      arrayOfByte1[3] = -47;
      arrayOfByte1[4] = -6;
      arrayOfByte1[5] = 7;
      arrayOfByte1[6] = 11;
      arrayOfByte1[7] = 75;
      arrayOfByte1[8] = -7;
      arrayOfByte1[9] = -121;
      arrayOfByte1[10] = 121;
      arrayOfByte1[11] = 69;
      arrayOfByte1[12] = 80;
      arrayOfByte1[13] = -61;
      arrayOfByte1[14] = 15;
      arrayOfByte1[15] = 5;
      localCipher.init(1, new SecretKeySpec(arrayOfByte1, "AES"));
      byte[] arrayOfByte2 = localCipher.getIV();
      byte[] arrayOfByte3 = localCipher.doFinal(paramString.getBytes());
      byte[] arrayOfByte4 = new byte[arrayOfByte2.length + arrayOfByte3.length];
      System.arraycopy(arrayOfByte2, 0, arrayOfByte4, 0, arrayOfByte2.length);
      System.arraycopy(arrayOfByte3, 0, arrayOfByte4, arrayOfByte2.length, arrayOfByte3.length);
      String str2 = b.a(arrayOfByte4);
      String str1 = str2;
      return str1;
    }
    catch (GeneralSecurityException localGeneralSecurityException)
    {
      while (true)
        ??? = null;
    }
  }

  public static HashMap<String, String> b(Uri paramUri)
  {
    HashMap localHashMap;
    String str1;
    byte b1 = 0;
    Object localObject = null;
    if (paramUri == null);
    while (true)
    {
      return localObject;
      localHashMap = new HashMap();
      str1 = paramUri.getEncodedQuery();
      if (str1 != null)
        break;
      localObject = localHashMap;
    }
    try
    {
      String str2;
      int j;
      do
      {
        String[] arrayOfString = str1.split("&");
        byte b2 = arrayOfString.length;
        if (b1 >= b2)
          break label155;
        str2 = arrayOfString[b1];
        j = str2.indexOf(61);
      }
      while (j == -1);
      String str3 = str2.substring(0, j);
      String str4 = str2.substring(j + 1);
      localHashMap.put(URLDecoder.decode(str3, "utf-8"), URLDecoder.decode(str4, "utf-8"));
      label155: ++b1;
    }
    catch (UnsupportedEncodingException localUnsupportedEncodingException)
    {
      a.a(localUnsupportedEncodingException);
    }
    catch (UnsupportedCharsetException localUnsupportedCharsetException)
    {
      a.a(localUnsupportedCharsetException);
    }
    catch (IllegalArgumentException localIllegalArgumentException)
    {
      while (true)
      {
        while (true)
          a.a(localIllegalArgumentException);
        localObject = localHashMap;
      }
    }
  }

  private static JSONObject b(Map<String, Object> paramMap)
    throws JSONException
  {
    JSONObject localJSONObject2;
    String str;
    Object localObject;
    JSONObject localJSONObject1 = new JSONObject();
    if ((paramMap == null) || (paramMap.isEmpty()))
    {
      localJSONObject2 = localJSONObject1;
      return localJSONObject2;
    }
    Iterator localIterator = paramMap.keySet().iterator();
    while (true)
    {
      if (!(localIterator.hasNext()))
        break label224;
      str = (String)localIterator.next();
      localObject = paramMap.get(str);
      if ((!(localObject instanceof String)) && (!(localObject instanceof Integer)) && (!(localObject instanceof Double)) && (!(localObject instanceof Long)) && (!(localObject instanceof Float)))
        break;
      localJSONObject1.put(str, localObject);
    }
    if (localObject instanceof Map);
    try
    {
      localJSONObject1.put(str, b((Map)localObject));
    }
    catch (ClassCastException localClassCastException2)
    {
      while (true)
        a.b("Unknown map type in json serialization: ", localClassCastException2);
      if (localObject instanceof Set);
      try
      {
        localJSONObject1.put(str, a((Set)localObject));
      }
      catch (ClassCastException localClassCastException1)
      {
        while (true)
        {
          while (true)
          {
            while (true)
              a.b("Unknown map type in json serialization: ", localClassCastException1);
            a.e("Unknown value in json serialization: " + localObject);
          }
          label224: localJSONObject2 = localJSONObject1;
        }
      }
    }
  }

  public static boolean b(Context paramContext)
  {
    byte b1 = 0;
    PackageManager localPackageManager = paramContext.getPackageManager();
    String str = paramContext.getPackageName();
    if (localPackageManager.checkPermission("android.permission.INTERNET", str) == -1)
      a.b("INTERNET permissions must be enabled in AndroidManifest.xml.");
    while (true)
    {
      while (true)
      {
        return b1;
        if (localPackageManager.checkPermission("android.permission.ACCESS_NETWORK_STATE", str) != -1)
          break;
        a.b("ACCESS_NETWORK_STATE permissions must be enabled in AndroidManifest.xml.");
      }
      b1 = 1;
    }
  }

  public static boolean c()
  {
    byte b1;
    if (("unknown".equals(Build.BOARD)) && ("generic".equals(Build.DEVICE)) && ("generic".equals(Build.BRAND)))
      b1 = 1;
    while (true)
    {
      return b1;
      b1 = 0;
    }
  }

  public static boolean c(Context paramContext)
  {
    boolean bool;
    if (b != null)
    {
      bool = b.booleanValue();
      return bool;
    }
    ResolveInfo localResolveInfo = paramContext.getPackageManager().resolveActivity(new Intent(paramContext, AdActivity.class), 65536);
    b = Boolean.valueOf(true);
    if ((localResolveInfo == null) || (localResolveInfo.activityInfo == null))
    {
      a.b("Could not find com.google.ads.AdActivity, please make sure it is registered in AndroidManifest.xml.");
      b = Boolean.valueOf(false);
    }
    while (true)
    {
      do
      {
        while (true)
          bool = b.booleanValue();
        if (!(a(localResolveInfo.activityInfo.configChanges, 16, "keyboard")))
          b = Boolean.valueOf(false);
        if (!(a(localResolveInfo.activityInfo.configChanges, 32, "keyboardHidden")))
          b = Boolean.valueOf(false);
        if (!(a(localResolveInfo.activityInfo.configChanges, 128, "orientation")))
          b = Boolean.valueOf(false);
        if (!(a(localResolveInfo.activityInfo.configChanges, 256, "screenLayout")))
          b = Boolean.valueOf(false);
        if (!(a(localResolveInfo.activityInfo.configChanges, 512, "uiMode")))
          b = Boolean.valueOf(false);
        if (!(a(localResolveInfo.activityInfo.configChanges, 1024, "screenSize")))
          b = Boolean.valueOf(false);
      }
      while (a(localResolveInfo.activityInfo.configChanges, 2048, "smallestScreenSize"));
      b = Boolean.valueOf(false);
    }
  }

  public static String d(Context paramContext)
  {
    String str;
    NetworkInfo localNetworkInfo = ((ConnectivityManager)paramContext.getSystemService("connectivity")).getActiveNetworkInfo();
    if (localNetworkInfo == null)
      str = null;
    while (true)
    {
      while (true)
      {
        while (true)
        {
          return str;
          switch (localNetworkInfo.getType())
          {
          default:
            str = "unknown";
          case 0:
          case 1:
          }
        }
        str = "ed";
      }
      str = "wi";
    }
  }

  public static boolean d()
  {
    return g;
  }

  public static String e(Context paramContext)
  {
    if (d == null)
    {
      StringBuilder localStringBuilder = new StringBuilder();
      PackageManager localPackageManager = paramContext.getPackageManager();
      List localList1 = localPackageManager.queryIntentActivities(new Intent("android.intent.action.VIEW", Uri.parse("geo:0,0?q=donuts")), 65536);
      if ((localList1 == null) || (localList1.size() == 0))
        localStringBuilder.append("m");
      List localList2 = localPackageManager.queryIntentActivities(new Intent("android.intent.action.VIEW", Uri.parse("market://search?q=pname:com.google")), 65536);
      if ((localList2 == null) || (localList2.size() == 0))
      {
        if (localStringBuilder.length() > 0)
          localStringBuilder.append(",");
        localStringBuilder.append("a");
      }
      List localList3 = localPackageManager.queryIntentActivities(new Intent("android.intent.action.VIEW", Uri.parse("tel://6509313940")), 65536);
      if ((localList3 == null) || (localList3.size() == 0))
      {
        if (localStringBuilder.length() > 0)
          localStringBuilder.append(",");
        localStringBuilder.append("t");
      }
      d = localStringBuilder.toString();
    }
    return d;
  }

  public static String f(Context paramContext)
  {
    Object localObject = null;
    if (e != null)
    {
      localObject = e;
      label12: return localObject;
    }
    try
    {
      ActivityInfo localActivityInfo;
      PackageInfo localPackageInfo;
      do
      {
        PackageManager localPackageManager;
        do
        {
          ResolveInfo localResolveInfo;
          do
          {
            localPackageManager = paramContext.getPackageManager();
            localResolveInfo = localPackageManager.resolveActivity(new Intent("android.intent.action.VIEW", Uri.parse("market://details?id=com.google.ads")), 0);
          }
          while (localResolveInfo == null);
          localActivityInfo = localResolveInfo.activityInfo;
        }
        while (localActivityInfo == null);
        localPackageInfo = localPackageManager.getPackageInfo(localActivityInfo.packageName, 0);
      }
      while (localPackageInfo == null);
      String str = localPackageInfo.versionCode + "." + localActivityInfo.packageName;
      e = str;
      localObject = str;
    }
    catch (PackageManager.NameNotFoundException localNameNotFoundException)
    {
      break label12:
    }
  }

  public static a g(Context paramContext)
  {
    a locala;
    if (f == null)
      f = (AudioManager)paramContext.getSystemService("audio");
    int j = f.getMode();
    if (c())
      locala = a.e;
    while (true)
    {
      while (true)
      {
        while (true)
        {
          return locala;
          if ((!(f.isMusicActive())) && (!(f.isSpeakerphoneOn())) && (j != 2) && (j != 1))
            break;
          locala = a.d;
        }
        int k = f.getRingerMode();
        if ((k != 0) && (k != 1))
          break;
        locala = a.d;
      }
      locala = a.b;
    }
  }

  public static void h(Context paramContext)
  {
    if (h);
    while (true)
    {
      return;
      IntentFilter localIntentFilter = new IntentFilter();
      localIntentFilter.addAction("android.intent.action.USER_PRESENT");
      localIntentFilter.addAction("android.intent.action.SCREEN_OFF");
      paramContext.registerReceiver(new UserActivityReceiver(), localIntentFilter);
      h = true;
    }
  }

  private static String i(Context paramContext)
  {
    if (i == null)
    {
      String str1 = new WebView(paramContext).getSettings().getUserAgentString();
      if ((str1 == null) || (str1.length() == 0) || (str1.equals("Java0")))
      {
        String str2 = System.getProperty("os.name", "Linux");
        String str3 = "Android " + Build.VERSION.RELEASE;
        Locale localLocale = Locale.getDefault();
        String str4 = localLocale.getLanguage().toLowerCase(Locale.US);
        if (str4.length() == 0)
          str4 = "en";
        String str5 = localLocale.getCountry().toLowerCase(Locale.US);
        if (str5.length() > 0)
          str4 = str4 + "-" + str5;
        String str6 = Build.MODEL + " Build/" + Build.ID;
        str1 = "Mozilla/5.0 (" + str2 + "; U; " + str3 + "; " + str4 + "; " + str6 + ") AppleWebKit/0.0 (KHTML, like " + "Gecko) Version/0.0 Mobile Safari/0.0";
      }
      i = str1 + " (Mobile; " + "afma-sdk-a-v" + "4.3.1" + ")";
    }
    return i;
  }

  public static class UserActivityReceiver extends BroadcastReceiver
  {
    public void onReceive(Context paramContext, Intent paramIntent)
    {
      if (paramIntent.getAction().equals("android.intent.action.USER_PRESENT"))
        AdUtil.a(true);
      while (true)
      {
        do
          return;
        while (!(paramIntent.getAction().equals("android.intent.action.SCREEN_OFF")));
        AdUtil.a(false);
      }
    }
  }

  public static enum a
  {
    static
    {
      a[] arrayOfa = new a[6];
      arrayOfa[0] = a;
      arrayOfa[1] = b;
      arrayOfa[2] = c;
      arrayOfa[3] = d;
      arrayOfa[4] = e;
      arrayOfa[5] = f;
      g = arrayOfa;
    }
  }
}