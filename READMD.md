#Android动画分类
##1.View动画
###1.1 View动画有四种变换效果(对应这Animation的四个子类):
- TranslateAnimation(平移动画)
- ScaleAnimation(缩放动画)
- RotateAnimation(旋转动画)
- AlphaAnimation(透明度动画)

###1.2 View动画的使用
- 1.2.1 XML方式使用动画
- 1.2.2代码方式使用动画

####1.2.1 XML方式使用动画
> 这里首先要说明一下创建的位置(一定是在res下面创建anim文件夹) : **res/anim/XXX.xml**
##### 1.2.1.1整体的属性
- android:duration 动画执行时间
- android:fillAfter 动画结束后View是否停留在结束为止
- android:interpolator 插值器默认为(加速减速插值器)@animation:anim/accelerate_decelerate_interpolator
- android:shareInterpolator 是否共享插值器
```
<set xmlns:android="http://schemas.android.com/apk/res/android"
     android:interpolator="@android:anim/accelerate_decelerate_interpolator"
     android:shareInterpolator="true"/>
```
这个后面在仔细说....

##### 1.2.1.2平移动画(对应TranslateAnimation类)
- fromXDelta 表示x的起始值,比如0,0%p
- fromYDelta 表示y的起始值,比如100,100%p
- toXDelta 表示x的结束值
- toYDelta 表示y的结束值
```
<?xml version="1.0" encoding="utf-8"?>
<set xmlns:android="http://schemas.android.com/apk/res/android">

    <translate
        android:fromXDelta="0"
        android:fromYDelta="0"
        android:toXDelta="0"
        android:toYDelta="0"
        />
</set>
```
##### 1.2.1.3缩放动画(对应ScaleAnimation类)
- android:fromXScale x水平方向缩放起始值,比如:0
- android:fromYScale y水平方向缩放起始值,比如:0
- android:toXScale x竖直方向缩放起始值
- android:toYScale y竖直方向缩放起始值
- android:toXScale 缩放轴点的X坐标
- android:toYScale 缩放轴点的Y坐标
```
<set xmlns:android="http://schemas.android.com/apk/res/android"
     android:fillAfter="false"
     android:interpolator="@android:anim/accelerate_decelerate_interpolator"
     android:shareInterpolator="true">

    <scale
        android:fromXScale="0"
        android:fromYScale="0"
        android:pivotX="0.5"
        android:pivotY="0.5"
        android:toXScale="1"
        android:toYScale="1"
        />
</set>
```
> 这里解释一下上面那段代码,就是从没有(因为是从0开始)到正常大小(这里的正常大小指的是原始尺寸),中心点要是"%"的话是指相对于自身控件的百分比但是如果要是"0.5"的话不代表"50%"而是指的是实际的像素点

##### 1.2.1.4 旋转动画(对应RotateAnimation类)
- android:fromDegrees 旋转的开始角度
- android:toDegrees 旋转的结束角度
- android:pivotX 旋转的x轴点
- android:pivotY 旋转的y轴点
```
<set xmlns:android="http://schemas.android.com/apk/res/android"
     android:duration="5000"
     android:fillAfter="false"
     android:interpolator="@android:anim/accelerate_decelerate_interpolator"
     android:shareInterpolator="true">

    <rotate
        android:fromDegrees="0"
        android:pivotX="0.5"
        android:pivotY="0.5"
        android:toDegrees="359"/>
</set>
```
##### 1.2.1.5 透明度动画 (对应AlphaAnimation)
- android:fromAlpha 透明度的起始值
- android:toAlpha 透明度的结束值

```
<set xmlns:android="http://schemas.android.com/apk/res/android"
     android:duration="5000"
     android:fillAfter="false"
     android:interpolator="@android:anim/accelerate_decelerate_interpolator"
     android:shareInterpolator="true">

    <alpha
        android:fromAlpha="0.5"
        android:toAlpha="1"/>
</set>
```

##### 1.2.1.6使用的代码:
```
        Animation translateAnimation = AnimationUtils.loadAnimation(this,R.anim.translate_animation);
        mIvAnimation.startAnimation(translateAnimation);
```

#### 1.2.2 代码使用
```
        AlphaAnimation alphaAnimation = new AlphaAnimation(0,1);
        alphaAnimation.setDuration(500);
        mIvAnimation.setAnimation(alphaAnimation);
```
这里就举个例子其他的都差不多,看一眼传入得参数就好了...

##1.3 帧动画(对应AnimationDrawable类)

