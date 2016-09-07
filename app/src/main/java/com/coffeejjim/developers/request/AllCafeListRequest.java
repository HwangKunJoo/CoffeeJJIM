package com.coffeejjim.developers.request;

import android.content.Context;

import com.coffeejjim.developers.data.Cafe;
import com.coffeejjim.developers.data.NetworkResult;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

import okhttp3.HttpUrl;
import okhttp3.Request;

/**
 * Created by acer on 2016-09-07.
 */
public class AllCafeListRequest extends AbstractRequest<NetworkResult<List<Cafe>>> {

   Request request;

    public AllCafeListRequest(Context context, String pageNo,
                              String rowCount, long latitude, long longitude) {

        HttpUrl url = getBaseUrlBuilder()
                .addPathSegment("cafes")
                .addQueryParameter("pageNo", pageNo)
                .addQueryParameter("rowCount", rowCount)
                .addQueryParameter("latitude", ""+latitude)
                .addQueryParameter("longitude", ""+longitude)
                .build();

        request = new Request.Builder()
                .url(url)
                .tag(context)
                .build();
    }

    @Override
    protected Type getType() {
        return new TypeToken<NetworkResult<List<Cafe>>>() {
        }.getType();
    }

    @Override
    public Request getRequest() {
        return request;
    }
}
