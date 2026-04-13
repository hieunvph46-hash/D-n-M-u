package com.example.duanmau.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.example.duanmau.R;
import com.example.duanmau.model.HoaDon;
import com.example.duanmau.ui.HoaDonChiTietActivity;
import com.example.duanmau.ui.QuanLyHoaDonActivity;
import java.util.List;

public class HoaDonAdapter extends ArrayAdapter<HoaDon> {
    private Context context;
    private QuanLyHoaDonActivity activity;
    private List<HoaDon> list;

    public HoaDonAdapter(@NonNull Context context, QuanLyHoaDonActivity activity, List<HoaDon> list) {
        super(context, 0, list);
        this.context = context;
        this.activity = activity;
        this.list = list;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_hoa_don, parent, false);
        }

        HoaDon item = list.get(position);
        if (item != null) {
            TextView tvMa = convertView.findViewById(R.id.tvMaHoaDon);
            TextView tvNV = convertView.findViewById(R.id.tvTenNhanVien);
            TextView tvKH = convertView.findViewById(R.id.tvTenKhachHang);
            TextView tvNgay = convertView.findViewById(R.id.tvNgayLap);
            TextView tvTien = convertView.findViewById(R.id.tvTongTien);
            ImageView imgXoa = convertView.findViewById(R.id.imgXoaHoaDon);

            tvMa.setText(String.valueOf(item.getMaHoaDon()));
            tvNV.setText(item.getMaNhanVien());
            tvKH.setText("Mã KH: " + item.getMaKhachHang());
            tvNgay.setText(item.getNgayMua());
            tvTien.setText(item.getTongTien() + " Đ");

            imgXoa.setOnClickListener(v -> activity.xoa(String.valueOf(item.getMaHoaDon())));
            
            convertView.setOnClickListener(v -> {
                Intent intent = new Intent(context, HoaDonChiTietActivity.class);
                intent.putExtra("maHoaDon", item.getMaHoaDon());
                context.startActivity(intent);
            });
        }

        return convertView;
    }
}
