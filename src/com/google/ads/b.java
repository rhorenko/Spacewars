package com.google.ads;

import android.text.TextUtils;
import com.google.ads.util.AdUtil;
import com.google.ads.util.a;
import java.net.HttpURLConnection;
import java.util.StringTokenizer;

public final class b
  implements Runnable
{
  private c a;
  private d b;
  private volatile boolean c;
  private boolean d;
  private String e;

  b(c paramc, d paramd)
  {
    this.a = paramc;
    this.b = paramd;
  }

  private void a(HttpURLConnection paramHttpURLConnection)
  {
    String str3;
    String str1 = paramHttpURLConnection.getHeaderField("X-Afma-Tracking-Urls");
    if (str1 != null)
    {
      StringTokenizer localStringTokenizer = new StringTokenizer(str1);
      while (localStringTokenizer.hasMoreTokens())
      {
        String str5 = localStringTokenizer.nextToken();
        this.b.a(str5);
      }
    }
    b(paramHttpURLConnection);
    String str2 = paramHttpURLConnection.getHeaderField("X-Afma-Refresh-Rate");
    if (str2 != null);
    try
    {
      float f2 = Float.parseFloat(str2);
      if (f2 < 0F)
      {
        this.b.a(f2);
        if (!(this.b.p()))
          this.b.d();
        str3 = paramHttpURLConnection.getHeaderField("X-Afma-Interstitial-Timeout");
        label282: if (str3 != null);
      }
    }
    catch (NumberFormatException localNumberFormatException2)
    {
      String str4;
      try
      {
        float f1 = Float.parseFloat(str3);
        this.b.a(()(f1 * 1000.0F));
        str4 = paramHttpURLConnection.getHeaderField("X-Afma-Orientation");
        if (str4 != null)
        {
          if (!(str4.equals("portrait")))
            break label282;
          this.a.a(AdUtil.b());
        }
        if (!(TextUtils.isEmpty(paramHttpURLConnection.getHeaderField("X-Afma-Doritos-Cache-Life"))));
      }
      catch (NumberFormatException localNumberFormatException2)
      {
        try
        {
          do
            while (true)
            {
              while (true)
              {
                while (true)
                {
                  do
                  {
                    long l = Long.parseLong(paramHttpURLConnection.getHeaderField("X-Afma-Doritos-Cache-Life"));
                    this.b.b(l);
                    return;
                  }
                  while (!(this.b.p()));
                  this.b.c();
                }
                localNumberFormatException3 = localNumberFormatException3;
                a.b("Could not get refresh value: " + str2, localNumberFormatException3);
              }
              localNumberFormatException2 = localNumberFormatException2;
              a.b("Could not get timeout value: " + str3, localNumberFormatException2);
            }
          while (!(str4.equals("landscape")));
          this.a.a(AdUtil.a());
        }
        catch (NumberFormatException localNumberFormatException1)
        {
          while (true)
            a.e("Got bad value of Doritos cookie cache life from header: " + paramHttpURLConnection.getHeaderField("X-Afma-Doritos-Cache-Life") + ". Using default value instead.");
        }
      }
    }
  }

  private void b(HttpURLConnection paramHttpURLConnection)
  {
    String str1 = paramHttpURLConnection.getHeaderField("X-Afma-Click-Tracking-Urls");
    if (str1 != null)
    {
      StringTokenizer localStringTokenizer = new StringTokenizer(str1);
      while (localStringTokenizer.hasMoreTokens())
      {
        String str2 = localStringTokenizer.nextToken();
        this.a.a(str2);
      }
    }
  }

  final void a()
  {
    this.c = true;
  }

  final void a(String paramString)
  {
    this.e = paramString;
    this.c = false;
    new Thread(this).start();
  }

  public final void a(boolean paramBoolean)
  {
    this.d = paramBoolean;
  }

  // ERROR //
  public final void run()
  {
    // Byte code:
    //   0: aload_0
    //   1: getfield 148	com/google/ads/b:c	Z
    //   4: ifne +56 -> 60
    //   7: new 170	java/net/URL
    //   10: dup
    //   11: aload_0
    //   12: getfield 150	com/google/ads/b:e	Ljava/lang/String;
    //   15: invokespecial 171	java/net/URL:<init>	(Ljava/lang/String;)V
    //   18: invokevirtual 175	java/net/URL:openConnection	()Ljava/net/URLConnection;
    //   21: checkcast 31	java/net/HttpURLConnection
    //   24: astore 4
    //   26: aload_0
    //   27: getfield 24	com/google/ads/b:b	Lcom/google/ads/d;
    //   30: invokevirtual 178	com/google/ads/d:e	()Landroid/app/Activity;
    //   33: astore 6
    //   35: aload 6
    //   37: ifnonnull +24 -> 61
    //   40: ldc 180
    //   42: invokestatic 182	com/google/ads/util/a:c	(Ljava/lang/String;)V
    //   45: aload_0
    //   46: getfield 22	com/google/ads/b:a	Lcom/google/ads/c;
    //   49: getstatic 188	com/google/ads/AdRequest$ErrorCode:INTERNAL_ERROR	Lcom/google/ads/AdRequest$ErrorCode;
    //   52: invokevirtual 191	com/google/ads/c:a	(Lcom/google/ads/AdRequest$ErrorCode;)V
    //   55: aload 4
    //   57: invokevirtual 194	java/net/HttpURLConnection:disconnect	()V
    //   60: return
    //   61: aload 6
    //   63: invokestatic 200	android/preference/PreferenceManager:getDefaultSharedPreferences	(Landroid/content/Context;)Landroid/content/SharedPreferences;
    //   66: astore 7
    //   68: aload_0
    //   69: getfield 161	com/google/ads/b:d	Z
    //   72: ifeq +46 -> 118
    //   75: aload 7
    //   77: ldc 202
    //   79: ldc 204
    //   81: invokeinterface 210 3 0
    //   86: invokestatic 104	android/text/TextUtils:isEmpty	(Ljava/lang/CharSequence;)Z
    //   89: ifne +29 -> 118
    //   92: getstatic 213	com/google/ads/util/AdUtil:a	I
    //   95: bipush 8
    //   97: if_icmpne +145 -> 242
    //   100: aload 4
    //   102: ldc 215
    //   104: aload 7
    //   106: ldc 202
    //   108: ldc 204
    //   110: invokeinterface 210 3 0
    //   115: invokevirtual 219	java/net/HttpURLConnection:addRequestProperty	(Ljava/lang/String;Ljava/lang/String;)V
    //   118: aload 4
    //   120: aload 6
    //   122: invokevirtual 225	android/app/Activity:getApplicationContext	()Landroid/content/Context;
    //   125: invokestatic 228	com/google/ads/util/AdUtil:a	(Ljava/net/HttpURLConnection;Landroid/content/Context;)V
    //   128: aload 4
    //   130: iconst_0
    //   131: invokevirtual 231	java/net/HttpURLConnection:setInstanceFollowRedirects	(Z)V
    //   134: aload 4
    //   136: invokevirtual 234	java/net/HttpURLConnection:connect	()V
    //   139: aload 4
    //   141: invokevirtual 237	java/net/HttpURLConnection:getResponseCode	()I
    //   144: istore 8
    //   146: sipush 300
    //   149: iload 8
    //   151: if_icmpgt +183 -> 334
    //   154: iload 8
    //   156: sipush 400
    //   159: if_icmpge +175 -> 334
    //   162: aload 4
    //   164: ldc 239
    //   166: invokevirtual 35	java/net/HttpURLConnection:getHeaderField	(Ljava/lang/String;)Ljava/lang/String;
    //   169: astore 15
    //   171: aload 15
    //   173: ifnonnull +120 -> 293
    //   176: new 116	java/lang/StringBuilder
    //   179: dup
    //   180: invokespecial 117	java/lang/StringBuilder:<init>	()V
    //   183: ldc 241
    //   185: invokevirtual 123	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   188: iload 8
    //   190: invokevirtual 244	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   193: ldc 246
    //   195: invokevirtual 123	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   198: invokevirtual 126	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   201: invokestatic 182	com/google/ads/util/a:c	(Ljava/lang/String;)V
    //   204: aload_0
    //   205: getfield 22	com/google/ads/b:a	Lcom/google/ads/c;
    //   208: getstatic 188	com/google/ads/AdRequest$ErrorCode:INTERNAL_ERROR	Lcom/google/ads/AdRequest$ErrorCode;
    //   211: invokevirtual 191	com/google/ads/c:a	(Lcom/google/ads/AdRequest$ErrorCode;)V
    //   214: aload 4
    //   216: invokevirtual 194	java/net/HttpURLConnection:disconnect	()V
    //   219: goto -159 -> 60
    //   222: astore_3
    //   223: ldc 248
    //   225: aload_3
    //   226: invokestatic 250	com/google/ads/util/a:a	(Ljava/lang/String;Ljava/lang/Throwable;)V
    //   229: aload_0
    //   230: getfield 22	com/google/ads/b:a	Lcom/google/ads/c;
    //   233: getstatic 188	com/google/ads/AdRequest$ErrorCode:INTERNAL_ERROR	Lcom/google/ads/AdRequest$ErrorCode;
    //   236: invokevirtual 191	com/google/ads/c:a	(Lcom/google/ads/AdRequest$ErrorCode;)V
    //   239: goto -179 -> 60
    //   242: aload 4
    //   244: ldc 252
    //   246: aload 7
    //   248: ldc 202
    //   250: ldc 204
    //   252: invokeinterface 210 3 0
    //   257: invokevirtual 219	java/net/HttpURLConnection:addRequestProperty	(Ljava/lang/String;Ljava/lang/String;)V
    //   260: goto -142 -> 118
    //   263: astore 5
    //   265: aload 4
    //   267: invokevirtual 194	java/net/HttpURLConnection:disconnect	()V
    //   270: aload 5
    //   272: athrow
    //   273: astore_2
    //   274: ldc 254
    //   276: aload_2
    //   277: invokestatic 131	com/google/ads/util/a:b	(Ljava/lang/String;Ljava/lang/Throwable;)V
    //   280: aload_0
    //   281: getfield 22	com/google/ads/b:a	Lcom/google/ads/c;
    //   284: getstatic 257	com/google/ads/AdRequest$ErrorCode:NETWORK_ERROR	Lcom/google/ads/AdRequest$ErrorCode;
    //   287: invokevirtual 191	com/google/ads/c:a	(Lcom/google/ads/AdRequest$ErrorCode;)V
    //   290: goto -230 -> 60
    //   293: aload_0
    //   294: aload 4
    //   296: invokespecial 259	com/google/ads/b:a	(Ljava/net/HttpURLConnection;)V
    //   299: aload_0
    //   300: aload 15
    //   302: putfield 150	com/google/ads/b:e	Ljava/lang/String;
    //   305: aload 4
    //   307: invokevirtual 194	java/net/HttpURLConnection:disconnect	()V
    //   310: goto -310 -> 0
    //   313: astore_1
    //   314: ldc_w 261
    //   317: aload_1
    //   318: invokestatic 250	com/google/ads/util/a:a	(Ljava/lang/String;Ljava/lang/Throwable;)V
    //   321: aload_0
    //   322: getfield 22	com/google/ads/b:a	Lcom/google/ads/c;
    //   325: getstatic 188	com/google/ads/AdRequest$ErrorCode:INTERNAL_ERROR	Lcom/google/ads/AdRequest$ErrorCode;
    //   328: invokevirtual 191	com/google/ads/c:a	(Lcom/google/ads/AdRequest$ErrorCode;)V
    //   331: goto -271 -> 60
    //   334: iload 8
    //   336: sipush 200
    //   339: if_icmpne +191 -> 530
    //   342: aload_0
    //   343: aload 4
    //   345: invokespecial 259	com/google/ads/b:a	(Ljava/net/HttpURLConnection;)V
    //   348: new 263	java/io/BufferedReader
    //   351: dup
    //   352: new 265	java/io/InputStreamReader
    //   355: dup
    //   356: aload 4
    //   358: invokevirtual 269	java/net/HttpURLConnection:getInputStream	()Ljava/io/InputStream;
    //   361: invokespecial 272	java/io/InputStreamReader:<init>	(Ljava/io/InputStream;)V
    //   364: sipush 4096
    //   367: invokespecial 275	java/io/BufferedReader:<init>	(Ljava/io/Reader;I)V
    //   370: astore 9
    //   372: new 116	java/lang/StringBuilder
    //   375: dup
    //   376: invokespecial 117	java/lang/StringBuilder:<init>	()V
    //   379: astore 10
    //   381: aload_0
    //   382: getfield 148	com/google/ads/b:c	Z
    //   385: ifne +35 -> 420
    //   388: aload 9
    //   390: invokevirtual 278	java/io/BufferedReader:readLine	()Ljava/lang/String;
    //   393: astore 12
    //   395: aload 12
    //   397: ifnull +23 -> 420
    //   400: aload 10
    //   402: aload 12
    //   404: invokevirtual 123	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   407: pop
    //   408: aload 10
    //   410: ldc_w 280
    //   413: invokevirtual 123	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   416: pop
    //   417: goto -36 -> 381
    //   420: aload 10
    //   422: invokevirtual 126	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   425: astore 11
    //   427: new 116	java/lang/StringBuilder
    //   430: dup
    //   431: invokespecial 117	java/lang/StringBuilder:<init>	()V
    //   434: ldc_w 282
    //   437: invokevirtual 123	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   440: aload 11
    //   442: invokevirtual 123	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   445: invokevirtual 126	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   448: invokestatic 283	com/google/ads/util/a:a	(Ljava/lang/String;)V
    //   451: aload 11
    //   453: ifnull +14 -> 467
    //   456: aload 11
    //   458: invokevirtual 286	java/lang/String:trim	()Ljava/lang/String;
    //   461: invokevirtual 289	java/lang/String:length	()I
    //   464: ifgt +45 -> 509
    //   467: new 116	java/lang/StringBuilder
    //   470: dup
    //   471: invokespecial 117	java/lang/StringBuilder:<init>	()V
    //   474: ldc_w 291
    //   477: invokevirtual 123	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   480: aload 11
    //   482: invokevirtual 123	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   485: invokevirtual 126	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   488: invokestatic 283	com/google/ads/util/a:a	(Ljava/lang/String;)V
    //   491: aload_0
    //   492: getfield 22	com/google/ads/b:a	Lcom/google/ads/c;
    //   495: getstatic 294	com/google/ads/AdRequest$ErrorCode:NO_FILL	Lcom/google/ads/AdRequest$ErrorCode;
    //   498: invokevirtual 191	com/google/ads/c:a	(Lcom/google/ads/AdRequest$ErrorCode;)V
    //   501: aload 4
    //   503: invokevirtual 194	java/net/HttpURLConnection:disconnect	()V
    //   506: goto -446 -> 60
    //   509: aload_0
    //   510: getfield 22	com/google/ads/b:a	Lcom/google/ads/c;
    //   513: aload 11
    //   515: aload_0
    //   516: getfield 150	com/google/ads/b:e	Ljava/lang/String;
    //   519: invokevirtual 296	com/google/ads/c:a	(Ljava/lang/String;Ljava/lang/String;)V
    //   522: aload 4
    //   524: invokevirtual 194	java/net/HttpURLConnection:disconnect	()V
    //   527: goto -467 -> 60
    //   530: iload 8
    //   532: sipush 400
    //   535: if_icmpne +27 -> 562
    //   538: ldc_w 298
    //   541: invokestatic 182	com/google/ads/util/a:c	(Ljava/lang/String;)V
    //   544: aload_0
    //   545: getfield 22	com/google/ads/b:a	Lcom/google/ads/c;
    //   548: getstatic 301	com/google/ads/AdRequest$ErrorCode:INVALID_REQUEST	Lcom/google/ads/AdRequest$ErrorCode;
    //   551: invokevirtual 191	com/google/ads/c:a	(Lcom/google/ads/AdRequest$ErrorCode;)V
    //   554: aload 4
    //   556: invokevirtual 194	java/net/HttpURLConnection:disconnect	()V
    //   559: goto -499 -> 60
    //   562: new 116	java/lang/StringBuilder
    //   565: dup
    //   566: invokespecial 117	java/lang/StringBuilder:<init>	()V
    //   569: ldc_w 303
    //   572: invokevirtual 123	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   575: iload 8
    //   577: invokevirtual 244	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   580: invokevirtual 126	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   583: invokestatic 182	com/google/ads/util/a:c	(Ljava/lang/String;)V
    //   586: aload_0
    //   587: getfield 22	com/google/ads/b:a	Lcom/google/ads/c;
    //   590: getstatic 188	com/google/ads/AdRequest$ErrorCode:INTERNAL_ERROR	Lcom/google/ads/AdRequest$ErrorCode;
    //   593: invokevirtual 191	com/google/ads/c:a	(Lcom/google/ads/AdRequest$ErrorCode;)V
    //   596: aload 4
    //   598: invokevirtual 194	java/net/HttpURLConnection:disconnect	()V
    //   601: goto -541 -> 60
    //
    // Exception table:
    //   from	to	target	type
    //   0	26	222	java/net/MalformedURLException
    //   55	60	222	java/net/MalformedURLException
    //   214	219	222	java/net/MalformedURLException
    //   265	273	222	java/net/MalformedURLException
    //   305	310	222	java/net/MalformedURLException
    //   501	506	222	java/net/MalformedURLException
    //   522	527	222	java/net/MalformedURLException
    //   554	559	222	java/net/MalformedURLException
    //   596	601	222	java/net/MalformedURLException
    //   26	55	263	finally
    //   61	214	263	finally
    //   242	260	263	finally
    //   293	305	263	finally
    //   342	501	263	finally
    //   509	522	263	finally
    //   538	554	263	finally
    //   562	596	263	finally
    //   0	26	273	java/io/IOException
    //   55	60	273	java/io/IOException
    //   214	219	273	java/io/IOException
    //   265	273	273	java/io/IOException
    //   305	310	273	java/io/IOException
    //   501	506	273	java/io/IOException
    //   522	527	273	java/io/IOException
    //   554	559	273	java/io/IOException
    //   596	601	273	java/io/IOException
    //   0	26	313	java/lang/Exception
    //   55	60	313	java/lang/Exception
    //   214	219	313	java/lang/Exception
    //   265	273	313	java/lang/Exception
    //   305	310	313	java/lang/Exception
    //   501	506	313	java/lang/Exception
    //   522	527	313	java/lang/Exception
    //   554	559	313	java/lang/Exception
    //   596	601	313	java/lang/Exception
  }
}