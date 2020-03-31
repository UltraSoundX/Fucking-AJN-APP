package android.support.v4.provider;

import android.content.Context;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.pm.ProviderInfo;
import android.content.pm.Signature;
import android.content.res.Resources;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.CancellationSignal;
import android.os.Handler;
import android.provider.BaseColumns;
import android.support.v4.content.res.FontResourcesParserCompat;
import android.support.v4.graphics.TypefaceCompat;
import android.support.v4.graphics.TypefaceCompatUtil;
import android.support.v4.provider.SelfDestructiveThread.ReplyCallback;
import android.support.v4.util.LruCache;
import android.support.v4.util.Preconditions;
import android.support.v4.util.SimpleArrayMap;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FontsContractCompat {
    private static final int BACKGROUND_THREAD_KEEP_ALIVE_DURATION_MS = 10000;
    public static final String PARCEL_FONT_RESULTS = "font_results";
    static final int RESULT_CODE_PROVIDER_NOT_FOUND = -1;
    static final int RESULT_CODE_WRONG_CERTIFICATES = -2;
    private static final String TAG = "FontsContractCompat";
    private static final SelfDestructiveThread sBackgroundThread = new SelfDestructiveThread("fonts", 10, BACKGROUND_THREAD_KEEP_ALIVE_DURATION_MS);
    private static final Comparator<byte[]> sByteArrayComparator = new Comparator<byte[]>() {
        public int compare(byte[] bArr, byte[] bArr2) {
            if (bArr.length != bArr2.length) {
                return bArr.length - bArr2.length;
            }
            for (int i = 0; i < bArr.length; i++) {
                if (bArr[i] != bArr2[i]) {
                    return bArr[i] - bArr2[i];
                }
            }
            return 0;
        }
    };
    static final Object sLock = new Object();
    static final SimpleArrayMap<String, ArrayList<ReplyCallback<TypefaceResult>>> sPendingReplies = new SimpleArrayMap<>();
    static final LruCache<String, Typeface> sTypefaceCache = new LruCache<>(16);

    public static final class Columns implements BaseColumns {
        public static final String FILE_ID = "file_id";
        public static final String ITALIC = "font_italic";
        public static final String RESULT_CODE = "result_code";
        public static final int RESULT_CODE_FONT_NOT_FOUND = 1;
        public static final int RESULT_CODE_FONT_UNAVAILABLE = 2;
        public static final int RESULT_CODE_MALFORMED_QUERY = 3;
        public static final int RESULT_CODE_OK = 0;
        public static final String TTC_INDEX = "font_ttc_index";
        public static final String VARIATION_SETTINGS = "font_variation_settings";
        public static final String WEIGHT = "font_weight";
    }

    public static class FontFamilyResult {
        public static final int STATUS_OK = 0;
        public static final int STATUS_UNEXPECTED_DATA_PROVIDED = 2;
        public static final int STATUS_WRONG_CERTIFICATES = 1;
        private final FontInfo[] mFonts;
        private final int mStatusCode;

        public FontFamilyResult(int i, FontInfo[] fontInfoArr) {
            this.mStatusCode = i;
            this.mFonts = fontInfoArr;
        }

        public int getStatusCode() {
            return this.mStatusCode;
        }

        public FontInfo[] getFonts() {
            return this.mFonts;
        }
    }

    public static class FontInfo {
        private final boolean mItalic;
        private final int mResultCode;
        private final int mTtcIndex;
        private final Uri mUri;
        private final int mWeight;

        public FontInfo(Uri uri, int i, int i2, boolean z, int i3) {
            this.mUri = (Uri) Preconditions.checkNotNull(uri);
            this.mTtcIndex = i;
            this.mWeight = i2;
            this.mItalic = z;
            this.mResultCode = i3;
        }

        public Uri getUri() {
            return this.mUri;
        }

        public int getTtcIndex() {
            return this.mTtcIndex;
        }

        public int getWeight() {
            return this.mWeight;
        }

        public boolean isItalic() {
            return this.mItalic;
        }

        public int getResultCode() {
            return this.mResultCode;
        }
    }

    public static class FontRequestCallback {
        public static final int FAIL_REASON_FONT_LOAD_ERROR = -3;
        public static final int FAIL_REASON_FONT_NOT_FOUND = 1;
        public static final int FAIL_REASON_FONT_UNAVAILABLE = 2;
        public static final int FAIL_REASON_MALFORMED_QUERY = 3;
        public static final int FAIL_REASON_PROVIDER_NOT_FOUND = -1;
        public static final int FAIL_REASON_SECURITY_VIOLATION = -4;
        public static final int FAIL_REASON_WRONG_CERTIFICATES = -2;
        public static final int RESULT_OK = 0;

        @Retention(RetentionPolicy.SOURCE)
        public @interface FontRequestFailReason {
        }

        public void onTypefaceRetrieved(Typeface typeface) {
        }

        public void onTypefaceRequestFailed(int i) {
        }
    }

    private static final class TypefaceResult {
        final int mResult;
        final Typeface mTypeface;

        TypefaceResult(Typeface typeface, int i) {
            this.mTypeface = typeface;
            this.mResult = i;
        }
    }

    private FontsContractCompat() {
    }

    static TypefaceResult getFontInternal(Context context, FontRequest fontRequest, int i) {
        int i2 = -3;
        try {
            FontFamilyResult fetchFonts = fetchFonts(context, null, fontRequest);
            if (fetchFonts.getStatusCode() == 0) {
                Typeface createFromFontInfo = TypefaceCompat.createFromFontInfo(context, null, fetchFonts.getFonts(), i);
                if (createFromFontInfo != null) {
                    i2 = 0;
                }
                return new TypefaceResult(createFromFontInfo, i2);
            }
            if (fetchFonts.getStatusCode() == 1) {
                i2 = -2;
            }
            return new TypefaceResult(null, i2);
        } catch (NameNotFoundException e) {
            return new TypefaceResult(null, -1);
        }
    }

    public static void resetCache() {
        sTypefaceCache.evictAll();
    }

    /* JADX WARNING: Code restructure failed: missing block: B:38:0x0093, code lost:
        sBackgroundThread.postAndReply(r4, new android.support.v4.provider.FontsContractCompat.AnonymousClass3());
     */
    /* JADX WARNING: Code restructure failed: missing block: B:42:?, code lost:
        return r2;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:43:?, code lost:
        return r2;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static android.graphics.Typeface getFontSync(final android.content.Context r6, final android.support.v4.provider.FontRequest r7, final android.support.v4.content.res.ResourcesCompat.FontCallback r8, final android.os.Handler r9, boolean r10, int r11, final int r12) {
        /*
            r2 = 0
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
            java.lang.String r1 = r7.getIdentifier()
            java.lang.StringBuilder r0 = r0.append(r1)
            java.lang.String r1 = "-"
            java.lang.StringBuilder r0 = r0.append(r1)
            java.lang.StringBuilder r0 = r0.append(r12)
            java.lang.String r3 = r0.toString()
            android.support.v4.util.LruCache<java.lang.String, android.graphics.Typeface> r0 = sTypefaceCache
            java.lang.Object r0 = r0.get(r3)
            android.graphics.Typeface r0 = (android.graphics.Typeface) r0
            if (r0 == 0) goto L_0x002d
            if (r8 == 0) goto L_0x002b
            r8.onFontRetrieved(r0)
        L_0x002b:
            r2 = r0
        L_0x002c:
            return r2
        L_0x002d:
            if (r10 == 0) goto L_0x004a
            r0 = -1
            if (r11 != r0) goto L_0x004a
            android.support.v4.provider.FontsContractCompat$TypefaceResult r0 = getFontInternal(r6, r7, r12)
            if (r8 == 0) goto L_0x0041
            int r1 = r0.mResult
            if (r1 != 0) goto L_0x0044
            android.graphics.Typeface r1 = r0.mTypeface
            r8.callbackSuccessAsync(r1, r9)
        L_0x0041:
            android.graphics.Typeface r2 = r0.mTypeface
            goto L_0x002c
        L_0x0044:
            int r1 = r0.mResult
            r8.callbackFailAsync(r1, r9)
            goto L_0x0041
        L_0x004a:
            android.support.v4.provider.FontsContractCompat$1 r4 = new android.support.v4.provider.FontsContractCompat$1
            r4.<init>(r6, r7, r12, r3)
            if (r10 == 0) goto L_0x005c
            android.support.v4.provider.SelfDestructiveThread r0 = sBackgroundThread     // Catch:{ InterruptedException -> 0x009e }
            java.lang.Object r0 = r0.postAndWait(r4, r11)     // Catch:{ InterruptedException -> 0x009e }
            android.support.v4.provider.FontsContractCompat$TypefaceResult r0 = (android.support.v4.provider.FontsContractCompat.TypefaceResult) r0     // Catch:{ InterruptedException -> 0x009e }
            android.graphics.Typeface r2 = r0.mTypeface     // Catch:{ InterruptedException -> 0x009e }
            goto L_0x002c
        L_0x005c:
            if (r8 != 0) goto L_0x007c
            r1 = r2
        L_0x005f:
            java.lang.Object r5 = sLock
            monitor-enter(r5)
            android.support.v4.util.SimpleArrayMap<java.lang.String, java.util.ArrayList<android.support.v4.provider.SelfDestructiveThread$ReplyCallback<android.support.v4.provider.FontsContractCompat$TypefaceResult>>> r0 = sPendingReplies     // Catch:{ all -> 0x0079 }
            boolean r0 = r0.containsKey(r3)     // Catch:{ all -> 0x0079 }
            if (r0 == 0) goto L_0x0083
            if (r1 == 0) goto L_0x0077
            android.support.v4.util.SimpleArrayMap<java.lang.String, java.util.ArrayList<android.support.v4.provider.SelfDestructiveThread$ReplyCallback<android.support.v4.provider.FontsContractCompat$TypefaceResult>>> r0 = sPendingReplies     // Catch:{ all -> 0x0079 }
            java.lang.Object r0 = r0.get(r3)     // Catch:{ all -> 0x0079 }
            java.util.ArrayList r0 = (java.util.ArrayList) r0     // Catch:{ all -> 0x0079 }
            r0.add(r1)     // Catch:{ all -> 0x0079 }
        L_0x0077:
            monitor-exit(r5)     // Catch:{ all -> 0x0079 }
            goto L_0x002c
        L_0x0079:
            r0 = move-exception
            monitor-exit(r5)     // Catch:{ all -> 0x0079 }
            throw r0
        L_0x007c:
            android.support.v4.provider.FontsContractCompat$2 r0 = new android.support.v4.provider.FontsContractCompat$2
            r0.<init>(r8, r9)
            r1 = r0
            goto L_0x005f
        L_0x0083:
            if (r1 == 0) goto L_0x0092
            java.util.ArrayList r0 = new java.util.ArrayList     // Catch:{ all -> 0x0079 }
            r0.<init>()     // Catch:{ all -> 0x0079 }
            r0.add(r1)     // Catch:{ all -> 0x0079 }
            android.support.v4.util.SimpleArrayMap<java.lang.String, java.util.ArrayList<android.support.v4.provider.SelfDestructiveThread$ReplyCallback<android.support.v4.provider.FontsContractCompat$TypefaceResult>>> r1 = sPendingReplies     // Catch:{ all -> 0x0079 }
            r1.put(r3, r0)     // Catch:{ all -> 0x0079 }
        L_0x0092:
            monitor-exit(r5)     // Catch:{ all -> 0x0079 }
            android.support.v4.provider.SelfDestructiveThread r0 = sBackgroundThread
            android.support.v4.provider.FontsContractCompat$3 r1 = new android.support.v4.provider.FontsContractCompat$3
            r1.<init>(r3)
            r0.postAndReply(r4, r1)
            goto L_0x002c
        L_0x009e:
            r0 = move-exception
            goto L_0x002c
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.v4.provider.FontsContractCompat.getFontSync(android.content.Context, android.support.v4.provider.FontRequest, android.support.v4.content.res.ResourcesCompat$FontCallback, android.os.Handler, boolean, int, int):android.graphics.Typeface");
    }

    public static void requestFont(final Context context, final FontRequest fontRequest, final FontRequestCallback fontRequestCallback, Handler handler) {
        final Handler handler2 = new Handler();
        handler.post(new Runnable() {
            public void run() {
                try {
                    FontFamilyResult fetchFonts = FontsContractCompat.fetchFonts(context, null, fontRequest);
                    if (fetchFonts.getStatusCode() != 0) {
                        switch (fetchFonts.getStatusCode()) {
                            case 1:
                                handler2.post(new Runnable() {
                                    public void run() {
                                        fontRequestCallback.onTypefaceRequestFailed(-2);
                                    }
                                });
                                return;
                            case 2:
                                handler2.post(new Runnable() {
                                    public void run() {
                                        fontRequestCallback.onTypefaceRequestFailed(-3);
                                    }
                                });
                                return;
                            default:
                                handler2.post(new Runnable() {
                                    public void run() {
                                        fontRequestCallback.onTypefaceRequestFailed(-3);
                                    }
                                });
                                return;
                        }
                    } else {
                        FontInfo[] fonts = fetchFonts.getFonts();
                        if (fonts == null || fonts.length == 0) {
                            handler2.post(new Runnable() {
                                public void run() {
                                    fontRequestCallback.onTypefaceRequestFailed(1);
                                }
                            });
                            return;
                        }
                        int length = fonts.length;
                        int i = 0;
                        while (i < length) {
                            FontInfo fontInfo = fonts[i];
                            if (fontInfo.getResultCode() != 0) {
                                final int resultCode = fontInfo.getResultCode();
                                if (resultCode < 0) {
                                    handler2.post(new Runnable() {
                                        public void run() {
                                            fontRequestCallback.onTypefaceRequestFailed(-3);
                                        }
                                    });
                                    return;
                                } else {
                                    handler2.post(new Runnable() {
                                        public void run() {
                                            fontRequestCallback.onTypefaceRequestFailed(resultCode);
                                        }
                                    });
                                    return;
                                }
                            } else {
                                i++;
                            }
                        }
                        final Typeface buildTypeface = FontsContractCompat.buildTypeface(context, null, fonts);
                        if (buildTypeface == null) {
                            handler2.post(new Runnable() {
                                public void run() {
                                    fontRequestCallback.onTypefaceRequestFailed(-3);
                                }
                            });
                        } else {
                            handler2.post(new Runnable() {
                                public void run() {
                                    fontRequestCallback.onTypefaceRetrieved(buildTypeface);
                                }
                            });
                        }
                    }
                } catch (NameNotFoundException e) {
                    handler2.post(new Runnable() {
                        public void run() {
                            fontRequestCallback.onTypefaceRequestFailed(-1);
                        }
                    });
                }
            }
        });
    }

    public static Typeface buildTypeface(Context context, CancellationSignal cancellationSignal, FontInfo[] fontInfoArr) {
        return TypefaceCompat.createFromFontInfo(context, cancellationSignal, fontInfoArr, 0);
    }

    public static Map<Uri, ByteBuffer> prepareFontData(Context context, FontInfo[] fontInfoArr, CancellationSignal cancellationSignal) {
        HashMap hashMap = new HashMap();
        for (FontInfo fontInfo : fontInfoArr) {
            if (fontInfo.getResultCode() == 0) {
                Uri uri = fontInfo.getUri();
                if (!hashMap.containsKey(uri)) {
                    hashMap.put(uri, TypefaceCompatUtil.mmap(context, cancellationSignal, uri));
                }
            }
        }
        return Collections.unmodifiableMap(hashMap);
    }

    public static FontFamilyResult fetchFonts(Context context, CancellationSignal cancellationSignal, FontRequest fontRequest) throws NameNotFoundException {
        ProviderInfo provider = getProvider(context.getPackageManager(), fontRequest, context.getResources());
        if (provider == null) {
            return new FontFamilyResult(1, null);
        }
        return new FontFamilyResult(0, getFontFromProvider(context, fontRequest, provider.authority, cancellationSignal));
    }

    public static ProviderInfo getProvider(PackageManager packageManager, FontRequest fontRequest, Resources resources) throws NameNotFoundException {
        int i = 0;
        String providerAuthority = fontRequest.getProviderAuthority();
        ProviderInfo resolveContentProvider = packageManager.resolveContentProvider(providerAuthority, 0);
        if (resolveContentProvider == null) {
            throw new NameNotFoundException("No package found for authority: " + providerAuthority);
        } else if (!resolveContentProvider.packageName.equals(fontRequest.getProviderPackage())) {
            throw new NameNotFoundException("Found content provider " + providerAuthority + ", but package was not " + fontRequest.getProviderPackage());
        } else {
            List convertToByteArrayList = convertToByteArrayList(packageManager.getPackageInfo(resolveContentProvider.packageName, 64).signatures);
            Collections.sort(convertToByteArrayList, sByteArrayComparator);
            List certificates = getCertificates(fontRequest, resources);
            while (true) {
                int i2 = i;
                if (i2 >= certificates.size()) {
                    return null;
                }
                ArrayList arrayList = new ArrayList((Collection) certificates.get(i2));
                Collections.sort(arrayList, sByteArrayComparator);
                if (equalsByteArrayList(convertToByteArrayList, arrayList)) {
                    return resolveContentProvider;
                }
                i = i2 + 1;
            }
        }
    }

    private static List<List<byte[]>> getCertificates(FontRequest fontRequest, Resources resources) {
        if (fontRequest.getCertificates() != null) {
            return fontRequest.getCertificates();
        }
        return FontResourcesParserCompat.readCerts(resources, fontRequest.getCertificatesArrayResId());
    }

    private static boolean equalsByteArrayList(List<byte[]> list, List<byte[]> list2) {
        if (list.size() != list2.size()) {
            return false;
        }
        for (int i = 0; i < list.size(); i++) {
            if (!Arrays.equals((byte[]) list.get(i), (byte[]) list2.get(i))) {
                return false;
            }
        }
        return true;
    }

    private static List<byte[]> convertToByteArrayList(Signature[] signatureArr) {
        ArrayList arrayList = new ArrayList();
        for (Signature byteArray : signatureArr) {
            arrayList.add(byteArray.toByteArray());
        }
        return arrayList;
    }

    /* JADX WARNING: Removed duplicated region for block: B:45:0x0146  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    static android.support.v4.provider.FontsContractCompat.FontInfo[] getFontFromProvider(android.content.Context r18, android.support.v4.provider.FontRequest r19, java.lang.String r20, android.os.CancellationSignal r21) {
        /*
            java.util.ArrayList r11 = new java.util.ArrayList
            r11.<init>()
            android.net.Uri$Builder r2 = new android.net.Uri$Builder
            r2.<init>()
            java.lang.String r3 = "content"
            android.net.Uri$Builder r2 = r2.scheme(r3)
            r0 = r20
            android.net.Uri$Builder r2 = r2.authority(r0)
            android.net.Uri r3 = r2.build()
            android.net.Uri$Builder r2 = new android.net.Uri$Builder
            r2.<init>()
            java.lang.String r4 = "content"
            android.net.Uri$Builder r2 = r2.scheme(r4)
            r0 = r20
            android.net.Uri$Builder r2 = r2.authority(r0)
            java.lang.String r4 = "file"
            android.net.Uri$Builder r2 = r2.appendPath(r4)
            android.net.Uri r12 = r2.build()
            r9 = 0
            int r2 = android.os.Build.VERSION.SDK_INT     // Catch:{ all -> 0x0153 }
            r4 = 16
            if (r2 <= r4) goto L_0x00f4
            android.content.ContentResolver r2 = r18.getContentResolver()     // Catch:{ all -> 0x0153 }
            r4 = 7
            java.lang.String[] r4 = new java.lang.String[r4]     // Catch:{ all -> 0x0153 }
            r5 = 0
            java.lang.String r6 = "_id"
            r4[r5] = r6     // Catch:{ all -> 0x0153 }
            r5 = 1
            java.lang.String r6 = "file_id"
            r4[r5] = r6     // Catch:{ all -> 0x0153 }
            r5 = 2
            java.lang.String r6 = "font_ttc_index"
            r4[r5] = r6     // Catch:{ all -> 0x0153 }
            r5 = 3
            java.lang.String r6 = "font_variation_settings"
            r4[r5] = r6     // Catch:{ all -> 0x0153 }
            r5 = 4
            java.lang.String r6 = "font_weight"
            r4[r5] = r6     // Catch:{ all -> 0x0153 }
            r5 = 5
            java.lang.String r6 = "font_italic"
            r4[r5] = r6     // Catch:{ all -> 0x0153 }
            r5 = 6
            java.lang.String r6 = "result_code"
            r4[r5] = r6     // Catch:{ all -> 0x0153 }
            java.lang.String r5 = "query = ?"
            r6 = 1
            java.lang.String[] r6 = new java.lang.String[r6]     // Catch:{ all -> 0x0153 }
            r7 = 0
            java.lang.String r8 = r19.getQuery()     // Catch:{ all -> 0x0153 }
            r6[r7] = r8     // Catch:{ all -> 0x0153 }
            r7 = 0
            r8 = r21
            android.database.Cursor r10 = r2.query(r3, r4, r5, r6, r7, r8)     // Catch:{ all -> 0x0153 }
        L_0x0079:
            if (r10 == 0) goto L_0x0143
            int r2 = r10.getCount()     // Catch:{ all -> 0x00ec }
            if (r2 <= 0) goto L_0x0143
            java.lang.String r2 = "result_code"
            int r11 = r10.getColumnIndex(r2)     // Catch:{ all -> 0x00ec }
            java.util.ArrayList r2 = new java.util.ArrayList     // Catch:{ all -> 0x00ec }
            r2.<init>()     // Catch:{ all -> 0x00ec }
            java.lang.String r4 = "_id"
            int r13 = r10.getColumnIndex(r4)     // Catch:{ all -> 0x00ec }
            java.lang.String r4 = "file_id"
            int r14 = r10.getColumnIndex(r4)     // Catch:{ all -> 0x00ec }
            java.lang.String r4 = "font_ttc_index"
            int r15 = r10.getColumnIndex(r4)     // Catch:{ all -> 0x00ec }
            java.lang.String r4 = "font_weight"
            int r16 = r10.getColumnIndex(r4)     // Catch:{ all -> 0x00ec }
            java.lang.String r4 = "font_italic"
            int r17 = r10.getColumnIndex(r4)     // Catch:{ all -> 0x00ec }
        L_0x00aa:
            boolean r4 = r10.moveToNext()     // Catch:{ all -> 0x00ec }
            if (r4 == 0) goto L_0x0144
            r4 = -1
            if (r11 == r4) goto L_0x0131
            int r9 = r10.getInt(r11)     // Catch:{ all -> 0x00ec }
        L_0x00b7:
            r4 = -1
            if (r15 == r4) goto L_0x0133
            int r6 = r10.getInt(r15)     // Catch:{ all -> 0x00ec }
        L_0x00be:
            r4 = -1
            if (r14 != r4) goto L_0x0135
            long r4 = r10.getLong(r13)     // Catch:{ all -> 0x00ec }
            android.net.Uri r5 = android.content.ContentUris.withAppendedId(r3, r4)     // Catch:{ all -> 0x00ec }
        L_0x00c9:
            r4 = -1
            r0 = r16
            if (r0 == r4) goto L_0x013e
            r0 = r16
            int r7 = r10.getInt(r0)     // Catch:{ all -> 0x00ec }
        L_0x00d4:
            r4 = -1
            r0 = r17
            if (r0 == r4) goto L_0x0141
            r0 = r17
            int r4 = r10.getInt(r0)     // Catch:{ all -> 0x00ec }
            r8 = 1
            if (r4 != r8) goto L_0x0141
            r8 = 1
        L_0x00e3:
            android.support.v4.provider.FontsContractCompat$FontInfo r4 = new android.support.v4.provider.FontsContractCompat$FontInfo     // Catch:{ all -> 0x00ec }
            r4.<init>(r5, r6, r7, r8, r9)     // Catch:{ all -> 0x00ec }
            r2.add(r4)     // Catch:{ all -> 0x00ec }
            goto L_0x00aa
        L_0x00ec:
            r2 = move-exception
            r3 = r10
        L_0x00ee:
            if (r3 == 0) goto L_0x00f3
            r3.close()
        L_0x00f3:
            throw r2
        L_0x00f4:
            android.content.ContentResolver r2 = r18.getContentResolver()     // Catch:{ all -> 0x0153 }
            r4 = 7
            java.lang.String[] r4 = new java.lang.String[r4]     // Catch:{ all -> 0x0153 }
            r5 = 0
            java.lang.String r6 = "_id"
            r4[r5] = r6     // Catch:{ all -> 0x0153 }
            r5 = 1
            java.lang.String r6 = "file_id"
            r4[r5] = r6     // Catch:{ all -> 0x0153 }
            r5 = 2
            java.lang.String r6 = "font_ttc_index"
            r4[r5] = r6     // Catch:{ all -> 0x0153 }
            r5 = 3
            java.lang.String r6 = "font_variation_settings"
            r4[r5] = r6     // Catch:{ all -> 0x0153 }
            r5 = 4
            java.lang.String r6 = "font_weight"
            r4[r5] = r6     // Catch:{ all -> 0x0153 }
            r5 = 5
            java.lang.String r6 = "font_italic"
            r4[r5] = r6     // Catch:{ all -> 0x0153 }
            r5 = 6
            java.lang.String r6 = "result_code"
            r4[r5] = r6     // Catch:{ all -> 0x0153 }
            java.lang.String r5 = "query = ?"
            r6 = 1
            java.lang.String[] r6 = new java.lang.String[r6]     // Catch:{ all -> 0x0153 }
            r7 = 0
            java.lang.String r8 = r19.getQuery()     // Catch:{ all -> 0x0153 }
            r6[r7] = r8     // Catch:{ all -> 0x0153 }
            r7 = 0
            android.database.Cursor r10 = r2.query(r3, r4, r5, r6, r7)     // Catch:{ all -> 0x0153 }
            goto L_0x0079
        L_0x0131:
            r9 = 0
            goto L_0x00b7
        L_0x0133:
            r6 = 0
            goto L_0x00be
        L_0x0135:
            long r4 = r10.getLong(r14)     // Catch:{ all -> 0x00ec }
            android.net.Uri r5 = android.content.ContentUris.withAppendedId(r12, r4)     // Catch:{ all -> 0x00ec }
            goto L_0x00c9
        L_0x013e:
            r7 = 400(0x190, float:5.6E-43)
            goto L_0x00d4
        L_0x0141:
            r8 = 0
            goto L_0x00e3
        L_0x0143:
            r2 = r11
        L_0x0144:
            if (r10 == 0) goto L_0x0149
            r10.close()
        L_0x0149:
            r3 = 0
            android.support.v4.provider.FontsContractCompat$FontInfo[] r3 = new android.support.v4.provider.FontsContractCompat.FontInfo[r3]
            java.lang.Object[] r2 = r2.toArray(r3)
            android.support.v4.provider.FontsContractCompat$FontInfo[] r2 = (android.support.v4.provider.FontsContractCompat.FontInfo[]) r2
            return r2
        L_0x0153:
            r2 = move-exception
            r3 = r9
            goto L_0x00ee
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.v4.provider.FontsContractCompat.getFontFromProvider(android.content.Context, android.support.v4.provider.FontRequest, java.lang.String, android.os.CancellationSignal):android.support.v4.provider.FontsContractCompat$FontInfo[]");
    }
}
