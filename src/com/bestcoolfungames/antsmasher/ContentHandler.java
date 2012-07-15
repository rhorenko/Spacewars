package com.bestcoolfungames.antsmasher;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

public class ContentHandler extends Activity
{
  public void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    TextView localTextView = new TextView(this);
    setContentView(localTextView);
    localTextView.setText("Ant Smasher\nBest, Cool & Fun Games. 2011\n\nGame Design\nGuilherme Schvartsman\n\nLead Programmer\nAndre Pereira\n\nProgramming\nMarco Salles\nRodrigo Perez\n\nAdditional Programming\nMarcos Diez\nDaniel Ribeiro\n\nArts\nLeonardo Silva\n");
    localTextView.setGravity(17);
  }
}