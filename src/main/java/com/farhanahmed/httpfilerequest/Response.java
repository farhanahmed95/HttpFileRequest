package com.farhanahmed.httpfilerequest;

/**
 * Created by farhanahmed on 27/06/16.
 */
public class Response {
    public int code;
    public String body;
    public String message;

    @Override
    public String toString() {
        return code + " | "+message+" | "+message;
    }
}
