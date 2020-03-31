package me.yokeyword.fragmentation.a;

import android.util.Log;

/* compiled from: AfterSaveStateTransactionWarning */
public class a extends RuntimeException {
    public a(String str) {
        super("Warning: Perform this " + str + " action after onSaveInstanceState!");
        Log.w("Fragmentation", getMessage());
    }
}
