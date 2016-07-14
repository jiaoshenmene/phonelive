package com.lzfutil.util;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import cg.a;
import com.weilian.phonelive.bean.MusicBean;
import java.util.ArrayList;
import java.util.List;

public class d {
    private cg.a a;
    private SQLiteDatabase b;

    public d(Context context) {
        this.a = new cg.a(context);
        b = this.a.getWritableDatabase();
    }

    public void a(List<MusicBean> list) {
        this.b.beginTransaction();
        try {
            for (MusicBean musicBean : list) {
                this.b.execSQL("INSERT INTO music VALUES(null, ?, ?, ?)", new Object[]{musicBean.getSongname(), musicBean.getSongid(), musicBean.getArtistname()});
            }
            this.b.setTransactionSuccessful();
        } finally {
            this.b.endTransaction();
        }
    }

    public void a(MusicBean musicBean) {
        this.b.delete("music", "songid=?", new String[]{musicBean.getSongid()});
    }

    public void b(MusicBean musicBean) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("songid", musicBean.getSongid());
        this.b.update("music", contentValues, "songname = ?", new String[]{musicBean.getSongname()});
    }

    public List<MusicBean> a() {
        List arrayList = new ArrayList();
        Cursor b = b();
        while (b.moveToNext()) {
            MusicBean musicBean = new MusicBean();
            musicBean.setSongid(b.getString(b.getColumnIndex("songid")));
            musicBean.setSongname(b.getString(b.getColumnIndex("songname")));
            musicBean.setArtistname(b.getString(b.getColumnIndex("artistname")));
            arrayList.add(musicBean);
        }
        b.close();
        return arrayList;
    }

    public Cursor b() {
        return this.b.rawQuery("SELECT * FROM music", null);
    }

    public void c() {
        this.b.close();
    }
}
