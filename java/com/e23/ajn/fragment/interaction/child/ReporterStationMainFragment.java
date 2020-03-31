package com.e23.ajn.fragment.interaction.child;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.e23.ajn.R;
import com.e23.ajn.base.BaseSupportFragment;
import com.e23.ajn.fragment.first_page.child.FirstPageViewPagerFragment;
import com.jaeger.library.a;
import me.yokeyword.fragmentation.d;

public class ReporterStationMainFragment extends BaseSupportFragment {
    private View a;
    private TextView d;
    private ImageView e;
    private String f;

    public static ReporterStationMainFragment a(String str) {
        Bundle bundle = new Bundle();
        ReporterStationMainFragment reporterStationMainFragment = new ReporterStationMainFragment();
        bundle.putString("cat_name", str);
        reporterStationMainFragment.setArguments(bundle);
        return reporterStationMainFragment;
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        if (getArguments() != null) {
            this.f = getArguments().getString("cat_name");
        }
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        this.a = layoutInflater.inflate(R.layout.f153jinanhao_list_item, viewGroup, false);
        h();
        return this.a;
    }

    public void onActivityCreated(Bundle bundle) {
        super.onActivityCreated(bundle);
        if (b(FirstPageViewPagerFragment.class) == null) {
            a((int) R.id.fl_reporter_container, (d) ReporterStationViewPagerFragment.h());
        }
    }

    public void b(Bundle bundle) {
        super.b(bundle);
    }

    private void h() {
        a.b(this.c, 0, null);
        a.a((Activity) this.c);
        this.d = (TextView) this.a.findViewById(R.id.toolbar_center_title);
        this.d.setText(this.f.replace("\n\r", ""));
        this.d.setVisibility(0);
        this.e = (ImageView) this.a.findViewById(R.id.toolbar_left_back);
        this.e.setVisibility(0);
        this.e.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                ReporterStationMainFragment.this.c.onBackPressed();
            }
        });
    }
}
