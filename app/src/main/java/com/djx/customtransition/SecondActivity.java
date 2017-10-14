package com.djx.customtransition;

import android.app.SharedElementCallback;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.transition.ChangeBounds;
import android.transition.Transition;
import android.transition.TransitionSet;
import android.util.TypedValue;
import android.view.View;
import android.widget.TextView;

import java.util.List;

public class SecondActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        getWindow().setSharedElementEnterTransition(getTransition());
        setEnterSharedElementCallback(new SharedElementCallback() {
            @Override
            public void onSharedElementStart(List<String> sharedElementNames, List<View> sharedElements, List<View> sharedElementSnapshots) {
                TextView target = (TextView) sharedElements.get(0);
                target.setTextColor(getColor(R.color.colorPrimary));
                target.setBackground(new ColorDrawable(getColor(R.color.bg_start)));
                target.setTextSize(TypedValue.COMPLEX_UNIT_PX, getResources().getDimensionPixelSize(R.dimen.start_text_size));
            }

            @Override
            public void onSharedElementEnd(List<String> sharedElementNames, List<View> sharedElements, List<View> sharedElementSnapshots) {
                TextView target = (TextView) sharedElements.get(0);

                // Record the TextView's old width/height.
                int oldWidth = target.getMeasuredWidth();
                int oldHeight = target.getMeasuredHeight();

                target.setTextColor(getColor(R.color.colorAccent));
                target.setBackground(new ColorDrawable(getColor(R.color.bg_end)));
                target.setTextSize(TypedValue.COMPLEX_UNIT_PX, getResources().getDimensionPixelSize(R.dimen.end_text_size));

                // Re-measure the TextView (since the text size has changed).
                int widthSpec = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED);
                int heightSpec = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED);
                target.measure(widthSpec, heightSpec);

                // Record the TextView's new width/height.
                int newWidth = target.getMeasuredWidth();
                int newHeight = target.getMeasuredHeight();

                // Set the new bounds
                int widthDiff = newWidth - oldWidth;
                int heightDiff = newHeight - oldHeight;
                target.layout(target.getLeft(), target.getTop(),
                        target.getRight() + widthDiff, target.getBottom() + heightDiff);
            }
        });
    }

    private Transition getTransition() {
        TransitionSet set = new TransitionSet();

        ChangeBounds bounds = new ChangeBounds();
        bounds.addTarget(R.id.hello);
        set.addTransition(bounds);

        ChangeColor bg = new ChangeColor();
        bg.addTarget(R.id.hello);
        set.addTransition(bg);

        ChangeTextColor textColor = new ChangeTextColor();
        textColor.addTarget(R.id.hello);
        set.addTransition(textColor);

        ChangeTextSize textSize = new ChangeTextSize();
        textSize.addTarget(R.id.hello);
        set.addTransition(textSize);

        return set;
    }
}
