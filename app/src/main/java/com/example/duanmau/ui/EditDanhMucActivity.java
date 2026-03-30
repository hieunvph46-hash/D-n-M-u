package com.example.duanmau.ui;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.example.duanmau.R;
import com.example.duanmau.dao.DanhMucDAO;
import com.example.duanmau.model.DanhMuc;

public class EditDanhMucActivity extends AppCompatActivity {
    EditText edtMa, edtTen;
    Button btnLuu, btnHuy;
    DanhMucDAO dao;
    int type; // 0: add, 1: edit

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_danh_muc);

        edtMa = findViewById(R.id.edtMaDanhMuc);
        edtTen = findViewById(R.id.edtTenSanPham); // Note: ID in layout is edtTenSanPham
        btnLuu = findViewById(R.id.btnLuu);
        btnHuy = findViewById(R.id.btnHuy);
        LinearLayout layoutMa = findViewById(R.id.layoutMaDanhMuc);

        dao = new DanhMucDAO(this);
        type = getIntent().getIntExtra("type", 0);

        if (type == 1) {
            edtMa.setText(String.valueOf(getIntent().getIntExtra("maDanhMuc", 0)));
            edtTen.setText(getIntent().getStringExtra("tenDanhMuc"));
            edtMa.setEnabled(false);
        } else {
            layoutMa.setVisibility(View.GONE);
        }

        btnHuy.setOnClickListener(v -> finish());

        btnLuu.setOnClickListener(v -> {
            String ten = edtTen.getText().toString();
            if (ten.isEmpty()) {
                Toast.makeText(this, "Không bỏ trống", Toast.LENGTH_SHORT).show();
                return;
            }

            DanhMuc dm = new DanhMuc();
            dm.setTenDanhMuc(ten);

            if (type == 0) {
                if (dao.insert(dm) > 0) {
                    Toast.makeText(this, "Thêm thành công", Toast.LENGTH_SHORT).show();
                    setResult(RESULT_OK);
                    finish();
                }
            } else {
                dm.setMaDanhMuc(Integer.parseInt(edtMa.getText().toString()));
                if (dao.update(dm) > 0) {
                    Toast.makeText(this, "Sửa thành công", Toast.LENGTH_SHORT).show();
                    setResult(RESULT_OK);
                    finish();
                }
            }
        });
    }
}
