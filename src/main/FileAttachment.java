package com.farhanahmed.httpfilerequest;

import java.io.File;

/**
 * Created by farhanahmed on 14/07/16.
 */
public class FileAttachment extends Attachment {
    public File file;

    public FileAttachment(String name, File file) {
        super(name);
        this.file = file;
    }
}
