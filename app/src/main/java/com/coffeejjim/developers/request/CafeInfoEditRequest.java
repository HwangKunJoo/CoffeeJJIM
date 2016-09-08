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
public class CafeInfoEditRequest extends AbstractRequest<NetworkResult<Cafe>> {

    Request request;

    public CafeInfoEditRequest(Context context, String type, String cafeAddress, double latitude, double longitude,
                               String cafePhoneNumber, String businessHour,
                               int wifi, int days, int parking, int socket) {

        HttpUrl.Builder builder = getBaseUrlHttpsBuilder();
        builder.addPathSegment("cafes")
                .addPathSegment("me");

        RequestBody body = new FormBody.Builder()
                .add("type", type)
                .add("cafeAddress", cafeAddress)
                .add("latitude", ""+latitude)
                .add("longitude", ""+longitude)
                .add("cafePhoneNumber", cafePhoneNumber)
                .add("businessHour", businessHour)
                .add("wifi", "" + wifi)
                .add("days", "" + days)
                .add("parking", "" + parking)
                .add("socket", "" + socket)
                .build();

        request = new Request.Builder()
                .url(builder.build())
                .put(body)
                .tag(context)
                .build();
    }

    @Override
    protected Type getType() {
        return new TypeToken<NetworkResult<Cafe>>() {
        }.getType();
    }

    @Override
    public Request getRequest() {
        return request;
    }
}
