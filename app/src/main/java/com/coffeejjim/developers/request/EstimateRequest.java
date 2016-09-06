package com.coffeejjim.developers.request;

import android.content.Context;

import com.coffeejjim.developers.data.Estimate;
import com.coffeejjim.developers.data.NetworkResult;
import com.coffeejjim.developers.data.Options;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;

import okhttp3.FormBody;
import okhttp3.HttpUrl;
import okhttp3.Request;
import okhttp3.RequestBody;

/**
 * Created by Tacademy on 2016-09-06.
 */
public class EstimateRequest extends AbstractRequest<NetworkResult<Estimate>> {
    Request request;


    public EstimateRequest(Context context, int people, String latitude, String longitude, String reservationTime,
                           Options options, int auctionTime) {
        HttpUrl.Builder builder = getBaseUrlBuilder();
        builder.addPathSegment("estimates");

        RequestBody body = new FormBody.Builder()
                .add("people", "" + people)
                .add("latitude", latitude)
                .add("longitude", longitude)
                .add("reservationTime", reservationTime)
                .add("wifi", "" + options.isWifi())
                .add("days", "" + options.isDays())
                .add("parking", "" + options.isParking())
                .add("socket", "" + options.isSocket())
                .add("auctionTime", "" + auctionTime)
                .build();

        request = new Request.Builder()
                .url(builder.build())
                .post(body)
                .tag(context)
                .build();
    }

    @Override
    protected Type getType() {
        return new TypeToken<NetworkResult<Estimate>>(){}.getType();
    }

    @Override
    public Request getRequest() {
        return request;
    }
}
