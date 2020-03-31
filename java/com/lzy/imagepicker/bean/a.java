package com.lzy.imagepicker.bean;

import java.io.Serializable;
import java.util.ArrayList;

/* compiled from: ImageFolder */
public class a implements Serializable {
    public String a;
    public String b;
    public ImageItem c;
    public ArrayList<ImageItem> d;

    public boolean equals(Object obj) {
        try {
            a aVar = (a) obj;
            return this.b.equalsIgnoreCase(aVar.b) && this.a.equalsIgnoreCase(aVar.a);
        } catch (ClassCastException e) {
            e.printStackTrace();
            return super.equals(obj);
        }
    }
}
