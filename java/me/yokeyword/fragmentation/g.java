package me.yokeyword.fragmentation;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentManager.BackStackEntry;
import android.support.v4.app.FragmentationMagician;
import java.util.ArrayList;
import java.util.List;

/* compiled from: SupportHelper */
public class g {
    public static d a(FragmentManager fragmentManager) {
        return a(fragmentManager, 0);
    }

    public static d a(FragmentManager fragmentManager, int i) {
        List activeFragments = FragmentationMagician.getActiveFragments(fragmentManager);
        if (activeFragments == null) {
            return null;
        }
        for (int size = activeFragments.size() - 1; size >= 0; size--) {
            Fragment fragment = (Fragment) activeFragments.get(size);
            if (fragment instanceof d) {
                d dVar = (d) fragment;
                if (i == 0 || i == dVar.b().d) {
                    return dVar;
                }
            }
        }
        return null;
    }

    public static d a(Fragment fragment) {
        FragmentManager fragmentManager = fragment.getFragmentManager();
        if (fragmentManager == null) {
            return null;
        }
        List activeFragments = FragmentationMagician.getActiveFragments(fragmentManager);
        if (activeFragments == null) {
            return null;
        }
        for (int indexOf = activeFragments.indexOf(fragment) - 1; indexOf >= 0; indexOf--) {
            Fragment fragment2 = (Fragment) activeFragments.get(indexOf);
            if (fragment2 instanceof d) {
                return (d) fragment2;
            }
        }
        return null;
    }

    public static <T extends d> T a(FragmentManager fragmentManager, Class<T> cls) {
        return a(cls, (String) null, fragmentManager);
    }

    public static d b(FragmentManager fragmentManager) {
        return a(fragmentManager, (d) null);
    }

    static <T extends d> T a(Class<T> cls, String str, FragmentManager fragmentManager) {
        Fragment findFragmentByTag;
        if (str == null) {
            List activeFragments = FragmentationMagician.getActiveFragments(fragmentManager);
            if (activeFragments != null) {
                int size = activeFragments.size() - 1;
                while (true) {
                    if (size < 0) {
                        findFragmentByTag = null;
                        break;
                    }
                    findFragmentByTag = (Fragment) activeFragments.get(size);
                    if ((findFragmentByTag instanceof d) && findFragmentByTag.getClass().getName().equals(cls.getName())) {
                        break;
                    }
                    size--;
                }
            } else {
                return null;
            }
        } else {
            findFragmentByTag = fragmentManager.findFragmentByTag(str);
            if (findFragmentByTag == null) {
                return null;
            }
        }
        return (d) findFragmentByTag;
    }

    private static d a(FragmentManager fragmentManager, d dVar) {
        List activeFragments = FragmentationMagician.getActiveFragments(fragmentManager);
        if (activeFragments == null) {
            return dVar;
        }
        for (int size = activeFragments.size() - 1; size >= 0; size--) {
            Fragment fragment = (Fragment) activeFragments.get(size);
            if ((fragment instanceof d) && fragment.isResumed() && !fragment.isHidden() && fragment.getUserVisibleHint()) {
                return a(fragment.getChildFragmentManager(), (d) fragment);
            }
        }
        return dVar;
    }

    public static d c(FragmentManager fragmentManager) {
        return b(fragmentManager, 0);
    }

    public static d b(FragmentManager fragmentManager, int i) {
        for (int backStackEntryCount = fragmentManager.getBackStackEntryCount() - 1; backStackEntryCount >= 0; backStackEntryCount--) {
            Fragment findFragmentByTag = fragmentManager.findFragmentByTag(fragmentManager.getBackStackEntryAt(backStackEntryCount).getName());
            if (findFragmentByTag instanceof d) {
                d dVar = (d) findFragmentByTag;
                if (i == 0 || i == dVar.b().d) {
                    return dVar;
                }
            }
        }
        return null;
    }

    static <T extends d> T b(Class<T> cls, String str, FragmentManager fragmentManager) {
        int backStackEntryCount = fragmentManager.getBackStackEntryCount();
        if (str == null) {
            str = cls.getName();
        }
        for (int i = backStackEntryCount - 1; i >= 0; i--) {
            BackStackEntry backStackEntryAt = fragmentManager.getBackStackEntryAt(i);
            if (str.equals(backStackEntryAt.getName())) {
                T findFragmentByTag = fragmentManager.findFragmentByTag(backStackEntryAt.getName());
                if (findFragmentByTag instanceof d) {
                    return (d) findFragmentByTag;
                }
            }
        }
        return null;
    }

    static List<Fragment> a(FragmentManager fragmentManager, String str, boolean z) {
        int i;
        Fragment findFragmentByTag = fragmentManager.findFragmentByTag(str);
        ArrayList arrayList = new ArrayList();
        List activeFragments = FragmentationMagician.getActiveFragments(fragmentManager);
        if (activeFragments == null) {
            return arrayList;
        }
        int size = activeFragments.size();
        int i2 = size - 1;
        while (true) {
            if (i2 < 0) {
                break;
            } else if (findFragmentByTag != activeFragments.get(i2)) {
                i2--;
            } else if (z) {
                i = i2;
            } else if (i2 + 1 < size) {
                i = i2 + 1;
            }
        }
        i = -1;
        if (i == -1) {
            return arrayList;
        }
        for (int i3 = size - 1; i3 >= i; i3--) {
            Fragment fragment = (Fragment) activeFragments.get(i3);
            if (!(fragment == null || fragment.getView() == null)) {
                arrayList.add(fragment);
            }
        }
        return arrayList;
    }
}
