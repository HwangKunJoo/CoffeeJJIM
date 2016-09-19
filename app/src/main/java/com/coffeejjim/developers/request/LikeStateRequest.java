package com.coffeejjim.developers.request;

import android.content.Context;

import com.coffeejjim.developers.data.Cafe;
import com.coffeejjim.developers.data.NetworkResult;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;

import okhttp3.HttpUrl;
import okhttp3.Request;

/**
 * Created by Tacademy on 2016-09-19.
 */
public class LikeStateRequest extends AbstractRequest<NetworkResult<Cafe>> {

    Request request;
    public LikeStateRequest(Context context, int cafeId) {
        HttpUrl url = getBaseUrlBuilder()
                .addPathSegment("bookmarks")
                .addPathSegment(""+cafeId)
                .build();

        request = new Request.Builder()
                .url(url)
                .tag(context)
                .build();
    }

    @Override
    protected Type getType() {
        return new TypeToken<NetworkResult<Cafe>>() {}.getType();
    }

    @Override
    public Request getRequest() {
        return request;
    }
}
