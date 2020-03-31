package com.tendyron.liveness.motion.view;

import android.content.Context;
import android.graphics.drawable.AnimationDrawable;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.tendyron.liveness.R;
import java.util.Arrays;

/* compiled from: MotionPagerAdapter */
public class c extends PagerAdapter {
    private int[] a;

    public c(int[] iArr) {
        this.a = Arrays.copyOf(iArr, iArr.length);
    }

    public void destroyItem(ViewGroup viewGroup, int i, Object obj) {
        viewGroup.removeView((View) obj);
    }

    public Object instantiateItem(ViewGroup viewGroup, int i) {
        Context context = viewGroup.getContext();
        View inflate = View.inflate(context, R.layout.common_view_motion, null);
        TextView textView = (TextView) inflate.findViewById(R.id.txt_title);
        ImageView imageView = (ImageView) inflate.findViewById(R.id.img_image);
        switch (this.a[i]) {
            case 0:
                textView.setText(context.getString(R.string.common_blink));
                imageView.setImageResource(R.drawable.common_img_blink);
                break;
            case 1:
                textView.setText(context.getString(R.string.common_mouth));
                imageView.setImageResource(R.drawable.common_img_mouth);
                break;
            case 2:
                textView.setText(context.getString(R.string.common_yaw));
                imageView.setImageResource(R.drawable.common_img_yaw);
                break;
            case 3:
                textView.setText(context.getString(R.string.common_nod));
                imageView.setImageResource(R.drawable.common_img_nod);
                break;
        }
        ((AnimationDrawable) imageView.getDrawable()).start();
        viewGroup.addView(inflate);
        return inflate;
    }

    public int getCount() {
        if (this.a == null) {
            return 0;
        }
        return this.a.length;
    }

    public boolean isViewFromObject(View view, Object obj) {
        return view == obj;
    }
}
