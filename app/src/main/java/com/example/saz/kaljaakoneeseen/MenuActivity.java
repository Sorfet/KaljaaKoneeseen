package com.example.saz.kaljaakoneeseen;

import android.media.Image;
import android.support.v7.app.ActionBarActivity;
import com.example.saz.kaljaakoneeseen.R;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.view.View.OnClickListener;
import android.widget.*;
import android.media.MediaPlayer;
import java.util.*;
import java.math.*;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.*;
import java.io.File;
import java.lang.*;
import android.app.AlertDialog;
import android.content.DialogInterface;


import android.widget.Button;


public class MenuActivity extends ActionBarActivity  {


    Global g;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        final MediaPlayer mp = MediaPlayer.create(this, R.raw.menumusic);
        mp.setVolume(0.1f, 0.1f);

        mp.start();
        g = (Global)getApplication();


        Button uusipeliNappi = (Button) findViewById(R.id.uusiPeli);
        final Button pelaajaValikko = (Button) findViewById(R.id.pelaajaNappi);
        Button poistumisNappi = (Button) findViewById(R.id.poistuNappi);

        pelaajaValikko.setText("Pelaajien määrä:"+ g.getPelaajamaara());





        //Uusipeli painike
        uusipeliNappi.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                mp.stop();
                startActivity(new Intent(MenuActivity.this, MainActivity.class));
            }
        });



        //Pelaajavalinta nappi
        pelaajaValikko.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {


                g.setPelaajamaara(g.getPelaajamaara() + 1);

                if(g.getPelaajamaara()==13){
                   g.setPelaajamaara(2);
                }

                pelaajaValikko.setText("Pelaajien määrä: "+ g.getPelaajamaara());

            }
        });



 // poistuminen
        poistumisNappi.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {

                mp.stop();


                //POistumiskäsky tänne

                finish();
                System.exit(0);

            }
        });





    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_menu, menu);
        return true;
    }




    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }


        return super.onOptionsItemSelected(item);
    }
}
