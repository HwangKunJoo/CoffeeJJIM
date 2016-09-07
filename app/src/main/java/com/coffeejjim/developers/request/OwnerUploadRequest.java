package com.coffeejjim.developers.request;

import android.content.Context;

import com.coffeejjim.developers.data.ContentData;
import com.coffeejjim.developers.data.NetworkResult;
import com.google.gson.reflect.TypeToken;

import java.io.File;
import java.lang.reflect.Type;

import okhttp3.HttpUrl;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.Request;
import okhttp3.RequestBody;

/**
 * Created by Tacademy on 2016-09-07.
 */
public class OwnerUploadRequest extends AbstractRequest<NetworkResult<ContentData>> {

    MediaType jpeg = MediaType.parse("photo/jpeg");
    Request request;

    public OwnerUploadRequest(Context context, String content, File file) {
        HttpUrl url = getBaseUrlBuilder()
                .addPathSegment("images")
                .build();

        MultipartBody.Builder builder = new MultipartBody.Builder()
                .setType(MultipartBody.FORM)
                .addFormDataPart("photo", content);
        if (file != null) {
            builder.addFormDataPart("myFile", file.getName(), RequestBody.create(jpeg, file));
        }
        RequestBody body = builder.build();
        request = new Request.Builder()
                .url(url)
                .put(body)
                .tag(content)
                .build();
    }

    @Override
    protected Type getType() {
        return new TypeToken<NetworkResult<ContentData>>(){}.getType();
    }

    @Override
    public Request getRequest() {
        return request;
    }
}
