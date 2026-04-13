package com.example.duanmau.ui;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.example.duanmau.R;
import com.example.duanmau.dao.KhachHangDAO;
import com.example.duanmau.model.KhachHang;

public class EditKhachHangActivity extends AppCompatActivity {
    EditText edtMa, edtTen, edtDiaChi, edtSdt;
    Button btnLuu, btnHuy;
    KhachHangDAO dao;
    int type, maKH;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_khach_hang);

        edtMa = findViewById(R.id.edtMaKhachHang);
        edtTen = findViewById(R.id.edtTenKhachHang);
        edtDiaChi = findViewById(R.id.edtDiaChi);
        edtSdt = findViewById(R.id.edtSoDienThoai);
        btnLuu = findViewById(R.id.btnLuu);
        btnHuy = findViewById(R.id.btnHuy);

        dao = new KhachHangDAO(this);
        type = getIntent().getIntExtra("type", 0);

        if (type == 1) {
            maKH = getIntent().getIntExtra("maKhachHang", 0);
            edtMa.setText(String.valueOf(maKH));
            edtMa.setEnabled(false);
            edtTen.setText(getIntent().getStringExtra("hoTen"));
            edtDiaChi.setText(getIntent().getStringExtra("diaChi"));
            edtSdt.setText(getIntent().getStringExtra("soDienThoai"));
        } else {
            findViewById(R.id.layoutMaKhachHang).setVisibility(View.GONE);
        }

        btnHuy.setOnClickListener(v -> finish());
        btnLuu.setOnClickListener(v -> {
            String ten = edtTen.getText().toString();
            String diaChi = edtDiaChi.getText().toString();
            String sdt = edtSdt.getText().toString();

            if (ten.isEmpty() || sdt.isEmpty()) {
                Toast.makeText(this, "Tên và SĐT không được để trống", Toast.LENGTH_SHORT).show();
                return;
            }

            KhachHang kh = new KhachHang();
            kh.setHoTen(ten);
            kh.setDiaChi(diaChi);
            kh.setSoDienThoai(sdt);

            if (type == 0) {
                if (dao.insert(kh) > 0) {
                    Toast.makeText(this, "Thêm thành công", Toast.LENGTH_SHORT).show();
                    setResult(RESULT_OK);
                    finish();
                }
            } else {
                kh.setMaKhachHang(maKH);
                if (dao.update(kh) > 0) {
                    Toast.makeText(this, "Sửa thành công", Toast.LENGTH_SHORT).show();
                    setResult(RESULT_OK);
                    finish();
                }
            }
        });
    }
}
