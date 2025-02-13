package com.example.pnlib.DbHelper;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class dbHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "PNLib.db";
    private static final int DATABASE_VERSION = 1;

    public dbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTableMembers = "CREATE TABLE ThanhVien (MaTV INTEGER PRIMARY KEY AUTOINCREMENT, HoTen TEXT, NamSinh TEXT)";
        String createTableBooks = "CREATE TABLE Sach (MaSach INTEGER PRIMARY KEY AUTOINCREMENT, TenSach TEXT, GiaThue INTEGER, MaLoai INTEGER)";
        String createTableLoans = "CREATE TABLE PhieuMuon (MaPM INTEGER PRIMARY KEY AUTOINCREMENT, MaTV INTEGER, MaSach INTEGER, Ngay DATE, TienThue INTEGER)";

        db.execSQL(createTableMembers);
        db.execSQL(createTableBooks);
        db.execSQL(createTableLoans);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS ThanhVien");
        db.execSQL("DROP TABLE IF EXISTS Sach");
        db.execSQL("DROP TABLE IF EXISTS PhieuMuon");
        onCreate(db);
    }
}
