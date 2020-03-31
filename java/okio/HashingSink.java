package okio;

import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public final class HashingSink extends ForwardingSink {
    private final MessageDigest messageDigest;

    public static HashingSink md5(Sink sink) {
        return new HashingSink(sink, "MD5");
    }

    public static HashingSink sha1(Sink sink) {
        return new HashingSink(sink, "SHA-1");
    }

    public static HashingSink sha256(Sink sink) {
        return new HashingSink(sink, "SHA-256");
    }

    private HashingSink(Sink sink, String str) {
        super(sink);
        try {
            this.messageDigest = MessageDigest.getInstance(str);
        } catch (NoSuchAlgorithmException e) {
            throw new AssertionError();
        }
    }

    public void write(Buffer buffer, long j) throws IOException {
        long j2 = 0;
        Util.checkOffsetAndCount(buffer.size, 0, j);
        Segment segment = buffer.head;
        while (j2 < j) {
            int min = (int) Math.min(j - j2, (long) (segment.limit - segment.pos));
            this.messageDigest.update(segment.data, segment.pos, min);
            j2 += (long) min;
            segment = segment.next;
        }
        super.write(buffer, j);
    }

    public ByteString hash() {
        return ByteString.of(this.messageDigest.digest());
    }
}