package com.mob.tools.utils;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Handler.Callback;
import android.os.Message;
import android.text.TextUtils;
import android.util.Base64;
import com.mob.tools.MobHandlerThread;
import com.mob.tools.MobLog;
import com.stub.StubApp;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStreamWriter;
import java.util.HashMap;

public class SharePrefrenceHelper {
    private Context context;
    private MobSharePreference prefrence;

    private static final class MobSharePreference {
        private static Handler handler = MobHandlerThread.newHandler("s", (Callback) new Callback() {
            public boolean handleMessage(Message message) {
                OnCommitListener onCommitListener = null;
                try {
                    onCommitListener = (OnCommitListener) message.obj;
                } catch (Throwable th) {
                }
                try {
                    Bundle data = message.getData();
                    String string = data.getString("json");
                    OutputStreamWriter outputStreamWriter = new OutputStreamWriter(new FileOutputStream(data.getString("file")), "utf-8");
                    outputStreamWriter.append(string);
                    outputStreamWriter.flush();
                    outputStreamWriter.close();
                    if (onCommitListener != null) {
                        onCommitListener.onCommit(null);
                    }
                } catch (Throwable th2) {
                    MobLog.getInstance().w(th2);
                    if (onCommitListener != null) {
                        onCommitListener.onCommit(th2);
                    }
                }
                return false;
            }
        });
        private Hashon hashon;
        private OnCommitListener listener;
        private File spFile;
        private HashMap<String, Object> spMap;

        public MobSharePreference(Context context, String str) {
            this.spFile = new File(new File(context.getFilesDir(), "Mob"), str);
            if (!this.spFile.getParentFile().exists()) {
                this.spFile.getParentFile().mkdirs();
            }
            this.spMap = new HashMap<>();
            this.hashon = new Hashon();
            open();
        }

