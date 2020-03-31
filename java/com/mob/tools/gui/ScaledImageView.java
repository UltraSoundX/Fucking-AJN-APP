package com.mob.tools.gui;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.ViewConfiguration;
import android.widget.ImageView;
import com.mob.tools.MobLog;
import com.mob.tools.utils.BitmapHelper;

public class ScaledImageView extends ImageView implements OnTouchListener {
    private static final int DRAG_1 = 1;
    private static final int DRAG_2 = 2;
    private static final int NONE = 0;
    private static final int ZOOM = 3;
    private Bitmap bitmap;
    private float distSquare;
    private float[] downPoint;
    private int dragScrollMinDistSquare;
    private OnMatrixChangedListener listener;
    private Matrix matrix;
    private int mode;
    private Matrix savedMatrix;

    public interface OnMatrixChangedListener {
        void onMactrixChage(Matrix matrix);
    }

    public ScaledImageView(Context context) {
        super(context);
        init(context);
    }

    public ScaledImageView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        init(context);
    }

    public ScaledImageView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        init(context);
    }

    private void init(Context context) {
        this.dragScrollMinDistSquare = ViewConfiguration.get(context).getScaledTouchSlop();
        this.dragScrollMinDistSquare *= this.dragScrollMinDistSquare;
        setOnTouchListener(this);
    }

    public void setBitmap(Bitmap bitmap2) {
        this.bitmap = bitmap2;
        setImageBitmap(bitmap2);
        int[] iArr = {getWidth(), getHeight()};
        int[] iArr2 = {this.bitmap.getWidth(), this.bitmap.getHeight()};
        int[] fixRect = BitmapHelper.fixRect(iArr2, iArr);
        int[] iArr3 = {(iArr[0] - fixRect[0]) / 2, (iArr[1] - fixRect[1]) / 2};
        float[] fArr = {((float) fixRect[0]) / ((float) iArr2[0]), ((float) fixRect[1]) / ((float) iArr2[1])};
        this.matrix = new Matrix();
        this.matrix.set(getImageMatrix());
        this.matrix.postScale(fArr[0], fArr[1]);
        this.matrix.postTranslate((float) iArr3[0], (float) iArr3[1]);
        if (this.listener != null) {
            this.listener.onMactrixChage(this.matrix);
        }
        setImageMatrix(this.matrix);
    }

    public void setOnMatrixChangedListener(OnMatrixChangedListener onMatrixChangedListener) {
        this.listener = onMatrixChangedListener;
        if (this.matrix != null) {
            if (this.listener != null) {
                this.listener.onMactrixChage(this.matrix);
            }
            setImageMatrix(this.matrix);
        }
    }

    public boolean onTouch(View view, MotionEvent motionEvent) {
        try {
            switch (motionEvent.getAction()) {
                case 0:
                    this.matrix = new Matrix();
                    this.matrix.set(getImageMatrix());
                    this.savedMatrix = new Matrix();
                    this.savedMatrix.set(this.matrix);
                    this.downPoint = new float[]{motionEvent.getX(0), motionEvent.getY(0)};
                    this.mode = 1;
                    break;
                case 1:
                    if (this.listener != null) {
                        this.listener.onMactrixChage(this.matrix);
                    }
                    float x = motionEvent.getX(0) - this.downPoint[0];
                    float y = motionEvent.getY(0) - this.downPoint[1];
                    if (this.mode == 1 && (x * x) + (y * y) <= ((float) this.dragScrollMinDistSquare)) {
                        performClick();
                    }
                    this.mode = 0;
                    break;
                case 2:
                    if (this.mode != 1) {
                        if (this.mode != 2) {
                            if (this.mode == 3) {
                                float[] fArr = {motionEvent.getX(0), motionEvent.getY(0)};
                                float[] fArr2 = {motionEvent.getX(1), motionEvent.getY(1)};
                                float f = fArr[0] - fArr2[0];
                                float f2 = fArr[1] - fArr2[1];
                                float f3 = (f * f) + (f2 * f2);
                                this.matrix.set(this.savedMatrix);
                                float sqrt = (float) Math.sqrt((double) (f3 / this.distSquare));
                                float[] fArr3 = {(fArr[0] + fArr2[0]) / 2.0f, (fArr[1] + fArr2[1]) / 2.0f};
                                this.matrix.postScale(sqrt, sqrt, fArr3[0], fArr3[1]);
                                break;
                            }
                        } else {
                            float[] fArr4 = {motionEvent.getX(1), motionEvent.getY(1)};
                            this.matrix.set(this.savedMatrix);
                            this.matrix.postTranslate(fArr4[0] - this.downPoint[0], fArr4[1] - this.downPoint[1]);
                            break;
                        }
                    } else {
                        float[] fArr5 = {motionEvent.getX(0), motionEvent.getY(0)};
                        this.matrix.set(this.savedMatrix);
                        this.matrix.postTranslate(fArr5[0] - this.downPoint[0], fArr5[1] - this.downPoint[1]);
                        break;
                    }
                    break;
                case 5:
                    float[] fArr6 = {motionEvent.getX(0), motionEvent.getY(0)};
                    float[] fArr7 = {motionEvent.getX(1), motionEvent.getY(1)};
                    float f4 = fArr6[0] - fArr7[0];
                    float f5 = fArr6[1] - fArr7[1];
                    this.distSquare = (f5 * f5) + (f4 * f4);
                    this.mode = 3;
                    break;
                case 6:
                    this.downPoint = new float[]{motionEvent.getX(1), motionEvent.getY(1)};
                    this.savedMatrix.set(this.matrix);
                    this.mode = 2;
                    break;
                case 261:
                    float[] fArr8 = {motionEvent.getX(0), motionEvent.getY(0)};
                    float[] fArr9 = {motionEvent.getX(1), motionEvent.getY(1)};
                    float f6 = fArr8[0] - fArr9[0];
                    float f7 = fArr8[1] - fArr9[1];
                    this.distSquare = (f7 * f7) + (f6 * f6);
                    this.mode = 3;
                    break;
                case 262:
                    this.downPoint = new float[]{motionEvent.getX(0), motionEvent.getY(0)};
                    this.savedMatrix.set(this.matrix);
                    this.mode = 1;
                    break;
                default:
                    return false;
            }
            setImageMatrix(this.matrix);
        } catch (Throwable th) {
            MobLog.getInstance().w(th);
        }
        return true;
    }

    public void rotateLeft() {
        try {
            this.matrix = new Matrix();
            float[] fArr = {0.0f, 1.0f, 0.0f, -1.0f, 0.0f, 0.0f, 0.0f, 0.0f, 1.0f};
            this.matrix.setValues(fArr);
            Bitmap createBitmap = Bitmap.createBitmap(this.bitmap, 0, 0, this.bitmap.getWidth(), this.bitmap.getHeight(), this.matrix, true);
            if (createBitmap != null && !createBitmap.isRecycled()) {
                this.bitmap.recycle();
                this.bitmap = createBitmap;
            }
            setImageBitmap(this.bitmap);
            this.matrix = new Matrix();
            this.matrix.set(getImageMatrix());
            this.matrix.getValues(fArr);
            int[] iArr = {getWidth(), getHeight()};
            float[] fArr2 = {fArr[0] * ((float) this.bitmap.getWidth()), fArr[4] * ((float) this.bitmap.getHeight())};
            float[] fArr3 = {(((float) iArr[0]) - fArr2[0]) / 2.0f, (((float) iArr[1]) - fArr2[1]) / 2.0f};
            fArr[2] = fArr3[0];
            fArr[5] = fArr3[1];
            this.matrix.setValues(fArr);
            if (this.listener != null) {
                this.listener.onMactrixChage(this.matrix);
            }
            setImageMatrix(this.matrix);
        } catch (Throwable th) {
            MobLog.getInstance().w(th);
        }
    }

    public void rotateRight() {
        try {
            this.matrix = new Matrix();
            float[] fArr = {0.0f, -1.0f, 0.0f, 1.0f, 0.0f, 0.0f, 0.0f, 0.0f, 1.0f};
            this.matrix.setValues(fArr);
            Bitmap createBitmap = Bitmap.createBitmap(this.bitmap, 0, 0, this.bitmap.getWidth(), this.bitmap.getHeight(), this.matrix, true);
            if (createBitmap != null && !createBitmap.isRecycled()) {
                this.bitmap.recycle();
                this.bitmap = createBitmap;
            }
            setImageBitmap(this.bitmap);
            this.matrix = new Matrix();
            this.matrix.set(getImageMatrix());
            this.matrix.getValues(fArr);
            int[] iArr = {getWidth(), getHeight()};
            float[] fArr2 = {fArr[0] * ((float) this.bitmap.getWidth()), fArr[4] * ((float) this.bitmap.getHeight())};
            float[] fArr3 = {(((float) iArr[0]) - fArr2[0]) / 2.0f, (((float) iArr[1]) - fArr2[1]) / 2.0f};
            fArr[2] = fArr3[0];
            fArr[5] = fArr3[1];
            this.matrix.setValues(fArr);
            if (this.listener != null) {
                this.listener.onMactrixChage(this.matrix);
            }
            setImageMatrix(this.matrix);
        } catch (Throwable th) {
            MobLog.getInstance().w(th);
        }
    }

    public void zoomIn() {
        this.matrix = new Matrix();
        this.matrix.set(getImageMatrix());
        this.matrix.postScale(1.072f, 1.072f);
        if (this.listener != null) {
            this.listener.onMactrixChage(this.matrix);
        }
        setImageMatrix(this.matrix);
    }

    public void zoomOut() {
        this.matrix = new Matrix();
        this.matrix.set(getImageMatrix());
        this.matrix.postScale(0.933f, 0.933f);
        if (this.listener != null) {
            this.listener.onMactrixChage(this.matrix);
        }
        setImageMatrix(this.matrix);
    }

    public Bitmap getCropedBitmap(Rect rect) {
        try {
            Bitmap captureView = BitmapHelper.captureView(this, getWidth(), getHeight());
            if (captureView == null) {
                MobLog.getInstance().w("ivPhoto.getDrawingCache() returns null");
                return null;
            }
            Bitmap createBitmap = Bitmap.createBitmap(captureView, rect.left, rect.top, rect.width(), rect.height());
            captureView.recycle();
            return createBitmap;
        } catch (Throwable th) {
            MobLog.getInstance().w(th);
            return null;
        }
    }
}
