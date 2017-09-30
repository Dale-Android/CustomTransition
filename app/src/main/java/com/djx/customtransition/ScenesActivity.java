package com.djx.customtransition;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.transition.ChangeBounds;
import android.transition.Explode;
import android.transition.Fade;
import android.transition.Scene;
import android.transition.Transition;
import android.transition.TransitionManager;
import android.transition.TransitionSet;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;

public class ScenesActivity extends AppCompatActivity implements View.OnClickListener {

    private FrameLayout mSceneRoot;
    private Scene mAScene;
    private Scene mAnotherScene;
    private int mCur = 0;

    private LinearLayout mWithoutRoot;
    private ImageView mRight;
    private boolean mIsRemoved = false;
    private Transition mWithoutTransition;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scenes);

        findViewById(R.id.start_scene).setOnClickListener(this);
        findViewById(R.id.start_without).setOnClickListener(this);

        mSceneRoot = (FrameLayout) findViewById(R.id.scene_root);
        mAScene = Scene.getSceneForLayout(mSceneRoot, R.layout.a_scene, this);
        mAnotherScene = Scene.getSceneForLayout(mSceneRoot, R.layout.another_scene, this);

        mWithoutRoot = (LinearLayout) findViewById(R.id.without_root);
        mRight = (ImageView) mWithoutRoot.findViewById(R.id.right);
        mWithoutTransition = new TransitionSet().addTransition(new ChangeBounds()).addTransition(new Fade());
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.start_scene:
                if ((++mCur) % 2 == 1)
                    TransitionManager.go(mAnotherScene, new ChangeBounds());
                else
                    TransitionManager.go(mAScene, new ChangeBounds());
                break;
            case R.id.start_without:
                if (!mIsRemoved) {
                    TransitionManager.beginDelayedTransition(mWithoutRoot, mWithoutTransition);
                    mWithoutRoot.removeView(mRight);
                    mIsRemoved = true;
                } else {
                    TransitionManager.beginDelayedTransition(mWithoutRoot, mWithoutTransition);
                    mWithoutRoot.addView(mRight);
                    mIsRemoved = false;
                }
                break;
        }

    }
}
