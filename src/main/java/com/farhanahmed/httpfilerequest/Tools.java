package com.farhanahmed.httpfilerequest;

import java.io.File;
import java.net.URLConnection;

/**
 * Created by farhanahmed on 15/09/2016.
 */
public class Tools {
    public static String getMimeType(File file) {
        return URLConnection.guessContentTypeFromName(file.getName());
    }
}
