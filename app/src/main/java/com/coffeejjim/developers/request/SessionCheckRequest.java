package com.coffeejjim.developers.request;

import android.content.Context;

import com.coffeejjim.developers.data.NetworkResult;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;

import okhttp3.HttpUrl;
import okhttp3.Request;

/**
 * Created by Tacademy on 2016-09-20.
 */

public class SessionCheckRequest extends AbstractRequest<NetworkResult<Object>> {

    Request request;
    public SessionCheckRequest(Context context) {
        HttpUrl url = getBaseUrlBuilder()
                .addPathSegment("auth")
                .addPathSegment("isLogined")
                .build();

        request = new Request.Builder()
                .url(url)
                .tag(context)
                .build();
    }
    @Override
    protected Type getType() {
        return new TypeToken<NetworkResult<Object>>(){}.getType();
    }

    @Override
    public Request getRequest() {
        return request;
    }
}
