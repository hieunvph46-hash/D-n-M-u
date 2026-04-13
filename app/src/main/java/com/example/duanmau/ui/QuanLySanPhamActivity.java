package com.example.duanmau.ui;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import com.example.duanmau.Cart;
import com.example.duanmau.R;
import com.example.duanmau.adapter.SanPhamAdapter;
import com.example.duanmau.dao.SanPhamDAO;
import com.example.duanmau.model.HoaDonChiTiet;
import com.example.duanmau.model.SanPham;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import java.util.ArrayList;

public class QuanLySanPhamActivity extends AppCompatActivity {
    ListView lv;
    ArrayList<SanPham> list;
    SanPhamDAO dao;
    SanPhamAdapter adapter;
    FloatingActionButton fab;
    EditText edtSearch;
    TextView tvSoLuongGioHang;
    String userRole;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quan_ly_san_pham);

        lv = findViewById(R.id.lvSanPham);
        fab = findViewById(R.id.fabThemDanhMuc);
        edtSearch = findViewById(R.id.edtSearch);
        tvSoLuongGioHang = findViewById(R.id.tvSoLuong);
        dao = new SanPhamDAO(this);

        SharedPreferences pref = getSharedPreferences("USER_FILE", Context.MODE_PRIVATE);
        userRole = pref.getString("ROLE", "");

        // Phân quyền: Chỉ Admin mới được THÊM, SỬA, XÓA sản phẩm. 
        // Nhân viên chỉ được xem và mua hàng.
        if (userRole.equalsIgnoreCase("Admin")) {
            fab.setVisibility(View.VISIBLE);
        } else {
            fab.setVisibility(View.GONE);
        }

        capNhatLv();
        updateCartBadge();

        fab.setOnClickListener(v -> {
            Intent intent = new Intent(this, EditSanPhamActivity.class);
            intent.putExtra("type", 0);
            startActivityForResult(intent, 999);
        });

        edtSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                list = (ArrayList<SanPham>) dao.search(s.toString());
                adapter = new SanPhamAdapter(QuanLySanPhamActivity.this, QuanLySanPhamActivity.this, list);
                lv.setAdapter(adapter);
            }
            @Override
            public void afterTextChanged(Editable s) {}
        });

        findViewById(R.id.itemCart).setOnClickListener(v -> {
            if (userRole.equalsIgnoreCase("Admin")) {
                Toast.makeText(this, "Admin không được sử dụng giỏ hàng", Toast.LENGTH_SHORT).show();
            } else {
                startActivity(new Intent(this, GioHangActivity.class));
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        updateCartBadge();
    }

    void capNhatLv() {
        list = (ArrayList<SanPham>) dao.getAll();
        adapter = new SanPhamAdapter(this, this, list);
        lv.setAdapter(adapter);
    }

    public void updateCartBadge() {
        int count = 0;
        for (HoaDonChiTiet it : Cart.cartList) {
            count += it.getSoLuong();
        }
        tvSoLuongGioHang.setText(String.valueOf(count));
    }

    public void xoa(String id) {
        if (!userRole.equalsIgnoreCase("Admin")) {
            Toast.makeText(this, "Bạn không có quyền xóa", Toast.LENGTH_SHORT).show();
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

    public void sua(SanPham item) {
        if (!userRole.equalsIgnoreCase("Admin")) {
            Toast.makeText(this, "Bạn không có quyền sửa", Toast.LENGTH_SHORT).show();
            return;
        }
        Intent intent = new Intent(this, EditSanPhamActivity.class);
        intent.putExtra("type", 1);
        intent.putExtra("maSanPham", item.getMaSanPham());
        intent.putExtra("tenSanPham", item.getTenSanPham());
        intent.putExtra("giaBan", item.getGiaBan());
        intent.putExtra("soLuong", item.getSoLuong());
        intent.putExtra("maDanhMuc", item.getMaDanhMuc());
        startActivityForResult(intent, 999);
    }

    public void themVaoGioHang(SanPham item) {
        if (userRole.equalsIgnoreCase("Admin")) {
            Toast.makeText(this, "Admin không được mua hàng", Toast.LENGTH_SHORT).show();
            return;
        }
        HoaDonChiTiet hdct = new HoaDonChiTiet();
        hdct.setMaSanPham(item.getMaSanPham());
        hdct.setTenSanPham(item.getTenSanPham());
        hdct.setDonGia(item.getGiaBan());
        hdct.setSoLuong(1);
        Cart.add(hdct);
        updateCartBadge();
        Toast.makeText(this, "Đã thêm " + item.getTenSanPham() + " vào giỏ", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 999 && resultCode == RESULT_OK) {
            capNhatLv();
        }
    }
}
