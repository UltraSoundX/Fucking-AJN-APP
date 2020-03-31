package com.e23.ajn.views;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import com.bumptech.glide.i;
import com.e23.ajn.R;
import com.e23.ajn.model.ThumbBean;
import com.zhouwei.mzbanner.a.b;

/* compiled from: BannerViewHolder */
public class c implements b<ThumbBean> {
    private ImageView a;
    private TextView b;

    public View a(Context context) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.f121bzlist_item, null);
        this.a = (ImageView) inflate.findViewById(R.id.banner_image);
        this.b = (TextView) inflate.findViewById(R.id.banner_title);
        return inflate;
    }

    public void a(Context context, int i, ThumbBean thumbBean) {
        i.b(context).a(thumbBean.getThumb()).b(com.bumptech.glide.d.b.b.ALL).d((int) R.mipmap.f275plimg).c((int) R.mipmap.f275plimg).a(this.a);
        this.b.setText(thumbBean.getTitle());
    }
}
