package com.example.duanmau.ui;

import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import androidx.appcompat.app.AppCompatActivity;
import com.example.duanmau.R;
import com.example.duanmau.adapter.TopKhachHangAdapter;
import com.example.duanmau.dao.ThongKeDAO;
import com.example.duanmau.model.KhachHang;
import java.util.ArrayList;

public class TopKhachHangActivity extends AppCompatActivity {
    ListView lv;
    ArrayList<KhachHang> list;
    ThongKeDAO dao;
    TopKhachHangAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thong_ke_khach_hang);

        lv = findViewById(R.id.listViewKhachHang);
        dao = new ThongKeDAO(this);

        list = (ArrayList<KhachHang>) dao.getTop10KhachHang();
        adapter = new TopKhachHangAdapter(this, list);
        lv.setAdapter(adapter);

        // Ẩn các trường không dùng trong layout chung nếu cần
        findViewById(R.id.edtNgayBatDau).setVisibility(View.GONE);
        findViewById(R.id.edtNgayKetThuc).setVisibility(View.GONE);
        findViewById(R.id.edtSoLuong).setVisibility(View.GONE);
        findViewById(R.id.btnTopKhachHang).setVisibility(View.GONE);
        findViewById(R.id.tvTuNgay).setVisibility(View.GONE);
        findViewById(R.id.tvDenNgay).setVisibility(View.GONE);
        findViewById(R.id.tvSoLuong).setVisibility(View.GONE);
    }
}
