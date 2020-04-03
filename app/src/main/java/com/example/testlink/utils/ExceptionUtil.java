package com.example.testlink.utils;

import android.util.Log;

import java.io.File;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by lxx on 2019/11/12.
 * Use by
 */

public class ExceptionUtil {
    private final String TAG = "ExceptionUtil";

    private DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss");
    private DateFormat formatterFileName = new SimpleDateFormat("yyyy-MM-dd");
    public static ExceptionUtil exceptionUtil = new ExceptionUtil();

    private ExceptionUtil() {
    }

    public static ExceptionUtil getInstance() {
        return exceptionUtil;
    }

    /**
     * @param path 文件存放路径
     * @param ex
     * @throws Exception
     */
    public void writeFile(String path, Throwable ex) throws Exception {
        String time = formatter.format(new Date());
        String fileTime = formatter.format(new Date());
        String fileName = "errorLog-" + fileTime + ".txt";

        StringBuffer sb = new StringBuffer();

        sb.append(time + "   --start------" + "\n");
        sb.append(getThrowableStr(ex));
        sb.append(time + "   ---end-------" + "\n");
        sb.append("\n\n");

        File dir = new File(path);
        Log.d(TAG, "writeFile: " + dir);
        if (!dir.exists())
            dir.mkdirs();

        FileOutputStream fos = new FileOutputStream(path + fileName, true);
        fos.write(sb.toString().getBytes());
        fos.flush();
        fos.close();
    }

    private static String getThrowableStr(Throwable ex) {
        Writer writer = new StringWriter();
        PrintWriter printWriter = new PrintWriter(writer);
        ex.printStackTrace(printWriter);
        Throwable cause = ex.getCause();
        while (cause != null) {
            cause.printStackTrace(printWriter);
            cause = cause.getCause();
        }
        printWriter.flush();
        printWriter.close();
        return writer.toString();
    }

    public void deleteLog(final String path) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                File fileDir = new File(path);
                if (fileDir.exists()) {
                    File[] files = fileDir.listFiles();
                    int size = files.length;
                    //保存8天的日志
                    int count = size - 7;
                    Log.d(TAG, "deleteLog: 日志数量= " + size + "   " + files[0].getAbsolutePath());
                    int index = 0;
                    if (count > 0) {
                        for (File childFile : files) {
                            if (childFile.isFile() && index < count) {
                                childFile.delete();
                                index++;
                            }
                        }
                    }
                }
            }
        }).start();
    }


}
