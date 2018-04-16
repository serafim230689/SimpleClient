package com.simpleclient.mvp.details;


import android.os.Bundle;

import com.simpleclient.mvp.BasePresenter;

public interface IPersonDetailPresenter extends BasePresenter {

    void setupPhoto(Bundle args, String key);
    String getTitleInfo();
}
