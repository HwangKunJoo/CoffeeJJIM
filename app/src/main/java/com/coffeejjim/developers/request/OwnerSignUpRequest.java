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
 * Created by Tacademy on 2016-09-01.
 */
public class OwnerSignUpRequest extends AbstractRequest<NetworkResult<Owner>> {


    Request request;
    public OwnerSignUpRequest(Context context, String ownerName, String ownerLoginId, String password,
                              String ownerPhoneNumber, String ownerEmail, String cafeName,
                              String cafeAddress, String latitude,
                              String longitude, String cafePhoneNumber, String fcmToken)
    {
        HttpUrl.Builder builder = getBaseUrlBuilder();
        builder.addPathSegment("cafes");

        RequestBody body = new FormBody.Builder()
                .add("ownerName", ownerName)
                .add("ownerLoginId", ownerLoginId)
                .add("password",password)
                .add("ownerPhoneNumber", ownerPhoneNumber)
                .add("ownerEmail", ownerEmail)
                .add("cafeName", cafeName)
                .add("cafeAddress", cafeAddress)
                .add("latitude", latitude)
                .add("longitude", longitude)
                .add("cafePhoneNumber", cafePhoneNumber)
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
