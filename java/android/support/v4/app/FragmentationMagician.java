package android.support.v4.app;

import android.util.SparseArray;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class FragmentationMagician {
    private static boolean sSupportGreaterThan27dot1dot0;
    private static boolean sSupportLessThan25dot4;

    static {
        int i = 0;
        sSupportLessThan25dot4 = false;
        sSupportGreaterThan27dot1dot0 = false;
        Field[] declaredFields = FragmentManagerImpl.class.getDeclaredFields();
        int length = declaredFields.length;
        while (i < length) {
            Field field = declaredFields[i];
            if (field.getName().equals("mStopped")) {
                sSupportGreaterThan27dot1dot0 = true;
                return;
            } else if (field.getName().equals("mAvailIndices")) {
                sSupportLessThan25dot4 = true;
                return;
            } else {
                i++;
            }
        }
    }

    public static boolean isSupportLessThan25dot4() {
        return sSupportLessThan25dot4;
    }

    public static boolean isExecutingActions(FragmentManager fragmentManager) {
        boolean z = false;
        if (!(fragmentManager instanceof FragmentManagerImpl)) {
            return z;
        }
        try {
            return ((FragmentManagerImpl) fragmentManager).mExecutingActions;
        } catch (Exception e) {
            e.printStackTrace();
            return z;
        }
    }

    public static void reorderIndices(FragmentManager fragmentManager) {
        if (sSupportLessThan25dot4 && (fragmentManager instanceof FragmentManagerImpl)) {
            try {
                Object value = getValue((FragmentManagerImpl) fragmentManager, "mAvailIndices");
                if (value != null) {
                    ArrayList arrayList = (ArrayList) value;
                    if (arrayList.size() > 1) {
                        Collections.sort(arrayList, Collections.reverseOrder());
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static boolean isStateSaved(FragmentManager fragmentManager) {
        boolean z = false;
        if (!(fragmentManager instanceof FragmentManagerImpl)) {
            return z;
        }
        try {
            return ((FragmentManagerImpl) fragmentManager).mStateSaved;
        } catch (Exception e) {
            e.printStackTrace();
            return z;
        }
    }

    public static void popBackStackAllowingStateLoss(final FragmentManager fragmentManager) {
        hookStateSaved(fragmentManager, new Runnable() {
            public void run() {
                fragmentManager.popBackStack();
            }
        });
    }

    public static void popBackStackImmediateAllowingStateLoss(final FragmentManager fragmentManager) {
        hookStateSaved(fragmentManager, new Runnable() {
            public void run() {
                fragmentManager.popBackStackImmediate();
            }
        });
    }

    public static void popBackStackAllowingStateLoss(final FragmentManager fragmentManager, final String str, final int i) {
        hookStateSaved(fragmentManager, new Runnable() {
            public void run() {
                fragmentManager.popBackStack(str, i);
            }
        });
    }

    public static void executePendingTransactionsAllowingStateLoss(final FragmentManager fragmentManager) {
        hookStateSaved(fragmentManager, new Runnable() {
            public void run() {
                fragmentManager.executePendingTransactions();
            }
        });
    }

    public static List<Fragment> getActiveFragments(FragmentManager fragmentManager) {
        if (!(fragmentManager instanceof FragmentManagerImpl)) {
            return Collections.EMPTY_LIST;
        }
        if (sSupportLessThan25dot4) {
            return fragmentManager.getFragments();
        }
        try {
            return getActiveList(((FragmentManagerImpl) fragmentManager).mActive);
        } catch (Exception e) {
            e.printStackTrace();
            return fragmentManager.getFragments();
        }
    }

    private static List<Fragment> getActiveList(SparseArray<Fragment> sparseArray) {
        if (sparseArray == null) {
            return Collections.EMPTY_LIST;
        }
        int size = sparseArray.size();
        ArrayList arrayList = new ArrayList(size);
        for (int i = 0; i < size; i++) {
            arrayList.add(sparseArray.valueAt(i));
        }
        return arrayList;
    }

    private static Object getValue(Object obj, String str) throws Exception {
        try {
            Field declaredField = obj.getClass().getDeclaredField(str);
            declaredField.setAccessible(true);
            return declaredField.get(obj);
        } catch (Exception e) {
            return null;
        }
    }

    private static void hookStateSaved(FragmentManager fragmentManager, Runnable runnable) {
        if (fragmentManager instanceof FragmentManagerImpl) {
            FragmentManagerImpl fragmentManagerImpl = (FragmentManagerImpl) fragmentManager;
            if (isStateSaved(fragmentManager)) {
                fragmentManagerImpl.mStateSaved = false;
                compatRunAction(fragmentManagerImpl, runnable);
                fragmentManagerImpl.mStateSaved = true;
                return;
            }
            runnable.run();
        }
    }

    private static void compatRunAction(FragmentManagerImpl fragmentManagerImpl, Runnable runnable) {
        if (!sSupportGreaterThan27dot1dot0) {
            runnable.run();
            return;
        }
        fragmentManagerImpl.mStopped = false;
        runnable.run();
        fragmentManagerImpl.mStopped = true;
    }
}
