package com.example.saz.kaljaakoneeseen;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.content.Intent;
import android.view.View.OnClickListener;
import android.media.MediaPlayer;
import java.lang.*;



import android.widget.Button;


public class MenuActivity extends ActionBarActivity  {


    Global g;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //piilotetaan menubar
        android.support.v7.app.ActionBar actionBar = getSupportActionBar();
        actionBar.hide();


        setContentView(R.layout.activity_menu);



        //final MediaPlayer mp = MediaPlayer.create(this, R.raw.menumusic);
        //mp.setVolume(0.1f, 0.1f);

        //mp.start();
        g = (Global)getApplication();


        Button uusipeliNappi = (Button) findViewById(R.id.uusiPeli);
        final Button pelaajaValikko = (Button) findViewById(R.id.pelaajaNappi);
        Button poistumisNappi = (Button) findViewById(R.id.poistuNappi);

        pelaajaValikko.setText("Pelaajien määrä:"+ g.getPelaajamaara());





        //Uusipeli painike
        uusipeliNappi.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
        //        mp.stop();
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


              aboutTeksti();

            }
        });








    }

    public void aboutTeksti(){

        String teksti = "Huikka on perinteinen perjantai-illan pelastaja! Korttipakka puuttuu, mutta juomapeliä olisi hauska pelata? Huikka pelastaa! Tämä korttijuomapelien sekasikiölapsi saa " +
                "illan kulumaan rattoisasti taaten hauskat aloittelumeiningit suomalaiseen tyyliin !" +
                "\n \n" +
                " \n OHJEET PELAAMISEEN:" +
                "\n - Ensin valitse pelaajien lukumäärä väliltä 2-12" +
                "\n - Tämän jälkeen aloita uusi peli." +
                "\n - Aloita peli korttia painamalla. Saat uuden kortin aina kun painat korttia. Kortin alapuolella on sääntö lyhennettynä ja ohje-painikkeesta saatte lisätietoa koskien kyseistä" +
                "sääntöä. Peli kestää yhden korttipakan verran, jonka jälkeen uuden pelin voi aloittaa päävalikon kautta. " +
                "\n - Yleisrangaistus pelissä on 3 huikkaa virheestä." +
                " " +
                "\n \n \n Tekijän yhteystiedot: Sorfet   " +
                "\n email: Sorfetti@gmail.com";

        AlertDialog.Builder helpBuilder = new AlertDialog.Builder(this);
        helpBuilder.setTitle("Tietoa sovelluksesta");
        helpBuilder.setMessage(teksti);
        helpBuilder.setPositiveButton("Poistu",
                new DialogInterface.OnClickListener() {

                    public void onClick(DialogInterface dialog, int which) {
                        // Do nothing but close the dialog
                    }
                });

        // Remember, create doesn't show the dialog
        AlertDialog helpDialog = helpBuilder.create();
        helpDialog.show();



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
