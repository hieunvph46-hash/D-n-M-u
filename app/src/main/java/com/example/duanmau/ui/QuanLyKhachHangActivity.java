package com.example.duanmau.ui;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.Toast;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import com.example.duanmau.R;
import com.example.duanmau.adapter.KhachHangAdapter;
import com.example.duanmau.dao.KhachHangDAO;
import com.example.duanmau.model.KhachHang;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import java.util.ArrayList;

public class QuanLyKhachHangActivity extends AppCompatActivity {
    ListView lv;
    ArrayList<KhachHang> list;
    KhachHangDAO dao;
    KhachHangAdapter adapter;
    FloatingActionButton fab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quan_ly_khach_hang);

        lv = findViewById(R.id.lvKhachHang);
        fab = findViewById(R.id.fabThemKhachHang);
        dao = new KhachHangDAO(this);

        capNhatLv();

        fab.setOnClickListener(v -> {
            Intent intent = new Intent(this, EditKhachHangActivity.class);
            intent.putExtra("type", 0);
            startActivityForResult(intent, 999);
        });
    }

    void capNhatLv() {
        list = (ArrayList<KhachHang>) dao.getAll();
        adapter = new KhachHangAdapter(this, this, list);
        lv.setAdapter(adapter);
    }

    public void xoa(String id) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Xóa?");
        builder.setMessage("Bạn có muốn xóa không?");
        builder.setPositiveButton("Có", (dialog, which) -> {
            if (dao.delete(id) > 0) {
                Toast.makeText(this, "Xóa thành công", Toast.LENGTH_SHORT).show();
                capNhatLv();
            }
        });
        builder.setNegativeButton("Không", null);
        builder.show();
    }

    public void sua(KhachHang item) {
        Intent intent = new Intent(this, EditKhachHangActivity.class);
        intent.putExtra("type", 1);
        intent.putExtra("maKhachHang", item.getMaKhachHang());
        intent.putExtra("hoTen", item.getHoTen());
        intent.putExtra("soDienThoai", item.getSoDienThoai());
        intent.putExtra("diaChi", item.getDiaChi());
        startActivityForResult(intent, 999);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 999 && resultCode == RESULT_OK) {
            capNhatLv();
        }
    }
}
