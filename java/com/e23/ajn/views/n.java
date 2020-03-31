package com.e23.ajn.views;

import android.app.Activity;
import android.app.Dialog;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import com.e23.ajn.R;
import java.util.List;

/* compiled from: SelectDialog */
public class n extends Dialog implements OnClickListener, OnItemClickListener {
    private c a;
    /* access modifiers changed from: private */
    public Activity b;
    private Button c;
    private TextView d;
    private List<String> e;
    private String f;
    /* access modifiers changed from: private */
    public boolean g = false;
    /* access modifiers changed from: private */
    public int h;
    /* access modifiers changed from: private */
    public int i;
    /* access modifiers changed from: private */
    public b j;

    /* compiled from: SelectDialog */
    private class a extends BaseAdapter {
        private List<String> b;
        private d c;
        private LayoutInflater d;

        public a(List<String> list) {
            this.b = list;
            this.d = n.this.b.getLayoutInflater();
        }

        public int getCount() {
            return this.b.size();
        }

        public Object getItem(int i) {
            return this.b.get(i);
        }

        public long getItemId(int i) {
            return (long) i;
        }

        public View getView(int i, View view, ViewGroup viewGroup) {
            if (view == null) {
                this.c = new d();
                view = this.d.inflate(R.layout.view_dialog_item, null);
                this.c.a = (TextView) view.findViewById(R.id.dialog_item_bt);
                view.setTag(this.c);
            } else {
                this.c = (d) view.getTag();
            }
            this.c.a.setText((CharSequence) this.b.get(i));
            if (!n.this.g) {
                n.this.h = n.this.b.getResources().getColor(R.color.blue);
                n.this.i = n.this.b.getResources().getColor(R.color.blue);
            }
            if (1 == this.b.size()) {
                this.c.a.setTextColor(n.this.h);
                this.c.a.setBackgroundResource(R.drawable.f85shape);
            } else if (i == 0) {
                this.c.a.setTextColor(n.this.h);
                this.c.a.setBackgroundResource(R.drawable.f95webloadingbg);
            } else if (i == this.b.size() - 1) {
                this.c.a.setTextColor(n.this.i);
                this.c.a.setBackgroundResource(R.drawable.f93webloading);
            } else {
                this.c.a.setTextColor(n.this.i);
                this.c.a.setBackgroundResource(R.drawable.f94webloading2);
            }
            return view;
        }
    }

    /* compiled from: SelectDialog */
    public interface b {
        void a(View view);
    }

    /* compiled from: SelectDialog */
    public interface c {
        void a(AdapterView<?> adapterView, View view, int i, long j);
    }

    /* compiled from: SelectDialog */
    public static class d {
        public TextView a;
    }

    public n(Activity activity, int i2, c cVar, List<String> list) {
        super(activity, i2);
        this.b = activity;
        this.a = cVar;
        this.e = list;
        setCanceledOnTouchOutside(true);
    }

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(getLayoutInflater().inflate(R.layout.view_dialog_select, null), new LayoutParams(-1, -2));
        Window window = getWindow();
        window.setWindowAnimations(R.style.main_menu_animstyle);
        WindowManager.LayoutParams attributes = window.getAttributes();
        attributes.x = 0;
        attributes.y = this.b.getWindowManager().getDefaultDisplay().getHeight();
        attributes.width = -1;
        attributes.height = -2;
        onWindowAttributesChanged(attributes);
        a();
    }

    private void a() {
        a aVar = new a(this.e);
        ListView listView = (ListView) findViewById(R.id.dialog_list);
        listView.setOnItemClickListener(this);
        listView.setAdapter(aVar);
        this.c = (Button) findViewById(R.id.mBtn_Cancel);
        this.d = (TextView) findViewById(R.id.mTv_Title);
        this.c.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                if (n.this.j != null) {
                    n.this.j.a(view);
                }
                n.this.dismiss();
            }
        });
        if (TextUtils.isEmpty(this.f) || this.d == null) {
            this.d.setVisibility(8);
            return;
        }
        this.d.setVisibility(0);
        this.d.setText(this.f);
    }

    public void onClick(View view) {
        dismiss();
    }

    public void onItemClick(AdapterView<?> adapterView, View view, int i2, long j2) {
        this.a.a(adapterView, view, i2, j2);
        dismiss();
    }
}
