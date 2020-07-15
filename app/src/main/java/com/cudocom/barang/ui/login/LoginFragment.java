package com.cudocom.barang.ui.login;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;

import com.cudocom.barang.R;
import com.cudocom.barang.binding.FragmentDataBindingComponent;
import com.cudocom.barang.databinding.LoginFragmentBinding;
import com.cudocom.barang.di.Injectable;
import com.cudocom.barang.util.AutoClearedValue;
import com.cudocom.barang.util.GlobalFunc;
import com.cudocom.barang.util.NavigationController;

import java.util.Objects;

import javax.inject.Inject;

public class LoginFragment extends Fragment implements Injectable {

    private LoginViewModel mViewModel;

    @Inject
    NavigationController navigationController;
    @Inject
    ViewModelProvider.Factory viewModelFactory;

    private androidx.databinding.DataBindingComponent dataBindingComponent = new FragmentDataBindingComponent(this);
    private AutoClearedValue<LoginFragmentBinding> binding;

    public static LoginFragment newInstance() {
        return new LoginFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        LoginFragmentBinding loginFragmentBinding = DataBindingUtil.inflate(inflater,
                R.layout.login_fragment, container, false, dataBindingComponent);
        binding = new AutoClearedValue<>(this, loginFragmentBinding);

        return loginFragmentBinding.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this, viewModelFactory).get(LoginViewModel.class);
        initComponent();
        initData();
    }


    void initComponent() {
        binding.get().btnLogin.setOnClickListener(v -> {
            doLogin(Objects.requireNonNull(binding.get().etUserid.getText()).toString(),
                    Objects.requireNonNull(binding.get().etPwd.getText()).toString());
        });

        binding.get().etUserid.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                binding.get().tvErrorLogin.setText("");
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        binding.get().etPwd.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                binding.get().tvErrorLogin.setText("");
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    void initData() {

    }


    void doLogin(String uName, String pass) {
        if (uName != null && pass != null) {
            if (uName.matches("") || pass.isEmpty()) {
                binding.get().tvErrorLogin.setText(getResources().getString(R.string.error_empty_email));
            } else if (!GlobalFunc.VALID_FORMAT_EMAIL(uName)) {
                binding.get().tvErrorLogin.setText(getResources().getString(R.string.error_email_wrong));
            } else if (pass.matches("") || pass.isEmpty()) {
                binding.get().tvErrorLogin.setText(getResources().getString(R.string.error_empty_password));
            } else {
                // navigate to next fragment
                navigationController.navigateStock();

                binding.get().tvErrorLogin.setText("");
                binding.get().etUserid.setText("");
                binding.get().etPwd.setText("");
            }
        }
    }

}