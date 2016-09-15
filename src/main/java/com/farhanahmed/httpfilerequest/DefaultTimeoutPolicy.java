package com.farhanahmed.httpfilerequest;

/**
 * Created by farhanahmed on 26/06/16.
 */
public class DefaultTimeoutPolicy implements TimeoutPolicy {
    private int connectTimeout = 15000;
    private int readTimeout = 15000;
    @Override
    public int connectTimeout() {
        return connectTimeout;
    }

    @Override
    public int readTimeout() {
        return readTimeout;
    }

    public void setConnectTimeout(int connectTimeout) {
        this.connectTimeout = connectTimeout;
    }

    public void setReadTimeout(int readTimeout) {
        this.readTimeout = readTimeout;
    }
}
