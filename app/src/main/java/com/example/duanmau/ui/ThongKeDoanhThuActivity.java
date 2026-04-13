package com.example.duanmau.ui;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import com.example.duanmau.R;
import com.example.duanmau.dao.ThongKeDAO;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Locale;

public class ThongKeDoanhThuActivity extends AppCompatActivity {
    Button btnThongKe;
    EditText edtTuNgay, edtDenNgay;
    TextView tvDoanhThu;
    // Định dạng dd/MM/yyyy để khớp với dữ liệu trong DbHelper
    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
    int mYear, mMonth, mDay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thong_ke_doanh_thu);

        edtTuNgay = findViewById(R.id.edtNgayBatDau);
        edtDenNgay = findViewById(R.id.edtNgayKetThuc);
        tvDoanhThu = findViewById(R.id.tvDoanhThu);
        btnThongKe = findViewById(R.id.btnThongKeDoanhThu);

        edtTuNgay.setOnClickListener(v -> {
            Calendar c = Calendar.getInstance();
            mYear = c.get(Calendar.YEAR);
            mMonth = c.get(Calendar.MONTH);
            mDay = c.get(Calendar.DAY_OF_MONTH);
            DatePickerDialog d = new DatePickerDialog(this, (view, year, month, dayOfMonth) -> {
                GregorianCalendar c1 = new GregorianCalendar(year, month, dayOfMonth);
                edtTuNgay.setText(sdf.format(c1.getTime()));
            }, mYear, mMonth, mDay);
            d.show();
        });

        edtDenNgay.setOnClickListener(v -> {
            Calendar c = Calendar.getInstance();
            mYear = c.get(Calendar.YEAR);
            mMonth = c.get(Calendar.MONTH);
            mDay = c.get(Calendar.DAY_OF_MONTH);
            DatePickerDialog d = new DatePickerDialog(this, (view, year, month, dayOfMonth) -> {
                GregorianCalendar c1 = new GregorianCalendar(year, month, dayOfMonth);
                edtDenNgay.setText(sdf.format(c1.getTime()));
            }, mYear, mMonth, mDay);
            d.show();
        });

        btnThongKe.setOnClickListener(v -> {
            String tuNgay = edtTuNgay.getText().toString();
            String denNgay = edtDenNgay.getText().toString();
            
            if (tuNgay.isEmpty() || denNgay.isEmpty()) {
                tvDoanhThu.setText("Vui lòng chọn đầy đủ ngày");
                return;
            }

            ThongKeDAO dao = new ThongKeDAO(this);
            // Chuyển đổi định dạng ngày để SQLite có thể so sánh BETWEEN (YYYY/MM/DD)
            // Vì dữ liệu đang lưu dd/MM/yyyy nên ta cần xử lý logic so sánh ngày trong DAO hoặc đồng bộ format
            tvDoanhThu.setText("Doanh thu: " + dao.getDoanhThu(tuNgay, denNgay) + " VND");
        });
    }
}
