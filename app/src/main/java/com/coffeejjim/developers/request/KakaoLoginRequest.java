package com.coffeejjim.developers.request;

import android.content.Context;

import com.coffeejjim.developers.data.NetworkResult;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;

import okhttp3.HttpUrl;
import okhttp3.Request;

/**
 * Created by Tacademy on 2016-09-12.
 */
public class KakaoLoginRequest extends AbstractRequest<NetworkResult<Object>> {

    Request mRequest;
    //auth/kakaotalk/token?access_token=21646513&fcmToken=awejflawefwea
    public KakaoLoginRequest(Context context, String access_token, String fcmToken) {
        HttpUrl url = getBaseUrlHttpsBuilder()
                .addPathSegment("auth")
                .addPathSegment("kakao")
                .addPathSegment("token")
                .addQueryParameter("access_token", access_token)
                .addQueryParameter("fcmToken", fcmToken)
                .build();

        mRequest = new Request.Builder()
                .url(url)
                .tag(context)
                .build();
    }
    @Override
    protected Type getType() {
        return new TypeToken<NetworkResult<Object>>(){}.getType();
    }

    @Override
    public Request getRequest() {
        return mRequest;
    }
}
