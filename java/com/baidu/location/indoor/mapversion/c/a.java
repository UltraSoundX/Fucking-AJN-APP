package com.baidu.location.indoor.mapversion.c;

import android.content.Context;
import android.location.Location;
import com.baidu.mobstat.Config;
import com.tencent.mid.sotrage.StorageInterface;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Serializable;
import java.lang.reflect.Array;
import java.math.BigInteger;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel.MapMode;
import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.HashMap;

public class a {
    private static a a;
    /* access modifiers changed from: private */
    public c b;
    /* access modifiers changed from: private */
    public String c;
    /* access modifiers changed from: private */
    public boolean d = false;
    /* access modifiers changed from: private */
    public String e = Config.EVENT_VIEW_RES_NAME;
    private b f;
    private String g = "gcj02";
    private HashMap<String, d> h = new HashMap<>();
    private HashMap<String, double[][]> i = new HashMap<>();
    private d j = null;

    /* renamed from: com.baidu.location.indoor.mapversion.c.a$a reason: collision with other inner class name */
    public static class C0034a {
        public double a;
        public double b;
        public double c;
        public double d;
        public double e;
        public double f;
        public double g;
        public double h;

        public C0034a(String str) {
            a(str);
        }

        public void a(String str) {
            String[] split = str.trim().split("\\|");
            this.a = Double.valueOf(split[0]).doubleValue();
            this.b = Double.valueOf(split[1]).doubleValue();
            this.c = Double.valueOf(split[2]).doubleValue();
            this.d = Double.valueOf(split[3]).doubleValue();
            this.e = Double.valueOf(split[4]).doubleValue();
            this.f = Double.valueOf(split[5]).doubleValue();
            this.g = Double.valueOf(split[6]).doubleValue();
            this.h = Double.valueOf(split[7]).doubleValue();
        }
    }

    private class b extends Thread {
        private String b;
        private String c;

        public b(String str, String str2) {
            this.b = str;
            this.c = str2;
        }

