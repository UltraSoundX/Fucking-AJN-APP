package com.e23.ajn.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.Toast;
import com.e23.ajn.R;
import com.e23.ajn.adapter.GuideViewPagerAdapter;
import com.e23.ajn.base.BaseSupportActivity;
import com.stub.StubApp;
import java.util.List;

public class WelcomeGuideActivity extends BaseSupportActivity implements OnClickListener {
    private static final int[] f = {R.layout.f160mis_cmp_customer_actionbar, R.layout.f161mis_fragment_multi_image, R.layout.f162mis_list_item_camera, R.layout.f163mis_list_item_folder, R.layout.f164mis_list_item_image};
    public long TOUCH_TIME = 0;
    private ViewPager b;
    private GuideViewPagerAdapter c;
    private List<View> d;
    private ImageView e;

    private class a implements OnPageChangeListener {
        private a() {
        }

        public void onPageScrollStateChanged(int i) {
        }

        public void onPageScrolled(int i, float f, int i2) {
        }

        public void onPageSelected(int i) {
        }
    }

    /* access modifiers changed from: protected */
    public native void onCreate(Bundle bundle);

    static {
        StubApp.interface11(3504);
    }

    public void onBackPressedSupport() {
        if (System.currentTimeMillis() - this.TOUCH_TIME < 2000) {
            ActivityCompat.finishAfterTransition(this);
            return;
        }
        this.TOUCH_TIME = System.currentTimeMillis();
        Toast.makeText(this, R.string.press_again_exit, 0).show();
    }

    public void onClick(View view) {
        if (view.getTag().equals("enter")) {
            Intent intent = new Intent();
            intent.setClass(this, MainActivity.class);
            startActivity(intent);
            finish();
        }
    }
}
