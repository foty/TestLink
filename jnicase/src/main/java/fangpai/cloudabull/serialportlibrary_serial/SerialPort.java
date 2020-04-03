package fangpai.cloudabull.serialportlibrary_serial;

import java.io.FileDescriptor;

/**
 * Created by lxx on 2019/10/16.
 * Use by
 */

public class SerialPort {

    static {
        System.loadLibrary("serial_port");
    }

    private native static FileDescriptor open(String path, int baudrate, int flags);


    public native void close();
}
