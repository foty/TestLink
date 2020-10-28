package com.example.testlink.binder;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Create by lxx
 * Date : 2020/10/28 10:51
 * Use by
 */
public class TestAidlBean implements Parcelable {

    private String name;
    private int number;

    protected TestAidlBean(Parcel in) {
        name = in.readString();
        number = in.readInt();
    }

    public static final Creator<TestAidlBean> CREATOR = new Creator<TestAidlBean>() {
        @Override
        public TestAidlBean createFromParcel(Parcel in) {
            return new TestAidlBean(in);
        }

        @Override
        public TestAidlBean[] newArray(int size) {
            return new TestAidlBean[size];
        }
    };

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    protected TestAidlBean(String name, int number) {
       this.name = name;
       this.number = number;
    }



    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeInt(number);
    }


}
