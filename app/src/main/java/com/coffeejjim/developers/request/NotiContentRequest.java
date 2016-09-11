package com.coffeejjim.developers.request;

import android.content.Context;

import com.coffeejjim.developers.data.NetworkResult;
import com.coffeejjim.developers.data.Notification;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

import okhttp3.HttpUrl;
import okhttp3.Request;

/**
 * Created by acer on 2016-09-11.
 */
public class NotiContentRequest extends AbstractRequest<NetworkResult<List<Notification>>> {
    Request request;

    public NotiContentRequest(Context context, String pageNo, String rowCount) {
        HttpUrl url = getBaseUrlBuilder()
                .addPathSegment("notices")
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
        return new TypeToken<NetworkResult<List<Notification>>>() {
        }.getType();
    }

    @Override
    public Request getRequest() {
        return request;
    }
}
