package cn.sharesdk.framework.b.a;

import android.text.TextUtils;
import com.mob.MobSDK;
import com.mob.tools.utils.SharePrefrenceHelper;

/* compiled from: SharePrefrenceUtil */
public class e {
    private static e b;
    private SharePrefrenceHelper a = new SharePrefrenceHelper(MobSDK.getContext());

    private e() {
        this.a.open("share_sdk", 1);
    }

    public static e a() {
        if (b == null) {
            b = new e();
        }
        return b;
    }

    public long b() {
        return this.a.getLong("service_time");
    }

    public boolean c() {
        String string = this.a.getString("upload_device_info");
        if (TextUtils.isEmpty(string)) {
            return true;
        }
        return Boolean.parseBoolean(string);
    }

    public boolean d() {
        String string = this.a.getString("upload_user_info");
        if (TextUtils.isEmpty(string)) {
            return true;
        }
        return Boolean.parseBoolean(string);
    }

    public boolean e() {
        String string = this.a.getString("trans_short_link");
        if (TextUtils.isEmpty(string)) {
            return false;
        }
        return Boolean.parseBoolean(string);
    }

    public int f() {
        String string = this.a.getString("upload_share_content");
        if ("true".equals(string)) {
            return 1;
        }
        if ("false".equals(string)) {
            return -1;
        }
        return 0;
    }

    public boolean g() {
        String string = this.a.getString("open_login_plus");
        if (TextUtils.isEmpty(string)) {
            return false;
        }
        return Boolean.parseBoolean(string);
    }

    public boolean h() {
        String string = this.a.getString("open_sina_link_card");
        if (TextUtils.isEmpty(string)) {
            return false;
        }
        return Boolean.parseBoolean(string);
    }

    public void a(String str) {
        this.a.putString("trans_short_link", str);
    }

    public void b(String str) {
        this.a.putString("upload_device_info", str);
    }

    public void c(String str) {
        this.a.putString("upload_user_info", str);
    }

    public void d(String str) {
        this.a.putString("upload_share_content", str);
    }

    public void e(String str) {
        this.a.putString("open_login_plus", str);
    }

    public void f(String str) {
        this.a.putString("open_sina_link_card", str);
    }

    public void g(String str) {
        this.a.putString("buffered_snsconf_" + MobSDK.getAppkey(), str);
    }

    public String i() {
        return this.a.getString("buffered_snsconf_" + MobSDK.getAppkey());
    }

    public void a(long j) {
        this.a.putLong("device_time", Long.valueOf(j));
    }

    public Long j() {
        return Long.valueOf(this.a.getLong("device_time"));
    }

    public void a(boolean z) {
        this.a.putBoolean("connect_server", Boolean.valueOf(z));
    }

    public boolean k() {
        return this.a.getBoolean("connect_server");
    }

    public void b(long j) {
        this.a.putLong("connect_server_time", Long.valueOf(j));
    }

    public Long l() {
        return Long.valueOf(this.a.getLong("connect_server_time"));
    }

    public void b(boolean z) {
        this.a.putBoolean("sns_info_buffered", Boolean.valueOf(z));
    }

    public boolean m() {
        return this.a.getBoolean("sns_info_buffered");
    }

    public void a(String str, Long l) {
        this.a.putLong(str, l);
    }

    public long h(String str) {
        return this.a.getLong(str);
    }

    public void a(String str, int i) {
        this.a.putInt(str, Integer.valueOf(i));
    }

    public int i(String str) {
        return this.a.getInt(str);
    }

    public void a(String str, Object obj) {
        this.a.put(str, obj);
    }

    public Object j(String str) {
        return this.a.get(str);
    }
}
