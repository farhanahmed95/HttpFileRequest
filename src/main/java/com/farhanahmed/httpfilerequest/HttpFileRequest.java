package com.farhanahmed.httpfilerequest;

import android.app.Activity;
import android.net.Uri;
import android.util.Log;
import android.webkit.MimeTypeMap;

import com.google.common.io.Files;

import org.apache.commons.io.FilenameUtils;

import java.io.File;
import java.io.IOException;
import java.net.URLConnection;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.internal.io.FileSystem;

/**
 * Created by farhanahmed on 24/06/16.
 */
public class HttpFileRequest {
    public RequestManager url(String url){
        return new RequestManager(url);
    }
    public HttpFileRequest client(OkHttpClient client){
        ClientUtil.setClient(client);
        return this;
    }

}
