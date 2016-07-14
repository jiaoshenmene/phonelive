package cg;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class a extends SQLiteOpenHelper {
    private static final String a = "phonelive.db";
    private static final int b = 1;

    public a(Context context) {
        super(context, a, null, b);
    }

    public void onCreate(SQLiteDatabase sQLiteDatabase) {
        sQLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS music(_id INTEGER PRIMARY KEY AUTOINCREMENT, songname VARCHAR, songid INTEGER, artistname VARCHAR)");
    }

    public void onUpgrade(SQLiteDatabase sQLiteDatabase, int i, int i2) {
    }
}
