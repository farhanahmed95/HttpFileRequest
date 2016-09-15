package com.farhanahmed.httpfilerequest.listener;

import com.farhanahmed.httpfilerequest.Response;
import com.farhanahmed.httpfilerequest.listener.Listener;

/**
 * Created by farhanahmed on 15/09/2016.
 */
public abstract class ListenerAdapter implements Listener {
    @Override
    public void onResponse(Response response) {

    }

    @Override
    public void onErrorResponse(Response response) {

    }

    @Override
    public void onError(Exception e) {

    }

    @Override
    public void onProgress(int progress) {

    }
}
