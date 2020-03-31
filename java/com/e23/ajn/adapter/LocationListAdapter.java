package com.e23.ajn.adapter;

import android.content.Context;
import android.text.TextUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.e23.ajn.R;
import java.util.List;

public class LocationListAdapter extends BaseQuickAdapter<String, BaseViewHolder> {
    private Context a;
    private String b;

    public LocationListAdapter(Context context, List<String> list) {
        super(R.layout.f186newslist_item_banner, list);
        this.a = context;
    }

    public void a(String str) {
        this.b = str;
    }

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public void convert(BaseViewHolder baseViewHolder, String str) {
        baseViewHolder.setText((int) R.id.location_item_tv, (CharSequence) str);
        if (TextUtils.isEmpty(this.b) || !this.b.equals(str)) {
            baseViewHolder.setTextColor(R.id.location_item_tv, this.a.getResources().getColor(R.color.colorBlack));
        } else {
            baseViewHolder.setTextColor(R.id.location_item_tv, this.a.getResources().getColor(R.color.colorBlue));
        }
    }
}
