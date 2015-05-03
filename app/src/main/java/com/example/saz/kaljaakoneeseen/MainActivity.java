package com.example.saz.kaljaakoneeseen;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.*;
import android.media.MediaPlayer;
import java.util.*;
import java.lang.*;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.widget.Button;



public class MainActivity extends ActionBarActivity {



    private int polku,count,vuorossaOleva=1, pelaajamaara;
    private ImageView kuvaPainike, ylaKuva;
    private TextView tulosteksti, pelaaja;
    private Kortti saatuKortti, ekakortti;
    private List<Kortti> pakka;
    private Button apuNappi;
    private String otsikko,saanto;
    private Global g;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //piilotetaan menubar
        android.support.v7.app.ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        setContentView(com.example.saz.kaljaakoneeseen.R.layout.activity_main);


        g = (Global)getApplication();

        pelaajamaara= g.getPelaajamaara();

        System.out.println("TESTI 1: "+pelaajamaara);

        uusiPeli(pelaajamaara);

    }



    public void uusiPeli(final int pelaajalkm){

        //luodaan uusi lista "pakka"
        pakka = new ArrayList<>();

        g = (Global)getApplication();
        final MediaPlayer mp = MediaPlayer.create(this, R.raw.draw);

        //alustetaan alkutilanne kun peliä ei ole alotettu ja ohjekortti.
        ekakortti = new Kortti(0,0,0,"Paina korttia niin peli alkaa!",R.drawable.j);
        saatuKortti=ekakortti;
        count=0;

        //rakentaa ja sekoittaa pakan
        rakennaPakka();


        System.out.println("TÄMÄ ON PELAAJIEN LUKUMÄÄRÄ:  "+pelaajalkm);


        //asetetaan aktiivinen pelaaja
        pelaaja = (TextView) findViewById(R.id.pelaajaVuoro);
        pelaaja.setText("Pelaaja: "+vuorossaOleva);


        //alustetaan painike ja tulosteksti
        kuvaPainike = (ImageView) findViewById(R.id.kuvaKortti);
        tulosteksti = (TextView) findViewById(R.id.tulosTeksti);

        //help nappi
        apuNappi = (Button) findViewById(R.id.apuPainike);
        apuNappi.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {

                otsikko = saatuKortti.getSaanto();

                switch (saatuKortti.getNumero()) {
                    case 0:
                        saanto= "Paina korttia niin peli alkaa!  ";
                        break;
                    case 1:
                        saanto = "Kortin nostaja ottaa itse 3 huikkaa.";
                        break;
                    case 2:
                        saanto = "Kortin nostaja saa antaa haluamalleen toiselle pelaajalle 3 huikkaa.";
                        break;
                    case 3:
                        saanto = "Kortin nostaja juo yhden huikan, tämän vasemmalla puolella istuva pelaaja 2 huikkaa ja hänen vasemmallaan istuva 3";
                        break;
                    case 4:
                        saanto = "Kun kortti 4 esiintyy pitää nopeasti huutaa HITLER ja viimeisenä huutanut joutuu juomaan 3 huikkaa";
                        break;
                    case 5:
                        saanto = "Numerokisassa aloittaja päättää numeron (väliltä 2-9) ja kaikki alkavat vuorotellen luetella numeroita. Kuitenkaan numeroita jotka sisältävät kyseisen numeron tai ovat luvun kertomia (esim jos numero on 7 näitä ovat: 7 -> 14 -> 17 -> 21 -> 27-> 28) ei saa sanoa ääneen vaan tilalla pitää sanoa PIIP. Seuraava tämän jälkeen jatkaa seuraavasta numerosta ja kuka ensimmäisenä sanoo väärin häviää ja juo rangaistuksena 3 huikkaa.";
                        break;
                    case 6:
                        saanto = "Kortin nostaja keksii kategorian, jonka mukaan vuorotellen pitää keksiä esine/asia kyseisestä kategoriasta Esim. Kategoria: automerkit -> volvo, audi jne... Jos toistaa saman, joka on jo mainittu häviää tai kun ei keksi enää uutta. Rangaistus 3 huikkaa.";
                        break;
                    case 7:
                        saanto = "Vesiputouksessa kortin nostaja aloittaa vesiputouksen ja hän saa lopettaa sen haluamallaan hetkellä. Kun hän lopettaa juomisen saa hänen vasemmalla puolella oleva lopettaa jne. kunnes kaikki ovat lopettaneet.";
                        break;
                    case 8:
                        saanto = "Kortin nostaja saa peukkukortin. Mikäli hän nostaa peukkunsa pystyyn haluamanaan ajankohtana muiden tulee myös nostaa peukku pystyyn. Viimeisin peukun ylösnostaja häviää ja juo 3 huikkaa.";
                        break;
                    case 9:
                        saanto = "Sääntökortilla kortin nostaja saa keksiä peliin uuden mielivaltaisen säännön! Säännön rikkomisesta rangaistuksena on 3 huikkaa.";
                        break;
                    case 10:
                        saanto = "Nostamalla 10 kortin pääsee kysymysmestariksi. Kysymysmestarina olevan henkilön mihinkään kysymykseen ei saa vastata tai joutuu juomaan 3 huikkaa rangaistuksena jokaisesta vastatusta kysymyksestä. Uusi kysymysmestarikortti korvaa vanhan.";
                        break;
                    case 11:
                        saanto = "Nostamalla taukokortin pääsee kortin nostanut käymään halutessaan vessassa kesken pelin ilman rangaistusta.";
                        break;
                    case 12:
                        saanto = "Nostamalla huorakortin tulee kortin nostajasta jonkun toisen pelaajan huora. Eli kortin nostaja valitsee kenen huora on ja kun hänen osoittamansa henkilö juo, joutuu myös hän itse aina juomaan saman verran. Uusi huorakortti kumoaa vanhan.";
                        break;
                    case 13:
                        saanto = "Tarinakortin nostaja aloittaa tarinan. Tarina etenee toistamalla koko tarinan ensin alusta asti ja lisäämällä siihen aina itse yhden sanan. Kun tarina menee väärin tai ei muista enää tarinaa häviää ja ottaa 3 huikkaa.";
                        break;
                }




                naytaApu(otsikko, saanto);

            }


        });





        kuvaPainike.setOnClickListener(new View.OnClickListener(){

            public void onClick(View v){



                //Täällä tapahtumat kun painaa korttia


                //vaihdetaan pelaajan tietoja

                if(saatuKortti.getId()==0){
                    vuorossaOleva=0;
                }
                if(vuorossaOleva<=pelaajalkm){
                    vuorossaOleva++;

                }if(vuorossaOleva==pelaajalkm+1){

                    vuorossaOleva=1;


                }

                pelaaja.setText("Pelaaja: "+vuorossaOleva);


                //tarkastetaan mones kortti ja joko annetaan seuraava kortti tai lopetetaan peli
                if(count<51){



                    count++;
                    //kortin vetoääni
                    mp.start();



                    saatuKortti = haeSeuraavaKortti();


                    //ASETA KUVA

                    polku = saatuKortti.getKuva();

                    kuvaPainike.setBackgroundResource(polku);

                    //ASETA SÄÄNTÖ
                    tulosteksti.setText(saatuKortti.getSaanto());



                }else {



                    kuvaPainike.setBackgroundResource(R.drawable.j);
                    tulosteksti.setText("Pakka loppui! ");


                }





            }

        });




    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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






    public List rakennaPakka(){

        //ID (int)
        //maat: (int):  1= hertta, 2=ruutu, 3= risti, 4=pata
        //numerot (int) 1=ace,... j=11, q=12,k=13
        //Saanto tekstinä (String)
        //kuva tiedostonnimi (int) -viittaus drawable resurssiin


//HERTTA

        pakka.add(new Kortti(1,1,1,"Ota 3", R.drawable.ah));
        pakka.add(new Kortti(2,1,2,"Anna 3", R.drawable.h2));
        pakka.add(new Kortti(3,1,3,"Juokaa 1,2,3", R.drawable.h3));
        pakka.add(new Kortti(4,1,4,"Hitler", R.drawable.h4));
        pakka.add(new Kortti(5,1,5,"Numerokisa", R.drawable.h5));
        pakka.add(new Kortti(6,1,6,"Kategoria", R.drawable.h6));
        pakka.add(new Kortti(7,1,7,"Vesiputous", R.drawable.h7));
        pakka.add(new Kortti(8,1,8,"Peukku", R.drawable.h8));
        pakka.add(new Kortti(9,1,9,"Sääntö", R.drawable.h9));
        pakka.add(new Kortti(10,1,10,"Kysymysmestari", R.drawable.th));
        pakka.add(new Kortti(11,1,11,"Taukokortti", R.drawable.jh));
        pakka.add(new Kortti(12,1,12,"HUORA", R.drawable.qh));
        pakka.add(new Kortti(13,1,13,"Tarina", R.drawable.kh));


        //RUUTU

        pakka.add(new Kortti(14,2,1,"Ota 3", R.drawable.ad));
        pakka.add(new Kortti(15,2,2,"Anna 3", R.drawable.d2));
        pakka.add(new Kortti(16,2,3,"Juokaa 1,2,3", R.drawable.d3));
        pakka.add(new Kortti(17,2,4,"Hitler", R.drawable.d4));
        pakka.add(new Kortti(18,2,5,"Numerokisa", R.drawable.d5));
        pakka.add(new Kortti(19,2,6,"Kategoria", R.drawable.d6));
        pakka.add(new Kortti(20,2,7,"Vesiputous", R.drawable.d7));
        pakka.add(new Kortti(21,2,8,"Peukku", R.drawable.d8));
        pakka.add(new Kortti(22,2,9,"Sääntö", R.drawable.d9));
        pakka.add(new Kortti(23,2,10,"Kysymysmestari", R.drawable.td));
        pakka.add(new Kortti(24,2,11,"Taukokortti", R.drawable.jd));
        pakka.add(new Kortti(25,2,12,"HUORA", R.drawable.qd));
        pakka.add(new Kortti(26,2,13,"Tarina", R.drawable.kd));


//RISTI

        pakka.add(new Kortti(27,3,1,"Ota 3", R.drawable.ac));
        pakka.add(new Kortti(28,3,2,"Anna 3", R.drawable.c2));
        pakka.add(new Kortti(29,3,3,"Juokaa 1,2,3", R.drawable.c3));
        pakka.add(new Kortti(30,3,4,"Hitler", R.drawable.c4));
        pakka.add(new Kortti(31,3,5,"Numerokisa", R.drawable.c5));
        pakka.add(new Kortti(32,3,6,"Kategoria", R.drawable.c6));
        pakka.add(new Kortti(33,3,7,"Vesiputous", R.drawable.c7));
        pakka.add(new Kortti(34,3,8,"Peukku", R.drawable.c8));
        pakka.add(new Kortti(35,3,9,"Sääntö", R.drawable.c9));
        pakka.add(new Kortti(36,3,10,"Kysymysmestari", R.drawable.tc));
        pakka.add(new Kortti(37,3,11,"Taukokortti", R.drawable.jc));
        pakka.add(new Kortti(38,3,12,"HUORA", R.drawable.qc));
        pakka.add(new Kortti(39,3,13,"Tarina", R.drawable.kc));

//PATA

        pakka.add(new Kortti(40,4,1,"Ota 3", R.drawable.as));
        pakka.add(new Kortti(41,4,2,"Anna 3", R.drawable.s2));
        pakka.add(new Kortti(42,4,3,"Juokaa 1,2,3", R.drawable.s3));
        pakka.add(new Kortti(43,4,4,"Hitler", R.drawable.s4));
        pakka.add(new Kortti(44,4,5,"Numerokisa", R.drawable.s5));
        pakka.add(new Kortti(45,4,6,"Kategoria", R.drawable.s6));
        pakka.add(new Kortti(46,4,7,"Vesiputous", R.drawable.s7));
        pakka.add(new Kortti(47,4,8,"Peukku", R.drawable.s8));
        pakka.add(new Kortti(48,4,9,"Sääntö", R.drawable.s9));
        pakka.add(new Kortti(49,4,10,"Kysymysmestari", R.drawable.ts));
        pakka.add(new Kortti(50,4,11,"Taukokortti", R.drawable.js));
        pakka.add(new Kortti(51,4,12,"HUORA", R.drawable.qs));
        pakka.add(new Kortti(52,4,13,"Tarina", R.drawable.ks));


        //sekoittaa pakan
        Collections.shuffle(pakka);

 return pakka;

    }



    //tätä ei enää käytetä pelissä
    //haeSeuraavaKortti korvasi tämän metodin

    public Kortti arvoKortti() {

        Kortti kortti;

        int arvottuKortti = (int)(Math.random()*51);

        kortti = pakka.get(arvottuKortti);

        return kortti;

    }


    public Kortti haeSeuraavaKortti(){

        Kortti kortti;

        kortti = pakka.get(count);

        return kortti;
    }


    private void naytaApu(String otsikko, String saanto) {

        AlertDialog.Builder helpBuilder = new AlertDialog.Builder(this);
        helpBuilder.setTitle(otsikko);
        helpBuilder.setMessage(saanto);
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





}
