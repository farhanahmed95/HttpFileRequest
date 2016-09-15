package com.farhanahmed.httpfilerequest;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;

/**
 * Created by farhanahmed on 15/09/2016.
 */
public class ClientUtil {
    private static OkHttpClient client;

    public static void setClient(OkHttpClient client) {
        ClientUtil.client = client;
    }

    public static OkHttpClient getClient() {
        if (client == null)
        {
            return defaultClient();
        }
        return client;
    }

    private static OkHttpClient defaultClient(){
        DefaultTimeoutPolicy policy = new DefaultTimeoutPolicy();
        return client = new OkHttpClient.Builder()
                .connectTimeout(policy.connectTimeout(), TimeUnit.MILLISECONDS)
                .readTimeout(policy.readTimeout(), TimeUnit.MILLISECONDS)
                .build();
    }
}
