package com.e23.ajn.adapter;

import android.app.Activity;
import android.content.Context;
import android.support.v7.widget.RecyclerView.v;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import com.e23.ajn.R;
import com.lzy.imagepicker.bean.ImageItem;
import java.util.ArrayList;
import java.util.List;

/* compiled from: ImagePickerAdapter */
public class b extends android.support.v7.widget.RecyclerView.a<C0050b> {
    private int a;
    /* access modifiers changed from: private */
    public Context b;
    /* access modifiers changed from: private */
    public List<ImageItem> c;
    private LayoutInflater d;
    /* access modifiers changed from: private */
    public a e;
    /* access modifiers changed from: private */
    public boolean f;

    /* compiled from: ImagePickerAdapter */
    public interface a {
        void a(View view, int i);
    }

    /* renamed from: com.e23.ajn.adapter.b$b reason: collision with other inner class name */
    /* compiled from: ImagePickerAdapter */
    public class C0050b extends v implements OnClickListener {
        private ImageView b;
        private int c;

        public C0050b(View view) {
            super(view);
            this.b = (ImageView) view.findViewById(R.id.iv_img);
        }

        public void a(int i) {
            this.itemView.setOnClickListener(this);
            ImageItem imageItem = (ImageItem) b.this.c.get(i);
            if (!b.this.f || i != b.this.getItemCount() - 1) {
                com.lzy.imagepicker.b.a().l().b((Activity) b.this.b, imageItem.b, this.b, 0, 0);
                this.c = i;
                return;
            }
            this.b.setImageResource(R.drawable.notification_template_icon_low_bg);
            this.c = -1;
        }

        public void onClick(View view) {
            if (b.this.e != null) {
                b.this.e.a(view, this.c);
            }
        }
    }

    public void a(a aVar) {
        this.e = aVar;
    }

    public void a(List<ImageItem> list) {
        this.c = new ArrayList(list);
        if (getItemCount() < this.a) {
            this.c.add(new ImageItem());
            this.f = true;
        } else {
            this.f = false;
        }
        notifyDataSetChanged();
    }

    public List<ImageItem> a() {
        if (this.f) {
            return new ArrayList(this.c.subList(0, this.c.size() - 1));
        }
        return this.c;
    }

    public b(Context context, List<ImageItem> list, int i) {
        this.b = context;
        this.a = i;
        this.d = LayoutInflater.from(context);
        a(list);
    }

    /* renamed from: a */
    public C0050b onCreateViewHolder(ViewGroup viewGroup, int i) {
        return new C0050b(this.d.inflate(R.layout.f192nvbaolistheader, viewGroup, false));
    }

    /* renamed from: a */
    public void onBindViewHolder(C0050b bVar, int i) {
        bVar.a(i);
    }

    public int getItemCount() {
        return this.c.size();
    }
}
