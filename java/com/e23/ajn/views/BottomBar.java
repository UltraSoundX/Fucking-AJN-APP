package com.e23.ajn.views;

import android.content.Context;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import android.util.AttributeSet;
import android.view.View;
import android.view.View.BaseSavedState;
import android.view.View.OnClickListener;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.Interpolator;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import java.util.ArrayList;
import java.util.List;

public class BottomBar extends LinearLayout {
    private final Interpolator a;
    private boolean b;
    /* access modifiers changed from: private */
    public List<d> c;
    /* access modifiers changed from: private */
    public LinearLayout d;
    private LayoutParams e;
    /* access modifiers changed from: private */
    public int f;
    /* access modifiers changed from: private */
    public a g;

    static class SavedState extends BaseSavedState {
        public static final Creator<SavedState> CREATOR = new Creator<SavedState>() {
            /* renamed from: a */
            public SavedState createFromParcel(Parcel parcel) {
                return new SavedState(parcel);
            }

            /* renamed from: a */
            public SavedState[] newArray(int i) {
                return new SavedState[i];
            }
        };
        /* access modifiers changed from: private */
        public int a;

        public SavedState(Parcel parcel) {
            super(parcel);
            this.a = parcel.readInt();
        }

        public SavedState(Parcelable parcelable, int i) {
            super(parcelable);
            this.a = i;
        }

        public void writeToParcel(Parcel parcel, int i) {
            super.writeToParcel(parcel, i);
            parcel.writeInt(this.a);
        }
    }

    public interface a {
        void a(int i);

        void a(int i, int i2);

        void b(int i);
    }

    public BottomBar(Context context) {
        this(context, null);
    }

    public BottomBar(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public BottomBar(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.a = new AccelerateDecelerateInterpolator();
        this.b = true;
        this.c = new ArrayList();
        this.f = 0;
        a(context, attributeSet);
    }

    private void a(Context context, AttributeSet attributeSet) {
        setOrientation(1);
        this.d = new LinearLayout(context);
        this.d.setBackgroundColor(-1);
        this.d.setOrientation(0);
        addView(this.d, new LayoutParams(-1, -1));
        this.e = new LayoutParams(0, -1);
        this.e.weight = 1.0f;
    }

    public BottomBar a(final d dVar) {
        dVar.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                if (BottomBar.this.g != null) {
                    int tabPosition = dVar.getTabPosition();
                    if (BottomBar.this.f == tabPosition) {
                        BottomBar.this.g.b(tabPosition);
                        return;
                    }
                    BottomBar.this.g.a(tabPosition, BottomBar.this.f);
                    dVar.setSelected(true);
                    BottomBar.this.g.a(BottomBar.this.f);
                    ((d) BottomBar.this.c.get(BottomBar.this.f)).setSelected(false);
                    BottomBar.this.f = tabPosition;
                }
            }
        });
        dVar.setTabPosition(this.d.getChildCount());
        dVar.setLayoutParams(this.e);
        this.d.addView(dVar);
        this.c.add(dVar);
        return this;
    }

    public void setOnTabSelectedListener(a aVar) {
        this.g = aVar;
    }

    public void setCurrentItem(final int i) {
        this.d.post(new Runnable() {
            public void run() {
                BottomBar.this.d.getChildAt(i).performClick();
            }
        });
    }

    public int getCurrentItemPosition() {
        return this.f;
    }

    public d a(int i) {
        if (this.c.size() < i) {
            return null;
        }
        return (d) this.c.get(i);
    }

    /* access modifiers changed from: protected */
    public Parcelable onSaveInstanceState() {
        return new SavedState(super.onSaveInstanceState(), this.f);
    }

    /* access modifiers changed from: protected */
    public void onRestoreInstanceState(Parcelable parcelable) {
        SavedState savedState = (SavedState) parcelable;
        super.onRestoreInstanceState(savedState.getSuperState());
        if (this.f != savedState.a) {
            this.d.getChildAt(this.f).setSelected(false);
            this.d.getChildAt(savedState.a).setSelected(true);
        }
        this.f = savedState.a;
    }
}
