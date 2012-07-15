package com.google.android.apps.analytics;

class CustomVariableBuffer
{
  private CustomVariable[] customVariables = new CustomVariable[5];

  private void throwOnInvalidIndex(int paramInt)
  {
    if ((paramInt < 1) || (paramInt > 5))
      throw new IllegalArgumentException("Index must be between 1 and 5 inclusive.");
  }

  public CustomVariable[] getCustomVariableArray()
  {
    return ((CustomVariable[])this.customVariables.clone());
  }

  public CustomVariable getCustomVariableAt(int paramInt)
  {
    throwOnInvalidIndex(paramInt);
    return this.customVariables[(paramInt - 1)];
  }

  public boolean hasCustomVariables()
  {
    byte b = 0;
    int i = 0;
    while (true)
    {
      if (i < this.customVariables.length)
      {
        if (this.customVariables[i] == null)
          break label26;
        b = 1;
      }
      return b;
      label26: ++i;
    }
  }

  public boolean isIndexAvailable(int paramInt)
  {
    byte b;
    throwOnInvalidIndex(paramInt);
    if (this.customVariables[(paramInt - 1)] == null)
      b = 1;
    while (true)
    {
      return b;
      b = 0;
    }
  }

  public void setCustomVariable(CustomVariable paramCustomVariable)
  {
    throwOnInvalidIndex(paramCustomVariable.getIndex());
    this.customVariables[(-1 + paramCustomVariable.getIndex())] = paramCustomVariable;
  }
}