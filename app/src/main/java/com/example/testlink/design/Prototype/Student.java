package com.example.testlink.design.Prototype;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.List;

/**
 * Created by lxx on 2019/5/15.
 * Use by
 */

public class Student implements StudentPro {

    public String name;
    public int age;
    public int sex;
    public String address;

    public List likes;
    public Country country;

    public List getLikes() {
        return likes;
    }

    public void setLikes(List likes) {
        this.likes = likes;
    }

    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }

    @Override
    public StudentPro clone() {
        try {
            return (StudentPro) super.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 通过读写流实现
     * @return
     */
    @Override
    public StudentPro deepClone() {
        try {

            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            ObjectOutputStream oos = new ObjectOutputStream(bos);
            oos.writeObject(this);

            ByteArrayInputStream bis = new ByteArrayInputStream(bos.toByteArray());
            ObjectInputStream ois;
            ois = new ObjectInputStream(bis);

            return (StudentPro) ois.readObject();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public String toString() {
        return "[" + name + ", " + age + ", " + sex + ", " + address + ", " + country.getName() + ", " + likes + "]";
    }
}
