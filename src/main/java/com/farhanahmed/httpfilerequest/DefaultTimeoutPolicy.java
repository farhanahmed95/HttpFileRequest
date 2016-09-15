package com.farhanahmed.httpfilerequest;

/**
 * Created by farhanahmed on 26/06/16.
 */
public class DefaultTimeoutPolicy implements TimeoutPolicy {
    @Override
    public int connectTimeout() {
        return 15000;
    }

    @Override
    public int readTimeout() {
        return 15000;
    }
}
