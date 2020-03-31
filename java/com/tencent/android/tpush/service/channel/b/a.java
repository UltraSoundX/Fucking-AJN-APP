package com.tencent.android.tpush.service.channel.b;

import com.tencent.android.tpush.common.Constants;
import com.tencent.android.tpush.service.channel.exception.IORefusedException;
import com.tencent.android.tpush.service.channel.exception.InnerException;
import com.tencent.android.tpush.service.channel.exception.UnexpectedDataException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/* compiled from: ProGuard */
public class a extends f implements d {
    private static final Pattern k = Pattern.compile("\\A(\\S+) +(\\d+) +(.*)\r\n");
    private static final Pattern l = Pattern.compile("(.*) *: *(.*)\r\n");
    protected StringBuffer a = new StringBuffer();
    protected String b;
    public int c;
    protected String d;
    protected final HashMap<String, String> e = new HashMap<>();
    protected int f = -1;
    protected int g = 0;
    protected int h = -1;
    public final ArrayList<g> i = new ArrayList<>();
    private int m = 0;
    private g n = null;

    public int a(InputStream inputStream) {
        int i2 = 0;
        c();
        if (inputStream.available() != 0) {
            try {
                this.g = 0;
                while (true) {
                    if (!b()) {
                        int i3 = this.g;
                        this.g = i3 + 1;
                        if (i3 > 2) {
                            throw new InnerException("the duration of the current step is too long!");
                        }
                        switch (this.h) {
                            case -3:
                                i2 += d(inputStream);
                                break;
                            case -2:
                                i2 += c(inputStream);
                                break;
                            case -1:
                                i2 += b(inputStream);
                                break;
                            case 0:
                                d();
                                break;
                            default:
                                throw new InnerException("illegal step value!");
                        }
                        if (this.h == 0 || inputStream.available() != 0) {
                        }
                    }
                }
            } catch (IORefusedException e2) {
                com.tencent.android.tpush.b.a.d("Channel.HttpRecvPacket", "read >>> IORefusedException thrown", e2);
            }
        }
        return i2;
    }

    /* access modifiers changed from: 0000 */
    public void a(int i2) {
        if (this.h != i2) {
            this.g = 0;
        }
        this.h = i2;
    }

    /* access modifiers changed from: protected */
    public int b(InputStream inputStream) {
        int available = inputStream.available();
        int i2 = 0;
        while (true) {
            int i3 = available - 1;
            if (available <= 0) {
                return i2;
            }
            int i4 = i2 + 1;
            int read = inputStream.read();
            switch (read) {
                case -1:
                    throw new IOException("the end of stream has been reached!");
                case 10:
                    this.a.append((char) read);
                    int length = this.a.length();
                    if (length >= 4 && "\r\n\r\n".contentEquals(this.a.subSequence(length - 4, length))) {
                        Matcher matcher = k.matcher(this.a.subSequence(0, this.a.length()));
                        if (!matcher.find() || matcher.groupCount() != 3) {
                            throw new UnexpectedDataException("http statusLine can not parsed!");
                        }
                        this.b = matcher.group(1);
                        try {
                            this.c = Integer.parseInt(matcher.group(2).trim());
                            this.d = matcher.group(3);
                            Matcher matcher2 = l.matcher(this.a.subSequence(0, this.a.length()));
                            while (matcher2.find() && matcher2.groupCount() == 2) {
                                this.e.put(matcher2.group(1).toLowerCase(Locale.US), matcher2.group(2));
                            }
                            if (this.e.containsKey("Transfer-Encoding".toLowerCase(Locale.US)) && ((String) this.e.get("Transfer-Encoding".toLowerCase(Locale.US))).equalsIgnoreCase("chunked")) {
                                this.f = -1;
                                a(-3);
                                return i4;
                            } else if (this.e.get("Content-Length".toLowerCase(Locale.US)) != null) {
                                try {
                                    this.f = Integer.parseInt(((String) this.e.get("Content-Length".toLowerCase(Locale.US))).trim());
                                    a(-2);
                                    return i4;
                                } catch (NumberFormatException e2) {
                                    com.tencent.android.tpush.b.a.d(Constants.LogTag, "", e2);
                                    throw new UnexpectedDataException("http Content-Length can not parsed!");
                                }
                            } else {
                                throw new UnexpectedDataException("http Content-Length == null && Transfer-Encoding not equal to 'chunked'!");
                            }
                        } catch (NumberFormatException e3) {
                            com.tencent.android.tpush.b.a.d(Constants.LogTag, "", e3);
                            throw new UnexpectedDataException("http statusLine can not parsed!");
                        }
                    }
                    break;
                default:
                    this.a.append((char) read);
                    break;
            }
            i2 = i4;
            available = i3;
        }
    }

    /* access modifiers changed from: protected */
    public int c(InputStream inputStream) {
        int i2 = 0;
        while (inputStream.available() >= 0) {
            if (this.m > this.f) {
                throw new UnexpectedDataException("readBodyLength > contentLength ?!!");
            } else if (this.m != this.f) {
                if (this.n == null) {
                    this.n = new g();
                    this.n.a(this.j);
                }
                int a2 = this.n.a(inputStream);
                int i3 = i2 + a2;
                this.m = a2 + this.m;
                if (this.n.b()) {
                    this.i.add(this.n);
                    this.n = null;
                }
                if (i2 == i3) {
                    return i3;
                }
                i2 = i3;
            } else if (this.n != null) {
                throw new InnerException("currentRecvPacket != null ?!!");
            } else {
                a(0);
                return i2;
            }
        }
        return i2;
    }

    /* access modifiers changed from: protected */
    public int d(InputStream inputStream) {
        throw new InnerException("not support chunked transfer encoding!");
    }
}
