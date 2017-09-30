package com.djx.customtransition;

import android.app.ActivityOptions;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.transition.ChangeTransform;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView mHello;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        (mHello = (TextView) findViewById(R.id.hello)).setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        startActivity(new Intent(this, SecondActivity.class),
                ActivityOptions.makeSceneTransitionAnimation(this, mHello, "hello").toBundle());
    }
}
