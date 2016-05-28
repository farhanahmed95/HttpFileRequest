package com.farhanahmed.httpfilerequest;

/**
 * Created by farhanahmed on 4/29/2016.
 */
public class Response {
    public int code;
    public String message;
    public String body;
    public Response(int code, String message, String body) {
        this.code = code;
        this.message = message;
        this.body = body;
    }


}
