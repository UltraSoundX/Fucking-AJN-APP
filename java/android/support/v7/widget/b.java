package android.support.v7.widget;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ResolveInfo;
import android.database.DataSetObservable;
import android.os.AsyncTask;
import android.text.TextUtils;
import android.util.Log;
import android.util.Xml;
import com.tencent.android.tpush.common.Constants;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlSerializer;

/* compiled from: ActivityChooserModel */
class b extends DataSetObservable {
    static final String a = b.class.getSimpleName();
    private static final Object e = new Object();
    private static final Map<String, b> f = new HashMap();
    final Context b;
    final String c;
    boolean d = true;
    private final Object g = new Object();
    private final List<a> h = new ArrayList();
    private final List<d> i = new ArrayList();
    private Intent j;
    private C0016b k = new c();
    private int l = 50;
    private boolean m = false;
    private boolean n = true;
    private boolean o = false;
    private e p;

    /* compiled from: ActivityChooserModel */
    public static final class a implements Comparable<a> {
        public final ResolveInfo a;
        public float b;

        public a(ResolveInfo resolveInfo) {
            this.a = resolveInfo;
        }

        public int hashCode() {
            return Float.floatToIntBits(this.b) + 31;
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj == null) {
                return false;
            }
            if (getClass() != obj.getClass()) {
                return false;
            }
            if (Float.floatToIntBits(this.b) != Float.floatToIntBits(((a) obj).b)) {
                return false;
            }
            return true;
        }

