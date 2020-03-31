package com.sensetime.senseid.sdk.liveness.interactive.common.util;

import android.content.Context;
import android.support.annotation.Keep;
import com.stub.StubApp;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

@Keep
public final class FileUtil {
    private FileUtil() {
    }

    public static void copyAssetsToFile(Context context, String str, String str2) {
        try {
            File file = new File(str2);
            if (!file.exists()) {
                File parentFile = file.getParentFile();
                if (parentFile != null && !parentFile.exists() && !parentFile.mkdirs()) {
                    return;
                }
            } else if (!file.delete()) {
                return;
            }
            InputStream open = StubApp.getOrigApplicationContext(context.getApplicationContext()).getAssets().open(str);
            FileOutputStream fileOutputStream = new FileOutputStream(str2);
            byte[] bArr = new byte[1024];
            for (int read = open.read(bArr); read > 0; read = open.read(bArr)) {
                fileOutputStream.write(bArr, 0, read);
            }
            open.close();
            fileOutputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void copyFile(File file, File file2) {
        if (file != null && file.exists() && !file.isDirectory() && file2 != null) {
            try {
                if (!file2.exists() || (!file2.isDirectory() && file2.delete())) {
                    FileInputStream fileInputStream = new FileInputStream(file);
                    FileOutputStream fileOutputStream = new FileOutputStream(file2);
                    byte[] bArr = new byte[1024];
                    for (int read = fileInputStream.read(bArr); read > 0; read = fileInputStream.read(bArr)) {
                        fileOutputStream.write(bArr, 0, read);
                    }
                    fileInputStream.close();
                    fileOutputStream.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static boolean deleteResultDir(String str) {
        File file = new File(str);
        if (!file.exists()) {
            return false;
        }
        if (file.isDirectory()) {
            File[] listFiles = file.listFiles();
            if (listFiles == null || listFiles.length <= 0) {
                return file.delete();
            }
            for (File absolutePath : listFiles) {
                if (!deleteResultDir(absolutePath.getAbsolutePath())) {
                    return false;
                }
            }
        }
        return file.delete();
    }

    public static String getStringFromFile(String str) {
        try {
            FileInputStream fileInputStream = new FileInputStream(new File(str));
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(fileInputStream));
            StringBuilder sb = new StringBuilder();
            for (String readLine = bufferedReader.readLine(); readLine != null; readLine = bufferedReader.readLine()) {
                sb.append(readLine).append("\n");
            }
            bufferedReader.close();
            fileInputStream.close();
            return sb.toString();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static void saveDataToFile(byte[] bArr, String str) {
        if (bArr != null && bArr.length > 0) {
            try {
                File file = new File(str);
                if (file.exists()) {
                    file.delete();
                    file.createNewFile();
                } else if (file.getParentFile().exists()) {
                    file.createNewFile();
                } else if (file.getParentFile().mkdirs()) {
                    file.createNewFile();
                }
                FileOutputStream fileOutputStream = new FileOutputStream(str);
                fileOutputStream.write(bArr);
                fileOutputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
