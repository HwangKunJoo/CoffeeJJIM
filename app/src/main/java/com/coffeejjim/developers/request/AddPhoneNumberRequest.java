package com.coffeejjim.developers.request;

import android.content.Context;

import com.coffeejjim.developers.data.NetworkResult;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;

import okhttp3.FormBody;
import okhttp3.HttpUrl;
import okhttp3.Request;
import okhttp3.RequestBody;

/**
 * Created by Tacademy on 2016-09-21.
 */

public class AddPhoneNumberRequest extends AbstractRequest<NetworkResult<String>> {
    Request request;

    public AddPhoneNumberRequest(Context context, String phoneNumber) {
        HttpUrl.Builder builder = getBaseUrlHttpsBuilder();
        builder.addPathSegment("customers")
                .addPathSegment("me");

        RequestBody body = new FormBody.Builder()
                .add("phoneNumber", phoneNumber)
                .build();

        request = new Request.Builder()
                .url(builder.build())
                .put(body)
                .tag(context)
                .build();
    }

    @Override
    protected Type getType() {
        return new TypeToken<NetworkResult<String>>() {
        }.getType();
    }

    @Override
    public Request getRequest() {
        return request;
    }
}