> 帧动画是顺序播放一组预先定义好的动画,类似于电影播放.不同于View动画,系统提供另外一个类AnimationDrawable来使用帧动画,这里注意一点就是存放帧动画XML的位置res/drawable下面!!!
###1.3.1 XML中的代码
**这里有一个属性说明一下**
- android:oneshot 是否只执行一次
```
<?xml version="1.0" encoding="utf-8"?>
<animation-list xmlns:android="http://schemas.android.com/apk/res/android"
                android:oneshot="true">

    <item
        android:drawable="@drawable/icon1"
        android:duration="500"/>
    <item
        android:drawable="@drawable/icon2"
        android:duration="500"/>
    <item
        android:drawable="@drawable/icon3"
        android:duration="500"/>
    <item
        android:drawable="@drawable/icon4"
        android:duration="500"/>
    <item
        android:drawable="@drawable/icon5"
        android:duration="500"/>
    <item
        android:drawable="@drawable/icon6"
        android:duration="500"/>
</animation-list>
```
### 1.3.2 代码使用
其实就是把上面定义的动画的XML当成背景设置到相应的ImageView上去,在通过getBackground取出来强转成AnimationDrawable调用start开启动画
```
        mIvFrame.setBackgroundResource(R.drawable.drawable_animation);
        AnimationDrawable animationDrawable = (AnimationDrawable) mIvFrame.getBackground();
        animationDrawable.start();
```

## 1.4 View动画的特殊使用场景
###1.4.1 特殊使用场景
- ViewGroup上面使用View动画
- Activity之间的切换效果动画
####1.4.1.1 LayoutAnimation 为ViewGroup设置制定动画
> 这个的使用场景一般是直接作用在ViewGroup 常用在ListView或者RecycleView的条目上,从而为每个子View展示一些出场效果
- (1) 定义LayoutAnimation(位置res/anim中)
    - android:delay 表示动画的时间延迟,比如子元素入场动画的时间周期为300ms,那么0.5表示每个子元素都需要延迟150ms才能播放入场动画
    - android:animationOrder 表示动画元素的顺序,有三种顺序选项:normal,reverse,random在其中normal表示顺序显示;reverse表示逆向显示;random则表示随机播放入场动画
    - android:animation 为元素指定具体的入场的动画
```
<?xml version="1.0" encoding="utf-8"?>
<layoutAnimation xmlns:android="http://schemas.android.com/apk/res/android"
                 android:animation="@anim/translate_animation"
                 android:animationOrder="normal"
                 android:delay="0.5"/>
```
- (2)为ViewGroup制定android:layoutAnimation属性:android:layoutAnimation="@anim/XXX".对于ListView来说,这样ListView的item就具有出场动画了,但是这种方式适用于所有的ViewGroup
```
android:layoutAnimation="@anim/item_layoutanimation"
```
就是在相应的ViewGroup控件中加上上面的属性标签就可以了
- (3)代码设定LayoutAnimation(通过LayoutAnimationController实现)
```
        Animation animation = AnimationUtils.loadAnimation(this, R.anim.XXX);
        LayoutAnimationController controller = new LayoutAnimationController(animation);
        controller.setDelay(0.5f);
        controller.setOrder(LayoutAnimationController.ORDER_NORMAL);
        View.setLayoutAnimation(controller);
        View.startAnimation(animation);//这句是开始动画用的
```
这里注意一点就是XXX代表的是普通的动感,而不是layoutAnimation为根节点的动画!!!
####1.4.1.2 Activity的切换效果
> Activity有默认的切换效果,但是这个效果我们是可以自定义的,主要用到的是**overridePendingTransition(int enterAnim, int exitAnim);**这个方法要在startActivity(Intent)或者finish()之后调用才会生效

动画设置
- enterAnim Activity被打开时,所需要的资源ID

- exitAnim Activity被暂停时,所需要都的资源ID

> Fragment可也可以添加切换动画,通过FragmentTransaction中的setCustomAnimations()方法来天机啊添加切换动画,这个切换动画需要时View动画,也可以是属性动画

##2.属性动画
> 属性动画是API11添加的新特性,和View动画不同,它对作用的对象进行了扩展,属性动画可以对任何对象做动画,甚至还可以没有对象

###2.1属性动画的使用
> 属性动画的默认时间间隔是300ms,默认的帧率是10ms/帧

####2.1.1属性动画的常用类
- ValueAnimator 
- ObjectAnimator 继承ValueAnimator
- AnimatorSet 动画集合

