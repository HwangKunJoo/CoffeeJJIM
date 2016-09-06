package com.coffeejjim.developers.request;

import android.content.Context;

import com.coffeejjim.developers.data.Event;
import com.coffeejjim.developers.data.NetworkResult;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

import okhttp3.HttpUrl;
import okhttp3.Request;

/**
 * Created by Tacademy on 2016-09-06.
 */
public class EventImageRequest extends AbstractRequest<NetworkResult<List<Event>>> {

    Request request;
    public EventImageRequest(Context context) {
        HttpUrl url = getBaseUrlBuilder()
                .addPathSegment("events")
                .build();

        request = new Request.Builder()
                .url(url)
                .tag(context)
                .build();
    }
    @Override
    protected Type getType() {
        return new TypeToken<NetworkResult<List<Event>>>(){}.getType();
    }

    @Override
    public Request getRequest() {
        return request;
    }
}
