package com.e23.ajn.fragment.interaction;

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
import com.e23.ajn.activity.SwipeBackCommonActivity;
import com.e23.ajn.base.BaseMainFragment;
import com.e23.ajn.d.p;
import com.e23.ajn.fragment.first_page.child.NewsListFragment;
import com.e23.ajn.fragment.interaction.child.InteractionFragment;
import com.jaeger.library.a;
import me.yokeyword.fragmentation.d;

public class InteractionMainFragment extends BaseMainFragment implements OnClickListener {
    private Toolbar d;
    private TextView e;
    private ImageView f;
    private ImageView g;
    private View h;

    public static InteractionMainFragment h() {
        Bundle bundle = new Bundle();
        InteractionMainFragment interactionMainFragment = new InteractionMainFragment();
        interactionMainFragment.setArguments(bundle);
        return interactionMainFragment;
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        this.h = layoutInflater.inflate(R.layout.f133fragment_planet, viewGroup, false);
        i();
        return this.h;
    }

    private void i() {
        a.b(this.c, 0, null);
        a.a((Activity) this.c);
        this.d = (Toolbar) this.h.findViewById(R.id.toolbar);
        this.e = (TextView) this.h.findViewById(R.id.toolbar_center_title);
        this.e.setText(getString(R.string.interaction_bbs));
        ((ImageView) this.h.findViewById(R.id.interaction_toolbar_center_logo)).setVisibility(0);
        this.f = (ImageView) this.h.findViewById(R.id.toolbar_add);
        this.f.setVisibility(0);
        this.f.setOnClickListener(this);
        this.g = (ImageView) this.h.findViewById(R.id.toolbar_left_reporter);
        this.g.setOnClickListener(this);
    }

    public void b(Bundle bundle) {
        super.b(bundle);
        if (b(NewsListFragment.class) == null) {
            a((int) R.id.fl_interaction_container, (d) InteractionFragment.a("7", 3, "", false));
        }
    }

    public void onClick(View view) {
        Intent intent = new Intent(this.c, SwipeBackCommonActivity.class);
        switch (view.getId()) {
            case R.id.toolbar_left_reporter /*2131821013*/:
                intent.putExtra(SwipeBackCommonActivity.TAG, 35);
                startActivity(intent);
                return;
            case R.id.toolbar_add /*2131821016*/:
                if (p.a("is_logined", false)) {
                    intent.putExtra("catid", "7");
                    intent.putExtra("reporter_id", "0");
                    intent.putExtra(SwipeBackCommonActivity.TAG, 38);
                } else {
                    intent.putExtra(SwipeBackCommonActivity.TAG, 21);
                }
                startActivity(intent);
                return;
            default:
                return;
        }
    }
}
