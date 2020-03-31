package me.yokeyword.fragmentation;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.app.FragmentationMagician;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import me.yokeyword.fragmentation.b.a;
import me.yokeyword.fragmentation.b.b;
import me.yokeyword.fragmentation.helper.internal.ResultRecord;

/* compiled from: TransactionDelegate */
class h {
    b a = new b(this.d);
    /* access modifiers changed from: private */
    public c b;
    private FragmentActivity c;
    /* access modifiers changed from: private */
    public Handler d = new Handler(Looper.getMainLooper());

    h(c cVar) {
        this.b = cVar;
        this.c = (FragmentActivity) cVar;
    }

    /* access modifiers changed from: 0000 */
    public void a(final Runnable runnable) {
        this.a.a((a) new a() {
            public void a() {
                runnable.run();
            }
        });
    }

    /* access modifiers changed from: 0000 */
    public void a(FragmentManager fragmentManager, int i, d dVar, boolean z, boolean z2) {
        final int i2 = i;
        final d dVar2 = dVar;
        final FragmentManager fragmentManager2 = fragmentManager;
        final boolean z3 = z;
        final boolean z4 = z2;
        a(fragmentManager, (a) new a(4) {
            public void a() {
                h.this.a(i2, dVar2);
                String name = dVar2.getClass().getName();
                me.yokeyword.fragmentation.helper.internal.b bVar = dVar2.b().e;
                if (!(bVar == null || bVar.a == null)) {
                    name = bVar.a;
                }
                h.this.a(fragmentManager2, null, dVar2, name, !z3, null, z4, 10);
            }
        });
    }

    /* access modifiers changed from: 0000 */
    public void a(FragmentManager fragmentManager, int i, int i2, d... dVarArr) {
        final FragmentManager fragmentManager2 = fragmentManager;
        final d[] dVarArr2 = dVarArr;
        final int i3 = i;
        final int i4 = i2;
        a(fragmentManager, (a) new a(4) {
            public void a() {
                FragmentTransaction beginTransaction = fragmentManager2.beginTransaction();
                int i = 0;
                while (true) {
                    int i2 = i;
                    if (i2 < dVarArr2.length) {
                        Fragment fragment = (Fragment) dVarArr2[i2];
                        h.this.b(fragment).putInt("fragmentation_arg_root_status", 1);
                        h.this.a(i3, dVarArr2[i2]);
                        beginTransaction.add(i3, fragment, fragment.getClass().getName());
                        if (i2 != i4) {
                            beginTransaction.hide(fragment);
                        }
                        i = i2 + 1;
                    } else {
                        h.this.a(fragmentManager2, beginTransaction);
                        return;
                    }
                }
            }
        });
    }

    /* access modifiers changed from: 0000 */
    public void a(FragmentManager fragmentManager, d dVar, d dVar2, int i, int i2, int i3) {
        int i4 = 2;
        if (i2 != 2) {
            i4 = 0;
        }
        final FragmentManager fragmentManager2 = fragmentManager;
        final d dVar3 = dVar;
        final d dVar4 = dVar2;
        final int i5 = i;
        final int i6 = i2;
        final int i7 = i3;
        a(fragmentManager, (a) new a(i4) {
            public void a() {
                h.this.b(fragmentManager2, dVar3, dVar4, i5, i6, i7);
            }
        });
    }

    /* access modifiers changed from: 0000 */
    public void a(final FragmentManager fragmentManager, final d dVar, final d dVar2) {
        a(fragmentManager, (a) new a() {
            public void a() {
                h.this.c(fragmentManager, dVar, dVar2);
            }
        });
    }

    /* access modifiers changed from: 0000 */
    public void b(FragmentManager fragmentManager, d dVar, d dVar2) {
        final d dVar3 = dVar;
        final FragmentManager fragmentManager2 = fragmentManager;
        final d dVar4 = dVar2;
        a(fragmentManager, (a) new a(2) {
            public void a() {
                d a2 = h.this.a(dVar3, fragmentManager2);
                if (a2 == null) {
                    throw new NullPointerException("There is no Fragment in the FragmentManager, maybe you need to call loadRootFragment() first!");
                }
                h.this.a(a2.b().d, dVar4);
                h.this.a(fragmentManager2, "popTo()");
                FragmentationMagician.executePendingTransactionsAllowingStateLoss(fragmentManager2);
                a2.b().c = true;
                if (!FragmentationMagician.isStateSaved(fragmentManager2)) {
                    h.this.a(g.a(fragmentManager2), dVar4, a2.b().b.d);
                }
                h.this.c(fragmentManager2);
                FragmentationMagician.popBackStackAllowingStateLoss(fragmentManager2);
                FragmentationMagician.executePendingTransactionsAllowingStateLoss(fragmentManager2);
                h.this.d.post(new Runnable() {
                    public void run() {
                        FragmentationMagician.reorderIndices(fragmentManager2);
                    }
                });
            }
        });
        a(fragmentManager, dVar, dVar2, 0, 0, 0);
    }

