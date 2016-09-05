package com.coffeejjim.developers.request;

import android.content.Context;

import com.coffeejjim.developers.data.NetworkResult;
import com.coffeejjim.developers.data.Owner;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;

import okhttp3.HttpUrl;
import okhttp3.Request;

/**
 * Created by Tacademy on 2016-09-05.
 */
public class LoginIdCheckedRequest extends AbstractRequest<NetworkResult<Owner>>  {

    Request request;
    public LoginIdCheckedRequest(Context context, String ownerLoginId) {

        HttpUrl url = getBaseUrlBuilder()
                .addPathSegment("cafes")
                .addPathSegment("checkid")
                .addQueryParameter("ownerLoginId", ownerLoginId)
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
