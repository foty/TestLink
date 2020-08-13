package com.example.testlink;

import android.content.Context;
import android.os.Environment;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;
import android.util.Log;

import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.*;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class ExampleInstrumentedTest {
    @Test
    public void useAppContext() {
        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getTargetContext();
        String path = appContext.getCacheDir().getAbsolutePath();
        String path1 = appContext.getExternalCacheDir().getAbsolutePath();
        String path2 = appContext.getExternalFilesDir(Environment.DIRECTORY_PICTURES).getAbsolutePath();
        String path3 = Environment.getExternalStorageDirectory().getAbsolutePath();
        String path4 =  Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES).getAbsolutePath();
        Log.e("ss",path);
        Log.e("ss",path1);
        Log.e("ss",path2);
        Log.e("ss",path3);
        Log.e("ss",path4);
        Log.e("ss","12345.jpg".toUpperCase());
        assertEquals("com.example.testlink", appContext.getPackageName());

    }
}