    /* access modifiers changed from: 0000 */
    public void a(FragmentManager fragmentManager, d dVar, d dVar2, String str, boolean z) {
        final boolean z2 = z;
        final FragmentManager fragmentManager2 = fragmentManager;
        final String str2 = str;
        final d dVar3 = dVar;
        final d dVar4 = dVar2;
        a(fragmentManager, (a) new a(2) {
            public void a() {
                int i = 0;
                if (z2) {
                    i = 1;
                }
                List a2 = g.a(fragmentManager2, str2, z2);
                d a3 = h.this.a(dVar3, fragmentManager2);
                if (a3 == null) {
                    throw new NullPointerException("There is no Fragment in the FragmentManager, maybe you need to call loadRootFragment() first!");
                }
                h.this.a(a3.b().d, dVar4);
                if (a2.size() > 0) {
                    h.this.a(fragmentManager2, "startWithPopTo()");
                    FragmentationMagician.executePendingTransactionsAllowingStateLoss(fragmentManager2);
                    if (!FragmentationMagician.isStateSaved(fragmentManager2)) {
                        h.this.a(g.a(fragmentManager2), dVar4, a3.b().b.d);
                    }
                    h.this.a(str2, fragmentManager2, i, a2);
                }
            }
        });
        a(fragmentManager, dVar, dVar2, 0, 0, 0);
    }

    /* access modifiers changed from: 0000 */
    public void a(final FragmentManager fragmentManager) {
        a(fragmentManager, (a) new a(1, fragmentManager) {
            public void a() {
                h.this.a(fragmentManager, "pop()");
                h.this.c(fragmentManager);
                FragmentationMagician.popBackStackAllowingStateLoss(fragmentManager);
            }
        });
    }

    /* access modifiers changed from: private */
    public void c(FragmentManager fragmentManager) {
        d c2 = g.c(fragmentManager);
        if (c2 != null) {
            fragmentManager.beginTransaction().setTransition(8194).remove((Fragment) c2).commitAllowingStateLoss();
        }
    }

    /* access modifiers changed from: 0000 */
    public void b(final FragmentManager fragmentManager) {
        a(fragmentManager, (a) new a(2) {
            public void a() {
                h.this.b.getSupportDelegate().a = true;
                h.this.c(fragmentManager);
                FragmentationMagician.popBackStackAllowingStateLoss(fragmentManager);
                FragmentationMagician.executePendingTransactionsAllowingStateLoss(fragmentManager);
                h.this.b.getSupportDelegate().a = false;
            }
        });
    }

    /* access modifiers changed from: 0000 */
    public void a(String str, boolean z, Runnable runnable, FragmentManager fragmentManager, int i) {
        final String str2 = str;
        final boolean z2 = z;
        final FragmentManager fragmentManager2 = fragmentManager;
        final int i2 = i;
        final Runnable runnable2 = runnable;
        a(fragmentManager, (a) new a(2) {
            public void a() {
                h.this.a(str2, z2, fragmentManager2, i2);
                if (runnable2 != null) {
                    runnable2.run();
                }
            }
        });
    }

    /* access modifiers changed from: 0000 */
    public boolean a(d dVar) {
        if (dVar != null) {
            if (dVar.a()) {
                return true;
            }
            if (a((d) ((Fragment) dVar).getParentFragment())) {
                return true;
            }
        }
        return false;
    }

    /* access modifiers changed from: 0000 */
    public void a(Fragment fragment) {
        try {
            Bundle arguments = fragment.getArguments();
            if (arguments != null) {
                ResultRecord resultRecord = (ResultRecord) arguments.getParcelable("fragment_arg_result_record");
                if (resultRecord != null) {
                    ((d) fragment.getFragmentManager().getFragment(fragment.getArguments(), "fragmentation_state_save_result")).a(resultRecord.a, resultRecord.b, resultRecord.c);
                }
            }
        } catch (IllegalStateException e) {
        }
    }

