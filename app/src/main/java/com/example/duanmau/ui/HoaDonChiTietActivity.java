package com.example.duanmau.ui;

import android.os.Bundle;
import android.widget.ListView;
import androidx.appcompat.app.AppCompatActivity;
import com.example.duanmau.R;
import com.example.duanmau.adapter.HoaDonChiTietAdapter;
import com.example.duanmau.dao.HoaDonDAO;
import com.example.duanmau.model.HoaDonChiTiet;
import java.util.ArrayList;

public class HoaDonChiTietActivity extends AppCompatActivity {
    ListView lv;
    ArrayList<HoaDonChiTiet> list;
    HoaDonDAO dao;
    HoaDonChiTietAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hoa_don_chi_tiet);

        lv = findViewById(R.id.lvSanPham);
        dao = new HoaDonDAO(this);

        int maHoaDon = getIntent().getIntExtra("maHoaDon", -1);
        if (maHoaDon != -1) {
            list = (ArrayList<HoaDonChiTiet>) dao.getChiTietHoaDon(maHoaDon);
            adapter = new HoaDonChiTietAdapter(this, list);
            lv.setAdapter(adapter);
        }
    }
}
