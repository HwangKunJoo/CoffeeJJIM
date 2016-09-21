package com.coffeejjim.developers.request;

import android.content.Context;

import com.coffeejjim.developers.data.Estimate;
import com.coffeejjim.developers.data.NetworkResult;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;

import okhttp3.HttpUrl;
import okhttp3.Request;

/**
 * Created by Tacademy on 2016-09-21.
 */

public class BookingInfoRequest extends AbstractRequest<NetworkResult<Estimate>> {

    Request request;

    public BookingInfoRequest(Context context, int estimateId, int proposalId) {
        HttpUrl url = getBaseUrlHttpsBuilder()
                .addPathSegment("estimates")
                .addPathSegment("" + estimateId)
                .addPathSegment("proposalId")
                .addPathSegment("" + proposalId)
                .build();

        request = new Request.Builder()
                .url(url)
                .tag(context)
                .build();
    }

    @Override
    protected Type getType() {
        return new TypeToken<NetworkResult<Estimate>>() {
        }.getType();
    }

    @Override
    public Request getRequest() {
        return request;
    }

}
