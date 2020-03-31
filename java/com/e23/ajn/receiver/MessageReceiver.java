package com.e23.ajn.receiver;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import com.e23.ajn.activity.MainActivity;
import com.e23.ajn.activity.OutUrlActivity;
import com.e23.ajn.activity.SwipeBackCommonActivity;
import com.e23.ajn.d.t;
import com.tencent.android.tpush.SettingsContentProvider;
import com.tencent.android.tpush.XGPushBaseReceiver;
import com.tencent.android.tpush.XGPushClickedResult;
import com.tencent.android.tpush.XGPushRegisterResult;
import com.tencent.android.tpush.XGPushShowedResult;
import com.tencent.android.tpush.XGPushTextMessage;
import net.sf.json.util.JSONUtils;
import org.json.JSONException;
import org.json.JSONObject;

public class MessageReceiver extends XGPushBaseReceiver {
    private Intent b = new Intent("com.qq.xgdemo.activity.UPDATE_LISTVIEW");

    private void a(Context context, String str) {
    }

    public void onNotifactionShowedResult(Context context, XGPushShowedResult xGPushShowedResult) {
        if (context != null && xGPushShowedResult != null) {
            context.sendBroadcast(this.b);
            System.out.println("您有1条新消息, 通知被展示 ， " + xGPushShowedResult.toString());
        }
    }

    public void onUnregisterResult(Context context, int i) {
        if (context != null) {
            String str = "";
            if (i == 0) {
                String str2 = "反注册成功";
            } else {
                "反注册失败" + i;
            }
        }
    }

    public void onSetTagResult(Context context, int i, String str) {
        if (context != null) {
            String str2 = "";
            if (i == 0) {
                JSONUtils.DOUBLE_QUOTE + str + "\"设置成功";
            } else {
                JSONUtils.DOUBLE_QUOTE + str + "\"设置失败,错误码：" + i;
            }
        }
    }

    public void onDeleteTagResult(Context context, int i, String str) {
        String str2;
        if (context != null) {
            String str3 = "";
            if (i == 0) {
                str2 = JSONUtils.DOUBLE_QUOTE + str + "\"删除成功";
            } else {
                str2 = JSONUtils.DOUBLE_QUOTE + str + "\"删除失败,错误码：" + i;
            }
            Log.d("TPushReceiver", str2);
        }
    }

    public void onNotifactionClickedResult(Context context, XGPushClickedResult xGPushClickedResult) {
        String str;
        String str2;
        if (context != null && xGPushClickedResult != null) {
            String str3 = "";
            if (xGPushClickedResult.getActionType() == ((long) XGPushClickedResult.NOTIFACTION_CLICKED_TYPE)) {
                "通知被打开 :" + xGPushClickedResult;
                String customContent = xGPushClickedResult.getCustomContent();
                System.out.println("customContent=>" + customContent);
                if (customContent != null && customContent.length() != 0) {
                    try {
                        JSONObject jSONObject = new JSONObject(customContent);
                        String str4 = "";
                        if (jSONObject.has("newstitle")) {
                            str = jSONObject.getString("newstitle");
                        } else {
                            str = str4;
                        }
                        String str5 = "";
                        if (jSONObject.has("newsid")) {
                            str2 = jSONObject.getString("newsid");
                        } else {
                            str2 = str5;
                        }
                        String str6 = "";
                        if (jSONObject.has("url")) {
                            str6 = jSONObject.getString("url");
                        }
                        if (!jSONObject.isNull("opentype")) {
                            String string = jSONObject.getString("opentype");
                            if (t.a(context, "com.e23.ajn")) {
                                Log.i("NotificationReceiver", "the app process is alive");
                                Intent intent = new Intent(context, MainActivity.class);
                                intent.setFlags(268435456);
                                if (string.equals("1")) {
                                    Intent intent2 = new Intent(context, SwipeBackCommonActivity.class);
                                    intent2.putExtra(SwipeBackCommonActivity.TAG, SwipeBackCommonActivity.NEWS_DETAIL_FROM_MINE);
                                    intent2.putExtra("newsid", str2);
                                    context.startActivities(new Intent[]{intent, intent2});
                                }
                                if (string.equals("3")) {
                                    Intent intent3 = new Intent(context, OutUrlActivity.class);
                                    intent3.putExtra("url", str6);
                                    intent3.putExtra("title", str);
                                    intent3.putExtra(OutUrlActivity.ARG_THUMB, "");
                                    intent3.putExtra(OutUrlActivity.ARG_DESC, "");
                                    context.startActivities(new Intent[]{intent, intent3});
                                }
                                if (string.equals("4")) {
                                    Intent intent4 = new Intent(context, SwipeBackCommonActivity.class);
                                    intent4.putExtra(SwipeBackCommonActivity.TAG, 9);
                                    intent4.putExtra(SwipeBackCommonActivity.TITLE, str);
                                    intent4.putExtra(SwipeBackCommonActivity.URL, str6);
                                    context.startActivities(new Intent[]{intent, intent4});
                                    return;
                                }
                                return;
                            }
                            Log.i("NotificationReceiver", "the app process is dead");
                            Intent launchIntentForPackage = context.getPackageManager().getLaunchIntentForPackage("com.e23.ajn");
                            launchIntentForPackage.setFlags(270532608);
                            launchIntentForPackage.putExtra("PUSH_ARG_OPENTYPE", string);
                            launchIntentForPackage.putExtra("PUSH_ARG_NEWSID", str2);
                            launchIntentForPackage.putExtra("PUSH_ARG_OPENTYPE", string);
                            launchIntentForPackage.putExtra("PUSH_ARG_OPENTYPE", string);
                            context.startActivity(launchIntentForPackage);
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            } else if (xGPushClickedResult.getActionType() == ((long) XGPushClickedResult.NOTIFACTION_DELETED_TYPE)) {
                "通知被清除 :" + xGPushClickedResult;
            }
        }
    }

    public void onRegisterResult(Context context, int i, XGPushRegisterResult xGPushRegisterResult) {
        if (context != null && xGPushRegisterResult != null) {
            String str = "";
            if (i == 0) {
                xGPushRegisterResult + "注册成功";
                xGPushRegisterResult.getToken();
                return;
            }
            xGPushRegisterResult + "注册失败，错误码：" + i;
        }
    }

    public void onTextMessage(Context context, XGPushTextMessage xGPushTextMessage) {
        String str = "收到消息:" + xGPushTextMessage.toString();
        String customContent = xGPushTextMessage.getCustomContent();
        System.out.println("customContent=>" + customContent);
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
