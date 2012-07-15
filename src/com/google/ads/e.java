package com.google.ads;

import android.os.Bundle;
import java.io.Serializable;
import java.util.HashMap;

public final class e
{
  private String a;
  private HashMap<String, String> b;

  public e(Bundle paramBundle)
  {
    this.a = paramBundle.getString("action");
    Serializable localSerializable = paramBundle.getSerializable("params");
    if (localSerializable instanceof HashMap)
      localHashMap = (HashMap)localSerializable;
    while (true)
    {
      this.b = localHashMap;
      return;
      ??? = null;
    }
  }

  public e(String paramString)
  {
    this.a = paramString;
  }

  public e(String paramString, HashMap<String, String> paramHashMap)
  {
    this(paramString);
    this.b = paramHashMap;
  }

  public final Bundle a()
  {
    Bundle localBundle = new Bundle();
    localBundle.putString("action", this.a);
    localBundle.putSerializable("params", this.b);
    return localBundle;
  }

  public final String b()
  {
    return this.a;
  }

  public final HashMap<String, String> c()
  {
    return this.b;
  }
}