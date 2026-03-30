package com.example.duanmau.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import com.example.duanmau.R;

public class HomeActivity extends AppCompatActivity {
    LinearLayout lnDoanhThu, lnTopSanPham, lnTopKhachHang;
    LinearLayout lnSanPham, lnKhachHang, lnHoaDon, lnDanhMuc, lnNhanVien;
    LinearLayout lnDoiMatKhau, lnDangXuat;
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("JP Mart - Dashboard");

        // Thống kê
        lnDoanhThu = findViewById(R.id.lnDoanhThu);
        lnTopSanPham = findViewById(R.id.lnTopSanPham);
        lnTopKhachHang = findViewById(R.id.lnTopKhachHang);

        // Quản lý
        lnSanPham = findViewById(R.id.lnSanPham);
        lnKhachHang = findViewById(R.id.lnKhachHang);
        lnHoaDon = findViewById(R.id.lnHoaDon);
        lnDanhMuc = findViewById(R.id.lnDanhMuc);
        lnNhanVien = findViewById(R.id.lnNhanVien);

        // User
        lnDoiMatKhau = findViewById(R.id.lnDoiMatKhau);
        lnDangXuat = findViewById(R.id.lnDangXuat);

        // Click listeners
        lnDanhMuc.setOnClickListener(v -> startActivity(new Intent(HomeActivity.this, QuanLyDanhMucActivity.class)));
        lnSanPham.setOnClickListener(v -> startActivity(new Intent(HomeActivity.this, QuanLySanPhamActivity.class)));
        lnNhanVien.setOnClickListener(v -> startActivity(new Intent(HomeActivity.this, QuanLyNhanVienActivity.class)));
        lnKhachHang.setOnClickListener(v -> startActivity(new Intent(HomeActivity.this, QuanLyKhachHangActivity.class)));
        lnHoaDon.setOnClickListener(v -> startActivity(new Intent(HomeActivity.this, QuanLyHoaDonActivity.class)));
        
        lnDoanhThu.setOnClickListener(v -> startActivity(new Intent(HomeActivity.this, ThongKeDoanhThuActivity.class)));
        lnTopSanPham.setOnClickListener(v -> startActivity(new Intent(HomeActivity.this, TopSanPhamActivity.class)));
        lnTopKhachHang.setOnClickListener(v -> startActivity(new Intent(HomeActivity.this, TopKhachHangActivity.class)));

        lnDoiMatKhau.setOnClickListener(v -> startActivity(new Intent(HomeActivity.this, DoiMatKhauActivity.class)));
        lnDangXuat.setOnClickListener(v -> {
            startActivity(new Intent(HomeActivity.this, LoginActivity.class));
            finish();
        });

        // Phân quyền: Nếu không phải tài khoản admin (H) thì ẩn quản lý nhân viên
        String user = getIntent().getStringExtra("user");
        if (user != null && !user.equals("h")) {
            lnNhanVien.setVisibility(View.GONE);
        }
    }
}

