package com.e23.ajn.fragment.paper;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import com.bumptech.glide.d.b.b;
import com.bumptech.glide.i;
import com.e23.ajn.activity.SwipeBackCommonActivity;
import com.e23.ajn.base.BaseSupportFragment;
import com.e23.ajn.views.ZoomableImageView;

public class VerFragment extends BaseSupportFragment {
    public static VerFragment a(String str, String str2, String str3, String str4, String str5) {
        VerFragment verFragment = new VerFragment();
        Bundle bundle = new Bundle();
        bundle.putString("url", str);
        bundle.putString("pid", str2);
        bundle.putString("vername", str3);
        bundle.putString("verorder", str4);
        bundle.putString("cname", str5);
        verFragment.setArguments(bundle);
        return verFragment;
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        ZoomableImageView zoomableImageView = new ZoomableImageView(getActivity());
        String string = getArguments().getString("url");
        final String string2 = getArguments().getString("pid");
        final String string3 = getArguments().getString("vername");
        final String string4 = getArguments().getString("verorder");
        final String string5 = getArguments().getString("cname");
        zoomableImageView.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                Intent intent = new Intent(VerFragment.this.c, SwipeBackCommonActivity.class);
                intent.putExtra(SwipeBackCommonActivity.TAG, 52);
                intent.putExtra("paper_pid", string2);
                intent.putExtra("paper_name", string3);
                intent.putExtra("paper_type", string4);
                intent.putExtra("paper_date", string5);
                VerFragment.this.c.startActivity(intent);
            }
        });
        i.a(getActivity()).a(string).h().b(b.SOURCE).a((ImageView) zoomableImageView);
        zoomableImageView.setTag(string);
        return zoomableImageView;
    }
}
