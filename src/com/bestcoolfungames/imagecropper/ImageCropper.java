package com.bestcoolfungames.imagecropper;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnCancelListener;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.content.res.Resources;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.graphics.Matrix;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore.Images.Media;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import com.bestcoolfungames.util.Util;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class ImageCropper extends Activity
{
  private static final int DIALOG_LIST = 0;
  private static final int IMAGE_FROM_CAMERA = 3;
  private static final int IMAGE_FROM_GALLERY = 2;
  public static final String croppedImageFile = "croppedImageFile";
  static final String croppedImageName = "photoCropped.jpg";
  static final String takenImageName = Environment.getExternalStorageDirectory() + File.separator + "photoToBeCropped.jpg";
  String croppedImageFullName = null;
  private CropImageView localCropImageView;

  void LoadButtons()
  {
    ((Button)findViewById(R.id.button_crop)).setOnClickListener(new View.OnClickListener(this)
    {
      public void onClick()
      {
        this.this$0.SaveCroppedImage();
      }
    });
    ((Button)findViewById(R.id.button_rotate)).setOnClickListener(new View.OnClickListener(this)
    {
      public void onClick()
      {
        ImageCropper.access$0(this.this$0).rotate();
      }
    });
  }

  void OpenCropSurface(Uri paramUri)
  {
    setContentView(R.layout.image_cropper);
    this.localCropImageView = ((CropImageView)findViewById(R.id.previewx));
    try
    {
      Bitmap localBitmap1 = MediaStore.Images.Media.getBitmap(getContentResolver(), paramUri);
      Bitmap localBitmap2 = localBitmap1;
      if (localBitmap2 == null)
        localBitmap2 = Util.getWrongFileBitmap();
      int i = localBitmap2.getWidth();
      int j = localBitmap2.getHeight();
      Matrix localMatrix = new Matrix();
      System.gc();
      Bitmap localBitmap3 = Util.createBitmap(localBitmap2, 0, 0, i, j, localMatrix, true);
      this.localCropImageView.setBitmap(localBitmap3);
      LoadButtons();
      return;
    }
    catch (FileNotFoundException localFileNotFoundException)
    {
      localFileNotFoundException.printStackTrace();
      Util.getDefaultBitmap();
    }
    catch (IOException localIOException)
    {
      localIOException.printStackTrace();
      Util.getDefaultBitmap();
    }
    catch (OutOfMemoryError localOutOfMemoryError)
    {
      localOutOfMemoryError.printStackTrace();
      Util.getDefaultBitmap();
    }
    catch (Exception localException)
    {
      while (true)
      {
        localException.printStackTrace();
        Util.getDefaultBitmap();
      }
    }
  }

  void SaveCroppedImage()
  {
    Util.Log("saving cropped file at " + this.croppedImageFullName);
    Bitmap localBitmap = this.localCropImageView.getCroppedBitmap();
    File localFile = new File(this.croppedImageFullName);
    if (localFile.exists())
      localFile.delete();
    try
    {
      FileOutputStream localFileOutputStream = openFileOutput("photoCropped.jpg", 1);
      localBitmap.compress(Bitmap.CompressFormat.JPEG, 90, localFileOutputStream);
      localFileOutputStream.close();
      Intent localIntent = new Intent();
      localIntent.putExtra("croppedImageFile", this.croppedImageFullName);
      setResult(-1, localIntent);
      finish();
      return;
    }
    catch (IOException localIOException)
    {
      while (true)
        localIOException.printStackTrace();
    }
  }

  public String getPath(Uri paramUri)
  {
    String[] arrayOfString = new String[1];
    arrayOfString[0] = "_data";
    Cursor localCursor = managedQuery(paramUri, arrayOfString, null, null, null);
    int i = localCursor.getColumnIndexOrThrow("_data");
    localCursor.moveToFirst();
    return localCursor.getString(i);
  }

  protected void onActivityResult(int paramInt1, int paramInt2, Intent paramIntent)
  {
    Util.Log("requestCode: " + paramInt1 + " resultCode:" + paramInt2 + " intent:" + paramIntent);
    if (paramInt2 != -1)
    {
      Util.Log("onActivityResultFail: requestCode: " + paramInt1 + " resultCode:" + paramInt2 + " intent:" + paramIntent);
      setResult(paramInt2, new Intent());
      finish();
    }
    while (true)
    {
      while (true)
      {
        while (true)
        {
          return;
          switch (paramInt1)
          {
          default:
            Util.Log("Unknown request code: requestCode: " + paramInt1 + " resultCode:" + paramInt2 + " intent:" + paramIntent);
          case 2:
          case 3:
          }
        }
        OpenCropSurface(paramIntent.getData());
      }
      OpenCropSurface(PictureHelper.getTakenPictureUri(this, paramIntent, takenImageName));
    }
  }

  protected void onCreate(Bundle paramBundle)
  {
    Util.setContext(getApplicationContext());
    super.onCreate(paramBundle);
    this.croppedImageFullName = getApplicationContext().getFilesDir() + File.separator + "photoCropped.jpg";
    showDialog(0);
  }

  protected Dialog onCreateDialog(int paramInt)
  {
    AlertDialog localAlertDialog;
    switch (paramInt)
    {
    default:
      localAlertDialog = null;
    case 0:
    }
    while (true)
    {
      return localAlertDialog;
      File localFile = new File(this.croppedImageFullName);
      int i = R.array.image_sources_first_time;
      if (localFile.exists())
        i = R.array.image_sources;
      localAlertDialog = new AlertDialog.Builder(this).setTitle(R.string.crop_image).setOnCancelListener(new DialogInterface.OnCancelListener(this)
      {
        public void onCancel()
        {
          Intent localIntent = new Intent();
          this.this$0.setResult(0, localIntent);
          this.this$0.finish();
        }
      }).setItems(i, new DialogInterface.OnClickListener(this)
      {
        public void onClick(, int paramInt)
        {
          switch (paramInt)
          {
          default:
            String[] arrayOfString = this.this$0.getResources().getStringArray(R.array.image_sources);
            new AlertDialog.Builder(this.this$0).setMessage(this.this$0.getString(R.string.you_selected) + paramInt + " , " + arrayOfString[paramInt]).show();
          case 0:
          case 1:
          case 2:
          case 3:
          }
          while (true)
          {
            while (true)
            {
              while (true)
              {
                while (true)
                {
                  return;
                  String str = Util.getString(R.string.select_picture);
                  Intent localIntent2 = new Intent();
                  localIntent2.setType("image/*");
                  localIntent2.setAction("android.intent.action.GET_CONTENT");
                  this.this$0.startActivityForResult(Intent.createChooser(localIntent2, str), 2);
                }
                this.this$0.startActivityForResult(PictureHelper.PrepareLaunchCameraIntent(ImageCropper.takenImageName), 3);
              }
              Intent localIntent1 = new Intent();
              localIntent1.putExtra("croppedImageFile", this.this$0.croppedImageFullName);
              this.this$0.setResult(-1, localIntent1);
              this.this$0.finish();
            }
            Uri localUri = Uri.parse("file:///mnt/sdcard/photo.jpg");
            this.this$0.OpenCropSurface(localUri);
          }
        }
      }).create();
    }
  }
}