package me.yokeyword.fragmentation.debug;

import android.content.DialogInterface;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager.BackStackEntry;
import android.support.v4.app.FragmentationMagician;
import android.support.v4.view.GravityCompat;
import android.support.v7.app.a.C0012a;
import android.util.TypedValue;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.FrameLayout.LayoutParams;
import android.widget.ImageView;
import java.util.ArrayList;
import java.util.List;
import me.yokeyword.fragmentation.R;

/* compiled from: DebugStackDelegate */
public class b implements SensorEventListener {
    private FragmentActivity a;
    private SensorManager b;
    private android.support.v7.app.a c;

    /* compiled from: DebugStackDelegate */
    private class a implements OnTouchListener {
        private View b;
        private float c;
        private float d = 0.0f;
        private float e;
        private float f = 0.0f;
        private boolean g;
        private int h;

        a(View view, int i) {
            this.b = view;
            this.h = i;
        }

        public boolean onTouch(View view, MotionEvent motionEvent) {
            float rawX = motionEvent.getRawX();
            float rawY = motionEvent.getRawY();
            switch (motionEvent.getAction()) {
                case 0:
                    this.g = true;
                    this.e = rawX;
                    this.f = rawY;
                    this.c = this.b.getX() - motionEvent.getRawX();
                    this.d = this.b.getY() - motionEvent.getRawY();
                    break;
                case 1:
                case 3:
                    if (rawX - this.e < ((float) this.h) && this.g) {
                        this.b.performClick();
                        break;
                    }
                case 2:
                    if (Math.abs(rawX - this.e) < ((float) this.h) && Math.abs(rawY - this.f) < ((float) this.h) && this.g) {
                        this.g = true;
                        break;
                    } else {
                        this.g = false;
                        this.b.setX(motionEvent.getRawX() + this.c);
                        this.b.setY(motionEvent.getRawY() + this.d);
                        break;
                    }
                    break;
                default:
                    return false;
            }
            return true;
        }
    }

    public b(FragmentActivity fragmentActivity) {
        this.a = fragmentActivity;
    }

    public void a(int i) {
        if (i == 1) {
            this.b = (SensorManager) this.a.getSystemService("sensor");
            this.b.registerListener(this, this.b.getDefaultSensor(1), 3);
        }
    }

    public void b(int i) {
        if (i == 2) {
            View findViewById = this.a.findViewById(16908290);
            if (findViewById instanceof FrameLayout) {
                FrameLayout frameLayout = (FrameLayout) findViewById;
                ImageView imageView = new ImageView(this.a);
                imageView.setImageResource(R.drawable.fragmentation_ic_stack);
                LayoutParams layoutParams = new LayoutParams(-2, -2);
                layoutParams.gravity = GravityCompat.END;
                int applyDimension = (int) TypedValue.applyDimension(1, 18.0f, this.a.getResources().getDisplayMetrics());
                layoutParams.topMargin = applyDimension * 7;
                layoutParams.rightMargin = applyDimension;
                imageView.setLayoutParams(layoutParams);
                frameLayout.addView(imageView);
                imageView.setOnTouchListener(new a(imageView, applyDimension / 4));
                imageView.setOnClickListener(new OnClickListener() {
                    public void onClick(View view) {
                        b.this.b();
                    }
                });
            }
        }
    }

    public void a() {
        if (this.b != null) {
            this.b.unregisterListener(this);
        }
    }

    public void onSensorChanged(SensorEvent sensorEvent) {
        int type = sensorEvent.sensor.getType();
        float[] fArr = sensorEvent.values;
        if (type != 1) {
            return;
        }
        if (Math.abs(fArr[0]) >= ((float) 12) || Math.abs(fArr[1]) >= ((float) 12) || Math.abs(fArr[2]) >= ((float) 12)) {
            b();
        }
    }

    public void onAccuracyChanged(Sensor sensor, int i) {
    }

    public void b() {
        if (this.c == null || !this.c.isShowing()) {
            DebugHierarchyViewContainer debugHierarchyViewContainer = new DebugHierarchyViewContainer(this.a);
            debugHierarchyViewContainer.a(c());
            debugHierarchyViewContainer.setLayoutParams(new ViewGroup.LayoutParams(-1, -1));
            this.c = new C0012a(this.a).b((View) debugHierarchyViewContainer).a(17039360, (DialogInterface.OnClickListener) null).a(true).b();
            this.c.show();
        }
    }

    private List<a> c() {
        ArrayList arrayList = new ArrayList();
        List<Fragment> activeFragments = FragmentationMagician.getActiveFragments(this.a.getSupportFragmentManager());
        if (activeFragments == null || activeFragments.size() < 1) {
            return null;
        }
        for (Fragment a2 : activeFragments) {
            a(arrayList, a2);
        }
        return arrayList;
    }

    private List<a> a(Fragment fragment) {
        ArrayList arrayList = new ArrayList();
        List activeFragments = FragmentationMagician.getActiveFragments(fragment.getChildFragmentManager());
        if (activeFragments == null || activeFragments.size() < 1) {
            return null;
        }
        for (int size = activeFragments.size() - 1; size >= 0; size--) {
            a(arrayList, (Fragment) activeFragments.get(size));
        }
        return arrayList;
    }

    private void a(List<a> list, Fragment fragment) {
        CharSequence charSequence;
        if (fragment != null) {
            int backStackEntryCount = fragment.getFragmentManager().getBackStackEntryCount();
            CharSequence simpleName = fragment.getClass().getSimpleName();
            if (backStackEntryCount != 0) {
                charSequence = simpleName;
                for (int i = 0; i < backStackEntryCount; i++) {
                    BackStackEntry backStackEntryAt = fragment.getFragmentManager().getBackStackEntryAt(i);
                    if ((backStackEntryAt.getName() != null && backStackEntryAt.getName().equals(fragment.getTag())) || (backStackEntryAt.getName() == null && fragment.getTag() == null)) {
                        break;
                    }
                    if (i == backStackEntryCount - 1) {
                        charSequence = a(charSequence);
                    }
                }
            } else {
                charSequence = a(simpleName);
            }
            list.add(new a(charSequence, a(fragment)));
        }
    }

    private CharSequence a(CharSequence charSequence) {
        return charSequence + " *";
    }
}
