package com.tendyron.liveness.motion.ui.camera;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.ImageFormat;
import android.hardware.Camera;
import android.hardware.Camera.CameraInfo;
import android.hardware.Camera.Parameters;
import android.hardware.Camera.PreviewCallback;
import android.view.SurfaceHolder;
import android.view.WindowManager;
import com.baidu.mobstat.Config;
import com.sensetime.senseid.sdk.liveness.interactive.common.type.Size;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/* compiled from: SenseCamera */
public class a {
    public static final int a = 0;
    public static final int b = 1;
    private static final int c = 100;
    private static final float d = 0.01f;
    private final Object e;
    /* access modifiers changed from: private */
    public Context f;
    private Camera g;
    /* access modifiers changed from: private */
    public int h;
    private int i;
    private Size j;
    /* access modifiers changed from: private */
    public float k;
    /* access modifiers changed from: private */
    public int l;
    /* access modifiers changed from: private */
    public int m;
    /* access modifiers changed from: private */
    public Map<byte[], ByteBuffer> n;
    /* access modifiers changed from: private */
    public PreviewCallback o;

    /* renamed from: com.tendyron.liveness.motion.ui.camera.a$a reason: collision with other inner class name */
    /* compiled from: SenseCamera */
    public static class C0083a {
        private final a a = new a();

        public C0083a(Context context) {
            if (context == null) {
                throw new IllegalArgumentException("No context supplied.");
            }
            this.a.f = context;
        }

        public C0083a a(float f) {
            if (f <= 0.0f) {
                throw new IllegalArgumentException("Invalid fps: " + f);
            }
            this.a.k = f;
            return this;
        }

        public C0083a a(int i, int i2) {
            if (i <= 0 || i > 1000000 || i2 <= 0 || i2 > 1000000) {
                throw new IllegalArgumentException("Invalid preview size: " + i + Config.EVENT_HEAT_X + i2);
            }
            this.a.l = i;
            this.a.m = i2;
            return this;
        }

        public C0083a a(int i) {
            if (i == 0 || i == 1) {
                this.a.h = i;
                return this;
            }
            throw new IllegalArgumentException("Invalid camera: " + i);
        }

        public a a() {
            return this.a;
        }
    }

    /* compiled from: SenseCamera */
    private static class b {
        private Size a;
        private Size b;

        public b(Camera.Size size, Camera.Size size2) {
            this.a = new Size(size.width, size.height);
            if (size2 != null) {
                this.b = new Size(size2.width, size2.height);
            }
        }

        public Size a() {
            return this.a;
        }

        public Size b() {
            return this.b;
        }
    }

    private a() {
        this.e = new Object();
        this.h = 0;
        this.j = new Size(640, 480);
        this.k = 120.0f;
        this.l = 640;
        this.m = 480;
        this.n = new HashMap();
    }

    private static int a(int i2) {
        CameraInfo cameraInfo = new CameraInfo();
        for (int i3 = 0; i3 < Camera.getNumberOfCameras(); i3++) {
            Camera.getCameraInfo(i3, cameraInfo);
            if (cameraInfo.facing == i2) {
                return i3;
            }
        }
        return -1;
    }

    private static b a(Camera camera, int i2, int i3) {
        int i4;
        b bVar;
        b bVar2 = null;
        int i5 = Integer.MAX_VALUE;
        for (b bVar3 : a(camera)) {
            Size a2 = bVar3.a();
            int abs = Math.abs(a2.getHeight() - i3) + Math.abs(a2.getWidth() - i2);
            if (abs < i5) {
                int i6 = abs;
                bVar = bVar3;
                i4 = i6;
            } else {
                i4 = i5;
                bVar = bVar2;
            }
            i5 = i4;
            bVar2 = bVar;
        }
        return bVar2;
    }

    private static List<b> a(Camera camera) {
        Parameters parameters = camera.getParameters();
        List<Camera.Size> supportedPreviewSizes = parameters.getSupportedPreviewSizes();
        List supportedPictureSizes = parameters.getSupportedPictureSizes();
        ArrayList arrayList = new ArrayList();
        for (Camera.Size size : supportedPreviewSizes) {
            float f2 = ((float) size.width) / ((float) size.height);
            Iterator it = supportedPictureSizes.iterator();
            while (true) {
                if (!it.hasNext()) {
                    break;
                }
                Camera.Size size2 = (Camera.Size) it.next();
                if (Math.abs(f2 - (((float) size2.width) / ((float) size2.height))) < d) {
                    arrayList.add(new b(size, size2));
                    break;
                }
            }
        }
        if (arrayList.isEmpty()) {
            for (Camera.Size bVar : supportedPreviewSizes) {
                arrayList.add(new b(bVar, null));
            }
        }
        return arrayList;
    }

    public void a() {
        synchronized (this.e) {
            b();
        }
    }

