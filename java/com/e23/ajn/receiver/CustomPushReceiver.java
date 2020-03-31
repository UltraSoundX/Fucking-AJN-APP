package com.e23.ajn.receiver;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;
import com.baidu.mobstat.Config;
import com.tencent.android.tpush.SettingsContentProvider;
import com.tencent.android.tpush.XGPushBaseReceiver;
import com.tencent.android.tpush.XGPushClickedResult;
import com.tencent.android.tpush.XGPushRegisterResult;
import com.tencent.android.tpush.XGPushShowedResult;
import com.tencent.android.tpush.XGPushTextMessage;
import net.sf.json.util.JSONUtils;
import org.json.JSONException;
import org.json.JSONObject;

public class CustomPushReceiver extends XGPushBaseReceiver {
    private void a(Context context, String str) {
        System.out.println(str);
        Toast.makeText(context, str, 0).show();
    }

    public void onRegisterResult(Context context, int i, XGPushRegisterResult xGPushRegisterResult) {
        String str;
        if (context != null && xGPushRegisterResult != null) {
            if (i == 0) {
                str = xGPushRegisterResult + "";
                xGPushRegisterResult.getToken();
            } else {
                str = xGPushRegisterResult + "" + i;
            }
            Log.d("TPushReceiver", str);
            a(context, str);
        }
    }

    public void onUnregisterResult(Context context, int i) {
        String str;
        if (context != null) {
            if (i == 0) {
                str = "";
            } else {
                str = "" + i;
            }
            Log.d("TPushReceiver", str);
            a(context, str);
        }
    }

    public void onSetTagResult(Context context, int i, String str) {
        String str2;
        if (context != null) {
            if (i == 0) {
                str2 = JSONUtils.DOUBLE_QUOTE + str + JSONUtils.DOUBLE_QUOTE;
            } else {
                str2 = JSONUtils.DOUBLE_QUOTE + str + JSONUtils.DOUBLE_QUOTE + i;
            }
            Log.d("TPushReceiver", str2);
            a(context, str2);
        }
    }

    public void onDeleteTagResult(Context context, int i, String str) {
        String str2;
        if (context != null) {
            if (i == 0) {
                str2 = JSONUtils.DOUBLE_QUOTE + str + JSONUtils.DOUBLE_QUOTE;
            } else {
                str2 = JSONUtils.DOUBLE_QUOTE + str + JSONUtils.DOUBLE_QUOTE + i;
            }
            Log.d("TPushReceiver", str2);
            a(context, str2);
        }
    }

    public void onTextMessage(Context context, XGPushTextMessage xGPushTextMessage) {
        if (context != null && xGPushTextMessage != null) {
            String str = "" + xGPushTextMessage.toString();
            String customContent = xGPushTextMessage.getCustomContent();
            if (!(customContent == null || customContent.length() == 0)) {
                try {
                    JSONObject jSONObject = new JSONObject(customContent);
                    if (!jSONObject.isNull(SettingsContentProvider.KEY)) {
                        Log.d("TPushReceiver", "get custom value:" + jSONObject.getString(SettingsContentProvider.KEY));
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
            Log.d("TPushReceiver", str);
            a(context, str);
        }
    }

    public void onNotifactionClickedResult(Context context, XGPushClickedResult xGPushClickedResult) {
        if (context != null && xGPushClickedResult != null) {
            String str = Config.TRACE_TODAY_VISIT_SPLIT + xGPushClickedResult;
            System.out.println("onNotifactionClickedResult=>" + xGPushClickedResult.toString());
            String customContent = xGPushClickedResult.getCustomContent();
            if (!(customContent == null || customContent.length() == 0)) {
                try {
                    JSONObject jSONObject = new JSONObject(customContent);
                    if (!jSONObject.isNull(SettingsContentProvider.KEY)) {
                        Log.d("TPushReceiver", "get custom value:" + jSONObject.getString(SettingsContentProvider.KEY));
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
            Log.d("TPushReceiver", str);
        }
    }

    public void onNotifactionShowedResult(Context context, XGPushShowedResult xGPushShowedResult) {
        if (context != null && xGPushShowedResult != null) {
            String str = "title:" + xGPushShowedResult.getTitle() + ",content:" + xGPushShowedResult.getContent() + ",custom_content:" + xGPushShowedResult.getCustomContent();
            Log.d("TPushReceiver", str);
            System.out.println("onNotifactionShowedResult=>" + str);
            a(context, str);
        }
    }
}