    private void a(FragmentManager fragmentManager, a aVar) {
        if (fragmentManager == null) {
            Log.w("Fragmentation", "FragmentManager is null, skip the action!");
        } else {
            this.a.a(aVar);
        }
    }

    /* access modifiers changed from: private */
    public void b(FragmentManager fragmentManager, d dVar, d dVar2, int i, int i2, int i3) {
        boolean z;
        String str;
        a((T) dVar2, "toFragment == null");
        if ((i3 == 1 || i3 == 3) && dVar != null) {
            if (!((Fragment) dVar).isAdded()) {
                Log.w("Fragmentation", ((Fragment) dVar).getClass().getSimpleName() + " has not been attached yet! startForResult() converted to start()");
            } else {
                a(fragmentManager, (Fragment) dVar, (Fragment) dVar2, i);
            }
        }
        d a2 = a(dVar, fragmentManager);
        int i4 = b((Fragment) dVar2).getInt("fragmentation_arg_container", 0);
        if (a2 == null && i4 == 0) {
            Log.e("Fragmentation", "There is no Fragment in the FragmentManager, maybe you need to call loadRootFragment()!");
            return;
        }
        if (a2 != null && i4 == 0) {
            a(a2.b().d, dVar2);
        }
        String name = dVar2.getClass().getName();
        ArrayList<me.yokeyword.fragmentation.helper.internal.b.a> arrayList = null;
        me.yokeyword.fragmentation.helper.internal.b bVar = dVar2.b().e;
        if (bVar != null) {
            if (bVar.a != null) {
                name = bVar.a;
            }
            boolean z2 = bVar.f;
            if (bVar.g != null) {
                arrayList = bVar.g;
                FragmentationMagician.reorderIndices(fragmentManager);
            }
            z = z2;
            str = name;
        } else {
            z = false;
            str = name;
        }
        if (!a(fragmentManager, a2, dVar2, str, i2)) {
            a(fragmentManager, a2, dVar2, str, z, arrayList, false, i3);
        }
    }

    /* access modifiers changed from: private */
    public d a(d dVar, FragmentManager fragmentManager) {
        if (dVar == null) {
            return g.a(fragmentManager);
        }
        if (dVar.b().d == 0) {
            Fragment fragment = (Fragment) dVar;
            if (fragment.getTag() != null && !fragment.getTag().startsWith("android:switcher:")) {
                throw new IllegalStateException("Can't find container, please call loadRootFragment() first!");
            }
        }
        return g.a(fragmentManager, dVar.b().d);
    }

    /* access modifiers changed from: private */
    public void a(FragmentManager fragmentManager, d dVar, d dVar2, String str, boolean z, ArrayList<me.yokeyword.fragmentation.helper.internal.b.a> arrayList, boolean z2, int i) {
        int i2;
        FragmentTransaction beginTransaction = fragmentManager.beginTransaction();
        boolean z3 = i == 0 || i == 1 || i == 2 || i == 3;
        Fragment fragment = (Fragment) dVar;
        Fragment fragment2 = (Fragment) dVar2;
        Bundle b2 = b(fragment2);
        b2.putBoolean("fragmentation_arg_replace", !z3);
        if (arrayList != null) {
            b2.putBoolean("fragmentation_arg_is_shared_element", true);
            Iterator it = arrayList.iterator();
            while (it.hasNext()) {
                me.yokeyword.fragmentation.helper.internal.b.a aVar = (me.yokeyword.fragmentation.helper.internal.b.a) it.next();
                beginTransaction.addSharedElement(aVar.a, aVar.b);
            }
        } else if (z3) {
            me.yokeyword.fragmentation.helper.internal.b bVar = dVar2.b().e;
            if (bVar == null || bVar.b == Integer.MIN_VALUE) {
                beginTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
            } else {
                beginTransaction.setCustomAnimations(bVar.b, bVar.c, bVar.d, bVar.e);
                b2.putInt("fragmentation_arg_custom_enter_anim", bVar.b);
                b2.putInt("fragmentation_arg_custom_exit_anim", bVar.e);
                b2.putInt("fragmentation_arg_custom_pop_exit_anim", bVar.c);
            }
        } else {
            b2.putInt("fragmentation_arg_root_status", 1);
        }
        if (dVar == null) {
            beginTransaction.replace(b2.getInt("fragmentation_arg_container"), fragment2, str);
            if (!z3) {
                beginTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
                String str2 = "fragmentation_arg_root_status";
                if (z2) {
                    i2 = 2;
                } else {
                    i2 = 1;
                }
                b2.putInt(str2, i2);
            }
        } else if (z3) {
            beginTransaction.add(dVar.b().d, fragment2, str);
            if (!(i == 2 || i == 3)) {
                beginTransaction.hide(fragment);
            }
        } else {
            beginTransaction.replace(dVar.b().d, fragment2, str);
        }
        if (!z && i != 11) {
            beginTransaction.addToBackStack(str);
        }
        a(fragmentManager, beginTransaction);
    }

