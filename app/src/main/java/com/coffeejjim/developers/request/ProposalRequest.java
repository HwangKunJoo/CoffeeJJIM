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
 * Created by acer on 2016-09-11.
 */
public class ProposalRequest extends AbstractRequest<NetworkResult<Proposal>> {
    Request request;

    public ProposalRequest(Context context, int estimateId, String bidPrice){
        HttpUrl.Builder builder = getBaseUrlBuilder();
        builder.addPathSegment("proposals");

        RequestBody body = new FormBody.Builder()
                .add("estimateId", ""+estimateId)
                .add("bidPrice", bidPrice)
                .build();

        request = new Request.Builder()
                .url(builder.build())
                .post(body)
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
