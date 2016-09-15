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
    private String url;
    private FileAttachment[] files;
    private Listener listener;
    private Callback callback;
    private static OkHttpClient client;
    private Request request;
    private Activity activity;
    private boolean debug = true;

    public void setDebug(boolean debug) {
        this.debug = debug;
    }

    public HttpFileRequest(Activity activity, final String url, final Listener listener, final FileAttachment... files) {
        this.url = url;
        this.files = files;
        this.listener = listener;
        this.activity = activity;

        if (client == null) {
            TimeoutPolicy policy = getTimeoutPolicy();
            if (client() != null)
            {
                client = client();
            }else {
                client = new OkHttpClient.Builder()
                        .connectTimeout(policy.connectTimeout(), TimeUnit.MILLISECONDS)
                        .readTimeout(policy.readTimeout(), TimeUnit.MILLISECONDS)
                        .build();
            }

        }

        makeRequest();
    }

    private void makeRequest() {
        try {
            MultipartBody.Builder builder = new MultipartBody.Builder().setType(MultipartBody.FORM);
            if (getParams().size() > 0)
                for (Map.Entry<String, String> entry : getParams().entrySet()) {
                    if (debug)
                        log("PARAMS", entry.getKey() + " : " + entry.getValue());
                    builder.addFormDataPart(entry.getKey(), entry.getValue());
                }
            if (files != null && files.length > 0)
                for (FileAttachment fileAttachment : files) {
                    if (fileAttachment != null && fileAttachment.file.exists())
                    {
                        String mime = getMimeType(fileAttachment.file);
                        MediaType fileType = MediaType.parse(mime);
                        builder.addFormDataPart(fileAttachment.name, FilenameUtils.getName(fileAttachment.file.getAbsolutePath()), RequestBody.create(fileType, fileAttachment.file));
                        log("addFormDataPart",fileAttachment.name + ", "+FilenameUtils.getName(fileAttachment.file.getAbsolutePath()));
                    }
                }
            MultipartBody body = builder.build();
            ProgressRequestBody requestBody = new ProgressRequestBody(body, new ProgressRequestBody.Listener() {
                @Override
                public void onProgress(final int progress) {
                    activity.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            listener.onProgress(progress);
                        }
                    });
                }
            });

            request = new Request.Builder().url(url).post(requestBody).build();

            callback = new Callback() {
                @Override
                public void onFailure(Call call, final IOException e) {
                    activity.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            listener.onError(e);
                        }
                    });
                }

                @Override
                public void onResponse(Call call, final okhttp3.Response response) {
                    final com.farhanahmed.httpfilerequest.Response res = new com.farhanahmed.httpfilerequest.Response();
                    res.code = response.code();
                    res.message = response.message();
                    try {
                        res.body = response.body().string();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    if (response.code() == 200) {
                        activity.runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                listener.onResponse(res);

                            }
                        });
                    } else if (response.code() != 200) {
                        activity.runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                listener.onErrorResponse(res);

                            }
                        });
                    }

                }
            };
        } catch (Exception e) {
            listener.onError(e);
        }
    }

    private void log(String tag, String msg) {
        if (debug)
        {
            Log.d(tag,msg);
        }
    }

    public Map<String, String> getParams() {
        return new HashMap<>();
    }
    public OkHttpClient client(){
        return null;
    }
    protected TimeoutPolicy getTimeoutPolicy() {
        return new DefaultTimeoutPolicy();
    }

    public Call exec() {

        Call call = null;
        if (request != null && callback != null)
        {
            call = client.newCall(request);
            call.enqueue(callback);
        }
        return call;
    }


    public interface Listener {
        void onResponse(com.farhanahmed.httpfilerequest.Response response);

        void onErrorResponse(Response response);

        void onError(Exception e);

        void onProgress(int progress);
    }

    private final static String getMimeType(File file) {
        String ext = URLConnection.guessContentTypeFromName(file.getName());
        return ext;
    }


}
