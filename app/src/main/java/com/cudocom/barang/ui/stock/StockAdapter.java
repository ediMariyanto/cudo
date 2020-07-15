package com.cudocom.barang.ui.stock;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.cudocom.barang.R;
import com.cudocom.barang.model.Barang;
import com.cudocom.barang.util.GlobalFunc;

import java.util.ArrayList;
import java.util.List;

public class StockAdapter extends RecyclerView.Adapter {

    List<Barang> data;

    public StockAdapter() {
        this.data = new ArrayList<>();
    }

    public List<Barang> getData() {
        return data;
    }

    public void setData(List<Barang> newData) {
        if (data != null) {
            data.clear();
            data.addAll(newData);

            notifyDataSetChanged();
        } else {
            data = newData;
        }
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_barang, parent, false);

        return new ComponentItem(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        final Barang barang = data.get(position);

        if (barang != null) {
            ((ComponentItem) holder).tvNamaBarang.setText(barang.getNama_barang());
            ((ComponentItem) holder).tvQtyBarang.setText(GlobalFunc.GET_FORMAT_THOUSAND_SEPARATOR_WITHOUT_COMMA(barang.getQty()));
            ((ComponentItem) holder).tvHargaBarang.setText(GlobalFunc.GET_FORMAT_THOUSAND_SEPARATOR_WITHOUT_COMMA(barang.getHarga()));
        }
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    private static class ComponentItem extends RecyclerView.ViewHolder {

        TextView tvNamaBarang, tvQtyBarang, tvHargaBarang;

        public ComponentItem(@NonNull View itemView) {
            super(itemView);
            tvNamaBarang = itemView.findViewById(R.id.tv_nama_barang);
            tvQtyBarang = itemView.findViewById(R.id.tv_qty_barang);
            tvHargaBarang = itemView.findViewById(R.id.tv_harga_barang);
        }
    }
}


