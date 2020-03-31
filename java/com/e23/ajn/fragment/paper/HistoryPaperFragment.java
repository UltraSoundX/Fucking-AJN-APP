package com.e23.ajn.fragment.paper;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import com.e23.ajn.R;
import com.e23.ajn.b.l;
import com.e23.ajn.base.BaseSwipeBackFragment;
import com.e23.ajn.calendar.CalendarCard;
import com.e23.ajn.d.e;
import com.e23.ajn.model.PaperMonthBzResponse;
import com.e23.ajn.model.PaperMonthBzResponse.DataBean;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.builder.GetBuilder;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import okhttp3.Call;
import okhttp3.Request;

public class HistoryPaperFragment extends BaseSwipeBackFragment implements com.e23.ajn.calendar.CalendarCard.b {
    private View d;
    private Toolbar e;
    private TextView f;
    private ImageView g;
    private String h;
    /* access modifiers changed from: private */
    public String i;
    private LinearLayout j;
    /* access modifiers changed from: private */
    public Spinner k;
    /* access modifiers changed from: private */
    public Spinner l;
    /* access modifiers changed from: private */
    public String m;
    /* access modifiers changed from: private */
    public String n;
    /* access modifiers changed from: private */
    public ArrayList<String> o;
    /* access modifiers changed from: private */
    public ArrayList<String> p;
    /* access modifiers changed from: private */

    /* renamed from: q reason: collision with root package name */
    public String[] f415q = {"一月", "二月", "三月", "四月", "五月", "六月", "七月", "八月", "九月", "十月", "十一月", "十二月"};
    /* access modifiers changed from: private */
    public ArrayAdapter<String> r;
    private ArrayAdapter<String> s;
    /* access modifiers changed from: private */
    public List<DataBean> t;
    private int u;

    class a implements OnItemSelectedListener {
        a() {
        }

        public void onItemSelected(AdapterView<?> adapterView, View view, int i, long j) {
        }

        public void onNothingSelected(AdapterView<?> adapterView) {
        }
    }

    class b implements OnItemSelectedListener {
        b() {
        }

        public void onItemSelected(AdapterView<?> adapterView, View view, int i, long j) {
            HistoryPaperFragment.this.m = (String) HistoryPaperFragment.this.o.get(HistoryPaperFragment.this.k.getSelectedItemPosition());
            if (HistoryPaperFragment.this.m.equals(Calendar.getInstance().get(1) + "")) {
                HistoryPaperFragment.this.p.clear();
                for (int i2 = 0; i2 < Calendar.getInstance().get(2) + 1; i2++) {
                    HistoryPaperFragment.this.p.add(HistoryPaperFragment.this.f415q[i2]);
                }
            } else {
                HistoryPaperFragment.this.p.clear();
                for (String add : HistoryPaperFragment.this.f415q) {
                    HistoryPaperFragment.this.p.add(add);
                }
            }
            HistoryPaperFragment.this.r.notifyDataSetChanged();
        }

        public void onNothingSelected(AdapterView<?> adapterView) {
        }
    }

    public static HistoryPaperFragment a(String str, String str2) {
        Bundle bundle = new Bundle();
        HistoryPaperFragment historyPaperFragment = new HistoryPaperFragment();
        bundle.putString("paper_type", str);
        bundle.putString("paper_date", str2);
        historyPaperFragment.setArguments(bundle);
        return historyPaperFragment;
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        if (getArguments() != null) {
            this.h = getArguments().getString("paper_type");
            this.i = getArguments().getString("paper_date");
        }
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        this.d = layoutInflater.inflate(R.layout.f104include_top_bar, viewGroup, false);
        if (this.h.equals("jinrb")) {
            this.u = 1990;
        }
        if (this.h.equals("jnsb")) {
            this.u = 2008;
        }
        if (this.h.equals("dsnb")) {
            this.u = 2008;
        }
        if (this.h.equals("ddjkb")) {
            this.u = 2008;
        }
        if (this.h.equals("rkdb")) {
            this.u = 2008;
        }
        b(this.d);
        h();
        return this.d;
    }

