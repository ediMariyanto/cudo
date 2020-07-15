package com.cudocom.barang.di;

import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.cudocom.barang.ui.login.LoginViewModel;
import com.cudocom.barang.ui.stock.StockViewModel;
import com.cudocom.barang.viewmodel.BarangViewModelFactory;

import dagger.Binds;
import dagger.Module;
import dagger.multibindings.IntoMap;

@Module
abstract class ViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(LoginViewModel.class)
    abstract ViewModel bindLoginActivityViewModel(LoginViewModel loginViewModel);

    @Binds
    @IntoMap
    @ViewModelKey(StockViewModel.class)
    abstract ViewModel bindStockViewModel(StockViewModel stockViewModel);

    @Binds
    abstract ViewModelProvider.Factory bindViewModelFactory(BarangViewModelFactory factory);

}
