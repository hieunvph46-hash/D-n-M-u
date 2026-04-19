package com.example.duanmau.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DbHelper extends SQLiteOpenHelper {
    public static final String DB_NAME = "duanmau.db";
    public static final int DB_VERSION = 6; // Tăng version để cập nhật cấu trúc bảng Sản phẩm (thêm ngày nhập)

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
                "ngayNhap TEXT, " +
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



        // 1. Chèn Danh Mục
        db.execSQL("INSERT INTO DanhMuc (tenDanhMuc) VALUES ('Thực phẩm')");
        db.execSQL("INSERT INTO DanhMuc (tenDanhMuc) VALUES ('Mỹ phẩm')");
        db.execSQL("INSERT INTO DanhMuc (tenDanhMuc) VALUES ('Điện tử')");
        db.execSQL("INSERT INTO DanhMuc (tenDanhMuc) VALUES ('Gia dụng')");

        // 2. Chèn Sản Phẩm
        db.execSQL("INSERT INTO SanPham (tenSanPham, giaBan, soLuong, ngayNhap, maDanhMuc) VALUES ('Sữa tươi Vinamilk', 28000, 100, '10/04/2026', 1)");
        db.execSQL("INSERT INTO SanPham (tenSanPham, giaBan, soLuong, ngayNhap, maDanhMuc) VALUES ('Bánh quy Cosy', 45000, 50, '10/04/2026', 1)");
        db.execSQL("INSERT INTO SanPham (tenSanPham, giaBan, soLuong, ngayNhap, maDanhMuc) VALUES ('Dầu gội Clear', 165000, 30, '15/04/2026', 2)");
        db.execSQL("INSERT INTO SanPham (tenSanPham, giaBan, soLuong, ngayNhap, maDanhMuc) VALUES ('Sữa tắm Lifebuoy', 120000, 40, '15/04/2026', 2)");
        db.execSQL("INSERT INTO SanPham (tenSanPham, giaBan, soLuong, ngayNhap, maDanhMuc) VALUES ('Điện thoại iPhone 15', 25000000, 10, '18/04/2026', 3)");
        db.execSQL("INSERT INTO SanPham (tenSanPham, giaBan, soLuong, ngayNhap, maDanhMuc) VALUES ('Laptop Dell XPS', 32000000, 5, '18/04/2026', 3)");
        db.execSQL("INSERT INTO SanPham (tenSanPham, giaBan, soLuong, ngayNhap, maDanhMuc) VALUES ('Nồi cơm điện Sunhouse', 950000, 15, '18/04/2026', 4)");
        db.execSQL("INSERT INTO SanPham (tenSanPham, giaBan, soLuong, ngayNhap, maDanhMuc) VALUES ('Chảo chống dính', 250000, 20, '19/04/2026', 4)");

        // 3. Chèn Nhân Viên
        db.execSQL("INSERT INTO NhanVien VALUES('h', 'Admin Manager', 'h', 'Admin')");
        db.execSQL("INSERT INTO NhanVien VALUES('admin', 'Tổng quản lý', 'admin', 'Admin')");
        db.execSQL("INSERT INTO NhanVien VALUES('nv01', 'Nguyễn Văn An', 'h', 'Nhân viên')");

        // 4. Chèn Khách Hàng
        db.execSQL("INSERT INTO KhachHang (hoTen, soDienThoai, diaChi) VALUES ('Khách lẻ', '00000000', 'Hệ thống')");
        db.execSQL("INSERT INTO KhachHang (hoTen, soDienThoai, diaChi) VALUES ('Khách vIP', '00000001', 'Quang Ninh')");


        // 5. Chèn Hóa Đơn
        db.execSQL("INSERT INTO HoaDon (maNhanVien, maKhachHang, ngayMua, tongTien) VALUES ('h', 1, '20/05/2024', 73000)");

        // 6. Chèn Hóa Đơn Chi Tiết
        db.execSQL("INSERT INTO HoaDonChiTiet (maHoaDon, maSanPham, soLuong, donGia) VALUES (1, 1, 1, 28000)");
        db.execSQL("INSERT INTO HoaDonChiTiet (maHoaDon, maSanPham, soLuong, donGia) VALUES (1, 2, 1, 45000)");
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
