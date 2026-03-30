package com.example.duanmau.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.example.duanmau.R;
import com.example.duanmau.model.HoaDonChiTiet;
import com.example.duanmau.ui.GioHangActivity;
import java.util.List;

public class GioHangAdapter extends ArrayAdapter<HoaDonChiTiet> {
    private Context context;
    private GioHangActivity activity;
    private List<HoaDonChiTiet> list;

    public GioHangAdapter(@NonNull Context context, GioHangActivity activity, List<HoaDonChiTiet> list) {
        super(context, 0, list);
        this.context = context;
        this.activity = activity;
        this.list = list;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_gio_hang, parent, false);
        }

        HoaDonChiTiet item = list.get(position);
        if (item != null) {
            TextView tvTen = convertView.findViewById(R.id.tvTenSanPham);
            TextView tvGia = convertView.findViewById(R.id.tvGiaSanPham);
            TextView tvSoLuong = convertView.findViewById(R.id.tvSoLuong);
            ImageButton btnGiam = convertView.findViewById(R.id.btnGiam);
            ImageButton btnTang = convertView.findViewById(R.id.btnTang);
            ImageView imgXoa = convertView.findViewById(R.id.imgXoaDanhMuc);

            tvTen.setText(item.getTenSanPham());
            tvGia.setText(item.getDonGia() + " VND");
            tvSoLuong.setText(String.valueOf(item.getSoLuong()));

            btnGiam.setOnClickListener(v -> activity.giamSoLuong(position));
            btnTang.setOnClickListener(v -> activity.tangSoLuong(position));
            imgXoa.setOnClickListener(v -> activity.xoaKhoiGio(position));
        }

        return convertView;
    }
}
