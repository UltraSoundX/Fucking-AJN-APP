package com.e23.ajn.base;

import android.content.Context;
import com.e23.ajn.fragment.first_page.FirstPageMainFragment;

public abstract class BaseMainFragment extends BaseSupportFragment {
    protected a a;

    public interface a {
        void onBackToFirstFragment();
    }

    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof a) {
            this.a = (a) context;
            return;
        }
        throw new RuntimeException(context.toString() + " must implement OnBackToFirstListener");
    }

    public void onDetach() {
        super.onDetach();
        this.a = null;
    }

    public boolean a() {
        if (getChildFragmentManager().getBackStackEntryCount() > 1) {
            g();
            return true;
        } else if (this instanceof FirstPageMainFragment) {
            return false;
        } else {
            this.a.onBackToFirstFragment();
            return true;
        }
    }
}
