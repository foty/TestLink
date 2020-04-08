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
https://blog.csdn.net/FooTyzZ/article/details/92066476     

3、属性动画  



4、(视图动画?让UI上的子控件呈动画形式显示)  
5、手势检测(GestureDetector)   
![](https://github.com/foty/TestLink/blob/master/app/src/main/java/com/example/testlink/animator/demo.gif)   
6、缩放手势检测(ScaleGestureDetector)

