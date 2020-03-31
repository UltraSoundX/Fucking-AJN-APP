package com.baidu.location.a;

import android.app.ActivityManager;
import android.app.ActivityManager.RunningAppProcessInfo;
import android.content.Context;
import android.location.Location;
import android.os.Handler;
import com.baidu.location.Jni;
import com.baidu.location.f;
import com.baidu.location.g.b;
import com.baidu.location.g.e;
import com.baidu.location.g.i;
import com.baidu.location.g.j;
import com.baidu.mobstat.Config;
import com.tencent.android.tpush.common.Constants;
import com.tencent.smtt.sdk.TbsListener.ErrorCode;
import java.io.BufferedOutputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.RandomAccessFile;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Random;
import java.util.UUID;
import net.sf.json.util.JSONUtils;
import org.json.JSONException;
import org.json.JSONObject;

public class d {
    public static String f = "0";
    private static d j = null;
    private a A;
    private boolean B;
    private boolean C;
    private int D;
    private float E;
    private float F;
    private long G;
    private int H;
    private Handler I;
    private byte[] J;
    private byte[] K;
    private int L;
    private List<Byte> M;
    private boolean N;
    long a;
    Location b;
    Location c;
    StringBuilder d;
    long e;
    int g;
    double h;
    double i;
    private int k;
    private double l;
    private String m;
    private int n;
    private int o;
    private int p;

    /* renamed from: q reason: collision with root package name */
    private int f369q;
    private double r;
    private double s;
    private double t;
    private int u;
    private int v;
    private int w;
    private int x;
    private int y;
    private long z;

    class a extends e {
        String a;

        public a() {
            this.a = null;
            this.k = new HashMap();
        }

        public void a() {
            this.h = "http://loc.map.baidu.com/cc.php";
            String a2 = Jni.a(this.a);
            this.a = null;
            this.k.put("q", a2);
        }

        public void a(String str) {
            this.a = str;
            e();
        }

        public void a(boolean z) {
            if (z && this.j != null) {
                try {
                    JSONObject jSONObject = new JSONObject(this.j);
                    jSONObject.put("prod", b.d);
                    jSONObject.put(Config.DEVICE_UPTIME, System.currentTimeMillis());
                    d.this.e(jSONObject.toString());
                } catch (Exception e) {
                }
            }
            if (this.k != null) {
                this.k.clear();
            }
        }
    }

    private d() {
        this.k = 1;
        this.l = 0.699999988079071d;
        this.m = "3G|4G";
        this.n = 1;
        this.o = 307200;
        this.p = 15;
        this.f369q = 1;
        this.r = 3.5d;
        this.s = 3.0d;
        this.t = 0.5d;
        this.u = ErrorCode.ERROR_CODE_LOAD_BASE;
        this.v = 60;
        this.w = 0;
        this.x = 60;
        this.y = 0;
        this.z = 0;
        this.A = null;
        this.B = false;
        this.C = false;
        this.D = 0;
        this.E = 0.0f;
        this.F = 0.0f;
        this.G = 0;
        this.H = ErrorCode.INFO_CODE_MINIQB;
        this.a = 0;
        this.b = null;
        this.c = null;
        this.d = null;
        this.e = 0;
        this.I = null;
        this.J = new byte[4];
        this.K = null;
        this.L = 0;
        this.M = null;
        this.N = false;
        this.g = 0;
        this.h = 116.22345545d;
        this.i = 40.245667323d;
        this.I = new Handler();
    }

    public static d a() {
        if (j == null) {
            j = new d();
        }
        return j;
    }

