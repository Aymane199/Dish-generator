package com.example.mic.projet_v3.Tables;

public class Repas {

    int REPAS_ID ;
    String REPAS_NAME;
    String REPAS_TAB_Ingredient;
    byte[] REPAS_POTO ;
    int REPAS_ID_USER  ;

    public Repas() {
    }

    public Repas(int REPAS_ID, String REPAS_NAME, String REPAS_TAB_Ingredient, byte[] REPAS_POTO, int REPAS_ID_USER) {
        this.REPAS_ID = REPAS_ID;
        this.REPAS_NAME = REPAS_NAME;
        this.REPAS_TAB_Ingredient = REPAS_TAB_Ingredient;
        this.REPAS_POTO = REPAS_POTO;
        this.REPAS_ID_USER = REPAS_ID_USER;
    }

    public Repas(String REPAS_NAME, String REPAS_TAB_Ingredient, byte[] REPAS_POTO, int REPAS_ID_USER) {
        this.REPAS_NAME = REPAS_NAME;
        this.REPAS_TAB_Ingredient = REPAS_TAB_Ingredient;
        this.REPAS_POTO = REPAS_POTO;
        this.REPAS_ID_USER = REPAS_ID_USER;
    }

    public int getREPAS_ID() { return REPAS_ID; }

    public String getREPAS_NAME() {
        return REPAS_NAME;
    }

    public String getREPAS_TAB_Ingredient() {
        return REPAS_TAB_Ingredient;
    }

    public byte[] getREPAS_POTO() {
        return REPAS_POTO;
    }

    public int getREPAS_ID_USER() {
        return REPAS_ID_USER;
    }

    public void setREPAS_ID(int REPAS_ID) {
        this.REPAS_ID = REPAS_ID;
    }

    public void setREPAS_NAME(String REPAS_NAME) {
        this.REPAS_NAME = REPAS_NAME;
    }

    public void setREPAS_TAB_Ingredient(String REPAS_TAB_Ingredient) {
        this.REPAS_TAB_Ingredient = REPAS_TAB_Ingredient;
    }

    public void setREPAS_POTO(byte[] REPAS_POTO) {
        this.REPAS_POTO = REPAS_POTO;
    }

    public void setREPAS_ID_USER(int REPAS_ID_USER) {
        this.REPAS_ID_USER = REPAS_ID_USER;
    }
}
