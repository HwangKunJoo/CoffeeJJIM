package com.coffeejjim.developers.request;

import android.content.Context;

import com.coffeejjim.developers.Utils;
import com.coffeejjim.developers.data.Estimate;
import com.coffeejjim.developers.data.NetworkResult;
import com.coffeejjim.developers.data.Options;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.Date;

import okhttp3.FormBody;
import okhttp3.HttpUrl;
import okhttp3.Request;
import okhttp3.RequestBody;

/**
 * Created by Tacademy on 2016-09-06.
 */
public class EstimateRequest extends AbstractRequest<NetworkResult<Estimate>> {
    Request request;


    public EstimateRequest(Context context, int people, String latitude, String longitude, Date reservationTime,
                           Options options, int auctionTime) {
        String dateString = Utils.convertTimeToString(reservationTime);
        HttpUrl.Builder builder = getBaseUrlHttpsBuilder();
        builder.addPathSegment("estimates");

        RequestBody body = new FormBody.Builder()
                .add("people", "" + people)
                .add("latitude", latitude)
                .add("longitude", longitude)
                .add("reservationTime", dateString)
                .add("options", "" + options.isWifi())
                .add("options", "" + options.isDays())
                .add("options", "" + options.isParking())
                .add("options", "" + options.isSocket())
                .add("auctionTime", ""+auctionTime)
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
