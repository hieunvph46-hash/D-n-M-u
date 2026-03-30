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
import com.example.duanmau.model.SanPham;
import java.util.List;

public class TopSanPhamAdapter extends ArrayAdapter<SanPham> {
    private Context context;
    private List<SanPham> list;

    public TopSanPhamAdapter(@NonNull Context context, List<SanPham> list) {
        super(context, 0, list);
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_top_san_pham, parent, false);
        }

        SanPham item = list.get(position);
        if (item != null) {
            TextView tvTen = convertView.findViewById(R.id.tv_ten_san_pham);
            TextView tvSoLuong = convertView.findViewById(R.id.tv_tong_so_luong);

            tvTen.setText(item.getTenSanPham());
            tvSoLuong.setText("Số lượng đã bán: " + item.getSoLuong());
        }

        return convertView;
    }
}
