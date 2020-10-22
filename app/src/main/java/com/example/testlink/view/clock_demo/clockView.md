原项目地址: <https://github.com/chenzongwen/MiClockView>
本篇主要是参照原项目记录自己动手实践的过程，仅当作学习练习，需要看原作者项目的朋友请移步[原项目地址](https://github.com/chenzongwen/MiClockView)。

先看原项目的效果图:

自己实现的效果图:

OK开始编码流程。首先去分析一下这个view的组成部分。有外圈时针圈，文字，内圈的秒针刻度，分针，时针，秒针，
动画效果，秒针移动时刻度盘的渐变效果。所以暂定这个view需要完成的步骤如下:
* 初始化
* 外圈时针圈，包括文字
* 秒针圈(刻度圈)
* 秒针，分针，时针
* 动画效果
* 秒针移动的颜色渐变效果
* 其他完善
     
<b>初始化</b>：每个自定义都是要继承view或者viewGroup,实现它的基本方法。
```java
public class ClockView extends View {
    public ClockView(Context context) {
        this(context, null, 0);
    }

    public ClockView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ClockView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }
    
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        //super.onMeasure(widthMeasureSpec, heightMeasureSpec);
         setMeasuredDimension(measureDimension(widthMeasureSpec), measureDimension(heightMeasureSpec));
    }
    
     @Override
    protected void onDraw(Canvas canvas) {
    }
    private int measureDimension(int measureSpec) {
        int defaultSize = 800;
        int model = MeasureSpec.getMode(measureSpec);
        int size = MeasureSpec.getSize(measureSpec);
        switch (model) {
            case MeasureSpec.EXACTLY:
                return size;
            case MeasureSpec.AT_MOST:
                return Math.min(size, defaultSize);
            case MeasureSpec.UNSPECIFIED:
            default:
                return defaultSize;
        }
    }
}
```
关于view测量方法的重写大多数情况下都是可以使用这套写法。先判断当前view的测量模式，
根据模式重新定义view的大小。只是为了简便使用完全可以固定一个大小设置
```
 setMeasuredDimension(500,500);
```
当然使用默认实现也是没有任何问题。

<b>绘制外圈，文字</b>
时针圈其实就是四条弧度，每条弧度中间有一定的间隔，刚好容下一个文字。为了美观一点，定义每条弧跨越度数为80度，每条弧度留出10个弧度
来放下文字。在onDraw 方法里:
```
    private void init() {
        //圆弧大小
        mRectf = new RectF();
        mRectf.set(200, 200, 500, 500);
        //画笔初始化
        mPaint = new Paint();
        mPaint.setColor(Color.parseColor("#ff22ff"));
        mPaint.setAntiAlias(true);
        mPaint.setStrokeWidth(4); 
        mPaint.setStyle(Paint.Style.STROKE);//设置绘制类型：填充/描边
    }
    
     @Override
    protected void onDraw(Canvas canvas) {
        //画弧     
        for (int i = 0; i < 4; i++) {
            canvas.drawArc(mRectf, 5 + i * 90, 80, false, mPaint);
        }
    }
```
上面的 ``mRectf``就是一个``RectF`` 对象，圈定了绘制弧度的大小，一个半径为150像素的圆。写demo为了简单
就定义成一个固定值。根据实际情况设置动态或者外部设置。之后运行查看一下效果先:(ps:记得在xml文件中
引用先，不然什么也看不到)

图片1

绘制文字.同样是onDraw方法里面，但是换一只画笔(别忘记对画笔初始化):
```
    private void init() {
       ....
       
       //文字
        mTextPaint = new Paint();
        mTextPaint.setColor(Color.parseColor("#ff22ff"));
        mTextPaint.setAntiAlias(true);
        mTextPaint.setTextSize(24);
        mTextPaint.setTextAlign(Paint.Align.CENTER);
        mTextPaint.setStyle(Paint.Style.STROKE);//设置绘制类型：填充/描边
    }
    
    protected void onDraw(Canvas canvas) {
        //画弧     
        ....
       
        //绘制文字
        canvas.drawText("3", 500, 200 + 300 / 2, mTextPaint);
        canvas.drawText("6", 200 + 300 / 2, 500, mTextPaint);
        canvas.drawText("9", 200, 200 + 300 / 2, mTextPaint);
        canvas.drawText("12", 200 + 300 / 2, 200, mTextPaint);
    }
```
展示效果:

图片2

这个字没有在中间的位置，很影响美观，要分别对文字做偏移处理，往下边移动一点(画布中以左上角为坐标原点，所以在Y轴方向偏移都是作加法计算)，
具体就是这个文字高度的一半。可以通过使用画笔的getTextBounds api来获取绘制文字的边界值。具体代码:
```
        //绘制文字
        Rect boundRf = new Rect();
        mTextPaint.getTextBounds("12", 0, 2, boundRf);
        int textH = boundRf.height() / 2;
        canvas.drawText("3", 500, 200 + 300 / 2 + textH, mTextPaint);
        canvas.drawText("6", 200 + 300 / 2, 500 + textH, mTextPaint);
        canvas.drawText("9", 200, 200 + 300 / 2 + textH, mTextPaint);
        canvas.drawText("12", 200 + 300 / 2, 200 + textH, mTextPaint);
```
效果图:

图片3

ok文字整体也居中了。
<b>内圈(刻度圈)</b>
由众多的线条围成的一个圆环。假设有60条弧度(对应60秒)，那么一条弧度跨越的度数为6度。做法之一是根据弧度起始位置，移动画笔画弧。
如0~6，7~12...,一直到360这样。另一种做法就是固定画笔位置，移动画布。每次画弧的位置都是同一个，但是每画完一次就旋转画布，最后
恢复画布开始状态。
```
        //刻度线
         canvas.save();
        for (int i = 0; i < 60; i++) {
            //刻度线长度为20
            canvas.drawLine(200 + 300 / 2, 200 + 20, 200 + 300 / 2, 200 + 40, mRulingPaint);
            //圆心为中心点，旋转画布
            canvas.rotate(6f, 200 + 300 / 2, 200 + 300 / 2);
        }
        canvas.restore();
```
使用不一样的画笔别忘了初始化，在init方法添加：
```
        //刻度线
        mRulingPaint = new Paint();
        mRulingPaint.setColor(Color.parseColor("#FFA500"));
        mRulingPaint.setAntiAlias(true);
        mRulingPaint.setStrokeWidth(2);
        mRulingPaint.setStyle(Paint.Style.FILL);//设置绘制类型：填充/描边
```
注意使用画布旋转时，需要先保存当前画布状态,调用`` canvas.save(); ``api，结束时恢复之前保存的状态调用
``canvas.restore();``,并且这俩个api是成对出现的，如果缺少了则会抛出异常。
<b>时针，分针，秒针</b>
先把秒针绘制，先绘制在12点钟方向。在onDraw()方法添加:
```
        canvas.drawLine(200 + 300 / 2, 200 + 55, 200 + 300 / 2, 200 + 300 / 2, mSecondPaint);
```
秒针画笔初始化,init()添加:
```
        //秒针
        mSecondPaint = new Paint();
        mSecondPaint.setColor(Color.parseColor("#ff22ff"));
        mSecondPaint.setAntiAlias(true);
        mSecondPaint.setStrokeWidth(3);
        mSecondPaint.setStyle(Paint.Style.FILL);//设置绘制类型：填充/描边
```
图片4

<b>添加动画</b>
在没有看到源码之前，我猜想实现动画效果的方案可以是在内部维护一个Handler计时器(不一定适用Handler)，每秒执行一次
,每次重绘指针位置，重绘可以旋转画布或者改变指针的终点位置(起点在圆心)。这个可以根据上一次时间计算出下一秒的度数(位置)，而且开始绘制时可以获取到当前的时间，计算出指针
的位置并且绘制上去。看完源码后发现原作者并没有使用任何定时器实现，而是通过不断重新绘制(在onDraw结束调用
invalidate()达到死循环的目的)实现的动画效果。实际上使用Handler也是要达到无限循环的效果，通过重绘还减少了
维护Handler的过程。好东西当然要借鉴。
```
    /**
     * 获取当前时间
     */
    private void getCurrentTime() {
        Calendar calendar = Calendar.getInstance();
        float milliSecond = calendar.get(Calendar.MILLISECOND);
        float second = calendar.get(Calendar.SECOND) + milliSecond / 1000;
        float minute = calendar.get(Calendar.MINUTE) + second / 60;
        float hour = calendar.get(Calendar.HOUR) + minute / 60;
        // 当前时间，秒针所在的位置(以12点钟方向为起始，划过的角度)
        mSecondDegree = second / 60 * 360;
        // 分针划过角度
        mMinuteDegree = minute / 60 * 360;
        //时针划过角度
        mHourDegree = hour / 12 * 360;
    }
    
     @Override
    protected void onDraw(Canvas canvas) {
        //获取时间。
        getCurrentTime();
        .....
        //秒针
        canvas.save();
        canvas.rotate(mSecondDegree, 200 + 300 / 2, 200 + 300 / 2);
        canvas.drawLine(200 + 300 / 2, 200 + 55, 200 + 300 / 2, 200 + 300 / 2, mSecondPaint);
        canvas.restore();
        invalidate();
    }   
```
如果没有意外的话，此时秒针已经能够动起来了，而且每次开始运行定位都是当前时间。

图片5

<b>渐变效果</b>
理解了源码的实现后，我才能明白这个渐变效果是怎么实现的。首先使用到了SweepGradient。翻译大概就是扫描渐变,梯度渐变，呈圆环形状
类似雷达那种。相同的渐变效果类还有LinearGradient -- 线性渐变。顾名思义就是渐变呈线性，水平方向。不明白SweepGradient
可以去 <https://www.cnblogs.com/androidsuperman/p/5021872.html>,<https://www.jianshu.com/p/b517cb479b24>
看下，会对梯度渐变有一定了解。主要是这个例子能够很好的解释这个梯度渐变的效果。
OK实际代码:
```
    //梯度扫描渐变
    private SweepGradient mSweepGradient;
    // 渐变矩阵，作用在SweepGradient
    private Matrix mGradientMatrix;
    //渐变的画笔
    private Paint mGradientPaint;
    
    private void init() {
         ....
         
        //渐变画笔。
        mGradientPaint = new Paint();
        mGradientPaint.setAntiAlias(true);
        mGradientPaint.setStrokeWidth(20);
        mGradientPaint.setStyle(Paint.Style.STROKE); //描边
        mGradientPaint.setColor(Color.parseColor("#ff22ff"));
        //颜色渐变，颜色选择使用白色加透明效果。(会产生一圈白色到白色半透明的效果)
        mGradientMatrix = new Matrix();
        mSweepGradient = new SweepGradient(200 + 300 / 2, 200 + 300 / 2,
                new int[]{Color.parseColor("#ffffff"), Color.parseColor("#80ffffff")}
                , new float[]{0.75f, 1f});
    }
```
SweepGradient的构造函数:
* float cx :渐变中心x坐标
* float cy :y坐标
* int colors[] : 渐变颜色组
* float positions[] : 表示对应颜色的相对位置。以0开始，1结束。可以为null，表示各种颜色均匀分布。

需要注意的是颜色数组长度必须>2,最后一个参数float数组的长度必须等于颜色数组的长度。
渐变的效果已经设置好，把它应用到画笔上。注意这个画笔一定要设置为描边，就是``Paint.Style.STROKE``,原因在下面代码注释有说明，自己在
实践的过程中也被这个困扰了好久。应用渐变效果的代码(要放在invalidate()之前):
```
     protected void onDraw(Canvas canvas) {
        ....
     
        //渐变效果。 matrix 默认会从0度(对应到时钟就是三点钟方向)顺时针旋转开始颜色的渐变
        mSweepGradient.setLocalMatrix(mGradientMatrix);
        //渐变内容与设置了渐变的画笔有关，如果画出的是一个圆，那么作用范围就是一个圆；如果是一个正方形，那么作用范围就一个正方形。
        mGradientPaint.setShader(mSweepGradient);
        RectF gradiendRectf = new RectF();
        gradiendRectf.set(200 + 30, 200 + 30, 500 - 30, 500 - 30);
        canvas.drawArc(gradiendRectf, 0, 360, false, mGradientPaint);//这里是环状的弧
    }
```
图6

这时的运行效果肯定会让人惊讶:之前的秒针盘几乎都看不到了，外圈还有一圈白色。冷静分析一下，我这个绘制渐变的颜色是白色不透明到白色半透明，所以
漏出那一点点的秒针刻度圈应该透明效果导致的。最终原因就是后绘制的白色渐变覆盖了先绘制的秒针刻度，只要调整一些位置就可以了。
还有画布本身也是白色的，渐变效果不明显，还需要给画布添加一个背景颜色。在xml添加背景颜色或者使用画笔添加颜色都可以。

图7

渐变的效果已经很明显，接着让它配合秒针能动起来。方法跟秒针的动画是一样的--通过旋转，并且这个角度跟秒针相同,但是设置动画不是画布，
而是``mGradientMatrix``,在``setLocalMatrix``之前设置旋转效果，代码:
```
        mGradientMatrix.setRotate(mSecondDegree - 90, 200 + 300 / 2, 200 + 300 / 2);
        mSweepGradient.setLocalMatrix(mGradientMatrix);
```
最后让渐变视觉效果更好一点，可以将秒针圈刻度的间隔密切一点。目前数量设置是60，调整到240:

图8

到这里这个时钟view已经成型了，剩下分针跟时针参考秒针的做法就好。最后总结下这个view涉及到的知识点:
* View的测量方法的重写；
* 画笔，画布，弧，线，文字的基本使用，包括获取到被绘制文字的宽高等；
* SweepGradient颜色渐变(最开始吸引我拿来练习的地方)，这里有2点注意:1.渐变画笔style要设置为描边。2.渐变画笔宽度=刻度线宽度(为了美观)；

以上，本篇结束。

