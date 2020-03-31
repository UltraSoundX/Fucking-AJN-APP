package android.support.v4.os;

import android.os.Build.VERSION;
import com.tencent.mid.sotrage.StorageInterface;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.Locale;

final class LocaleListHelper {
    private static final Locale EN_LATN = LocaleHelper.forLanguageTag("en-Latn");
    private static final Locale LOCALE_AR_XB = new Locale("ar", "XB");
    private static final Locale LOCALE_EN_XA = new Locale("en", "XA");
    private static final int NUM_PSEUDO_LOCALES = 2;
    private static final String STRING_AR_XB = "ar-XB";
    private static final String STRING_EN_XA = "en-XA";
    private static LocaleListHelper sDefaultAdjustedLocaleList = null;
    private static LocaleListHelper sDefaultLocaleList = null;
    private static final Locale[] sEmptyList = new Locale[0];
    private static final LocaleListHelper sEmptyLocaleList = new LocaleListHelper(new Locale[0]);
    private static Locale sLastDefaultLocale = null;
    private static LocaleListHelper sLastExplicitlySetLocaleList = null;
    private static final Object sLock = new Object();
    private final Locale[] mList;
    private final String mStringRepresentation;

    /* access modifiers changed from: 0000 */
    public Locale get(int i) {
        if (i < 0 || i >= this.mList.length) {
            return null;
        }
        return this.mList[i];
    }

    /* access modifiers changed from: 0000 */
    public boolean isEmpty() {
        return this.mList.length == 0;
    }

    /* access modifiers changed from: 0000 */
    public int size() {
        return this.mList.length;
    }

