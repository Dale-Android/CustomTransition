package com.djx.customtransition;

import android.animation.Animator;
import android.animation.ArgbEvaluator;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.transition.Transition;
import android.transition.TransitionValues;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class ChangeTextColor extends Transition {
    private static final String PROPNAME_TEXT_COLOR = "android:ChangeTextColor:textColor";

    public ChangeTextColor() {
    }

    public ChangeTextColor(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    private void captureValues(TransitionValues transitionValues) {
        if (transitionValues.view instanceof TextView) {
            int c = ((TextView) transitionValues.view).getCurrentTextColor();
            transitionValues.values.put(PROPNAME_TEXT_COLOR,
                    ((TextView) transitionValues.view).getCurrentTextColor());
        }
    }

    @Override
    public void captureStartValues(TransitionValues transitionValues) {
        captureValues(transitionValues);
    }

    @Override
    public void captureEndValues(TransitionValues transitionValues) {
        captureValues(transitionValues);
//        transitionValues.values.put(PROPNAME_TEXT_COLOR, 0xff000000);
    }

    @Override
    public Animator createAnimator(ViewGroup sceneRoot, TransitionValues startValues,
                                   TransitionValues endValues) {
        if (startValues == null || endValues == null) {
            return null;
        }
        final View view = endValues.view;
        if (view instanceof TextView) {
            TextView textView = (TextView) view;
            int start = (Integer) startValues.values.get(PROPNAME_TEXT_COLOR);
            int end = (Integer) endValues.values.get(PROPNAME_TEXT_COLOR);
            if (start != end) {
//                setDuration(3000);
                textView.setTextColor(end);
                return ObjectAnimator.ofObject(textView, "textColor",
                        new ArgbEvaluator(), start, end);
            }
        }
        return null;
    }

    @Override
    public String[] getTransitionProperties() {
        return new String[] {
            PROPNAME_TEXT_COLOR
        };
    }
}
