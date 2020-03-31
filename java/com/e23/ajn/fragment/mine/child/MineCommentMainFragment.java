package com.e23.ajn.fragment.mine.child;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.e23.ajn.R;
import com.e23.ajn.base.BaseSupportFragment;
import com.jaeger.library.a;
import me.yokeyword.fragmentation.d;

public class MineCommentMainFragment extends BaseSupportFragment implements OnClickListener {
    private View a;
    private TextView d;
    private TextView e;
    private ImageView f;
    private LinearLayout g;
    private BaseSupportFragment[] h = new BaseSupportFragment[2];
    private TextView[] i = new TextView[2];
    private int j = 0;
    private int k;

    public static MineCommentMainFragment a(int i2) {
        MineCommentMainFragment mineCommentMainFragment = new MineCommentMainFragment();
        Bundle bundle = new Bundle();
        bundle.putInt("type", i2);
        mineCommentMainFragment.setArguments(bundle);
        return mineCommentMainFragment;
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        if (getArguments() != null) {
            this.k = getArguments().getInt("type");
        }
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        this.a = layoutInflater.inflate(R.layout.f140gwmlist, viewGroup, false);
        if (this.h[0] == null) {
            this.h[0] = MineCommentListFragment.a(0);
            this.h[1] = MineCommentListFragment.a(1);
            if (this.k == 0) {
                a((int) R.id.fl_rank_container, 0, this.h[0], this.h[1]);
                this.j = 0;
            }
            if (this.k == 1) {
                a((int) R.id.fl_rank_container, 1, this.h[0], this.h[1]);
                this.j = 1;
            }
        } else {
            this.h[0] = this.h[0];
            this.h[1] = (BaseSupportFragment) a(this.h[1].getClass());
        }
        h();
        return this.a;
    }

    private void h() {
        a.b(this.c, 0, null);
        a.a((Activity) this.c);
        this.f = (ImageView) this.a.findViewById(R.id.toolbar_left_back);
        this.f.setVisibility(0);
        this.f.setOnClickListener(this);
        this.d = (TextView) this.a.findViewById(R.id.fragment_comment_main_tv_reply);
        this.d.setOnClickListener(this);
        this.i[0] = this.d;
        this.e = (TextView) this.a.findViewById(R.id.fragment_comment_main_tv_comment);
        this.e.setOnClickListener(this);
        this.i[1] = this.e;
        this.g = (LinearLayout) this.a.findViewById(R.id.fragment_follow_ll_line);
        if (this.k == 1) {
            this.i[0].setTextColor(getResources().getColor(R.color.fragment_mine_comment_main_color_select_n));
            this.i[1].setTextColor(getResources().getColor(R.color.colorBlack0));
            this.g.getChildAt(0).setBackgroundColor(getResources().getColor(R.color.white));
            this.g.getChildAt(1).setBackgroundColor(getResources().getColor(R.color.colorBlue));
        }
    }

    public void onDestroy() {
        super.onDestroy();
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.toolbar_left_back /*2131820915*/:
                this.c.onBackPressed();
                return;
            case R.id.fragment_comment_main_tv_reply /*2131820916*/:
                if (this.j != 0) {
                    a(0, this.j);
                    return;
                }
                return;
            case R.id.fragment_comment_main_tv_comment /*2131820917*/:
                if (this.j != 1) {
                    a(1, this.j);
                    return;
                }
                return;
            default:
                return;
        }
    }

    private void a(int i2, int i3) {
        this.j = i2;
        this.i[i3].setTextColor(getResources().getColor(R.color.fragment_mine_comment_main_color_select_n));
        this.i[i2].setTextColor(getResources().getColor(R.color.colorBlack0));
        this.g.getChildAt(i3).setBackgroundColor(getResources().getColor(R.color.white));
        this.g.getChildAt(i2).setBackgroundColor(getResources().getColor(R.color.colorBlue));
        a((d) this.h[i2], (d) this.h[i3]);
    }
}