    /* access modifiers changed from: private */
    public String a(File file, String str) {
        String uuid = UUID.randomUUID().toString();
        String str2 = "--";
        String str3 = "\r\n";
        String str4 = "multipart/form-data";
        try {
            HttpURLConnection httpURLConnection = (HttpURLConnection) new URL(str).openConnection();
            httpURLConnection.setReadTimeout(10000);
            httpURLConnection.setConnectTimeout(10000);
            httpURLConnection.setDoInput(true);
            httpURLConnection.setDoOutput(true);
            httpURLConnection.setUseCaches(false);
            httpURLConnection.setRequestMethod("POST");
            httpURLConnection.setRequestProperty("Charset", "utf-8");
            httpURLConnection.setRequestProperty("connection", "close");
            httpURLConnection.setRequestProperty("Content-Type", str4 + ";boundary=" + uuid);
            if (file != null && file.exists()) {
                OutputStream outputStream = httpURLConnection.getOutputStream();
                DataOutputStream dataOutputStream = new DataOutputStream(outputStream);
                StringBuffer stringBuffer = new StringBuffer();
                stringBuffer.append(str2);
                stringBuffer.append(uuid);
                stringBuffer.append(str3);
                stringBuffer.append("Content-Disposition: form-data; name=\"location_dat\"; filename=\"" + file.getName() + JSONUtils.DOUBLE_QUOTE + str3);
                stringBuffer.append("Content-Type: application/octet-stream; charset=utf-8" + str3);
                stringBuffer.append(str3);
                dataOutputStream.write(stringBuffer.toString().getBytes());
                FileInputStream fileInputStream = new FileInputStream(file);
                byte[] bArr = new byte[1024];
                while (true) {
                    int read = fileInputStream.read(bArr);
                    if (read == -1) {
                        break;
                    }
                    dataOutputStream.write(bArr, 0, read);
                }
                fileInputStream.close();
                dataOutputStream.write(str3.getBytes());
                dataOutputStream.write((str2 + uuid + str2 + str3).getBytes());
                dataOutputStream.flush();
                dataOutputStream.close();
                int responseCode = httpURLConnection.getResponseCode();
                outputStream.close();
                httpURLConnection.disconnect();
                this.y += ErrorCode.INFO_CODE_BASE;
                c(this.y);
                if (responseCode == 200) {
                    return "1";
                }
            }
        } catch (IOException | MalformedURLException e2) {
        }
        return "0";
    }

    private boolean a(String str, Context context) {
        boolean z2;
        boolean z3 = false;
        try {
            List<RunningAppProcessInfo> runningAppProcesses = ((ActivityManager) context.getSystemService(Constants.FLAG_ACTIVITY_NAME)).getRunningAppProcesses();
            if (runningAppProcesses != null) {
                for (RunningAppProcessInfo runningAppProcessInfo : runningAppProcesses) {
                    if (runningAppProcessInfo.processName.equals(str)) {
                        int i2 = runningAppProcessInfo.importance;
                        if (i2 == 200 || i2 == 100) {
                            z2 = true;
                            z3 = z2;
                        }
                    }
                    z2 = z3;
                    z3 = z2;
                }
            }
        } catch (Exception e2) {
        }
        return z3;
    }

    private byte[] a(int i2) {
        return new byte[]{(byte) (i2 & 255), (byte) ((65280 & i2) >> 8), (byte) ((16711680 & i2) >> 16), (byte) ((-16777216 & i2) >> 24)};
    }

    private byte[] a(String str) {
        int i2 = 0;
        if (str == null) {
            return null;
        }
        byte[] bytes = str.getBytes();
        byte nextInt = (byte) new Random().nextInt(255);
        byte nextInt2 = (byte) new Random().nextInt(255);
        byte[] bArr = new byte[(bytes.length + 2)];
        int length = bytes.length;
        int i3 = 0;
        while (i2 < length) {
            int i4 = i3 + 1;
            bArr[i3] = (byte) (bytes[i2] ^ nextInt);
            i2++;
            i3 = i4;
        }
        int i5 = i3 + 1;
        bArr[i3] = nextInt;
        int i6 = i5 + 1;
        bArr[i5] = nextInt2;
        return bArr;
    }

    private String b(String str) {
        Calendar instance = Calendar.getInstance();
        return String.format(str, new Object[]{Integer.valueOf(instance.get(1)), Integer.valueOf(instance.get(2) + 1), Integer.valueOf(instance.get(5))});
    }

    private void b(int i2) {
        byte[] a2 = a(i2);
        for (int i3 = 0; i3 < 4; i3++) {
            this.M.add(Byte.valueOf(a2[i3]));
        }
    }

    /* access modifiers changed from: private */
    public void b(Location location) {
        c(location);
        h();
    }

