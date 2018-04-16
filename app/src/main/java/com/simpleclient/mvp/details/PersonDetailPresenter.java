package com.simpleclient.mvp.details;

import android.os.Bundle;

import com.simpleclient.api.Person;

public class PersonDetailPresenter implements IPersonDetailPresenter {

    private IPersonDetailView view;
    private Person person;

    public PersonDetailPresenter(IPersonDetailView view) {
        this.view = view;
    }

    @Override
    public void attachView() {

    }

    @Override
    public void detachView() {

    }

    @Override
    public void setupPhoto(Bundle args, String key) {
        if (args != null) {
            person = (Person) args.get(key);
            if(person != null && person.getFoto() != null) {
                String largePhoto = person.getFoto().replace("@", "b_");
                view.showPhoto(largePhoto);
            }
        }
    }

    @Override
    public String getTitleInfo() {
        String title = "";
        if (person != null) {
            title = person.getName() + ", " + String.valueOf(person.getAge());
        }
        return title;
    }


}
