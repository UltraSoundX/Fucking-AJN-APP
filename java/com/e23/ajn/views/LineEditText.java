package com.e23.ajn.views;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.support.v7.widget.AppCompatEditText;
import android.util.AttributeSet;
import com.e23.ajn.R;

public class LineEditText extends AppCompatEditText {
    private Paint a = new Paint();

    public LineEditText(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.a.setStyle(Style.STROKE);
        this.a.setColor(context.getResources().getColor(R.color.fragment_mine_line_a4a4a4));
    }

    public void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawLine(0.0f, (float) (getHeight() - 1), (float) (getWidth() - 1), (float) (getHeight() - 1), this.a);
    }
}
