1、帧动画  
也称逐帧动画，由若干图片一次播放而形成动画效果。一般使用xml文件创建置于res/drawable文件夹下。
```xml
<?xml version="1.0" encoding="utf-8"?>
<animation-list xmlns:android="http://schemas.android.com/apk/res/android"
    android:oneshot="true">
    <item
        android:drawable="@drawable/ic_launcher_background"
        android:duration="1000" />
    <item
        android:drawable="@drawable/ic_launcher_background"
        android:duration="1000" />
    <item
        android:drawable="@drawable/ic_launcher_background"
        android:duration="1000" />
</animation-list>
```
如上。帧动画xml文件由 animation-list为根节点，每个item 节点为一帧。根节点的属性有:
* oneshot: 是否单次播放。false为循环播放。
item 的属性有:
* drawable: 资源图片。
* duration: 该帧播放持续时间。

在代码中使用动画文件:
```
ImageView image = findViewById(R.id.img);
image.setImageResource(R.drawable.my_animation);
AnimationDrawable animationDrawable = (AnimationDrawable) image.getDrawable();
animationDrawable.start();
//animationDrawable.stop();停止播放
```

java代码实现方式(): 
```
//代码定义、创建、执行动画
 AnimationDrawable animationDrawable = new AnimationDrawable();
 animationDrawable.addFrame(getResources().getDrawable(R.drawable.ic_launcher_background), 1000);
 animationDrawable.addFrame(getResources().getDrawable(R.drawable.ic_launcher_background), 1000);
 animationDrawable.addFrame(getResources().getDrawable(R.drawable.ic_launcher_background), 1000);
 animationDrawable.addFrame(getResources().getDrawable(R.drawable.ic_launcher_background), 1000);
 animationDrawable.addFrame(getResources().getDrawable(R.drawable.ic_launcher_background), 1000);
 animationDrawable.addFrame(getResources().getDrawable(R.drawable.ic_launcher_background), 1000);
 animationDrawable.setOneShot(true);
 image.setImageDrawable(animationDrawable);
 animationDrawable.start();
```
2、补间动画   
补间动画一般包括透明度、旋转、位移、缩放等几种，是由指定动画开始、动画结束关键点，中间部分的动画由系统完成。
xml实现方式一般存放在res/amin文件夹下面。  

https://blog.csdn.net/FooTyzZ/article/details/92066476     

3、属性动画  
动画类型跟补间动画类型一样，但是补间动画只改变view的显示，并没有改变view本身。比如一个view在(200,300)- (200,500)的区域做平移动画(补间)。动画结束后
肉眼看到view停留在(200,500)的地方,但实际上view本身还是在(200,300)的地方。这就是view本身(属性)没有改变的原因。属性动画将弥补这个缺点。属性动画又有objectAnimator
与animator两种实现,分别对应java类 ObjectAnimator 与 ValueAnimator。其中ObjectAnimator继承至ValueAnimator。   
objectAnimator实现属性动画，对应是ObjectAnimator类:
先看xml的实现方式(文件存放目录: res/animator，很少使用这种方式实现属性动画):
```xml
<?xml version="1.0" encoding="utf-8"?>
<objectAnimator xmlns:android="http://schemas.android.com/apk/res/android"
    android:propertyName="rotation"
    android:repeatCount="infinite"
    android:repeatMode="restart"
    android:duration="3000"
    android:valueType="floatType"
    android:valueFrom="0"
    android:valueTo="360"
    android:startOffset="0">
</objectAnimator>
```
以上是一个旋转动画的xml文件，加载xml代码:
```
   ImageView img = findViewById(R.id.img);
   ObjectAnimator animator = (ObjectAnimator) AnimatorInflater.loadAnimator(this, R.animator.my_object_animation);
   animator.setTarget(img);
   animator.start();
```
objectAnimator的属性介绍:
* propertyName: 属性名称
* repeatCount: 重复次数(infinite为无限次数)
* repeatMode: 重复模式(restart为重新开始，reverse为反转)
* duration: 动画播放时间
* valueType: 变化值类型，有floatType，intType,colorType,pathType(不同版本api可能没有这个候选值)
* valueFrom: 属性初始值
* valueTo: 属性结束值
* startOffset: 延迟(播放)时长
* interpolator: 差值器
* pathData: 定义path规范，没有使用过(API 21及以上)
* propertyXName: pathData X方向动画的属性名称(API 21及以上)
* propertyYName: pathData Y方向动画的属性名称(API 21及以上)

前面大部分都是比较常用的属性，基本上都会用上，后面3个属性没有使用过。   
另外一种实现方式: 代码实现方式(常用)，这个效果与xml文件实现是一样的,而且使用更加简单方便:
```
 ObjectAnimator animator1 = ObjectAnimator.ofFloat(img, "rotation", 0, 360).setDuration(3000);
 animator1.setRepeatMode(ValueAnimator.REVERSE);
 animator1.setRepeatCount(-1);
 animator1.start();
```

