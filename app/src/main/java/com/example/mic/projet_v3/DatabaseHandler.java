package com.example.mic.projet_v3;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.mic.projet_v3.Tables.User;
import com.example.mic.projet_v3.Tables.Etape;
import com.example.mic.projet_v3.Tables.Ingredient;
import com.example.mic.projet_v3.Tables.Ingredient_checkbox;
import com.example.mic.projet_v3.Tables.Repas;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by sada on 1/31/2017.
 */
public class DatabaseHandler extends SQLiteOpenHelper {

    // Database Version
    private static final int DATABASE_VERSION = 1;

    // Database Name
    private static final String DATABASE_NAME = "ingredientsManager2";

    // Tables
    private static final String TABLE_ingredient = "ingredient";
    private static final String TABLE_Repas = "repas";
    private static final String TABLE_User = "user";
    private static final String TABLE_Etape = "etape";

    //user
    private static final String KEY_USER_ID = "user_id";
    private static final String KEY_USER_NAME = "user_name";
    private static final String KEY_USER_AGE = "user_age";
    private static final String KEY_USER_SEXE = "user_sexe";
    private static final String KEY_USER_USERNAME = "user_username";
    private static final String KEY_USER_PASSWORD = "user_password";
    //ingredient
    private static final String KEY_Ingredient_ID = "Ingredient_id";
    private static final String KEY_Ingredient_NAME = "Ingredient_name";
    private static final String KEY_Ingredient_POTO = "Ingredient_tab_poto";
    private static final String KEY_Ingredient_ID_USER = "Ingredient_id_user";
    //repas
    private static final String KEY_REPAS_ID = "repas_id";
    private static final String KEY_REPAS_NAME = "repas_name";
    private static final String KEY_REPAS_TAB_Ingredient = "repas_tab_ingredient";
    private static final String KEY_REPAS_POTO = "repas_tab_poto";
    private static final String KEY_REPAS_ID_USER = "repas_id_user";
    //Etape
    private static final String KEY_Etape_ID = "Etape_id";
    private static final String KEY_Etape_NAME = "Etape_name";
    private static final String KEY_Etape_POTO = "Etape_tab_poto";
    private static final String KEY_Etape_Description = "Etape_description";
    private static final String KEY_Etape_ID_USER = "Etape_id_user";

    public DatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    //Create tables
    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_TABLE = "CREATE TABLE " + TABLE_User + "("
                + KEY_USER_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + KEY_USER_NAME + " TEXT,"
                + KEY_USER_AGE + " TEXT,"
                + KEY_USER_SEXE + " TEXT,"
                + KEY_USER_USERNAME + " TEXT,"
                + KEY_USER_PASSWORD + " TEXT" + ")";
        db.execSQL(CREATE_TABLE);

        CREATE_TABLE = "CREATE TABLE " + TABLE_ingredient + "("
                + KEY_Ingredient_ID + " INTEGER PRIMARY KEY AUTOINCREMENT ,"
                + KEY_Ingredient_NAME + " TEXT,"
                + KEY_Ingredient_POTO + " BLOB,"
                + KEY_Ingredient_ID_USER + " INTEGER" + ")";
        db.execSQL(CREATE_TABLE);

        CREATE_TABLE = "CREATE TABLE " + TABLE_Repas + "("
                + KEY_REPAS_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + KEY_REPAS_NAME + " TEXT,"
                + KEY_REPAS_TAB_Ingredient + " TEXT,"
                + KEY_REPAS_POTO + " BLOB,"
                + KEY_REPAS_ID_USER + " INTEGER" + ")";
        db.execSQL(CREATE_TABLE);