        private void open() {
            synchronized (this.spMap) {
                if (this.spFile.exists()) {
                    try {
                        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(this.spFile), "utf-8"));
                        StringBuilder sb = new StringBuilder();
                        for (String readLine = bufferedReader.readLine(); readLine != null; readLine = bufferedReader.readLine()) {
                            if (sb.length() > 0) {
                                sb.append("\n");
                            }
                            sb.append(readLine);
                        }
                        bufferedReader.close();
                        this.spMap = this.hashon.fromJson(sb.toString());
                    } catch (Throwable th) {
                        MobLog.getInstance().w(th);
                    }
                }
            }
        }

        private Object get(String str) {
            Object obj;
            synchronized (this.spMap) {
                obj = this.spMap.get(str);
            }
            return obj;
        }

        private void put(String str, Object obj) {
            synchronized (this.spMap) {
                this.spMap.put(str, obj);
                if (handler != null) {
                    Message message = new Message();
                    Bundle bundle = new Bundle();
                    bundle.putString("json", this.hashon.fromHashMap(this.spMap));
                    bundle.putString("file", this.spFile.getAbsolutePath());
                    message.setData(bundle);
                    message.what = 1;
                    message.obj = this.listener;
                    handler.sendMessage(message);
                }
            }
        }

        public byte getByte(String str, byte b) {
            Object obj = get(str);
            if (obj != null) {
                return ((Number) obj).byteValue();
            }
            return b;
        }

        public void putByte(String str, byte b) {
            put(str, Byte.valueOf(b));
        }

        public short getShort(String str, short s) {
            Object obj = get(str);
            if (obj != null) {
                return ((Number) obj).shortValue();
            }
            return s;
        }

        public void putShort(String str, short s) {
            put(str, Short.valueOf(s));
        }

        public int getInt(String str, int i) {
            Object obj = get(str);
            if (obj != null) {
                return ((Number) obj).intValue();
            }
            return i;
        }

        public void putInt(String str, int i) {
            put(str, Integer.valueOf(i));
        }

        public long getLong(String str, long j) {
            Object obj = get(str);
            if (obj != null) {
                return ((Number) obj).longValue();
            }
            return j;
        }

        public void putLong(String str, long j) {
            put(str, Long.valueOf(j));
        }

        public float getFloat(String str, float f) {
            Object obj = get(str);
            if (obj != null) {
                return ((Number) obj).floatValue();
            }
            return f;
        }

        public void putFloat(String str, float f) {
            put(str, Float.valueOf(f));
        }

        public double getDouble(String str, double d) {
            Object obj = get(str);
            if (obj != null) {
                return ((Number) obj).doubleValue();
            }
            return d;
        }

        public void putDouble(String str, double d) {
            put(str, Double.valueOf(d));
        }

        public char getChar(String str, char c) {
            Object obj = get(str);
            if (obj != null) {
                return ((String) obj).charAt(0);
            }
            return c;
        }

        public void putChar(String str, char c) {
            putString(str, String.valueOf(c));
        }

        public boolean getBoolean(String str, boolean z) {
            Object obj = get(str);
            if (obj != null) {
                return ((Number) obj).byteValue() == 1;
            }
            return z;
        }

        public void putBoolean(String str, boolean z) {
            putByte(str, (byte) (z ? 1 : 0));
        }

        public String getString(String str, String str2) {
            Object obj = get(str);
            if (obj != null) {
                return (String) obj;
            }
            return str2;
        }

        public void putString(String str, String str2) {
            put(str, str2);
        }

        public HashMap<String, Object> getAll() {
            HashMap<String, Object> hashMap;
            synchronized (this.spMap) {
                hashMap = new HashMap<>();
                hashMap.putAll(this.spMap);
            }
            return hashMap;
        }

        public void putAll(HashMap<String, Object> hashMap) {
            synchronized (this.spMap) {
                this.spMap.putAll(hashMap);
            }
            if (handler != null) {
                Message message = new Message();
                Bundle bundle = new Bundle();
                bundle.putString("json", this.hashon.fromHashMap(this.spMap));
                bundle.putString("file", this.spFile.getAbsolutePath());
                message.setData(bundle);
                message.what = 1;
                message.obj = this.listener;
                handler.sendMessage(message);
            }
        }

        public void remove(String str) {
            put(str, null);
        }

        public void clear() {
            synchronized (this.spMap) {
                this.spMap.clear();
            }
            if (handler != null) {
                Message message = new Message();
                Bundle bundle = new Bundle();
                bundle.putString("json", this.hashon.fromHashMap(this.spMap));
                bundle.putString("file", this.spFile.getAbsolutePath());
                message.setData(bundle);
                message.what = 1;
                message.obj = this.listener;
                handler.sendMessage(message);
            }
        }

        public void setOnCommitListener(OnCommitListener onCommitListener) {
            this.listener = onCommitListener;
        }
    }

    public interface OnCommitListener {
        void onCommit(Throwable th);
    }

    public SharePrefrenceHelper(Context context2) {
        this.context = StubApp.getOrigApplicationContext(context2.getApplicationContext());
    }

    public void open(String str) {
        open(str, 0);
    }

    public void open(String str, int i) {
        this.prefrence = new MobSharePreference(this.context, str + "_" + i);
    }

    public void putString(String str, String str2) {
        this.prefrence.putString(str, str2);
    }

    public String getString(String str) {
        return this.prefrence.getString(str, "");
    }

    public void putBoolean(String str, Boolean bool) {
        this.prefrence.putBoolean(str, bool.booleanValue());
    }

    public boolean getBoolean(String str) {
        return this.prefrence.getBoolean(str, false);
    }

    public void putLong(String str, Long l) {
        this.prefrence.putLong(str, l.longValue());
    }

    public long getLong(String str) {
        return this.prefrence.getLong(str, 0);
    }

    public void putInt(String str, Integer num) {
        this.prefrence.putInt(str, num.intValue());
    }

    public int getInt(String str) {
        return this.prefrence.getInt(str, 0);
    }

    public void putFloat(String str, Float f) {
        this.prefrence.putFloat(str, f.floatValue());
    }

    public float getFloat(String str) {
        return this.prefrence.getFloat(str, 0.0f);
    }

    public void put(String str, Object obj) {
        if (obj != null) {
            try {
                ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                ObjectOutputStream objectOutputStream = new ObjectOutputStream(byteArrayOutputStream);
                objectOutputStream.writeObject(obj);
                objectOutputStream.flush();
                objectOutputStream.close();
                putString(str, Base64.encodeToString(byteArrayOutputStream.toByteArray(), 2));
            } catch (Throwable th) {
                MobLog.getInstance().w(th);
            }
        }
    }

    public Object get(String str) {
        try {
            String string = getString(str);
            if (TextUtils.isEmpty(string)) {
                return null;
            }
            ObjectInputStream objectInputStream = new ObjectInputStream(new ByteArrayInputStream(Base64.decode(string, 2)));
            Object readObject = objectInputStream.readObject();
            objectInputStream.close();
            return readObject;
        } catch (Throwable th) {
            MobLog.getInstance().w(th);
            return null;
        }
    }

    public HashMap<String, Object> getAll() {
        return this.prefrence.getAll();
    }

    public void putAll(HashMap<String, Object> hashMap) {
        this.prefrence.putAll(hashMap);
    }

    public void remove(String str) {
        this.prefrence.remove(str);
    }

    public void clear() {
        this.prefrence.clear();
    }

    public void setOnCommitListener(OnCommitListener onCommitListener) {
        if (this.prefrence != null) {
            this.prefrence.setOnCommitListener(onCommitListener);
        }
    }
}
