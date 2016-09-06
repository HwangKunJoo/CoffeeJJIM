package com.coffeejjim.developers.request;

import com.coffeejjim.developers.data.NetworkResult;
import com.coffeejjim.developers.data.Proposal;

import java.lang.reflect.Type;

import okhttp3.Request;

/**
 * Created by Tacademy on 2016-09-06.
 */
public class AuctionListRequest extends AbstractRequest<NetworkResult<Proposal>> {
    @Override
    protected Type getType() {
        return null;
    }

    @Override
    public Request getRequest() {
        return null;
    }
}
