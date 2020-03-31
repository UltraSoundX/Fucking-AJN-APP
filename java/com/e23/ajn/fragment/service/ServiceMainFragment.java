package com.e23.ajn.fragment.service;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.e23.ajn.R;
import com.e23.ajn.base.BaseMainFragment;
import com.e23.ajn.fragment.service.child.ServiceFragment;
import com.jaeger.library.a;
import me.yokeyword.fragmentation.d;

public class ServiceMainFragment extends BaseMainFragment {
    private View d;
    private Toolbar e;
    private TextView f;

    public static ServiceMainFragment h() {
        Bundle bundle = new Bundle();
        ServiceMainFragment serviceMainFragment = new ServiceMainFragment();
        serviceMainFragment.setArguments(bundle);
        return serviceMainFragment;
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        this.d = layoutInflater.inflate(R.layout.f157main_old, viewGroup, false);
        i();
        return this.d;
    }

    public void b(Bundle bundle) {
        super.b(bundle);
        if (b(ServiceFragment.class) == null) {
            a((int) R.id.fl_service_container, (d) ServiceFragment.h());
        }
    }

    private void i() {
        a.b(this.c, 0, null);
        a.a((Activity) this.c);
        this.e = (Toolbar) this.d.findViewById(R.id.toolbar);
        this.f = (TextView) this.d.findViewById(R.id.toolbar_center_title);
        this.f.setText(getString(R.string.service));
    }
}
