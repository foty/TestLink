package com.example.testlink.design.Prototype;

import java.io.Serializable;

/**
 * Created by lxx on 2019/5/15.
 * Use by
 */

public interface StudentPro extends Cloneable,Serializable {

    StudentPro clone();

    StudentPro deepClone();

}
