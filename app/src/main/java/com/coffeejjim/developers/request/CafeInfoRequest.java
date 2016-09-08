package com.coffeejjim.developers.request;

import android.content.Context;

import com.coffeejjim.developers.data.CafeInfo;
import com.coffeejjim.developers.data.NetworkResult;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;

import okhttp3.HttpUrl;
import okhttp3.Request;

/**
 * Created by Tacademy on 2016-09-08.
 */
public class CafeInfoRequest extends AbstractRequest<NetworkResult<CafeInfo>> {
    Request request;

    public CafeInfoRequest(Context context, int type){
        HttpUrl url = getBaseUrlHttpsBuilder()
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
        return new TypeToken<NetworkResult<CafeInfo>>(){}.getType();
    }

    @Override
    public Request getRequest() {
        return request;
    }
}