####2.1.2举例说明
- 改变一个对象的translationY属性,让其沿着Y轴平移一段距离
```
ObjectAnimator.ofFloat(mIvAnimation, "translationY", mIvAnimation.getHeight()).setDuration(1000).start();
```
- 改变一个对象的背景色属性
```
        ValueAnimator colorAnim = ObjectAnimator.ofInt(mRvBg, "backgroundColor", 0xffff8080, 0xff8080ff);
        colorAnim.setDuration(3000);
        colorAnim.setEvaluator(new ArgbEvaluator());/*设置估值器*/
        colorAnim.setRepeatCount(ValueAnimator.INFINITE);/*设置循环次数,这里是无限循环的*/
        colorAnim.setRepeatMode(ValueAnimator.REVERSE);/*设置循环模式-这里设置的是逆向模式*/
        colorAnim.start();
```
- 动画集合
```
        AnimatorSet set = new AnimatorSet();
        set.playTogether(
                ObjectAnimator.ofFloat(mIvAnimation, "rotationX", 0, 360),//X轴旋转
                ObjectAnimator.ofFloat(mIvAnimation, "rotationY", 0, 180),//Y轴旋转
                ObjectAnimator.ofFloat(mIvAnimation, "rotation", 0, -90),//旋转
                ObjectAnimator.ofFloat(mIvAnimation, "translationX", 0, 90),//x轴平移
                ObjectAnimator.ofFloat(mIvAnimation, "translationY", 0, 90),//y轴平移
                ObjectAnimator.ofFloat(mIvAnimation, "scaleX", 1, 1.5f),//X轴缩放
                ObjectAnimator.ofFloat(mIvAnimation, "scaleY", 1, 0.5f),//Y轴缩放
                ObjectAnimator.ofFloat(mIvAnimation, "alpha", 1, 0.25f, 1)//渐变
        );
        set.setDuration(500).start();
```
####2.1.2 XML使用属性动画(位置res/animator)
- XML中的代码

    > 属性动画的各个参数都比较好理解,在XML中可以定义ValueAnimation,ObjectAnimator以及AnimatorSet,其中AnimatorSet对应\<Set\>标签,ValueAnimator对应\<animator\>标签,ObjectAnimator对应\<objectAnimator\>标签
    
    - \<Set\>标签的详细属性   
        - android:ordering动画的播放顺序,有两个参数可选;1.together代表所有子动画同时执行;2.sequentially代表所有子动画按照先后顺序执行.默认是together
    
    - \<objectAnimator\>和\<animator\>标签的详细属性
        - android:propertyName 表示属性动画的作用对象的属性名称
        - android:duration 表示动画的时长
        - android:valueFrom 表示属性动画的初始值
        - android:valueTo 表示属性的结束值
        - android:startOffset 表示动画的延迟时间,当动画开始后,需要延迟多少毫秒才能真正播放动画
        - android:repeatCount 表示动画的重复次数
        - android:repeatMode 表示动画的重复模式
        - android:valueType 表示Android:propertyName所制定的属性的类型,有"intType"和"floatType"两个选项,分别代表整型和浮点型,如果android:propertyName所指定的属性表示的是颜色的话,那么就不需要指定android:valueType系统会自动对颜色类型进行属性处理
    
这做下说明:
- android:repeatCount 表示动画循环的次数 默认是0 当为-1的时候表示无线循环
- android:repeatMode 表示动画的循环模式 逆向重复的时候是指当第一次播放完成的时候,第二次会倒着播放动画,第三次在从头开始播放动画,第四次在倒着播放,以此循环
```
<?xml version="1.0" encoding="utf-8"?>
<set xmlns:android="http://schemas.android.com/apk/res/android"
     android:ordering="together">

    <objectAnimator
        android:duration="300"
        android:propertyName="x"
        android:valueTo="200"
        android:valueType="intType"/>

    <objectAnimator
        android:duration="300"
        android:propertyName="y"
        android:valueTo="200"
        android:valueType="intType"/>
</set>
```
- 代码中使用上面的xml
```
        AnimatorSet set = (AnimatorSet) AnimatorInflater.loadAnimator(this, R.animator.animator_anim);
        set.setTarget(mIvAnimation);
        set.start();
```
###2.2 插值器和估值器

- 插值器(Interpolator)
    - TimeInterpolator 时间插值器
    - LinearInterpolator 线性插值器(匀速动画)
    - AccelerateDecelerateInterpolator 加速减速插值器(动画两头慢中间快)
    - DecelerateInterpolator 减速插值器(动画越来越慢)

- 估值器(TypeEvaluator)
    - IntEvaluator (针对帧数属性)
    - FloatEvaluator (针对浮点数属性)
    - ArgbEvaluator (针对Color属性)

###2.3 属性动画的监听器
> 监听动画的播放过程,主要有如下两个接口 **AnimatorUpdateListener**,**AnimatorListener**

- AnimatorListener 的定义如下:
```
 public static interface AnimatorListener {
        void onAnimationStart(Animator animation);
        void onAnimationEnd(Animator animation);
        void onAnimationCancel(Animator animation);
        void onAnimationRepeat(Animator animation);
    }
```
- AnimatorUpdateListener 的定义如下:(它会监听整个动画过程,因为动画是由许多帧组成的,所以每播放一帧就会回调一次)
```
public static interface AnimatorUpdateListener {
        void onAnimationUpdate(ValueAnimator animation);
    }
```