package com.coffeejjim.developers.request;

import android.content.Context;

import com.coffeejjim.developers.data.NetworkResult;
import com.coffeejjim.developers.data.Proposal;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;

import okhttp3.FormBody;
import okhttp3.HttpUrl;
import okhttp3.Request;
import okhttp3.RequestBody;

/**
 * Created by Tacademy on 2016-09-19.
 */
public class CafeReservationRequest extends AbstractRequest<NetworkResult<Proposal>> {
    Request request;

    public CafeReservationRequest(Context context, int proposalId) {

        HttpUrl.Builder builder = getBaseUrlHttpsBuilder();
        builder.addPathSegment("proposals")
                .addPathSegment(""+proposalId);

        RequestBody body = new FormBody.Builder()
                .add("proposalId", ""+proposalId)
                .build();

        request = new Request.Builder()
                .url(builder.build())
                .put(body)
                .tag(context)
                .build();
    }

    @Override
    protected Type getType() {
        return new TypeToken<NetworkResult<Proposal>>() {
        }.getType();
    }

    @Override
    public Request getRequest() {
        return request;
    }
}
