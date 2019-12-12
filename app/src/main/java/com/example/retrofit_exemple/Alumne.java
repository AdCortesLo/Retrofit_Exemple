package com.example.retrofit_exemple;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Alumne {


    @SerializedName("id")
    @Expose
    private int id;

    @SerializedName("nom")
    @Expose
    private String nom;

    @SerializedName("edat")
    @Expose
    private int edat;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public int getEdat() {
        return edat;
    }

    public void setEdat(int edat) {
        this.edat = edat;
    }
}
