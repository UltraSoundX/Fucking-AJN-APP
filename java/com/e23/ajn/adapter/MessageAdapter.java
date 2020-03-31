package com.e23.ajn.adapter;

import android.content.Context;
import android.widget.ImageView;
import com.bumptech.glide.d.b.b;
import com.bumptech.glide.i;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.e23.ajn.R;
import com.e23.ajn.d.f;
import com.e23.ajn.model.MessageBean;
import com.e23.ajn.views.CirleImageView;
import java.util.List;
import q.rorbin.badgeview.e;

public class MessageAdapter extends BaseQuickAdapter<MessageBean, BaseViewHolder> {
    private Context a;
    private String b;

    public MessageAdapter(Context context, List<MessageBean> list, String str) {
        super(R.layout.f187newslist_item_imgs, list);
        this.a = context;
        this.b = str;
    }

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public void convert(BaseViewHolder baseViewHolder, MessageBean messageBean) {
        if (messageBean.getIscom().equals("0")) {
            baseViewHolder.setText((int) R.id.message_item_username, (CharSequence) messageBean.getSend_to_id());
            i.b(this.a).a(messageBean.getSend_to_avatar()).h().b(b.ALL).d((int) R.mipmap.f278plitemding).c((int) R.mipmap.f278plitemding).a((ImageView) (CirleImageView) baseViewHolder.getView(R.id.message_item_avatar));
        } else {
            baseViewHolder.setText((int) R.id.message_item_username, (CharSequence) messageBean.getSend_from_id());
            i.b(this.a).a(messageBean.getSend_from_avatar()).h().b(b.ALL).d((int) R.mipmap.f278plitemding).c((int) R.mipmap.f278plitemding).a((ImageView) (CirleImageView) baseViewHolder.getView(R.id.message_item_avatar));
        }
        baseViewHolder.setText((int) R.id.message_item_content, (CharSequence) f.a(this.a, messageBean.getContent()));
        if (!messageBean.getStatus().equals("1") || !messageBean.getIscom().equals("1")) {
            new e(baseViewHolder.getView(R.id.message_item_avatar).getContext()).a(baseViewHolder.getView(R.id.message_item_avatar)).b(this.a.getResources().getColor(R.color.colorRed2)).d(8388661).a(5.0f, 5.0f, true).a(true).c(baseViewHolder.getView(R.id.message_item_avatar).getContext().getResources().getColor(R.color.colorWhite)).b(2.0f, true).a(10.0f, true).a(0);
        } else {
            new e(baseViewHolder.getView(R.id.message_item_avatar).getContext()).a(baseViewHolder.getView(R.id.message_item_avatar)).b(this.a.getResources().getColor(R.color.colorRed2)).d(8388661).a(5.0f, 5.0f, true).a(true).c(baseViewHolder.getView(R.id.message_item_avatar).getContext().getResources().getColor(R.color.colorWhite)).b(2.0f, true).a(10.0f, true).a(-1);
        }
    }
}
