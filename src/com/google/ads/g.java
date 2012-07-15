package com.google.ads;

import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;
import android.media.MediaPlayer.OnErrorListener;
import android.media.MediaPlayer.OnPreparedListener;
import android.os.Handler;
import android.text.TextUtils;
import android.view.MotionEvent;
import android.widget.FrameLayout;
import android.widget.FrameLayout.LayoutParams;
import android.widget.MediaController;
import android.widget.VideoView;
import com.google.ads.util.a;
import java.lang.ref.WeakReference;

public final class g extends FrameLayout
  implements MediaPlayer.OnCompletionListener, MediaPlayer.OnErrorListener, MediaPlayer.OnPreparedListener
{
  public MediaController a;
  private WeakReference<AdActivity> b;
  private h c;
  private long d;
  private VideoView e;
  private String f;

  public g(AdActivity paramAdActivity, h paramh)
  {
    super(paramAdActivity);
    this.b = new WeakReference(paramAdActivity);
    this.c = paramh;
    this.e = new VideoView(paramAdActivity);
    FrameLayout.LayoutParams localLayoutParams = new FrameLayout.LayoutParams(-1, -1, 17);
    addView(this.e, localLayoutParams);
    this.a = null;
    this.f = null;
    this.d = 0L;
    new a(this).a();
    this.e.setOnCompletionListener(this);
    this.e.setOnPreparedListener(this);
    this.e.setOnErrorListener(this);
  }

  public final void a()
  {
    if (!(TextUtils.isEmpty(this.f)))
      this.e.setVideoPath(this.f);
    while (true)
    {
      return;
      a.a(this.c, "onVideoEvent", "{'event': 'error', 'what': 'no_src'}");
    }
  }

  public final void a(int paramInt)
  {
    this.e.seekTo(paramInt);
  }

  public final void a(MotionEvent paramMotionEvent)
  {
    this.e.onTouchEvent(paramMotionEvent);
  }

  public final void a(String paramString)
  {
    this.f = paramString;
  }

  public final void a(boolean paramBoolean)
  {
    AdActivity localAdActivity = (AdActivity)this.b.get();
    if (localAdActivity == null)
      com.google.ads.util.a.e("adActivity was null while trying to enable controls on a video.");
    while (true)
    {
      while (true)
      {
        return;
        if (!(paramBoolean))
          break;
        if (this.a == null)
          this.a = new MediaController(localAdActivity);
        this.e.setMediaController(this.a);
      }
      if (this.a != null)
        this.a.hide();
      this.e.setMediaController(null);
    }
  }

  public final void b()
  {
    this.e.pause();
  }

  public final void c()
  {
    this.e.start();
  }

  public final void d()
  {
    this.e.stopPlayback();
  }

  final void e()
  {
    long l = this.e.getCurrentPosition();
    if (this.d > l)
    {
      float f1 = (float)l / 1000.0F;
      a.a(this.c, "onVideoEvent", "{'event': 'timeupdate', 'time': " + f1 + "}");
      this.d = l;
    }
  }

  public final void onCompletion(MediaPlayer paramMediaPlayer)
  {
    a.a(this.c, "onVideoEvent", "{'event': 'ended'}");
  }

  public final boolean onError(MediaPlayer paramMediaPlayer, int paramInt1, int paramInt2)
  {
    com.google.ads.util.a.e("Video threw error! <what:" + paramInt1 + ", extra:" + paramInt2 + ">");
    a.a(this.c, "onVideoEvent", "{'event': 'error', 'what': '" + paramInt1 + "', 'extra': '" + paramInt2 + "'}");
    return true;
  }

  public final void onPrepared(MediaPlayer paramMediaPlayer)
  {
    float f1 = this.e.getDuration() / 1000.0F;
    a.a(this.c, "onVideoEvent", "{'event': 'canplaythrough', 'duration': '" + f1 + "'}");
  }

  private static class a
  implements Runnable
  {
    private WeakReference<g> a;
    private Handler b;

    public a(g paramg)
    {
      this.a = new WeakReference(paramg);
      this.b = new Handler();
    }

    public final void a()
    {
      this.b.postDelayed(this, 250);
    }

    public final void run()
    {
      g localg = (g)this.a.get();
      if (localg == null)
        com.google.ads.util.a.d("The video must be gone, so cancelling the timeupdate task.");
      while (true)
      {
        return;
        localg.e();
        this.b.postDelayed(this, 250);
      }
    }
  }
}