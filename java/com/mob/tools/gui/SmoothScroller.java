package com.mob.tools.gui;

import android.content.Context;
import android.view.animation.Interpolator;
import android.widget.Scroller;

public enum SmoothScroller {
    DEFAULT(new DefaultInterpolator()),
    OVER_SCROLL(new OverScrollInterpolator()),
    LINEAR_ACC(new LinearACCInterpolator()),
    LINEAR_DEC(new LinearDECInterpolator());
    
    private Interpolator interpolator;

    private static class DefaultInterpolator implements Interpolator {
        private float[] defaultInt;

        private DefaultInterpolator() {
            this.defaultInt = new float[]{0.0f, 3.0E-4f, 0.0012f, 0.0026f, 0.0047f, 0.0073f, 0.0104f, 0.014f, 0.0182f, 0.0228f, 0.028f, 0.0336f, 0.0397f, 0.0463f, 0.0533f, 0.0608f, 0.0686f, 0.0769f, 0.0855f, 0.0946f, 0.104f, 0.1138f, 0.1239f, 0.1344f, 0.1452f, 0.1563f, 0.1676f, 0.1793f, 0.1913f, 0.2035f, 0.216f, 0.2287f, 0.2417f, 0.2548f, 0.2682f, 0.2817f, 0.2955f, 0.3094f, 0.3235f, 0.3377f, 0.352f, 0.3665f, 0.381f, 0.3957f, 0.4104f, 0.4253f, 0.4401f, 0.4551f, 0.47f, 0.485f, 0.5f, 0.515f, 0.53f, 0.5449f, 0.5599f, 0.5748f, 0.5896f, 0.6043f, 0.619f, 0.6335f, 0.648f, 0.6623f, 0.6765f, 0.6906f, 0.7045f, 0.7183f, 0.7318f, 0.7452f, 0.7583f, 0.7713f, 0.784f, 0.7965f, 0.8087f, 0.8207f, 0.8324f, 0.8438f, 0.8548f, 0.8656f, 0.8761f, 0.8862f, 0.896f, 0.9054f, 0.9145f, 0.9231f, 0.9314f, 0.9392f, 0.9467f, 0.9537f, 0.9603f, 0.9664f, 0.972f, 0.9772f, 0.9818f, 0.986f, 0.9896f, 0.9927f, 0.9953f, 0.9974f, 0.9988f, 0.9997f, 1.0f};
        }

        public float getInterpolation(float f) {
            return this.defaultInt[(int) (100.0f * f)];
        }
    }

    private static class LinearACCInterpolator implements Interpolator {
        private LinearACCInterpolator() {
        }

        public float getInterpolation(float f) {
            return f * f;
        }
    }

    private static class LinearDECInterpolator implements Interpolator {
        private LinearDECInterpolator() {
        }

        public float getInterpolation(float f) {
            return (2.0f - f) * f;
        }
    }

    private static class OverScrollInterpolator implements Interpolator {
        private float[] defaultInt;

        private OverScrollInterpolator() {
            this.defaultInt = new float[]{0.0f, 3.0E-4f, 0.0012f, 0.0026f, 0.0047f, 0.0073f, 0.0104f, 0.014f, 0.0182f, 0.0228f, 0.028f, 0.0336f, 0.0397f, 0.0463f, 0.0533f, 0.0608f, 0.0686f, 0.0769f, 0.0855f, 0.0946f, 0.104f, 0.1138f, 0.1239f, 0.1344f, 0.1452f, 0.1563f, 0.1676f, 0.1793f, 0.1913f, 0.2035f, 0.216f, 0.2287f, 0.2417f, 0.2548f, 0.2682f, 0.2817f, 0.2955f, 0.3094f, 0.3235f, 0.3377f, 0.352f, 0.3665f, 0.381f, 0.3957f, 0.4104f, 0.4253f, 0.4401f, 0.4551f, 0.47f, 0.485f, 0.5f, 0.515f, 0.53f, 0.5449f, 0.5599f, 0.5748f, 0.5896f, 0.6043f, 0.619f, 0.6335f, 0.648f, 0.6623f, 0.6765f, 0.6906f, 0.7045f, 0.7183f, 0.7318f, 0.7452f, 0.7583f, 0.7713f, 0.784f, 0.7965f, 0.8087f, 0.8207f, 0.8324f, 0.8438f, 0.8548f, 0.8656f, 0.8761f, 0.8862f, 0.896f, 0.9054f, 0.9145f, 0.9231f, 0.9314f, 0.9392f, 0.9467f, 0.9537f, 0.9603f, 0.9664f, 0.972f, 0.9772f, 0.9818f, 0.986f, 0.9896f, 0.9927f, 0.9953f, 0.9974f, 0.9988f, 0.9997f, 1.0f};
        }

        public float getInterpolation(float f) {
            return this.defaultInt[(int) (100.0f * f)];
        }
    }

    private SmoothScroller(Interpolator interpolator2) {
        this.interpolator = interpolator2;
    }

    public Scroller getScroller(Context context) {
        return new Scroller(context, this.interpolator);
    }
}
