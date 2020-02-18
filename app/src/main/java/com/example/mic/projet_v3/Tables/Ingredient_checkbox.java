package com.example.mic.projet_v3.Tables;

public class Ingredient_checkbox {


    int Ingredient_ID ;
    String Ingredient_NAME ;
    byte[] Ingredient_POTO ;
    int Ingredient_ID_USER ;
    boolean selected = false;

    public Ingredient_checkbox(int ingredient_ID, String ingredient_NAME, byte[] ingredient_POTO, int ingredient_ID_USER) {
        Ingredient_ID = ingredient_ID;
        Ingredient_NAME = ingredient_NAME;
        Ingredient_POTO = ingredient_POTO;
        Ingredient_ID_USER = ingredient_ID_USER;
        this.selected = false;
    }

    public Ingredient_checkbox() {

        boolean selected = false;
    }

    public Ingredient_checkbox(String ingredient_NAME, byte[] ingredient_POTO, int ingredient_ID_USER) {
        Ingredient_NAME = ingredient_NAME;
        Ingredient_POTO = ingredient_POTO;
        Ingredient_ID_USER = ingredient_ID_USER;
        this.selected = false;
    }

    public int getIngredient_ID() {
        return Ingredient_ID;
    }

    public void setIngredient_ID(int ingredient_ID) {
        Ingredient_ID = ingredient_ID;
    }

    public String getIngredient_NAME() {
        return Ingredient_NAME;
    }

    public void setIngredient_NAME(String ingredient_NAME) {
        Ingredient_NAME = ingredient_NAME;
    }

    public byte[] getIngredient_POTO() {
        return Ingredient_POTO;
    }

    public void setIngredient_POTO(byte[] ingredient_POTO) {
        Ingredient_POTO = ingredient_POTO;
    }

    public int getIngredient_ID_USER() {
        return Ingredient_ID_USER;
    }

    public void setIngredient_ID_USER(int ingredient_ID_USER) {
        Ingredient_ID_USER = ingredient_ID_USER;
    }

    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }
}
