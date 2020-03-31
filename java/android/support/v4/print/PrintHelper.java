package android.support.v4.print;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.BitmapFactory;
import android.graphics.BitmapFactory.Options;
import android.graphics.Canvas;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.RectF;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.os.CancellationSignal;
import android.os.CancellationSignal.OnCancelListener;
import android.os.ParcelFileDescriptor;
import android.print.PageRange;
import android.print.PrintAttributes;
import android.print.PrintAttributes.Margins;
import android.print.PrintAttributes.MediaSize;
import android.print.PrintDocumentAdapter;
import android.print.PrintDocumentAdapter.LayoutResultCallback;
import android.print.PrintDocumentAdapter.WriteResultCallback;
import android.print.PrintDocumentInfo;
import android.print.PrintDocumentInfo.Builder;
import android.print.PrintManager;
import android.util.Log;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

public final class PrintHelper {
    @SuppressLint({"InlinedApi"})
    public static final int COLOR_MODE_COLOR = 2;
    @SuppressLint({"InlinedApi"})
    public static final int COLOR_MODE_MONOCHROME = 1;
    static final boolean IS_MIN_MARGINS_HANDLING_CORRECT;
    private static final String LOG_TAG = "PrintHelper";
    private static final int MAX_PRINT_SIZE = 3500;
    public static final int ORIENTATION_LANDSCAPE = 1;
    public static final int ORIENTATION_PORTRAIT = 2;
    static final boolean PRINT_ACTIVITY_RESPECTS_ORIENTATION = (VERSION.SDK_INT < 20 || VERSION.SDK_INT > 23);
    public static final int SCALE_MODE_FILL = 2;
    public static final int SCALE_MODE_FIT = 1;
    int mColorMode = 2;
    final Context mContext;
    Options mDecodeOptions = null;
    final Object mLock = new Object();
    int mOrientation = 1;
    int mScaleMode = 2;

    public interface OnPrintFinishCallback {
        void onFinish();
    }

    private class PrintBitmapAdapter extends PrintDocumentAdapter {
        private PrintAttributes mAttributes;
        private final Bitmap mBitmap;
        private final OnPrintFinishCallback mCallback;
        private final int mFittingMode;
        private final String mJobName;

        PrintBitmapAdapter(String str, int i, Bitmap bitmap, OnPrintFinishCallback onPrintFinishCallback) {
            this.mJobName = str;
            this.mFittingMode = i;
            this.mBitmap = bitmap;
            this.mCallback = onPrintFinishCallback;
        }

        public void onLayout(PrintAttributes printAttributes, PrintAttributes printAttributes2, CancellationSignal cancellationSignal, LayoutResultCallback layoutResultCallback, Bundle bundle) {
            boolean z = true;
            this.mAttributes = printAttributes2;
            PrintDocumentInfo build = new Builder(this.mJobName).setContentType(1).setPageCount(1).build();
            if (printAttributes2.equals(printAttributes)) {
                z = false;
            }
            layoutResultCallback.onLayoutFinished(build, z);
        }

        public void onWrite(PageRange[] pageRangeArr, ParcelFileDescriptor parcelFileDescriptor, CancellationSignal cancellationSignal, WriteResultCallback writeResultCallback) {
            PrintHelper.this.writeBitmap(this.mAttributes, this.mFittingMode, this.mBitmap, parcelFileDescriptor, cancellationSignal, writeResultCallback);
        }

        public void onFinish() {
            if (this.mCallback != null) {
                this.mCallback.onFinish();
            }
        }
    }

    private class PrintUriAdapter extends PrintDocumentAdapter {
        PrintAttributes mAttributes;
        Bitmap mBitmap = null;
        final OnPrintFinishCallback mCallback;
        final int mFittingMode;
        final Uri mImageFile;
        final String mJobName;
        AsyncTask<Uri, Boolean, Bitmap> mLoadBitmap;

        PrintUriAdapter(String str, Uri uri, OnPrintFinishCallback onPrintFinishCallback, int i) {
            this.mJobName = str;
            this.mImageFile = uri;
            this.mCallback = onPrintFinishCallback;
            this.mFittingMode = i;
        }

