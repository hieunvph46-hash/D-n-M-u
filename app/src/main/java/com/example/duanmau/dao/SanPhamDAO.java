package com.example.duanmau.dao;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import com.example.duanmau.database.DbHelper;
import com.example.duanmau.model.SanPham;
import java.util.ArrayList;
import java.util.List;

public class SanPhamDAO {
    private final SQLiteDatabase db;

    public SanPhamDAO(Context context) {
        DbHelper dbHelper = new DbHelper(context);
        db = dbHelper.getWritableDatabase();
    }

    public long insert(SanPham obj) {
        ContentValues values = new ContentValues();
        values.put("tenSanPham", obj.getTenSanPham());
        values.put("giaBan", obj.getGiaBan());
        values.put("soLuong", obj.getSoLuong());
        values.put("maDanhMuc", obj.getMaDanhMuc());
        return db.insert("SanPham", null, values);
    }

    public int update(SanPham obj) {
        ContentValues values = new ContentValues();
        values.put("tenSanPham", obj.getTenSanPham());
        values.put("giaBan", obj.getGiaBan());
        values.put("soLuong", obj.getSoLuong());
        values.put("maDanhMuc", obj.getMaDanhMuc());
        return db.update("SanPham", values, "maSanPham=?", new String[]{String.valueOf(obj.getMaSanPham())});
    }

    public int delete(String id) {
        return db.delete("SanPham", "maSanPham=?", new String[]{id});
    }

    @SuppressLint("Range")
    public List<SanPham> getData(String sql, String... selectionArgs) {
        List<SanPham> list = new ArrayList<>();
        Cursor c = db.rawQuery(sql, selectionArgs);
        while (c.moveToNext()) {
            SanPham obj = new SanPham();
            obj.setMaSanPham(c.getInt(c.getColumnIndex("maSanPham")));
            obj.setTenSanPham(c.getString(c.getColumnIndex("tenSanPham")));
            obj.setGiaBan(c.getInt(c.getColumnIndex("giaBan")));
            obj.setSoLuong(c.getInt(c.getColumnIndex("soLuong")));
            obj.setMaDanhMuc(c.getInt(c.getColumnIndex("maDanhMuc")));
            list.add(obj);
        }
        c.close();
        return list;
    }

    public List<SanPham> getAll() {
        String sql = "SELECT * FROM SanPham";
        return getData(sql);
    }

    public SanPham getID(String id) {
        String sql = "SELECT * FROM SanPham WHERE maSanPham=?";
        List<SanPham> list = getData(sql, id);
        return list.size() > 0 ? list.get(0) : null;
    }
    
    public List<SanPham> search(String name) {
        String sql = "SELECT * FROM SanPham WHERE tenSanPham LIKE ?";
        return getData(sql, "%" + name + "%");
    }
}