    private void c() {
        if (!this.N) {
            this.N = true;
            d(b.d);
            j();
            d();
        }
    }

    private void c(int i2) {
        if (i2 != 0) {
            try {
                File file = new File(i.a + "/grtcf.dat");
                if (!file.exists()) {
                    File file2 = new File(i.a);
                    if (!file2.exists()) {
                        file2.mkdirs();
                    }
                    if (file.createNewFile()) {
                        RandomAccessFile randomAccessFile = new RandomAccessFile(file, "rw");
                        randomAccessFile.seek(2);
                        randomAccessFile.writeInt(0);
                        randomAccessFile.seek(8);
                        byte[] bytes = "1980_01_01:0".getBytes();
                        randomAccessFile.writeInt(bytes.length);
                        randomAccessFile.write(bytes);
                        randomAccessFile.seek(200);
                        randomAccessFile.writeBoolean(false);
                        randomAccessFile.seek(800);
                        randomAccessFile.writeBoolean(false);
                        randomAccessFile.close();
                    } else {
                        return;
                    }
                }
                RandomAccessFile randomAccessFile2 = new RandomAccessFile(file, "rw");
                randomAccessFile2.seek(8);
                byte[] bytes2 = (b("%d_%02d_%02d") + Config.TRACE_TODAY_VISIT_SPLIT + i2).getBytes();
                randomAccessFile2.writeInt(bytes2.length);
                randomAccessFile2.write(bytes2);
                randomAccessFile2.close();
            } catch (Exception e2) {
            }
        }
    }

    private void c(Location location) {
        if (System.currentTimeMillis() - this.a >= ((long) this.H) && location != null) {
            if (location != null && location.hasSpeed() && location.getSpeed() > this.E) {
                this.E = location.getSpeed();
            }
            try {
                if (this.M == null) {
                    this.M = new ArrayList();
                    i();
                    d(location);
                } else {
                    e(location);
                }
            } catch (Exception e2) {
            }
            this.L++;
        }
    }

    private void c(String str) {
        if (str != null) {
            try {
                JSONObject jSONObject = new JSONObject(str);
                if (jSONObject.has("on")) {
                    this.k = jSONObject.getInt("on");
                }
                if (jSONObject.has("bash")) {
                    this.l = jSONObject.getDouble("bash");
                }
                if (jSONObject.has("net")) {
                    this.m = jSONObject.getString("net");
                }
                if (jSONObject.has("tcon")) {
                    this.n = jSONObject.getInt("tcon");
                }
                if (jSONObject.has("tcsh")) {
                    this.o = jSONObject.getInt("tcsh");
                }
                if (jSONObject.has("per")) {
                    this.p = jSONObject.getInt("per");
                }
                if (jSONObject.has("chdron")) {
                    this.f369q = jSONObject.getInt("chdron");
                }
                if (jSONObject.has("spsh")) {
                    this.r = jSONObject.getDouble("spsh");
                }
                if (jSONObject.has("acsh")) {
                    this.s = jSONObject.getDouble("acsh");
                }
                if (jSONObject.has("stspsh")) {
                    this.t = jSONObject.getDouble("stspsh");
                }
                if (jSONObject.has("drstsh")) {
                    this.u = jSONObject.getInt("drstsh");
                }
                if (jSONObject.has("stper")) {
                    this.v = jSONObject.getInt("stper");
                }
                if (jSONObject.has("nondron")) {
                    this.w = jSONObject.getInt("nondron");
                }
                if (jSONObject.has("nondrper")) {
                    this.x = jSONObject.getInt("nondrper");
                }
                if (jSONObject.has(Config.DEVICE_UPTIME)) {
                    this.z = jSONObject.getLong(Config.DEVICE_UPTIME);
                }
                k();
            } catch (JSONException e2) {
            }
        }
    }

    private void d() {
        String str = null;
        if (0 == 0) {
            str = "7.6.2";
        }
        String[] split = str.split("\\.");
        int length = split.length;
        this.J[0] = 0;
        this.J[1] = 0;
        this.J[2] = 0;
        this.J[3] = 0;
        if (length >= 4) {
            length = 4;
        }
        int i2 = 0;
        while (i2 < length) {
            try {
                this.J[i2] = (byte) (Integer.valueOf(split[i2]).intValue() & 255);
                i2++;
            } catch (Exception e2) {
            }
        }
        this.K = a(b.d + Config.TRACE_TODAY_VISIT_SPLIT + b.a().b);
    }

