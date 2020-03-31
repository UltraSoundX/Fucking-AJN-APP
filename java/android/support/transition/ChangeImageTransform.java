package android.support.transition;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.animation.TypeEvaluator;
import android.content.Context;
import android.graphics.Matrix;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.util.Property;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import java.util.Map;

public class ChangeImageTransform extends Transition {
    private static final String[] a = {"android:changeImageTransform:matrix", "android:changeImageTransform:bounds"};
    private static final TypeEvaluator<Matrix> i = new TypeEvaluator<Matrix>() {
        /* renamed from: a */
        public Matrix evaluate(float f, Matrix matrix, Matrix matrix2) {
            return null;
        }
    };
    private static final Property<ImageView, Matrix> j = new Property<ImageView, Matrix>(Matrix.class, "animatedTransform") {
        /* renamed from: a */
        public void set(ImageView imageView, Matrix matrix) {
            h.a(imageView, matrix);
        }

        /* renamed from: a */
        public Matrix get(ImageView imageView) {
            return null;
        }
    };

    /* renamed from: android.support.transition.ChangeImageTransform$3 reason: invalid class name */
    static /* synthetic */ class AnonymousClass3 {
        static final /* synthetic */ int[] a = new int[ScaleType.values().length];

        static {
            try {
                a[ScaleType.FIT_XY.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                a[ScaleType.CENTER_CROP.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
        }
    }

    public ChangeImageTransform() {
    }

    public ChangeImageTransform(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    private void d(v vVar) {
        View view = vVar.b;
        if ((view instanceof ImageView) && view.getVisibility() == 0) {
            ImageView imageView = (ImageView) view;
            if (imageView.getDrawable() != null) {
                Map<String, Object> map = vVar.a;
                map.put("android:changeImageTransform:bounds", new Rect(view.getLeft(), view.getTop(), view.getRight(), view.getBottom()));
                map.put("android:changeImageTransform:matrix", b(imageView));
            }
        }
    }

    public void a(v vVar) {
        d(vVar);
    }

    public void b(v vVar) {
        d(vVar);
    }

    public String[] a() {
        return a;
    }

    public Animator a(ViewGroup viewGroup, v vVar, v vVar2) {
        ObjectAnimator objectAnimator;
        if (vVar == null || vVar2 == null) {
            return null;
        }
        Rect rect = (Rect) vVar.a.get("android:changeImageTransform:bounds");
        Rect rect2 = (Rect) vVar2.a.get("android:changeImageTransform:bounds");
        if (rect == null || rect2 == null) {
            return null;
        }
        Matrix matrix = (Matrix) vVar.a.get("android:changeImageTransform:matrix");
        Matrix matrix2 = (Matrix) vVar2.a.get("android:changeImageTransform:matrix");
        boolean z = (matrix == null && matrix2 == null) || (matrix != null && matrix.equals(matrix2));
        if (rect.equals(rect2) && z) {
            return null;
        }
        ImageView imageView = (ImageView) vVar2.b;
        Drawable drawable = imageView.getDrawable();
        int intrinsicWidth = drawable.getIntrinsicWidth();
        int intrinsicHeight = drawable.getIntrinsicHeight();
        h.a(imageView);
        if (intrinsicWidth == 0 || intrinsicHeight == 0) {
            objectAnimator = a(imageView);
        } else {
            if (matrix == null) {
                matrix = i.a;
            }
            if (matrix2 == null) {
                matrix2 = i.a;
            }
            j.set(imageView, matrix);
            objectAnimator = a(imageView, matrix, matrix2);
        }
        h.a(imageView, (Animator) objectAnimator);
        return objectAnimator;
    }

    private ObjectAnimator a(ImageView imageView) {
        return ObjectAnimator.ofObject(imageView, j, i, new Matrix[]{null, null});
    }

    private ObjectAnimator a(ImageView imageView, Matrix matrix, Matrix matrix2) {
        return ObjectAnimator.ofObject(imageView, j, new a(), new Matrix[]{matrix, matrix2});
    }

    private static Matrix b(ImageView imageView) {
        switch (AnonymousClass3.a[imageView.getScaleType().ordinal()]) {
            case 1:
                return c(imageView);
            case 2:
                return d(imageView);
            default:
                return new Matrix(imageView.getImageMatrix());
        }
    }

    private static Matrix c(ImageView imageView) {
        Drawable drawable = imageView.getDrawable();
        Matrix matrix = new Matrix();
        matrix.postScale(((float) imageView.getWidth()) / ((float) drawable.getIntrinsicWidth()), ((float) imageView.getHeight()) / ((float) drawable.getIntrinsicHeight()));
        return matrix;
    }

    private static Matrix d(ImageView imageView) {
        Drawable drawable = imageView.getDrawable();
        int intrinsicWidth = drawable.getIntrinsicWidth();
        int width = imageView.getWidth();
        float f = ((float) width) / ((float) intrinsicWidth);
        int intrinsicHeight = drawable.getIntrinsicHeight();
        int height = imageView.getHeight();
        float max = Math.max(f, ((float) height) / ((float) intrinsicHeight));
        float f2 = ((float) intrinsicHeight) * max;
        int round = Math.round((((float) width) - (((float) intrinsicWidth) * max)) / 2.0f);
        int round2 = Math.round((((float) height) - f2) / 2.0f);
        Matrix matrix = new Matrix();
        matrix.postScale(max, max);
        matrix.postTranslate((float) round, (float) round2);
        return matrix;
    }
}
