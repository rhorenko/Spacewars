package com.google.ads;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.database.Cursor;
import android.net.Uri;
import android.preference.PreferenceManager;
import android.text.TextUtils;
import android.webkit.WebView;
import com.google.ads.util.a;
import java.lang.ref.WeakReference;
import java.util.Date;

public final class z
{
  public static void a(Activity paramActivity)
  {
    new Thread(new a(paramActivity)).start();
  }

  public static void a(Activity paramActivity, WebView paramWebView, String paramString)
  {
    new Thread(new b(paramActivity, paramWebView, paramString)).start();
  }

  public static void a(WebView paramWebView, boolean paramBoolean)
  {
    a.a(paramWebView, "(G_updatePlusOne(" + paramBoolean + "))");
  }

  private static class c
  implements Runnable
  {
    private boolean a;
    private WebView b;

    public c(WebView paramWebView, boolean paramBoolean)
    {
      this.b = paramWebView;
      this.a = paramBoolean;
    }

    public final void run()
    {
      z.a(this.b, this.a);
    }
  }

  private static class b
  implements Runnable
  {
    private WeakReference<Activity> a;
    private WebView b;
    private String c;

    public b(Activity paramActivity, WebView paramWebView, String paramString)
    {
      this.a = new WeakReference(paramActivity);
      this.c = paramString;
      this.b = paramWebView;
    }

    public final void run()
    {
      while (true)
      {
        while (true)
        {
          Uri localUri;
          try
          {
            localUri = Uri.withAppendedPath(y.a, this.c);
            Activity localActivity = (Activity)this.a.get();
            if (localActivity == null)
            {
              com.google.ads.util.a.a("Activity was null while getting the +1 button state.");
              return;
            }
            Cursor localCursor = localActivity.getContentResolver().query(localUri, y.c, null, null, null);
            if ((localCursor == null) || (!(localCursor.moveToFirst())))
              break label128;
            if (localCursor.getInt(localCursor.getColumnIndex("has_plus1")) != 1)
              break label122;
            int i = 1;
            this.b.post(new z.c(this.b, i));
          }
          catch (Exception localException)
          {
            com.google.ads.util.a.a("An unknown error occurred while updating the +1 state.", localException);
            return;
          }
          label122: b1 = 0;
        }
        label128: com.google.ads.util.a.a("Google+ app not installed, showing ad as not +1'd");
        byte b1 = 0;
      }
    }
  }

  private static class a
  implements Runnable
  {
    private WeakReference<Activity> a;

    public a(Activity paramActivity)
    {
      this.a = new WeakReference(paramActivity);
    }

    public final void run()
    {
      while (true)
      {
        SharedPreferences.Editor localEditor;
        while (true)
        {
          Activity localActivity;
          try
          {
            localActivity = (Activity)this.a.get();
            if (localActivity == null)
            {
              com.google.ads.util.a.a("Activity was null while making a doritos cookie request.");
              return;
            }
            Cursor localCursor = localActivity.getContentResolver().query(y.b, y.d, null, null, null);
            if ((localCursor == null) || (!(localCursor.moveToFirst())) || (localCursor.getColumnNames().length <= 0))
              break label159;
            String str = localCursor.getString(localCursor.getColumnIndex(localCursor.getColumnName(0)));
            localEditor = PreferenceManager.getDefaultSharedPreferences(localActivity.getApplicationContext()).edit();
            if (TextUtils.isEmpty(str))
              break label170;
            localEditor.putString("drt", str);
            localEditor.putLong("drt_ts", new Date().getTime());
            localEditor.commit();
          }
          catch (Exception localException)
          {
            com.google.ads.util.a.a("An unknown error occurred while sending a doritos request.", localException);
            return;
          }
          label159: com.google.ads.util.a.a("Google+ app not installed, not storing doritos cookie");
          ??? = null;
        }
        label170: localEditor.putString("drt", "");
        localEditor.putLong("drt_ts", 0L);
      }
    }
  }
}