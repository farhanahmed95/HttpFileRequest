package com.farhanahmed.httpfilerequest;

import android.view.View;

import com.farhanahmed.httpfilerequest.listener.Listener;

import java.io.File;
import java.util.Map;

/**
 * Created by farhanahmed on 15/09/2016.
 */
public interface Request {
    Request add(String fileName,File file);
    Request setParams(Map<String,String> formData);
    Executor listener(Listener listener);
}
