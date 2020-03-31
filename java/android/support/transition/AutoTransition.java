package android.support.transition;

import android.content.Context;
import android.util.AttributeSet;

public class AutoTransition extends TransitionSet {
    public AutoTransition() {
        s();
    }

    public AutoTransition(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        s();
    }

    private void s() {
        a(1);
        a((Transition) new Fade(2)).a((Transition) new ChangeBounds()).a((Transition) new Fade(1));
    }
}
