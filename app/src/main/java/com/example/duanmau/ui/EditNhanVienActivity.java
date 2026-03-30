package com.example.duanmau.ui;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.example.duanmau.R;
import com.example.duanmau.dao.NhanVienDAO;
import com.example.duanmau.model.NhanVien;

public class EditNhanVienActivity extends AppCompatActivity {
    EditText edtMa, edtTen, edtMatKhau;
    Spinner spVaiTro;
    Button btnLuu, btnHuy;
    NhanVienDAO dao;
    int type;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_nhan_vien);

        edtMa = findViewById(R.id.edtMaNhanVien);
        edtTen = findViewById(R.id.edtTenNhanVien);
        edtMatKhau = findViewById(R.id.edtMatKhau);
        spVaiTro = findViewById(R.id.spChucVu);
        btnLuu = findViewById(R.id.btnLuu);
        btnHuy = findViewById(R.id.btnHuy);

        // Hide unused fields for this simplified project
        findViewById(R.id.edtDiaChi).setVisibility(android.view.View.GONE);
        findViewById(R.id.edtLuong).setVisibility(android.view.View.GONE);

        dao = new NhanVienDAO(this);
        
        String[] vaiTros = {"Admin", "Nhân viên"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, vaiTros);
        spVaiTro.setAdapter(adapter);

        type = getIntent().getIntExtra("type", 0);
        if (type == 1) {
            String ma = getIntent().getStringExtra("maNhanVien");
            edtMa.setText(ma);
            edtMa.setEnabled(false);
            edtTen.setText(getIntent().getStringExtra("hoTen"));
            edtMatKhau.setText(getIntent().getStringExtra("matKhau"));
            String vt = getIntent().getStringExtra("vaiTro");
            if (vt != null && vt.equals("Admin")) spVaiTro.setSelection(0);
            else spVaiTro.setSelection(1);
        }

        btnHuy.setOnClickListener(v -> finish());
        btnLuu.setOnClickListener(v -> {
            String ma = edtMa.getText().toString();
            String ten = edtTen.getText().toString();
            String mk = edtMatKhau.getText().toString();
            String vt = spVaiTro.getSelectedItem().toString();

            if (ma.isEmpty() || ten.isEmpty() || mk.isEmpty()) {
                Toast.makeText(this, "Không bỏ trống", Toast.LENGTH_SHORT).show();
                return;
            }

            NhanVien nv = new NhanVien(ma, ten, mk, vt);
            if (type == 0) {
                if (dao.insert(nv) > 0) {
                    Toast.makeText(this, "Thêm thành công", Toast.LENGTH_SHORT).show();
                    setResult(RESULT_OK);
                    finish();
                } else {
                    Toast.makeText(this, "Mã nhân viên đã tồn tại", Toast.LENGTH_SHORT).show();
                }
            } else {
                if (dao.update(nv) > 0) {
                    Toast.makeText(this, "Sửa thành công", Toast.LENGTH_SHORT).show();
                    setResult(RESULT_OK);
                    finish();
                }
            }
        });
    }
}