animator实现属性动画，对应是ValueAnimator类:  
先看xml实现方式:
```xml
<?xml version="1.0" encoding="utf-8"?>
<animator xmlns:android="http://schemas.android.com/apk/res/android"
    android:duration="3000"
    android:repeatMode="reverse"
    android:repeatCount="infinite"
    android:startOffset="0"
    android:valueType="floatType"
    android:valueFrom="0"
    android:valueTo="360" />
```
animator属性动画跟ObjectAnimator在属性条上没有很大的区别，属性数量上缺少了propertyName属性(pathData相关的3条也没有，通常不用所以可以忽略)。加载xml代码:
```
   ImageView img = findViewById(R.id.img);

   ValueAnimator animator = (ValueAnimator) AnimatorInflater.loadAnimator(this, R.animator.my_animator);
   //一定到手动添加这个监听器，并在回调方法设置需要改变的值。
   animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
       @Override
       public void onAnimationUpdate(ValueAnimator animation) {
           // 对应xml文件的 valueType 属性，如float就是对应floatType。
           float f = (float) animation.getAnimatedValue();
           img.setRotation(f); //手动赋值
       }
   });
   animator.setTarget(img);
   animator.start();
```
纯代码实现方式:
```
 PropertyValuesHolder holder =  PropertyValuesHolder.ofFloat("rotation",0,360);
 ValueAnimator animator1 = ValueAnimator.ofPropertyValuesHolder(holder)
                .setDuration(3000);
 animator1.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
     @Override
     public void onAnimationUpdate(ValueAnimator animation) {
         float f = (float) animation.getAnimatedValue();
         img.setRotation(f);
     }
 });
 animator1.setTarget(img);
 animator1.setRepeatMode(ValueAnimator.REVERSE);
 animator1.setRepeatCount(-1);
 animator1.start();
```
ObjectAnimator 与 ValueAnimator 相比较可以看出: 除了在设置作动画的属性设置上有区别外(ValueAnimator需要借助PropertyValuesHolder，ObjectAnimator
则可以直接在创建实例时传入动画属性，也可通过 ofPropertyValuesHolder()这个方法，借助PropertyValuesHolder来完成构建)。ObjectAnimator相对比较智能化，
使用ValueAnimator还需要手动添加监听器，手动赋值。   
通常情况下一个酷炫的动画效果都是由多种单一动画组合而成的，在xml中使用 set作为根标签，对应java类是AnimatorSet。   
xml实现：
```
<?xml version="1.0" encoding="utf-8"?>
<set xmlns:android="http://schemas.android.com/apk/res/android"
    android:ordering="together"> //此属性表示所有子动画一起开始,还有一个 sequentially:表示按照配置顺序依次执行。
    <objectAnimator
        android:duration="3000"
        android:propertyName="rotation"
        android:valueFrom="0"
        android:valueTo="360"
        android:valueType="floatType" />
    <objectAnimator
        android:duration="3000"
        android:propertyName="translationX"
        android:valueFrom="20"
        android:valueTo="300"
        android:valueType="floatType" />
</set>

   //加载xml代码。
   AnimatorSet set = (AnimatorSet) AnimatorInflater.loadAnimator(this, R.animator.my_set);
   set.setTarget(img);
   set.start();
```
纯代码实现更加简单(常用):
```
   AnimatorSet animatorSet = new AnimatorSet();
   animatorSet.playTogether(  //这是其中一种方式
           ObjectAnimator.ofFloat(img, "translationX", 20, 300).setDuration(3000),
           ObjectAnimator.ofFloat(img, "rotation", 0, 360).setDuration(3000));
   animatorSet.start();
```

属性动画下实现补间动画效果的propertyName值列表:

动画名称  | 属性名称 
:---:   | :---: 
透明度   | alpha
旋转(平面内，顺时针)     | rotation
旋转(x轴方向，前->后)   | rotationX
旋转(y轴方向，左->右)   | rotationY
平移(x轴方向)   | translationX
平移(y轴方向)   | translationY
放缩(x轴方向)   | scaleX
放缩(y轴方向)   | scaleY

除了上面的属性外，还可以对View的其他属性作动画如height、width、color等等。其实属性动画本质就是通过该属性的set，get方法改变其属性值，所以只要view的属性值
具有get，set方法都是可以做动画效果的。如果某些属性设置后没有效果则需要检查该属性是否具有get(),set()方法了；或者这个get、set方法是否真实改变该属性的值。解决
这种情况的办法就是封装一个包装类，包裹原始对象，对该属性设置set，get方法。最后将此包装类作为target传入。例子如下
```
public class DemoView1 {
    private View view;

    public DemoView1(View view){
        this.view = view;
    }

    public int getHeight() {
        return view.getLayoutParams().height;
    }

    public void setHeight(int height){
        view.getLayoutParams().height = height;
        view.requestLayout();
    }
}
```
使用这个包装类:
```
   DemoView1 view = new DemoView1(img);
   ObjectAnimator animator1 = ObjectAnimator.ofInt(view, "height", 120, 0).setDuration(3000);
   animator1.setRepeatMode(ValueAnimator.REVERSE);
   animator1.setRepeatCount(-1);
   animator1.start();
```
通过这样的包装方式，就可以对view的任何属性做动画效果了。

参考文章(详细，全面):
<https://www.jianshu.com/p/b117c974deaf]>  
<https://www.jianshu.com/p/bce3f1d4e1f2>  

4、(视图动画?让UI上的子控件呈动画形式显示)  
5、手势检测(GestureDetector)   
![](https://github.com/foty/TestLink/blob/master/app/src/main/java/com/example/testlink/animator/demo.gif)   
6、缩放手势检测(ScaleGestureDetector)

