package com.coffeejjim.developers.request;

import android.content.Context;

import com.coffeejjim.developers.data.Cafe;
import com.coffeejjim.developers.data.Customer;
import com.coffeejjim.developers.data.NetworkResult;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;

import okhttp3.FormBody;
import okhttp3.HttpUrl;
import okhttp3.Request;
import okhttp3.RequestBody;

/**
 * Created by acer on 2016-09-11.
 */
public class AuctionRangeEditRequest extends AbstractRequest<NetworkResult<Integer>> {
    Request request;

    public AuctionRangeEditRequest(Context context, String auctionRange) {

        HttpUrl.Builder builder = getBaseUrlHttpsBuilder();
        builder.addPathSegment("users")
                .addPathSegment("me");

        RequestBody body = new FormBody.Builder()
                .add("auctionRange", auctionRange)
                .build();

        request = new Request.Builder()
                .url(builder.build())
                .put(body)
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
