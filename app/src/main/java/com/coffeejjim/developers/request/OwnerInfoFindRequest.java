package com.coffeejjim.developers.request;

import android.content.Context;

import com.coffeejjim.developers.data.NetworkResult;
import com.coffeejjim.developers.data.Owner;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;

import okhttp3.FormBody;
import okhttp3.HttpUrl;
import okhttp3.Request;
import okhttp3.RequestBody;

/**
 * Created by Tacademy on 2016-09-21.
 */

public class OwnerInfoFindRequest extends AbstractRequest<NetworkResult<Owner>> {
    Request request;

    public OwnerInfoFindRequest(Context context, String ownerEmail) {
        HttpUrl.Builder builder = getBaseUrlHttpsBuilder();
        builder.addPathSegment("cafes")
                .addPathSegment("find");

        RequestBody body = new FormBody.Builder()
                .add("ownerEmail", ownerEmail)
                .build();

        request = new Request.Builder()
                .url(builder.build())
                .put(body)
                .tag(context)
                .build();
    }

    @Override
    protected Type getType() {
        return new TypeToken<NetworkResult<Owner>>() {
        }.getType();
    }

    @Override
    public Request getRequest() {
        return request;
    }
}
