package com.e23.ajn.adapter;

import android.app.Activity;
import android.content.Context;
import android.widget.ImageView;
import com.bumptech.glide.d.b.b;
import com.bumptech.glide.i;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.e23.ajn.R;
import com.e23.ajn.d.q;
import com.e23.ajn.model.ReporterListResponse.DataBean;
import java.util.List;

public class ReporterListAdapter extends BaseQuickAdapter<DataBean, BaseViewHolder> {
    private Context a;
    private int b;

    public ReporterListAdapter(Context context, List<DataBean> list) {
        super(R.layout.f112notification_template_big_media_narrow_custom, list);
        this.a = context;
        this.b = q.b((Activity) context);
    }

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public void convert(BaseViewHolder baseViewHolder, DataBean dataBean) {
        i.b(this.a).a(dataBean.getProreporter_avatar()).b(b.ALL).d((int) R.mipmap.f277plimgn).c((int) R.mipmap.f277plimgn).a((ImageView) baseViewHolder.getView(R.id.reporter_header));
        baseViewHolder.setText((int) R.id.reporter_name, (CharSequence) dataBean.getTruename());
        baseViewHolder.setText((int) R.id.reporter_type, (CharSequence) dataBean.getReporter_type());
    }
}
