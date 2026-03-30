package com.example.duanmau.dao;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import com.example.duanmau.database.DbHelper;
import com.example.duanmau.model.HoaDon;
import com.example.duanmau.model.HoaDonChiTiet;
import java.util.ArrayList;
import java.util.List;

public class HoaDonDAO {
    private final SQLiteDatabase db;

    public HoaDonDAO(Context context) {
        DbHelper dbHelper = new DbHelper(context);
        db = dbHelper.getWritableDatabase();
    }

    public long insertHoaDon(HoaDon obj) {
        ContentValues values = new ContentValues();
        values.put("maNhanVien", obj.getMaNhanVien());
        values.put("maKhachHang", obj.getMaKhachHang());
        values.put("ngayMua", obj.getNgayMua());
        values.put("tongTien", obj.getTongTien());
        return db.insert("HoaDon", null, values);
    }

    public long insertHDCT(HoaDonChiTiet obj) {
        ContentValues values = new ContentValues();
        values.put("maHoaDon", obj.getMaHoaDon());
        values.put("maSanPham", obj.getMaSanPham());
        values.put("soLuong", obj.getSoLuong());
        values.put("donGia", obj.getDonGia());
        return db.insert("HoaDonChiTiet", null, values);
    }

    public int delete(String id) {
        db.delete("HoaDonChiTiet", "maHoaDon=?", new String[]{id});
        return db.delete("HoaDon", "maHoaDon=?", new String[]{id});
    }

    @SuppressLint("Range")
    public List<HoaDon> getData(String sql, String... selectionArgs) {
        List<HoaDon> list = new ArrayList<>();
        Cursor c = db.rawQuery(sql, selectionArgs);
        while (c.moveToNext()) {
            HoaDon obj = new HoaDon();
            obj.setMaHoaDon(c.getInt(c.getColumnIndex("maHoaDon")));
            obj.setMaNhanVien(c.getString(c.getColumnIndex("maNhanVien")));
            obj.setMaKhachHang(c.getInt(c.getColumnIndex("maKhachHang")));
            obj.setNgayMua(c.getString(c.getColumnIndex("ngayMua")));
            obj.setTongTien(c.getInt(c.getColumnIndex("tongTien")));
            list.add(obj);
        }
        c.close();
        return list;
    }

    public List<HoaDon> getAll() {
        String sql = "SELECT * FROM HoaDon";
        return getData(sql);
    }

    @SuppressLint("Range")
    public List<HoaDonChiTiet> getChiTietHoaDon(int maHoaDon) {
        List<HoaDonChiTiet> list = new ArrayList<>();
        String sql = "SELECT HoaDonChiTiet.*, SanPham.tenSanPham FROM HoaDonChiTiet " +
                     "JOIN SanPham ON HoaDonChiTiet.maSanPham = SanPham.maSanPham " +
                     "WHERE maHoaDon = ?";
        Cursor c = db.rawQuery(sql, new String[]{String.valueOf(maHoaDon)});
        while (c.moveToNext()) {
            HoaDonChiTiet obj = new HoaDonChiTiet();
            obj.setMaHDCT(c.getInt(c.getColumnIndex("maHDCT")));
            obj.setMaHoaDon(c.getInt(c.getColumnIndex("maHoaDon")));
            obj.setMaSanPham(c.getInt(c.getColumnIndex("maSanPham")));
            obj.setSoLuong(c.getInt(c.getColumnIndex("soLuong")));
            obj.setDonGia(c.getInt(c.getColumnIndex("donGia")));
            obj.setTenSanPham(c.getString(c.getColumnIndex("tenSanPham")));
            list.add(obj);
        }
        c.close();
        return list;
    }
}
