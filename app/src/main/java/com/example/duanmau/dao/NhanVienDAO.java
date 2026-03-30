package com.example.duanmau.dao;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import com.example.duanmau.database.DbHelper;
import com.example.duanmau.model.NhanVien;
import java.util.ArrayList;
import java.util.List;

public class NhanVienDAO {
    private final SQLiteDatabase db;

    public NhanVienDAO(Context context) {
        DbHelper dbHelper = new DbHelper(context);
        db = dbHelper.getWritableDatabase();
    }

    public long insert(NhanVien obj) {
        ContentValues values = new ContentValues();
        values.put("maNhanVien", obj.getMaNhanVien());
        values.put("hoTen", obj.getHoTen());
        values.put("matKhau", obj.getMatKhau());
        values.put("vaiTro", obj.getVaiTro());
        return db.insert("NhanVien", null, values);
    }

    public int update(NhanVien obj) {
        ContentValues values = new ContentValues();
        values.put("hoTen", obj.getHoTen());
        values.put("matKhau", obj.getMatKhau());
        values.put("vaiTro", obj.getVaiTro());
        return db.update("NhanVien", values, "maNhanVien=?", new String[]{obj.getMaNhanVien()});
    }

    public int delete(String id) {
        return db.delete("NhanVien", "maNhanVien=?", new String[]{id});
    }

    @SuppressLint("Range")
    public List<NhanVien> getData(String sql, String... selectionArgs) {
        List<NhanVien> list = new ArrayList<>();
        Cursor c = db.rawQuery(sql, selectionArgs);
        while (c.moveToNext()) {
            NhanVien obj = new NhanVien();
            obj.setMaNhanVien(c.getString(c.getColumnIndex("maNhanVien")));
            obj.setHoTen(c.getString(c.getColumnIndex("hoTen")));
            obj.setMatKhau(c.getString(c.getColumnIndex("matKhau")));
            obj.setVaiTro(c.getString(c.getColumnIndex("vaiTro")));
            list.add(obj);
        }
        c.close();
        return list;
    }

    public List<NhanVien> getAll() {
        String sql = "SELECT * FROM NhanVien";
        return getData(sql);
    }

    public NhanVien getID(String id) {
        String sql = "SELECT * FROM NhanVien WHERE maNhanVien=?";
        List<NhanVien> list = getData(sql, id);
        return list.size() > 0 ? list.get(0) : null;
    }

    public int checkLogin(String id, String password) {
        String sql = "SELECT * FROM NhanVien WHERE maNhanVien=? AND matKhau=?";
        List<NhanVien> list = getData(sql, id, password);
        if (list.size() == 0) return -1;
        return 1;
    }
}
