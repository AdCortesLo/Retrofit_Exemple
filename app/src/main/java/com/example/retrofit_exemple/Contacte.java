package com.example.retrofit_exemple;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Contacte {

    @SerializedName("contacteId")
    @Expose
    private Integer contacteId;
    @SerializedName("nom")
    @Expose
    private String nom;
    @SerializedName("cognoms")
    @Expose
    private String cognoms;

    public Integer getContacteId() {
        return contacteId;
    }

    public void setContacteId(Integer contacteId) {
        this.contacteId = contacteId;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getCognoms() {
        return cognoms;
    }

    public void setCognoms(String cognoms) {
        this.cognoms = cognoms;
    }

}