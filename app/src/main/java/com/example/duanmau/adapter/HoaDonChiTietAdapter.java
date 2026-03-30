package com.example.duanmau.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.example.duanmau.R;
import com.example.duanmau.model.HoaDonChiTiet;
import java.util.List;

public class HoaDonChiTietAdapter extends ArrayAdapter<HoaDonChiTiet> {
    private Context context;
    private List<HoaDonChiTiet> list;

    public HoaDonChiTietAdapter(@NonNull Context context, List<HoaDonChiTiet> list) {
        super(context, 0, list);
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_hoa_don_chi_tiet_item, parent, false);
        }

        HoaDonChiTiet item = list.get(position);
        if (item != null) {
            TextView tvTen = convertView.findViewById(R.id.tvTenSanPham);
            TextView tvGia = convertView.findViewById(R.id.tvGiaSanPham);
            TextView tvSL = convertView.findViewById(R.id.tvSoLuong);

            tvTen.setText(item.getTenSanPham());
            tvGia.setText("Đơn giá: " + item.getDonGia() + " VND");
            tvSL.setText("Số lượng: " + item.getSoLuong());
        }

        return convertView;
    }
}