        /* renamed from: a */
        public int compareTo(a aVar) {
            return Float.floatToIntBits(aVar.b) - Float.floatToIntBits(this.b);
        }

        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append("[");
            sb.append("resolveInfo:").append(this.a.toString());
            sb.append("; weight:").append(new BigDecimal((double) this.b));
            sb.append("]");
            return sb.toString();
        }
    }

    /* renamed from: android.support.v7.widget.b$b reason: collision with other inner class name */
    /* compiled from: ActivityChooserModel */
    public interface C0016b {
        void a(Intent intent, List<a> list, List<d> list2);
    }

    /* compiled from: ActivityChooserModel */
    private static final class c implements C0016b {
        private final Map<ComponentName, a> a = new HashMap();

        c() {
        }

        public void a(Intent intent, List<a> list, List<d> list2) {
            float f;
            Map<ComponentName, a> map = this.a;
            map.clear();
            int size = list.size();
            for (int i = 0; i < size; i++) {
                a aVar = (a) list.get(i);
                aVar.b = 0.0f;
                map.put(new ComponentName(aVar.a.activityInfo.packageName, aVar.a.activityInfo.name), aVar);
            }
            float f2 = 1.0f;
            int size2 = list2.size() - 1;
            while (size2 >= 0) {
                d dVar = (d) list2.get(size2);
                a aVar2 = (a) map.get(dVar.a);
                if (aVar2 != null) {
                    aVar2.b = (dVar.c * f2) + aVar2.b;
                    f = 0.95f * f2;
                } else {
                    f = f2;
                }
                size2--;
                f2 = f;
            }
            Collections.sort(list);
        }
    }

    /* compiled from: ActivityChooserModel */
    public static final class d {
        public final ComponentName a;
        public final long b;
        public final float c;

        public d(String str, long j, float f) {
            this(ComponentName.unflattenFromString(str), j, f);
        }

        public d(ComponentName componentName, long j, float f) {
            this.a = componentName;
            this.b = j;
            this.c = f;
        }

        public int hashCode() {
            return (((((this.a == null ? 0 : this.a.hashCode()) + 31) * 31) + ((int) (this.b ^ (this.b >>> 32)))) * 31) + Float.floatToIntBits(this.c);
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj == null) {
                return false;
            }
            if (getClass() != obj.getClass()) {
                return false;
            }
            d dVar = (d) obj;
            if (this.a == null) {
                if (dVar.a != null) {
                    return false;
                }
            } else if (!this.a.equals(dVar.a)) {
                return false;
            }
            if (this.b != dVar.b) {
                return false;
            }
            if (Float.floatToIntBits(this.c) != Float.floatToIntBits(dVar.c)) {
                return false;
            }
            return true;
        }

        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append("[");
            sb.append("; activity:").append(this.a);
            sb.append("; time:").append(this.b);
            sb.append("; weight:").append(new BigDecimal((double) this.c));
            sb.append("]");
            return sb.toString();
        }
    }

    /* compiled from: ActivityChooserModel */
    public interface e {
        boolean a(b bVar, Intent intent);
    }

    /* compiled from: ActivityChooserModel */
    private final class f extends AsyncTask<Object, Void, Void> {
        f() {
        }

        /* renamed from: a */
        public Void doInBackground(Object... objArr) {
            List list = objArr[0];
            String str = objArr[1];
            try {
                FileOutputStream openFileOutput = b.this.b.openFileOutput(str, 0);
                XmlSerializer newSerializer = Xml.newSerializer();
                try {
                    newSerializer.setOutput(openFileOutput, null);
                    newSerializer.startDocument("UTF-8", Boolean.valueOf(true));
                    newSerializer.startTag(null, "historical-records");
                    int size = list.size();
                    for (int i = 0; i < size; i++) {
                        d dVar = (d) list.remove(0);
                        newSerializer.startTag(null, "historical-record");
                        newSerializer.attribute(null, Constants.FLAG_ACTIVITY_NAME, dVar.a.flattenToString());
                        newSerializer.attribute(null, "time", String.valueOf(dVar.b));
                        newSerializer.attribute(null, "weight", String.valueOf(dVar.c));
                        newSerializer.endTag(null, "historical-record");
                    }
                    newSerializer.endTag(null, "historical-records");
                    newSerializer.endDocument();
                    b.this.d = true;
                    if (openFileOutput != null) {
                        try {
                            openFileOutput.close();
                        } catch (IOException e) {
                        }
                    }
                } catch (IllegalArgumentException e2) {
                    Log.e(b.a, "Error writing historical record file: " + b.this.c, e2);
                    b.this.d = true;
                    if (openFileOutput != null) {
                        try {
                            openFileOutput.close();
                        } catch (IOException e3) {
                        }
                    }
                } catch (IllegalStateException e4) {
                    Log.e(b.a, "Error writing historical record file: " + b.this.c, e4);
                    b.this.d = true;
                    if (openFileOutput != null) {
                        try {
                            openFileOutput.close();
                        } catch (IOException e5) {
                        }
                    }
                } catch (IOException e6) {
                    Log.e(b.a, "Error writing historical record file: " + b.this.c, e6);
                    b.this.d = true;
                    if (openFileOutput != null) {
                        try {
                            openFileOutput.close();
                        } catch (IOException e7) {
                        }
                    }
                } catch (Throwable th) {
                    b.this.d = true;
                    if (openFileOutput != null) {
                        try {
                            openFileOutput.close();
                        } catch (IOException e8) {
                        }
                    }
                    throw th;
                }
            } catch (FileNotFoundException e9) {
                Log.e(b.a, "Error writing historical record file: " + str, e9);
            }
            return null;
        }
    }

    public static b a(Context context, String str) {
        b bVar;
        synchronized (e) {
            bVar = (b) f.get(str);
            if (bVar == null) {
                bVar = new b(context, str);
                f.put(str, bVar);
            }
        }
        return bVar;
    }

    private b(Context context, String str) {
        this.b = context.getApplicationContext();
        if (TextUtils.isEmpty(str) || str.endsWith(".xml")) {
            this.c = str;
        } else {
            this.c = str + ".xml";
        }
    }

    public int a() {
        int size;
        synchronized (this.g) {
            e();
            size = this.h.size();
        }
        return size;
    }

    public ResolveInfo a(int i2) {
        ResolveInfo resolveInfo;
        synchronized (this.g) {
            e();
            resolveInfo = ((a) this.h.get(i2)).a;
        }
        return resolveInfo;
    }

    public int a(ResolveInfo resolveInfo) {
        synchronized (this.g) {
            e();
            List<a> list = this.h;
            int size = list.size();
            for (int i2 = 0; i2 < size; i2++) {
                if (((a) list.get(i2)).a == resolveInfo) {
                    return i2;
                }
            }
            return -1;
        }
    }

    public Intent b(int i2) {
        synchronized (this.g) {
            if (this.j == null) {
                return null;
            }
            e();
            a aVar = (a) this.h.get(i2);
            ComponentName componentName = new ComponentName(aVar.a.activityInfo.packageName, aVar.a.activityInfo.name);
            Intent intent = new Intent(this.j);
            intent.setComponent(componentName);
            if (this.p != null) {
                if (this.p.a(this, new Intent(intent))) {
                    return null;
                }
            }
            a(new d(componentName, System.currentTimeMillis(), 1.0f));
            return intent;
        }
    }

    public ResolveInfo b() {
        synchronized (this.g) {
            e();
            if (this.h.isEmpty()) {
                return null;
            }
            ResolveInfo resolveInfo = ((a) this.h.get(0)).a;
            return resolveInfo;
        }
    }

    public void c(int i2) {
        float f2;
        synchronized (this.g) {
            e();
            a aVar = (a) this.h.get(i2);
            a aVar2 = (a) this.h.get(0);
            if (aVar2 != null) {
                f2 = (aVar2.b - aVar.b) + 5.0f;
            } else {
                f2 = 1.0f;
            }
            a(new d(new ComponentName(aVar.a.activityInfo.packageName, aVar.a.activityInfo.name), System.currentTimeMillis(), f2));
        }
    }

    private void d() {
        if (!this.m) {
            throw new IllegalStateException("No preceding call to #readHistoricalData");
        } else if (this.n) {
            this.n = false;
            if (!TextUtils.isEmpty(this.c)) {
                new f().executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new Object[]{new ArrayList(this.i), this.c});
            }
        }
    }

    public int c() {
        int size;
        synchronized (this.g) {
            e();
            size = this.i.size();
        }
        return size;
    }

    private void e() {
        boolean g2 = g() | h();
        i();
        if (g2) {
            f();
            notifyChanged();
        }
    }

    private boolean f() {
        if (this.k == null || this.j == null || this.h.isEmpty() || this.i.isEmpty()) {
            return false;
        }
        this.k.a(this.j, this.h, Collections.unmodifiableList(this.i));
        return true;
    }

    private boolean g() {
        if (!this.o || this.j == null) {
            return false;
        }
        this.o = false;
        this.h.clear();
        List queryIntentActivities = this.b.getPackageManager().queryIntentActivities(this.j, 0);
        int size = queryIntentActivities.size();
        for (int i2 = 0; i2 < size; i2++) {
            this.h.add(new a((ResolveInfo) queryIntentActivities.get(i2)));
        }
        return true;
    }

    private boolean h() {
        if (!this.d || !this.n || TextUtils.isEmpty(this.c)) {
            return false;
        }
        this.d = false;
        this.m = true;
        j();
        return true;
    }

    private boolean a(d dVar) {
        boolean add = this.i.add(dVar);
        if (add) {
            this.n = true;
            i();
            d();
            f();
            notifyChanged();
        }
        return add;
    }

    private void i() {
        int size = this.i.size() - this.l;
        if (size > 0) {
            this.n = true;
            for (int i2 = 0; i2 < size; i2++) {
                d dVar = (d) this.i.remove(0);
            }
        }
    }

    private void j() {
        try {
            FileInputStream openFileInput = this.b.openFileInput(this.c);
            try {
                XmlPullParser newPullParser = Xml.newPullParser();
                newPullParser.setInput(openFileInput, "UTF-8");
                int i2 = 0;
                while (i2 != 1 && i2 != 2) {
                    i2 = newPullParser.next();
                }
                if (!"historical-records".equals(newPullParser.getName())) {
                    throw new XmlPullParserException("Share records file does not start with historical-records tag.");
                }
                List<d> list = this.i;
                list.clear();
                while (true) {
                    int next = newPullParser.next();
                    if (next == 1) {
                        if (openFileInput != null) {
                            try {
                                openFileInput.close();
                                return;
                            } catch (IOException e2) {
                                return;
                            }
                        } else {
                            return;
                        }
                    } else if (!(next == 3 || next == 4)) {
                        if (!"historical-record".equals(newPullParser.getName())) {
                            throw new XmlPullParserException("Share records file not well-formed.");
                        }
                        list.add(new d(newPullParser.getAttributeValue(null, Constants.FLAG_ACTIVITY_NAME), Long.parseLong(newPullParser.getAttributeValue(null, "time")), Float.parseFloat(newPullParser.getAttributeValue(null, "weight"))));
                    }
                }
            } catch (XmlPullParserException e3) {
                Log.e(a, "Error reading historical recrod file: " + this.c, e3);
                if (openFileInput != null) {
                    try {
                        openFileInput.close();
                    } catch (IOException e4) {
                    }
                }
            } catch (IOException e5) {
                Log.e(a, "Error reading historical recrod file: " + this.c, e5);
                if (openFileInput != null) {
                    try {
                        openFileInput.close();
                    } catch (IOException e6) {
                    }
                }
            } finally {
                if (openFileInput != null) {
                    try {
                        openFileInput.close();
                    } catch (IOException e7) {
                    }
                }
            }
        } catch (FileNotFoundException e8) {
        }
    }
}
