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
    public List<SanPham> getTop10SanPham() {
        String sql = "SELECT SanPham.maSanPham, SanPham.tenSanPham, SUM(HoaDonChiTiet.soLuong) as soLuongBan " +
                "FROM SanPham JOIN HoaDonChiTiet ON SanPham.maSanPham = HoaDonChiTiet.maSanPham " +
                "GROUP BY SanPham.maSanPham " +
                "ORDER BY soLuongBan DESC LIMIT 10";
        List<SanPham> list = new ArrayList<>();
        Cursor c = db.rawQuery(sql, null);
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
    public List<KhachHang> getTop10KhachHang() {
        String sql = "SELECT KhachHang.maKhachHang, KhachHang.hoTen, SUM(HoaDon.tongTien) as tongChi " +
                "FROM KhachHang JOIN HoaDon ON KhachHang.maKhachHang = HoaDon.maKhachHang " +
                "GROUP BY KhachHang.maKhachHang " +
                "ORDER BY tongChi DESC LIMIT 10";
        List<KhachHang> list = new ArrayList<>();
        Cursor c = db.rawQuery(sql, null);
        while (c.moveToNext()) {
            KhachHang kh = new KhachHang();
            kh.setMaKhachHang(c.getInt(c.getColumnIndex("maKhachHang")));
            kh.setHoTen(c.getString(c.getColumnIndex("hoTen")));
            kh.setDiaChi(String.valueOf(c.getInt(c.getColumnIndex("tongChi")))); // Using diaChi field to store total spend for display
            list.add(kh);
        }
        c.close();
        return list;
    }

    public int getDoanhThu(String tuNgay, String denNgay) {
        String sql = "SELECT SUM(tongTien) FROM HoaDon WHERE ngayMua BETWEEN ? AND ?";
        Cursor c = db.rawQuery(sql, new String[]{tuNgay, denNgay});
        if (c.moveToNext()) {
            return c.getInt(0);
        }
        return 0;
    }
}
