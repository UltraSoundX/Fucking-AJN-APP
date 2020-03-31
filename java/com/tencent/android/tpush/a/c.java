package com.tencent.android.tpush.a;

import android.content.pm.ProviderInfo;
import java.util.ArrayList;

/* compiled from: ProGuard */
public class c {
    private String a;
    private ProviderInfo b;
    private ProviderInfo c;
    private ArrayList<ProviderInfo> d;

    public void a(String str) {
        this.a = str;
    }

    public void a(ProviderInfo providerInfo) {
        this.b = providerInfo;
    }

    public void b(ProviderInfo providerInfo) {
        this.c = providerInfo;
    }

    public ArrayList<ProviderInfo> a() {
        return this.d;
    }

    public void c(ProviderInfo providerInfo) {
        if (this.d == null) {
            this.d = new ArrayList<>();
        }
        this.d.add(providerInfo);
    }
}
