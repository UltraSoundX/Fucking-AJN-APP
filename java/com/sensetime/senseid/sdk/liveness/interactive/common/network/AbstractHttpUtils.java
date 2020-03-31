package com.sensetime.senseid.sdk.liveness.interactive.common.network;

import android.net.Uri.Builder;
import android.os.AsyncTask;
import android.os.Build.VERSION;
import com.sensetime.senseid.sdk.liveness.interactive.common.type.AbstractContentType;
import com.sensetime.senseid.sdk.liveness.interactive.common.type.ResultCode;
import com.sensetime.senseid.sdk.liveness.interactive.common.util.StringUtil;
import com.tencent.smtt.sdk.TbsListener.ErrorCode;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.SocketTimeoutException;
import java.net.URL;
import java.util.UUID;

public abstract class AbstractHttpUtils {
    private a a = null;

    class a extends AsyncTask<String, Void, HttpResult> {
        private String b;
        private String c;
        private String d;
        private String e;
        private String f;
        private AbstractContentType g;
        private String[] h;
        private HttpListener i;

        public a(HttpListener httpListener, String str, String str2, String str3, String str4, String str5, AbstractContentType abstractContentType, String... strArr) {
            this.b = str;
            this.c = str2;
            this.d = str3;
            this.e = str4;
            this.f = str5;
            this.g = abstractContentType;
            this.h = strArr;
            this.i = httpListener;
        }

        private HttpResult a() {
            ResultCode resultCode;
            try {
                Builder builder = new Builder();
                if (AbstractHttpUtils.this.isHttps()) {
                    builder.scheme("https");
                } else {
                    builder.scheme("http");
                }
                builder.encodedAuthority(AbstractHttpUtils.this.getHost());
                for (String appendPath : this.h) {
                    builder.appendPath(appendPath);
                }
                HttpURLConnection a2 = AbstractHttpUtils.this.a(new URL(builder.build().toString()), this.d, this.e, this.b, this.c, this.f);
                if (a2 == null) {
                    return new HttpResult(ResultCode.STID_E_SERVER_ACCESS, this.g);
                }
                if (isCancelled()) {
                    a2.disconnect();
                    return new HttpResult(ResultCode.STID_E_SERVER_ACCESS, this.g);
                }
                OutputStream outputStream = a2.getOutputStream();
                outputStream.write(this.f.getBytes());
                outputStream.flush();
                outputStream.close();
                if (isCancelled()) {
                    a2.disconnect();
                    return new HttpResult(ResultCode.STID_E_SERVER_ACCESS, this.g);
                }
                int responseCode = a2.getResponseCode();
                InputStream errorStream = responseCode == 200 ? a2.getInputStream() : a2.getErrorStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(errorStream));
                StringBuilder sb = new StringBuilder();
                for (String readLine = bufferedReader.readLine(); readLine != null && this.i != null; readLine = bufferedReader.readLine()) {
                    if (isCancelled()) {
                        a2.disconnect();
                        return new HttpResult(ResultCode.STID_E_SERVER_ACCESS, this.g);
                    }
                    sb.append(readLine).append("\n");
                }
                errorStream.close();
                String sb2 = sb.toString();
                switch (responseCode) {
                    case 200:
                        resultCode = ResultCode.OK;
                        break;
                    case ErrorCode.INFO_CODE_BASE /*400*/:
                        resultCode = ResultCode.STID_E_SERVER_DETECT_FAIL;
                        break;
                    case ErrorCode.INFO_FORCE_SYSTEM_WEBVIEW_INNER /*401*/:
                        resultCode = ResultCode.STID_E_API_KEY_INVALID;
                        break;
                    case ErrorCode.INFO_CAN_NOT_DISABLED_BY_CRASH /*408*/:
                        resultCode = ResultCode.STID_E_SERVER_TIMEOUT;
                        break;
                    default:
                        resultCode = ResultCode.STID_E_SERVER_ACCESS;
                        break;
                }
                a2.disconnect();
                return new HttpResult(resultCode, sb2, a2.getHeaderFields(), this.g, responseCode);
            } catch (SocketTimeoutException e2) {
                e2.printStackTrace();
                return new HttpResult(ResultCode.STID_E_SERVER_TIMEOUT, this.g);
            } catch (IOException e3) {
                e = e3;
                e.printStackTrace();
                return new HttpResult(ResultCode.STID_E_SERVER_ACCESS, this.g);
            } catch (SecurityException e4) {
                e = e4;
                e.printStackTrace();
                return new HttpResult(ResultCode.STID_E_SERVER_ACCESS, this.g);
            } catch (NullPointerException e5) {
                e = e5;
                e.printStackTrace();
                return new HttpResult(ResultCode.STID_E_SERVER_ACCESS, this.g);
            }
        }