        CREATE_TABLE = "CREATE TABLE " + TABLE_Etape + "("
                + KEY_Etape_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + KEY_Etape_NAME + " TEXT,"
                + KEY_Etape_Description + " TEXT,"
                + KEY_Etape_POTO + " BLOB,"
                + KEY_Etape_ID_USER + " INTEGER" + ")";
        db.execSQL(CREATE_TABLE);

    }

    // Upgrading database
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_ingredient);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_User);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_Repas);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_Etape);

        // Create tables again
        onCreate(db);
    }

    /**
     * All CRUD(Create, Read, Update, Delete) Operations
     */

    //Insert values to the table ingredients
    public void addingredients(Ingredient ingredient){

        SQLiteDatabase db = this.getReadableDatabase();
        ContentValues values=new ContentValues();

        values.put(KEY_Ingredient_NAME, ingredient.getIngredient_NAME());
        values.put(KEY_Ingredient_POTO, ingredient.getIngredient_POTO() );
        values.put(KEY_Ingredient_ID_USER, ingredient.getIngredient_ID_USER() );


        db.insert(TABLE_ingredient, null, values);
        db.close();
    }
    public void addUser(User user){
        SQLiteDatabase db = this.getReadableDatabase();
        ContentValues values=new ContentValues();

        values.put(KEY_USER_NAME, user.getUser_name());
        values.put(KEY_USER_AGE, user.getUser_age() );
        values.put(KEY_USER_SEXE, user.getUser_sexe() );
        values.put(KEY_USER_USERNAME, user.getUser_username() );
        values.put(KEY_USER_PASSWORD, user.getUser_password() );


        db.insert(TABLE_User, null, values);
        db.close();
    }
    public void addrepas(Repas repas){
            SQLiteDatabase db = this.getReadableDatabase();
            ContentValues values=new ContentValues();

            values.put(KEY_REPAS_NAME, repas.getREPAS_NAME());
            values.put(KEY_REPAS_TAB_Ingredient, repas.getREPAS_TAB_Ingredient() );
            values.put(KEY_REPAS_POTO, repas.getREPAS_POTO());
            values.put(KEY_REPAS_ID_USER, repas.getREPAS_ID_USER() );


            db.insert(TABLE_Repas, null, values);
            db.close();
        }
    public void addetape(Etape etape){
            SQLiteDatabase db = this.getReadableDatabase();
            ContentValues values=new ContentValues();

            values.put(KEY_Etape_NAME, etape.getEtape_NAME());
            values.put(KEY_Etape_POTO, etape.getEtape_POTO());
            values.put(KEY_Etape_Description, etape.getEtape_Description());
            values.put(KEY_Etape_ID_USER, etape.getEtape_ID_USER() );


            db.insert(TABLE_Etape, null, values);
            db.close();
        }



    /**
     *Getting All ingredients
     **/

    public Ingredient get_ingredients_by_id(int id) {
        String selectQuery = "SELECT  * FROM " + TABLE_ingredient+" where "+KEY_Ingredient_ID+" = "+id+"";;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        Ingredient ingredient = new Ingredient();

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
               ingredient.setIngredient_ID(Integer.parseInt(cursor.getString(0)));
                ingredient.setIngredient_NAME(cursor.getString(1));
                ingredient.setIngredient_POTO(cursor.getBlob(2));
                ingredient.setIngredient_ID_USER(Integer.parseInt(cursor.getString(3)));

          } while (cursor.moveToNext());
        }
        return ingredient;
    }
    public List<Ingredient> getAllingredients() {
        List<Ingredient> ingredientList = new ArrayList<Ingredient>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_ingredient+ " ORDER BY "+KEY_Ingredient_NAME+" ASC";

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                Ingredient ingredient = new Ingredient();
                ingredient.setIngredient_ID(Integer.parseInt(cursor.getString(0)));
                ingredient.setIngredient_NAME(cursor.getString(1));
                ingredient.setIngredient_POTO(cursor.getBlob(2));
                ingredient.setIngredient_ID_USER(Integer.parseInt(cursor.getString(3)));


                // Adding ingredient to list
                ingredientList.add(ingredient);
            } while (cursor.moveToNext());
        }
        return ingredientList;
    }
    public List<Ingredient_checkbox> getAllingredients_checkbox() {

                List<Ingredient_checkbox> ingredientList = new ArrayList<Ingredient_checkbox>();
                // Select All Query
                String selectQuery = "SELECT  * FROM " + TABLE_ingredient+ " ORDER BY "+KEY_Ingredient_NAME+" ASC";

                SQLiteDatabase db = this.getWritableDatabase();
                Cursor cursor = db.rawQuery(selectQuery, null);

                // looping through all rows and adding to list
                if (cursor.moveToFirst()) {
                    do {
                        Ingredient_checkbox ingredient = new Ingredient_checkbox();
                        ingredient.setIngredient_ID(Integer.parseInt(cursor.getString(0)));
                        ingredient.setIngredient_NAME(cursor.getString(1));
                        ingredient.setIngredient_POTO(cursor.getBlob(2));
                        ingredient.setIngredient_ID_USER(Integer.parseInt(cursor.getString(3)));


                        // Adding ingredient to list
                        ingredientList.add(ingredient);
                    } while (cursor.moveToNext());
                }// return ingredient list
            return ingredientList;
        }
    public List<User> getAllUsers() {
        List<User> UserList = new ArrayList<User>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_User;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                User User = new User();
                User.setUser_id(Integer.parseInt(cursor.getString(0)));
                User.setUser_name(cursor.getString(1));
                User.setUser_age(cursor.getString(2));
                User.setUser_sexe(cursor.getString(3));
                User.setUser_username(cursor.getString(4));
                User.setUser_password(cursor.getString(5));


                // Adding User to list
                UserList.add(User);
            } while (cursor.moveToNext());
        }

        // return User list
        return UserList;
    }
    public int CheckUser(String Username , String Password) {
       String selectQuery = "SELECT  * FROM " + TABLE_User + " where "+KEY_USER_USERNAME +" = '"+Username+"' AND "+KEY_USER_PASSWORD+" = '"+Password+"' ";

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        int id = -1;

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
                id = Integer.parseInt(cursor.getString(0));
            } while (cursor.moveToNext());


        // return User list
        return id;
    }
    public User getUser_by_id(int id) {
       String selectQuery = "SELECT  * FROM " + TABLE_User + " where "+KEY_USER_ID +" = "+id+"";

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        User User = new User();

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            User.setUser_id(Integer.parseInt(cursor.getString(0)));
            User.setUser_name(cursor.getString(1));
            User.setUser_age(cursor.getString(2));
            User.setUser_sexe(cursor.getString(3));
            User.setUser_username(cursor.getString(4));
            User.setUser_password(cursor.getString(5));
            } while (cursor.moveToNext());


        // return User list
        return User;
    }
    public List<Repas> getAllrepass() {
        List<Repas> repasList = new ArrayList<Repas>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_Repas+" ORDER BY "+KEY_REPAS_NAME+" ASC";

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                Repas ingr = new Repas();
                ingr.setREPAS_ID(Integer.parseInt(cursor.getString(0)));
                ingr.setREPAS_NAME(cursor.getString(1));
                ingr.setREPAS_TAB_Ingredient(cursor.getString(2));
                ingr.setREPAS_POTO(cursor.getBlob(3));
                ingr.setREPAS_ID_USER(Integer.parseInt(cursor.getString(4)));


                // Adding contact to list
                repasList.add(ingr);
            } while (cursor.moveToNext());
        }
        return repasList;
    }
    public Repas get_repass_by_id(int id) {
        Repas ingr = new Repas();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_Repas+" where "+KEY_REPAS_ID+" = "+id+"";

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                ingr.setREPAS_ID(Integer.parseInt(cursor.getString(0)));
                ingr.setREPAS_NAME(cursor.getString(1));
                ingr.setREPAS_TAB_Ingredient(cursor.getString(2));
                ingr.setREPAS_POTO(cursor.getBlob(3));
                ingr.setREPAS_ID_USER(Integer.parseInt(cursor.getString(4)));


                // Adding contact to list
                } while (cursor.moveToNext());
        }
        return ingr;
    }
    public int getMaxidRepas() {
       String selectQuery = "SELECT  MAX("+KEY_REPAS_ID+") FROM " + TABLE_Repas;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        int id = -1;
        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                id=Integer.parseInt(cursor.getString(0));

            } while (cursor.moveToNext());
        }
        return id;
    }
    public int get_nb_Repas_by_id_user(int id) {
       String selectQuery = "SELECT count(*) FROM " + TABLE_Repas + " where "+KEY_REPAS_ID_USER+" = "+id ;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        int nb = 0;
        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                nb=Integer.parseInt(cursor.getString(0));

            } while (cursor.moveToNext());
        }
        return nb;
    }
    public List<Etape> getAlletape() {
        List<Etape> etapeList = new ArrayList<Etape>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_Etape;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                Etape etape = new Etape();
                etape.setEtape_ID(Integer.parseInt(cursor.getString(0)));
                etape.setEtape_NAME(cursor.getString(1));
                etape.setEtape_Description(cursor.getString(2));
                etape.setEtape_POTO(cursor.getBlob(3));
                etape.setEtape_ID_USER(Integer.parseInt(cursor.getString(4)));


                // Adding etape to list
                etapeList.add(etape);
            } while (cursor.moveToNext());
        }

        // return etape list
        return etapeList;
    }
    public List<Etape> getAlletape_by_idrepas(int repas){
        List<Etape> etapeList = new ArrayList<Etape>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_Etape+" where "+KEY_Etape_ID_USER+" = "+repas;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                Etape etape = new Etape();
                etape.setEtape_ID(Integer.parseInt(cursor.getString(0)));
                etape.setEtape_NAME(cursor.getString(1));
                etape.setEtape_Description(cursor.getString(2));
                etape.setEtape_POTO(cursor.getBlob(3));
                etape.setEtape_ID_USER(Integer.parseInt(cursor.getString(4)));


                // Adding etape to list
                etapeList.add(etape);
            } while (cursor.moveToNext());
        }

        // return etape list
        return etapeList;
    }
    /**
     *Updating single ingredient
     **/

   /* public int updateingredient(ingredient ingredient, int id) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_Ingredient_NAME, ingredient.getFName());
        values.put(KEY_Ingredient_POTO, ingredient.getImage());


        // updating row
        return db.update(TABLE_ingredient, values, KEY_Ingredient_ID + " = ?",
                new String[] { String.valueOf(id) });
    }*/

    /**
     *Deleting single ingredient
     **/

    public void deleteingredient(int Id) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_ingredient, KEY_Ingredient_ID + " = ?",
                new String[] { String.valueOf(Id) });
        db.close();
    }

}