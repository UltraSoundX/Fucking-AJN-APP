package com.e23.ajn.views;

import android.app.Activity;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build.VERSION;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.FileProvider;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.webkit.URLUtil;
import android.widget.Button;
import android.widget.TextView;
import com.e23.ajn.R;
import com.e23.ajn.activity.MainActivity;
import com.e23.ajn.d.i;
import com.e23.ajn.model.UpdateModel;
import com.tencent.mid.core.Constants;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;

/* compiled from: UpdateDialog */
public class q extends Dialog implements OnClickListener {
    private static String[] i = {"android.permission.READ_EXTERNAL_STORAGE", Constants.PERMISSION_WRITE_EXTERNAL_STORAGE};
    /* access modifiers changed from: private */
    public Context a;
    private TextView b;
    private TextView c;
    private Button d;
    private Button e;
    private UpdateModel f;
    /* access modifiers changed from: private */
    public ProgressDialog g;
    private int h = 10;

    public q(Context context, UpdateModel updateModel) {
        super(context, R.style.dm_alert_dialog);
        this.a = context;
        this.f = updateModel;
        setContentView(R.layout.update_dialog_layout);
        this.b = (TextView) findViewById(R.id.update_dialog_layout_title);
        this.c = (TextView) findViewById(R.id.update_dialog_layout_content);
        this.d = (Button) findViewById(R.id.update_dialog_layout_btn_update);
        this.e = (Button) findViewById(R.id.update_dialog_layout_btn_cancel);
        if (updateModel != null) {
            if (TextUtils.isEmpty(updateModel.getEnforce_update()) || !updateModel.getEnforce_update().equals("1")) {
                this.e.setText(context.getString(R.string.cancel));
            } else {
                this.e.setText(context.getString(R.string.quite_app));
            }
            this.c.setText(updateModel.getUpdate_info());
        }
        this.d.setOnClickListener(this);
        this.e.setOnClickListener(this);
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.update_dialog_layout_btn_update /*2131821202*/:
                if (VERSION.SDK_INT <= 21) {
                    cancel();
                    a();
                    if (this.f != null) {
                        a(this.f.getApp_url());
                        return;
                    }
                    return;
                } else if (ActivityCompat.checkSelfPermission(this.a, Constants.PERMISSION_WRITE_EXTERNAL_STORAGE) != 0) {
                    ActivityCompat.requestPermissions((Activity) this.a, i, this.h);
                    return;
                } else {
                    cancel();
                    a();
                    if (this.f != null) {
                        a(this.f.getApp_url());
                        return;
                    }
                    return;
                }
            case R.id.update_dialog_layout_btn_cancel /*2131821203*/:
                if (TextUtils.isEmpty(this.f.getEnforce_update()) || !this.f.getEnforce_update().equals("1")) {
                    cancel();
                    return;
                }
                cancel();
                ((MainActivity) this.a).finish();
                System.exit(0);
                return;
            default:
                return;
        }
    }

    /* access modifiers changed from: protected */
    public void a() {
        this.g = new ProgressDialog(this.a);
        this.g.setMessage("正在更新，请稍候...");
        this.g.setIndeterminate(true);
        this.g.setCancelable(false);
        this.g.show();
    }

    private void a(final String str) {
        if (!URLUtil.isNetworkUrl(str)) {
            k.c(this.a, "服务器地址错误！");
            if (this.g != null && this.g.isShowing()) {
                this.g.dismiss();
                return;
            }
            return;
        }
        new Thread(new Runnable() {
            public void run() {
                if (!q.this.b(str)) {
                    ((Activity) q.this.a).runOnUiThread(new Runnable() {
                        public void run() {
                            k.c(q.this.a, "对不起，下载失败");
                            if (q.this.g != null) {
                                q.this.g.dismiss();
                            }
                        }
                    });
                } else {
                    ((Activity) q.this.a).runOnUiThread(new Runnable() {
                        public void run() {
                            k.c(q.this.a, "下载成功");
                            if (q.this.g != null) {
                                q.this.g.dismiss();
                            }
                        }
                    });
                }
            }
        }).start();
    }

    /* access modifiers changed from: private */
    public boolean b(String str) {
        boolean z = false;
        InputStream inputStream = null;
        try {
            String substring = str.substring(str.lastIndexOf("/") + 1, str.lastIndexOf("."));
            URLConnection openConnection = new URL(str).openConnection();
            openConnection.connect();
            inputStream = openConnection.getInputStream();
            if (inputStream == null) {
                throw new RuntimeException("stream is null");
            }
            File b2 = i.b(substring);
            FileOutputStream fileOutputStream = new FileOutputStream(b2);
            byte[] bArr = new byte[128];
            while (true) {
                int read = inputStream.read(bArr);
                if (read <= 0) {
                    break;
                }
                fileOutputStream.write(bArr, 0, read);
            }
            a(b2);
            z = true;
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (Exception e2) {
                    e2.printStackTrace();
                }
            }
            return z;
        } catch (Exception e3) {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (Exception e4) {
                    e4.printStackTrace();
                }
            }
        } finally {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (Exception e5) {
                    e5.printStackTrace();
                }
            }
        }
    }

    private void a(File file) {
        Intent intent = new Intent("android.intent.action.VIEW");
        if (VERSION.SDK_INT >= 24) {
            Uri uriForFile = FileProvider.getUriForFile(this.a, "com.e23.ajn.fileprovider", file);
            intent.addFlags(1);
            intent.setDataAndType(uriForFile, "application/vnd.android.package-archive");
        } else {
            intent.setFlags(268435456);
            intent.setDataAndType(Uri.fromFile(file), "application/vnd.android.package-archive");
        }
        this.a.startActivity(intent);
    }
}
