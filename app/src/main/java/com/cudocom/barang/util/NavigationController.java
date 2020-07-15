package com.cudocom.barang.util;

import androidx.fragment.app.FragmentManager;

import com.cudocom.barang.MainActivity;
import com.cudocom.barang.R;
import com.cudocom.barang.ui.login.LoginFragment;
import com.cudocom.barang.ui.stock.StockFragment;

import javax.inject.Inject;


public class NavigationController {

    private final int containerId;
    private final FragmentManager fragmentManager;

    @Inject
    public NavigationController(MainActivity mainActivity) {
        this.containerId = R.id.container;
        this.fragmentManager = mainActivity.getSupportFragmentManager();
    }

    public void navigateLogin() {
        LoginFragment loginFragment = LoginFragment.newInstance();
        fragmentManager.beginTransaction()
                .replace(containerId, loginFragment)
                .addToBackStack(null)
                .commitAllowingStateLoss();
    }

    public void navigateStock() {
        StockFragment stockFragment = StockFragment.newInstance();
        fragmentManager.beginTransaction()
                .replace(containerId, stockFragment)
                .addToBackStack(null)
                .commitAllowingStateLoss();
    }

}