    /* access modifiers changed from: 0000 */
    public int indexOf(Locale locale) {
        for (int i = 0; i < this.mList.length; i++) {
            if (this.mList[i].equals(locale)) {
                return i;
            }
        }
        return -1;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof LocaleListHelper)) {
            return false;
        }
        Locale[] localeArr = ((LocaleListHelper) obj).mList;
        if (this.mList.length != localeArr.length) {
            return false;
        }
        for (int i = 0; i < this.mList.length; i++) {
            if (!this.mList[i].equals(localeArr[i])) {
                return false;
            }
        }
        return true;
    }

    public int hashCode() {
        int i = 1;
        for (Locale hashCode : this.mList) {
            i = (i * 31) + hashCode.hashCode();
        }
        return i;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        for (int i = 0; i < this.mList.length; i++) {
            sb.append(this.mList[i]);
            if (i < this.mList.length - 1) {
                sb.append(',');
            }
        }
        sb.append("]");
        return sb.toString();
    }

    /* access modifiers changed from: 0000 */
    public String toLanguageTags() {
        return this.mStringRepresentation;
    }

    LocaleListHelper(Locale... localeArr) {
        if (localeArr.length == 0) {
            this.mList = sEmptyList;
            this.mStringRepresentation = "";
            return;
        }
        Locale[] localeArr2 = new Locale[localeArr.length];
        HashSet hashSet = new HashSet();
        StringBuilder sb = new StringBuilder();
        int i = 0;
        while (true) {
            int i2 = i;
            if (i2 < localeArr.length) {
                Locale locale = localeArr[i2];
                if (locale == null) {
                    throw new NullPointerException("list[" + i2 + "] is null");
                } else if (hashSet.contains(locale)) {
                    throw new IllegalArgumentException("list[" + i2 + "] is a repetition");
                } else {
                    Locale locale2 = (Locale) locale.clone();
                    localeArr2[i2] = locale2;
                    sb.append(LocaleHelper.toLanguageTag(locale2));
                    if (i2 < localeArr.length - 1) {
                        sb.append(',');
                    }
                    hashSet.add(locale2);
                    i = i2 + 1;
                }
            } else {
                this.mList = localeArr2;
                this.mStringRepresentation = sb.toString();
                return;
            }
        }
    }

    LocaleListHelper(Locale locale, LocaleListHelper localeListHelper) {
        int i;
        int i2;
        if (locale == null) {
            throw new NullPointerException("topLocale is null");
        }
        int length = localeListHelper == null ? 0 : localeListHelper.mList.length;
        int i3 = 0;
        while (true) {
            if (i3 >= length) {
                i = -1;
                break;
            } else if (locale.equals(localeListHelper.mList[i3])) {
                i = i3;
                break;
            } else {
                i3++;
            }
        }
        if (i == -1) {
            i2 = 1;
        } else {
            i2 = 0;
        }
        int i4 = length + i2;
        Locale[] localeArr = new Locale[i4];
        localeArr[0] = (Locale) locale.clone();
        if (i == -1) {
            for (int i5 = 0; i5 < length; i5++) {
                localeArr[i5 + 1] = (Locale) localeListHelper.mList[i5].clone();
            }
        } else {
            for (int i6 = 0; i6 < i; i6++) {
                localeArr[i6 + 1] = (Locale) localeListHelper.mList[i6].clone();
            }
            for (int i7 = i + 1; i7 < length; i7++) {
                localeArr[i7] = (Locale) localeListHelper.mList[i7].clone();
            }
        }
        StringBuilder sb = new StringBuilder();
        for (int i8 = 0; i8 < i4; i8++) {
            sb.append(LocaleHelper.toLanguageTag(localeArr[i8]));
            if (i8 < i4 - 1) {
                sb.append(',');
            }
        }
        this.mList = localeArr;
        this.mStringRepresentation = sb.toString();
    }

    static LocaleListHelper getEmptyLocaleList() {
        return sEmptyLocaleList;
    }

    static LocaleListHelper forLanguageTags(String str) {
        if (str == null || str.isEmpty()) {
            return getEmptyLocaleList();
        }
        String[] split = str.split(StorageInterface.KEY_SPLITER, -1);
        Locale[] localeArr = new Locale[split.length];
        for (int i = 0; i < localeArr.length; i++) {
            localeArr[i] = LocaleHelper.forLanguageTag(split[i]);
        }
        return new LocaleListHelper(localeArr);
    }

    private static String getLikelyScript(Locale locale) {
        if (VERSION.SDK_INT < 21) {
            return "";
        }
        String script = locale.getScript();
        if (!script.isEmpty()) {
            return script;
        }
        return "";
    }

    private static boolean isPseudoLocale(String str) {
        return STRING_EN_XA.equals(str) || STRING_AR_XB.equals(str);
    }

    private static boolean isPseudoLocale(Locale locale) {
        return LOCALE_EN_XA.equals(locale) || LOCALE_AR_XB.equals(locale);
    }

    private static int matchScore(Locale locale, Locale locale2) {
        int i = 1;
        if (locale.equals(locale2)) {
            return 1;
        }
        if (!locale.getLanguage().equals(locale2.getLanguage()) || isPseudoLocale(locale) || isPseudoLocale(locale2)) {
            return 0;
        }
        String likelyScript = getLikelyScript(locale);
        if (likelyScript.isEmpty()) {
            String country = locale.getCountry();
            if (country.isEmpty() || country.equals(locale2.getCountry())) {
                return 1;
            }
            return 0;
        }
        if (!likelyScript.equals(getLikelyScript(locale2))) {
            i = 0;
        }
        return i;
    }

    private int findFirstMatchIndex(Locale locale) {
        for (int i = 0; i < this.mList.length; i++) {
            if (matchScore(locale, this.mList[i]) > 0) {
                return i;
            }
        }
        return Integer.MAX_VALUE;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:10:0x001f, code lost:
        if (r0 < Integer.MAX_VALUE) goto L_0x0021;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private int computeFirstMatchIndex(java.util.Collection<java.lang.String> r6, boolean r7) {
        /*
            r5 = this;
            r1 = 2147483647(0x7fffffff, float:NaN)
            r3 = 0
            java.util.Locale[] r0 = r5.mList
            int r0 = r0.length
            r2 = 1
            if (r0 != r2) goto L_0x000c
            r2 = r3
        L_0x000b:
            return r2
        L_0x000c:
            java.util.Locale[] r0 = r5.mList
            int r0 = r0.length
            if (r0 != 0) goto L_0x0013
            r2 = -1
            goto L_0x000b
        L_0x0013:
            if (r7 == 0) goto L_0x0048
            java.util.Locale r0 = EN_LATN
            int r0 = r5.findFirstMatchIndex(r0)
            if (r0 != 0) goto L_0x001f
            r2 = r3
            goto L_0x000b
        L_0x001f:
            if (r0 >= r1) goto L_0x0048
        L_0x0021:
            java.util.Iterator r4 = r6.iterator()
            r2 = r0
        L_0x0026:
            boolean r0 = r4.hasNext()
            if (r0 == 0) goto L_0x0042
            java.lang.Object r0 = r4.next()
            java.lang.String r0 = (java.lang.String) r0
            java.util.Locale r0 = android.support.v4.os.LocaleHelper.forLanguageTag(r0)
            int r0 = r5.findFirstMatchIndex(r0)
            if (r0 != 0) goto L_0x003e
            r2 = r3
            goto L_0x000b
        L_0x003e:
            if (r0 >= r2) goto L_0x0046
        L_0x0040:
            r2 = r0
            goto L_0x0026
        L_0x0042:
            if (r2 != r1) goto L_0x000b
            r2 = r3
            goto L_0x000b
        L_0x0046:
            r0 = r2
            goto L_0x0040
        L_0x0048:
            r0 = r1
            goto L_0x0021
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.v4.os.LocaleListHelper.computeFirstMatchIndex(java.util.Collection, boolean):int");
    }

    private Locale computeFirstMatch(Collection<String> collection, boolean z) {
        int computeFirstMatchIndex = computeFirstMatchIndex(collection, z);
        if (computeFirstMatchIndex == -1) {
            return null;
        }
        return this.mList[computeFirstMatchIndex];
    }

    /* access modifiers changed from: 0000 */
    public Locale getFirstMatch(String[] strArr) {
        return computeFirstMatch(Arrays.asList(strArr), false);
    }

    /* access modifiers changed from: 0000 */
    public int getFirstMatchIndex(String[] strArr) {
        return computeFirstMatchIndex(Arrays.asList(strArr), false);
    }

    /* access modifiers changed from: 0000 */
    public Locale getFirstMatchWithEnglishSupported(String[] strArr) {
        return computeFirstMatch(Arrays.asList(strArr), true);
    }

    /* access modifiers changed from: 0000 */
    public int getFirstMatchIndexWithEnglishSupported(Collection<String> collection) {
        return computeFirstMatchIndex(collection, true);
    }

    /* access modifiers changed from: 0000 */
    public int getFirstMatchIndexWithEnglishSupported(String[] strArr) {
        return getFirstMatchIndexWithEnglishSupported((Collection<String>) Arrays.asList(strArr));
    }

    static boolean isPseudoLocalesOnly(String[] strArr) {
        if (strArr == null) {
            return true;
        }
        if (strArr.length > 3) {
            return false;
        }
        for (String str : strArr) {
            if (!str.isEmpty() && !isPseudoLocale(str)) {
                return false;
            }
        }
        return true;
    }

    static LocaleListHelper getDefault() {
        LocaleListHelper localeListHelper;
        Locale locale = Locale.getDefault();
        synchronized (sLock) {
            if (!locale.equals(sLastDefaultLocale)) {
                sLastDefaultLocale = locale;
                if (sDefaultLocaleList == null || !locale.equals(sDefaultLocaleList.get(0))) {
                    sDefaultLocaleList = new LocaleListHelper(locale, sLastExplicitlySetLocaleList);
                    sDefaultAdjustedLocaleList = sDefaultLocaleList;
                } else {
                    localeListHelper = sDefaultLocaleList;
                }
            }
            localeListHelper = sDefaultLocaleList;
        }
        return localeListHelper;
    }

    static LocaleListHelper getAdjustedDefault() {
        LocaleListHelper localeListHelper;
        getDefault();
        synchronized (sLock) {
            localeListHelper = sDefaultAdjustedLocaleList;
        }
        return localeListHelper;
    }

    static void setDefault(LocaleListHelper localeListHelper) {
        setDefault(localeListHelper, 0);
    }

    static void setDefault(LocaleListHelper localeListHelper, int i) {
        if (localeListHelper == null) {
            throw new NullPointerException("locales is null");
        } else if (localeListHelper.isEmpty()) {
            throw new IllegalArgumentException("locales is empty");
        } else {
            synchronized (sLock) {
                sLastDefaultLocale = localeListHelper.get(i);
                Locale.setDefault(sLastDefaultLocale);
                sLastExplicitlySetLocaleList = localeListHelper;
                sDefaultLocaleList = localeListHelper;
                if (i == 0) {
                    sDefaultAdjustedLocaleList = sDefaultLocaleList;
                } else {
                    sDefaultAdjustedLocaleList = new LocaleListHelper(sLastDefaultLocale, sDefaultLocaleList);
                }
            }
        }
    }
}
