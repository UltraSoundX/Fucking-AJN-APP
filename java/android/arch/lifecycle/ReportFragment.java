package android.arch.lifecycle;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.os.Bundle;

public class ReportFragment extends Fragment {
    private a a;

    interface a {
        void a();

        void b();

        void c();
    }

    public static void a(Activity activity) {
        FragmentManager fragmentManager = activity.getFragmentManager();
        if (fragmentManager.findFragmentByTag("android.arch.lifecycle.LifecycleDispatcher.report_fragment_tag") == null) {
            fragmentManager.beginTransaction().add(new ReportFragment(), "android.arch.lifecycle.LifecycleDispatcher.report_fragment_tag").commit();
            fragmentManager.executePendingTransactions();
        }
    }

    private void a(a aVar) {
        if (aVar != null) {
            aVar.a();
        }
    }

    private void b(a aVar) {
        if (aVar != null) {
            aVar.b();
        }
    }

    private void c(a aVar) {
        if (aVar != null) {
            aVar.c();
        }
    }

    public void onActivityCreated(Bundle bundle) {
        super.onActivityCreated(bundle);
        a(this.a);
        a(android.arch.lifecycle.c.a.ON_CREATE);
    }

    public void onStart() {
        super.onStart();
        b(this.a);
        a(android.arch.lifecycle.c.a.ON_START);
    }

    public void onResume() {
        super.onResume();
        c(this.a);
        a(android.arch.lifecycle.c.a.ON_RESUME);
    }

    public void onPause() {
        super.onPause();
        a(android.arch.lifecycle.c.a.ON_PAUSE);
    }

    public void onStop() {
        super.onStop();
        a(android.arch.lifecycle.c.a.ON_STOP);
    }

    public void onDestroy() {
        super.onDestroy();
        a(android.arch.lifecycle.c.a.ON_DESTROY);
        this.a = null;
    }

    private void a(android.arch.lifecycle.c.a aVar) {
        Activity activity = getActivity();
        if (activity instanceof g) {
            ((g) activity).a().a(aVar);
        } else if (activity instanceof e) {
            c lifecycle = ((e) activity).getLifecycle();
            if (lifecycle instanceof f) {
                ((f) lifecycle).a(aVar);
            }
        }
    }
}
