package com.farhanahmed.httpfilerequest;

/**
 * Created by farhanahmed on 26/06/16.
 */
public interface TimeoutPolicy {
    int connectTimeout();
    int readTimeout();
}
