package com.google.ads;

import android.content.Context;

public final class w
  implements Runnable
{
  private Context a;
  private String b;

  public w(String paramString, Context paramContext)
  {
    this.b = paramString;
    this.a = paramContext;
  }

  // ERROR //
  public final void run()
  {
    // Byte code:
    //   0: new 24	java/lang/StringBuilder
    //   3: dup
    //   4: invokespecial 25	java/lang/StringBuilder:<init>	()V
    //   7: ldc 27
    //   9: invokevirtual 31	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   12: aload_0
    //   13: getfield 17	com/google/ads/w:b	Ljava/lang/String;
    //   16: invokevirtual 31	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   19: invokevirtual 35	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   22: invokestatic 40	com/google/ads/util/a:a	(Ljava/lang/String;)V
    //   25: new 42	java/net/URL
    //   28: dup
    //   29: aload_0
    //   30: getfield 17	com/google/ads/w:b	Ljava/lang/String;
    //   33: invokespecial 44	java/net/URL:<init>	(Ljava/lang/String;)V
    //   36: invokevirtual 48	java/net/URL:openConnection	()Ljava/net/URLConnection;
    //   39: checkcast 50	java/net/HttpURLConnection
    //   42: astore_2
    //   43: aload_2
    //   44: aload_0
    //   45: getfield 19	com/google/ads/w:a	Landroid/content/Context;
    //   48: invokestatic 55	com/google/ads/util/AdUtil:a	(Ljava/net/HttpURLConnection;Landroid/content/Context;)V
    //   51: aload_2
    //   52: iconst_1
    //   53: invokevirtual 59	java/net/HttpURLConnection:setInstanceFollowRedirects	(Z)V
    //   56: aload_2
    //   57: invokevirtual 62	java/net/HttpURLConnection:connect	()V
    //   60: aload_2
    //   61: invokevirtual 66	java/net/HttpURLConnection:getResponseCode	()I
    //   64: istore 4
    //   66: iload 4
    //   68: sipush 200
    //   71: if_icmplt +11 -> 82
    //   74: iload 4
    //   76: sipush 300
    //   79: if_icmplt +38 -> 117
    //   82: new 24	java/lang/StringBuilder
    //   85: dup
    //   86: invokespecial 25	java/lang/StringBuilder:<init>	()V
    //   89: ldc 68
    //   91: invokevirtual 31	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   94: iload 4
    //   96: invokevirtual 71	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   99: ldc 73
    //   101: invokevirtual 31	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   104: aload_0
    //   105: getfield 17	com/google/ads/w:b	Ljava/lang/String;
    //   108: invokevirtual 31	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   111: invokevirtual 35	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   114: invokestatic 76	com/google/ads/util/a:e	(Ljava/lang/String;)V
    //   117: aload_2
    //   118: invokevirtual 79	java/net/HttpURLConnection:disconnect	()V
    //   121: goto +37 -> 158
    //   124: astore_3
    //   125: aload_2
    //   126: invokevirtual 79	java/net/HttpURLConnection:disconnect	()V
    //   129: aload_3
    //   130: athrow
    //   131: astore_1
    //   132: new 24	java/lang/StringBuilder
    //   135: dup
    //   136: invokespecial 25	java/lang/StringBuilder:<init>	()V
    //   139: ldc 81
    //   141: invokevirtual 31	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   144: aload_0
    //   145: getfield 17	com/google/ads/w:b	Ljava/lang/String;
    //   148: invokevirtual 31	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   151: invokevirtual 35	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   154: aload_1
    //   155: invokestatic 84	com/google/ads/util/a:b	(Ljava/lang/String;Ljava/lang/Throwable;)V
    //   158: return
    //
    // Exception table:
    //   from	to	target	type
    //   43	117	124	finally
    //   0	43	131	java/io/IOException
    //   117	131	131	java/io/IOException
  }
}