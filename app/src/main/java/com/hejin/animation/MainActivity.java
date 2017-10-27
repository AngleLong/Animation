package com.hejin.animation;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

/**
 * author :  贺金龙
 * create time : 2017/10/27 11:55
 * description : 跳转动画
 * instructions :
 */
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void ViewAnimation(View view) {
        startActivity(new Intent(this, ViewAnimationActivity.class));
        overridePendingTransition(R.anim.enter_right_anim, R.anim.exit_left_anim);
    }

    public void ValueAnimation(View view) {
        startActivity(new Intent(this, ValueAnimationActivity.class));
        overridePendingTransition(R.anim.enter_right_anim, R.anim.exit_left_anim);
    }
}
