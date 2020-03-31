package com.e23.ajn.views;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager;
import android.view.WindowManager.LayoutParams;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import com.e23.ajn.R;
import com.e23.ajn.d.e;
import java.util.ArrayList;
import java.util.List;

/* compiled from: ActionSheetDialog */
public class a {
    private Context a;
    /* access modifiers changed from: private */
    public Dialog b;
    private TextView c;
    private TextView d;
    private LinearLayout e;
    private ScrollView f;
    private boolean g = false;
    private List<b> h;
    private Display i;

    /* renamed from: com.e23.ajn.views.a$a reason: collision with other inner class name */
    /* compiled from: ActionSheetDialog */
    public interface C0051a {
        void a(int i);
    }

    /* compiled from: ActionSheetDialog */
    public class b {
        String a;
        C0051a b;
        c c;
        int d = 18;

        public b(String str, c cVar, C0051a aVar) {
            this.a = str;
            this.c = cVar;
            this.b = aVar;
        }
    }

    /* compiled from: ActionSheetDialog */
    public enum c {
        Blue("#0079ff"),
        Red("#FD4A2E"),
        Gray("#333333"),
        Night("#8ed7f8");
        
        private String e;

        private c(String str) {
            this.e = str;
        }

        public String a() {
            return this.e;
        }
    }

    public a(Context context) {
        this.a = context;
        this.i = ((WindowManager) context.getSystemService("window")).getDefaultDisplay();
    }

    public a a() {
        View inflate = LayoutInflater.from(this.a).inflate(R.layout.view_actionsheet, null);
        inflate.setMinimumWidth(this.i.getWidth());
        this.f = (ScrollView) inflate.findViewById(R.id.sLayout_content);
        this.e = (LinearLayout) inflate.findViewById(R.id.lLayout_content);
        this.c = (TextView) inflate.findViewById(2131820821);
        this.d = (TextView) inflate.findViewById(R.id.txt_cancel);
        this.d.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                a.this.b.dismiss();
            }
        });
        this.b = new Dialog(this.a, R.style.ActionSheetDialogStyle);
        this.b.setContentView(inflate);
        Window window = this.b.getWindow();
        window.setGravity(83);
        LayoutParams attributes = window.getAttributes();
        attributes.x = 0;
        attributes.y = 0;
        window.setAttributes(attributes);
        return this;
    }

    public a a(String str) {
        if (e.b(str)) {
            this.g = true;
            this.c.setVisibility(0);
            this.c.setText(str);
            this.c.setTextSize(18.0f);
        }
        return this;
    }

    public a a(boolean z) {
        this.b.setCancelable(z);
        return this;
    }

    public a b(boolean z) {
        this.b.setCanceledOnTouchOutside(z);
        return this;
    }

    public a a(String str, c cVar, C0051a aVar) {
        if (this.h == null) {
            this.h = new ArrayList();
        }
        this.h.add(new b(str, cVar, aVar));
        return this;
    }

    private void c() {
        if (this.h != null && this.h.size() > 0) {
            this.e.removeAllViews();
            int size = this.h.size();
            if (size >= 7) {
                LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) this.f.getLayoutParams();
                layoutParams.height = this.i.getHeight() / 2;
                this.f.setLayoutParams(layoutParams);
            }
            for (final int i2 = 1; i2 <= size; i2++) {
                b bVar = (b) this.h.get(i2 - 1);
                String str = bVar.a;
                c cVar = bVar.c;
                int i3 = bVar.d;
                final C0051a aVar = bVar.b;
                TextView textView = new TextView(this.a);
                textView.setText(str);
                textView.setTextSize((float) i3);
                textView.setGravity(17);
                if (size == 1) {
                    if (this.g) {
                        textView.setBackgroundResource(R.drawable.f12bg_btn_dis);
                    } else {
                        textView.setBackgroundResource(R.drawable.f65bt_nobgd);
                    }
                } else if (this.g) {
                    if (i2 < 1 || i2 >= size) {
                        textView.setBackgroundResource(R.drawable.f12bg_btn_dis);
                    } else {
                        textView.setBackgroundResource(R.drawable.f15bg_folder_item);
                    }
                } else if (i2 == 1) {
                    textView.setBackgroundResource(R.drawable.f67buttonstyle);
                } else if (i2 < size) {
                    textView.setBackgroundResource(R.drawable.f15bg_folder_item);
                } else {
                    textView.setBackgroundResource(R.drawable.f12bg_btn_dis);
                }
                if (cVar == null) {
                    textView.setTextColor(Color.parseColor(c.Blue.a()));
                } else {
                    textView.setTextColor(Color.parseColor(cVar.a()));
                }
                textView.setLayoutParams(new LinearLayout.LayoutParams(-1, (int) ((this.a.getResources().getDisplayMetrics().density * 45.0f) + 0.5f)));
                textView.setOnClickListener(new OnClickListener() {
                    public void onClick(View view) {
                        aVar.a(i2);
                        a.this.b.dismiss();
                    }
                });
                this.e.addView(textView);
            }
        }
    }

    public void b() {
        c();
        this.b.show();
    }
}