    @SuppressLint({"MissingPermission"})
    public a a(SurfaceHolder surfaceHolder) throws IOException, RuntimeException {
        synchronized (this.e) {
            if (this.g == null) {
                this.g = f();
                this.g.setPreviewDisplay(surfaceHolder);
                this.g.startPreview();
            }
        }
        return this;
    }

    public void b() {
        synchronized (this.e) {
            this.n.clear();
            if (this.g != null) {
                this.g.stopPreview();
                this.g.setPreviewCallbackWithBuffer(null);
                try {
                    this.g.setPreviewDisplay(null);
                } catch (Exception e2) {
                    e2.printStackTrace();
                }
                this.g.release();
                this.g = null;
            }
        }
    }

    public Size c() {
        return this.j;
    }

    public int d() {
        return this.h;
    }

    private Camera f() throws RuntimeException {
        int a2 = a(this.h);
        if (a2 == -1) {
            throw new IllegalStateException("Could not find requested camera.");
        }
        Camera open = Camera.open(a2);
        if (open == null) {
            throw new IllegalStateException("Unknown camera error");
        }
        b a3 = a(open, this.l, this.m);
        if (a3 == null) {
            throw new IllegalStateException("Could not find suitable preview size.");
        }
        Size b2 = a3.b();
        this.j = a3.a();
        if (a(open, this.k) == null) {
            throw new IllegalStateException("Could not find suitable preview frames per second range.");
        }
        Parameters parameters = open.getParameters();
        if (b2 != null) {
            parameters.setPictureSize(b2.getWidth(), b2.getHeight());
        }
        parameters.setPreviewSize(this.j.getWidth(), this.j.getHeight());
        parameters.setPreviewFormat(17);
        a(open, parameters, a2);
        if (parameters.getSupportedFocusModes().contains("continuous-video")) {
            parameters.setFocusMode("continuous-video");
        }
        open.setParameters(parameters);
        open.setPreviewCallbackWithBuffer(new PreviewCallback() {
            public void onPreviewFrame(byte[] bArr, Camera camera) {
                camera.addCallbackBuffer(((ByteBuffer) a.this.n.get(bArr)).array());
                if (a.this.o != null) {
                    a.this.o.onPreviewFrame(((ByteBuffer) a.this.n.get(bArr)).array(), camera);
                }
            }
        });
        open.addCallbackBuffer(a(this.j));
        return open;
    }

    public void a(PreviewCallback previewCallback) {
        this.o = previewCallback;
    }

    private int[] a(Camera camera, float f2) {
        int i2;
        int[] iArr;
        int i3 = (int) (1000.0f * f2);
        int[] iArr2 = null;
        int i4 = Integer.MAX_VALUE;
        for (int[] iArr3 : camera.getParameters().getSupportedPreviewFpsRange()) {
            int abs = Math.abs(i3 - iArr3[0]) + Math.abs(i3 - iArr3[1]);
            if (abs < i4) {
                int i5 = abs;
                iArr = iArr3;
                i2 = i5;
            } else {
                i2 = i4;
                iArr = iArr2;
            }
            i4 = i2;
            iArr2 = iArr;
        }
        return iArr2;
    }

    private void a(Camera camera, Parameters parameters, int i2) {
        int i3;
        int i4;
        int i5;
        WindowManager windowManager = (WindowManager) this.f.getSystemService("window");
        switch (windowManager != null ? windowManager.getDefaultDisplay().getRotation() : 0) {
            case 0:
                i3 = 0;
                break;
            case 1:
                i3 = 90;
                break;
            case 2:
                i3 = 180;
                break;
            case 3:
                i3 = 270;
                break;
            default:
                i3 = 0;
                break;
        }
        CameraInfo cameraInfo = new CameraInfo();
        Camera.getCameraInfo(i2, cameraInfo);
        if (cameraInfo.facing == 1) {
            i5 = (i3 + cameraInfo.orientation) % 360;
            i4 = (360 - i5) % 360;
        } else {
            i4 = ((cameraInfo.orientation - i3) + 360) % 360;
            i5 = i4;
        }
        this.i = i5 / 90;
        camera.setDisplayOrientation(i4);
        parameters.setRotation(i5);
    }

    public int e() {
        return this.i * 90;
    }

    private byte[] a(Size size) {
        byte[] bArr = new byte[(((int) Math.ceil(((double) ((long) (ImageFormat.getBitsPerPixel(17) * (size.getHeight() * size.getWidth())))) / 8.0d)) + 1)];
        ByteBuffer wrap = ByteBuffer.wrap(bArr);
        if (!wrap.hasArray() || wrap.array() != bArr) {
            throw new IllegalStateException("Failed to create valid buffer for camera.");
        }
        this.n.put(bArr, wrap);
        return bArr;
    }
}
