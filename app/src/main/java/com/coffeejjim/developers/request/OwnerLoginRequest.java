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
 * Created by Tacademy on 2016-09-05.
 */
public class OwnerLoginRequest extends AbstractRequest<NetworkResult<Owner>> {

    Request request;

    public OwnerLoginRequest(Context context, String ownerLoginId, String password, String fcmToken) {
        HttpUrl.Builder builder = getBaseUrlHttpsBuilder();
        builder.addPathSegment("auth").addPathSegment("local").addPathSegment("login");

        RequestBody body = new FormBody.Builder()
                .add("ownerLoginId", ownerLoginId)
                .add("password", password)
                .add("fcmToken", fcmToken)
                .build();

        request = new Request.Builder()
                .url(builder.build())
                .post(body)
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
