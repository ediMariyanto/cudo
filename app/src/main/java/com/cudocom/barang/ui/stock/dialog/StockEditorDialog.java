package com.cudocom.barang.ui.stock.dialog;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Point;
import android.os.Bundle;
import android.view.Display;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.DialogFragment;

import com.cudocom.barang.R;
import com.cudocom.barang.binding.FragmentDataBindingComponent;
import com.cudocom.barang.databinding.FragmentStockEditorDialogBinding;
import com.cudocom.barang.model.Barang;
import com.cudocom.barang.ui.stock.StockFragment;
import com.cudocom.barang.util.AutoClearedValue;
import com.cudocom.barang.util.Constants;
import com.cudocom.barang.util.GlobalFunc;

import java.util.Objects;

import timber.log.Timber;

public class StockEditorDialog extends DialogFragment {

    private androidx.databinding.DataBindingComponent dataBindingComponent = new FragmentDataBindingComponent(this);
    private AutoClearedValue<FragmentStockEditorDialogBinding> binding;

    Barang barang;
    String layoutType;

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initComponent();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        FragmentStockEditorDialogBinding stockFragmentBinding = DataBindingUtil.inflate(inflater,
                R.layout.fragment_stock_editor_dialog, container, false, dataBindingComponent);
        binding = new AutoClearedValue<>(this, stockFragmentBinding);

        return stockFragmentBinding.getRoot();
    }

    public static StockEditorDialog newInstance(Barang barang, String layoutType) {

        Bundle args = new Bundle();

        StockEditorDialog fragment = new StockEditorDialog();
        fragment.barang = barang;
        fragment.layoutType = layoutType;
        fragment.setArguments(args);
        return fragment;
    }

    @SuppressLint("ShowToast")
    void initComponent() {

        if (layoutType.equals("Edit")) {
            binding.get().tvIdEditorStock.setText(GlobalFunc.GET_FORMAT_THOUSAND_SEPARATOR_WITHOUT_COMMA(barang.getId()));
            binding.get().etNamaEditorStock.setText(barang.getNama_barang());
            binding.get().etQtyEditorStock.setText(GlobalFunc.GET_FORMAT_THOUSAND_SEPARATOR_WITHOUT_COMMA(barang.getQty()));
            binding.get().etHargaEditorStock.setText(GlobalFunc.GET_FORMAT_THOUSAND_SEPARATOR_WITHOUT_COMMA(barang.getHarga()));
        } else {
            binding.get().tvIdEditorStock.setText(GlobalFunc.GET_FORMAT_THOUSAND_SEPARATOR_WITHOUT_COMMA(barang.getId()));
        }

        binding.get().btnSubmitEditorStock.setOnClickListener(v -> {
            String nama = binding.get().etNamaEditorStock.getText().toString();
            String qty = GlobalFunc.GET_UNFORMAT_THOUSAND_SEPARATOR(binding.get().etQtyEditorStock.getText().toString());
            String harga = GlobalFunc.GET_UNFORMAT_THOUSAND_SEPARATOR(binding.get().etHargaEditorStock.getText().toString());

            if (nama.equals("")){
                Toast.makeText(getContext(), "Silahkan isi nama terlebih dahulu", Toast.LENGTH_SHORT);
            } else if(qty.equals("")){
                Toast.makeText(getContext(), "Silahkan isi qty terlebih dahulu", Toast.LENGTH_SHORT);
            } else if (harga.equals("")){
                Toast.makeText(getContext(), "Silahkan isi harga terlebih dahulu", Toast.LENGTH_SHORT);
            } else {
                Barang barang = new Barang();
                barang.setId(this.barang.getId());
                barang.setNama_barang(nama);
                barang.setQty(Integer.parseInt(qty));
                barang.setHarga(Integer.parseInt(harga));

                sendResult(barang, this.layoutType);
            }

        });
    }

    void sendResult(Barang barang, String layoutType) {
        Intent intent = null;

        if (getTargetFragment() == null) {
            return;
        }

        if (getTargetRequestCode() == Constants.REQUEST_CODE_FROM_STOCK) {
            intent = StockFragment.newIntent(barang, layoutType);
        }

        try {
            getTargetFragment().onActivityResult(getTargetRequestCode(), Activity.RESULT_OK, intent);
        } catch (Exception e) {
            Timber.e(e);
        }
        dismiss();
    }

    @Override
    public int getTheme() {
        return super.getTheme();
    }

    @Override
    public void onResume() {
        Window window = Objects.requireNonNull(getDialog()).getWindow();
        Point size = new Point();
        Display display = null;
        if (window != null) {
            display = window.getWindowManager().getDefaultDisplay();
        }
        if (display != null) {
            display.getSize(size);
        }
        Objects.requireNonNull(window).setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);
        window.setGravity(Gravity.CENTER);
        super.onResume();
    }
}