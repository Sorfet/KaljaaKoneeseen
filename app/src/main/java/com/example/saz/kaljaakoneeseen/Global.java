package com.example.saz.kaljaakoneeseen;

/**
 * Created by Saz on 14.2.2015.
 */
import android.app.*;



public class Global extends Application{


    private int pelaajamaara=2;

    public int getPelaajamaara() {
        return pelaajamaara;
    }

    public void setPelaajamaara(int pelaajamaara) {
        this.pelaajamaara = pelaajamaara;
    }
}