    /* access modifiers changed from: private */
    public void c(FragmentManager fragmentManager, d dVar, d dVar2) {
        if (dVar != dVar2) {
            FragmentTransaction show = fragmentManager.beginTransaction().show((Fragment) dVar);
            if (dVar2 == null) {
                List<Fragment> activeFragments = FragmentationMagician.getActiveFragments(fragmentManager);
                if (activeFragments != null) {
                    for (Fragment fragment : activeFragments) {
                        if (!(fragment == null || fragment == dVar)) {
                            show.hide(fragment);
                        }
                    }
                }
            } else {
                show.hide((Fragment) dVar2);
            }
            a(fragmentManager, show);
        }
    }

    /* access modifiers changed from: private */
    public void a(int i, d dVar) {
        b((Fragment) dVar).putInt("fragmentation_arg_container", i);
    }

    /* access modifiers changed from: private */
    public Bundle b(Fragment fragment) {
        Bundle arguments = fragment.getArguments();
        if (arguments != null) {
            return arguments;
        }
        Bundle bundle = new Bundle();
        fragment.setArguments(bundle);
        return bundle;
    }

    /* access modifiers changed from: private */
    public void a(FragmentManager fragmentManager, FragmentTransaction fragmentTransaction) {
        a(fragmentManager, "commit()");
        fragmentTransaction.commitAllowingStateLoss();
    }

    private boolean a(FragmentManager fragmentManager, d dVar, final d dVar2, String str, int i) {
        if (dVar == null) {
            return false;
        }
        final d b2 = g.b(dVar2.getClass(), str, fragmentManager);
        if (b2 == null) {
            return false;
        }
        if (i == 1) {
            if (dVar2 != dVar && !dVar2.getClass().getName().equals(dVar.getClass().getName())) {
                return false;
            }
            a(dVar2, b2);
            return true;
        } else if (i != 2) {
            return false;
        } else {
            a(str, false, fragmentManager, Integer.MAX_VALUE);
            this.d.post(new Runnable() {
                public void run() {
                    h.this.a(dVar2, b2);
                }
            });
            return true;
        }
    }

    /* access modifiers changed from: private */
    public void a(d dVar, d dVar2) {
        Bundle bundle = dVar.b().f;
        Bundle b2 = b((Fragment) dVar);
        if (b2.containsKey("fragmentation_arg_container")) {
            b2.remove("fragmentation_arg_container");
        }
        if (bundle != null) {
            b2.putAll(bundle);
        }
        dVar2.c(b2);
    }

    private void a(FragmentManager fragmentManager, Fragment fragment, Fragment fragment2, int i) {
        Bundle b2 = b(fragment2);
        ResultRecord resultRecord = new ResultRecord();
        resultRecord.a = i;
        b2.putParcelable("fragment_arg_result_record", resultRecord);
        fragmentManager.putFragment(b2, "fragmentation_state_save_result", fragment);
    }

    /* access modifiers changed from: private */
    public void a(String str, boolean z, FragmentManager fragmentManager, int i) {
        int i2;
        a(fragmentManager, "popTo()");
        if (fragmentManager.findFragmentByTag(str) == null) {
            Log.e("Fragmentation", "Pop failure! Can't find FragmentTag:" + str + " in the FragmentManager's Stack.");
            return;
        }
        if (z) {
            i2 = 1;
        } else {
            i2 = 0;
        }
        List a2 = g.a(fragmentManager, str, z);
        if (a2.size() > 0) {
            a((Fragment) a2.get(0), str, fragmentManager, i2, a2, i);
        }
    }

