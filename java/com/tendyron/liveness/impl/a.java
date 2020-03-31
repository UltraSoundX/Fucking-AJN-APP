package com.tendyron.liveness.impl;

import android.app.Activity;
import android.content.Intent;
import android.text.TextUtils;
import com.tendyron.liveness.motion.AbstractCommonMotionLivingActivity;
import com.tendyron.liveness.motion.MotionLivenessActivity;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/* compiled from: LivenessImpl */
public class a implements LivenessInterface {
    public void startLivenessActivityForResult(Activity activity, int i, int i2, boolean z, int[] iArr, int i3) {
        Intent intent = new Intent(activity, MotionLivenessActivity.class);
        intent.putExtra(AbstractCommonMotionLivingActivity.a, i2);
        intent.putExtra(AbstractCommonMotionLivingActivity.b, z);
        intent.putExtra(AbstractCommonMotionLivingActivity.c, iArr);
        intent.putExtra(AbstractCommonMotionLivingActivity.d, i3);
        activity.startActivityForResult(intent, i);
    }

    public void startLivenessActivityForResult(Activity activity, int i, int i2, boolean z, int[] iArr) {
        Intent intent = new Intent(activity, MotionLivenessActivity.class);
        intent.putExtra(AbstractCommonMotionLivingActivity.a, i2);
        intent.putExtra(AbstractCommonMotionLivingActivity.b, z);
        intent.putExtra(AbstractCommonMotionLivingActivity.c, iArr);
        intent.putExtra(AbstractCommonMotionLivingActivity.d, 1);
        activity.startActivityForResult(intent, i);
    }

    public String getLivenessErrorMessage(int i) {
        switch (i) {
            case -1:
                return "活体检测成功";
            case 0:
                return "活体检测已取消";
            case 2:
                return "权限检测失败，请检查应用权限设置";
            case 3:
                return "初始化相机失败";
            case 4:
                return "授权文件不存在";
            case 5:
                return "错误的方法状态调用";
            case 6:
                return "授权文件过期";
            case 7:
                return "绑定包名错误";
            case 8:
            case 17:
            case 18:
                return "未通过授权验证";
            case 9:
                return "检测超时，请重试一次";
            case 10:
                return "模型文件错误";
            case 11:
                return "模型文件不存在";
            case 12:
                return "API_KEY 或 API_SECRET 错误";
            case 14:
                return "服务器访问错误";
            case 15:
                return "活体检测失败，请重试一次";
            case 16:
                return "动作幅度过大，请保持人脸在屏幕中央，重试一次";
            case 19:
                return "请调整人脸姿态，去除面部遮挡，正对屏幕重试一次";
            case 20:
                return "网络请求超时,请稍后重试";
            case 21:
                return "参数设置不合法";
            default:
                return "未知错误";
        }
    }

    public List<byte[]> getLivenessResultImages(Intent intent, int i) {
        int i2 = 0;
        ArrayList arrayList = new ArrayList();
        if (intent == null) {
            return null;
        }
        String stringExtra = intent.getStringExtra(AbstractCommonMotionLivingActivity.e);
        if (TextUtils.isEmpty(stringExtra)) {
            return null;
        }
        File file = new File(stringExtra);
        if (!file.exists() || file.list() == null) {
            return null;
        }
        String[] list = file.list();
        ArrayList arrayList2 = new ArrayList();
        for (String str : list) {
            int lastIndexOf = str.lastIndexOf(46);
            if (lastIndexOf > 0 && str.substring(lastIndexOf + 1).equals("jpg")) {
                if (str.equals("first.jpg")) {
                    arrayList2.add(0, str);
                }
                if (!str.equals("first.jpg")) {
                    arrayList2.add(str);
                }
            }
        }
        while (true) {
            int i3 = i2;
            if (i3 >= arrayList2.size()) {
                return arrayList;
            }
            File file2 = new File(stringExtra + ((String) arrayList2.get(i3)));
            byte[] bArr = new byte[((int) file2.length())];
            try {
                BufferedInputStream bufferedInputStream = new BufferedInputStream(new FileInputStream(file2));
                bufferedInputStream.read(bArr, 0, bArr.length);
                bufferedInputStream.close();
                arrayList.add(bArr);
                i2 = i3 + 1;
            } catch (FileNotFoundException e) {
                e.printStackTrace();
                return null;
            } catch (IOException e2) {
                e2.printStackTrace();
                return null;
            }
        }
    }

    public int[] getSequences(int i) {
        Random random = new Random();
        ArrayList arrayList = new ArrayList();
        arrayList.add(Integer.valueOf(0));
        arrayList.add(Integer.valueOf(2));
        arrayList.add(Integer.valueOf(3));
        int[] iArr = new int[i];
        for (int i2 = 0; i2 < i; i2++) {
            int nextInt = random.nextInt(arrayList.size());
            iArr[i2] = ((Integer) arrayList.get(nextInt)).intValue();
            if (i <= 3) {
                arrayList.remove(nextInt);
            }
        }
        return iArr;
    }

    public String getVersion() {
        return LivenessInterface.VERSION;
    }
}
