package com.mob.tools.network;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

public class FilePart extends HTTPPart {
    private File file;

    public void setFile(File file2) {
        this.file = file2;
    }

    public void setFile(String str) {
        this.file = new File(str);
    }

    /* access modifiers changed from: protected */
    public InputStream getInputStream() throws Throwable {
        return new FileInputStream(this.file);
    }

    public String toString() {
        return this.file.toString();
    }

    /* access modifiers changed from: protected */
    public long length() throws Throwable {
        return this.file.length();
    }
}
