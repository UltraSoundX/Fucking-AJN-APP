package com.lzy.imagepicker.adapter;

import android.app.Activity;
import android.support.v4.view.PagerAdapter;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewGroup;
import com.lzy.imagepicker.b;
import com.lzy.imagepicker.b.d;
import com.lzy.imagepicker.bean.ImageItem;
import java.util.ArrayList;
import uk.co.senab.photoview.PhotoView;
import uk.co.senab.photoview.d.C0087d;

public class ImagePageAdapter extends PagerAdapter {
    public a a;
    private int b;
    private int c;
    private b d;
    private ArrayList<ImageItem> e = new ArrayList<>();
    private Activity f;

    public interface a {
        void a(View view, float f, float f2);
    }

    public ImagePageAdapter(Activity activity, ArrayList<ImageItem> arrayList) {
        this.f = activity;
        this.e = arrayList;
        DisplayMetrics b2 = d.b(activity);
        this.b = b2.widthPixels;
        this.c = b2.heightPixels;
        this.d = b.a();
    }

    public void a(ArrayList<ImageItem> arrayList) {
        this.e = arrayList;
    }

    public void a(a aVar) {
        this.a = aVar;
    }

    public Object instantiateItem(ViewGroup viewGroup, int i) {
        PhotoView photoView = new PhotoView(this.f);
        this.d.l().a(this.f, ((ImageItem) this.e.get(i)).b, photoView, this.b, this.c);
        photoView.setOnPhotoTapListener(new C0087d() {
            public void a(View view, float f, float f2) {
                if (ImagePageAdapter.this.a != null) {
                    ImagePageAdapter.this.a.a(view, f, f2);
                }
            }
        });
        viewGroup.addView(photoView);
        return photoView;
    }

    public int getCount() {
        return this.e.size();
    }

    public boolean isViewFromObject(View view, Object obj) {
        return view == obj;
    }

    public void destroyItem(ViewGroup viewGroup, int i, Object obj) {
        viewGroup.removeView((View) obj);
    }

    public int getItemPosition(Object obj) {
        return -2;
    }
}
