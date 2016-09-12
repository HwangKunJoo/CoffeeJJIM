package com.coffeejjim.developers.request;

import android.content.Context;

import com.coffeejjim.developers.data.Estimate;
import com.coffeejjim.developers.data.NetworkResult;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

import okhttp3.HttpUrl;
import okhttp3.Request;

/**
 * Created by acer on 2016-09-12.
 */
public class AuctionListRequest extends AbstractRequest<NetworkResult<List<Estimate>>> {

    Request request;

    public AuctionListRequest(Context context, int type, int year, int month) {
        HttpUrl url = getBaseUrlHttpsBuilder()
                .addPathSegment("estimates")
                .addPathSegment("booked")
                .addQueryParameter("type", "" + type)
                .addQueryParameter("year", "" + year)
                .addQueryParameter("month", "" + month)
                .build();

        request = new Request.Builder()
                .url(url)
                .tag(context)
                .build();
    }

    @Override
    protected Type getType() {
        return new TypeToken<NetworkResult<List<Estimate>>>() {
        }.getType();
    }

    @Override
    public Request getRequest() {
        return request;
    }

}