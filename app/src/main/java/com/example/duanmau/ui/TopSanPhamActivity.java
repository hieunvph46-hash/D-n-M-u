package com.example.duanmau.ui;

import android.os.Bundle;
import android.widget.ListView;
import androidx.appcompat.app.AppCompatActivity;
import com.example.duanmau.R;
import com.example.duanmau.adapter.TopSanPhamAdapter;
import com.example.duanmau.dao.ThongKeDAO;
import com.example.duanmau.model.SanPham;
import java.util.ArrayList;
import java.util.List;

public class TopSanPhamActivity extends AppCompatActivity {
    ListView lv;
    ArrayList<SanPham> list;
    ThongKeDAO dao;
    TopSanPhamAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thong_ke_san_pham);

        lv = findViewById(R.id.lvSanPham);
        dao = new ThongKeDAO(this);
        
        list = (ArrayList<SanPham>) dao.getTop10SanPham();
        adapter = new TopSanPhamAdapter(this, list);
        lv.setAdapter(adapter);
        
        // Hide unused fields in the general statistics layout if any
        findViewById(R.id.edtNgayBatDau).setVisibility(android.view.View.GONE);
        findViewById(R.id.edtNgayKetThuc).setVisibility(android.view.View.GONE);
        findViewById(R.id.edtSoLuong).setVisibility(android.view.View.GONE);
        findViewById(R.id.btnTopSanPham).setVisibility(android.view.View.GONE);
        findViewById(R.id.tvTuNgay).setVisibility(android.view.View.GONE);
        findViewById(R.id.tvDenNgay).setVisibility(android.view.View.GONE);
        findViewById(R.id.tvSoLuong).setVisibility(android.view.View.GONE);
    }
}
