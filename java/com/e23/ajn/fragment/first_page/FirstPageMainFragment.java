package com.e23.ajn.fragment.first_page;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.e23.ajn.R;
import com.e23.ajn.activity.OutUrlActivity;
import com.e23.ajn.activity.SwipeBackCommonActivity;
import com.e23.ajn.base.BaseMainFragment;
import com.e23.ajn.d.p;
import com.e23.ajn.fragment.first_page.child.FirstPageViewPagerFragment;
import com.jaeger.library.a;
import me.yokeyword.fragmentation.d;

public class FirstPageMainFragment extends BaseMainFragment implements OnClickListener {
    private View d;
    private TextView e;
    private ImageView f;
    private Toolbar g;
    private ImageView h;
    private LinearLayout i;
    private ImageView j;

    public static FirstPageMainFragment h() {
        Bundle bundle = new Bundle();
        FirstPageMainFragment firstPageMainFragment = new FirstPageMainFragment();
        firstPageMainFragment.setArguments(bundle);
        return firstPageMainFragment;
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        this.d = layoutInflater.inflate(R.layout.f132fragment_home, viewGroup, false);
        i();
        return this.d;
    }

    public void onActivityCreated(Bundle bundle) {
        super.onActivityCreated(bundle);
        if (b(FirstPageViewPagerFragment.class) == null) {
            a((int) R.id.fl_first_container, (d) FirstPageViewPagerFragment.h());
        }
    }

    public void b(Bundle bundle) {
        super.b(bundle);
    }

    private void i() {
        this.e = (TextView) this.d.findViewById(R.id.toolbar_search_edt);
        this.f = (ImageView) this.d.findViewById(R.id.toolbar_right_12345);
        this.h = (ImageView) this.d.findViewById(R.id.toolbar_left_logo);
        this.g = (Toolbar) this.d.findViewById(R.id.toolbar);
        this.j = (ImageView) this.d.findViewById(R.id.search_ic_search);
        this.i = (LinearLayout) this.d.findViewById(R.id.toolbar_search_layout);
        if (p.a("is_red", false)) {
            this.g.setBackgroundColor(getResources().getColor(R.color.colorRed));
            this.f.setImageResource(R.mipmap.f261mainrightunloginhead);
            this.h.setImageResource(R.mipmap.f263mall_alipay_logo);
            this.i.setBackground(getResources().getDrawable(R.drawable.toolbar_search_edt_bg_red));
            this.e.setTextColor(getResources().getColor(R.color.colorWhite));
            this.j.setBackgroundResource(R.mipmap.f287rb_home);
        } else {
            this.g.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
            this.f.setImageResource(R.mipmap.f260mainrightpic);
            this.h.setImageResource(R.mipmap.f262mainslidebg);
            this.i.setBackground(getResources().getDrawable(R.drawable.toolbar_search_edt_bg));
            this.e.setTextColor(getResources().getColor(R.color.color_tab_un_select));
            this.j.setBackgroundResource(R.mipmap.f292rb_select_arrow_trans);
        }
        a.b(this.c, 0, null);
        a.a((Activity) this.c);
        this.e.setOnClickListener(this);
        this.f.setOnClickListener(this);
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.toolbar_right_12345 /*2131820852*/:
                Intent intent = new Intent(this.c, OutUrlActivity.class);
                intent.putExtra("url", "http://12345.e23.cn/index.php?m=content&c=index&a=ajnIndex");
                intent.putExtra("title", getString(R.string.zhangshang12345));
                intent.putExtra(OutUrlActivity.ARG_THUMB, "");
                intent.putExtra(OutUrlActivity.ARG_DESC, "");
                startActivity(intent);
                return;
            case R.id.toolbar_search_edt /*2131820855*/:
                startActivity(new Intent(this.c, SwipeBackCommonActivity.class).putExtra(SwipeBackCommonActivity.TAG, 5));
                return;
            default:
                return;
        }
    }
}
