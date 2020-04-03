LOCAL_PATH := $(call my-dir)

include $(CLEAR_VARS)

LOCAL_MODULE    := serial_port
LOCAL_SRC_FILES := fangpai_cloudabull_serialportlibrary_serial_SerialPort.c

LOCAL_LDFLAGS += -fPIC

include $(BUILD_SHARED_LIBRARY)
