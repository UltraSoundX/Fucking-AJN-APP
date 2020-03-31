package com.baidu.location.a;

import com.baidu.location.g.j;
import java.io.File;

class f extends Thread {
    final /* synthetic */ d a;

    f(d dVar) {
        this.a = dVar;
    }

    public void run() {
        this.a.a(new File(j.j() + "/baidu/tempdata", "intime.dat"), "http://itsdata.map.baidu.com/long-conn-gps/sdk.php");
    }
}
