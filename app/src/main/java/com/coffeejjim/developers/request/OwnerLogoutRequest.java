package com.coffeejjim.developers.request;

import android.content.Context;

import com.coffeejjim.developers.data.NetworkResult;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;

import okhttp3.HttpUrl;
import okhttp3.Request;

/**
 * Created by Tacademy on 2016-09-07.
 */
public class OwnerLogoutRequest extends AbstractRequest<NetworkResult<String>> {

    Request request;

    public OwnerLogoutRequest(Context context) {
        HttpUrl url = getBaseUrlBuilder()
                .addPathSegment("auth")
                .addPathSegment("local")
                .addPathSegment("logout")
                .build();
        request = new Request.Builder()
                .url(url)
                .tag(context)
                .build();
    }

    @Override
    protected Type getType() {
        return new TypeToken<NetworkResult<String>>(){}.getType();
    }

    @Override
    public Request getRequest() {
        return request;
    }

}
