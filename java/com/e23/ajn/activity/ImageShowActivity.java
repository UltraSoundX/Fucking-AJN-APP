package com.e23.ajn.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.widget.TextView;
import com.e23.ajn.fragment.first_page.child.EnlargeFragment;
import com.e23.ajn.views.HackyViewPager;
import com.stub.StubApp;
import java.util.ArrayList;

public class ImageShowActivity extends SwipeBackCommonActivity {
    /* access modifiers changed from: private */
    public TextView c;
    /* access modifiers changed from: private */
    public HackyViewPager d;
    /* access modifiers changed from: private */
    public int e;
    /* access modifiers changed from: private */
    public ArrayList<String> f = new ArrayList<>();
    /* access modifiers changed from: private */
    public int g;
    private FragmentStatePagerAdapter h = new FragmentStatePagerAdapter(getSupportFragmentManager()) {
        public int getCount() {
            return ImageShowActivity.this.f.size();
        }

        public Fragment getItem(int i) {
            return EnlargeFragment.a((String) ImageShowActivity.this.f.get(i));
        }
    };

    static {
        StubApp.interface11(3482);
    }

    /* access modifiers changed from: protected */
    public native void onCreate(Bundle bundle);
}
