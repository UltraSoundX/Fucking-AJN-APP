package com.e23.ajn.fragment.interaction.child;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.baidu.location.BDLocation;
import com.baidu.location.Poi;
import com.baidu.location.b;
import com.baidu.location.g;
import com.baidu.location.h;
import com.e23.ajn.R;
import com.e23.ajn.activity.SwipeBackCommonActivity;
import com.e23.ajn.adapter.b.a;
import com.e23.ajn.base.BaseSwipeBackFragment;
import com.e23.ajn.d.e;
import com.e23.ajn.d.f;
import com.e23.ajn.d.k;
import com.e23.ajn.d.p;
import com.e23.ajn.d.w;
import com.e23.ajn.model.BaseResponse;
import com.e23.ajn.model.PhotoUploadResponse;
import com.e23.ajn.views.SmiliesEditText;
import com.e23.ajn.views.a.C0051a;
import com.e23.ajn.views.j;
import com.e23.ajn.views.n;
import com.e23.ajn.views.n.c;
import com.lzy.imagepicker.bean.ImageItem;
import com.lzy.imagepicker.ui.ImageGridActivity;
import com.lzy.imagepicker.ui.ImagePreviewDelActivity;
import com.stub.StubApp;
import com.tencent.android.tpush.common.Constants;
import com.tencent.mid.sotrage.StorageInterface;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.builder.PostFormBuilder;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import okhttp3.Call;

