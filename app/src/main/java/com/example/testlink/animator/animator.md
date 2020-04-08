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
动画类型跟补间动画类型一样，但是补间动画只是改变view的显示，并没有改变view本身。比如一个view在(200,300)- (200,500)的区域做平移动画(补间)。动画结束后
肉眼看到view停留在(200,500)的地方。但实际上view本身还是在(200,300)的地方。这就是view本身(属性)没有改变的原因。属性动画将弥补这个缺点。   
objectAnimator属性动画，对应是ObjectAnimator类:
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
* pathData: 定义path规范，没有使用过(API 21及以上才有)
* propertyXName: pathData X方向动画的属性名称(API 21及以上才有)
* propertyYName: pathData Y方向动画的属性名称(API 21及以上才有)

前面的都是比较常用的属性，基本上都能用上，后面3个属性没有使用过。
代码实现方式(常用)，这个效果与xml文件实现是一样的。
```
 ObjectAnimator animator1 = ObjectAnimator.ofFloat(img, "rotation", 0, 360).setDuration(3000);
 animator1.setRepeatMode(ValueAnimator.REVERSE);
 animator1.setRepeatCount(-1);
 animator1.start();
```

animator属性动画对应是ValueAnimator类:  
先看xml实现方式:
```xml
<?xml version="1.0" encoding="utf-8"?>
<animator xmlns:android="http://schemas.android.com/apk/res/android"
    android:duration="1000"
    android:repeatMode="restart"
    android:repeatCount="infinite"
    android:startOffset="0"
    android:valueType="floatType"
    android:valueFrom="1.0"
    android:valueTo="2.0"/>
```
animator属性动画跟ObjectAnimator没有什么区别，属性上只缺少了propertyName这条(pathData相关的3条也没有，通常不用所以可以忽略)


4、(视图动画?让UI上的子控件呈动画形式显示)  
5、手势检测(GestureDetector)   
![](https://github.com/foty/TestLink/blob/master/app/src/main/java/com/example/testlink/animator/demo.gif)   
6、缩放手势检测(ScaleGestureDetector)

