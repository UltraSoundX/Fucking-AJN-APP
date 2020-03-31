package com.baidu.mobstat;

import com.tencent.smtt.sdk.TbsListener.ErrorCode;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public enum k {
    AP_LIST(0) {
        public j a() {
            return new n();
        }
    },
    APP_LIST(1) {
        public j a() {
            return new q();
        }
    },
    APP_TRACE(2) {
        public j a() {
            return new r();
        }
    },
    APP_CHANGE(3) {
        public j a() {
            return new p();
        }
    },
    APP_APK(4) {
        public j a() {
            return new o();
        }
    };
    
    private int f;

    public abstract j a();

    private k(int i) {
        this.f = i;
    }

    public String toString() {
        return String.valueOf(this.f);
    }

    public synchronized ArrayList<i> a(int i, int i2) {
        ArrayList<i> arrayList;
        arrayList = new ArrayList<>();
        j jVar = null;
        try {
            jVar = a();
            if (jVar.a()) {
                arrayList = jVar.a(i, i2);
                if (jVar != null) {
                    jVar.close();
                }
            } else if (jVar != null) {
                jVar.close();
            }
        } catch (Exception e) {
            al.c().b((Throwable) e);
            if (jVar != null) {
                jVar.close();
            }
        } catch (Throwable th) {
            if (jVar != null) {
                jVar.close();
            }
            throw th;
        }
        return arrayList;
    }

    public synchronized long a(long j, String str) {
        long j2;
        j2 = -1;
        j jVar = null;
        try {
            jVar = a();
            if (jVar.a()) {
                j2 = jVar.a(String.valueOf(j), str);
                if (jVar != null) {
                    jVar.close();
                }
            } else if (jVar != null) {
                jVar.close();
            }
        } catch (Exception e) {
            al.c().b((Throwable) e);
            if (jVar != null) {
                jVar.close();
            }
        } catch (Throwable th) {
            if (jVar != null) {
                jVar.close();
            }
            throw th;
        }
        return j2;
    }

    public synchronized int a(ArrayList<Long> arrayList) {
        int i;
        int i2 = 0;
        synchronized (this) {
            if (arrayList != null) {
                if (arrayList.size() != 0) {
                    j jVar = null;
                    try {
                        jVar = a();
                        if (jVar.a()) {
                            int size = arrayList.size();
                            int i3 = 0;
                            while (i3 < size) {
                                if (jVar.b(((Long) arrayList.get(i3)).longValue())) {
                                    i3++;
                                    i2++;
                                } else if (jVar != null) {
                                    jVar.close();
                                }
                            }
                            if (jVar != null) {
                                jVar.close();
                                i = i2;
                            } else {
                                i = i2;
                            }
                            i2 = i;
                        } else if (jVar != null) {
                            jVar.close();
                        }
                    } catch (Exception e) {
                        Exception exc = e;
                        i = 0;
                        al.c().b((Throwable) exc);
                        if (jVar != null) {
                            jVar.close();
                        }
                    } catch (Throwable th) {
                        if (jVar != null) {
                            jVar.close();
                        }
                        throw th;
                    }
                }
            }
        }
        return i2;
    }

    public synchronized List<String> a(int i) {
        List<String> arrayList;
        arrayList = new ArrayList<>();
        ArrayList arrayList2 = new ArrayList();
        ArrayList arrayList3 = new ArrayList();
        a(arrayList, arrayList2, arrayList3, i, ErrorCode.INFO_CODE_MINIQB);
        if (arrayList3.size() != 0 && arrayList.size() == 0 && arrayList2.size() == 0) {
            i iVar = (i) arrayList3.get(0);
            long a = iVar.a();
            String b = iVar.b();
            arrayList2.add(Long.valueOf(a));
            arrayList.add(b);
        }
        int a2 = a(arrayList2);
        if (a2 != arrayList.size()) {
            arrayList = arrayList.subList(0, a2);
        }
        return arrayList;
    }

    private int a(List<String> list, ArrayList<Long> arrayList, ArrayList<i> arrayList2, int i, int i2) {
        int i3;
        int i4 = 0;
        int c = c();
        int i5 = 0;
        int i6 = i2;
        while (c > 0) {
            if (c < i6) {
                i3 = c;
            } else {
                i3 = i6;
            }
            ArrayList a = a(i3, i5);
            if (i5 == 0 && a.size() != 0) {
                arrayList2.add((i) a.get(0));
            }
            Iterator it = a.iterator();
            while (it.hasNext()) {
                i iVar = (i) it.next();
                long a2 = iVar.a();
                String b = iVar.b();
                int length = b.length();
                if (i4 + length > i) {
                    break;
                }
                arrayList.add(Long.valueOf(a2));
                list.add(b);
                i4 += length;
            }
            c -= i3;
            i5 += i3;
            i6 = i3;
        }
        return i4;
    }

    public synchronized boolean b() {
        return c() == 0;
    }

    public synchronized boolean b(int i) {
        return c() >= i;
    }

    private int c() {
        j jVar = null;
        try {
            j a = a();
            if (a.a()) {
                int b = a.b();
                if (a == null) {
                    return b;
                }
                a.close();
                return b;
            }
            if (a != null) {
                a.close();
            }
            return 0;
        } catch (Exception e) {
            al.c().b((Throwable) e);
            if (jVar != null) {
                jVar.close();
            }
        } catch (Throwable th) {
            if (jVar != null) {
                jVar.close();
            }
            throw th;
        }
    }
}
