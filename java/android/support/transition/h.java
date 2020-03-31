package android.support.transition;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.graphics.Matrix;
import android.os.Build.VERSION;
import android.util.Log;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/* compiled from: ImageViewUtils */
class h {
    private static Method a;
    private static boolean b;

    static void a(ImageView imageView) {
        if (VERSION.SDK_INT < 21) {
            ScaleType scaleType = imageView.getScaleType();
            imageView.setTag(R.id.save_scale_type, scaleType);
            if (scaleType == ScaleType.MATRIX) {
                imageView.setTag(R.id.save_image_matrix, imageView.getImageMatrix());
            } else {
                imageView.setScaleType(ScaleType.MATRIX);
            }
            imageView.setImageMatrix(i.a);
        }
    }

    static void a(ImageView imageView, Matrix matrix) {
        if (VERSION.SDK_INT < 21) {
            imageView.setImageMatrix(matrix);
            return;
        }
        a();
        if (a != null) {
            try {
                a.invoke(imageView, new Object[]{matrix});
            } catch (IllegalAccessException e) {
            } catch (InvocationTargetException e2) {
                throw new RuntimeException(e2.getCause());
            }
        }
    }

    private static void a() {
        if (!b) {
            try {
                a = ImageView.class.getDeclaredMethod("animateTransform", new Class[]{Matrix.class});
                a.setAccessible(true);
            } catch (NoSuchMethodException e) {
                Log.i("ImageViewUtils", "Failed to retrieve animateTransform method", e);
            }
            b = true;
        }
    }

    static void a(final ImageView imageView, Animator animator) {
        if (VERSION.SDK_INT < 21) {
            animator.addListener(new AnimatorListenerAdapter() {
                public void onAnimationEnd(Animator animator) {
                    ScaleType scaleType = (ScaleType) imageView.getTag(R.id.save_scale_type);
                    imageView.setScaleType(scaleType);
                    imageView.setTag(R.id.save_scale_type, null);
                    if (scaleType == ScaleType.MATRIX) {
                        imageView.setImageMatrix((Matrix) imageView.getTag(R.id.save_image_matrix));
                        imageView.setTag(R.id.save_image_matrix, null);
                    }
                    animator.removeListener(this);
                }
            });
        }
    }
}
