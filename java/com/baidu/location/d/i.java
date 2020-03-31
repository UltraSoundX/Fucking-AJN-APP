package com.baidu.location.d;

import android.database.Cursor;
import android.database.MatrixCursor;
import com.baidu.location.BDLocation;
import com.baidu.location.Poi;
import com.baidu.location.a.C0022a;
import com.baidu.location.e.h;
import com.baidu.location.g.b;
import com.baidu.location.g.j;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;

final class i {
    private static final String[] a = {"CoorType", "Time", "LocType", "Longitude", "Latitude", "Radius", "NetworkLocationType", "Country", "CountryCode", "Province", "City", "CityCode", "District", "Street", "StreetNumber", "PoiList", "LocationDescription"};

    static final class a {
        final String a;
        final String b;
        final boolean c;
        final boolean d;
        final boolean e;
        final int f;
        final BDLocation g;
        final boolean h;
        final LinkedHashMap<String, Integer> i;

        public a(String[] strArr) {
            boolean z;
            if (strArr == null) {
                this.a = null;
                this.b = null;
                this.i = null;
                this.c = false;
                this.d = false;
                this.e = false;
                this.g = null;
                this.h = false;
                this.f = 8;
                return;
            }
            LinkedHashMap<String, Integer> linkedHashMap = new LinkedHashMap<>();
            int i2 = 0;
            int i3 = 8;
            boolean z2 = false;
            boolean z3 = false;
            boolean z4 = false;
            boolean z5 = false;
            BDLocation bDLocation = null;
            String str = null;
            String str2 = null;
            while (i2 < strArr.length) {
                try {
                    if (strArr[i2].equals("-loc")) {
                        str2 = strArr[i2 + 1];
                        String[] split = str2.split("&");
                        for (int i4 = 0; i4 < split.length; i4++) {
                            if (split[i4].startsWith("cl=")) {
                                str = split[i4].substring(3);
                            } else if (split[i4].startsWith("wf=")) {
                                String[] split2 = split[i4].substring(3).split("\\|");
                                for (String split3 : split2) {
                                    String[] split4 = split3.split(";");
                                    if (split4.length >= 2) {
                                        linkedHashMap.put(split4[0], Integer.valueOf(split4[1]));
                                    }
                                }
                            }
                        }
                    } else if (strArr[i2].equals("-com")) {
                        String[] split5 = strArr[i2 + 1].split(";");
                        if (split5.length > 0) {
                            BDLocation bDLocation2 = new BDLocation();
                            try {
                                bDLocation2.a(Double.valueOf(split5[0]).doubleValue());
                                bDLocation2.b(Double.valueOf(split5[1]).doubleValue());
                                bDLocation2.e(Integer.valueOf(split5[2]).intValue());
                                bDLocation2.k(split5[3]);
                                bDLocation = bDLocation2;
                            } catch (Exception e2) {
                                bDLocation = bDLocation2;
                                z = false;
                                this.a = str2;
                                this.b = str;
                                this.i = linkedHashMap;
                                this.c = z;
                                this.d = z4;
                                this.e = z3;
                                this.f = i3;
                                this.g = bDLocation;
                                this.h = z2;
                            }
                        }
                    } else if (strArr[i2].equals("-log")) {
                        if (strArr[i2 + 1].equals("true")) {
                            z5 = true;
                        }
                    } else if (strArr[i2].equals("-rgc")) {
                        if (strArr[i2 + 1].equals("true")) {
                            z3 = true;
                        }
                    } else if (strArr[i2].equals("-poi")) {
                        if (strArr[i2 + 1].equals("true")) {
                            z4 = true;
                        }
                    } else if (strArr[i2].equals("-minap")) {
                        try {
                            i3 = Integer.valueOf(strArr[i2 + 1]).intValue();
                        } catch (Exception e3) {
                        }
                    } else if (strArr[i2].equals("-des") && strArr[i2 + 1].equals("true")) {
                        z2 = true;
                    }
                    i2 += 2;
                } catch (Exception e4) {
                    z = false;
                    this.a = str2;
                    this.b = str;
                    this.i = linkedHashMap;
                    this.c = z;
                    this.d = z4;
                    this.e = z3;
                    this.f = i3;
                    this.g = bDLocation;
                    this.h = z2;
                }
            }
            if (!z5) {
                str2 = null;
            }
            z = true;
            this.a = str2;
            this.b = str;
            this.i = linkedHashMap;
            this.c = z;
            this.d = z4;
            this.e = z3;
            this.f = i3;
            this.g = bDLocation;
            this.h = z2;
        }
    }

