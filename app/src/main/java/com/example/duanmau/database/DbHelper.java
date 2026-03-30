package com.example.duanmau.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DbHelper extends SQLiteOpenHelper {
    public static final String DB_NAME = "duanmau.db";
    public static final int DB_VERSION = 2; // Tăng version để thực hiện onUpgrade

    public DbHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // Table DanhMuc
        String createTableDanhMuc = "CREATE TABLE DanhMuc (" +
                "maDanhMuc INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "tenDanhMuc TEXT NOT NULL)";
        db.execSQL(createTableDanhMuc);

        // Table SanPham
        String createTableSanPham = "CREATE TABLE SanPham (" +
                "maSanPham INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "tenSanPham TEXT NOT NULL, " +
                "giaBan INTEGER NOT NULL, " +
                "soLuong INTEGER NOT NULL, " +
                "maDanhMuc INTEGER REFERENCES DanhMuc(maDanhMuc))";
        db.execSQL(createTableSanPham);

        // Table NhanVien
        String createTableNhanVien = "CREATE TABLE NhanVien (" +
                "maNhanVien TEXT PRIMARY KEY, " +
                "hoTen TEXT NOT NULL, " +
                "matKhau TEXT NOT NULL, " +
                "vaiTro TEXT NOT NULL)";
        db.execSQL(createTableNhanVien);

        // Table KhachHang
        String createTableKhachHang = "CREATE TABLE KhachHang (" +
                "maKhachHang INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "hoTen TEXT NOT NULL, " +
                "soDienThoai TEXT NOT NULL, " +
                "diaChi TEXT)";
        db.execSQL(createTableKhachHang);

        // Table HoaDon
        String createTableHoaDon = "CREATE TABLE HoaDon (" +
                "maHoaDon INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "maNhanVien TEXT REFERENCES NhanVien(maNhanVien), " +
                "maKhachHang INTEGER REFERENCES KhachHang(maKhachHang), " +
                "ngayMua TEXT NOT NULL, " +
                "tongTien INTEGER NOT NULL)";
        db.execSQL(createTableHoaDon);

        // Table HoaDonChiTiet
        String createTableHoaDonChiTiet = "CREATE TABLE HoaDonChiTiet (" +
                "maHDCT INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "maHoaDon INTEGER REFERENCES HoaDon(maHoaDon), " +
                "maSanPham INTEGER REFERENCES SanPham(maSanPham), " +
                "soLuong INTEGER NOT NULL, " +
                "donGia INTEGER NOT NULL)";
        db.execSQL(createTableHoaDonChiTiet);
        
        // Insert user H với mật khẩu H
        db.execSQL("INSERT INTO NhanVien VALUES('h', 'Admin Manager', 'h', 'Admin')");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS HoaDonChiTiet");
        db.execSQL("DROP TABLE IF EXISTS HoaDon");
        db.execSQL("DROP TABLE IF EXISTS KhachHang");
        db.execSQL("DROP TABLE IF EXISTS NhanVien");
        db.execSQL("DROP TABLE IF EXISTS SanPham");
        db.execSQL("DROP TABLE IF EXISTS DanhMuc");
        onCreate(db);
    }
}
