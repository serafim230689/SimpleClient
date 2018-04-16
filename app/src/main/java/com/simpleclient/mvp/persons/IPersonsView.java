package com.simpleclient.mvp.persons;

import com.simpleclient.api.Person;

import java.util.List;

public interface IPersonsView {

    void show(List<Person> personList);
    void showProgress(boolean show);
}
