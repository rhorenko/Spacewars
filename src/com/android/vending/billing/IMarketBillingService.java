package com.android.vending.billing;

import android.os.Binder;
import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

public abstract interface IMarketBillingService
  extends IInterface
{
  public abstract Bundle sendBillingRequest(Bundle paramBundle)
    throws RemoteException;

  public static abstract class Stub extends Binder
  implements IMarketBillingService
  {
    private static final String DESCRIPTOR = "com.android.vending.billing.IMarketBillingService";
    static final int TRANSACTION_sendBillingRequest = 1;

    public Stub()
    {
      attachInterface(this, "com.android.vending.billing.IMarketBillingService");
    }

    public static IMarketBillingService asInterface(IBinder paramIBinder)
    {
      Object localObject;
      if (paramIBinder == null)
        localObject = null;
      while (true)
      {
        while (true)
        {
          return localObject;
          IInterface localIInterface = paramIBinder.queryLocalInterface("com.android.vending.billing.IMarketBillingService");
          if ((localIInterface == null) || (!(localIInterface instanceof IMarketBillingService)))
            break;
          localObject = (IMarketBillingService)localIInterface;
        }
        localObject = new Proxy(paramIBinder);
      }
    }

    public IBinder asBinder()
    {
      return this;
    }

    public boolean onTransact(int paramInt1, Parcel paramParcel1, Parcel paramParcel2, int paramInt2)
      throws RemoteException
    {
      int i;
      byte b = 1;
      switch (paramInt1)
      {
      default:
        i = super.onTransact(paramInt1, paramParcel1, paramParcel2, paramInt2);
      case 1598968902:
      case 1:
      }
      while (true)
      {
        Bundle localBundle1;
        while (true)
        {
          return i;
          paramParcel2.writeString("com.android.vending.billing.IMarketBillingService");
        }
        paramParcel1.enforceInterface("com.android.vending.billing.IMarketBillingService");
        if (paramParcel1.readInt() != 0)
          localBundle1 = (Bundle)Bundle.CREATOR.createFromParcel(paramParcel1);
        while (true)
        {
          while (true)
          {
            Bundle localBundle2 = sendBillingRequest(localBundle1);
            paramParcel2.writeNoException();
            if (localBundle2 == null)
              break label122;
            paramParcel2.writeInt(i);
            localBundle2.writeToParcel(paramParcel2, i);
          }
          ??? = null;
        }
        label122: paramParcel2.writeInt(0);
      }
    }

    private static class Proxy
  implements IMarketBillingService
    {
      private IBinder mRemote;

      Proxy(IBinder paramIBinder)
      {
        this.mRemote = paramIBinder;
      }

      public IBinder asBinder()
      {
        return this.mRemote;
      }

      public String getInterfaceDescriptor()
      {
        return "com.android.vending.billing.IMarketBillingService";
      }

      public Bundle sendBillingRequest(Bundle paramBundle)
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("com.android.vending.billing.IMarketBillingService");
          if (paramBundle != null)
          {
            localParcel1.writeInt(1);
            paramBundle.writeToParcel(localParcel1, 0);
            this.mRemote.transact(1, localParcel1, localParcel2, 0);
            localParcel2.readException();
            label100: if (localParcel2.readInt() == 0)
              break label100;
          }
        }
        finally
        {
          while (true)
          {
            Bundle localBundle;
            localParcel2.recycle();
            localParcel1.recycle();
            throw localObject;
            ??? = null;
          }
        }
      }
    }
  }
}