    private void d(Location location) {
        char c2 = 0;
        this.e = System.currentTimeMillis();
        b((int) (location.getTime() / 1000));
        b((int) (location.getLongitude() * 1000000.0d));
        b((int) (location.getLatitude() * 1000000.0d));
        char c3 = location.hasBearing() ? (char) 0 : 1;
        if (!location.hasSpeed()) {
            c2 = 1;
        }
        if (c3 > 0) {
            this.M.add(Byte.valueOf(32));
        } else {
            this.M.add(Byte.valueOf((byte) (((byte) (((int) (location.getBearing() / 15.0f)) & 255)) & -33)));
        }
        if (c2 > 0) {
            this.M.add(Byte.valueOf(Byte.MIN_VALUE));
        } else {
            this.M.add(Byte.valueOf((byte) (((byte) (((int) ((((double) location.getSpeed()) * 3.6d) / 4.0d)) & 255)) & Byte.MAX_VALUE)));
        }
        this.b = location;
    }

    private void d(String str) {
        int i2 = 1;
        try {
            File file = new File(i.a + "/grtcf.dat");
            if (file.exists()) {
                RandomAccessFile randomAccessFile = new RandomAccessFile(file, "rw");
                randomAccessFile.seek(2);
                int readInt = randomAccessFile.readInt();
                randomAccessFile.seek(8);
                int readInt2 = randomAccessFile.readInt();
                byte[] bArr = new byte[readInt2];
                randomAccessFile.read(bArr, 0, readInt2);
                String str2 = new String(bArr);
                if (str2.contains(b("%d_%02d_%02d")) && str2.contains(Config.TRACE_TODAY_VISIT_SPLIT)) {
                    try {
                        String[] split = str2.split(Config.TRACE_TODAY_VISIT_SPLIT);
                        if (split.length > 1) {
                            this.y = Integer.valueOf(split[1]).intValue();
                        }
                    } catch (Exception e2) {
                    }
                }
                while (true) {
                    if (i2 > readInt) {
                        break;
                    }
                    randomAccessFile.seek((long) (i2 * 2048));
                    int readInt3 = randomAccessFile.readInt();
                    byte[] bArr2 = new byte[readInt3];
                    randomAccessFile.read(bArr2, 0, readInt3);
                    String str3 = new String(bArr2);
                    if (str != null && str3.contains(str)) {
                        c(str3);
                        break;
                    }
                    i2++;
                }
                randomAccessFile.close();
            }
        } catch (Exception e3) {
        }
    }

    private void e(Location location) {
        if (location != null) {
            int longitude = (int) ((location.getLongitude() - this.b.getLongitude()) * 1000000.0d);
            int latitude = (int) ((location.getLatitude() - this.b.getLatitude()) * 1000000.0d);
            char c2 = location.hasBearing() ? (char) 0 : 1;
            char c3 = location.hasSpeed() ? (char) 0 : 1;
            char c4 = longitude > 0 ? (char) 0 : 1;
            int abs = Math.abs(longitude);
            char c5 = latitude > 0 ? (char) 0 : 1;
            int abs2 = Math.abs(latitude);
            if (this.L > 1) {
                this.c = null;
                this.c = this.b;
            }
            this.b = location;
            if (this.b != null && this.c != null && this.b.getTime() > this.c.getTime() && this.b.getTime() - this.c.getTime() < Config.BPLUS_DELAY_TIME) {
                long time = this.b.getTime() - this.c.getTime();
                float[] fArr = new float[2];
                Location.distanceBetween(this.b.getAltitude(), this.b.getLongitude(), this.c.getLatitude(), this.c.getLongitude(), fArr);
                double speed = (double) ((2.0f * (fArr[0] - (this.c.getSpeed() * ((float) time)))) / ((float) (time * time)));
                if (speed > ((double) this.F)) {
                    this.F = (float) speed;
                }
            }
            this.M.add(Byte.valueOf((byte) (abs & 255)));
            this.M.add(Byte.valueOf((byte) ((65280 & abs) >> 8)));
            this.M.add(Byte.valueOf((byte) (abs2 & 255)));
            this.M.add(Byte.valueOf((byte) ((65280 & abs2) >> 8)));
            if (c2 > 0) {
                byte b2 = 32;
                if (c5 > 0) {
                    b2 = (byte) 96;
                }
                if (c4 > 0) {
                    b2 = (byte) (b2 | Byte.MIN_VALUE);
                }
                this.M.add(Byte.valueOf(b2));
            } else {
                byte bearing = (byte) (((byte) (((int) (location.getBearing() / 15.0f)) & 255)) & 31);
                if (c5 > 0) {
                    bearing = (byte) (bearing | 64);
                }
                if (c4 > 0) {
                    bearing = (byte) (bearing | Byte.MIN_VALUE);
                }
                this.M.add(Byte.valueOf(bearing));
            }
            if (c3 > 0) {
                this.M.add(Byte.valueOf(Byte.MIN_VALUE));
            } else {
                this.M.add(Byte.valueOf((byte) (((byte) (((int) ((((double) location.getSpeed()) * 3.6d) / 4.0d)) & 255)) & Byte.MAX_VALUE)));
            }
        }
    }

