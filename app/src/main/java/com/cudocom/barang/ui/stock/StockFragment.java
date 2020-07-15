package com.cudocom.barang.ui.stock;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Canvas;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;

import com.cudocom.barang.R;
import com.cudocom.barang.binding.FragmentDataBindingComponent;
import com.cudocom.barang.databinding.StockFragmentBinding;
import com.cudocom.barang.di.Injectable;
import com.cudocom.barang.model.Barang;
import com.cudocom.barang.ui.stock.dialog.StockEditorDialog;
import com.cudocom.barang.util.AutoClearedValue;
import com.cudocom.barang.util.Constants;
import com.cudocom.barang.util.NavigationController;
import com.cudocom.barang.util.SwipeController;
import com.cudocom.barang.util.SwipeControllerActions;

import org.jetbrains.annotations.NotNull;

import java.util.Objects;

import javax.inject.Inject;

public class StockFragment extends Fragment implements Injectable {

    private StockViewModel mViewModel;

    @Inject
    NavigationController navigationController;
    @Inject
    ViewModelProvider.Factory viewModelFactory;

    private androidx.databinding.DataBindingComponent dataBindingComponent = new FragmentDataBindingComponent(this);
    private AutoClearedValue<StockFragmentBinding> binding;

    AutoClearedValue<StockAdapter> stockAdapterAutoClearedValue;

    public static StockFragment newInstance() {
        return new StockFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        StockFragmentBinding stockFragmentBinding = DataBindingUtil.inflate(inflater,
                R.layout.stock_fragment, container, false, dataBindingComponent);
        binding = new AutoClearedValue<>(this, stockFragmentBinding);

        return stockFragmentBinding.getRoot();

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this, viewModelFactory).get(StockViewModel.class);

        initAdapter();
        initComponent();
        initData();

    }

    void initComponent() {

        binding.get().btnTambahBarang.setOnClickListener(v -> {

            Barang barang = new Barang();
            barang.setId(mViewModel.getMaxSeq() + 1);

            StockEditorDialog stockEditorDialog = StockEditorDialog.newInstance(barang, "");
            stockEditorDialog.setTargetFragment(StockFragment.this, Constants.REQUEST_CODE_FROM_STOCK);
            assert getFragmentManager() != null;
            stockEditorDialog.show(getFragmentManager(), "editor_stock");
        });
    }

    void initData() {
        mViewModel.getListSingleLiveEvent().observe(getViewLifecycleOwner(), result -> {
            if (result != null) {
                if (result.isEmpty()) {
                    binding.get().tvDataKosong.setVisibility(View.VISIBLE);
                    binding.get().rcvStock.setVisibility(View.GONE);
                } else {
                    binding.get().tvDataKosong.setVisibility(View.GONE);
                    binding.get().rcvStock.setVisibility(View.VISIBLE);
                    this.stockAdapterAutoClearedValue.get().setData(result);
                    Toast.makeText(getContext(), "Silahkan geser kanan dan kiri pada list diatas untuk mengubah data", Toast.LENGTH_LONG).show();
                }
            }
        });

    }

    void showConfirm(Barang barang){
        new AlertDialog.Builder(Objects.requireNonNull(getContext()))
                .setTitle("Delete Data Barang")
                .setMessage("Apakah anda yakin akan menghapus data "+barang.getNama_barang()+ " ?")
                .setIcon(android.R.drawable.ic_dialog_alert)
                .setPositiveButton(android.R.string.yes, (dialog, whichButton) -> {
                    mViewModel.deleteStock(barang);
                    mViewModel.getAllStock();
                })
                .setNegativeButton(android.R.string.no, null).show();
    }

    void initAdapter() {
        StockAdapter adapter = new StockAdapter();
        this.stockAdapterAutoClearedValue = new AutoClearedValue<>(this, adapter);
        binding.get().rcvStock.setAdapter(adapter);

        SwipeController swipeController = new SwipeController(new SwipeControllerActions() {
            @SuppressLint("ShowToast")
            @Override
            public void onLeftClicked(int position) {
                Barang listBarang = adapter.getData().get(position);
                showConfirm(listBarang);

            }

            @SuppressLint("ShowToast")
            @Override
            public void onRightClicked(int position) {
                Barang listBarang = adapter.getData().get(position);

                StockEditorDialog stockEditorDialog = StockEditorDialog.newInstance(listBarang, "Edit");
                stockEditorDialog.setTargetFragment(StockFragment.this, Constants.REQUEST_CODE_FROM_STOCK);
                assert getFragmentManager() != null;
                stockEditorDialog.show(getFragmentManager(), "editor_stock");
            }
        });

        ItemTouchHelper itemTouchhelper = new ItemTouchHelper(swipeController);
        itemTouchhelper.attachToRecyclerView(binding.get().rcvStock);

        binding.get().rcvStock.addItemDecoration(new RecyclerView.ItemDecoration() {
            @Override
            public void onDraw(@NotNull Canvas c, @NotNull RecyclerView parent, @NotNull RecyclerView.State state) {
                swipeController.onDraw(c, "");
            }
        });
    }

    public static Intent newIntent(Barang barang, String layoutType) {
        Intent intent = new Intent();
        intent.putExtra(Constants.RESULT_CODE_FROM_STOCK_ID, barang.getId());
        intent.putExtra(Constants.RESULT_CODE_FROM_STOCK_NAME, barang.getNama_barang());
        intent.putExtra(Constants.RESULT_CODE_FROM_STOCK_QTY, barang.getQty());
        intent.putExtra(Constants.RESULT_CODE_FROM_STOCK_HARGA, barang.getHarga());
        intent.putExtra(Constants.RESULT_CODE_FROM_STOCK_LAYOUTTYPE, layoutType);
        return intent;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode != Activity.RESULT_OK) {
            return;
        }

        if (requestCode == Constants.REQUEST_CODE_FROM_STOCK) {
            if (data != null) {
                int id = data.getIntExtra(Constants.RESULT_CODE_FROM_STOCK_ID, 0);
                String nama = data.getStringExtra(Constants.RESULT_CODE_FROM_STOCK_NAME);
                int qty = data.getIntExtra(Constants.RESULT_CODE_FROM_STOCK_QTY, 0);
                int harga = data.getIntExtra(Constants.RESULT_CODE_FROM_STOCK_HARGA, 0);
                String layoutType = data.getStringExtra(Constants.RESULT_CODE_FROM_STOCK_LAYOUTTYPE);

                Barang barang = new Barang();
                barang.setId(id);
                barang.setNama_barang(nama);
                barang.setQty(qty);
                barang.setHarga(harga);

                if (layoutType != null) {
                    if (layoutType.equals("Edit")) {
                        mViewModel.updateStock(barang);
                    } else {
                        mViewModel.inserStock(barang);
                    }
                }
                mViewModel.getAllStock();
            }
        }

    }
}