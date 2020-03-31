package android.support.v4.graphics;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Typeface;
import android.support.v4.content.res.FontResourcesParserCompat.FontFamilyFilesResourceEntry;
import android.support.v4.content.res.FontResourcesParserCompat.FontFileResourceEntry;
import android.support.v4.provider.FontsContractCompat.FontInfo;
import java.io.File;
import java.io.InputStream;

class TypefaceCompatBaseImpl {
    private static final String CACHE_FILE_PREFIX = "cached_font_";
    private static final String TAG = "TypefaceCompatBaseImpl";

    private interface StyleExtractor<T> {
        int getWeight(T t);

        boolean isItalic(T t);
    }

    TypefaceCompatBaseImpl() {
    }

    private static <T> T findBestFont(T[] tArr, int i, StyleExtractor<T> styleExtractor) {
        boolean z;
        int i2;
        T t;
        int i3 = (i & 1) == 0 ? 400 : 700;
        if ((i & 2) != 0) {
            z = true;
        } else {
            z = false;
        }
        T t2 = null;
        int i4 = Integer.MAX_VALUE;
        int length = tArr.length;
        int i5 = 0;
        while (i5 < length) {
            T t3 = tArr[i5];
            int abs = Math.abs(styleExtractor.getWeight(t3) - i3) * 2;
            if (styleExtractor.isItalic(t3) == z) {
                i2 = 0;
            } else {
                i2 = 1;
            }
            int i6 = i2 + abs;
            if (t2 == null || i4 > i6) {
                i4 = i6;
                t = t3;
            } else {
                t = t2;
            }
            i5++;
            t2 = t;
        }
        return t2;
    }

    /* access modifiers changed from: protected */
    public FontInfo findBestInfo(FontInfo[] fontInfoArr, int i) {
        return (FontInfo) findBestFont(fontInfoArr, i, new StyleExtractor<FontInfo>() {
            public int getWeight(FontInfo fontInfo) {
                return fontInfo.getWeight();
            }

            public boolean isItalic(FontInfo fontInfo) {
                return fontInfo.isItalic();
            }
        });
    }

    /* access modifiers changed from: protected */
    public Typeface createFromInputStream(Context context, InputStream inputStream) {
        Typeface typeface = null;
        File tempFile = TypefaceCompatUtil.getTempFile(context);
        if (tempFile != null) {
            try {
                if (TypefaceCompatUtil.copyToFile(tempFile, inputStream)) {
                    typeface = Typeface.createFromFile(tempFile.getPath());
                    tempFile.delete();
                }
            } catch (RuntimeException e) {
            } finally {
                tempFile.delete();
            }
        }
        return typeface;
    }

    /* JADX WARNING: type inference failed for: r1v2, types: [java.io.Closeable] */
    /* JADX WARNING: type inference failed for: r1v4 */
    /* JADX WARNING: type inference failed for: r1v5, types: [java.io.Closeable] */
    /* JADX WARNING: type inference failed for: r1v7 */
    /* JADX WARNING: type inference failed for: r1v10 */
    /* JADX WARNING: type inference failed for: r1v11 */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Unknown variable types count: 2 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public android.graphics.Typeface createFromFontInfo(android.content.Context r5, android.os.CancellationSignal r6, android.support.v4.provider.FontsContractCompat.FontInfo[] r7, int r8) {
        /*
            r4 = this;
            r0 = 0
            int r1 = r7.length
            r2 = 1
            if (r1 >= r2) goto L_0x0006
        L_0x0005:
            return r0
        L_0x0006:
            android.support.v4.provider.FontsContractCompat$FontInfo r1 = r4.findBestInfo(r7, r8)
            android.content.ContentResolver r2 = r5.getContentResolver()     // Catch:{ IOException -> 0x001e, all -> 0x0024 }
            android.net.Uri r1 = r1.getUri()     // Catch:{ IOException -> 0x001e, all -> 0x0024 }
            java.io.InputStream r1 = r2.openInputStream(r1)     // Catch:{ IOException -> 0x001e, all -> 0x0024 }
            android.graphics.Typeface r0 = r4.createFromInputStream(r5, r1)     // Catch:{ IOException -> 0x002e, all -> 0x002c }
            android.support.v4.graphics.TypefaceCompatUtil.closeQuietly(r1)
            goto L_0x0005
        L_0x001e:
            r1 = move-exception
            r1 = r0
        L_0x0020:
            android.support.v4.graphics.TypefaceCompatUtil.closeQuietly(r1)
            goto L_0x0005
        L_0x0024:
            r1 = move-exception
            r3 = r1
            r1 = r0
            r0 = r3
        L_0x0028:
            android.support.v4.graphics.TypefaceCompatUtil.closeQuietly(r1)
            throw r0
        L_0x002c:
            r0 = move-exception
            goto L_0x0028
        L_0x002e:
            r2 = move-exception
            goto L_0x0020
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.v4.graphics.TypefaceCompatBaseImpl.createFromFontInfo(android.content.Context, android.os.CancellationSignal, android.support.v4.provider.FontsContractCompat$FontInfo[], int):android.graphics.Typeface");
    }

    private FontFileResourceEntry findBestEntry(FontFamilyFilesResourceEntry fontFamilyFilesResourceEntry, int i) {
        return (FontFileResourceEntry) findBestFont(fontFamilyFilesResourceEntry.getEntries(), i, new StyleExtractor<FontFileResourceEntry>() {
            public int getWeight(FontFileResourceEntry fontFileResourceEntry) {
                return fontFileResourceEntry.getWeight();
            }

            public boolean isItalic(FontFileResourceEntry fontFileResourceEntry) {
                return fontFileResourceEntry.isItalic();
            }
        });
    }

    public Typeface createFromFontFamilyFilesResourceEntry(Context context, FontFamilyFilesResourceEntry fontFamilyFilesResourceEntry, Resources resources, int i) {
        FontFileResourceEntry findBestEntry = findBestEntry(fontFamilyFilesResourceEntry, i);
        if (findBestEntry == null) {
            return null;
        }
        return TypefaceCompat.createFromResourcesFontFile(context, resources, findBestEntry.getResourceId(), findBestEntry.getFileName(), i);
    }

    public Typeface createFromResourcesFontFile(Context context, Resources resources, int i, String str, int i2) {
        Typeface typeface = null;
        File tempFile = TypefaceCompatUtil.getTempFile(context);
        if (tempFile != null) {
            try {
                if (TypefaceCompatUtil.copyToFile(tempFile, resources, i)) {
                    typeface = Typeface.createFromFile(tempFile.getPath());
                    tempFile.delete();
                }
            } catch (RuntimeException e) {
            } finally {
                tempFile.delete();
            }
        }
        return typeface;
    }
}
