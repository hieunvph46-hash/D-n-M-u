package com.example.duanmau.dao;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import com.example.duanmau.database.DbHelper;
import com.example.duanmau.model.KhachHang;
import java.util.ArrayList;
import java.util.List;

public class KhachHangDAO {
    private final SQLiteDatabase db;

    public KhachHangDAO(Context context) {
        DbHelper dbHelper = new DbHelper(context);
        db = dbHelper.getWritableDatabase();
    }

    public long insert(KhachHang obj) {
        ContentValues values = new ContentValues();
        values.put("hoTen", obj.getHoTen());
        values.put("soDienThoai", obj.getSoDienThoai());
        values.put("diaChi", obj.getDiaChi());
        return db.insert("KhachHang", null, values);
    }

    public int update(KhachHang obj) {
        ContentValues values = new ContentValues();
        values.put("hoTen", obj.getHoTen());
        values.put("soDienThoai", obj.getSoDienThoai());
        values.put("diaChi", obj.getDiaChi());
        return db.update("KhachHang", values, "maKhachHang=?", new String[]{String.valueOf(obj.getMaKhachHang())});
    }

    public int delete(String id) {
        return db.delete("KhachHang", "maKhachHang=?", new String[]{id});
    }

    @SuppressLint("Range")
    public List<KhachHang> getData(String sql, String... selectionArgs) {
        List<KhachHang> list = new ArrayList<>();
        Cursor c = db.rawQuery(sql, selectionArgs);
        while (c.moveToNext()) {
            KhachHang obj = new KhachHang();
            obj.setMaKhachHang(c.getInt(c.getColumnIndex("maKhachHang")));
            obj.setHoTen(c.getString(c.getColumnIndex("hoTen")));
            obj.setSoDienThoai(c.getString(c.getColumnIndex("soDienThoai")));
            obj.setDiaChi(c.getString(c.getColumnIndex("diaChi")));
            list.add(obj);
        }
        c.close();
        return list;
    }

    public List<KhachHang> getAll() {
        String sql = "SELECT * FROM KhachHang";
        return getData(sql);
    }

    public KhachHang getID(String id) {
        String sql = "SELECT * FROM KhachHang WHERE maKhachHang=?";
        List<KhachHang> list = getData(sql, id);
        return list.size() > 0 ? list.get(0) : null;
    }

    public List<KhachHang> search(String phone) {
        String sql = "SELECT * FROM KhachHang WHERE soDienThoai LIKE ?";
        return getData(sql, "%" + phone + "%");
    }
}
