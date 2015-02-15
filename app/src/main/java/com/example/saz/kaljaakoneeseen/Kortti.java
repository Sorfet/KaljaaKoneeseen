package com.example.saz.kaljaakoneeseen;

/**
 * Created by Saz on 13.2.2015.
 */
import android.graphics.drawable.Drawable;
import android.widget.*;

public class Kortti {

    private int id;
    private int kuva;
    private int maa;
    private int numero;
    private String saanto;

    public Kortti(int id,int maa, int numero, String saanto, int kuva){

        this.id=id;
        this.maa = maa;
        this.numero = numero;
        this.saanto = saanto;
        this.kuva = kuva;

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getKuva() {
        return kuva;
    }

    public void setKuva(int kuva) {
        this.kuva = kuva;
    }

    public String getSaanto() {
        return saanto;
    }

    public void setSaanto(String saanto) {
        this.saanto = saanto;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public int getMaa() {
        return maa;
    }

    public void setMaa(int maa) {
        this.maa = maa;
    }
}
