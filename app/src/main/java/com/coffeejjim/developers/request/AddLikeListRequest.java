package com.coffeejjim.developers.request;

import android.content.Context;

import com.coffeejjim.developers.data.Cafe;
import com.coffeejjim.developers.data.NetworkResult;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;

import okhttp3.FormBody;
import okhttp3.HttpUrl;
import okhttp3.Request;
import okhttp3.RequestBody;

/**
 * Created by Tacademy on 2016-09-08.
 */
public class AddLikeListRequest extends AbstractRequest<NetworkResult<Cafe>>{

    Request request;
    public AddLikeListRequest(Context context, String cafeId) {
        HttpUrl url = getBaseUrlBuilder()
                .addPathSegment("bookmarks")
                .build();

        RequestBody body = new FormBody.Builder()
                .add("cafeId",cafeId).build();

        request = new Request.Builder()
                .url(url)
                .tag(context)
                .post(body)
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
