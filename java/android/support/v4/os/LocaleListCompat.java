package android.support.v4.os;

import android.os.Build.VERSION;
import android.os.LocaleList;
import com.tencent.mid.sotrage.StorageInterface;
import java.util.Locale;

public final class LocaleListCompat {
    static final LocaleListInterface IMPL;
    private static final LocaleListCompat sEmptyLocaleList = new LocaleListCompat();

    static class LocaleListCompatApi24Impl implements LocaleListInterface {
        private LocaleList mLocaleList = new LocaleList(new Locale[0]);

        LocaleListCompatApi24Impl() {
        }

        public void setLocaleList(Locale... localeArr) {
            this.mLocaleList = new LocaleList(localeArr);
        }

        public Object getLocaleList() {
            return this.mLocaleList;
        }

        public Locale get(int i) {
            return this.mLocaleList.get(i);
        }

        public boolean isEmpty() {
            return this.mLocaleList.isEmpty();
        }

        public int size() {
            return this.mLocaleList.size();
        }

        public int indexOf(Locale locale) {
            return this.mLocaleList.indexOf(locale);
        }

        public boolean equals(Object obj) {
            return this.mLocaleList.equals(((LocaleListCompat) obj).unwrap());
        }

        public int hashCode() {
            return this.mLocaleList.hashCode();
        }

        public String toString() {
            return this.mLocaleList.toString();
        }

        public String toLanguageTags() {
            return this.mLocaleList.toLanguageTags();
        }

        public Locale getFirstMatch(String[] strArr) {
            if (this.mLocaleList != null) {
                return this.mLocaleList.getFirstMatch(strArr);
            }
            return null;
        }
    }

    static class LocaleListCompatBaseImpl implements LocaleListInterface {
        private LocaleListHelper mLocaleList = new LocaleListHelper(new Locale[0]);

        LocaleListCompatBaseImpl() {
        }

        public void setLocaleList(Locale... localeArr) {
            this.mLocaleList = new LocaleListHelper(localeArr);
        }

        public Object getLocaleList() {
            return this.mLocaleList;
        }

        public Locale get(int i) {
            return this.mLocaleList.get(i);
        }

        public boolean isEmpty() {
            return this.mLocaleList.isEmpty();
        }

        public int size() {
            return this.mLocaleList.size();
        }

        public int indexOf(Locale locale) {
            return this.mLocaleList.indexOf(locale);
        }

        public boolean equals(Object obj) {
            return this.mLocaleList.equals(((LocaleListCompat) obj).unwrap());
        }

        public int hashCode() {
            return this.mLocaleList.hashCode();
        }

        public String toString() {
            return this.mLocaleList.toString();
        }

        public String toLanguageTags() {
            return this.mLocaleList.toLanguageTags();
        }

        public Locale getFirstMatch(String[] strArr) {
            if (this.mLocaleList != null) {
                return this.mLocaleList.getFirstMatch(strArr);
            }
            return null;
        }
    }

    static {
        if (VERSION.SDK_INT >= 24) {
            IMPL = new LocaleListCompatApi24Impl();
        } else {
            IMPL = new LocaleListCompatBaseImpl();
        }
    }

    private LocaleListCompat() {
    }

    public static LocaleListCompat wrap(Object obj) {
        LocaleListCompat localeListCompat = new LocaleListCompat();
        if (obj instanceof LocaleList) {
            localeListCompat.setLocaleList((LocaleList) obj);
        }
        return localeListCompat;
    }

    public Object unwrap() {
        return IMPL.getLocaleList();
    }

    public static LocaleListCompat create(Locale... localeArr) {
        LocaleListCompat localeListCompat = new LocaleListCompat();
        localeListCompat.setLocaleListArray(localeArr);
        return localeListCompat;
    }

    public Locale get(int i) {
        return IMPL.get(i);
    }

    public boolean isEmpty() {
        return IMPL.isEmpty();
    }

    public int size() {
        return IMPL.size();
    }

    public int indexOf(Locale locale) {
        return IMPL.indexOf(locale);
    }

    public String toLanguageTags() {
        return IMPL.toLanguageTags();
    }

    public Locale getFirstMatch(String[] strArr) {
        return IMPL.getFirstMatch(strArr);
    }

    public static LocaleListCompat getEmptyLocaleList() {
        return sEmptyLocaleList;
    }

    public static LocaleListCompat forLanguageTags(String str) {
        Locale forLanguageTag;
        if (str == null || str.isEmpty()) {
            return getEmptyLocaleList();
        }
        String[] split = str.split(StorageInterface.KEY_SPLITER, -1);
        Locale[] localeArr = new Locale[split.length];
        for (int i = 0; i < localeArr.length; i++) {
            if (VERSION.SDK_INT >= 21) {
                forLanguageTag = Locale.forLanguageTag(split[i]);
            } else {
                forLanguageTag = LocaleHelper.forLanguageTag(split[i]);
            }
            localeArr[i] = forLanguageTag;
        }
        LocaleListCompat localeListCompat = new LocaleListCompat();
        localeListCompat.setLocaleListArray(localeArr);
        return localeListCompat;
    }

    public static LocaleListCompat getAdjustedDefault() {
        if (VERSION.SDK_INT >= 24) {
            return wrap(LocaleList.getAdjustedDefault());
        }
        return create(Locale.getDefault());
    }

    public static LocaleListCompat getDefault() {
        if (VERSION.SDK_INT >= 24) {
            return wrap(LocaleList.getDefault());
        }
        return create(Locale.getDefault());
    }

    public boolean equals(Object obj) {
        return IMPL.equals(obj);
    }

    public int hashCode() {
        return IMPL.hashCode();
    }

    public String toString() {
        return IMPL.toString();
    }

    private void setLocaleList(LocaleList localeList) {
        int size = localeList.size();
        if (size > 0) {
            Locale[] localeArr = new Locale[size];
            for (int i = 0; i < size; i++) {
                localeArr[i] = localeList.get(i);
            }
            IMPL.setLocaleList(localeArr);
        }
    }

    private void setLocaleListArray(Locale... localeArr) {
        IMPL.setLocaleList(localeArr);
    }
}
