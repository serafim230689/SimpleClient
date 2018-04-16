package com.simpleclient.mvp.persons;

import com.simpleclient.mvp.BasePresenter;

public interface IPersonsPresenter extends BasePresenter {

    void loadMorePersons();

    boolean isLoadAvailable();

    int getPageItemsCount();
}
