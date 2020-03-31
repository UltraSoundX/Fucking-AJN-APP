package com.baidu.location.indoor.mapversion.c;

import java.io.File;
import java.io.FilenameFilter;

class b implements FilenameFilter {
    final /* synthetic */ String a;
    final /* synthetic */ a b;

    b(a aVar, String str) {
        this.b = aVar;
        this.a = str;
    }

    public boolean accept(File file, String str) {
        return str.startsWith(new StringBuilder().append(this.b.d(this.a)).append("_").toString());
    }
}
