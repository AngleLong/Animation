package com.hejin.animation;

import android.view.View;

/**
 * author :  贺金龙
 * create time : 2017/10/29 14:21
 * description : 包装类,为了向外面提供相应的get/set方法
 * instructions : 因为没有相应改变宽度和高度的方法,所以这里自己包装一个相应的get/set方法
 * version :
 */
public class ViewWrapper {
    private View mTargetView;


    /**
     * author :  贺金龙
     * create time : 2017/10/29 14:28
     * description : 传入相应的View,进行设置一些内容
     * instructions :
     * version :
     */
    public void setTargetView(View targetView) {
        mTargetView = targetView;
    }

    /**
     * author :  贺金龙
     * create time : 2017/10/29 14:30
     * description : 获取宽度的方法
     * instructions :
     * version :
     */
    public int getWidth() {
        return mTargetView.getLayoutParams().width;
    }

    /**
     * author :  贺金龙
     * create time : 2017/10/29 14:31
     * description : 设置宽度的方法
     * instructions : 设置相应的宽度
     * version :
     *
     * @param width 宽度
     */
    public void setWidth(int width) {
        mTargetView.getLayoutParams().width = width;
        /*重新绘制View的方法*/
        mTargetView.requestLayout();
    }
}
