package com.google.android.apps.analytics;

import java.util.Locale;

class NetworkRequestUtil
{
  private static final String FAKE_DOMAIN_HASH = "999";
  private static final String GOOGLE_ANALYTICS_GIF_PATH = "/__utm.gif";
  private static final int X10_PROJECT_NAMES = 8;
  private static final int X10_PROJECT_SCOPES = 11;
  private static final int X10_PROJECT_VALUES = 9;

  static void appendCurrencyValue(StringBuilder paramStringBuilder, String paramString, double paramDouble)
  {
    paramStringBuilder.append(paramString).append("=");
    double d = Math.floor(0.5D + paramDouble * 1000000.0D) / 1000000.0D;
    if (d < 0D)
      paramStringBuilder.append(Double.toString(d));
  }

  private static void appendStringValue(StringBuilder paramStringBuilder, String paramString1, String paramString2)
  {
    paramStringBuilder.append(paramString1).append("=");
    if ((paramString2 != null) && (paramString2.trim().length() > 0))
      paramStringBuilder.append(AnalyticsParameterEncoder.encode(paramString2));
  }

  public static String constructEventRequestPath(Event paramEvent, String paramString)
  {
    Locale localLocale = Locale.getDefault();
    StringBuilder localStringBuilder1 = new StringBuilder();
    StringBuilder localStringBuilder2 = new StringBuilder();
    Object[] arrayOfObject1 = new Object[2];
    arrayOfObject1[0] = encode(paramEvent.category);
    arrayOfObject1[1] = encode(paramEvent.action);
    localStringBuilder2.append(String.format("5(%s*%s", arrayOfObject1));
    if (paramEvent.label != null)
      localStringBuilder2.append("*").append(encode(paramEvent.label));
    localStringBuilder2.append(")");
    if (paramEvent.value > -1)
    {
      Object[] arrayOfObject4 = new Object[1];
      arrayOfObject4[0] = Integer.valueOf(paramEvent.value);
      localStringBuilder2.append(String.format("(%d)", arrayOfObject4));
    }
    localStringBuilder2.append(getCustomVariableParams(paramEvent));
    localStringBuilder1.append("/__utm.gif");
    localStringBuilder1.append("?utmwv=4.6ma");
    localStringBuilder1.append("&utmn=").append(paramEvent.randomVal);
    localStringBuilder1.append("&utmt=event");
    localStringBuilder1.append("&utme=").append(localStringBuilder2.toString());
    localStringBuilder1.append("&utmcs=UTF-8");
    Object[] arrayOfObject2 = new Object[2];
    arrayOfObject2[0] = Integer.valueOf(paramEvent.screenWidth);
    arrayOfObject2[1] = Integer.valueOf(paramEvent.screenHeight);
    localStringBuilder1.append(String.format("&utmsr=%dx%d", arrayOfObject2));
    Object[] arrayOfObject3 = new Object[2];
    arrayOfObject3[0] = localLocale.getLanguage();
    arrayOfObject3[1] = localLocale.getCountry();
    localStringBuilder1.append(String.format("&utmul=%s-%s", arrayOfObject3));
    localStringBuilder1.append("&utmac=").append(paramEvent.accountId);
    localStringBuilder1.append("&utmcc=").append(getEscapedCookieString(paramEvent, paramString));
    return localStringBuilder1.toString();
  }

  public static String constructItemRequestPath(Event paramEvent, String paramString)
  {
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append("/__utm.gif");
    localStringBuilder.append("?utmwv=4.6ma");
    localStringBuilder.append("&utmn=").append(paramEvent.randomVal);
    localStringBuilder.append("&utmt=item");
    Item localItem = paramEvent.getItem();
    if (localItem != null)
    {
      appendStringValue(localStringBuilder, "&utmtid", localItem.getOrderId());
      appendStringValue(localStringBuilder, "&utmipc", localItem.getItemSKU());
      appendStringValue(localStringBuilder, "&utmipn", localItem.getItemName());
      appendStringValue(localStringBuilder, "&utmiva", localItem.getItemCategory());
      appendCurrencyValue(localStringBuilder, "&utmipr", localItem.getItemPrice());
      localStringBuilder.append("&utmiqt=");
      if (localItem.getItemCount() > 0L)
        localStringBuilder.append(localItem.getItemCount());
    }
    localStringBuilder.append("&utmac=").append(paramEvent.accountId);
    localStringBuilder.append("&utmcc=").append(getEscapedCookieString(paramEvent, paramString));
    return localStringBuilder.toString();
  }

