package com.example.duanmau.ui;

import android.app.AlertDialog;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.example.duanmau.R;
import com.example.duanmau.adapter.HoaDonAdapter;
import com.example.duanmau.dao.HoaDonDAO;
import com.example.duanmau.model.HoaDon;
import java.util.ArrayList;

public class QuanLyHoaDonActivity extends AppCompatActivity {
    ListView lv;
    ArrayList<HoaDon> list;
    HoaDonDAO dao;
    HoaDonAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quan_ly_hoa_don);

        lv = findViewById(R.id.lvHoaDon);
        dao = new HoaDonDAO(this);
        
        capNhatLv();
    }

    void capNhatLv() {
        list = (ArrayList<HoaDon>) dao.getAll();
        adapter = new HoaDonAdapter(this, this, list);
        lv.setAdapter(adapter);
    }

    public void xoa(String id) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Xóa?");
        builder.setMessage("Bạn có muốn xóa hóa đơn này?");
        builder.setPositiveButton("Có", (dialog, which) -> {
            if (dao.delete(id) > 0) {
                Toast.makeText(this, "Xóa thành công", Toast.LENGTH_SHORT).show();
                capNhatLv();
            }
        });
        builder.setNegativeButton("Không", null);
        builder.show();
    }
}
