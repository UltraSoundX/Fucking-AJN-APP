package com.d.a.a;

import android.graphics.drawable.ColorDrawable;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.RelativeLayout;
import android.widget.TextView;
import cn.sharesdk.framework.Platform;
import com.d.a.a.c.b;
import com.d.a.c;
import com.mob.tools.gui.PullToRequestView;
import com.mob.tools.utils.ResHelper;
import java.util.ArrayList;
import java.util.HashMap;

/* compiled from: FriendListPage */
public abstract class e extends c implements OnClickListener, OnItemClickListener {
    private Platform a;
    private LinearLayout b;
    private RelativeLayout c;
    private TextView d;
    private TextView e;
    private c f;
    private int g = -1;
    private int h = 0;

    /* access modifiers changed from: protected */
    public abstract float h();

    /* access modifiers changed from: protected */
    public abstract int i();

    public e(com.d.a.e eVar) {
        super(eVar);
    }

    public void d(Platform platform) {
        this.a = platform;
    }

    public void onCreate() {
        this.activity.getWindow().setBackgroundDrawable(new ColorDrawable(-789517));
        this.b = new LinearLayout(this.activity);
        this.b.setOrientation(1);
        this.activity.setContentView(this.b);
        this.c = new RelativeLayout(this.activity);
        float h2 = h();
        this.b.addView(this.c, new LayoutParams(-1, (int) (((float) i()) * h2)));
        a(this.c, h2);
        View view = new View(this.activity);
        LayoutParams layoutParams = new LayoutParams(-1, (int) (h2 < 1.0f ? 1.0f : h2));
        view.setBackgroundColor(-2434599);
        this.b.addView(view, layoutParams);
        FrameLayout frameLayout = new FrameLayout(getContext());
        LayoutParams layoutParams2 = new LayoutParams(-1, -2);
        layoutParams2.weight = 1.0f;
        frameLayout.setLayoutParams(layoutParams2);
        this.b.addView(frameLayout);
        PullToRequestView pullToRequestView = new PullToRequestView(getContext());
        pullToRequestView.setLayoutParams(new FrameLayout.LayoutParams(-1, -1));
        frameLayout.addView(pullToRequestView);
        this.f = new c(this, pullToRequestView);
        this.f.a(this.a);
        this.f.a(h2);
        this.f.a((OnItemClickListener) this);
        pullToRequestView.setAdapter(this.f);
        pullToRequestView.performPullingDown(true);
    }

    private void a(RelativeLayout relativeLayout, float f2) {
        this.d = new TextView(this.activity);
        this.d.setTextColor(-12895429);
        this.d.setTextSize(2, 18.0f);
        this.d.setGravity(17);
        int stringRes = ResHelper.getStringRes(this.activity, "ssdk_oks_cancel");
        if (stringRes > 0) {
            this.d.setText(stringRes);
        }
        int i = (int) (40.0f * f2);
        this.d.setPadding(i, 0, i, 0);
        relativeLayout.addView(this.d, new RelativeLayout.LayoutParams(-2, -1));
        this.d.setOnClickListener(this);
        TextView textView = new TextView(this.activity);
        textView.setTextColor(-12895429);
        textView.setTextSize(2, 22.0f);
        textView.setGravity(17);
        int stringRes2 = ResHelper.getStringRes(this.activity, "ssdk_oks_contacts");
        if (stringRes2 > 0) {
            textView.setText(stringRes2);
        }
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(-2, -1);
        layoutParams.addRule(13);
        relativeLayout.addView(textView, layoutParams);
        this.e = new TextView(this.activity);
        this.e.setTextColor(-37615);
        this.e.setTextSize(2, 18.0f);
        this.e.setGravity(17);
        int stringRes3 = ResHelper.getStringRes(this.activity, "ssdk_oks_confirm");
        if (stringRes3 > 0) {
            this.e.setText(stringRes3);
        }
        this.e.setPadding(i, 0, i, 0);
        RelativeLayout.LayoutParams layoutParams2 = new RelativeLayout.LayoutParams(-2, -1);
        layoutParams2.addRule(11);
        relativeLayout.addView(this.e, layoutParams2);
        this.e.setOnClickListener(this);
    }

    public void onClick(View view) {
        if (view.equals(this.d)) {
            finish();
            return;
        }
        ArrayList arrayList = new ArrayList();
        int count = this.f.getCount();
        for (int i = 0; i < count; i++) {
            if (this.f.getItem(i).a) {
                arrayList.add(this.f.getItem(i).f);
            }
        }
        HashMap hashMap = new HashMap();
        hashMap.put("selected", arrayList);
        hashMap.put("platform", this.a);
        setResult(hashMap);
        finish();
    }

    public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
        boolean z = false;
        if ("FacebookMessenger".equals(this.a.getName())) {
            if (this.g >= 0) {
                this.f.getItem(this.g).a = false;
            }
            this.g = i;
        }
        b a2 = this.f.getItem(i);
        if (!a2.a) {
            z = true;
        }
        a2.a = z;
        if (a2.a) {
            this.h++;
        } else {
            this.h--;
        }
        j();
        this.f.notifyDataSetChanged();
    }

    private void j() {
        int stringRes = ResHelper.getStringRes(this.activity, "ssdk_oks_confirm");
        String str = "Confirm";
        if (stringRes > 0) {
            str = getContext().getResources().getString(stringRes);
        }
        if (this.h == 0) {
            this.e.setText(str);
        } else if (this.h > 0) {
            this.e.setText(str + "(" + this.h + ")");
        }
    }
}
