package com.mob.tools.network;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;

public class MultiPart extends HTTPPart {
    private ArrayList<HTTPPart> parts = new ArrayList<>();

    public MultiPart append(HTTPPart hTTPPart) throws Throwable {
        this.parts.add(hTTPPart);
        return this;
    }

    /* access modifiers changed from: protected */
    public InputStream getInputStream() throws Throwable {
        MultiPartInputStream multiPartInputStream = new MultiPartInputStream();
        Iterator it = this.parts.iterator();
        while (it.hasNext()) {
            multiPartInputStream.addInputStream(((HTTPPart) it.next()).getInputStream());
        }
        return multiPartInputStream;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        Iterator it = this.parts.iterator();
        while (it.hasNext()) {
            sb.append(((HTTPPart) it.next()).toString());
        }
        return sb.toString();
    }

    /* access modifiers changed from: protected */
    public long length() throws Throwable {
        long j = 0;
        Iterator it = this.parts.iterator();
        while (true) {
            long j2 = j;
            if (!it.hasNext()) {
                return j2;
            }
            j = ((HTTPPart) it.next()).length() + j2;
        }
    }
}
