// IMyAidlInterface.aidl
package com.example.testlink.binder;
import com.example.testlink.binder.TestAidlBean;

// Declare any non-default types here with import statements

interface ITestAidlBean {

    void deCode(in TestAidlBean bean);
}
