package com.simpleclient.mvp.home;

import com.simpleclient.api.Person;

public interface IHomeView {

    void showDetailedView(Person person);
    void showPersons();
}
