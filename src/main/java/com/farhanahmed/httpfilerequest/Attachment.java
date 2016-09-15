package com.farhanahmed.httpfilerequest;

import java.io.File;

/**
 * Created by farhanahmed on 14/07/16.
 */
public class Attachment {
    public String name;
    public File file;
    public Attachment(String name, File file) {
        this.name = name;
        this.file = file;
    }
}
