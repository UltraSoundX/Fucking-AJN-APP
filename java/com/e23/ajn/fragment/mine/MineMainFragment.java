package com.e23.ajn.fragment.mine;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.e23.ajn.R;
import com.e23.ajn.base.BaseMainFragment;
import com.e23.ajn.fragment.mine.child.MineFragment;
import me.yokeyword.fragmentation.d;

public class MineMainFragment extends BaseMainFragment {
    public static MineMainFragment h() {
        Bundle bundle = new Bundle();
        MineMainFragment mineMainFragment = new MineMainFragment();
        mineMainFragment.setArguments(bundle);
        return mineMainFragment;
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        return layoutInflater.inflate(R.layout.f144hdfatie, viewGroup, false);
    }

    public void b(Bundle bundle) {
        super.b(bundle);
        if (b(MineFragment.class) == null) {
            a((int) R.id.fl_mine_container, (d) MineFragment.h());
        }
    }
}
