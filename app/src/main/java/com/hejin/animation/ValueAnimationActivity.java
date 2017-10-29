package com.hejin.animation;

import android.animation.AnimatorInflater;
import android.animation.AnimatorSet;
import android.animation.ArgbEvaluator;
import android.animation.IntEvaluator;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

public class ValueAnimationActivity extends AppCompatActivity {

    private ImageView mIvAnimation;
    private RelativeLayout mRvBg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_value_animation);

        mIvAnimation = (ImageView) findViewById(R.id.iv);
        mRvBg = (RelativeLayout) findViewById(R.id.rv_bg);
    }

    public void translation(View view) {
        ObjectAnimator.ofFloat(mIvAnimation, "translationY", 0, mIvAnimation.getHeight()).setDuration(1000).start();
    }

    public void background(View view) {
        ValueAnimator colorAnim = ObjectAnimator.ofInt(mRvBg, "backgroundColor", 0xffff8080, 0xff8080ff);
        colorAnim.setDuration(3000);
        colorAnim.setEvaluator(new ArgbEvaluator());/*设置估值器*/
        colorAnim.setRepeatCount(ValueAnimator.INFINITE);/*设置循环次数,这里是无限循环的*/
        colorAnim.setRepeatMode(ValueAnimator.REVERSE);/*设置循环模式-这里设置的是逆向模式*/
        colorAnim.start();
    }

    public void animatorSet(View view) {
        AnimatorSet set = new AnimatorSet();
        set.playTogether(
                ObjectAnimator.ofFloat(mIvAnimation, "rotationX", 0, 360),
                ObjectAnimator.ofFloat(mIvAnimation, "rotationY", 0, 180),
                ObjectAnimator.ofFloat(mIvAnimation, "rotation", 0, -90),
                ObjectAnimator.ofFloat(mIvAnimation, "translationX", 0, 90),
                ObjectAnimator.ofFloat(mIvAnimation, "translationY", 0, 90),
                ObjectAnimator.ofFloat(mIvAnimation, "scaleX", 1, 1.5f),
                ObjectAnimator.ofFloat(mIvAnimation, "scaleY", 1, 0.5f),
                ObjectAnimator.ofFloat(mIvAnimation, "alpha", 1, 0.25f, 1)
        );
        set.setDuration(500).start();
    }

    public void codeAnimatorSet(View view) {
        AnimatorSet set = (AnimatorSet) AnimatorInflater.loadAnimator(this, R.animator.animator_anim);
        set.setTarget(mIvAnimation);
        set.start();
    }

    public void ObjectAnimator(View view) {
        ViewWrapper wrapper = new ViewWrapper();
        wrapper.setTargetView(view);
        ObjectAnimator.ofInt(wrapper, "width", 0, 500).setDuration(1000).start();
    }

    public void ValueAnimator(final View view) {
        ValueAnimator valueAnimator = ValueAnimator.ofInt(0,500);
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            /*持有一个整形的估值器*/
            private IntEvaluator mIntEvaluator = new IntEvaluator();

            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                /*获取当前的动画值*/
                Integer animatedValue = (Integer) animation.getAnimatedValue();
                Log.e("done", "onAnimationUpdate: " + animatedValue);

                /*获取当前值占整个动画的比例,这个比例是0~1*/
                float animatedFraction = animation.getAnimatedFraction();

                view.getLayoutParams().width = mIntEvaluator.evaluate(animatedFraction, 0, 500);
                view.requestLayout();
            }
        });
        valueAnimator.setDuration(500);
        valueAnimator.start();
    }
}
