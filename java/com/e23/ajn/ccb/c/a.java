package com.e23.ajn.ccb.c;

import android.content.Context;
import com.c.a.c.a.d;
import com.e23.ajn.ccb.d.g;
import com.e23.ajn.ccb.d.i;
import com.e23.ajn.ccb.entity.BaseReq;
import com.e23.ajn.ccb.entity.FileUploadEntity;
import com.e23.ajn.ccb.entity.SecurityReqBody;
import java.io.File;
import org.apache.http.client.CookieStore;

/* compiled from: MainController */
public class a {
    private static a a;

    public static a a() {
        if (a == null) {
            a = new a();
        }
        return a;
    }

    public void a(Context context, BaseReq baseReq, SecurityReqBody securityReqBody, d dVar) {
        i.a().a(context);
        g.a().a(context, com.e23.ajn.ccb.b.a.a, baseReq, securityReqBody, dVar);
    }

    public void a(CookieStore cookieStore, File file, BaseReq baseReq, d dVar) {
        g.a().a(cookieStore, com.e23.ajn.ccb.b.a.b + ((FileUploadEntity) baseReq).ACTION, file, baseReq, dVar);
    }
}