    static Cursor a(BDLocation bDLocation) {
        Object obj;
        Object obj2;
        Object obj3;
        Object obj4;
        Object obj5;
        Object obj6;
        Object obj7;
        Object obj8;
        String format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.US).format(new Date(System.currentTimeMillis()));
        MatrixCursor matrixCursor = new MatrixCursor(a);
        Object[] objArr = new Object[a.length];
        objArr[matrixCursor.getColumnIndex("CoorType")] = "gcj02";
        objArr[matrixCursor.getColumnIndex("Time")] = format;
        objArr[matrixCursor.getColumnIndex("LocType")] = Integer.valueOf(bDLocation.o());
        objArr[matrixCursor.getColumnIndex("Longitude")] = Double.valueOf(bDLocation.i());
        objArr[matrixCursor.getColumnIndex("Latitude")] = Double.valueOf(bDLocation.h());
        objArr[matrixCursor.getColumnIndex("Radius")] = Float.valueOf(bDLocation.l());
        objArr[matrixCursor.getColumnIndex("NetworkLocationType")] = bDLocation.H();
        com.baidu.location.a t = bDLocation.t();
        if (t != null) {
            obj8 = t.a;
            obj7 = t.b;
            obj6 = t.c;
            obj5 = t.d;
            obj4 = t.e;
            obj3 = t.f;
            obj2 = t.g;
            obj = t.h;
        } else {
            obj = null;
            obj2 = null;
            obj3 = null;
            obj4 = null;
            obj5 = null;
            obj6 = null;
            obj7 = null;
            obj8 = null;
        }
        objArr[matrixCursor.getColumnIndex("Country")] = obj8;
        objArr[matrixCursor.getColumnIndex("CountryCode")] = obj7;
        objArr[matrixCursor.getColumnIndex("Province")] = obj6;
        objArr[matrixCursor.getColumnIndex("City")] = obj5;
        objArr[matrixCursor.getColumnIndex("CityCode")] = obj4;
        objArr[matrixCursor.getColumnIndex("District")] = obj3;
        objArr[matrixCursor.getColumnIndex("Street")] = obj2;
        objArr[matrixCursor.getColumnIndex("StreetNumber")] = obj;
        List a2 = bDLocation.a();
        if (a2 == null) {
            objArr[matrixCursor.getColumnIndex("PoiList")] = null;
        } else {
            StringBuffer stringBuffer = new StringBuffer();
            int i = 0;
            while (true) {
                int i2 = i;
                if (i2 >= a2.size()) {
                    break;
                }
                Poi poi = (Poi) a2.get(i2);
                stringBuffer.append(poi.a()).append(";").append(poi.c()).append(";").append(poi.b()).append(";|");
                i = i2 + 1;
            }
            objArr[matrixCursor.getColumnIndex("PoiList")] = stringBuffer.toString();
        }
        objArr[matrixCursor.getColumnIndex("LocationDescription")] = bDLocation.B();
        matrixCursor.addRow(objArr);
        return matrixCursor;
    }

    static BDLocation a(Cursor cursor) {
        BDLocation bDLocation = new BDLocation();
        if (cursor == null || cursor.getCount() <= 0 || !cursor.moveToFirst()) {
            bDLocation.e(67);
        } else {
            int i = 0;
            double d = 0.0d;
            double d2 = 0.0d;
            String str = null;
            String str2 = null;
            float f = 0.0f;
            String str3 = null;
            if (cursor.getColumnIndex("LocType") != -1) {
                i = cursor.getInt(cursor.getColumnIndex("LocType"));
            }
            if (cursor.getColumnIndex("Latitude") != -1) {
                d = cursor.getDouble(cursor.getColumnIndex("Latitude"));
            }
            if (cursor.getColumnIndex("Longitude") != -1) {
                d2 = cursor.getDouble(cursor.getColumnIndex("Longitude"));
            }
            if (cursor.getColumnIndex("CoorType") != -1) {
                str = cursor.getString(cursor.getColumnIndex("CoorType"));
            }
            if (cursor.getColumnIndex("NetworkLocationType") != -1) {
                str2 = cursor.getString(cursor.getColumnIndex("NetworkLocationType"));
            }
            if (cursor.getColumnIndex("Radius") != -1) {
                f = cursor.getFloat(cursor.getColumnIndex("Radius"));
            }
            if (cursor.getColumnIndex("Time") != -1) {
                str3 = cursor.getString(cursor.getColumnIndex("Time"));
            }
            String str4 = null;
            String str5 = null;
            String str6 = null;
            String str7 = null;
            String str8 = null;
            String str9 = null;
            String str10 = null;
            String str11 = null;
            if (cursor.getColumnIndex("Country") != -1) {
                str4 = cursor.getString(cursor.getColumnIndex("Country"));
            }
            if (cursor.getColumnIndex("CountryCode") != -1) {
                str5 = cursor.getString(cursor.getColumnIndex("CountryCode"));
            }
            if (cursor.getColumnIndex("Province") != -1) {
                str6 = cursor.getString(cursor.getColumnIndex("Province"));
            }
            if (cursor.getColumnIndex("City") != -1) {
                str7 = cursor.getString(cursor.getColumnIndex("City"));
            }
            if (cursor.getColumnIndex("CityCode") != -1) {
                str8 = cursor.getString(cursor.getColumnIndex("CityCode"));
            }
            if (cursor.getColumnIndex("District") != -1) {
                str9 = cursor.getString(cursor.getColumnIndex("District"));
            }
            if (cursor.getColumnIndex("Street") != -1) {
                str10 = cursor.getString(cursor.getColumnIndex("Street"));
            }
            if (cursor.getColumnIndex("StreetNumber") != -1) {
                str11 = cursor.getString(cursor.getColumnIndex("StreetNumber"));
            }
            com.baidu.location.a a2 = new C0022a().a(str4).c(str5).d(str6).e(str7).f(str8).g(str9).h(str10).i(str11).a();
            ArrayList arrayList = null;
            if (cursor.getColumnIndex("PoiList") != -1) {
                arrayList = new ArrayList();
                String string = cursor.getString(cursor.getColumnIndex("PoiList"));
                if (string != null) {
                    try {
                        String[] split = string.split("\\|");
                        for (int i2 = 0; i2 < split.length; i2++) {
                            String[] split2 = split[i2].split(";");
                            if (split2.length >= 3) {
                                Poi poi = new Poi(split2[0], split2[1], Double.valueOf(split2[2]).doubleValue());
                                arrayList.add(poi);
                            }
                        }
                    } catch (Exception e) {
                        if (arrayList.size() == 0) {
                            arrayList = null;
                        }
                    } catch (Throwable th) {
                        if (arrayList.size() == 0) {
                        }
                        throw th;
                    }
                }
                if (arrayList.size() == 0) {
                    arrayList = null;
                }
            }
            String str12 = null;
            if (cursor.getColumnIndex("LocationDescription") != -1) {
                str12 = cursor.getString(cursor.getColumnIndex("LocationDescription"));
            }
            bDLocation.b(str3);
            bDLocation.b(f);
            bDLocation.e(i);
            bDLocation.d(str);
            bDLocation.a(d);
            bDLocation.b(d2);
            bDLocation.k(str2);
            bDLocation.a(a2);
            bDLocation.a((List<Poi>) arrayList);
            bDLocation.g(str12);
        }
        return bDLocation;
    }

    static String a(BDLocation bDLocation, int i) {
        if (bDLocation == null || bDLocation.o() == 67) {
            return String.format(Locale.CHINA, "&ofl=%s|%d", new Object[]{"1", Integer.valueOf(i)});
        }
        String format = String.format(Locale.CHINA, "&ofl=%s|%d|%f|%f|%d", new Object[]{"1", Integer.valueOf(i), Double.valueOf(bDLocation.i()), Double.valueOf(bDLocation.h()), Integer.valueOf((int) bDLocation.l())});
        String str = bDLocation.t() != null ? format + "&ofaddr=" + bDLocation.t().i : format;
        if (bDLocation.a() != null && bDLocation.a().size() > 0) {
            Poi poi = (Poi) bDLocation.a().get(0);
            str = str + String.format(Locale.US, "&ofpoi=%s|%s", new Object[]{poi.a(), poi.c()});
        }
        if (b.d == null) {
            return str;
        }
        return str + String.format(Locale.US, "&pack=%s&sdk=%.3f", new Object[]{b.d, Float.valueOf(7.62f)});
    }

    static String a(BDLocation bDLocation, BDLocation bDLocation2, a aVar) {
        StringBuffer stringBuffer = new StringBuffer();
        if (bDLocation2 == null) {
            stringBuffer.append("&ofcl=0");
        } else {
            stringBuffer.append(String.format(Locale.US, "&ofcl=1|%f|%f|%d", new Object[]{Double.valueOf(bDLocation2.i()), Double.valueOf(bDLocation2.h()), Integer.valueOf((int) bDLocation2.l())}));
        }
        if (bDLocation == null) {
            stringBuffer.append("&ofwf=0");
        } else {
            stringBuffer.append(String.format(Locale.US, "&ofwf=1|%f|%f|%d", new Object[]{Double.valueOf(bDLocation.i()), Double.valueOf(bDLocation.h()), Integer.valueOf((int) bDLocation.l())}));
        }
        if (aVar == null || !aVar.e) {
            stringBuffer.append("&rgcn=0");
        } else {
            stringBuffer.append("&rgcn=1");
        }
        if (aVar == null || !aVar.d) {
            stringBuffer.append("&poin=0");
        } else {
            stringBuffer.append("&poin=1");
        }
        if (aVar == null || !aVar.h) {
            stringBuffer.append("&desc=0");
        } else {
            stringBuffer.append("&desc=1");
        }
        if (aVar != null) {
            stringBuffer.append(String.format(Locale.US, "&aps=%d", new Object[]{Integer.valueOf(aVar.f)}));
        }
        return stringBuffer.toString();
    }

    static String[] a(com.baidu.location.e.a aVar, h hVar, BDLocation bDLocation, String str, boolean z, int i) {
        ArrayList arrayList = new ArrayList();
        StringBuffer stringBuffer = new StringBuffer();
        if (aVar != null) {
            stringBuffer.append(com.baidu.location.e.b.a().b(aVar));
        }
        if (hVar != null) {
            stringBuffer.append(hVar.a(30));
        }
        if (stringBuffer.length() > 0) {
            if (str != null) {
                stringBuffer.append(str);
            }
            arrayList.add("-loc");
            arrayList.add(stringBuffer.toString());
        }
        if (bDLocation != null) {
            String format = String.format(Locale.US, "%f;%f;%d;%s", new Object[]{Double.valueOf(bDLocation.h()), Double.valueOf(bDLocation.i()), Integer.valueOf(bDLocation.o()), bDLocation.H()});
            arrayList.add("-com");
            arrayList.add(format);
        }
        if (z) {
            arrayList.add("-log");
            arrayList.add("true");
        }
        if (j.g.equals("all")) {
            arrayList.add("-rgc");
            arrayList.add("true");
        }
        if (j.j) {
            arrayList.add("-poi");
            arrayList.add("true");
        }
        if (j.h) {
            arrayList.add("-des");
            arrayList.add("true");
        }
        arrayList.add("-minap");
        arrayList.add(Integer.toString(i));
        String[] strArr = new String[arrayList.size()];
        arrayList.toArray(strArr);
        return strArr;
    }
}