    /* access modifiers changed from: private */
    public void e(String str) {
        try {
            File file = new File(i.a + "/grtcf.dat");
            if (!file.exists()) {
                File file2 = new File(i.a);
                if (!file2.exists()) {
                    file2.mkdirs();
                }
                if (file.createNewFile()) {
                    RandomAccessFile randomAccessFile = new RandomAccessFile(file, "rw");
                    randomAccessFile.seek(2);
                    randomAccessFile.writeInt(0);
                    randomAccessFile.seek(8);
                    byte[] bytes = "1980_01_01:0".getBytes();
                    randomAccessFile.writeInt(bytes.length);
                    randomAccessFile.write(bytes);
                    randomAccessFile.seek(200);
                    randomAccessFile.writeBoolean(false);
                    randomAccessFile.seek(800);
                    randomAccessFile.writeBoolean(false);
                    randomAccessFile.close();
                } else {
                    return;
                }
            }
            RandomAccessFile randomAccessFile2 = new RandomAccessFile(file, "rw");
            randomAccessFile2.seek(2);
            int readInt = randomAccessFile2.readInt();
            int i2 = 1;
            while (i2 <= readInt) {
                randomAccessFile2.seek((long) (i2 * 2048));
                int readInt2 = randomAccessFile2.readInt();
                byte[] bArr = new byte[readInt2];
                randomAccessFile2.read(bArr, 0, readInt2);
                if (new String(bArr).contains(b.d)) {
                    break;
                }
                i2++;
            }
            if (i2 >= readInt) {
                randomAccessFile2.seek(2);
                randomAccessFile2.writeInt(i2);
            }
            randomAccessFile2.seek((long) (i2 * 2048));
            byte[] bytes2 = str.getBytes();
            randomAccessFile2.writeInt(bytes2.length);
            randomAccessFile2.write(bytes2);
            randomAccessFile2.close();
        } catch (Exception e2) {
        }
    }

