package net.sf.json.util;

import net.sf.json.JSONException;

public interface JsonEventListener {
    void onArrayEnd();

    void onArrayStart();

    void onElementAdded(int i, Object obj);

    void onError(JSONException jSONException);

    void onObjectEnd();

    void onObjectStart();

    void onPropertySet(String str, Object obj, boolean z);

    void onWarning(String str);
}
