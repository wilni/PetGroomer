package com.example.petgroomer;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class PetDBHelper extends SQLiteOpenHelper {
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "PetReader.db";

    public PetDBHelper(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);

    }
    // SQL string for creating entry
    private static final String SQL_CREATE_ENTRIES = "CREATE TABLE " + PetHelper.PetEntry.TABLE_NAME + " (" +
            PetHelper.PetEntry._ID + " INTEGER PRIMARY KEY," +
            PetHelper.PetEntry.COLUMN_NAME_NAME + " TEXT," +
            PetHelper.PetEntry.COLUMN_NAME_BREED + " REAL," +
            PetHelper.PetEntry.COLUMN_NAME_WEIGHT + " INTEGER," +
            PetHelper.PetEntry.COLUMN_NAME_SPEC_INSTRUCTIONS + " TEXT)";

    //SQL string for deleting all entries
    private static final String SQL_DELETE_ENTRIES =
            "DROP TABLE IF EXISTS " + PetHelper.PetEntry.TABLE_NAME;

    //SQL string for deleting single entry

    //sql string for update


    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(SQL_CREATE_ENTRIES);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL(SQL_DELETE_ENTRIES);
        onCreate(sqLiteDatabase);
    }
    @Override
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onUpgrade(db, oldVersion, newVersion);
    }
}
