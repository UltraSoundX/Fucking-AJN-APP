package com.ccb.b.c;

import android.content.Context;
import com.baidu.mobstat.Config;
import com.ccb.b.b.a;
import com.ccb.b.b.b;
import com.ccb.b.b.g;
import com.ccb.b.b.i;
import com.ccb.b.b.j;
import com.ccb.b.b.m;
import com.tencent.mid.sotrage.StorageInterface;
import net.sf.json.util.JSONUtils;

public class d extends g {
    private final String[] b = {"`", "~", "!", "@", "#", "$", "%", "^", "&", "*", "(", ")", "_", "-", "=", "+", "[", "]", "{", "}", "|", "\\", ";", Config.TRACE_TODAY_VISIT_SPLIT, JSONUtils.SINGLE_QUOTE, JSONUtils.DOUBLE_QUOTE, StorageInterface.KEY_SPLITER, ".", "<", ">", "/", "?"};

    public d(Context context) {
        super(context);
        setOrientation(1);
        i iVar = new i(getContext());
        iVar.a(a(new j(getContext(), this.b[0], this.b[0], 0, 1.0f)));
        iVar.a(a(new j(getContext(), this.b[1], this.b[1], 0, 1.0f)));
        iVar.a(a(new j(getContext(), this.b[2], this.b[2], 0, 1.0f)));
        iVar.a(a(new j(getContext(), this.b[3], this.b[3], 0, 1.0f)));
        iVar.a(a(new j(getContext(), this.b[4], this.b[4], 0, 1.0f)));
        iVar.a(a(new j(getContext(), this.b[5], this.b[5], 0, 1.0f)));
        iVar.a(a(new j(getContext(), this.b[6], this.b[6], 0, 1.0f)));
        iVar.a(a(new j(getContext(), this.b[7], this.b[7], 0, 1.0f)));
        iVar.a(a(new j(getContext(), this.b[8], this.b[8], 0, 1.0f)));
        iVar.a(a(new j(getContext(), this.b[9], this.b[9], 0, 1.0f)));
        addView(iVar);
        i iVar2 = new i(getContext());
        iVar2.a(a(new j(getContext(), this.b[10], this.b[10], 0, 1.0f)));
        iVar2.a(a(new j(getContext(), this.b[11], this.b[11], 0, 1.0f)));
        iVar2.a(a(new j(getContext(), this.b[12], this.b[12], 0, 1.0f)));
        iVar2.a(a(new j(getContext(), this.b[13], this.b[13], 0, 1.0f)));
        iVar2.a(a(new j(getContext(), this.b[14], this.b[14], 0, 1.0f)));
        iVar2.a(a(new j(getContext(), this.b[15], this.b[15], 0, 1.0f)));
        iVar2.a(a(new j(getContext(), this.b[16], this.b[16], 0, 1.0f)));
        iVar2.a(a(new j(getContext(), this.b[17], this.b[17], 0, 1.0f)));
        iVar2.a(a(new j(getContext(), this.b[18], this.b[18], 0, 1.0f)));
        iVar2.a(a(new j(getContext(), this.b[19], this.b[19], 0, 1.0f)));
        addView(iVar2);
        i iVar3 = new i(getContext());
        iVar3.a(a(new j(getContext(), this.b[20], this.b[20], 0, 1.0f)));
        iVar3.a(a(new j(getContext(), this.b[21], this.b[21], 0, 1.0f)));
        iVar3.a(a(new j(getContext(), this.b[22], this.b[22], 0, 1.0f)));
        iVar3.a(a(new j(getContext(), this.b[23], this.b[23], 0, 1.0f)));
        iVar3.a(a(new j(getContext(), this.b[24], this.b[24], 0, 1.0f)));
        iVar3.a(a(new j(getContext(), this.b[25], this.b[25], 0, 1.0f)));
        iVar3.a(a(new j(getContext(), this.b[26], this.b[26], 0, 1.0f)));
        iVar3.a(a(new j(getContext(), this.b[27], this.b[27], 0, 1.0f)));
        iVar3.a(a(new j(getContext(), this.b[28], this.b[28], 0, 1.0f)));
        iVar3.a(a(new j(getContext(), this.b[29], this.b[29], 0, 1.0f)));
        addView(iVar3);
        i iVar4 = new i(getContext());
        iVar4.a(a(new j(getContext(), this.b[30], this.b[30], 0, 3.0f)));
        iVar4.a(a(new j(getContext(), this.b[31], this.b[31], 0, 3.0f)));
        iVar4.a((b) new j(getContext(), "", " ", 62, 1.1f, m.a("symbol_space.png"), m.a("symbol_space_press.png")));
        iVar4.a((b) new a(getContext(), "", null, 67, 2.9f, m.a("symbol_delete.png"), m.a("symbol_delete_press.png")));
        addView(iVar4);
    }
}
