package com.simpleclient.api;


import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public class PersonMessage implements Parcelable{

    @SerializedName("time")
    public long time;

    @SerializedName("msg")
    public String msg;

    public PersonMessage(Parcel in){
        time = in.readLong();
        msg = in.readString();
    }

    public static final Creator<PersonMessage> CREATOR = new Creator<PersonMessage>() {
        @Override
        public PersonMessage createFromParcel(Parcel in) {
            return new PersonMessage(in);
        }

        @Override
        public PersonMessage[] newArray(int size) {
            return new PersonMessage[size];
        }
    };

    public long getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    @Override
    public String toString() {
        return "PersonMessage{" +
                "time=" + time +
                ", msg='" + msg + '\'' +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeLong(time);
        parcel.writeString(msg);
    }
}