    /* JADX WARNING: type inference failed for: r2v0 */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Removed duplicated region for block: B:34:0x006c A[SYNTHETIC, Splitter:B:34:0x006c] */
    /* JADX WARNING: Removed duplicated region for block: B:37:0x0071 A[Catch:{ Exception -> 0x007a }] */
    /* JADX WARNING: Removed duplicated region for block: B:39:0x0076 A[Catch:{ Exception -> 0x007a }] */
    /* JADX WARNING: Unknown variable types count: 1 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private boolean e() {
        /*
            r7 = this;
            r2 = 0
            r3 = 0
            r4 = 0
            r0 = 0
            java.io.File r5 = new java.io.File     // Catch:{ Exception -> 0x0053, all -> 0x0067 }
            java.lang.StringBuilder r1 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x0053, all -> 0x0067 }
            r1.<init>()     // Catch:{ Exception -> 0x0053, all -> 0x0067 }
            java.lang.String r6 = com.baidu.location.g.j.h()     // Catch:{ Exception -> 0x0053, all -> 0x0067 }
            java.lang.StringBuilder r1 = r1.append(r6)     // Catch:{ Exception -> 0x0053, all -> 0x0067 }
            java.lang.String r6 = java.io.File.separator     // Catch:{ Exception -> 0x0053, all -> 0x0067 }
            java.lang.StringBuilder r1 = r1.append(r6)     // Catch:{ Exception -> 0x0053, all -> 0x0067 }
            java.lang.String r6 = "gflk.dat"
            java.lang.StringBuilder r1 = r1.append(r6)     // Catch:{ Exception -> 0x0053, all -> 0x0067 }
            java.lang.String r1 = r1.toString()     // Catch:{ Exception -> 0x0053, all -> 0x0067 }
            r5.<init>(r1)     // Catch:{ Exception -> 0x0053, all -> 0x0067 }
            boolean r1 = r5.exists()     // Catch:{ Exception -> 0x0053, all -> 0x0067 }
            if (r1 != 0) goto L_0x002f
            r5.createNewFile()     // Catch:{ Exception -> 0x0053, all -> 0x0067 }
        L_0x002f:
            if (r2 != 0) goto L_0x0085
            java.io.RandomAccessFile r1 = new java.io.RandomAccessFile     // Catch:{ Exception -> 0x0053, all -> 0x0067 }
            java.lang.String r6 = "rw"
            r1.<init>(r5, r6)     // Catch:{ Exception -> 0x0053, all -> 0x0067 }
            java.nio.channels.FileChannel r3 = r1.getChannel()     // Catch:{ Exception -> 0x0081, all -> 0x007c }
            java.nio.channels.FileLock r2 = r3.tryLock()     // Catch:{ Exception -> 0x0050, all -> 0x007f }
        L_0x0040:
            if (r2 == 0) goto L_0x0045
            r2.release()     // Catch:{ Exception -> 0x0083 }
        L_0x0045:
            if (r3 == 0) goto L_0x004a
            r3.close()     // Catch:{ Exception -> 0x0083 }
        L_0x004a:
            if (r1 == 0) goto L_0x004f
            r1.close()     // Catch:{ Exception -> 0x0083 }
        L_0x004f:
            return r0
        L_0x0050:
            r0 = move-exception
            r0 = 1
            goto L_0x0040
        L_0x0053:
            r1 = move-exception
            r1 = r2
        L_0x0055:
            if (r2 == 0) goto L_0x005a
            r4.release()     // Catch:{ Exception -> 0x0065 }
        L_0x005a:
            if (r2 == 0) goto L_0x005f
            r3.close()     // Catch:{ Exception -> 0x0065 }
        L_0x005f:
            if (r1 == 0) goto L_0x004f
            r1.close()     // Catch:{ Exception -> 0x0065 }
            goto L_0x004f
        L_0x0065:
            r1 = move-exception
            goto L_0x004f
        L_0x0067:
            r0 = move-exception
            r1 = r2
            r3 = r2
        L_0x006a:
            if (r2 == 0) goto L_0x006f
            r4.release()     // Catch:{ Exception -> 0x007a }
        L_0x006f:
            if (r3 == 0) goto L_0x0074
            r3.close()     // Catch:{ Exception -> 0x007a }
        L_0x0074:
            if (r1 == 0) goto L_0x0079
            r1.close()     // Catch:{ Exception -> 0x007a }
        L_0x0079:
            throw r0
        L_0x007a:
            r1 = move-exception
            goto L_0x0079
        L_0x007c:
            r0 = move-exception
            r3 = r2
            goto L_0x006a
        L_0x007f:
            r0 = move-exception
            goto L_0x006a
        L_0x0081:
            r5 = move-exception
            goto L_0x0055
        L_0x0083:
            r1 = move-exception
            goto L_0x004f
        L_0x0085:
            r1 = r2
            r3 = r2
            goto L_0x0040
        */
        throw new UnsupportedOperationException("Method not decompiled: com.baidu.location.a.d.e():boolean");
    }

    private boolean f() {
        if (this.B) {
            if (this.C) {
                if (((double) this.E) < this.t) {
                    this.D += this.p;
                    if (this.D <= this.u || System.currentTimeMillis() - this.G > ((long) (this.v * 1000))) {
                        return true;
                    }
                } else {
                    this.D = 0;
                    this.C = false;
                    return true;
                }
            } else if (((double) this.E) >= this.t) {
                return true;
            } else {
                this.C = true;
                this.D = 0;
                this.D += this.p;
                return true;
            }
        } else if (((double) this.E) >= this.r || ((double) this.F) >= this.s) {
            this.B = true;
            return true;
        } else if (this.w == 1 && System.currentTimeMillis() - this.G > ((long) (this.x * 1000))) {
            return true;
        }
        return false;
    }

    private void g() {
        this.M = null;
        this.e = 0;
        this.L = 0;
        this.b = null;
        this.c = null;
        this.E = 0.0f;
        this.F = 0.0f;
    }

    private void h() {
        if (this.e != 0 && System.currentTimeMillis() - this.e >= ((long) (this.p * 1000))) {
            if (f.c().getSharedPreferences("loc_navi_mode", 4).getBoolean("is_navi_on", false)) {
                g();
            } else if (this.n != 1 || f()) {
                if (!b.d.equals("com.ubercab.driver")) {
                    if (!a(b.d, f.c())) {
                        g();
                        return;
                    }
                } else if (e()) {
                    g();
                    return;
                }
                if (this.M != null) {
                    int size = this.M.size();
                    this.M.set(0, Byte.valueOf((byte) (size & 255)));
                    this.M.set(1, Byte.valueOf((byte) ((65280 & size) >> 8)));
                    this.M.set(3, Byte.valueOf((byte) (this.L & 255)));
                    byte[] bArr = new byte[size];
                    for (int i2 = 0; i2 < size; i2++) {
                        bArr[i2] = ((Byte) this.M.get(i2)).byteValue();
                    }
                    File file = new File(j.j(), "baidu/tempdata");
                    if (!file.exists()) {
                        file.mkdirs();
                    }
                    if (file.exists()) {
                        File file2 = new File(file, "intime.dat");
                        if (file2.exists()) {
                            file2.delete();
                        }
                        try {
                            BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(new FileOutputStream(file2));
                            bufferedOutputStream.write(bArr);
                            bufferedOutputStream.flush();
                            bufferedOutputStream.close();
                            new f(this).start();
                        } catch (Exception e2) {
                        }
                    }
                    g();
                    this.G = System.currentTimeMillis();
                }
            } else {
                g();
            }
        }
    }

    private void i() {
        this.M.add(Byte.valueOf(0));
        this.M.add(Byte.valueOf(0));
        if (f.equals("0")) {
            this.M.add(Byte.valueOf(-82));
        } else {
            this.M.add(Byte.valueOf(-66));
        }
        this.M.add(Byte.valueOf(0));
        this.M.add(Byte.valueOf(this.J[0]));
        this.M.add(Byte.valueOf(this.J[1]));
        this.M.add(Byte.valueOf(this.J[2]));
        this.M.add(Byte.valueOf(this.J[3]));
        this.M.add(Byte.valueOf((byte) ((r1 + 1) & 255)));
        for (byte valueOf : this.K) {
            this.M.add(Byte.valueOf(valueOf));
        }
    }

    private void j() {
        if (System.currentTimeMillis() - this.z > 86400000) {
            if (this.A == null) {
                this.A = new a();
            }
            StringBuffer stringBuffer = new StringBuffer();
            stringBuffer.append(b.a().a(false));
            stringBuffer.append(a.a().d());
            this.A.a(stringBuffer.toString());
        }
        k();
    }

    private void k() {
    }

    public void a(Location location) {
        if (!this.N) {
            c();
        }
        boolean z2 = ((double) com.baidu.location.b.d.a().f()) < this.l * 100.0d;
        if (this.k == 1 && z2 && this.m.contains(com.baidu.location.e.d.a(com.baidu.location.e.b.a().e()))) {
            if (this.n != 1 || this.y <= this.o) {
                this.I.post(new e(this, location));
            }
        }
    }

    public void b() {
        if (this.N) {
            this.N = false;
            g();
        }
    }
}