    /* access modifiers changed from: private */
    public void a(String str, final FragmentManager fragmentManager, int i, List<Fragment> list) {
        this.b.getSupportDelegate().a = true;
        FragmentTransaction transition = fragmentManager.beginTransaction().setTransition(8194);
        for (Fragment remove : list) {
            transition.remove(remove);
        }
        transition.commitAllowingStateLoss();
        FragmentationMagician.popBackStackAllowingStateLoss(fragmentManager, str, i);
        FragmentationMagician.executePendingTransactionsAllowingStateLoss(fragmentManager);
        this.b.getSupportDelegate().a = false;
        if (FragmentationMagician.isSupportLessThan25dot4()) {
            this.d.post(new Runnable() {
                public void run() {
                    FragmentationMagician.reorderIndices(fragmentManager);
                }
            });
        }
    }

    private void a(Fragment fragment, String str, FragmentManager fragmentManager, int i, List<Fragment> list, int i2) {
        Animation loadAnimation;
        if (!(fragment instanceof d)) {
            a(str, fragmentManager, i, list);
            return;
        }
        d dVar = (d) fragment;
        final ViewGroup a2 = a(fragment, dVar.b().d);
        if (a2 != null) {
            final View view = fragment.getView();
            if (view != null) {
                a2.removeViewInLayout(view);
                final ViewGroup a3 = a(view, a2);
                a(str, fragmentManager, i, list);
                if (i2 == Integer.MAX_VALUE) {
                    loadAnimation = dVar.b().p();
                    if (loadAnimation == null) {
                        loadAnimation = new Animation() {
                        };
                    }
                } else if (i2 == 0) {
                    loadAnimation = new Animation() {
                    };
                } else {
                    loadAnimation = AnimationUtils.loadAnimation(this.c, i2);
                }
                view.startAnimation(loadAnimation);
                this.d.postDelayed(new Runnable() {
                    public void run() {
                        try {
                            a3.removeViewInLayout(view);
                            a2.removeViewInLayout(a3);
                        } catch (Exception e) {
                        }
                    }
                }, loadAnimation.getDuration());
            }
        }
    }

    /* access modifiers changed from: private */
    public void a(d dVar, d dVar2, Animation animation) {
        Fragment fragment = (Fragment) dVar;
        final ViewGroup a2 = a(fragment, dVar.b().d);
        if (a2 != null) {
            final View view = fragment.getView();
            if (view != null) {
                a2.removeViewInLayout(view);
                final ViewGroup a3 = a(view, a2);
                final Animation animation2 = animation;
                dVar2.b().i = new a() {
                    public void a() {
                        view.startAnimation(animation2);
                        h.this.d.postDelayed(new Runnable() {
                            public void run() {
                                try {
                                    a3.removeViewInLayout(view);
                                    a2.removeViewInLayout(a3);
                                } catch (Exception e) {
                                }
                            }
                        }, animation2.getDuration());
                    }
                };
            }
        }
    }

    private ViewGroup a(View view, ViewGroup viewGroup) {
        AnonymousClass10 r0 = new ViewGroup(this.c) {
            /* access modifiers changed from: protected */
            public void onLayout(boolean z, int i, int i2, int i3, int i4) {
            }
        };
        r0.addView(view);
        viewGroup.addView(r0);
        return r0;
    }

    private ViewGroup a(Fragment fragment, int i) {
        Object findViewById;
        if (fragment.getView() == null) {
            return null;
        }
        Fragment parentFragment = fragment.getParentFragment();
        if (parentFragment == null) {
            findViewById = this.c.findViewById(i);
        } else if (parentFragment.getView() != null) {
            findViewById = parentFragment.getView().findViewById(i);
        } else {
            findViewById = a(parentFragment, i);
        }
        if (findViewById instanceof ViewGroup) {
            return (ViewGroup) findViewById;
        }
        return null;
    }

    private static <T> void a(T t, String str) {
        if (t == null) {
            throw new NullPointerException(str);
        }
    }

    /* access modifiers changed from: private */
    public void a(FragmentManager fragmentManager, String str) {
        if (FragmentationMagician.isStateSaved(fragmentManager)) {
            me.yokeyword.fragmentation.a.a aVar = new me.yokeyword.fragmentation.a.a(str);
            if (b.a().b() != null) {
                b.a().b().a(aVar);
            }
        }
    }
}
