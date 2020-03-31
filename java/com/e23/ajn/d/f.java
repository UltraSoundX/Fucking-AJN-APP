package com.e23.ajn.d;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.text.Html;
import android.text.Html.ImageGetter;
import android.text.SpannableStringBuilder;
import com.e23.ajn.R;
import net.sf.json.util.JSONUtils;

/* compiled from: CommentTextUtil */
public class f {
    public static final String[] a = {"{:6_344:}", "{:6_339:}", "{:6_347:}", "{:6_342:}", "{:6_343:}", "{:6_333:}", "{:6_332:}", "{:6_330:}", "{:6_324:}", "{:6_345:}", "{:6_338:}", "{:6_351:}", "{:6_340:}", "{:6_335:}", "{:6_348:}", "{:6_327:}", "{:6_326:}", "{:6_341:}", "{:6_328:}", "{:6_331:}", "{:6_329:}", "{:6_325:}", "{:6_337:}", "{:6_349:}", "{:6_336:}", "{:6_350:}", "{:6_346:}", "{:6_334:}"};
    public static final int[] b = {R.mipmap.f232dingyu_01, R.mipmap.f233dingyu_1, R.mipmap.em0, R.mipmap.em17, R.mipmap.em18, R.mipmap.em19, R.mipmap.em2, R.mipmap.em20, R.mipmap.em21, R.mipmap.em22, R.mipmap.f234dingyue_header_img, R.mipmap.f235divider_horizontal_common, R.mipmap.f236editcloumok, R.mipmap.f237ee, R.mipmap.dingyu_01, R.mipmap.dingyu_1, R.mipmap.dingyue_header_img, R.mipmap.divider_horizontal_common, R.mipmap.editcloumok, R.mipmap.ee, R.mipmap.em1, R.mipmap.em10, R.mipmap.em11, R.mipmap.em12, R.mipmap.em13, R.mipmap.em14, R.mipmap.em15, R.mipmap.em16};

    public static SpannableStringBuilder a(Context context, String str) {
        return new SpannableStringBuilder(Html.fromHtml(b(c(d(str))), a(context), null));
    }

    public static ImageGetter a(final Context context) {
        return new ImageGetter() {
            public Drawable getDrawable(String str) {
                Drawable drawable = context.getResources().getDrawable(Integer.parseInt(str));
                drawable.setBounds(0, 0, drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight());
                return drawable;
            }
        };
    }

    public static String a(String str) {
        String str2 = "";
        for (int i = 0; i < str.length(); i++) {
            char charAt = str.charAt(i);
            if (Integer.toHexString(charAt).length() == 2) {
                str2 = str2 + "\\u00" + Integer.toHexString(charAt);
            } else if (Integer.toHexString(charAt).length() == 3) {
                str2 = str2 + "\\u0" + Integer.toHexString(charAt);
            } else {
                str2 = str2 + "\\u" + Integer.toHexString(charAt);
            }
        }
        return str2;
    }

    public static String b(String str) {
        for (int i = 0; i < a.length; i++) {
            String str2 = "<img src=\"" + b[i] + "\">";
            if (str.indexOf(a[i]) > -1) {
                str = str.replace(a[i], str2);
            }
        }
        return str;
    }

    public static String c(String str) {
        if (!str.equals("&amp;")) {
            str = str.replace("&amp;", "&");
        }
        if (!str.equals("&nbsp;")) {
            str = str.replace("&nbsp;", "");
        }
        if (!str.equals("&quot;")) {
            str = str.replace("&quot;", JSONUtils.DOUBLE_QUOTE);
        }
        if (!str.equals("&lt;")) {
            str = str.replace("&lt;", "<");
        }
        if (!str.equals("&gt;")) {
            str = str.replace("&gt;", ">");
        }
        if (!str.equals("&mdash;")) {
            return str.replace("&mdash;", "-");
        }
        return str;
    }

    public static String d(String str) {
        int length = str.length();
        StringBuffer stringBuffer = new StringBuffer(length);
        int i = 0;
        while (i < length) {
            int i2 = i + 1;
            char charAt = str.charAt(i);
            if (charAt == '\\') {
                int i3 = i2 + 1;
                char charAt2 = str.charAt(i2);
                if (charAt2 == 'u') {
                    int i4 = 0;
                    i = i3;
                    int i5 = 0;
                    while (i5 < 4) {
                        int i6 = i + 1;
                        char charAt3 = str.charAt(i);
                        switch (charAt3) {
                            case '0':
                            case '1':
                            case '2':
                            case '3':
                            case '4':
                            case '5':
                            case '6':
                            case '7':
                            case '8':
                            case '9':
                                i4 = ((i4 << 4) + charAt3) - 48;
                                break;
                            case 'A':
                            case 'B':
                            case 'C':
                            case 'D':
                            case 'E':
                            case 'F':
                                i4 = (((i4 << 4) + 10) + charAt3) - 65;
                                break;
                            case 'a':
                            case 'b':
                            case 'c':
                            case 'd':
                            case 'e':
                            case 'f':
                                i4 = (((i4 << 4) + 10) + charAt3) - 97;
                                break;
                            default:
                                throw new IllegalArgumentException("Malformed   \\uxxxx   encoding.");
                        }
                        i5++;
                        i = i6;
                    }
                    stringBuffer.append((char) i4);
                } else {
                    if (charAt2 == 't') {
                        charAt2 = 9;
                    } else if (charAt2 == 'r') {
                        charAt2 = 13;
                    } else if (charAt2 == 'n') {
                        charAt2 = 10;
                    } else if (charAt2 == 'f') {
                        charAt2 = 12;
                    }
                    stringBuffer.append(charAt2);
                    i = i3;
                }
            } else {
                stringBuffer.append(charAt);
                i = i2;
            }
        }
        return stringBuffer.toString();
    }
}
