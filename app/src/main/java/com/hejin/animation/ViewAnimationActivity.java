package com.hejin.animation;

import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.LayoutAnimationController;
import android.widget.ImageView;
import android.widget.LinearLayout;

/**
 * author :  贺金龙
 * create time : 2017/10/27 11:47
 * description : View动画的演示
 * instructions : 里面包含所有的View动画
 * 1.TranslateAnimation(平移动画)
 * 2.ScaleAnimation(缩放动画)
 * 3.RotateAnimation(旋转动画)
 * 4.AlphaAnimation(透明度动画)
 * 5.LayoutAnimation(ViewGroup动画)
 */
public class ViewAnimationActivity extends AppCompatActivity {

    private ImageView mIvAnimation;
    private ImageView mIvFrame;
    private LinearLayout mLlBottom;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_animation);

        mIvAnimation = (ImageView) findViewById(R.id.iv);
        mIvFrame = (ImageView) findViewById(R.id.iv_frame);
        mLlBottom = (LinearLayout) findViewById(R.id.ll_bottom);
    }

    public void translate(View view) {
        Animation translateAnimation = AnimationUtils.loadAnimation(this, R.anim.translate_animation);
        mIvAnimation.startAnimation(translateAnimation);
    }

    public void scale(View view) {
        Animation translateAnimation = AnimationUtils.loadAnimation(this, R.anim.scale_animation);
        mIvAnimation.startAnimation(translateAnimation);
    }

    public void rotate(View view) {
        Animation translateAnimation = AnimationUtils.loadAnimation(this, R.anim.rotate_animation);
        mIvAnimation.startAnimation(translateAnimation);
    }

    public void alpha(View view) {
        Animation translateAnimation = AnimationUtils.loadAnimation(this, R.anim.alpha_animation);
        mIvAnimation.startAnimation(translateAnimation);
    }

    public void code(View view) {
        AlphaAnimation alphaAnimation = new AlphaAnimation(0, 1);
        alphaAnimation.setDuration(5000);
        mIvAnimation.setAnimation(alphaAnimation);
    }

    public void frame(View view) {
        mIvFrame.setBackgroundResource(R.drawable.drawable_animation);
        AnimationDrawable animationDrawable = (AnimationDrawable) mIvFrame.getBackground();
        animationDrawable.start();
    }

    public void layoutAnimation(View view) {
        Animation animation = AnimationUtils.loadAnimation(this, R.anim.translate_animation);
        LayoutAnimationController controller = new LayoutAnimationController(animation);
        controller.setDelay(0.5f);
        controller.setOrder(LayoutAnimationController.ORDER_NORMAL);
        mLlBottom.setLayoutAnimation(controller);
        mLlBottom.startAnimation(animation);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
        overridePendingTransition(R.anim.enter_left_anim,R.anim.exit_right_anim);
    }
}
