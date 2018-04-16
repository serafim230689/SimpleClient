package com.simpleclient.api;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class PersonResponse {
    @SerializedName("user")
    public List<Person> user;

    public List<Person> getUsers() {
        return user;
    }

    public void setUser(List<Person> user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "PersonResponse{" +
                "user=" + user +
                '}';
    }
}
