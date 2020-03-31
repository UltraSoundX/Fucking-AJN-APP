package com.flyco.tablayout.b;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import java.util.ArrayList;

/* compiled from: FragmentChangeManager */
public class a {
    private FragmentManager a;
    private ArrayList<Fragment> b;
    private int c;

    public void a(int i) {
        int i2 = 0;
        while (true) {
            int i3 = i2;
            if (i3 < this.b.size()) {
                FragmentTransaction beginTransaction = this.a.beginTransaction();
                Fragment fragment = (Fragment) this.b.get(i3);
                if (i3 == i) {
                    beginTransaction.show(fragment);
                } else {
                    beginTransaction.hide(fragment);
                }
                beginTransaction.commit();
                i2 = i3 + 1;
            } else {
                this.c = i;
                return;
            }
        }
    }
}
