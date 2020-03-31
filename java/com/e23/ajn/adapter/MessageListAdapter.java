package com.e23.ajn.adapter;

import android.content.Context;
import android.widget.ImageView;
import com.bumptech.glide.d.b.b;
import com.bumptech.glide.i;
import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.e23.ajn.R;
import com.e23.ajn.d.f;
import com.e23.ajn.model.MessageBean;
import com.e23.ajn.views.CirleImageView;
import java.util.List;

public class MessageListAdapter extends BaseMultiItemQuickAdapter<MessageBean, BaseViewHolder> {
    private Context a;
    private MessageBean b;

    public MessageListAdapter(Context context, List<MessageBean> list, MessageBean messageBean) {
        super(list);
        this.a = context;
        this.b = messageBean;
        addItemType(1, R.layout.f122chat);
        addItemType(0, R.layout.chat);
    }

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public void convert(BaseViewHolder baseViewHolder, MessageBean messageBean) {
        switch (baseViewHolder.getItemViewType()) {
            case 0:
                i.b(this.a).a(this.b.getSend_from_avatar()).h().b(b.ALL).d((int) R.mipmap.f278plitemding).c((int) R.mipmap.f278plitemding).a((ImageView) (CirleImageView) baseViewHolder.getView(R.id.message_item_avatar));
                baseViewHolder.setText((int) R.id.tv_chatcontent, (CharSequence) f.d(messageBean.getContent()));
                baseViewHolder.setText((int) R.id.tv_time, (CharSequence) messageBean.getMessage_time());
                return;
            case 1:
                i.b(this.a).a(this.b.getSend_to_avatar()).h().b(b.ALL).d((int) R.mipmap.f278plitemding).c((int) R.mipmap.f278plitemding).a((ImageView) (CirleImageView) baseViewHolder.getView(R.id.message_item_avatar));
                baseViewHolder.setText((int) R.id.tv_chatcontent, (CharSequence) f.d(messageBean.getContent()));
                baseViewHolder.setText((int) R.id.tv_time, (CharSequence) messageBean.getMessage_time());
                return;
            default:
                return;
        }
    }
}
