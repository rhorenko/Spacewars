package com.bestcoolfungames.antsmasher;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Process;

public class NotifyAlarm extends BroadcastReceiver
{
  public void onReceive(Context paramContext, Intent paramIntent)
  {
    if (!(InitialView.initialViewCreated.booleanValue()))
    {
      NotificationManager localNotificationManager = (NotificationManager)paramContext.getSystemService("notification");
      localNotificationManager.cancel(82642793);
      Notification localNotification = new Notification(2130837505, paramContext.getString(2131165212), System.currentTimeMillis());
      String str1 = paramContext.getString(2131165184);
      String str2 = paramContext.getString(2131165212);
      Intent localIntent = new Intent(paramContext, InitialView.class);
      localIntent.putExtra("byNotifyAlarm", true);
      localNotification.setLatestEventInfo(paramContext, str1, str2, PendingIntent.getActivity(paramContext, 0, localIntent, 0));
      localNotificationManager.notify(82642793, localNotification);
      Process.killProcess(Process.myPid());
    }
  }
}