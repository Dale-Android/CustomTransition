package com.djx.customtransition;

import android.app.SharedElementCallback;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.transition.ChangeBounds;
import android.transition.Fade;
import android.transition.Scene;
import android.transition.Transition;
import android.transition.TransitionManager;
import android.transition.TransitionSet;
import android.view.View;
import android.widget.TextView;

import java.util.List;

public class SecondActivity extends AppCompatActivity {//TODO 实在搞不懂，为什么前后的值是一样的？！

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

//        getWindow().setEnterTransition(new Fade());
//        getWindow().setSharedElementEnterTransition(new ChangeColor()
//                .addTarget(R.id.hello)
//                .addTarget("hello")
//                .setDuration(3000));
        getWindow().setSharedElementEnterTransition(getTransition());
        //TODO 我靠，添加这个回调监听才是关键啊，要不然不生效！！
        setEnterSharedElementCallback(new SharedElementCallback() {
            @Override
            public void onSharedElementStart(List<String> sharedElementNames, List<View> sharedElements, List<View> sharedElementSnapshots) {
                TextView target = (TextView) sharedElements.get(0);
                target.setTextColor(getColor(R.color.colorPrimary));
//                target.setBackgroundColor(getColor(R.color.bg_start));
                target.setBackground(new ColorDrawable(getColor(R.color.bg_start)));
            }

            @Override
            public void onSharedElementEnd(List<String> sharedElementNames, List<View> sharedElements, List<View> sharedElementSnapshots) {
                TextView target = (TextView) sharedElements.get(0);
                target.setTextColor(getColor(R.color.colorAccent));
//                target.setBackgroundColor(getColor(R.color.bg_end));
                target.setBackground(new ColorDrawable(getColor(R.color.bg_end)));
            }
        });
    }

    private Transition getTransition() {
        TransitionSet set = new TransitionSet();
//        set.setOrdering(TransitionSet.ORDERING_TOGETHER);

        ChangeTextColor textColor = new ChangeTextColor();
        textColor.addTarget(R.id.hello);
//        textColor.addTarget("hello");
        set.addTransition(textColor);

        //TODO 还有bounds变化，要不然也不生效！！
        ChangeBounds bounds = new ChangeBounds();
        bounds.addTarget(R.id.hello);
//        bounds.addTarget("hello");
        set.addTransition(bounds);

        ChangeColor bg = new ChangeColor();
        bg.addTarget(R.id.hello);
        set.addTransition(bg);

        return set;
    }
}
