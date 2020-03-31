package com.lzy.imagepicker.view;

import android.animation.Animator;
import android.animation.Animator.AnimatorListener;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.view.ViewTreeObserver.OnGlobalLayoutListener;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import com.lzy.imagepicker.R;

/* compiled from: FolderPopUpWindow */
public class a extends PopupWindow implements OnClickListener {
    /* access modifiers changed from: private */
    public ListView a;
    /* access modifiers changed from: private */
    public C0054a b;
    private final View c;
    /* access modifiers changed from: private */
    public final View d;
    /* access modifiers changed from: private */
    public int e;

    /* renamed from: com.lzy.imagepicker.view.a$a reason: collision with other inner class name */
    /* compiled from: FolderPopUpWindow */
    public interface C0054a {
        void a(AdapterView<?> adapterView, View view, int i, long j);
    }

    public a(Context context, BaseAdapter baseAdapter) {
        super(context);
        final View inflate = View.inflate(context, R.layout.pop_folder, null);
        this.c = inflate.findViewById(R.id.masker);
        this.c.setOnClickListener(this);
        this.d = inflate.findViewById(R.id.margin);
        this.d.setOnClickListener(this);
        this.a = (ListView) inflate.findViewById(R.id.listView);
        this.a.setAdapter(baseAdapter);
        setContentView(inflate);
        setWidth(-1);
        setHeight(-1);
        setFocusable(true);
        setOutsideTouchable(true);
        setBackgroundDrawable(new ColorDrawable(0));
        setAnimationStyle(0);
        inflate.getViewTreeObserver().addOnGlobalLayoutListener(new OnGlobalLayoutListener() {
            public void onGlobalLayout() {
                inflate.getViewTreeObserver().removeGlobalOnLayoutListener(this);
                int height = (inflate.getHeight() * 5) / 8;
                int height2 = a.this.a.getHeight();
                LayoutParams layoutParams = a.this.a.getLayoutParams();
                if (height2 <= height) {
                    height = height2;
                }
                layoutParams.height = height;
                a.this.a.setLayoutParams(layoutParams);
                LinearLayout.LayoutParams layoutParams2 = (LinearLayout.LayoutParams) a.this.d.getLayoutParams();
                layoutParams2.height = a.this.e;
                a.this.d.setLayoutParams(layoutParams2);
                a.this.a();
            }
        });
        this.a.setOnItemClickListener(new OnItemClickListener() {
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
                if (a.this.b != null) {
                    a.this.b.a(adapterView, view, i, j);
                }
            }
        });
    }

    /* access modifiers changed from: private */
    public void a() {
        ObjectAnimator ofFloat = ObjectAnimator.ofFloat(this.c, "alpha", new float[]{0.0f, 1.0f});
        ObjectAnimator ofFloat2 = ObjectAnimator.ofFloat(this.a, "translationY", new float[]{(float) this.a.getHeight(), 0.0f});
        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.setDuration(400);
        animatorSet.playTogether(new Animator[]{ofFloat, ofFloat2});
        animatorSet.setInterpolator(new AccelerateDecelerateInterpolator());
        animatorSet.start();
    }

    public void dismiss() {
        b();
    }

    private void b() {
        ObjectAnimator ofFloat = ObjectAnimator.ofFloat(this.c, "alpha", new float[]{1.0f, 0.0f});
        ObjectAnimator ofFloat2 = ObjectAnimator.ofFloat(this.a, "translationY", new float[]{0.0f, (float) this.a.getHeight()});
        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.setDuration(300);
        animatorSet.playTogether(new Animator[]{ofFloat, ofFloat2});
        animatorSet.setInterpolator(new AccelerateDecelerateInterpolator());
        animatorSet.addListener(new AnimatorListener() {
            public void onAnimationStart(Animator animator) {
                a.this.a.setVisibility(0);
            }

            public void onAnimationEnd(Animator animator) {
                a.super.dismiss();
            }

            public void onAnimationCancel(Animator animator) {
            }

            public void onAnimationRepeat(Animator animator) {
            }
        });
        animatorSet.start();
    }

    public void a(C0054a aVar) {
        this.b = aVar;
    }

    public void a(int i) {
        this.a.setSelection(i);
    }

    public void b(int i) {
        this.e = i;
    }

    public void onClick(View view) {
        dismiss();
    }
}
