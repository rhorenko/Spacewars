package com.bestcoolfungames.imagecropper;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.Intent;
import android.net.Uri;
import android.provider.MediaStore.Images.Media;
import com.bestcoolfungames.util.Util;
import java.io.File;

public class PictureHelper
{
  public static Intent PrepareLaunchCameraIntent(String paramString)
  {
    Intent localIntent = new Intent("android.media.action.IMAGE_CAPTURE");
    localIntent.putExtra("output", Uri.fromFile(new File(paramString)));
    return localIntent;
  }

  public static Uri getTakenPictureUri(Activity paramActivity, Intent paramIntent, String paramString)
  {
    Uri localUri = null;
    File localFile = new File(paramString);
    try
    {
      ContentResolver localContentResolver = paramActivity.getContentResolver();
      String str = localFile.getAbsolutePath();
      System.gc();
      localUri = Uri.parse(MediaStore.Images.Media.insertImage(localContentResolver, str, null, null));
      if (!(localFile.delete()))
        Util.Log("Failed to delete " + localFile);
      return localUri;
    }
    catch (Exception localException)
    {
      while (true)
        localException.printStackTrace();
    }
  }
}