        public void onLayout(PrintAttributes printAttributes, PrintAttributes printAttributes2, CancellationSignal cancellationSignal, LayoutResultCallback layoutResultCallback, Bundle bundle) {
            boolean z = true;
            synchronized (this) {
                this.mAttributes = printAttributes2;
            }
            if (cancellationSignal.isCanceled()) {
                layoutResultCallback.onLayoutCancelled();
            } else if (this.mBitmap != null) {
                PrintDocumentInfo build = new Builder(this.mJobName).setContentType(1).setPageCount(1).build();
                if (printAttributes2.equals(printAttributes)) {
                    z = false;
                }
                layoutResultCallback.onLayoutFinished(build, z);
            } else {
                final CancellationSignal cancellationSignal2 = cancellationSignal;
                final PrintAttributes printAttributes3 = printAttributes2;
                final PrintAttributes printAttributes4 = printAttributes;
                final LayoutResultCallback layoutResultCallback2 = layoutResultCallback;
                this.mLoadBitmap = new AsyncTask<Uri, Boolean, Bitmap>() {
                    /* access modifiers changed from: protected */
                    public void onPreExecute() {
                        cancellationSignal2.setOnCancelListener(new OnCancelListener() {
                            public void onCancel() {
                                PrintUriAdapter.this.cancelLoad();
                                AnonymousClass1.this.cancel(false);
                            }
                        });
                    }

                    /* access modifiers changed from: protected */
                    public Bitmap doInBackground(Uri... uriArr) {
                        try {
                            return PrintHelper.this.loadConstrainedBitmap(PrintUriAdapter.this.mImageFile);
                        } catch (FileNotFoundException e) {
                            return null;
                        }
                    }

                    /* access modifiers changed from: protected */
                    public void onPostExecute(Bitmap bitmap) {
                        MediaSize mediaSize;
                        boolean z = true;
                        super.onPostExecute(bitmap);
                        if (bitmap != null && (!PrintHelper.PRINT_ACTIVITY_RESPECTS_ORIENTATION || PrintHelper.this.mOrientation == 0)) {
                            synchronized (this) {
                                mediaSize = PrintUriAdapter.this.mAttributes.getMediaSize();
                            }
                            if (!(mediaSize == null || mediaSize.isPortrait() == PrintHelper.isPortrait(bitmap))) {
                                Matrix matrix = new Matrix();
                                matrix.postRotate(90.0f);
                                bitmap = Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), matrix, true);
                            }
                        }
                        PrintUriAdapter.this.mBitmap = bitmap;
                        if (bitmap != null) {
                            PrintDocumentInfo build = new Builder(PrintUriAdapter.this.mJobName).setContentType(1).setPageCount(1).build();
                            if (printAttributes3.equals(printAttributes4)) {
                                z = false;
                            }
                            layoutResultCallback2.onLayoutFinished(build, z);
                        } else {
                            layoutResultCallback2.onLayoutFailed(null);
                        }
                        PrintUriAdapter.this.mLoadBitmap = null;
                    }

                    /* access modifiers changed from: protected */
                    public void onCancelled(Bitmap bitmap) {
                        layoutResultCallback2.onLayoutCancelled();
                        PrintUriAdapter.this.mLoadBitmap = null;
                    }
                }.execute(new Uri[0]);
            }
        }

        /* access modifiers changed from: 0000 */
        public void cancelLoad() {
            synchronized (PrintHelper.this.mLock) {
                if (PrintHelper.this.mDecodeOptions != null) {
                    if (VERSION.SDK_INT < 24) {
                        PrintHelper.this.mDecodeOptions.requestCancelDecode();
                    }
                    PrintHelper.this.mDecodeOptions = null;
                }
            }
        }

        public void onFinish() {
            super.onFinish();
            cancelLoad();
            if (this.mLoadBitmap != null) {
                this.mLoadBitmap.cancel(true);
            }
            if (this.mCallback != null) {
                this.mCallback.onFinish();
            }
            if (this.mBitmap != null) {
                this.mBitmap.recycle();
                this.mBitmap = null;
            }
        }

        public void onWrite(PageRange[] pageRangeArr, ParcelFileDescriptor parcelFileDescriptor, CancellationSignal cancellationSignal, WriteResultCallback writeResultCallback) {
            PrintHelper.this.writeBitmap(this.mAttributes, this.mFittingMode, this.mBitmap, parcelFileDescriptor, cancellationSignal, writeResultCallback);
        }
    }

    static {
        boolean z = true;
        if (VERSION.SDK_INT == 23) {
            z = false;
        }
        IS_MIN_MARGINS_HANDLING_CORRECT = z;
    }

    public static boolean systemSupportsPrint() {
        return VERSION.SDK_INT >= 19;
    }

    public PrintHelper(Context context) {
        this.mContext = context;
    }

    public void setScaleMode(int i) {
        this.mScaleMode = i;
    }

    public int getScaleMode() {
        return this.mScaleMode;
    }

    public void setColorMode(int i) {
        this.mColorMode = i;
    }

    public int getColorMode() {
        return this.mColorMode;
    }

    public void setOrientation(int i) {
        this.mOrientation = i;
    }

    public int getOrientation() {
        if (VERSION.SDK_INT < 19 || this.mOrientation != 0) {
            return this.mOrientation;
        }
        return 1;
    }

    public void printBitmap(String str, Bitmap bitmap) {
        printBitmap(str, bitmap, (OnPrintFinishCallback) null);
    }

    public void printBitmap(String str, Bitmap bitmap, OnPrintFinishCallback onPrintFinishCallback) {
        MediaSize mediaSize;
        if (VERSION.SDK_INT >= 19 && bitmap != null) {
            PrintManager printManager = (PrintManager) this.mContext.getSystemService("print");
            if (isPortrait(bitmap)) {
                mediaSize = MediaSize.UNKNOWN_PORTRAIT;
            } else {
                mediaSize = MediaSize.UNKNOWN_LANDSCAPE;
            }
            printManager.print(str, new PrintBitmapAdapter(str, this.mScaleMode, bitmap, onPrintFinishCallback), new PrintAttributes.Builder().setMediaSize(mediaSize).setColorMode(this.mColorMode).build());
        }
    }

    public void printBitmap(String str, Uri uri) throws FileNotFoundException {
        printBitmap(str, uri, (OnPrintFinishCallback) null);
    }

    public void printBitmap(String str, Uri uri, OnPrintFinishCallback onPrintFinishCallback) throws FileNotFoundException {
        if (VERSION.SDK_INT >= 19) {
            PrintUriAdapter printUriAdapter = new PrintUriAdapter(str, uri, onPrintFinishCallback, this.mScaleMode);
            PrintManager printManager = (PrintManager) this.mContext.getSystemService("print");
            PrintAttributes.Builder builder = new PrintAttributes.Builder();
            builder.setColorMode(this.mColorMode);
            if (this.mOrientation == 1 || this.mOrientation == 0) {
                builder.setMediaSize(MediaSize.UNKNOWN_LANDSCAPE);
            } else if (this.mOrientation == 2) {
                builder.setMediaSize(MediaSize.UNKNOWN_PORTRAIT);
            }
            printManager.print(str, printUriAdapter, builder.build());
        }
    }

    static boolean isPortrait(Bitmap bitmap) {
        return bitmap.getWidth() <= bitmap.getHeight();
    }

    private static PrintAttributes.Builder copyAttributes(PrintAttributes printAttributes) {
        PrintAttributes.Builder minMargins = new PrintAttributes.Builder().setMediaSize(printAttributes.getMediaSize()).setResolution(printAttributes.getResolution()).setMinMargins(printAttributes.getMinMargins());
        if (printAttributes.getColorMode() != 0) {
            minMargins.setColorMode(printAttributes.getColorMode());
        }
        if (VERSION.SDK_INT >= 23 && printAttributes.getDuplexMode() != 0) {
            minMargins.setDuplexMode(printAttributes.getDuplexMode());
        }
        return minMargins;
    }

    static Matrix getMatrix(int i, int i2, RectF rectF, int i3) {
        float min;
        Matrix matrix = new Matrix();
        float width = rectF.width() / ((float) i);
        if (i3 == 2) {
            min = Math.max(width, rectF.height() / ((float) i2));
        } else {
            min = Math.min(width, rectF.height() / ((float) i2));
        }
        matrix.postScale(min, min);
        matrix.postTranslate((rectF.width() - (((float) i) * min)) / 2.0f, (rectF.height() - (min * ((float) i2))) / 2.0f);
        return matrix;
    }

    /* access modifiers changed from: 0000 */
    public void writeBitmap(PrintAttributes printAttributes, int i, Bitmap bitmap, ParcelFileDescriptor parcelFileDescriptor, CancellationSignal cancellationSignal, WriteResultCallback writeResultCallback) {
        final PrintAttributes build;
        if (IS_MIN_MARGINS_HANDLING_CORRECT) {
            build = printAttributes;
        } else {
            build = copyAttributes(printAttributes).setMinMargins(new Margins(0, 0, 0, 0)).build();
        }
        final CancellationSignal cancellationSignal2 = cancellationSignal;
        final Bitmap bitmap2 = bitmap;
        final PrintAttributes printAttributes2 = printAttributes;
        final int i2 = i;
        final ParcelFileDescriptor parcelFileDescriptor2 = parcelFileDescriptor;
        final WriteResultCallback writeResultCallback2 = writeResultCallback;
        new AsyncTask<Void, Void, Throwable>() {
            /* access modifiers changed from: protected */
            /* JADX WARNING: No exception handlers in catch block: Catch:{  } */
            /* JADX WARNING: Unknown top exception splitter block from list: {B:20:0x0070=Splitter:B:20:0x0070, B:33:0x00ab=Splitter:B:33:0x00ab, B:46:0x00dc=Splitter:B:46:0x00dc} */
            /* Code decompiled incorrectly, please refer to instructions dump. */
            public java.lang.Throwable doInBackground(java.lang.Void... r9) {
                /*
                    r8 = this;
                    r0 = 0
                    android.os.CancellationSignal r1 = r2     // Catch:{ Throwable -> 0x0078 }
                    boolean r1 = r1.isCanceled()     // Catch:{ Throwable -> 0x0078 }
                    if (r1 == 0) goto L_0x000a
                L_0x0009:
                    return r0
                L_0x000a:
                    android.print.pdf.PrintedPdfDocument r2 = new android.print.pdf.PrintedPdfDocument     // Catch:{ Throwable -> 0x0078 }
                    android.support.v4.print.PrintHelper r1 = android.support.v4.print.PrintHelper.this     // Catch:{ Throwable -> 0x0078 }
                    android.content.Context r1 = r1.mContext     // Catch:{ Throwable -> 0x0078 }
                    android.print.PrintAttributes r3 = r3     // Catch:{ Throwable -> 0x0078 }
                    r2.<init>(r1, r3)     // Catch:{ Throwable -> 0x0078 }
                    android.graphics.Bitmap r1 = r4     // Catch:{ Throwable -> 0x0078 }
                    android.print.PrintAttributes r3 = r3     // Catch:{ Throwable -> 0x0078 }
                    int r3 = r3.getColorMode()     // Catch:{ Throwable -> 0x0078 }
                    android.graphics.Bitmap r3 = android.support.v4.print.PrintHelper.convertBitmapForColorMode(r1, r3)     // Catch:{ Throwable -> 0x0078 }
                    android.os.CancellationSignal r1 = r2     // Catch:{ Throwable -> 0x0078 }
                    boolean r1 = r1.isCanceled()     // Catch:{ Throwable -> 0x0078 }
                    if (r1 != 0) goto L_0x0009
                    r1 = 1
                    android.graphics.pdf.PdfDocument$Page r4 = r2.startPage(r1)     // Catch:{ all -> 0x009e }
                    boolean r1 = android.support.v4.print.PrintHelper.IS_MIN_MARGINS_HANDLING_CORRECT     // Catch:{ all -> 0x009e }
                    if (r1 == 0) goto L_0x007a
                    android.graphics.RectF r1 = new android.graphics.RectF     // Catch:{ all -> 0x009e }
                    android.graphics.pdf.PdfDocument$PageInfo r5 = r4.getInfo()     // Catch:{ all -> 0x009e }
                    android.graphics.Rect r5 = r5.getContentRect()     // Catch:{ all -> 0x009e }
                    r1.<init>(r5)     // Catch:{ all -> 0x009e }
                L_0x003f:
                    int r5 = r3.getWidth()     // Catch:{ all -> 0x009e }
                    int r6 = r3.getHeight()     // Catch:{ all -> 0x009e }
                    int r7 = r6     // Catch:{ all -> 0x009e }
                    android.graphics.Matrix r5 = android.support.v4.print.PrintHelper.getMatrix(r5, r6, r1, r7)     // Catch:{ all -> 0x009e }
                    boolean r6 = android.support.v4.print.PrintHelper.IS_MIN_MARGINS_HANDLING_CORRECT     // Catch:{ all -> 0x009e }
                    if (r6 == 0) goto L_0x00b3
                L_0x0051:
                    android.graphics.Canvas r1 = r4.getCanvas()     // Catch:{ all -> 0x009e }
                    r6 = 0
                    r1.drawBitmap(r3, r5, r6)     // Catch:{ all -> 0x009e }
                    r2.finishPage(r4)     // Catch:{ all -> 0x009e }
                    android.os.CancellationSignal r1 = r2     // Catch:{ all -> 0x009e }
                    boolean r1 = r1.isCanceled()     // Catch:{ all -> 0x009e }
                    if (r1 == 0) goto L_0x00c2
                    r2.close()     // Catch:{ Throwable -> 0x0078 }
                    android.os.ParcelFileDescriptor r1 = r7     // Catch:{ Throwable -> 0x0078 }
                    if (r1 == 0) goto L_0x0070
                    android.os.ParcelFileDescriptor r1 = r7     // Catch:{ IOException -> 0x00e9 }
                    r1.close()     // Catch:{ IOException -> 0x00e9 }
                L_0x0070:
                    android.graphics.Bitmap r1 = r4     // Catch:{ Throwable -> 0x0078 }
                    if (r3 == r1) goto L_0x0009
                    r3.recycle()     // Catch:{ Throwable -> 0x0078 }
                    goto L_0x0009
                L_0x0078:
                    r0 = move-exception
                    goto L_0x0009
                L_0x007a:
                    android.print.pdf.PrintedPdfDocument r5 = new android.print.pdf.PrintedPdfDocument     // Catch:{ all -> 0x009e }
                    android.support.v4.print.PrintHelper r1 = android.support.v4.print.PrintHelper.this     // Catch:{ all -> 0x009e }
                    android.content.Context r1 = r1.mContext     // Catch:{ all -> 0x009e }
                    android.print.PrintAttributes r6 = r5     // Catch:{ all -> 0x009e }
                    r5.<init>(r1, r6)     // Catch:{ all -> 0x009e }
                    r1 = 1
                    android.graphics.pdf.PdfDocument$Page r6 = r5.startPage(r1)     // Catch:{ all -> 0x009e }
                    android.graphics.RectF r1 = new android.graphics.RectF     // Catch:{ all -> 0x009e }
                    android.graphics.pdf.PdfDocument$PageInfo r7 = r6.getInfo()     // Catch:{ all -> 0x009e }
                    android.graphics.Rect r7 = r7.getContentRect()     // Catch:{ all -> 0x009e }
                    r1.<init>(r7)     // Catch:{ all -> 0x009e }
                    r5.finishPage(r6)     // Catch:{ all -> 0x009e }
                    r5.close()     // Catch:{ all -> 0x009e }
                    goto L_0x003f
                L_0x009e:
                    r0 = move-exception
                    r2.close()     // Catch:{ Throwable -> 0x0078 }
                    android.os.ParcelFileDescriptor r1 = r7     // Catch:{ Throwable -> 0x0078 }
                    if (r1 == 0) goto L_0x00ab
                    android.os.ParcelFileDescriptor r1 = r7     // Catch:{ IOException -> 0x00e5 }
                    r1.close()     // Catch:{ IOException -> 0x00e5 }
                L_0x00ab:
                    android.graphics.Bitmap r1 = r4     // Catch:{ Throwable -> 0x0078 }
                    if (r3 == r1) goto L_0x00b2
                    r3.recycle()     // Catch:{ Throwable -> 0x0078 }
                L_0x00b2:
                    throw r0     // Catch:{ Throwable -> 0x0078 }
                L_0x00b3:
                    float r6 = r1.left     // Catch:{ all -> 0x009e }
                    float r7 = r1.top     // Catch:{ all -> 0x009e }
                    r5.postTranslate(r6, r7)     // Catch:{ all -> 0x009e }
                    android.graphics.Canvas r6 = r4.getCanvas()     // Catch:{ all -> 0x009e }
                    r6.clipRect(r1)     // Catch:{ all -> 0x009e }
                    goto L_0x0051
                L_0x00c2:
                    java.io.FileOutputStream r1 = new java.io.FileOutputStream     // Catch:{ all -> 0x009e }
                    android.os.ParcelFileDescriptor r4 = r7     // Catch:{ all -> 0x009e }
                    java.io.FileDescriptor r4 = r4.getFileDescriptor()     // Catch:{ all -> 0x009e }
                    r1.<init>(r4)     // Catch:{ all -> 0x009e }
                    r2.writeTo(r1)     // Catch:{ all -> 0x009e }
                    r2.close()     // Catch:{ Throwable -> 0x0078 }
                    android.os.ParcelFileDescriptor r1 = r7     // Catch:{ Throwable -> 0x0078 }
                    if (r1 == 0) goto L_0x00dc
                    android.os.ParcelFileDescriptor r1 = r7     // Catch:{ IOException -> 0x00e7 }
                    r1.close()     // Catch:{ IOException -> 0x00e7 }
                L_0x00dc:
                    android.graphics.Bitmap r1 = r4     // Catch:{ Throwable -> 0x0078 }
                    if (r3 == r1) goto L_0x0009
                    r3.recycle()     // Catch:{ Throwable -> 0x0078 }
                    goto L_0x0009
                L_0x00e5:
                    r1 = move-exception
                    goto L_0x00ab
                L_0x00e7:
                    r1 = move-exception
                    goto L_0x00dc
                L_0x00e9:
                    r1 = move-exception
                    goto L_0x0070
                */
                throw new UnsupportedOperationException("Method not decompiled: android.support.v4.print.PrintHelper.AnonymousClass1.doInBackground(java.lang.Void[]):java.lang.Throwable");
            }

            /* access modifiers changed from: protected */
            public void onPostExecute(Throwable th) {
                if (cancellationSignal2.isCanceled()) {
                    writeResultCallback2.onWriteCancelled();
                } else if (th == null) {
                    writeResultCallback2.onWriteFinished(new PageRange[]{PageRange.ALL_PAGES});
                } else {
                    Log.e(PrintHelper.LOG_TAG, "Error writing printed content", th);
                    writeResultCallback2.onWriteFailed(null);
                }
            }
        }.execute(new Void[0]);
    }

    /* access modifiers changed from: 0000 */
    public Bitmap loadConstrainedBitmap(Uri uri) throws FileNotFoundException {
        Options options;
        int i = 1;
        Bitmap bitmap = null;
        if (uri == null || this.mContext == null) {
            throw new IllegalArgumentException("bad argument to getScaledBitmap");
        }
        Options options2 = new Options();
        options2.inJustDecodeBounds = true;
        loadBitmap(uri, options2);
        int i2 = options2.outWidth;
        int i3 = options2.outHeight;
        if (i2 > 0 && i3 > 0) {
            int max = Math.max(i2, i3);
            while (max > MAX_PRINT_SIZE) {
                max >>>= 1;
                i <<= 1;
            }
            if (i > 0 && Math.min(i2, i3) / i > 0) {
                synchronized (this.mLock) {
                    this.mDecodeOptions = new Options();
                    this.mDecodeOptions.inMutable = true;
                    this.mDecodeOptions.inSampleSize = i;
                    options = this.mDecodeOptions;
                }
                try {
                    bitmap = loadBitmap(uri, options);
                    synchronized (this.mLock) {
                        this.mDecodeOptions = null;
                    }
                } catch (Throwable th) {
                    synchronized (this.mLock) {
                        this.mDecodeOptions = null;
                        throw th;
                    }
                }
            }
        }
        return bitmap;
    }

    private Bitmap loadBitmap(Uri uri, Options options) throws FileNotFoundException {
        InputStream inputStream = null;
        if (uri == null || this.mContext == null) {
            throw new IllegalArgumentException("bad argument to loadBitmap");
        }
        try {
            inputStream = this.mContext.getContentResolver().openInputStream(uri);
            Bitmap decodeStream = BitmapFactory.decodeStream(inputStream, null, options);
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    Log.w(LOG_TAG, "close fail ", e);
                }
            }
            return decodeStream;
        } finally {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e2) {
                    Log.w(LOG_TAG, "close fail ", e2);
                }
            }
        }
    }

    static Bitmap convertBitmapForColorMode(Bitmap bitmap, int i) {
        if (i != 1) {
            return bitmap;
        }
        Bitmap createBitmap = Bitmap.createBitmap(bitmap.getWidth(), bitmap.getHeight(), Config.ARGB_8888);
        Canvas canvas = new Canvas(createBitmap);
        Paint paint = new Paint();
        ColorMatrix colorMatrix = new ColorMatrix();
        colorMatrix.setSaturation(0.0f);
        paint.setColorFilter(new ColorMatrixColorFilter(colorMatrix));
        canvas.drawBitmap(bitmap, 0.0f, 0.0f, paint);
        canvas.setBitmap(null);
        return createBitmap;
    }
}
