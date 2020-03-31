package com.mob.tools.network;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Map;

public interface HttpConnection {
    InputStream getErrorStream() throws IOException;

    Map<String, List<String>> getHeaderFields() throws IOException;

    InputStream getInputStream() throws IOException;

    int getResponseCode() throws IOException;
}
