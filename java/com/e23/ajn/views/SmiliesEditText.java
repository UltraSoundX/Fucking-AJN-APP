package com.e23.ajn.views;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.AppCompatEditText;
import android.text.SpannableString;
import android.text.style.ImageSpan;
import android.util.AttributeSet;

public class SmiliesEditText extends AppCompatEditText {
    public SmiliesEditText(Context context) {
        super(context);
    }

    public SmiliesEditText(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    /* access modifiers changed from: protected */
    public void onDraw(Canvas canvas) {
        super.onDraw(canvas);
    }

    /* access modifiers changed from: protected */
    public void onMeasure(int i, int i2) {
        super.onMeasure(i, i2);
    }

    public void a(String str, int i) {
        SpannableString spannableString = new SpannableString(str);
        Drawable drawable = getResources().getDrawable(i);
        drawable.setBounds(0, 0, drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight());
        spannableString.setSpan(new ImageSpan(drawable, 1), 0, str.length(), 33);
        int selectionStart = getSelectionStart();
        int selectionEnd = getSelectionEnd();
        if (selectionStart == selectionEnd) {
            getText().insert(selectionStart, spannableString);
        } else {
            getText().replace(selectionStart, selectionEnd, spannableString);
        }
        setSelection(spannableString.length() + selectionStart);
    }
}
