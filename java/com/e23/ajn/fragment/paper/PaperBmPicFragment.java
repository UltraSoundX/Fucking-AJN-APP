package com.e23.ajn.fragment.paper;

import android.app.Activity;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.support.v7.app.a.C0012a;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import com.e23.ajn.R;
import com.e23.ajn.b.e;
import com.e23.ajn.b.l;
import com.e23.ajn.base.BaseSwipeBackFragment;
import com.e23.ajn.c.b;
import com.e23.ajn.model.PaperBmPicResponse;
import com.e23.ajn.model.PaperBmPicResponse.DataBean;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.builder.PostFormBuilder;
import java.util.ArrayList;
import java.util.List;
import me.yokeyword.fragmentation.d;
import okhttp3.Call;
import okhttp3.Request;
import org.greenrobot.eventbus.j;

public class PaperBmPicFragment extends BaseSwipeBackFragment {
    OnClickListener d = new OnClickListener() {
        public void onClick(View view) {
            int i = 0;
            LinearLayout linearLayout = new LinearLayout(PaperBmPicFragment.this.c);
            linearLayout.setLayoutParams(new LayoutParams(-1, -2));
            ListView listView = new ListView(PaperBmPicFragment.this.c);
            listView.setFadingEdgeLength(0);
            ArrayList arrayList = new ArrayList();
            while (true) {
                int i2 = i;
                if (i2 < PaperBmPicFragment.this.u.size()) {
                    arrayList.add(((DataBean) PaperBmPicFragment.this.u.get(i2)).getVerOrder() + "." + ((DataBean) PaperBmPicFragment.this.u.get(i2)).getVerName());
                    i = i2 + 1;
                } else {
                    listView.setAdapter(new ArrayAdapter(PaperBmPicFragment.this.c, 17367046, arrayList));
                    linearLayout.addView(listView);
                    final android.support.v7.app.a b = new C0012a(PaperBmPicFragment.this.c).a((CharSequence) PaperBmPicFragment.this.getString(R.string.choosever)).b((View) linearLayout).b(PaperBmPicFragment.this.getString(R.string.cancel), new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialogInterface, int i) {
                            dialogInterface.cancel();
                        }
                    }).b();
                    b.setCanceledOnTouchOutside(true);
                    b.show();
                    listView.setOnItemClickListener(new OnItemClickListener() {
                        public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
                            PaperBmPicFragment.this.n.setCurrentItem(i);
                            b.cancel();
                        }
                    });
                    return;
                }
            }
        }
    };
    private View e;
    private Toolbar f;
    private TextView g;
    private ImageView h;
    private String i;
    /* access modifiers changed from: private */
    public String j;
    /* access modifiers changed from: private */
    public String k;
    /* access modifiers changed from: private */
    public String l;
    private TextView m;
    /* access modifiers changed from: private */
    public ViewPager n;
    /* access modifiers changed from: private */
    public RelativeLayout o;
    private Button p;
    /* access modifiers changed from: private */

    /* renamed from: q reason: collision with root package name */
    public Button f416q;
    private Button r;
    private a s;
    /* access modifiers changed from: private */
    public int t = 0;
    /* access modifiers changed from: private */
    public List<DataBean> u = new ArrayList();
    private OnClickListener v = new OnClickListener() {
        public void onClick(View view) {
            PaperBmPicFragment.this.a((d) HistoryPaperFragment.a(PaperBmPicFragment.this.k, PaperBmPicFragment.this.l));
        }
    };

    private class a extends FragmentStatePagerAdapter {
        private List<DataBean> b;

        public a(FragmentManager fragmentManager, List<DataBean> list) {
            super(fragmentManager);
            this.b = list;
        }

        public void a(List<DataBean> list) {
            if (this.b != null) {
                this.b.clear();
                this.b = list;
            }
            notifyDataSetChanged();
        }

        public int getCount() {
            return this.b.size();
        }

        public int getItemPosition(Object obj) {
            return -2;
        }

        public Fragment getItem(int i) {
            return VerFragment.a(((DataBean) this.b.get(i)).getBm(), ((DataBean) this.b.get(i)).getPaperid(), ((DataBean) this.b.get(i)).getVerName(), ((DataBean) this.b.get(i)).getVerOrder(), PaperBmPicFragment.this.j);
        }
    }

    public static PaperBmPicFragment a(String str, String str2, String str3, String str4) {
        Bundle bundle = new Bundle();
        PaperBmPicFragment paperBmPicFragment = new PaperBmPicFragment();
        bundle.putString("paper_pid", str);
        bundle.putString("paper_name", str2);
        bundle.putString("paper_type", str3);
        bundle.putString("paper_date", str4);
        paperBmPicFragment.setArguments(bundle);
        return paperBmPicFragment;
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        if (getArguments() != null) {
            this.i = getArguments().getString("paper_pid");
            this.j = getArguments().getString("paper_name");
            this.k = getArguments().getString("paper_type");
            this.l = getArguments().getString("paper_date");
        }
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        this.e = layoutInflater.inflate(R.layout.f148hydy_item, viewGroup, false);
        this.s = new a(this.c.getSupportFragmentManager(), this.u);
        b(this.e);
        e.a(this.c).a((Object) this);
        h();
        return this.e;
    }

    private void b(View view) {
        com.jaeger.library.a.b(this.c, 0, null);
        com.jaeger.library.a.a((Activity) this.c);
        this.f = (Toolbar) view.findViewById(R.id.toolbar);
        this.g = (TextView) this.f.findViewById(R.id.toolbar_center_title);
        this.h = (ImageView) this.f.findViewById(R.id.toolbar_left_back);
        this.h.setVisibility(0);
        this.h.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                PaperBmPicFragment.this.c.onBackPressed();
            }
        });
        this.g.setText(getString(R.string.paper_read));
        if (this.k.equals("jinrb")) {
            this.g.setText(getString(R.string.jinrbtime));
        }
        if (this.k.equals("jnsb")) {
            this.g.setText(getString(R.string.jnsbtime));
        }
        if (this.k.equals("dsnb")) {
            this.g.setText(getString(R.string.dsnbtime));
        }
        if (this.k.equals("ddjkb")) {
            this.g.setText(getString(R.string.ddjkbtime));
        }
        if (this.k.equals("rkdb")) {
            this.g.setText(getString(R.string.rkdbtime));
        }
        this.m = (TextView) view.findViewById(R.id.wangqiliulan);
        this.m.setVisibility(0);
        this.m.setOnClickListener(this.v);
        this.n = (ViewPager) view.findViewById(2131820758);
        this.o = (RelativeLayout) view.findViewById(R.id.jumplayout);
        this.p = (Button) view.findViewById(R.id.prever);
        this.f416q = (Button) view.findViewById(R.id.vershow);
        this.f416q.setOnClickListener(this.d);
        this.r = (Button) view.findViewById(R.id.nextver);
        this.n.setAdapter(this.s);
        this.n.setOnPageChangeListener(new OnPageChangeListener() {
            public void onPageSelected(int i) {
                PaperBmPicFragment.this.f416q.setText(((DataBean) PaperBmPicFragment.this.u.get(i)).getVerOrder() + "." + ((DataBean) PaperBmPicFragment.this.u.get(i)).getVerName());
                PaperBmPicFragment.this.t = i;
            }

            public void onPageScrolled(int i, float f, int i2) {
            }

            public void onPageScrollStateChanged(int i) {
            }
        });
    }

    private void h() {
        ((PostFormBuilder) OkHttpUtils.post().url("http://appc.e23.cn/index.php?m=api&c=paper&a=paperBmListJson")).params(b.a(null)).addParams("bz", this.k).addParams("pid", this.i).build().execute(new com.e23.ajn.c.a<PaperBmPicResponse>() {
            public void onBefore(Request request, int i) {
            }

            public void onError(Call call, Exception exc, int i) {
            }

            /* renamed from: a */
            public void onResponse(PaperBmPicResponse paperBmPicResponse, int i) {
                if (paperBmPicResponse != null && paperBmPicResponse.getCode() == 200 && com.e23.ajn.d.e.b(paperBmPicResponse.getData())) {
                    PaperBmPicFragment.this.n.setVisibility(0);
                    PaperBmPicFragment.this.o.setVisibility(0);
                    PaperBmPicFragment.this.u = paperBmPicResponse.getData();
                    PaperBmPicFragment.this.i();
                }
            }
        });
    }

    /* access modifiers changed from: private */
    public void i() {
        if (this.u.size() > 0) {
            this.s.a(this.u);
            this.f416q.setText(((DataBean) this.u.get(1)).getVerName());
            this.n.setCurrentItem(0);
        } else {
            this.n.setVisibility(8);
            this.o.setVisibility(8);
        }
        this.p.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                if (PaperBmPicFragment.this.t == 0) {
                    Toast.makeText(PaperBmPicFragment.this.c, PaperBmPicFragment.this.getString(R.string.isfirstver), 1).show();
                } else {
                    PaperBmPicFragment.this.n.setCurrentItem(PaperBmPicFragment.this.t - 1);
                }
            }
        });
        this.r.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                if (PaperBmPicFragment.this.t == PaperBmPicFragment.this.u.size() - 1) {
                    Toast.makeText(PaperBmPicFragment.this.c, PaperBmPicFragment.this.getString(R.string.islastver), 1).show();
                } else {
                    PaperBmPicFragment.this.n.setCurrentItem(PaperBmPicFragment.this.t + 1);
                }
            }
        });
    }

    @j
    public void onReloadPaperEvent(l lVar) {
        if (lVar != null && !TextUtils.isEmpty(lVar.a)) {
            this.i = lVar.a;
            h();
        }
    }

    public void onDestroyView() {
        super.onDestroyView();
        e.a(this.c).b(this);
    }
}
