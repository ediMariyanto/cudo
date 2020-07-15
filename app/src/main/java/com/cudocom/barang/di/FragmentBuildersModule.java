package com.cudocom.barang.di;


import com.cudocom.barang.ui.login.LoginFragment;
import com.cudocom.barang.ui.stock.StockFragment;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
abstract class FragmentBuildersModule {

    @ContributesAndroidInjector
    abstract LoginFragment loginFragment();

    @ContributesAndroidInjector
    abstract StockFragment stockFragment();

}
