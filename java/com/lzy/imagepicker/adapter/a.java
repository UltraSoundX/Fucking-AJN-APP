package com.lzy.imagepicker.adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.lzy.imagepicker.R;
import com.lzy.imagepicker.b;
import com.lzy.imagepicker.b.d;
import java.util.ArrayList;
import java.util.List;

/* compiled from: ImageFolderAdapter */
public class a extends BaseAdapter {
    private b a;
    private Activity b;
    private LayoutInflater c;
    private int d;
    private List<com.lzy.imagepicker.bean.a> e;
    private int f = 0;

    /* renamed from: com.lzy.imagepicker.adapter.a$a reason: collision with other inner class name */
    /* compiled from: ImageFolderAdapter */
    private class C0052a {
        ImageView a;
        TextView b;
        TextView c;
        ImageView d;

        public C0052a(View view) {
            this.a = (ImageView) view.findViewById(R.id.iv_cover);
            this.b = (TextView) view.findViewById(R.id.tv_folder_name);
            this.c = (TextView) view.findViewById(R.id.tv_image_count);
            this.d = (ImageView) view.findViewById(R.id.iv_folder_check);
            view.setTag(this);
        }
    }

    public a(Activity activity, List<com.lzy.imagepicker.bean.a> list) {
        this.b = activity;
        if (list == null || list.size() <= 0) {
            this.e = new ArrayList();
        } else {
            this.e = list;
        }
        this.a = b.a();
        this.d = d.a(this.b);
        this.c = (LayoutInflater) activity.getSystemService("layout_inflater");
    }

    public void a(List<com.lzy.imagepicker.bean.a> list) {
        if (list == null || list.size() <= 0) {
            this.e.clear();
        } else {
            this.e = list;
        }
        notifyDataSetChanged();
    }

    public int getCount() {
        return this.e.size();
    }

    /* renamed from: a */
    public com.lzy.imagepicker.bean.a getItem(int i) {
        return (com.lzy.imagepicker.bean.a) this.e.get(i);
    }

    public long getItemId(int i) {
        return (long) i;
    }

    public View getView(int i, View view, ViewGroup viewGroup) {
        C0052a aVar;
        if (view == null) {
            view = this.c.inflate(R.layout.adapter_folder_list_item, viewGroup, false);
            aVar = new C0052a(view);
        } else {
            aVar = (C0052a) view.getTag();
        }
        com.lzy.imagepicker.bean.a a2 = getItem(i);
        aVar.b.setText(a2.a);
        aVar.c.setText(this.b.getString(R.string.ip_folder_image_count, new Object[]{Integer.valueOf(a2.d.size())}));
        this.a.l().b(this.b, a2.c.b, aVar.a, this.d, this.d);
        if (this.f == i) {
            aVar.d.setVisibility(0);
        } else {
            aVar.d.setVisibility(4);
        }
        return view;
    }

    public void b(int i) {
        if (this.f != i) {
            this.f = i;
            notifyDataSetChanged();
        }
    }

    public int a() {
        return this.f;
    }
}
