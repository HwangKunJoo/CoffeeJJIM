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
 * Created by Tacademy on 2016-09-08.
 */
public class AuctionProcessRequest extends AbstractRequest<NetworkResult<List<Estimate>>> {

    Request request;

    public AuctionProcessRequest(Context context, String pageNo, String rowCount) {
        HttpUrl url = getBaseUrlBuilder()
                .addPathSegment("estimates")
                .addQueryParameter("pageNo", pageNo)
                .addQueryParameter("rowCount", rowCount)
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
