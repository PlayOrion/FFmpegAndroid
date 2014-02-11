LOCAL_PATH := $(call my-dir)

include $(CLEAR_VARS)

LOCAL_MODULE    := FFmpegExample
LOCAL_SRC_FILES := FFmpegExample.cpp

include $(BUILD_SHARED_LIBRARY)
