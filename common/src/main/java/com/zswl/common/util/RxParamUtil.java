package com.zswl.common.util;

import java.io.File;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

public class RxParamUtil {

    public static RequestBody get(String value) {
        return RequestBody.create(MediaType.parse("text/plain"), value);
    }

    public static MultipartBody.Part get(File value) {
        return get("img", value);
    }

    public static MultipartBody.Part get(String param, File value) {
        RequestBody imageBody = RequestBody.create(MediaType.parse("image/*"), value);
        return MultipartBody.Part.createFormData(param,
                "android" + System.currentTimeMillis() + ".jpg", imageBody);
    }
}