public class BaoLiaoPostFragment extends BaseSwipeBackFragment implements OnClickListener, a {
    private String A;
    private ImageView B;
    private ImageView C;
    private GridView D;
    private Button E;
    /* access modifiers changed from: private */
    public TextView F;
    private com.e23.ajn.views.a G;
    private OnItemClickListener H = new OnItemClickListener() {
        public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
            BaoLiaoPostFragment.this.w.a(f.a[i] + "", f.b[i]);
        }
    };
    private j I;
    /* access modifiers changed from: private */
    public ArrayList<String> J;
    private b K = new b() {
        public void a(BDLocation bDLocation) {
            if (bDLocation != null && bDLocation.o() != 167) {
                StringBuffer stringBuffer = new StringBuffer(256);
                stringBuffer.append("time : ");
                stringBuffer.append(bDLocation.g());
                stringBuffer.append("\nlocType : ");
                stringBuffer.append(bDLocation.o());
                stringBuffer.append("\nlocType description : ");
                stringBuffer.append(bDLocation.p());
                stringBuffer.append("\nlatitude : ");
                stringBuffer.append(bDLocation.h());
                stringBuffer.append("\nlontitude : ");
                stringBuffer.append(bDLocation.i());
                stringBuffer.append("\nradius : ");
                stringBuffer.append(bDLocation.l());
                stringBuffer.append("\nCountryCode : ");
                stringBuffer.append(bDLocation.y());
                stringBuffer.append("\nCountry : ");
                stringBuffer.append(bDLocation.x());
                stringBuffer.append("\ncitycode : ");
                stringBuffer.append(bDLocation.w());
                stringBuffer.append("\ncity : ");
                stringBuffer.append(bDLocation.v());
                BaoLiaoPostFragment.this.g = bDLocation.v();
                stringBuffer.append("\nDistrict : ");
                stringBuffer.append(bDLocation.z());
                BaoLiaoPostFragment.this.h = bDLocation.z();
                stringBuffer.append("\nStreet : ");
                stringBuffer.append(bDLocation.A());
                BaoLiaoPostFragment.this.i = bDLocation.A();
                stringBuffer.append("\naddr : ");
                stringBuffer.append(bDLocation.u());
                stringBuffer.append("\nUserIndoorState: ");
                stringBuffer.append(bDLocation.b());
                stringBuffer.append("\nDirection(not all devices have value): ");
                stringBuffer.append(bDLocation.r());
                stringBuffer.append("\nlocationdescribe: ");
                stringBuffer.append(bDLocation.B());
                stringBuffer.append("\nPoi: ");
                if (bDLocation.a() != null && !bDLocation.a().isEmpty()) {
                    BaoLiaoPostFragment.this.J = new ArrayList();
                    int i = 0;
                    while (true) {
                        int i2 = i;
                        if (i2 >= bDLocation.a().size()) {
                            break;
                        }
                        Poi poi = (Poi) bDLocation.a().get(i2);
                        stringBuffer.append(poi.c() + ";");
                        BaoLiaoPostFragment.this.J.add(poi.c());
                        if (i2 == 0) {
                            BaoLiaoPostFragment.this.F.setText(poi.c());
                        }
                        i = i2 + 1;
                    }
                }
                if (bDLocation.o() == 61) {
                    stringBuffer.append("\nspeed : ");
                    stringBuffer.append(bDLocation.k());
                    stringBuffer.append("\nsatellite : ");
                    stringBuffer.append(bDLocation.q());
                    stringBuffer.append("\nheight : ");
                    stringBuffer.append(bDLocation.j());
                    stringBuffer.append("\ngps status : ");
                    stringBuffer.append(bDLocation.G());
                    stringBuffer.append("\ndescribe : ");
                    stringBuffer.append("gps定位成功");
                } else if (bDLocation.o() == 161) {
                    if (bDLocation.n()) {
                        stringBuffer.append("\nheight : ");
                        stringBuffer.append(bDLocation.j());
                    }
                    stringBuffer.append("\noperationers : ");
                    stringBuffer.append(bDLocation.I());
                    stringBuffer.append("\ndescribe : ");
                    stringBuffer.append("网络定位成功");
                } else if (bDLocation.o() == 66) {
                    stringBuffer.append("\ndescribe : ");
                    stringBuffer.append("离线定位成功，离线定位结果也是有效的");
                } else if (bDLocation.o() == 167) {
                    stringBuffer.append("\ndescribe : ");
                    stringBuffer.append("服务端网络定位失败，可以反馈IMEI号和大体定位时间到loc-bugs@baidu.com，会有人追查原因");
                } else if (bDLocation.o() == 63) {
                    stringBuffer.append("\ndescribe : ");
                    stringBuffer.append("网络不同导致定位失败，请检查网络是否通畅");
                } else if (bDLocation.o() == 62) {
                    stringBuffer.append("\ndescribe : ");
                    stringBuffer.append("无法获取有效定位依据导致定位失败，一般是由于手机的原因，处于飞行模式下一般会造成这种结果，可以试着重启手机");
                }
                Log.d("定位", "定位：" + stringBuffer.toString());
            }
        }
    };
    TextWatcher d = new TextWatcher() {
        public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            BaoLiaoPostFragment.this.m.setText((3000 - charSequence.length()) + "");
        }

        public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
        }

        public void afterTextChanged(Editable editable) {
        }
    };
    public g e = null;
    StringBuffer f;
    String g;
    String h;
    String i;
    private TextView j;
    private TextView k;
    /* access modifiers changed from: private */
    public TextView l;
    /* access modifiers changed from: private */
    public TextView m;
    private com.e23.ajn.adapter.b n;
    /* access modifiers changed from: private */
    public ArrayList<ImageItem> o;
    /* access modifiers changed from: private */
    public int p = 9;

    /* renamed from: q reason: collision with root package name */
    private com.lzy.imagepicker.b f408q;
    /* access modifiers changed from: private */
    public ArrayList<ImageItem> r;
    /* access modifiers changed from: private */
    public ArrayList<String> s = new ArrayList<>();
    private EditText t;
    private EditText u;
    private LinearLayout v;
    /* access modifiers changed from: private */
    public SmiliesEditText w;
    private RecyclerView x;
    private TextView y;
    /* access modifiers changed from: private */
    public String z;

    public static BaoLiaoPostFragment a(String str, String str2) {
        BaoLiaoPostFragment baoLiaoPostFragment = new BaoLiaoPostFragment();
        Bundle bundle = new Bundle();
        bundle.putString("catid", str);
        bundle.putString("reporter_id", str2);
        baoLiaoPostFragment.setArguments(bundle);
        return baoLiaoPostFragment;
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View inflate = layoutInflater.inflate(R.layout.f129f_szb, viewGroup, false);
        this.z = getArguments().getString("catid");
        this.A = getArguments().getString("reporter_id");
        h();
        b(inflate);
        l();
        return inflate;
    }

    private void h() {
        this.f408q = com.lzy.imagepicker.b.a();
        this.f408q.a((com.lzy.imagepicker.a.a) new k());
        this.f408q.c(true);
        this.f408q.b(false);
        this.f408q.a(true);
        this.f408q.a(this.p);
        this.f408q.b(1000);
        this.f408q.c(1000);
    }

    private void b(View view) {
        com.jaeger.library.a.b(this.c, 0, null);
        com.jaeger.library.a.a((Activity) this.c);
        this.j = (TextView) view.findViewById(R.id.toolbar_center_title);
        this.j.setText(getString(R.string.interaction_post));
        this.j.setVisibility(0);
        this.l = (TextView) view.findViewById(R.id.toolbar_right_title);
        if (this.z.equals("7")) {
            this.l.setText("说说");
            this.l.setVisibility(0);
            this.l.setOnClickListener(this);
            this.l.setTextColor(getResources().getColor(R.color.blue));
        }
        this.k = (TextView) view.findViewById(R.id.toolbar_left_title);
        this.k.setText(getString(R.string.cancel));
        this.k.setVisibility(0);
        this.k.setOnClickListener(this);
        this.t = (EditText) view.findViewById(R.id.hd_post_et_title);
        this.v = (LinearLayout) view.findViewById(R.id.hd_post_ll_contact);
        this.u = (EditText) view.findViewById(R.id.hd_post_et_contact);
        this.w = (SmiliesEditText) view.findViewById(R.id.hd_post_et_content);
        this.w.addTextChangedListener(this.d);
        this.y = (TextView) view.findViewById(R.id.hd_post_rcv_max_pic_tip);
        this.x = (RecyclerView) view.findViewById(R.id.hd_post_rcv_photo);
        this.o = new ArrayList<>();
        this.n = new com.e23.ajn.adapter.b(this.c, this.o, this.p);
        this.n.a((a) this);
        this.x.setLayoutManager(new GridLayoutManager(this.c, 4));
        this.x.setHasFixedSize(true);
        this.x.setAdapter(this.n);
        this.m = (TextView) view.findViewById(R.id.hd_post_tv_words);
        this.B = (ImageView) view.findViewById(R.id.hd_post_iv_iamge);
        this.B.setOnClickListener(this);
        this.C = (ImageView) view.findViewById(R.id.hd_post_iv_em);
        this.C.setOnClickListener(this);
        this.D = (GridView) view.findViewById(R.id.hd_post_gv_em);
        this.D.setAdapter(new com.e23.ajn.adapter.a(this.c));
        this.D.setOnItemClickListener(this.H);
        this.E = (Button) view.findViewById(R.id.hd_post_btn_post);
        this.E.setOnClickListener(this);
        this.F = (TextView) view.findViewById(R.id.hd_post_location_tv);
        this.F.setOnClickListener(this);
        if (this.z.equals("373")) {
            this.j.setText(getString(R.string.report_material));
            this.v.setVisibility(0);
        }
    }

    private n a(c cVar, List<String> list) {
        n nVar = new n(this.c, R.style.transparentFrameWindowStyle, cVar, list);
        if (!this.c.isFinishing()) {
            nVar.show();
        }
        return nVar;
    }

    public void a(View view, int i2) {
        switch (i2) {
            case -1:
                ArrayList arrayList = new ArrayList();
                arrayList.add("拍照");
                arrayList.add("相册");
                a((c) new c() {
                    public void a(AdapterView<?> adapterView, View view, int i, long j) {
                        switch (i) {
                            case 0:
                                com.lzy.imagepicker.b.a().a(BaoLiaoPostFragment.this.p - BaoLiaoPostFragment.this.o.size());
                                Intent intent = new Intent(BaoLiaoPostFragment.this.c, ImageGridActivity.class);
                                intent.putExtra(ImageGridActivity.EXTRAS_TAKE_PICKERS, true);
                                BaoLiaoPostFragment.this.startActivityForResult(intent, 100);
                                return;
                            case 1:
                                com.lzy.imagepicker.b.a().a(BaoLiaoPostFragment.this.p - BaoLiaoPostFragment.this.o.size());
                                BaoLiaoPostFragment.this.startActivityForResult(new Intent(BaoLiaoPostFragment.this.c, ImageGridActivity.class), 100);
                                return;
                            default:
                                return;
                        }
                    }
                }, (List<String>) arrayList);
                return;
            default:
                Intent intent = new Intent(this.c, ImagePreviewDelActivity.class);
                intent.putExtra("extra_image_items", (ArrayList) this.n.a());
                intent.putExtra("selected_image_position", i2);
                intent.putExtra("extra_from_items", true);
                startActivityForResult(intent, 101);
                return;
        }
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.hd_post_iv_em /*2131820860*/:
                this.x.setVisibility(8);
                this.D.setVisibility(0);
                this.y.setVisibility(8);
                return;
            case R.id.hd_post_iv_iamge /*2131820861*/:
                this.x.setVisibility(0);
                this.D.setVisibility(8);
                this.y.setVisibility(0);
                return;
            case R.id.hd_post_btn_post /*2131820862*/:
                j();
                return;
            case R.id.hd_post_location_tv /*2131820864*/:
                if (!e.a((List) this.J)) {
                    if (this.I == null) {
                        this.I = new j(this.c);
                        this.I.a((j.a) new j.a() {
                            public void a(String str) {
                                BaoLiaoPostFragment.this.F.setText(str);
                            }
                        });
                    }
                    this.I.a(this.J, this.F.getText().toString());
                    this.I.show();
                    return;
                }
                return;
            case R.id.toolbar_left_title /*2131821014*/:
                this.c.onBackPressed();
                return;
            case R.id.toolbar_right_title /*2131821017*/:
                if (this.G == null) {
                    i();
                }
                this.G.b();
                return;
            default:
                return;
        }
    }

    private void i() {
        this.G = new com.e23.ajn.views.a(this.c).a();
        this.G.a("选择栏目");
        this.G.a(true);
        this.G.b(true);
        this.G.a("说说", com.e23.ajn.views.a.c.Blue, new C0051a() {
            public void a(int i) {
                BaoLiaoPostFragment.this.z = "7";
                BaoLiaoPostFragment.this.l.setText("说说");
            }
        });
        this.G.a("问答", com.e23.ajn.views.a.c.Blue, new C0051a() {
            public void a(int i) {
                BaoLiaoPostFragment.this.z = "235";
                BaoLiaoPostFragment.this.l.setText("问答");
            }
        });
        this.G.a("互助", com.e23.ajn.views.a.c.Blue, new C0051a() {
            public void a(int i) {
                BaoLiaoPostFragment.this.z = "446";
                BaoLiaoPostFragment.this.l.setText("互助");
            }
        });
    }

    public void onActivityResult(int i2, int i3, Intent intent) {
        super.onActivityResult(i2, i3, intent);
        if (i3 == 1004) {
            if (intent != null && i2 == 100) {
                this.r = (ArrayList) intent.getSerializableExtra("extra_result_items");
                if (this.r != null) {
                    this.o.addAll(this.r);
                    this.n.a((List<ImageItem>) this.o);
                }
            }
        } else if (i3 == 1005 && intent != null && i2 == 101) {
            this.r = (ArrayList) intent.getSerializableExtra("extra_image_items");
            if (this.r != null) {
                this.o.clear();
                this.o.addAll(this.r);
                this.n.a((List<ImageItem>) this.o);
            }
        }
    }

    private void j() {
        int i2 = 0;
        if (!p.a("is_logined", false)) {
            Intent intent = new Intent(this.c, SwipeBackCommonActivity.class);
            intent.putExtra(SwipeBackCommonActivity.TAG, 21);
            startActivity(intent);
        } else if (TextUtils.isEmpty(this.t.getText().toString().trim())) {
            w.a(getString(R.string.fragment_suggestion_yzjy_title_noempty));
        } else if (TextUtils.isEmpty(this.w.getText().toString().trim())) {
            w.a(getString(R.string.fragment_suggestion_yzjy_content_noempty));
        } else if (this.r == null || this.r.size() <= 0) {
            k();
        } else {
            com.e23.ajn.views.k.b(this.c, getString(R.string.waiting));
            while (true) {
                int i3 = i2;
                if (i3 < this.r.size()) {
                    String str = ((ImageItem) this.r.get(i3)).b;
                    File a = com.nanchen.compresshelper.b.a((Context) this.c).a(new File(str));
                    String[] split = str.split("/");
                    ((PostFormBuilder) OkHttpUtils.post().url("http://appc.e23.cn/index.php?m=api&c=contentc&a=imgUpload")).addParams(Constants.FLAG_TOKEN, p.b(Constants.FLAG_TOKEN, "")).addFile("uploadfile", split[split.length - 1], a).build().execute(new com.e23.ajn.c.a<PhotoUploadResponse>() {
                        public void onError(Call call, Exception exc, int i) {
                            BaoLiaoPostFragment.this.s.add("");
                            if (BaoLiaoPostFragment.this.s.size() == BaoLiaoPostFragment.this.r.size()) {
                                com.e23.ajn.views.k.b();
                                BaoLiaoPostFragment.this.k();
                            }
                        }

                        /* renamed from: a */
                        public void onResponse(PhotoUploadResponse photoUploadResponse, int i) {
                            if (photoUploadResponse.getCode() != 200) {
                                w.b(BaoLiaoPostFragment.this.getString(R.string.upload_fail));
                                return;
                            }
                            BaoLiaoPostFragment.this.s.add(photoUploadResponse.getData());
                            if (BaoLiaoPostFragment.this.s.size() == BaoLiaoPostFragment.this.r.size()) {
                                com.e23.ajn.views.k.b();
                                BaoLiaoPostFragment.this.k();
                            }
                        }
                    });
                    i2 = i3 + 1;
                } else {
                    return;
                }
            }
        }
    }

    /* access modifiers changed from: private */
    public void k() {
        String str;
        String str2 = "";
        if (this.s.size() > 0) {
            for (int i2 = 0; i2 < this.s.size(); i2++) {
                if (!"".equals(this.s.get(i2))) {
                    str2 = str2 + ((String) this.s.get(i2)) + StorageInterface.KEY_SPLITER;
                }
            }
        }
        if (!"".equals(str2)) {
            str = str2.substring(0, str2.length() - 1);
        } else {
            str = str2;
        }
        com.e23.ajn.views.k.b(this.c, getString(R.string.waiting));
        PostFormBuilder addParams = ((PostFormBuilder) OkHttpUtils.post().url("http://appc.e23.cn/index.php?m=api&c=contentc&a=addContent")).addParams("title", this.t.getText().toString().trim()).addParams("content", this.w.getText().toString().trim()).addParams("contact", this.u.getText().toString().trim()).addParams("reporter_id", this.A).addParams("catid", this.z).addParams("thumbs", str).addParams(Constants.FLAG_TOKEN, p.b(Constants.FLAG_TOKEN, ""));
        if (!TextUtils.isEmpty(this.F.getText().toString())) {
            this.f = new StringBuffer(256);
            this.f.append(this.g).append(StorageInterface.KEY_SPLITER).append(this.h).append(StorageInterface.KEY_SPLITER).append(this.i).append(StorageInterface.KEY_SPLITER).append(this.F.getText().toString());
            addParams.addParams("location", this.f.toString());
        }
        addParams.build().execute(new com.e23.ajn.c.a<BaseResponse>() {
            public void onError(Call call, Exception exc, int i) {
                com.e23.ajn.views.k.b();
                w.b(BaoLiaoPostFragment.this.getString(R.string.fragment_suggestion_yzjy_submit_fail));
            }

            /* renamed from: a */
            public void onResponse(BaseResponse baseResponse, int i) {
                com.e23.ajn.views.k.b();
                if (baseResponse.getCode() != 200) {
                    w.b(baseResponse.getMessage());
                    return;
                }
                com.e23.ajn.d.a.a("postcontent", BaoLiaoPostFragment.this.c);
                BaoLiaoPostFragment.this.c.onBackPressed();
            }
        });
    }

    private void l() {
        this.e = new g(StubApp.getOrigApplicationContext(this.c.getApplicationContext()));
        h hVar = new h();
        hVar.a(true);
        hVar.c(true);
        hVar.d(true);
        this.e.a(hVar);
        this.e.a(this.K);
        this.e.b();
    }

    public void onDestroy() {
        super.onDestroy();
        if (this.e != null) {
            this.e.c();
            this.e = null;
        }
        if (this.f != null) {
            this.f = null;
        }
    }
}
