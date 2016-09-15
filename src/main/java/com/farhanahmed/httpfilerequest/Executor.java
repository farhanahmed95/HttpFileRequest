package com.farhanahmed.httpfilerequest;

import okhttp3.Call;

/**
 * Created by farhanahmed on 15/09/2016.
 */
public interface Executor {
    Executor connectTimeOut(int time);
    Executor readTimeOut(int time);
    Call run();
}
