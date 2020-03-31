package com.tendyron.liveness.impl;

public class LivenessInstance {
    private static LivenessInterface liveness = null;

    public static LivenessInterface getInstance() {
        if (liveness == null) {
            liveness = new a();
        }
        return liveness;
    }
}
