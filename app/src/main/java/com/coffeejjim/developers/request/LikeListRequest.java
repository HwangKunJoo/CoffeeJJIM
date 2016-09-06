package com.coffeejjim.developers.request;

import android.content.Context;
import android.util.Log;

import com.coffeejjim.developers.data.Cafe;
import com.coffeejjim.developers.data.NetworkResult;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

import okhttp3.HttpUrl;
import okhttp3.Request;

/**
 * Created by Tacademy on 2016-09-06.
 */
public class LikeListRequest extends AbstractRequest<NetworkResult<List<Cafe>>> {

    Request request;
    public LikeListRequest(Context context, String pageNo, String rowCount) {
        HttpUrl url = getBaseUrlBuilder()
                .addPathSegment("bookmarks")
                .addQueryParameter("pageNo", pageNo)
                .addQueryParameter("rowCount", rowCount)
                .build();

        request = new Request.Builder()
                .url(url)
                .tag(context)
                .build();
        Log.i("url", url.toString());
    }

    @Override
    protected Type getType() {
        return new TypeToken<NetworkResult<List<Cafe>>>(){}.getType();
    }

    @Override
    public Request getRequest() {
        return request;
    }
}
