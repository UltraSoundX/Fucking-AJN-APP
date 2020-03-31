package com.mob.tools.network;

import com.mob.tools.utils.Data;
import java.io.ByteArrayInputStream;
import java.io.InputStream;

public class ByteArrayPart extends HTTPPart {
    private BufferedByteArrayOutputStream buffer;

    public ByteArrayPart append(byte[] bArr) throws Throwable {
        if (this.buffer == null) {
            this.buffer = new BufferedByteArrayOutputStream(bArr.length);
        }
        this.buffer.write(bArr);
        this.buffer.flush();
        return this;
    }

    /* access modifiers changed from: protected */
    public InputStream getInputStream() throws Throwable {
        if (this.buffer == null) {
            return new ByteArrayInputStream(new byte[0]);
        }
        byte[] buffer2 = this.buffer.getBuffer();
        if (buffer2 == null || this.buffer.size() <= 0) {
            return new ByteArrayInputStream(new byte[0]);
        }
        return new ByteArrayInputStream(buffer2, 0, this.buffer.size());
    }

    public String toString() {
        if (this.buffer == null) {
            return null;
        }
        byte[] buffer2 = this.buffer.getBuffer();
        if (buffer2 != null) {
            return Data.byteToHex(buffer2, 0, this.buffer.size());
        }
        return null;
    }

    /* access modifiers changed from: protected */
    public long length() throws Throwable {
        if (this.buffer == null) {
            return 0;
        }
        return (long) this.buffer.size();
    }
}
