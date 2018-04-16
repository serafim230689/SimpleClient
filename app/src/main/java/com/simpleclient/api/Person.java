package com.simpleclient.api;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public class Person implements Parcelable {

    @SerializedName("deleted")
    public int deleted;

    @SerializedName("foto")
    public String foto;

    @SerializedName("name")
    public String name;

    @SerializedName("sex")
    public int sex;

    @SerializedName("age")
    public int age;

    @SerializedName("message")
    public PersonMessage data;

    public Person(Parcel in) {
        deleted = in.readInt();
        foto = in.readString();
        name = in.readString();
        sex = in.readInt();
        age = in.readInt();
        data = in.readParcelable(PersonMessage.class.getClassLoader());
    }

    public static final Creator<Person> CREATOR = new Creator<Person>() {
        @Override
        public Person createFromParcel(Parcel in) {
            return new Person(in);
        }

        @Override
        public Person[] newArray(int size) {
            return new Person[size];
        }
    };

    public int getDeleted() {
        return deleted;
    }

    public void setDeleted(int deleted) {
        this.deleted = deleted;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getSex() {
        return sex;
    }

    public void setSex(int sex) {
        this.sex = sex;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public PersonMessage getData() {
        return data;
    }

    public void setData(PersonMessage data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "Person{" +
                "deleted=" + deleted +
                ", foto='" + foto + '\'' +
                ", name='" + name + '\'' +
                ", sex=" + sex +
                ", age=" + age +
                ", data=" + data +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int flag) {
        parcel.writeInt(deleted);
        parcel.writeString(foto);
        parcel.writeString(name);
        parcel.writeInt(sex);
        parcel.writeInt(age);
        parcel.writeParcelable(data, flag);
    }
}
