package com.e23.ajn.fragment.first_page.child;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnFocusChangeListener;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.TextView.OnEditorActionListener;
import com.e23.ajn.R;
import com.e23.ajn.b.e;
import com.e23.ajn.b.m;
import com.e23.ajn.b.n;
import com.e23.ajn.base.BaseSupportFragment;
import com.e23.ajn.base.BaseSwipeBackFragment;
import com.e23.ajn.views.k;
import com.jaeger.library.a;
import me.yokeyword.fragmentation.d;
import org.greenrobot.eventbus.j;

public class SearchFragment extends BaseSwipeBackFragment implements OnClickListener {
    private View d;
    private Toolbar e;
    /* access modifiers changed from: private */
    public EditText f;
    private ImageView g;
    private TextView h;
    /* access modifiers changed from: private */
    public BaseSupportFragment[] i = new BaseSupportFragment[2];

    public static SearchFragment h() {
        SearchFragment searchFragment = new SearchFragment();
        searchFragment.setArguments(new Bundle());
        return searchFragment;
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        this.d = layoutInflater.inflate(R.layout.f154list, viewGroup, false);
        a.b(this.c, 0, null);
        a.a((Activity) this.c);
        BaseSupportFragment baseSupportFragment = (BaseSupportFragment) a(SearchKeyWordFragment.class);
        if (baseSupportFragment == null) {
            this.i[0] = SearchKeyWordFragment.h();
            this.i[1] = SearchListFragment.r();
            a((int) R.id.fl_search_container, 0, this.i[0], this.i[1]);
        } else {
            this.i[0] = baseSupportFragment;
            this.i[1] = (BaseSupportFragment) a(SearchListFragment.class);
        }
        i();
        e.a(this.c).a((Object) this);
        return a(this.d);
    }

    private void i() {
        this.e = (Toolbar) this.d.findViewById(R.id.toolbar);
        this.f = (EditText) this.e.findViewById(R.id.toolbar_search_edt);
        this.g = (ImageView) this.e.findViewById(R.id.toolbar_left_back);
        this.g.setOnClickListener(this);
        this.h = (TextView) this.e.findViewById(R.id.toolbar_search);
        this.h.setOnClickListener(this);
        this.f.setOnFocusChangeListener(new OnFocusChangeListener() {
            public void onFocusChange(View view, boolean z) {
                if (z) {
                    SearchFragment.this.a((d) SearchFragment.this.i[0], (d) SearchFragment.this.i[1]);
                }
            }
        });
        this.f.setOnEditorActionListener(new OnEditorActionListener() {
            public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
                if (i == 3) {
                    if (!TextUtils.isEmpty(SearchFragment.this.f.getText().toString())) {
                        e.a(SearchFragment.this.c).c(new m(SearchFragment.this.f.getText().toString()));
                    } else {
                        k.a(SearchFragment.this.c, "请输入关键词！");
                    }
                }
                return false;
            }
        });
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.toolbar_left_back /*2131820915*/:
                this.c.onBackPressed();
                return;
            case R.id.toolbar_search /*2131821169*/:
                if (!TextUtils.isEmpty(this.f.getText().toString())) {
                    e.a(this.c).c(new m(this.f.getText().toString()));
                    return;
                } else {
                    k.a(this.c, "请输入关键词！");
                    return;
                }
            default:
                return;
        }
    }

    @j
    public void onSearchEvent(m mVar) {
        if (!TextUtils.isEmpty(mVar.a)) {
            this.f.setText(mVar.a);
            this.f.clearFocus();
            a((d) this.i[1], (d) this.i[0]);
            ((SearchListFragment) this.i[1]).a(mVar.a);
            ((SearchListFragment) this.i[1]).t();
        }
    }

    @j
    public void onSearchShowKeyboard(n nVar) {
        this.f.requestFocus();
        ((InputMethodManager) this.c.getSystemService("input_method")).toggleSoftInput(0, 2);
    }

    public void onDestroy() {
        super.onDestroy();
        e.a(this.c).b(this);
    }
}
