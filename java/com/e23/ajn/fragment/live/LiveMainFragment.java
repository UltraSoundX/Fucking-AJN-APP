package com.e23.ajn.fragment.live;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.e23.ajn.R;
import com.e23.ajn.activity.OutUrlActivity;
import com.e23.ajn.base.BaseMainFragment;
import com.e23.ajn.fragment.live.child.LiveListFragment;
import com.e23.ajn.fragment.live.child.LiveViewPagerFragment;
import com.jaeger.library.a;
import me.yokeyword.fragmentation.d;

public class LiveMainFragment extends BaseMainFragment {
    private View d;
    private Toolbar e;
    private TextView f;
    private ImageView g;
    private ImageView h;

    public static LiveMainFragment h() {
        Bundle bundle = new Bundle();
        LiveMainFragment liveMainFragment = new LiveMainFragment();
        liveMainFragment.setArguments(bundle);
        return liveMainFragment;
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        this.d = layoutInflater.inflate(R.layout.f135fuwu, viewGroup, false);
        i();
        return this.d;
    }

    public void b(Bundle bundle) {
        super.b(bundle);
        if (b(LiveListFragment.class) == null) {
            a((int) R.id.fl_live_container, (d) LiveViewPagerFragment.h());
        }
    }

    private void i() {
        a.b(this.c, 0, null);
        a.a((Activity) this.c);
        this.e = (Toolbar) this.d.findViewById(R.id.toolbar);
        this.f = (TextView) this.d.findViewById(R.id.toolbar_center_title);
        this.f.setText(getString(R.string.live_top_title));
        this.g = (ImageView) this.d.findViewById(R.id.live_toolbar_center_logo);
        this.g.setVisibility(0);
        this.h = (ImageView) this.d.findViewById(R.id.live_toolbar_ask);
        this.h.setVisibility(0);
        this.h.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                Intent intent = new Intent(LiveMainFragment.this.c, OutUrlActivity.class);
                intent.putExtra("url", "http://appc.e23.cn/index.php?m=content&c=index&a=show&catid=44&id=331782");
                intent.putExtra("title", "");
                intent.putExtra(OutUrlActivity.ARG_THUMB, "");
                intent.putExtra(OutUrlActivity.ARG_DESC, "");
                LiveMainFragment.this.c.startActivity(intent);
            }
        });
    }
}