        /* access modifiers changed from: protected */
        public final /* synthetic */ Object doInBackground(Object[] objArr) {
            return a();
        }

        /* access modifiers changed from: protected */
        public final void onCancelled() {
            this.b = null;
            this.c = null;
            this.d = null;
            this.e = null;
            this.f = null;
            this.g = null;
            this.h = null;
            this.i = null;
            super.onCancelled();
        }

        /* access modifiers changed from: protected */
        public final /* synthetic */ void onPostExecute(Object obj) {
            HttpResult httpResult = (HttpResult) obj;
            if (this.i != null) {
                this.i.onFinished(httpResult);
            }
        }
    }

    /* access modifiers changed from: private */
    public HttpURLConnection a(URL url, String str, String str2, String str3, String str4, String str5) {
        long currentTimeMillis = System.currentTimeMillis();
        String replace = UUID.randomUUID().toString().replace("-", "");
        String signature = getSignature(String.valueOf(currentTimeMillis), StringUtil.md5(str5), replace, str3, str4);
        try {
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            httpURLConnection.setUseCaches(false);
            httpURLConnection.setRequestProperty("Content-Type", "application/json");
            httpURLConnection.setRequestProperty("Authorization", "Basic");
            httpURLConnection.setRequestProperty("X-SenseTime-Api-Key", str3);
            httpURLConnection.setRequestProperty("X-SenseTime-Timestamp", String.valueOf(currentTimeMillis));
            httpURLConnection.setRequestProperty("X-SenseTime-Nonce", replace);
            httpURLConnection.setRequestProperty("X-SenseTime-Signature", signature);
            httpURLConnection.setRequestProperty("X-SenseTime-Client-Source", getBundleId());
            httpURLConnection.setRequestProperty("X-SenseTime-Sdkversion", str2);
            httpURLConnection.setRequestProperty("X-SenseTime-Sysversion", VERSION.RELEASE);
            httpURLConnection.setRequestProperty("X-SenseTime-Network", str);
            httpURLConnection.setRequestMethod("POST");
            httpURLConnection.setConnectTimeout(15000);
            httpURLConnection.setReadTimeout(15000);
            httpURLConnection.setDoOutput(true);
            httpURLConnection.setDoInput(true);
            return httpURLConnection;
        } catch (IOException e) {
            cancel();
            e.printStackTrace();
            return null;
        }
    }

    public void cancel() {
        if (this.a != null) {
            this.a.cancel(true);
            this.a = null;
        }
    }

    /* access modifiers changed from: protected */
    public abstract String getBundleId();

    /* access modifiers changed from: protected */
    public abstract String getHost();

    /* access modifiers changed from: protected */
    public abstract String getSignature(String str, String str2, String str3, String str4, String str5);

    /* access modifiers changed from: protected */
    public boolean isHttps() {
        return true;
    }

    public void sendRequestAsync(String str, String str2, String str3, String str4, String str5, AbstractContentType abstractContentType, String... strArr) {
        new a(null, str, str2, str3, str4, str5, abstractContentType, strArr).execute(new String[0]);
    }

    public void sendRequestSync(HttpListener httpListener, String str, String str2, String str3, String str4, String str5, AbstractContentType abstractContentType, String... strArr) {
        this.a = new a(httpListener, str, str2, str3, str4, str5, abstractContentType, strArr);
        this.a.execute(new String[0]);
    }
}
