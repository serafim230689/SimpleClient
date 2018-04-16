package com.simpleclient.mvp;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.simpleclient.mvp.home.HomeActivity;


public abstract class BaseFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    public void onResume() {
        super.onResume();
        ((HomeActivity) getActivity()).setActionBarTitle(getTitle(), getBackButtonEnabled());

    }

    public abstract String getTitle();
    public abstract boolean getBackButtonEnabled();
}
