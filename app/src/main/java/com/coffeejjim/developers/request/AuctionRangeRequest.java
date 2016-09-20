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
public class AuctionRangeRequest extends AbstractRequest<NetworkResult<Integer>> {
    Request request;

    public AuctionRangeRequest(Context context) {

        HttpUrl.Builder builder = getBaseUrlHttpsBuilder();
        builder.addPathSegment("users")
                .addPathSegment("me");

        request = new Request.Builder()
                .url(builder.build())
                .tag(context)
                .build();
    }

    @Override
    protected Type getType() {
        return new TypeToken<NetworkResult<Integer>>() {
        }.getType();
    }

    @Override
    public Request getRequest() {
        return request;
    }
}
