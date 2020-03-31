package com.e23.ajn.fragment.mine.child;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.bumptech.glide.i;
import com.e23.ajn.R;
import com.e23.ajn.b.d;
import com.e23.ajn.b.e;
import com.e23.ajn.base.BaseSupportFragment;
import com.e23.ajn.d.k;
import com.e23.ajn.d.p;
import com.e23.ajn.d.q;
import com.e23.ajn.d.w;
import com.e23.ajn.model.BaseResponse;
import com.e23.ajn.model.LoginResponse.DataBean;
import com.e23.ajn.model.UploadAvatarResponse;
import com.e23.ajn.views.CircularImage;
import com.e23.ajn.views.a.C0051a;
import com.e23.ajn.views.h;
import com.lzy.imagepicker.a.a;
import com.lzy.imagepicker.b;
import com.lzy.imagepicker.bean.ImageItem;
import com.lzy.imagepicker.ui.ImageGridActivity;
import com.lzy.imagepicker.view.CropImageView.c;
import com.tencent.android.tpush.common.Constants;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.builder.PostFormBuilder;
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import okhttp3.Call;

public class MineEditMeFragment extends BaseSupportFragment implements OnClickListener, C0051a {
    C0051a a = new C0051a() {
        public void a(int i) {
            MineEditMeFragment.this.a(MineEditMeFragment.this.getString(R.string.fragment_mine_editme_tv_area_text), ((String) Arrays.asList(MineEditMeFragment.this.o).get(i - 1)).toString());
        }
    };
    private b d;
    private TextView e;
    private ImageView f;
    /* access modifiers changed from: private */
    public CircularImage g;
    /* access modifiers changed from: private */
    public TextView h;
    /* access modifiers changed from: private */
    public TextView i;
    /* access modifiers changed from: private */
    public TextView j;
    private ArrayList<ImageItem> k;
    /* access modifiers changed from: private */
    public h l;
    /* access modifiers changed from: private */
    public EditText m;
    private DataBean n;
    /* access modifiers changed from: private */
    public String[] o = {"历下", "市中", "天桥", "槐荫", "历城", "章丘", "济阳", "长清", "商河", "平阴", "南山"};

    public static MineEditMeFragment a(DataBean dataBean) {
        Bundle bundle = new Bundle();
        MineEditMeFragment mineEditMeFragment = new MineEditMeFragment();
        bundle.putSerializable("user", dataBean);
        mineEditMeFragment.setArguments(bundle);
        return mineEditMeFragment;
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        if (getArguments() != null) {
            this.n = (DataBean) getArguments().getSerializable("user");
        }
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View inflate = layoutInflater.inflate(R.layout.f141gywm, viewGroup, false);
        a(inflate);
        return inflate;
    }

    private void h() {
        if (this.d == null) {
            this.d = b.a();
        }
        this.d.a((a) new k());
        this.d.b(true);
        this.d.a(false);
        this.d.b(true);
        this.d.a(c.CIRCLE);
        int b = (int) (((double) q.b(this.c)) * 0.5d);
        this.d.d(b);
        this.d.e(b);
        this.d.b(this.c.getResources().getDimensionPixelSize(R.dimen.dp_80));
        this.d.c(this.c.getResources().getDimensionPixelSize(R.dimen.dp_80));
    }

