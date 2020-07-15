package com.cudocom.barang.ui.stock;

import androidx.lifecycle.ViewModel;

import com.cudocom.barang.model.Barang;
import com.cudocom.barang.repo.StockRepository;
import com.cudocom.barang.util.SingleLiveEvent;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

public class StockViewModel extends ViewModel {

    private final StockRepository stockRepository;

    SingleLiveEvent<List<Barang>> listSingleLiveEvent = new SingleLiveEvent<>();

    @Inject
    public StockViewModel(StockRepository stockRepository) {
        this.stockRepository = stockRepository;
        this.listSingleLiveEvent.setValue(stockRepository.getBarangAll());
    }

    public SingleLiveEvent<List<Barang>> getListSingleLiveEvent() {
        return listSingleLiveEvent;
    }

    void getAllStock(){
        this.listSingleLiveEvent.setValue(stockRepository.getBarangAll());
    }

    void inserStock(Barang barang){
        stockRepository.saveBarang(barang);
    }

    int getMaxSeq(){
        return stockRepository.getMaxSeq();
    }

    void updateStock(Barang barang){
        stockRepository.updateBarang(barang);
    }

    void deleteStock(Barang barang){
        stockRepository.deleteBarang(barang);
    }
}