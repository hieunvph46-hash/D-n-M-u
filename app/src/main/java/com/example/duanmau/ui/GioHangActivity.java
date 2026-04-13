package com.example.duanmau.ui;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.example.duanmau.Cart;
import com.example.duanmau.R;
import com.example.duanmau.adapter.GioHangAdapter;
import com.example.duanmau.dao.HoaDonDAO;
import com.example.duanmau.dao.KhachHangDAO;
import com.example.duanmau.model.HoaDon;
import com.example.duanmau.model.HoaDonChiTiet;
import com.example.duanmau.model.KhachHang;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

public class GioHangActivity extends AppCompatActivity {
    ListView lv;
    Spinner spKhachHang;
    TextView tvTongTien;
    Button btnThanhToan;
    GioHangAdapter adapter;
    KhachHangDAO khDao;
    HoaDonDAO hdDao;
    ArrayList<KhachHang> listKH;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gio_hang);

        lv = findViewById(R.id.lvSanPham);
        spKhachHang = findViewById(R.id.spKhachHang);
        tvTongTien = findViewById(R.id.tvTongTien);
        btnThanhToan = findViewById(R.id.btnThanhToan);

        khDao = new KhachHangDAO(this);
        hdDao = new HoaDonDAO(this);

        loadKhachHang();
        capNhatGioHang();

        btnThanhToan.setOnClickListener(v -> {
            if (Cart.cartList.isEmpty()) {
                Toast.makeText(this, "Giỏ hàng trống", Toast.LENGTH_SHORT).show();
                return;
            }
            if (spKhachHang.getSelectedItem() == null) {
                Toast.makeText(this, "Vui lòng chọn khách hàng", Toast.LENGTH_SHORT).show();
                return;
            }

            HoaDon hd = new HoaDon();
            SharedPreferences pref = getSharedPreferences("USER_FILE", Context.MODE_PRIVATE);
            hd.setMaNhanVien(pref.getString("USERNAME", "admin"));
            hd.setMaKhachHang(((KhachHang) spKhachHang.getSelectedItem()).getMaKhachHang());
            // Đổi định dạng ngày sang dd/MM/yyyy để khớp với ThongKeDAO
            hd.setNgayMua(new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault()).format(new Date()));
            hd.setTongTien(Cart.getTotal());

            long maHD = hdDao.insertHoaDon(hd);
            if (maHD > 0) {
                for (HoaDonChiTiet it : Cart.cartList) {
                    it.setMaHoaDon((int) maHD);
                    hdDao.insertHDCT(it);
                }
                Toast.makeText(this, "Thanh toán thành công", Toast.LENGTH_SHORT).show();
                Cart.cartList.clear();
                finish();
            } else {
                Toast.makeText(this, "Lỗi thanh toán", Toast.LENGTH_SHORT).show();
            }
        });
    }

    void loadKhachHang() {
        listKH = (ArrayList<KhachHang>) khDao.getAll();
        ArrayAdapter<KhachHang> adapterKH = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, listKH);
        spKhachHang.setAdapter(adapterKH);
    }

    public void capNhatGioHang() {
        adapter = new GioHangAdapter(this, this, Cart.cartList);
        lv.setAdapter(adapter);
        tvTongTien.setText("Tổng tiền: " + Cart.getTotal() + " VND");
    }

    public void tangSoLuong(int position) {
        Cart.cartList.get(position).setSoLuong(Cart.cartList.get(position).getSoLuong() + 1);
        capNhatGioHang();
    }

    public void giamSoLuong(int position) {
        int sl = Cart.cartList.get(position).getSoLuong();
        if (sl > 1) {
            Cart.cartList.get(position).setSoLuong(sl - 1);
            capNhatGioHang();
        }
    }

    public void xoaKhoiGio(int position) {
        Cart.cartList.remove(position);
        capNhatGioHang();
    }
}
