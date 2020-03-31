package com.e23.ajn.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import com.e23.ajn.R;
import com.e23.ajn.d.f;

/* compiled from: EmGridAdapter */
public class a extends BaseAdapter {
    private LayoutInflater a = ((LayoutInflater) this.b.getSystemService("layout_inflater"));
    private Context b;

    /* renamed from: com.e23.ajn.adapter.a$a reason: collision with other inner class name */
    /* compiled from: EmGridAdapter */
    private class C0049a {
        ImageView a;

        private C0049a() {
        }
    }

    public a(Context context) {
        this.b = context;
    }

    public int getCount() {
        return f.b.length;
    }

    public Object getItem(int i) {
        return null;
    }

    public long getItemId(int i) {
        return 0;
    }

    public View getView(int i, View view, ViewGroup viewGroup) {
        C0049a aVar;
        if (view == null) {
            view = this.a.inflate(R.layout.design_navigation_menu_item, null);
            C0049a aVar2 = new C0049a();
            aVar2.a = (ImageView) view.findViewById(R.id.itemImage);
            view.setTag(aVar2);
            aVar = aVar2;
        } else {
            aVar = (C0049a) view.getTag();
        }
        aVar.a.setImageResource(f.b[i]);
        return view;
    }
}