    private void b(View view) {
        com.jaeger.library.a.b(this.c, 0, null);
        com.jaeger.library.a.a((Activity) this.c);
        this.e = (Toolbar) view.findViewById(R.id.toolbar);
        this.f = (TextView) this.e.findViewById(R.id.toolbar_center_title);
        this.g = (ImageView) this.e.findViewById(R.id.toolbar_left_back);
        this.g.setVisibility(0);
        this.g.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                HistoryPaperFragment.this.c.onBackPressed();
            }
        });
        this.f.setText(getString(R.string.paper_read));
        this.l = (Spinner) view.findViewById(R.id.mspinner);
        this.p = new ArrayList<>();
        for (String add : this.f415q) {
            this.p.add(add);
        }
        this.r = new ArrayAdapter<>(this.c, 17367048, this.p);
        this.r.setDropDownViewResource(17367049);
        this.l.setAdapter(this.r);
        this.n = this.i.substring(5, 7);
        this.l.setSelection(Integer.parseInt(this.i.substring(5, 7)) - 1);
        this.l.setOnItemSelectedListener(new a());
        this.k = (Spinner) view.findViewById(R.id.yspinner);
        int i2 = (Calendar.getInstance().get(1) - this.u) + 1;
        this.o = new ArrayList<>();
        int i3 = 0;
        for (int i4 = 0; i4 < i2; i4++) {
            this.o.add((this.u + i4) + "");
            if (((this.u + i4) + "").equals(this.i.substring(0, 4))) {
                this.m = this.i.substring(0, 4);
                i3 = i4;
            }
        }
        this.s = new ArrayAdapter<>(this.c, 17367048, this.o);
        this.s.setDropDownViewResource(17367049);
        this.k.setAdapter(this.s);
        this.k.setSelection(i3);
        this.k.setOnItemSelectedListener(new b());
        this.j = (LinearLayout) view.findViewById(R.id.showview);
        ((TextView) view.findViewById(R.id.jiansuo)).setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                HistoryPaperFragment.this.m = (String) HistoryPaperFragment.this.o.get(HistoryPaperFragment.this.k.getSelectedItemPosition());
                HistoryPaperFragment.this.i = HistoryPaperFragment.this.m + "-" + HistoryPaperFragment.this.n + "-01";
                HistoryPaperFragment.this.n = (HistoryPaperFragment.this.l.getSelectedItemPosition() + 1) + "";
                if (HistoryPaperFragment.this.n.length() == 1) {
                    HistoryPaperFragment.this.n = "0" + HistoryPaperFragment.this.n;
                }
                HistoryPaperFragment.this.i = HistoryPaperFragment.this.m + "-" + HistoryPaperFragment.this.n + "-01";
                HistoryPaperFragment.this.h();
            }
        });
    }

    /* access modifiers changed from: private */
    public void h() {
        String[] split = this.i.split("-");
        ((GetBuilder) OkHttpUtils.get().url("http://appc.e23.cn/index.php?m=api&c=paper&a=getMonthPaperList")).addParams("bz", this.h).addParams("month", split[0] + "-" + split[1]).build().execute(new com.e23.ajn.c.a<PaperMonthBzResponse>() {
            public void onBefore(Request request, int i) {
            }

            public void onError(Call call, Exception exc, int i) {
            }

            /* renamed from: a */
            public void onResponse(PaperMonthBzResponse paperMonthBzResponse, int i) {
                if (paperMonthBzResponse != null && paperMonthBzResponse.getCode() == 200 && e.b(paperMonthBzResponse.getData())) {
                    HistoryPaperFragment.this.t = paperMonthBzResponse.getData();
                    HistoryPaperFragment.this.a(HistoryPaperFragment.this.t);
                }
            }
        });
    }

    /* access modifiers changed from: private */
    public void a(List<DataBean> list) {
        this.j.removeAllViews();
        this.j.addView(new CalendarCard(this.c, this, this.i, list));
    }

    public void a(com.e23.ajn.calendar.a aVar) {
        String str;
        String str2 = aVar.c + "";
        if (str2.length() == 1) {
            str = "0" + str2;
        } else {
            str = str2;
        }
        boolean z = false;
        for (int i2 = 0; i2 < this.t.size(); i2++) {
            if (((DataBean) this.t.get(i2)).getPublishdate().substring(8, 10).equals(str)) {
                com.e23.ajn.b.e.a(this.c).c(new l(((DataBean) this.t.get(i2)).getId()));
                this.c.onBackPressed();
                z = true;
            }
        }
        if (!z) {
            Toast.makeText(this.c, getString(R.string.nopaper), 1).show();
        }
    }

    public void b(com.e23.ajn.calendar.a aVar) {
    }
}
