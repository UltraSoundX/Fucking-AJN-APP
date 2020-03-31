package com.tendyron.liveness.motion;

import android.app.Activity;
import android.graphics.Rect;
import android.hardware.Camera;
import android.hardware.Camera.PreviewCallback;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.ImageView;
import android.widget.TextView;
import com.sensetime.senseid.sdk.liveness.interactive.InteractiveLivenessApi;
import com.sensetime.senseid.sdk.liveness.interactive.common.type.PixelFormat;
import com.sensetime.senseid.sdk.liveness.interactive.common.util.FileUtil;
import com.sensetime.senseid.sdk.liveness.interactive.type.BoundInfo;
import com.tendyron.liveness.R;
import com.tendyron.liveness.motion.ui.camera.SenseCameraPreview;
import com.tendyron.liveness.motion.ui.camera.a;
import com.tendyron.liveness.motion.view.CircleTimeView;
import com.tendyron.liveness.motion.view.c;
import com.tendyron.liveness.motion.view.d;
import java.io.File;
import java.lang.reflect.Field;
import java.util.List;

public abstract class AbstractCommonMotionLivingActivity extends Activity implements PreviewCallback {
    public static final String a = "extra_difficulty";
    public static final String b = "extra_voice";
    public static final String c = "extra_sequences";
    public static final String d = "extra_facing";
    public static final String e = "extra_path";
    public static String f = null;
    public static String g = null;
    protected static final String h = "motionLivenessResult";
    protected static final String i = "Liveness_Interactive.lic";
    protected static final String j = "M_Detect_Hunter_SmallFace.model";
    protected static final String k = "M_Align_occlusion.model";
    protected static final String l = "M_Liveness_Cnn_half.model";
    protected static final String m = "M_Liveness_Antispoofing.model";
    protected static final int[] n = {R.drawable.common_step_1_normal, R.drawable.common_step_2_normal, R.drawable.common_step_3_normal, R.drawable.common_step_4_normal, R.drawable.common_step_5_normal, R.drawable.common_step_6_normal, R.drawable.common_step_7_normal, R.drawable.common_step_8_normal, R.drawable.common_step_9_normal, R.drawable.common_step_10_normal};
    protected static final int[] o = {R.drawable.common_step_1_selected, R.drawable.common_step_2_selected, R.drawable.common_step_3_selected, R.drawable.common_step_4_selected, R.drawable.common_step_5_selected, R.drawable.common_step_6_selected, R.drawable.common_step_7_selected, R.drawable.common_step_8_selected, R.drawable.common_step_9_selected, R.drawable.common_step_10_selected};
    protected d A = null;
    protected ImageView B = null;
    protected boolean C = false;
    protected int D = 1;
    protected boolean p = true;

    /* renamed from: q reason: collision with root package name */
    protected int f437q = 2;
    protected int[] r = {0, 1, 3, 2};
    protected int s = -1;
    protected TextView t = null;
    protected View u = null;
    protected View v = null;
    protected ViewPager w = null;
    protected ViewGroup x = null;
    protected SenseCameraPreview y = null;
    protected a z = null;

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        f = getFilesDir() + "/liveness/";
        g = f + "interactive/";
    }

    /* access modifiers changed from: protected */
    public void onResume() {
        super.onResume();
        try {
            this.y.a(this.z);
            this.z.a((PreviewCallback) this);
        } catch (Exception e2) {
            setResult(3);
            finish();
        }
    }

    /* access modifiers changed from: protected */
    public void onPause() {
        this.C = false;
        this.v.setVisibility(8);
        InteractiveLivenessApi.release();
        if (this.A != null) {
            this.A.a();
            this.A.a((d.a) null);
            this.A = null;
        }
        com.tendyron.liveness.motion.a.a.a().b();
        this.y.a();
        this.y.b();
        setResult(0);
        if (!isFinishing()) {
            finish();
        }
        super.onPause();
    }

    /* access modifiers changed from: protected */
    public void onDestroy() {
        super.onDestroy();
    }

    public void onPreviewFrame(byte[] bArr, Camera camera) {
        int i2;
        int width = this.y.getWidth();
        int height = this.y.getHeight();
        ViewParent parent = this.y.getParent();
        if (parent != null) {
            int width2 = ((View) parent).getWidth();
            height = ((View) parent).getHeight();
            i2 = width2;
        } else {
            i2 = width;
        }
        if (this.C) {
            Rect a2 = this.y.a(new Rect(0, 0, i2, height));
            BoundInfo a3 = this.y.a(new BoundInfo(i2 / 2, height / 2, i2 / 3));
            InteractiveLivenessApi.inputData(bArr, PixelFormat.NV21, this.z.c(), a2, true, this.z.e(), a3);
        }
    }

    /* access modifiers changed from: protected */
    public void onStart() {
        super.onStart();
    }

    /* access modifiers changed from: protected */
    public void a() {
        this.x = (ViewGroup) this.u.findViewById(R.id.layout_steps);
        for (int i2 = 0; i2 < this.r.length; i2++) {
            ImageView imageView = (ImageView) getLayoutInflater().inflate(R.layout.common_item_motion_step, this.x, false);
            imageView.setImageResource(n[i2]);
            this.x.addView(imageView);
        }
        this.w = (ViewPager) findViewById(R.id.pager_action);
        this.w.setOnTouchListener(new OnTouchListener() {
            public boolean onTouch(View view, MotionEvent motionEvent) {
                return true;
            }
        });
        this.w.setAdapter(new c(this.r));
        try {
            Field declaredField = ViewPager.class.getDeclaredField("mScroller");
            declaredField.setAccessible(true);
            declaredField.set(this.w, new com.tendyron.liveness.motion.view.a(this.w.getContext()));
        } catch (IllegalAccessException | IllegalArgumentException | NoSuchFieldException e2) {
            e2.printStackTrace();
        }
        this.A = new d((CircleTimeView) findViewById(R.id.time_view));
        this.A.a((d.a) new d.a() {
            public void a() {
                AbstractCommonMotionLivingActivity.this.A.a();
            }
        });
    }

    /* access modifiers changed from: protected */
    public void a(List<byte[]> list, String str) {
        if (list != null && !list.isEmpty()) {
            File file = new File(str);
            if (!file.exists()) {
                file.mkdirs();
            }
            int i2 = 0;
            while (true) {
                int i3 = i2;
                if (i3 < list.size()) {
                    FileUtil.saveDataToFile((byte[]) list.get(i3), str + i3 + ".jpg");
                    i2 = i3 + 1;
                } else {
                    return;
                }
            }
        }
    }

    /* access modifiers changed from: protected */
    public boolean a(String... strArr) {
        if (strArr == null || strArr.length < 1 || VERSION.SDK_INT < 23) {
            return true;
        }
        for (String checkSelfPermission : strArr) {
            if (checkSelfPermission(checkSelfPermission) != 0) {
                return false;
            }
        }
        return true;
    }
}
