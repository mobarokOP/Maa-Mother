package com.mobarok.pragnancytracker.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.mobarok.pragnancytracker.adapter.NoteModel;

import java.util.ArrayList;

public class DatabaseNotePad extends SQLiteOpenHelper {

    public static final int DATABASE_VERSION = 2;
    public static final String DATABASE_NAME = "notepad.db";
    public static final String TABLE_NOTE_NAME = "tbl_notepad";

    public static final String KEY_ID = "id";
    public static final String KEY_TITLE = "title";
    public static final String KEY_SYMPTOMS = "symptoms";
    public static final String KEY_DETAILS = "details";
    public static final String KEY_DATE = "date";

    public DatabaseNotePad(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String CREATE_FAVORITE_TABLE = "CREATE TABLE " + TABLE_NOTE_NAME + "("
                + KEY_ID + " INTEGER,"
                + KEY_TITLE + " TEXT,"
                + KEY_SYMPTOMS + " TEXT,"
                + KEY_DETAILS + " TEXT,"
                + KEY_DATE + " TEXT"
                + ")";
        db.execSQL(CREATE_FAVORITE_TABLE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NOTE_NAME);
        onCreate(db);
    }

    public boolean getNoteById(String story_id) {
        boolean count = false;
        SQLiteDatabase db = this.getWritableDatabase();
        String[] args = new String[]{story_id};
        Cursor cursor = db.rawQuery("SELECT id FROM "+ TABLE_NOTE_NAME +" WHERE id=? ", args);
        if (cursor.moveToFirst()) {
            count = true;
        }
        cursor.close();
        db.close();
        return count;
    }

    public void removeNoteById(String _id) {
        SQLiteDatabase db = this.getWritableDatabase();
        String[] whereArgs = { _id };
        db.delete(TABLE_NOTE_NAME, KEY_ID + " = ?", whereArgs);
        db.close();
    }


    public long addToNote(ContentValues contentvalues, String s1) {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.insert(TABLE_NOTE_NAME, s1, contentvalues);
    }



    public ArrayList<NoteModel> getNoteList() {
        ArrayList<NoteModel> noteList = new ArrayList<>();
        String selectQuery = "SELECT *  FROM "
                + TABLE_NOTE_NAME;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {
            do {
                NoteModel note = new NoteModel();
                note.setId(cursor.getString(cursor.getColumnIndexOrThrow(KEY_ID)));
                note.setSymptoms(cursor.getString(cursor.getColumnIndexOrThrow(KEY_SYMPTOMS)));
                note.setTitle(cursor.getString(cursor.getColumnIndexOrThrow(KEY_TITLE)));
                note.setDetails(cursor.getString(cursor.getColumnIndexOrThrow(KEY_DETAILS)));
                note.setDate(cursor.getString(cursor.getColumnIndexOrThrow(KEY_DATE)));
                noteList.add(note);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return noteList;
    }



}
