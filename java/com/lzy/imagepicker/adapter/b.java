package com.lzy.imagepicker.adapter;

import android.app.Activity;
import android.support.v4.app.ActivityCompat;
import android.support.v4.view.PointerIconCompat;
import android.support.v7.widget.RecyclerView.v;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AbsListView.LayoutParams;
import android.widget.ImageView;
import android.widget.Toast;
import com.lzy.imagepicker.R;
import com.lzy.imagepicker.b.d;
import com.lzy.imagepicker.bean.ImageItem;
import com.lzy.imagepicker.ui.ImageBaseActivity;
import com.lzy.imagepicker.view.SuperCheckBox;
import com.stub.StubApp;
import java.util.ArrayList;

/* compiled from: ImageRecyclerAdapter */
public class b extends android.support.v7.widget.RecyclerView.a<v> {
    /* access modifiers changed from: private */
    public com.lzy.imagepicker.b a;
    /* access modifiers changed from: private */
    public Activity b;
    private ArrayList<ImageItem> c;
    /* access modifiers changed from: private */
    public ArrayList<ImageItem> d;
    private boolean e;
    /* access modifiers changed from: private */
    public int f;
    private LayoutInflater g;
    /* access modifiers changed from: private */
    public c h;

    /* compiled from: ImageRecyclerAdapter */
    private class a extends v {
        View a;

        a(View view) {
            super(view);
            this.a = view;
        }

        /* access modifiers changed from: 0000 */
        public void a() {
            this.a.setLayoutParams(new LayoutParams(-1, b.this.f));
            this.a.setTag(null);
            this.a.setOnClickListener(new OnClickListener() {
                public void onClick(View view) {
                    if (!((ImageBaseActivity) b.this.b).checkPermission("android.permission.CAMERA")) {
                        ActivityCompat.requestPermissions(b.this.b, new String[]{"android.permission.CAMERA"}, 2);
                        return;
                    }
                    b.this.a.a(b.this.b, (int) PointerIconCompat.TYPE_CONTEXT_MENU);
                }
            });
        }
    }

    /* renamed from: com.lzy.imagepicker.adapter.b$b reason: collision with other inner class name */
    /* compiled from: ImageRecyclerAdapter */
    private class C0053b extends v {
        View a;
        ImageView b;
        View c;
        View d;
        SuperCheckBox e;

        C0053b(View view) {
            super(view);
            this.a = view;
            this.b = (ImageView) view.findViewById(R.id.iv_thumb);
            this.c = view.findViewById(R.id.mask);
            this.d = view.findViewById(R.id.checkView);
            this.e = (SuperCheckBox) view.findViewById(R.id.cb_check);
            view.setLayoutParams(new LayoutParams(-1, b.this.f));
        }

        /* access modifiers changed from: 0000 */
        public void a(final int i) {
            final ImageItem a2 = b.this.a(i);
            this.b.setOnClickListener(new OnClickListener() {
                public void onClick(View view) {
                    if (b.this.h != null) {
                        b.this.h.onImageItemClick(C0053b.this.a, a2, i);
                    }
                }
            });
            this.d.setOnClickListener(new OnClickListener() {
                public void onClick(View view) {
                    C0053b.this.e.setChecked(!C0053b.this.e.isChecked());
                    int c2 = b.this.a.c();
                    if (!C0053b.this.e.isChecked() || b.this.d.size() < c2) {
                        b.this.a.a(i, a2, C0053b.this.e.isChecked());
                        C0053b.this.c.setVisibility(0);
                        return;
                    }
                    Toast.makeText(StubApp.getOrigApplicationContext(b.this.b.getApplicationContext()), b.this.b.getString(R.string.ip_select_limit, new Object[]{Integer.valueOf(c2)}), 0).show();
                    C0053b.this.e.setChecked(false);
                    C0053b.this.c.setVisibility(8);
                }
            });
            if (b.this.a.b()) {
                this.e.setVisibility(0);
                if (b.this.d.contains(a2)) {
                    this.c.setVisibility(0);
                    this.e.setChecked(true);
                } else {
                    this.c.setVisibility(8);
                    this.e.setChecked(false);
                }
            } else {
                this.e.setVisibility(8);
            }
            b.this.a.l().b(b.this.b, a2.b, this.b, b.this.f, b.this.f);
        }
    }

    /* compiled from: ImageRecyclerAdapter */
    public interface c {
        void onImageItemClick(View view, ImageItem imageItem, int i);
    }

    public void a(c cVar) {
        this.h = cVar;
    }

    public void a(ArrayList<ImageItem> arrayList) {
        if (arrayList == null || arrayList.size() == 0) {
            this.c = new ArrayList<>();
        } else {
            this.c = arrayList;
        }
        notifyDataSetChanged();
    }

    public b(Activity activity, ArrayList<ImageItem> arrayList) {
        this.b = activity;
        if (arrayList == null || arrayList.size() == 0) {
            this.c = new ArrayList<>();
        } else {
            this.c = arrayList;
        }
        this.f = d.a(this.b);
        this.a = com.lzy.imagepicker.b.a();
        this.e = this.a.e();
        this.d = this.a.p();
        this.g = LayoutInflater.from(activity);
    }

    public v onCreateViewHolder(ViewGroup viewGroup, int i) {
        if (i == 0) {
            return new a(this.g.inflate(R.layout.adapter_camera_item, viewGroup, false));
        }
        return new C0053b(this.g.inflate(R.layout.adapter_image_list_item, viewGroup, false));
    }

    public void onBindViewHolder(v vVar, int i) {
        if (vVar instanceof a) {
            ((a) vVar).a();
        } else if (vVar instanceof C0053b) {
            ((C0053b) vVar).a(i);
        }
    }

    public int getItemViewType(int i) {
        return (!this.e || i != 0) ? 1 : 0;
    }

    public long getItemId(int i) {
        return (long) i;
    }

    public int getItemCount() {
        return this.e ? this.c.size() + 1 : this.c.size();
    }

    public ImageItem a(int i) {
        if (!this.e) {
            return (ImageItem) this.c.get(i);
        }
        if (i == 0) {
            return null;
        }
        return (ImageItem) this.c.get(i - 1);
    }
}
