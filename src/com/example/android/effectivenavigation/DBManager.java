package com.example.android.effectivenavigation;

import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by hackeru on 24/02/14.
 */
public class DBManager extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "appDB2";
    private static final int DATABASE_VERSION = 1;
    private static final SQLiteDatabase.CursorFactory DATABASE_FACTORY = null;

    private static DBManager db;

    private DBManager(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);

    }

    public static DBManager myData(Context context) {
        if (db == null) {
            db = new DBManager(context, DATABASE_NAME, DATABASE_FACTORY, DATABASE_VERSION);
            creatTable();
      //      insert();
        }
        return db;
    }

    public static void creatTable(){


        try {
            db.getWritableDatabase().execSQL("CREATE TABLE products(id INTEGER, name VARCHAR, quantity INTEGER, category VARCHAR, store VARCHAR)");
        } catch (SQLException e) {
            e.printStackTrace();
            Log.i("****", " null Exception create Table");
        }

    }

    public static void createProduct(int id, String prodName, int quan, String category, String store) {

        try {
            db.getWritableDatabase().execSQL("INSERT INTO products VALUES (" + id + ", '" + prodName + "', " + quan + ", '" + category + "','" + store + "');");
        } catch (SQLException e) {
            e.printStackTrace();
            Log.i("****", " null Exception insert Product");
        }
    }

    public static void insert() {
        createProduct(101, "Canon EOS Rebel T3", 10, "Digital Cameras", "Cameras&Photo");
        createProduct(102, "Mobius ActionCam", 34, "Camcorders", "Cameras&Photo");
        createProduct(103, "Nikon brand camera strap", 65, "Camera Accessories", "Cameras&Photo");
        createProduct(104, "Studio Chromakey ", 10, "Lighting & Studio", "Cameras&Photo");

        createProduct(201, "Apple iPad ", 43, "iPads, Tablets", "Computers/Tablets");
        createProduct(202, "Apple iPad ", 5, "Laptops & Netbooks", "Computers/Tablets");
        createProduct(203, "Dell Optiplex 740 ", 5, "Desktops", "Computers/Tablets");
        createProduct(204, "HP COLORJET LASER", 65, "Printers, Scanners", "Computers/Tablets");

        createProduct(301, "Motorola Droid X1", 8, "Cell Phones1", "Cell Phones");
        createProduct(302, "Motorola Droid X2", 23, "Cell Phones2", "Cell Phones");
        createProduct(303, "Motorola Droid X3", 98, "Cell Phones3", "Cell Phones");
        createProduct(304, "Motorola Droid X4", 21, "Cell Phones4", "Cell Phones");

        createProduct(401, "Samsung LN32D403 32`", 54, "TV, Audio1", "TV, Audio");
        createProduct(402, "Samsung LN32D403 34`", 34, "TV, Audio2", "TV, Audio");
        createProduct(403, "Samsung LN32D403 36`", 65, "TV, Audio3", "TV, Audio");
        createProduct(404, "Samsung LN32D403 78`", 9, "TV, Audio4", "TV, Audio");


    }


    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {




    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i2) {

    }
}
