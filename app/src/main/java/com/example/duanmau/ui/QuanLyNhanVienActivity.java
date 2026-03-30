package com.example.duanmau.ui;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.Toast;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import com.example.duanmau.R;
import com.example.duanmau.adapter.NhanVienAdapter;
import com.example.duanmau.dao.NhanVienDAO;
import com.example.duanmau.model.NhanVien;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import java.util.ArrayList;

public class QuanLyNhanVienActivity extends AppCompatActivity {
    ListView lv;
    ArrayList<NhanVien> list;
    NhanVienDAO dao;
    NhanVienAdapter adapter;
    FloatingActionButton fab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quan_ly_nhan_vien);

        lv = findViewById(R.id.lvNhanVien);
        fab = findViewById(R.id.fabThemNhanVien);
        dao = new NhanVienDAO(this);

        capNhatLv();

        fab.setOnClickListener(v -> {
            Intent intent = new Intent(this, EditNhanVienActivity.class);
            intent.putExtra("type", 0);
            startActivityForResult(intent, 999);
        });
    }

    void capNhatLv() {
        list = (ArrayList<NhanVien>) dao.getAll();
        adapter = new NhanVienAdapter(this, this, list);
        lv.setAdapter(adapter);
    }

    public void xoa(String id) {
        if (id.equals("admin")) {
            Toast.makeText(this, "Không thể xóa admin", Toast.LENGTH_SHORT).show();
            return;
        }
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

    public void sua(NhanVien item) {
        Intent intent = new Intent(this, EditNhanVienActivity.class);
        intent.putExtra("type", 1);
        intent.putExtra("maNhanVien", item.getMaNhanVien());
        intent.putExtra("hoTen", item.getHoTen());
        intent.putExtra("vaiTro", item.getVaiTro());
        intent.putExtra("matKhau", item.getMatKhau());
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
