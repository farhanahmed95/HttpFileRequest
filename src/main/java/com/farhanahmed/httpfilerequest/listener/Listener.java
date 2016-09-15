package com.farhanahmed.httpfilerequest.listener;

import com.farhanahmed.httpfilerequest.Response;

/**
 * Created by farhanahmed on 15/09/2016.
 */
public interface Listener {
    void onResponse(com.farhanahmed.httpfilerequest.Response response);

    void onErrorResponse(Response response);

    void onError(Exception e);

    void onProgress(int progress);
}
