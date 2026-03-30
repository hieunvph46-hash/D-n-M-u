package com.example.duanmau.ui;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.Toast;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import com.example.duanmau.R;
import com.example.duanmau.adapter.DanhMucAdapter;
import com.example.duanmau.dao.DanhMucDAO;
import com.example.duanmau.model.DanhMuc;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import java.util.ArrayList;
import java.util.List;

public class QuanLyDanhMucActivity extends AppCompatActivity {
    ListView lv;
    ArrayList<DanhMuc> list;
    DanhMucDAO dao;
    DanhMucAdapter adapter;
    FloatingActionButton fab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quan_ly_danh_muc);

        lv = findViewById(R.id.lvSanPham); // From layout
        fab = findViewById(R.id.fabThemDanhMuc);
        dao = new DanhMucDAO(this);
        
        capNhatLv();

        fab.setOnClickListener(v -> {
            Intent intent = new Intent(QuanLyDanhMucActivity.this, EditDanhMucActivity.class);
            intent.putExtra("type", 0); // 0 for add
            startActivityForResult(intent, 999);
        });
    }

    void capNhatLv() {
        list = (ArrayList<DanhMuc>) dao.getAll();
        adapter = new DanhMucAdapter(this, this, list);
        lv.setAdapter(adapter);
    }

    public void xoa(final String id) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Xóa?");
        builder.setMessage("Bạn có muốn xóa không?");
        builder.setPositiveButton("Có", (dialog, which) -> {
            if (dao.delete(id) > 0) {
                Toast.makeText(this, "Xóa thành công", Toast.LENGTH_SHORT).show();
                capNhatLv();
            } else {
                Toast.makeText(this, "Xóa thất bại", Toast.LENGTH_SHORT).show();
            }
        });
        builder.setNegativeButton("Không", (dialog, which) -> dialog.dismiss());
        builder.show();
    }

    public void sua(DanhMuc item) {
        Intent intent = new Intent(QuanLyDanhMucActivity.this, EditDanhMucActivity.class);
        intent.putExtra("type", 1); // 1 for edit
        intent.putExtra("maDanhMuc", item.getMaDanhMuc());
        intent.putExtra("tenDanhMuc", item.getTenDanhMuc());
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
