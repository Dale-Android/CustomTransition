package com.djx.customtransition;

import android.app.SharedElementCallback;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.transition.ChangeBounds;
import android.transition.Transition;
import android.transition.TransitionSet;
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
            }

            @Override
            public void onSharedElementEnd(List<String> sharedElementNames, List<View> sharedElements, List<View> sharedElementSnapshots) {
                TextView target = (TextView) sharedElements.get(0);
                target.setTextColor(getColor(R.color.colorAccent));
                target.setBackground(new ColorDrawable(getColor(R.color.bg_end)));
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

        return set;
    }
}
