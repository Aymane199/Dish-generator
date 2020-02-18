package com.example.mic.projet_v3.Tables;

public class Etape {

    int Etape_ID ;
    String Etape_NAME ;
    String Etape_Description;
    byte[] Etape_POTO ;
    int Etape_ID_USER  ;

    public Etape() {
    }

    public Etape(int Etape_ID, String Etape_NAME, String Etape_Description, byte[] Etape_POTO, int Etape_ID_USER) {
        this.Etape_ID = Etape_ID;
        this.Etape_NAME = Etape_NAME;
        this.Etape_Description = Etape_Description;
        this.Etape_POTO = Etape_POTO;
        this.Etape_ID_USER = Etape_ID_USER;
    }

    public Etape(String Etape_NAME, String Etape_Description, byte[] Etape_POTO, int Etape_ID_USER) {
        this.Etape_NAME = Etape_NAME;
        this.Etape_Description = Etape_Description;
        this.Etape_POTO = Etape_POTO;
        this.Etape_ID_USER = Etape_ID_USER;
    }

    public void setEtape_ID(int Etape_ID) {
        this.Etape_ID = Etape_ID;
    }

    public void setEtape_NAME(String Etape_NAME) {
        this.Etape_NAME = Etape_NAME;
    }

    public void setEtape_Description(String Etape_Description) {
        this.Etape_Description = Etape_Description;
    }

    public void setEtape_POTO(byte[] Etape_POTO) {
        this.Etape_POTO = Etape_POTO;
    }

    public void setEtape_ID_USER(int Etape_ID_USER) {
        this.Etape_ID_USER = Etape_ID_USER;
    }

    public int getEtape_ID() {
        return Etape_ID;
    }

    public String getEtape_NAME() {
        return Etape_NAME;
    }

    public String getEtape_Description() {
        return Etape_Description;
    }

    public byte[] getEtape_POTO() {
        return Etape_POTO;
    }

    public int getEtape_ID_USER() {
        return Etape_ID_USER;
    }
}