    private void a(View view) {
        com.jaeger.library.a.b(this.c, 0, null);
        com.jaeger.library.a.a((Activity) this.c);
        this.f = (ImageView) view.findViewById(R.id.toolbar_left_back);
        this.f.setOnClickListener(this);
        this.f.setVisibility(0);
        this.e = (TextView) view.findViewById(R.id.toolbar_center_title);
        this.e.setText(getString(R.string.fragment_mine_editme_tv_editme_text));
        this.e.setVisibility(0);
        ((RelativeLayout) view.findViewById(R.id.fragment_mine_editme_rl_avatar)).setOnClickListener(this);
        this.g = (CircularImage) view.findViewById(R.id.fragment_mine_editme_civ_avatar);
        ((RelativeLayout) view.findViewById(R.id.fragment_mine_editme_rl_nickname)).setOnClickListener(this);
        this.h = (TextView) view.findViewById(R.id.fragment_mine_editme_tv_nickname);
        i.a(this.c).a(this.n.getAvatar()).b(com.bumptech.glide.d.b.b.ALL).d((int) R.mipmap.f239fatieimg).c((int) R.mipmap.f239fatieimg).a((ImageView) this.g);
        this.h.setText(this.n.getUsername());
        ((RelativeLayout) view.findViewById(R.id.fragment_mine_editme_rl_sex)).setOnClickListener(this);
        this.i = (TextView) view.findViewById(R.id.fragment_mine_editme_tv_sex);
        this.i.setText(this.n.getSex());
        ((RelativeLayout) view.findViewById(R.id.fragment_mine_editme_rl_area)).setOnClickListener(this);
        this.j = (TextView) view.findViewById(R.id.fragment_mine_editme_tv_area);
        this.j.setText(this.n.getArea());
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.toolbar_left_back /*2131820915*/:
                this.c.onBackPressed();
                return;
            case R.id.fragment_mine_editme_rl_avatar /*2131820920*/:
                h();
                com.e23.ajn.views.a a2 = new com.e23.ajn.views.a(this.c).a();
                a2.a(getString(R.string.fragment_mine_editme_selecttype));
                a2.a(true);
                a2.b(true);
                a2.a(getString(R.string.fragment_mine_editme_fromgally), com.e23.ajn.views.a.c.Blue, this);
                a2.a(getString(R.string.fragment_mine_editme_takeapicture), com.e23.ajn.views.a.c.Blue, this);
                a2.b();
                return;
            case R.id.fragment_mine_editme_rl_nickname /*2131820923*/:
                i();
                return;
            case R.id.fragment_mine_editme_rl_sex /*2131820926*/:
                b(getString(R.string.fragment_mine_editme_tv_sex_text));
                return;
            case R.id.fragment_mine_editme_rl_area /*2131820929*/:
                b(getString(R.string.fragment_mine_editme_tv_area_text));
                return;
            case R.id.fragment_mine_editme_btn_confirm /*2131820934*/:
                a(getString(R.string.fragment_mine_editme_tv_nickname_text), this.m.getText().toString().trim());
                return;
            default:
                return;
        }
    }

    public void a(int i2) {
        switch (i2) {
            case 1:
                startActivityForResult(new Intent(this.c, ImageGridActivity.class), 100);
                return;
            case 2:
                Intent intent = new Intent(this.c, ImageGridActivity.class);
                intent.putExtra(ImageGridActivity.EXTRAS_TAKE_PICKERS, true);
                startActivityForResult(intent, 100);
                return;
            default:
                return;
        }
    }

    public void onActivityResult(int i2, int i3, Intent intent) {
        super.onActivityResult(i2, i3, intent);
        if (i3 == 1004 && intent != null && i2 == 100) {
            this.k = (ArrayList) intent.getSerializableExtra("extra_result_items");
            if (this.k.size() > 0) {
                a(((ImageItem) this.k.get(0)).b);
            }
        }
    }

    private void a(String str) {
        com.e23.ajn.views.k.b(this.c, getString(R.string.uploading));
        String[] split = str.split("/");
        ((PostFormBuilder) OkHttpUtils.post().url("http://appc.e23.cn/index.php?m=api&c=user&a=uploadHeadImg")).params(com.e23.ajn.c.b.a(null)).addParams(Constants.FLAG_TOKEN, p.b(Constants.FLAG_TOKEN, "")).addFile("uploadfile", split[split.length - 1], new File(str)).build().execute(new com.e23.ajn.c.a<UploadAvatarResponse>() {
            public void onError(Call call, Exception exc, int i) {
                com.e23.ajn.views.k.b();
                w.b(MineEditMeFragment.this.getString(R.string.upload_fail));
            }

            /* renamed from: a */
            public void onResponse(UploadAvatarResponse uploadAvatarResponse, int i) {
                if (uploadAvatarResponse.getCode() != 200) {
                    com.e23.ajn.views.k.b();
                    w.b(MineEditMeFragment.this.getString(R.string.upload_fail));
                    return;
                }
                e.a(MineEditMeFragment.this.c).c(new d(uploadAvatarResponse.getData().getHeadimg(), ""));
                com.e23.ajn.views.k.b();
                p.a("user_avatar", uploadAvatarResponse.getData().getHeadimg());
                i.a(MineEditMeFragment.this.c).a(uploadAvatarResponse.getData().getHeadimg()).b(com.bumptech.glide.d.b.b.ALL).d((int) R.mipmap.f307shoujileft).c((int) R.mipmap.f307shoujileft).a((ImageView) MineEditMeFragment.this.g);
                w.b(MineEditMeFragment.this.getString(R.string.upload_succeed));
            }
        });
    }

    private void i() {
        if (this.l != null) {
            this.l.show();
            this.m.requestFocus();
            return;
        }
        this.l = new h(this.c, R.style.EditNicknamelDialog, this.n.getUsername());
        this.l.show();
        this.m = (EditText) this.l.findViewById(R.id.fragment_mine_editme_et_nickname);
        this.m.setText(p.b("user_name", ""));
        this.m.setSelection(this.m.getText().toString().length());
        ((Button) this.l.findViewById(R.id.fragment_mine_editme_btn_confirm)).setOnClickListener(this);
        ((Button) this.l.findViewById(R.id.fragment_mine_editme_btn_cancel)).setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                MineEditMeFragment.this.l.dismiss();
            }
        });
        this.m.requestFocus();
    }

    /* access modifiers changed from: private */
    public void a(final String str, final String str2) {
        String str3;
        String str4 = "";
        if (str.equals(getString(R.string.fragment_mine_editme_tv_nickname_text))) {
            str4 = "username";
        }
        if (str.equals(getString(R.string.fragment_mine_editme_tv_sex_text))) {
            str4 = "sex";
        }
        if (str.equals(getString(R.string.fragment_mine_editme_tv_area_text))) {
            str3 = "area";
        } else {
            str3 = str4;
        }
        com.e23.ajn.views.k.b(this.c, getString(R.string.waiting));
        ((PostFormBuilder) OkHttpUtils.post().url("http://appc.e23.cn/index.php?m=api&c=user&a=updateUserInfo")).params(com.e23.ajn.c.b.a(null)).addParams(str3, str2).build().execute(new com.e23.ajn.c.a<BaseResponse>() {
            public void onError(Call call, Exception exc, int i) {
                com.e23.ajn.views.k.b();
                w.b(MineEditMeFragment.this.getString(R.string.submit_fail));
            }

            /* renamed from: a */
            public void onResponse(BaseResponse baseResponse, int i) {
                com.e23.ajn.views.k.b();
                if (baseResponse.getCode() != 200) {
                    w.b(baseResponse.getMessage());
                    return;
                }
                if (str.equals(MineEditMeFragment.this.getString(R.string.fragment_mine_editme_tv_nickname_text))) {
                    MineEditMeFragment.this.l.dismiss();
                    MineEditMeFragment.this.h.setText(str2);
                    e.a(MineEditMeFragment.this.c).c(new d("", MineEditMeFragment.this.m.getText().toString().trim()));
                    p.a("user_name", MineEditMeFragment.this.m.getText().toString().trim());
                }
                if (str.equals(MineEditMeFragment.this.getString(R.string.fragment_mine_editme_tv_sex_text))) {
                    MineEditMeFragment.this.i.setText(str2);
                }
                if (str.equals(MineEditMeFragment.this.getString(R.string.fragment_mine_editme_tv_area_text))) {
                    MineEditMeFragment.this.j.setText(str2);
                }
            }
        });
    }

    public void onDestroyView() {
        super.onDestroyView();
    }

    private void b(final String str) {
        com.e23.ajn.views.a a2 = new com.e23.ajn.views.a(this.c).a();
        a2.a(str);
        a2.a(true);
        a2.b(true);
        if (str.equals(getString(R.string.fragment_mine_editme_tv_sex_text))) {
            a2.a("男", com.e23.ajn.views.a.c.Blue, new C0051a() {
                public void a(int i) {
                    MineEditMeFragment.this.a(str, "男");
                }
            });
            a2.a("女", com.e23.ajn.views.a.c.Blue, new C0051a() {
                public void a(int i) {
                    MineEditMeFragment.this.a(str, "女");
                }
            });
        }
        if (str.equals(getString(R.string.fragment_mine_editme_tv_area_text))) {
            int i2 = 0;
            while (true) {
                int i3 = i2;
                if (i3 >= Arrays.asList(this.o).size()) {
                    break;
                }
                a2.a((String) Arrays.asList(this.o).get(i3), com.e23.ajn.views.a.c.Blue, this.a);
                i2 = i3 + 1;
            }
        }
        a2.b();
    }
}
