package com.revmob;

import android.app.Activity;

class BlockingOnUIRunnable<T>
{
  private Activity activity;
  private BlockingOnUIRunnableListener<T> listener;
  private T returnValue = null;
  private Runnable uiRunnable;

  public BlockingOnUIRunnable(Activity paramActivity, BlockingOnUIRunnableListener<T> paramBlockingOnUIRunnableListener)
  {
    this.activity = paramActivity;
    this.listener = paramBlockingOnUIRunnableListener;
    this.uiRunnable = new Runnable(this)
    {
      // ERROR //
      public void run()
      {
        // Byte code:
        //   0: aload_0
        //   1: monitorenter
        //   2: aload_0
        //   3: getfield 16	com/revmob/BlockingOnUIRunnable$1:this$0	Lcom/revmob/BlockingOnUIRunnable;
        //   6: aload_0
        //   7: getfield 16	com/revmob/BlockingOnUIRunnable$1:this$0	Lcom/revmob/BlockingOnUIRunnable;
        //   10: invokestatic 24	com/revmob/BlockingOnUIRunnable:access$100	(Lcom/revmob/BlockingOnUIRunnable;)Lcom/revmob/BlockingOnUIRunnableListener;
        //   13: invokeinterface 30 1 0
        //   18: invokestatic 34	com/revmob/BlockingOnUIRunnable:access$002	(Lcom/revmob/BlockingOnUIRunnable;Ljava/lang/Object;)Ljava/lang/Object;
        //   21: pop
        //   22: aload_0
        //   23: invokevirtual 37	java/lang/Object:notify	()V
        //   26: aload_0
        //   27: monitorexit
        //   28: return
        //   29: astore_1
        //   30: aload_0
        //   31: monitorexit
        //   32: aload_1
        //   33: athrow
        //
        // Exception table:
        //   from	to	target	type
        //   2	32	29	finally
      }
    };
  }

  public T startOnUiAndWait()
  {
    Runnable localRunnable = this.uiRunnable;
    monitorenter;
    try
    {
    }
    finally
    {
      try
      {
        if (this.returnValue == null)
          this.uiRunnable.wait();
        monitorexit;
        return this.returnValue;
      }
      catch (InterruptedException localInterruptedException)
      {
        while (true)
          localInterruptedException.printStackTrace();
        localObject = finally;
        monitorexit;
        throw localObject;
      }
    }
  }
}