  public static String constructPageviewRequestPath(Event paramEvent, String paramString)
  {
    String str1 = "";
    if (paramEvent.action != null)
      str1 = paramEvent.action;
    if (!(str1.startsWith("/")))
      str1 = "/" + str1;
    String str2 = encode(str1);
    String str3 = getCustomVariableParams(paramEvent);
    Locale localLocale = Locale.getDefault();
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append("/__utm.gif");
    localStringBuilder.append("?utmwv=4.6ma");
    localStringBuilder.append("&utmn=").append(paramEvent.randomVal);
    if (str3.length() > 0)
      localStringBuilder.append("&utme=").append(str3);
    localStringBuilder.append("&utmcs=UTF-8");
    Object[] arrayOfObject1 = new Object[2];
    arrayOfObject1[0] = Integer.valueOf(paramEvent.screenWidth);
    arrayOfObject1[1] = Integer.valueOf(paramEvent.screenHeight);
    localStringBuilder.append(String.format("&utmsr=%dx%d", arrayOfObject1));
    Object[] arrayOfObject2 = new Object[2];
    arrayOfObject2[0] = localLocale.getLanguage();
    arrayOfObject2[1] = localLocale.getCountry();
    localStringBuilder.append(String.format("&utmul=%s-%s", arrayOfObject2));
    localStringBuilder.append("&utmp=").append(str2);
    localStringBuilder.append("&utmac=").append(paramEvent.accountId);
    localStringBuilder.append("&utmcc=").append(getEscapedCookieString(paramEvent, paramString));
    return localStringBuilder.toString();
  }

  public static String constructTransactionRequestPath(Event paramEvent, String paramString)
  {
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append("/__utm.gif");
    localStringBuilder.append("?utmwv=4.6ma");
    localStringBuilder.append("&utmn=").append(paramEvent.randomVal);
    localStringBuilder.append("&utmt=tran");
    Transaction localTransaction = paramEvent.getTransaction();
    if (localTransaction != null)
    {
      appendStringValue(localStringBuilder, "&utmtid", localTransaction.getOrderId());
      appendStringValue(localStringBuilder, "&utmtst", localTransaction.getStoreName());
      appendCurrencyValue(localStringBuilder, "&utmtto", localTransaction.getTotalCost());
      appendCurrencyValue(localStringBuilder, "&utmttx", localTransaction.getTotalTax());
      appendCurrencyValue(localStringBuilder, "&utmtsp", localTransaction.getShippingCost());
      appendStringValue(localStringBuilder, "&utmtci", "");
      appendStringValue(localStringBuilder, "&utmtrg", "");
      appendStringValue(localStringBuilder, "&utmtco", "");
    }
    localStringBuilder.append("&utmac=").append(paramEvent.accountId);
    localStringBuilder.append("&utmcc=").append(getEscapedCookieString(paramEvent, paramString));
    return localStringBuilder.toString();
  }

  private static void createX10Project(CustomVariable[] paramArrayOfCustomVariable, StringBuilder paramStringBuilder, int paramInt)
  {
    paramStringBuilder.append(paramInt).append("(");
    byte b = 1;
    int i = 0;
    if (i < paramArrayOfCustomVariable.length)
    {
      CustomVariable localCustomVariable;
      if (paramArrayOfCustomVariable[i] != null)
      {
        localCustomVariable = paramArrayOfCustomVariable[i];
        if (b != 0)
          break label102;
        paramStringBuilder.append("*");
        paramStringBuilder.append(localCustomVariable.getIndex()).append("!");
        switch (paramInt)
        {
        case 10:
        default:
        case 8:
        case 9:
        case 11:
        }
      }
      while (true)
      {
        while (true)
        {
          while (true)
          {
            while (true)
            {
              while (true)
                ++i;
              label102: b = 0;
            }
            paramStringBuilder.append(x10Escape(encode(localCustomVariable.getName())));
          }
          paramStringBuilder.append(x10Escape(encode(localCustomVariable.getValue())));
        }
        paramStringBuilder.append(localCustomVariable.getScope());
      }
    }
    paramStringBuilder.append(")");
  }

  private static String encode(String paramString)
  {
    return AnalyticsParameterEncoder.encode(paramString);
  }

  public static String getCustomVariableParams(Event paramEvent)
  {
    String str;
    StringBuilder localStringBuilder = new StringBuilder();
    CustomVariableBuffer localCustomVariableBuffer = paramEvent.getCustomVariableBuffer();
    if (localCustomVariableBuffer == null)
      str = "";
    while (true)
    {
      while (true)
      {
        return str;
        if (localCustomVariableBuffer.hasCustomVariables())
          break;
        str = "";
      }
      CustomVariable[] arrayOfCustomVariable = localCustomVariableBuffer.getCustomVariableArray();
      createX10Project(arrayOfCustomVariable, localStringBuilder, 8);
      createX10Project(arrayOfCustomVariable, localStringBuilder, 9);
      createX10Project(arrayOfCustomVariable, localStringBuilder, 11);
      str = localStringBuilder.toString();
    }
  }

  public static String getEscapedCookieString(Event paramEvent, String paramString)
  {
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append("__utma=");
    localStringBuilder.append("999").append(".");
    localStringBuilder.append(paramEvent.userId).append(".");
    localStringBuilder.append(paramEvent.timestampFirst).append(".");
    localStringBuilder.append(paramEvent.timestampPrevious).append(".");
    localStringBuilder.append(paramEvent.timestampCurrent).append(".");
    localStringBuilder.append(paramEvent.visits);
    if (paramString != null)
    {
      localStringBuilder.append("+__utmz=");
      localStringBuilder.append("999").append(".");
      localStringBuilder.append(paramEvent.timestampFirst).append(".");
      localStringBuilder.append("1.1.");
      localStringBuilder.append(paramString);
    }
    return encode(localStringBuilder.toString());
  }

  private static String x10Escape(String paramString)
  {
    return paramString.replace("'", "'0").replace(")", "'1").replace("*", "'2").replace("!", "'3");
  }
}