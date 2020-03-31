package com.ccb.b.c;

import android.content.Context;
import com.baidu.mobstat.Config;
import com.ccb.b.b.a;
import com.ccb.b.b.b;
import com.ccb.b.b.g;
import com.ccb.b.b.h;
import com.ccb.b.b.i;
import com.ccb.b.b.k;
import com.ccb.b.b.m;
import com.tencent.mid.sotrage.StorageInterface;

public class c extends g {
    private final String[] b = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "0"};
    private final String[] c = {"/", "?", "!", Config.TRACE_TODAY_VISIT_SPLIT, StorageInterface.KEY_SPLITER, "."};

    public c(Context context) {
        super(context);
        setOrientation(1);
        i iVar = new i(getContext());
        h hVar = new h(getContext(), 0.4f);
        i iVar2 = new i(getContext(), 0.0f);
        iVar2.a(a(new k(getContext(), this.b[0], this.b[0], 0, 1.0f)));
        iVar2.a(a(new k(getContext(), this.b[1], this.b[1], 0, 1.0f)));
        iVar2.a(a(new k(getContext(), this.b[2], this.b[2], 0, 1.0f)));
        hVar.a(iVar2);
        i iVar3 = new i(getContext(), 0.0f);
        iVar3.a(a(new k(getContext(), this.b[3], this.b[3], 0, 1.0f)));
        iVar3.a(a(new k(getContext(), this.b[4], this.b[4], 0, 1.0f)));
        iVar3.a(a(new k(getContext(), this.b[5], this.b[5], 0, 1.0f)));
        hVar.a(iVar3);
        i iVar4 = new i(getContext(), 0.0f);
        iVar4.a(a(new k(getContext(), this.b[6], this.b[6], 0, 1.0f)));
        iVar4.a(a(new k(getContext(), this.b[7], this.b[7], 0, 1.0f)));
        iVar4.a(a(new k(getContext(), this.b[8], this.b[8], 0, 1.0f)));
        hVar.a(iVar4);
        i iVar5 = new i(getContext(), 0.0f);
        iVar5.a((b) new k(getContext(), "  ", " ", 0, 1.0f, m.a("number_space.png"), m.a("number_space_press.png")));
        iVar5.a(a(new k(getContext(), this.b[9], this.b[9], 0, 1.0f)));
        iVar5.a((b) new a(getContext(), "", null, 67, 1.0f, m.a("number_delete.png"), m.a("number_delete_press.png")));
        hVar.a(iVar5);
        iVar.a(hVar);
        addView(iVar);
    }
}
