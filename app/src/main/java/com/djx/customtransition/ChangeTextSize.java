package com.djx.customtransition;

import android.animation.Animator;
import android.animation.ValueAnimator;
import android.transition.Transition;
import android.transition.TransitionValues;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class ChangeTextSize extends Transition {

    private static final String PROPNAME_TEXT_SIZE = "com.djx.customtransition:ChangeTextSize:textSize";

    private void captureValues(TransitionValues transitionValues) {
        if (transitionValues.view instanceof TextView) {
            transitionValues.values.put(PROPNAME_TEXT_SIZE,
                    ((TextView) transitionValues.view).getTextSize());
        }
    }

    @Override
    public void captureStartValues(TransitionValues transitionValues) {
        captureValues(transitionValues);
    }

    @Override
    public void captureEndValues(TransitionValues transitionValues) {
        captureValues(transitionValues);
    }

    @Override
    public Animator createAnimator(ViewGroup sceneRoot, TransitionValues startValues, TransitionValues endValues) {
        if (startValues == null || endValues == null) {
            return null;
        }
        final View view = endValues.view;
        if (view instanceof TextView) {
            final TextView textView = (TextView) view;
            float start = (Float) startValues.values.get(PROPNAME_TEXT_SIZE);
            float end = (Float) endValues.values.get(PROPNAME_TEXT_SIZE);
            if (start != end) {
                ValueAnimator animator = ValueAnimator.ofFloat(start, end);
                animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                    @Override
                    public void onAnimationUpdate(ValueAnimator animation) {
                        Object value = animation.getAnimatedValue();
                        if (null != value) {
                            textView.setTextSize(TypedValue.COMPLEX_UNIT_PX, (Float) value);
                        }
                    }
                });
                return animator;
            }
        }
        return null;
    }
}