        public void run() {
            String str;
            boolean z;
            try {
                File file = new File(a.this.e);
                if (!file.exists() || !file.isDirectory()) {
                    file.mkdirs();
                }
                HttpURLConnection httpURLConnection = (HttpURLConnection) new URL("http://loc.map.baidu.com/cfgs/indoorloc/indoorroadnet").openConnection();
                httpURLConnection.setDoInput(true);
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.addRequestProperty("Accept-encoding", "gzip");
                httpURLConnection.getOutputStream().write(("bldg=" + this.b + "&md5=" + (this.c == null ? "null" : this.c)).getBytes());
                int responseCode = httpURLConnection.getResponseCode();
                if (responseCode == 200) {
                    String headerField = httpURLConnection.getHeaderField("Hash");
                    InputStream inputStream = httpURLConnection.getInputStream();
                    File file2 = new File(file, a.this.a(this.b, headerField));
                    FileOutputStream fileOutputStream = new FileOutputStream(file2);
                    byte[] bArr = new byte[1024];
                    while (true) {
                        int read = inputStream.read(bArr);
                        if (read < 0) {
                            break;
                        }
                        fileOutputStream.write(bArr, 0, read);
                    }
                    fileOutputStream.flush();
                    fileOutputStream.close();
                    if (a.a(file2).equalsIgnoreCase(headerField)) {
                        a.this.b(this.b, this.c);
                        a.this.c = this.b;
                        z = a.this.d();
                        str = "OK";
                    } else {
                        str = "Download failed";
                        file2.delete();
                        z = false;
                    }
                } else if (responseCode == 304) {
                    a.this.c = this.b;
                    z = a.this.d();
                    str = "No roadnet update for " + this.b + StorageInterface.KEY_SPLITER + this.c;
                } else if (responseCode == 204) {
                    str = "Not found bldg " + this.b;
                    z = false;
                } else {
                    str = null;
                    z = false;
                }
                if (a.this.b != null) {
                    a.this.b.a(z, str);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            a.this.d = false;
        }
    }

    public interface c {
        void a(boolean z, String str);
    }

    public static class d implements Serializable {
        public String a;
        public String b;
        public C0034a c;
        public C0034a d;
        public C0034a e;
        public C0034a f = this.d;
        public short[][] g;
        private String h = "gcj02";

        public d(String str) {
            this.a = str;
        }

        public double a(double d2) {
            return (this.f.d + d2) * this.f.c;
        }

        public C0034a a() {
            return this.f;
        }

        public void a(String str) {
            if (str != null) {
                this.h = str.toLowerCase();
                if (this.h.startsWith("wgs84")) {
                    this.f = this.c;
                } else if (this.h.startsWith("bd09")) {
                    this.f = this.e;
                } else if (this.h.startsWith("gcj02")) {
                    this.f = this.d;
                }
            }
        }

        public double b(double d2) {
            return (this.f.f + d2) * this.f.e;
        }

        public void b(String str) {
            String[] split = str.split("\\t");
            this.b = split[1];
            this.c = new C0034a(split[2]);
            this.e = new C0034a(split[3]);
            this.d = new C0034a(split[4]);
            this.f = this.d;
            this.g = (short[][]) Array.newInstance(Short.TYPE, new int[]{(int) this.f.g, (int) this.f.h});
            for (int i = 0; ((double) i) < this.f.g; i++) {
                for (int i2 = 0; ((double) i2) < this.f.h; i2++) {
                    this.g[i][i2] = (short) (split[5].charAt((((int) this.f.h) * i) + i2) - '0');
                }
            }
        }

        public double c(double d2) {
            return (d2 / this.f.c) - this.f.d;
        }

        public double d(double d2) {
            return (d2 / this.f.e) - this.f.f;
        }
    }

    private a(Context context) {
        this.e = new File(context.getCacheDir(), this.e).getAbsolutePath();
    }

    public static a a() {
        return a;
    }

    public static a a(Context context) {
        if (a == null) {
            a = new a(context);
        }
        return a;
    }

    public static String a(File file) {
        String str;
        Exception e2;
        try {
            FileInputStream fileInputStream = new FileInputStream(file);
            MappedByteBuffer map = fileInputStream.getChannel().map(MapMode.READ_ONLY, 0, file.length());
            MessageDigest instance = MessageDigest.getInstance("MD5");
            instance.update(map);
            String bigInteger = new BigInteger(1, instance.digest()).toString(16);
            try {
                fileInputStream.close();
                str = bigInteger;
                int length = 32 - bigInteger.length();
                while (length > 0) {
                    try {
                        length--;
                        str = "0" + str;
                    } catch (Exception e3) {
                        e2 = e3;
                        e2.printStackTrace();
                        return str;
                    }
                }
            } catch (Exception e4) {
                Exception exc = e4;
                str = bigInteger;
                e2 = exc;
                e2.printStackTrace();
                return str;
            }
        } catch (Exception e5) {
            e2 = e5;
            str = null;
        }
        return str;
    }

    /* access modifiers changed from: private */
    public String a(String str, String str2) {
        return d(str) + "_" + str2;
    }

    /* access modifiers changed from: private */
    public void b(String str, String str2) {
        try {
            File file = new File(this.e + "/" + a(str, str2));
            if (file.exists()) {
                file.delete();
            }
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    /* access modifiers changed from: private */
    public String d(String str) {
        return str;
    }

    /* access modifiers changed from: private */
    public boolean d() {
        if (this.c == null) {
            return false;
        }
        File f2 = f(this.c);
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        if (!d.a(f2, byteArrayOutputStream)) {
            return false;
        }
        this.h.clear();
        this.i.clear();
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new ByteArrayInputStream(byteArrayOutputStream.toByteArray())));
        while (true) {
            try {
                String readLine = bufferedReader.readLine();
                if (readLine == null) {
                    break;
                } else if (readLine.split("\\t")[1].split("_")[0].equals("geo")) {
                    j(readLine);
                } else {
                    d dVar = new d(this.c);
                    dVar.b(readLine);
                    dVar.a(this.g);
                    this.h.put(dVar.b.toLowerCase(), dVar);
                }
            } catch (IOException e2) {
                e2.printStackTrace();
            }
        }
        bufferedReader.close();
        return true;
    }

    private String e(String str) {
        int i2 = 0;
        File file = new File(this.e);
        if (!file.exists() || !file.isDirectory()) {
            return null;
        }
        File[] listFiles = file.listFiles(new b(this, str));
        if (listFiles == null || listFiles.length != 1) {
            while (listFiles != null && i2 < listFiles.length) {
                listFiles[i2].delete();
                i2++;
            }
            return null;
        }
        String[] split = listFiles[0].getName().split("_");
        if (split.length >= 2) {
            return split[1];
        }
        return null;
    }

    private File f(String str) {
        return new File(this.e + "/" + a(str, e(str)));
    }

    private boolean g(String str) {
        File f2 = f(str);
        return f2.exists() && f2.length() > 0;
    }

    private boolean h(String str) {
        return System.currentTimeMillis() - f(str).lastModified() > 1296000000;
    }

    private ArrayList<Double> i(String str) {
        double intValue;
        int i2 = 0;
        ArrayList<Double> arrayList = new ArrayList<>();
        while (i2 < str.length()) {
            if (str.charAt(i2) == ',') {
                intValue = (double) Integer.valueOf(str.substring(i2 + 1, i2 + 2)).intValue();
                i2 += 2;
            } else if (str.charAt(i2) == '.') {
                intValue = Double.valueOf(str.substring(i2 + 1, i2 + 4)).doubleValue();
                i2 += 4;
            } else {
                intValue = (double) Integer.valueOf(str.substring(i2, i2 + 2)).intValue();
                i2 += 2;
            }
            arrayList.add(Double.valueOf(intValue));
        }
        return arrayList;
    }

    private void j(String str) {
        int i2;
        String[] split = str.split("\\t");
        String lowerCase = split[1].split("_")[1].toLowerCase();
        try {
            if (this.h.containsKey(lowerCase)) {
                ArrayList i3 = i(split[5]);
                int length = ((d) this.h.get(lowerCase)).g.length;
                int length2 = ((d) this.h.get(lowerCase)).g[0].length;
                double[][] dArr = (double[][]) Array.newInstance(Double.TYPE, new int[]{length, length2});
                int i4 = 0;
                for (int i5 = 0; i5 < length; i5++) {
                    int i6 = 0;
                    while (i6 < length2) {
                        if (((d) this.h.get(lowerCase)).g[i5][i6] <= 0 || ((d) this.h.get(lowerCase)).g[i5][i6] == 9) {
                            dArr[i5][i6] = 0.0d;
                            i2 = i4;
                        } else {
                            dArr[i5][i6] = ((Double) i3.get(i4)).doubleValue();
                            i2 = i4 + 1;
                        }
                        i6++;
                        i4 = i2;
                    }
                }
                this.i.put(lowerCase.toLowerCase(), dArr);
            }
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    private void k(String str) {
        if (!this.d) {
            this.d = true;
            this.f = new b(str, e(str));
            this.f.start();
        }
    }

    public void a(double d2, double d3) {
        if (this.j == null) {
            float[] fArr = new float[2];
            float[] fArr2 = new float[2];
            double d4 = d3 + 0.01d;
            Location.distanceBetween(d3, d2, d3, d2 + 0.01d, fArr);
            double d5 = ((double) fArr[0]) / 0.01d;
            Location.distanceBetween(d3, d2, d4, d2, fArr2);
            double d6 = ((double) fArr2[0]) / 0.01d;
            this.j = new d("outdoor");
            this.j.b = "out";
            this.j.f = new C0034a("0|1.0|" + d5 + "|" + (-d2) + "|" + d6 + "|" + (-d3) + "|0|0");
        }
    }

    public void a(String str) {
        this.g = str;
    }

    public void a(String str, c cVar) {
        if (this.c == null || !str.equals(this.c)) {
            this.b = cVar;
            if (!g(str) || h(str)) {
                k(str);
                return;
            }
            this.c = str;
            d();
            if (this.b != null) {
                this.b.a(true, "OK");
            }
        }
    }

    public d b(String str) {
        return (d) this.h.get(str.toLowerCase());
    }

    public void b() {
        this.h.clear();
        this.i.clear();
        this.c = null;
        this.d = false;
    }

    public d c() {
        return this.j;
    }

    public double[][] c(String str) {
        return (double[][]) this.i.get(str.toLowerCase());
    }
}
