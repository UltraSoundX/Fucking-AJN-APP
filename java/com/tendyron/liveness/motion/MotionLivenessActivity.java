package com.tendyron.liveness.motion;

import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.Rect;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.SystemClock;
import android.widget.ImageView;
import com.sensetime.senseid.sdk.liveness.interactive.InteractiveLivenessApi;
import com.sensetime.senseid.sdk.liveness.interactive.OnLivenessListener;
import com.sensetime.senseid.sdk.liveness.interactive.common.type.ResultCode;
import com.sensetime.senseid.sdk.liveness.interactive.common.util.FileUtil;
import com.sensetime.senseid.sdk.liveness.interactive.type.FaceOcclusion;
import com.stub.StubApp;
import com.tendyron.liveness.R;
import com.tendyron.liveness.motion.view.d.a;
import java.util.List;

public class MotionLivenessActivity extends AbstractCommonMotionLivingActivity {
    private OnLivenessListener E = new OnLivenessListener() {
        private long b;

        public void onInitialized() {
            MotionLivenessActivity.this.C = true;
        }

        public void onStatusUpdate(int i, FaceOcclusion faceOcclusion, int i2) {
            boolean z;
            if (SystemClock.elapsedRealtime() - this.b >= 300 || i == 0) {
                if (i == 3) {
                    StringBuffer stringBuffer = new StringBuffer();
                    if (faceOcclusion.getBrowOcclusionState() == 2) {
                        stringBuffer.append(MotionLivenessActivity.this.getString(R.string.common_covered_brow));
                        z = true;
                    } else {
                        z = false;
                    }
                    if (faceOcclusion.getEyeOcclusionState() == 2) {
                        stringBuffer.append(MotionLivenessActivity.this.getString(R.string.common_covered_eye));
                        z = true;
                    }
                    if (faceOcclusion.getNoseOcclusionState() == 2) {
                        stringBuffer.append(z ? "、" : "");
                        stringBuffer.append(MotionLivenessActivity.this.getString(R.string.common_covered_nose));
                        z = true;
                    }
                    if (faceOcclusion.getMouthOcclusionState() == 2) {
                        stringBuffer.append(z ? "、" : "");
                        stringBuffer.append(MotionLivenessActivity.this.getString(R.string.common_covered_mouth));
                    }
                    MotionLivenessActivity.this.t.setText(MotionLivenessActivity.this.getString(R.string.common_face_covered, new Object[]{stringBuffer.toString()}));
                } else if (i2 == -1) {
                    MotionLivenessActivity.this.t.setText(R.string.common_face_too_close);
                } else if (i2 == 1) {
                    MotionLivenessActivity.this.t.setText(R.string.common_face_too_far);
                } else if (i == 0) {
                    MotionLivenessActivity.this.t.setText(R.string.common_detecting);
                } else {
                    MotionLivenessActivity.this.t.setText(R.string.common_tracking_missed);
                }
                this.b = SystemClock.elapsedRealtime();
            }
        }

        public void onError(ResultCode resultCode) {
            MotionLivenessActivity.this.C = false;
            MotionLivenessActivity.this.setResult(a.a(resultCode));
            MotionLivenessActivity.this.finish();
        }

        public void onDetectOver(ResultCode resultCode, byte[] bArr, List list, Rect rect) {
            MotionLivenessActivity.this.C = false;
            if (bArr != null && bArr.length > 0) {
                FileUtil.saveDataToFile(bArr, AbstractCommonMotionLivingActivity.g + "motionLivenessResult");
            }
            switch (AnonymousClass4.a[resultCode.ordinal()]) {
                case 1:
                    if (MotionLivenessActivity.this.A != null) {
                        MotionLivenessActivity.this.A.a((a) null);
                        MotionLivenessActivity.this.A.c();
                        MotionLivenessActivity.this.A = null;
                    }
                    MotionLivenessActivity.this.a(list, AbstractCommonMotionLivingActivity.g);
                    Intent intent = new Intent();
                    intent.putExtra(AbstractCommonMotionLivingActivity.e, AbstractCommonMotionLivingActivity.g);
                    MotionLivenessActivity.this.setResult(-1, intent);
                    break;
                default:
                    MotionLivenessActivity.this.setResult(a.a(resultCode));
                    break;
            }
            MotionLivenessActivity.this.finish();
        }

        public void onAligned() {
            MotionLivenessActivity.this.B.setImageDrawable(MotionLivenessActivity.this.getResources().getDrawable(R.drawable.common_background_success));
            MotionLivenessActivity.this.C = false;
            new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
                public void run() {
                    MotionLivenessActivity.this.C = true;
                    MotionLivenessActivity.this.B.setImageDrawable(MotionLivenessActivity.this.getResources().getDrawable(R.drawable.common_background));
                }
            }, 500);
            InteractiveLivenessApi.start(MotionLivenessActivity.this.r, MotionLivenessActivity.this.f437q);
            MotionLivenessActivity.this.t.setVisibility(8);
            MotionLivenessActivity.this.u.setVisibility(0);
            MotionLivenessActivity.this.w.setCurrentItem(0, false);
            if (MotionLivenessActivity.this.s > -1) {
                ((ImageView) MotionLivenessActivity.this.x.getChildAt(MotionLivenessActivity.this.s)).setImageResource(AbstractCommonMotionLivingActivity.n[MotionLivenessActivity.this.s]);
            }
        }

        public void onMotionSet(int i, int i2) {
            MotionLivenessActivity.this.s = i;
            MotionLivenessActivity.this.w.setCurrentItem(i, true);
            if (i > 0) {
                ((ImageView) MotionLivenessActivity.this.x.getChildAt(i - 1)).setImageResource(AbstractCommonMotionLivingActivity.n[i - 1]);
            }
            ((ImageView) MotionLivenessActivity.this.x.getChildAt(i)).setImageResource(AbstractCommonMotionLivingActivity.o[i]);
            if (MotionLivenessActivity.this.A != null) {
                MotionLivenessActivity.this.A.a(true);
            }
            if (MotionLivenessActivity.this.p) {
                com.tendyron.liveness.motion.a.a.a().a(MotionLivenessActivity.this, MotionLivenessActivity.this.r[MotionLivenessActivity.this.s]);
            }
        }
    };

    /* renamed from: com.tendyron.liveness.motion.MotionLivenessActivity$4 reason: invalid class name */
    static /* synthetic */ class AnonymousClass4 {
        static final /* synthetic */ int[] a = new int[ResultCode.values().length];

        static {
            try {
                a[ResultCode.OK.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
        }
    }

    static {
        StubApp.interface11(5752);
    }

    /* access modifiers changed from: protected */
    public native void onCreate(Bundle bundle);

    public void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
    }
}
