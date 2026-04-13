package com.example.duanmau.ui;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.example.duanmau.R;
import com.example.duanmau.adapter.TopSanPhamAdapter;
import com.example.duanmau.dao.ThongKeDAO;
import com.example.duanmau.model.SanPham;
import java.util.ArrayList;
import java.util.Calendar;

public class TopSanPhamActivity extends AppCompatActivity {
    EditText edtTuNgay, edtDenNgay, edtSoLuong;
    Button btnThongKe;
    ListView lv;
    ArrayList<SanPham> list;
    ThongKeDAO dao;
    TopSanPhamAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thong_ke_san_pham);

        edtTuNgay = findViewById(R.id.edtNgayBatDau);
        edtDenNgay = findViewById(R.id.edtNgayKetThuc);
        edtSoLuong = findViewById(R.id.edtSoLuong);
        btnThongKe = findViewById(R.id.btnTopSanPham);
        lv = findViewById(R.id.lvSanPham);

        dao = new ThongKeDAO(this);

        // Date Picker for Tu Ngay
        edtTuNgay.setOnClickListener(v -> {
            Calendar c = Calendar.getInstance();
            new DatePickerDialog(this, (view, year, month, dayOfMonth) -> {
                String date = String.format("%02d/%02d/%d", dayOfMonth, (month + 1), year);
                edtTuNgay.setText(date);
            }, c.get(Calendar.YEAR), c.get(Calendar.MONTH), c.get(Calendar.DAY_OF_MONTH)).show();
        });

        // Date Picker for Den Ngay
        edtDenNgay.setOnClickListener(v -> {
            Calendar c = Calendar.getInstance();
            new DatePickerDialog(this, (view, year, month, dayOfMonth) -> {
                String date = String.format("%02d/%02d/%d", dayOfMonth, (month + 1), year);
                edtDenNgay.setText(date);
            }, c.get(Calendar.YEAR), c.get(Calendar.MONTH), c.get(Calendar.DAY_OF_MONTH)).show();
        });

        btnThongKe.setOnClickListener(v -> {
            String tuNgay = edtTuNgay.getText().toString();
            String denNgay = edtDenNgay.getText().toString();
            String limit = edtSoLuong.getText().toString();

            if (tuNgay.isEmpty() || denNgay.isEmpty() || limit.isEmpty()) {
                Toast.makeText(this, "Vui lòng nhập đầy đủ thông tin", Toast.LENGTH_SHORT).show();
                return;
            }

            list = (ArrayList<SanPham>) dao.getTopSanPham(tuNgay, denNgay, limit);
            if (list.isEmpty()) {
                Toast.makeText(this, "Không có dữ liệu trong khoảng thời gian này", Toast.LENGTH_SHORT).show();
            }
            adapter = new TopSanPhamAdapter(this, list);
            lv.setAdapter(adapter);
        });
    }
}
