package com.coffeejjim.developers.request;

import android.content.Context;

import com.coffeejjim.developers.data.NetworkResult;
import com.coffeejjim.developers.data.Proposal;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

import okhttp3.HttpUrl;
import okhttp3.Request;

/**
 * Created by Tacademy on 2016-09-06.
 */
public class CafeReservationRequest extends AbstractRequest<NetworkResult<List<Proposal>>> {

    Request request;
    public CafeReservationRequest(Context context, String pageNo, String rowCount) {
        HttpUrl url = getBaseUrlBuilder()
                .addPathSegment("proposals")
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
        return new TypeToken<NetworkResult<Proposal>>(){}.getType();
    }

    @Override
    public Request getRequest() {
        return request;
    }
}
