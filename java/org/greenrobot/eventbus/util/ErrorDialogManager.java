package org.greenrobot.eventbus.util;

import android.annotation.TargetApi;
import android.app.Fragment;
import android.os.Bundle;
import org.greenrobot.eventbus.c;

public class ErrorDialogManager {
    public static b<?> a;

    @TargetApi(11)
    public static class HoneycombManagerFragment extends Fragment {
        private c a;

        public void onResume() {
            super.onResume();
            this.a = ErrorDialogManager.a.a.a();
            this.a.a((Object) this);
        }

        public void onPause() {
            this.a.b(this);
            super.onPause();
        }
    }

    public static class SupportManagerFragment extends android.support.v4.app.Fragment {
        private c a;
        private boolean b;

        public void onCreate(Bundle bundle) {
            super.onCreate(bundle);
            this.a = ErrorDialogManager.a.a.a();
            this.a.a((Object) this);
            this.b = true;
        }

        public void onResume() {
            super.onResume();
            if (this.b) {
                this.b = false;
                return;
            }
            this.a = ErrorDialogManager.a.a.a();
            this.a.a((Object) this);
        }

        public void onPause() {
            this.a.b(this);
            super.onPause();
        }
    }
}
