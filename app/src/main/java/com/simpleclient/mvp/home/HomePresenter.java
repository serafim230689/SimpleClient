package com.simpleclient.mvp.home;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.util.Log;

import com.simpleclient.SimpleClientApplication;
import com.simpleclient.api.Person;
import com.simpleclient.utils.PersonObserver;

import java.util.Observable;
import java.util.Observer;

public class HomePresenter implements IHomePresenter, Observer {

    private IHomeView view;

    private PersonObserver personObserver;
    private Context context = SimpleClientApplication.getContext();

    public HomePresenter(IHomeView view) {
        this.view = view;
        personObserver = PersonObserver.getInstance();
    }

    @Override
    public void attachView() {
        personObserver.addObserver(this);
        view.showPersons();
    }

    @Override
    public void detachView() {
        personObserver.deleteObserver(this);
    }

    @Override
    public void update(Observable observable, Object o) {
        Log.d(HomePresenter.class.getSimpleName(), "This method is notified after click on some person.");
        if (observable instanceof PersonObserver) {
            PersonObserver observer = (PersonObserver) observable;
            Person selectedPerson = observer.getPerson();
            view.showDetailedView(selectedPerson);
        }
    }

    @Override
    public String getVersionName() {
        String version = "";
        try {
            PackageInfo pInfo = context.getPackageManager().getPackageInfo(context.getPackageName(), 0);
            version = pInfo.versionName;
        } catch (PackageManager.NameNotFoundException e) {
            Log.e(HomePresenter.class.getSimpleName(), e.getMessage(), e);
        }
        return "v " + version;
    }
}
