package com.simpleclient.utils;

import com.simpleclient.api.Person;

import java.util.Observable;

public class PersonObserver extends Observable {

    private static PersonObserver instance;
    private Person person;

    private PersonObserver() {

    }

    public static PersonObserver getInstance() {
        if (instance == null) {
            instance = new PersonObserver();
        }
        return instance;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
        setChanged();
        notifyObservers();
    }
}
