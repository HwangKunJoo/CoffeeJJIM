package com.coffeejjim.developers.request;

import android.content.Context;

import com.coffeejjim.developers.data.NetworkResult;
import com.coffeejjim.developers.data.Owner;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;

import okhttp3.HttpUrl;
import okhttp3.Request;

/**
 * Created by Tacademy on 2016-09-08.
 */
public class OwnerInfoRequest extends AbstractRequest<NetworkResult<Owner>>{
    Request request;

    public OwnerInfoRequest(Context context, int type){
        HttpUrl url = getBaseUrlBuilder()
                .addPathSegment("cafes")
                .addPathSegment("me")
                .addQueryParameter("type", ""+type)
                .build();

        request = new Request.Builder()
                .url(url)
                .tag(context)
                .build();
    }

    @Override
    protected Type getType() {
        return new TypeToken<NetworkResult<Owner>>(){}.getType();
    }

    @Override
    public Request getRequest() {
        return request;
    }
}
