package com.baidu.location.indoor.mapversion.a;

import android.text.TextUtils;
import android.util.Base64;
import com.baidu.mobstat.Config;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import org.json.JSONArray;
import org.json.JSONObject;

public class d {
    private HashMap<String, c> a;
    private HashSet<a> b;
    /* access modifiers changed from: private */
    public File c;
    /* access modifiers changed from: private */
    public boolean d;
    /* access modifiers changed from: private */
    public String e;
    /* access modifiers changed from: private */
    public String f;

    public interface a {
        void a(boolean z);
    }

    private void a(boolean z) {
        Iterator it = this.b.iterator();
        while (it.hasNext()) {
            ((a) it.next()).a(z);
        }
        this.d = false;
    }

    private boolean b(String str) {
        if (str == null) {
            return false;
        }
        try {
            if (str.equalsIgnoreCase("")) {
                return false;
            }
            JSONArray optJSONArray = new JSONObject(str).optJSONArray("item");
            this.a = new HashMap<>();
            for (int i = 0; i < optJSONArray.length(); i++) {
                c cVar = new c(optJSONArray.getJSONObject(i));
                if (!(cVar.b() == null || cVar.c() == null)) {
                    this.a.put(c.a(cVar.c()), cVar);
                }
            }
            return true;
        } catch (Exception e2) {
            e2.printStackTrace();
            return false;
        }
    }

    private void c() {
        new Thread(new e(this)).start();
    }

    /* JADX INFO: finally extract failed */
    /* access modifiers changed from: private */
    public boolean d() {
        try {
            File file = new File(this.c, this.e);
            long currentTimeMillis = System.currentTimeMillis();
            long lastModified = currentTimeMillis - file.lastModified();
            if (!file.exists() || currentTimeMillis - file.lastModified() > Config.MAX_LOG_DATA_EXSIT_TIME) {
                this.d = false;
                this.d = false;
                return false;
            }
            StringBuilder sb = new StringBuilder();
            BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
            this.f = bufferedReader.readLine();
            while (true) {
                String readLine = bufferedReader.readLine();
                if (readLine != null) {
                    sb.append(readLine).append("\n");
                } else {
                    bufferedReader.close();
                    a(b(new String(Base64.decode(sb.toString(), 0))));
                    this.d = false;
                    return true;
                }
            }
        } catch (Exception e2) {
            e2.printStackTrace();
            this.d = false;
            return false;
        } catch (Throwable th) {
            this.d = false;
            throw th;
        }
    }

    public c a(String str, String str2) {
        if (this.a == null || this.e == null || !str.equalsIgnoreCase(this.e)) {
            return null;
        }
        return (c) this.a.get(c.a(str2));
    }

    public void a(String str) {
        if (!this.d) {
            this.d = true;
            if (TextUtils.isEmpty(str)) {
                a(false);
            } else if (this.e == null || !str.equalsIgnoreCase(this.e) || !a()) {
                this.e = str;
                this.f = null;
                if (!d()) {
                    c();
                }
            } else {
                a(true);
            }
        }
    }

    public boolean a() {
        return this.a != null && this.a.size() > 0;
    }

    public String b() {
        if (a()) {
            Object[] array = this.a.keySet().toArray();
            if (array.length > 0 && this.a.get(array[0].toString()) != null) {
                return ((c) this.a.get(array[0].toString())).c();
            }
        }
        return "A001";
    }
}
