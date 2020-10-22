<h3>view<h3>
 
关于构造函数:
1个参数的构造函数通常是通过代码new时调用的;
2个参数的构造函数通常写在布局文件中使用时调用，对应的属性通过attrs获取到;

一、画布(canvas)，画笔(paint)
* 画线
* 画矩形
* 画点(一系列的点)
* 画椭圆
* 画弧(扇形)
* 画路径(path),它分成两种表示。
>一种是添加图形，对应的方法都是以 path.addXXX()的格式。如果只添加一个图形，那么跟canvas.drawXXX()的结果是一样的。在调用path.addXXX()的时候，最后会有一个Direction dir的参数(也不是add全部图形
都有),这个参数表示绘制path的方向,取值有 Path.Direction.CW(顺时针)或Path.Direction.CCW(逆时针)。      
另一种是画线条,直线或曲线。它的方法格式为 path.xxxTo(): lineTo():画直线/rLineTo():从当前位置画增量位置,后面相同的一致如此;quadTo()
/rQuadTo():画二次贝塞曲线;cubicTo()/rCubicTo():画三次贝塞曲线;arcTo():画弧线，最后boolean型参数表示为当前线点是否移动
到弧线起点(true:强行移动至弧线起点，这会使得弧线与线原来位置终点产生一段空白距离); close(): 当前位置向当前图形的起点绘制一条直线.

* 颜色填充(drawColor())

二、颜色填充，色彩优化，特殊效果
* setShader(Shader shader): 设置颜色填充,Shader的实现子类有 LinearGradient(线性渐变颜色), RadialGradient(辐射渐变颜色), 
SweepGradient(扫描渐变,可以参考雷达扫描), BitmapShader(使用图片填充-圆形图片可以使用这种方式实现), ComposeShader(结合多个Shader使用,有多种使用模式);
* setColorFilter(ColorFilter cf):  设置颜色过滤, ColorFilter 有三个实现子类 LightingColorFilter,
 PorterDuffColorFilter 和 ColorMatrixColorFilter。主要就是对绘制的颜色深度处理。
* setXfermode(Xfermode x): 绘制的内容和Canvas的目标位置的内容结合计算出最终的颜色。Xfermoder 只有一个实现子类 PorterDuffXfermode。
* 掌握setXfermode()正常绘制使用的离屏缓存(1.Canvas.saveLayer() 2.View.setLayerType())。
* setStrokeCap(Paint.Cap cap): 线条头的三种形状(1.正常BUTT(平), 2.圆形ROUND ,3.方形SQUARE);
* 色彩优化  setDither(true): 设置图像抖动;
* 色彩优化  setFilterBitmap(true):  双线性过滤绘制;
* 设置图形的轮廓效果 setPathEffect(PathEffect p) ;
* 使用虚线绘制线条 setPathEffect(DashPathEffect d);
* 绘制阴影效果 setShadowLayer();
* 绘制模糊效果 setMaskFilter(MaskFilter m) MaskFilter有两种 BlurMaskFilter 和 EmbossMaskFilter;

三、文字绘制(主角是Paint)  
* drawTextOnPath(): 沿着一条path来绘制文字;
* StaticLayout :使用它的draw(Canvas c)方法绘制文字，可以自动换行;
* 设置文字字体大小(由Paint画笔调用);
* 设置文字字体风格(由Paint画笔调用);
* 设置粗体;
* 设置下划线，删除线，倾斜角度;

四、Canvas画布辅助操作
* 裁剪: clipRect()/clipPath()。第一个是裁剪成矩形内容，第二个则是path，有更多的裁剪形状。注意(裁剪前后不要忘记了保存
与恢复画布状态。)
* 平移 translate(float px,float py);
* 旋转 rotate(float degrees, float px, float py) :degrees为旋转角度，顺时针方向为正，px和py是旋转轴心。
* 放缩 scale(float sx, float sy, float px, float py): sx,sy是横向和纵向的放缩倍数;px,py是放缩的轴心。
* 错切 skew(float sx, float sy): 有点理解为扭曲的效果，sx和sy分别是x方向和y方向的错切系数。