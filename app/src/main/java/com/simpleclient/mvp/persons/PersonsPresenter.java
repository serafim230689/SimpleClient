package com.simpleclient.mvp.persons;


import android.os.Handler;
import android.os.Looper;
import android.util.Log;

import com.google.gson.Gson;
import com.simpleclient.api.APIConfig;
import com.simpleclient.api.PersonResponse;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class PersonsPresenter implements IPersonsPresenter {

    private static final String LOG_TAG = PersonsPresenter.class.getSimpleName();
    private static final int TOTAL_PAGES_COUNT = 3;
    private static final int PAGE_SIZE = 10;
    private IPersonsView view;
    private boolean isLoading;
    private int pageNumber = 1;

    public PersonsPresenter(IPersonsView view) {
        this.view = view;
    }

    @Override
    public void attachView() {

    }

    @Override
    public void detachView() {

    }

    @Override
    public void loadMorePersons() {
        Log.d(LOG_TAG, "===> loadMorePersons");
        view.showProgress(true);
        loadMoreItems();
    }

    @Override
    public boolean isLoadAvailable() {
        boolean isLastPage = (pageNumber == TOTAL_PAGES_COUNT ? true : false);;
        return !isLoading && !isLastPage;
    }

    @Override
    public int getPageItemsCount() {
        return PAGE_SIZE;
    }

    private void loadMoreItems() {
        OkHttpClient client = new OkHttpClient();
        String url = pageNumber == 1 ? APIConfig.PAGE_1 : APIConfig.PAGE_2;
        Request request = new Request.Builder()
                .url(url)
                .build();

        isLoading = true;
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.e(LOG_TAG, e.getMessage(), e);
                isLoading = false;
                view.showProgress(false);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (response.isSuccessful()) {
                    String json = response.body().string();
                    final PersonResponse personResponse = new Gson().fromJson(json, PersonResponse.class);
                    Log.d("===> personResponse: ", personResponse.toString());

                    new Handler(Looper.getMainLooper()).post(new Runnable() {
                        @Override
                        public void run() {
                            view.showProgress(false);
                            view.show(personResponse.getUsers());
                        }
                    });
                    pageNumber++;
                    isLoading = false;
                }
            }
        });
    }

}
