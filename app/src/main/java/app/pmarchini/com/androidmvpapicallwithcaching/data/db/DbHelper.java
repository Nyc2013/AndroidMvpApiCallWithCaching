package app.pmarchini.com.androidmvpapicallwithcaching.data.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.Nullable;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import app.pmarchini.com.androidmvpapicallwithcaching.data.model.Record;
import app.pmarchini.com.androidmvpapicallwithcaching.dependencies.ApplicationContext;
import app.pmarchini.com.androidmvpapicallwithcaching.dependencies.InfoDbName;
import app.pmarchini.com.androidmvpapicallwithcaching.dependencies.InfoDbVersion;

/**
 * Created by Pierre on 25/11/2017.
 */

@Singleton
public class DbHelper extends SQLiteOpenHelper implements DbHelperMvp{

    String tag = "ModelTest";

    private final static String T_RECORDS = "records";
    private final static String C_ID = "C_ID";
    private final static String C_ALBUM_ID = "C_ALBUM_ID";
    private final static String C_RECORD_ID = "C_RECORD_ID";
    private final static String C_TITLE = "C_TITLE";
    private final static String C_URL = "C_URL";
    private final static String C_THUMBNAIL_URL = "C_THUMBNAIL_URL";


    @Inject
    public DbHelper(@ApplicationContext Context context, @InfoDbName String name, @Nullable SQLiteDatabase.CursorFactory factory,
                    @InfoDbVersion int version) {
        super(context, name, factory, version);

        Log.i(tag,"Constructeur DbHelper");
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.i(tag,"Creation");
        db.execSQL("CREATE TABLE "+T_RECORDS+" ("+
                C_ID+		" INTEGER PRIMARY KEY AUTOINCREMENT," +
                C_ALBUM_ID+ " INTERGER," +
                C_RECORD_ID+ " INTERGER," +
                C_TITLE+ " TEXT, " +
                C_URL+ " TEXT, " +
                C_THUMBNAIL_URL+ " TEXT) ");

        Log.i(tag,"Fin Creation");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.i(tag,"Upgrade");
        db.execSQL("DROP TABLE IF EXISTS " + T_RECORDS);
        onCreate(db);
        Log.i(tag,"Fin Upgrade");
    }

    private Cursor getAllRecordsAsCursor(){

        return this.getReadableDatabase().rawQuery("select * from "+T_RECORDS, null);

    }

    private Record convert(Cursor c){
        Record s = new Record();

        s.setAlbumId(c.getInt(c.getColumnIndex(C_ALBUM_ID)));
        s.setId(c.getInt(c.getColumnIndex(C_RECORD_ID)));//il s'agit de l'id de l'entrée du json
        s.setTitle(c.getString(c.getColumnIndex(C_TITLE)));
        s.setUrl(c.getString(c.getColumnIndex(C_URL)));
        s.setThumbnailUrl(c.getString(c.getColumnIndex(C_THUMBNAIL_URL)));

        return s;
    }

    @Override
    public long save(Record s){
        ContentValues v  = new ContentValues();
        v.put(C_ALBUM_ID, s.getAlbumId());
        v.put(C_RECORD_ID, s.getId());
        v.put(C_TITLE, s.getTitle());
        v.put(C_URL, s.getUrl());
        v.put(C_THUMBNAIL_URL, s.getThumbnailUrl());

        // creation
        return this.getWritableDatabase().insert(T_RECORDS, null, v);

    }

    @Override
    public int deleteAllRecords(){
        return this.getWritableDatabase().delete(T_RECORDS, null, null);
    }

    @Override
    public List<Record> getAllRecords(){
        List<Record> result = new ArrayList<>() ;
        Cursor c = getAllRecordsAsCursor();
        if (c.moveToFirst()) {
            do {
                Record s = convert(c);
                result.add(s);
            } while (c.moveToNext());
        }
        return result;
    }
}
