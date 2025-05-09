package com.example.mainactivity;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import androidx.annotation.Nullable;

public class BaseDatos extends SQLiteOpenHelper {

    public BaseDatos(@Nullable Context context, int version) {
        super(context, DefBD.nameDb, null, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL(DefBD.create_tabla_lib);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP TABLE IF EXISTS " + DefBD.tabla_lib);

        onCreate(db);
    }
}
