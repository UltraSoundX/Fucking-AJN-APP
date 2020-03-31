package com.e23.ajn.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.e23.ajn.R;
import java.util.ArrayList;
import java.util.Map;

/* compiled from: ShareAdapter */
public class c extends BaseAdapter {
    private Context a;
    private ArrayList<Map<String, Object>> b;

    /* compiled from: ShareAdapter */
    class a {
        /* access modifiers changed from: private */
        public ImageView b;
        /* access modifiers changed from: private */
        public TextView c;

        a() {
        }
    }

    public c(Context context, ArrayList<Map<String, Object>> arrayList) {
        this.b = arrayList;
        this.a = context;
    }

    public int getCount() {
        if (this.b != null) {
            return this.b.size();
        }
        return 0;
    }

    public Object getItem(int i) {
        if (this.b == null || this.b.size() <= 0) {
            return null;
        }
        return (Map) this.b.get(i);
    }

    public long getItemId(int i) {
        return (long) i;
    }

    public View getView(int i, View view, ViewGroup viewGroup) {
        a aVar;
        if (view == null) {
            aVar = new a();
            view = LayoutInflater.from(this.a).inflate(R.layout.text_item_share, null);
            aVar.b = (ImageView) view.findViewById(R.id.ItemImage);
            aVar.c = (TextView) view.findViewById(R.id.ItemText);
            view.setTag(aVar);
        } else {
            aVar = (a) view.getTag();
        }
        aVar.c.setTextColor(Color.parseColor("#ff000000"));
        aVar.b.setImageResource(((Integer) ((Map) this.b.get(i)).get("ItemImage")).intValue());
        aVar.c.setText((CharSequence) ((Map) this.b.get(i)).get("ItemText"));
        return view;
    }
}
