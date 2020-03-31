package com.e23.ajn.fragment.first_page.child;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import com.bumptech.glide.d.b.b;
import com.bumptech.glide.i;
import com.e23.ajn.R;
import com.e23.ajn.base.BaseSupportFragment;
import com.github.chrisbanes.photoview.PhotoView;

public class EnlargeFragment extends BaseSupportFragment {
    public static EnlargeFragment a(String str) {
        EnlargeFragment enlargeFragment = new EnlargeFragment();
        Bundle bundle = new Bundle();
        bundle.putString("url", str);
        enlargeFragment.setArguments(bundle);
        return enlargeFragment;
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        PhotoView photoView = new PhotoView(getActivity());
        String string = getArguments().getString("url");
        i.a(this.c).a(string).b(b.ALL).d((int) R.mipmap.f276plimg_s).c((int) R.mipmap.f276plimg_s).a((ImageView) photoView);
        photoView.setTag(string);
        return photoView;
    }
}
