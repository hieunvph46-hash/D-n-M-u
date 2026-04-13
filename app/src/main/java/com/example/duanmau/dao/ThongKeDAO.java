package com.example.duanmau.dao;

import android.annotation.SuppressLint;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import com.example.duanmau.database.DbHelper;
import com.example.duanmau.model.KhachHang;
import com.example.duanmau.model.SanPham;
import java.util.ArrayList;
import java.util.List;

public class ThongKeDAO {
    private final SQLiteDatabase db;

    public ThongKeDAO(Context context) {
        DbHelper dbHelper = new DbHelper(context);
        db = dbHelper.getWritableDatabase();
    }

    @SuppressLint("Range")
    public List<SanPham> getTopSanPham(String tuNgay, String denNgay, String limit) {
        // Chuyển đổi dd/MM/yyyy sang yyyyMMdd để so sánh chuỗi trong SQLite
        String tu = formatDate(tuNgay);
        String den = formatDate(denNgay);

        String sql = "SELECT SanPham.maSanPham, SanPham.tenSanPham, SUM(HoaDonChiTiet.soLuong) as soLuongBan " +
                "FROM SanPham JOIN HoaDonChiTiet ON SanPham.maSanPham = HoaDonChiTiet.maSanPham " +
                "JOIN HoaDon ON HoaDonChiTiet.maHoaDon = HoaDon.maHoaDon " +
                "WHERE (substr(ngayMua,7,4)||substr(ngayMua,4,2)||substr(ngayMua,1,2)) " +
                "BETWEEN ? AND ? " +
                "GROUP BY SanPham.maSanPham " +
                "ORDER BY soLuongBan DESC LIMIT ?";
        
        List<SanPham> list = new ArrayList<>();
        Cursor c = db.rawQuery(sql, new String[]{tu, den, limit});
        while (c.moveToNext()) {
            SanPham sp = new SanPham();
            sp.setMaSanPham(c.getInt(c.getColumnIndex("maSanPham")));
            sp.setTenSanPham(c.getString(c.getColumnIndex("tenSanPham")));
            sp.setSoLuong(c.getInt(c.getColumnIndex("soLuongBan")));
            list.add(sp);
        }
        c.close();
        return list;
    }

    @SuppressLint("Range")
    public List<KhachHang> getTopKhachHang(String tuNgay, String denNgay, String limit) {
        String tu = formatDate(tuNgay);
        String den = formatDate(denNgay);

        String sql = "SELECT KhachHang.maKhachHang, KhachHang.hoTen, SUM(HoaDon.tongTien) as tongChi " +
                "FROM KhachHang JOIN HoaDon ON KhachHang.maKhachHang = HoaDon.maKhachHang " +
                "WHERE (substr(ngayMua,7,4)||substr(ngayMua,4,2)||substr(ngayMua,1,2)) " +
                "BETWEEN ? AND ? " +
                "GROUP BY KhachHang.maKhachHang " +
                "ORDER BY tongChi DESC LIMIT ?";
        
        List<KhachHang> list = new ArrayList<>();
        Cursor c = db.rawQuery(sql, new String[]{tu, den, limit});
        while (c.moveToNext()) {
            KhachHang kh = new KhachHang();
            kh.setMaKhachHang(c.getInt(c.getColumnIndex("maKhachHang")));
            kh.setHoTen(c.getString(c.getColumnIndex("hoTen")));
            kh.setDiaChi(String.valueOf(c.getInt(c.getColumnIndex("tongChi")))); 
            list.add(kh);
        }
        c.close();
        return list;
    }

    public int getDoanhThu(String tuNgay, String denNgay) {
        String tu = formatDate(tuNgay);
        String den = formatDate(denNgay);

        String sql = "SELECT SUM(tongTien) FROM HoaDon WHERE " +
                "(substr(ngayMua,7,4)||substr(ngayMua,4,2)||substr(ngayMua,1,2)) " +
                "BETWEEN ? AND ?";
        
        Cursor c = db.rawQuery(sql, new String[]{tu, den});
        if (c.moveToNext()) {
            return c.getInt(0);
        }
        return 0;
    }

    private String formatDate(String date) {
        String[] parts = date.split("/");
        if (parts.length != 3) return date;
        return parts[2] + parts[1] + parts[0];
    }

    public List<SanPham> getTop10SanPham() {
        return getTopSanPham("01/01/2000", "01/01/2100", "10");
    }

    public List<KhachHang> getTop10KhachHang() {
        return getTopKhachHang("01/01/2000", "01/01/2100", "10");
    }
}
