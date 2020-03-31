package com.e23.ajn.fragment.interaction.child;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import com.bumptech.glide.d.b.b;
import com.bumptech.glide.i;
import com.e23.ajn.R;
import com.e23.ajn.base.BaseSwipeBackFragment;
import com.github.chrisbanes.photoview.PhotoView;

public class ReporterHeadShowFragment extends BaseSwipeBackFragment {
    private View d;
    private String e;
    private ImageView f;
    private PhotoView g;

    public static ReporterHeadShowFragment a(String str) {
        ReporterHeadShowFragment reporterHeadShowFragment = new ReporterHeadShowFragment();
        Bundle bundle = new Bundle();
        bundle.putString("url", str);
        reporterHeadShowFragment.setArguments(bundle);
        return reporterHeadShowFragment;
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        if (getArguments() != null) {
            this.e = getArguments().getString("url");
        }
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        this.d = layoutInflater.inflate(R.layout.f151item_published_grida, viewGroup, false);
        h();
        return this.d;
    }

    private void h() {
        this.f = (ImageView) this.d.findViewById(R.id.toolbar_left_back);
        this.f.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                ReporterHeadShowFragment.this.c.onBackPressed();
            }
        });
        this.g = (PhotoView) this.d.findViewById(R.id.reporter_head_view);
        i.a(this.c).a(this.e).b(b.ALL).d((int) R.mipmap.f276plimg_s).c((int) R.mipmap.f276plimg_s).a((ImageView) this.g);
        this.g.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                ReporterHeadShowFragment.this.c.onBackPressed();
            }
        });
    }
}
