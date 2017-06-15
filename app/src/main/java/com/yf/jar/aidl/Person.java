package com.yf.jar.aidl;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * 类功能：
 * Created by lenovo on 2017/5/8 11:19.
 */
public class Person implements Parcelable{


    private int id;
    private String name;

    public Person(int id, String name) {
        this.id = id;
        this.name = name;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(id);
        parcel.writeString(name);

    }

    public static final Creator<Person> CREATOR = new Creator<Person>(){

        @Override
        public Person createFromParcel(Parcel in) {
            return new Person(in);
        }

        @Override
        public Person[] newArray(int i) {
            return new Person[i];
        }
    };


    private Person(Parcel in){

        id=in.readInt();
        name=in.readString();
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
