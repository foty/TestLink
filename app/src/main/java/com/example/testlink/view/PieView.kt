package com.example.testlink.view

import android.R
import android.content.Context
import android.graphics.*
import android.support.v4.content.ContextCompat
import android.util.AttributeSet
import android.util.DisplayMetrics
import android.view.View
import android.view.WindowManager
import org.jetbrains.annotations.Nullable
import java.text.DecimalFormat
import java.util.*
import kotlin.math.roundToInt


/**
 * Create by lxx
 * Date : 2020/9/4 14:57
 * Use by
 */
class PieView : View {

    // https://yq.aliyun.com/articles/638335

    private val rectF: RectF by lazy {
        RectF(centerX - radius, 0f, centerX + radius, radius * 2)
    }
    private val mContext: Context = context!!

    private var mPaint: Paint? = null

    //每块占比的绘制的颜色
    private var mColorList: MutableList<Int> = ArrayList()

    //圆弧占比的集合
    private var mRateList: MutableList<Float> = ArrayList()

    //是否展示文字
    private var isShowRateText = false

    //圆弧半径
    private var radius = 0f
    private var startAngle = 0

    //不同色块之间是否需要空隙offset
    private val offset = 0

    //圆弧中心点小圆点的圆心半径
    private var centerPointRadius = 0
    private var showRateSize = 0
    private var textRect: Rect? = null

    //折线横向长度
    private var xOffset = 0f

    //折线偏Y方向长度
    private var yOffset = 0f
    private val mChangeAngle = 0f
    private val isAnimation = false
    private val sign = 0f

    private var centerX = 0f
    private var centery = 0f


    constructor(context: Context?) : this(context, null)

    constructor(context: Context?, @Nullable attrs: AttributeSet?) : this(context, attrs, 0)

    constructor(context: Context?, @Nullable attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr)

    init {
        init()
        initData()
    }

    private fun initData() {
        val rate = floatArrayOf(30f, 40f, 15f, 15f)
        val colors = intArrayOf(Color.RED, Color.BLUE, Color.YELLOW, Color.GRAY)
        for (i in rate.indices) {
            mRateList.add(rate[i] / 100)
            mColorList.add(colors[i])
        }
        textRect = Rect()
        if (mRateList.size > 0) {
            mPaint?.getTextBounds(mRateList[0].toString() + "%", 0, (mRateList[0].toString() + "%").length, textRect)
        }
    }

    private fun init() {

        centerX = getScreenWidth() / 2.toFloat()
        radius = dip2px(mContext, 100)

        centerPointRadius = dip2px(mContext, 2).toInt()

        xOffset = dip2px(mContext, 20)
        yOffset = dip2px(mContext, 5)

        showRateSize = dip2px(mContext, 10).toInt()

        mPaint = Paint(Paint.ANTI_ALIAS_FLAG)
        mPaint!!.color = Color.RED
        mPaint!!.isAntiAlias = true
        mPaint!!.style = Paint.Style.FILL
        mPaint!!.textSize = showRateSize.toFloat()

        if (mRateList.size > 0) {
            textRect = Rect()
            mPaint!!.getTextBounds(mRateList[0].toString() + "%", 0, (mRateList[0].toString() + "%").length, textRect)
        }
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
//        val widthMode = MeasureSpec.getMode(widthMeasureSpec)
//        val heightMode = MeasureSpec.getMode(heightMeasureSpec)
//        var widthSize = MeasureSpec.getSize(widthMeasureSpec)
//        var heightSize = MeasureSpec.getSize(heightMeasureSpec)
//
//        if (heightMode == MeasureSpec.AT_MOST) {
//            //边沿线和文字所占的长度：(xOffset + yOffset + textRect.width())
//            heightSize = (radius * 2).toInt() + 2 * centerPointRadius + getPaddingLeft()
//            +getPaddingRight() + (xOffset + yOffset + textRect!!.height()) * 2
//        }
//
//        if (widthMode == MeasureSpec.AT_MOST) {
//            widthSize = (radius * 2).toInt() + 2 * centerPointRadius + getPaddingLeft() + getPaddingRight()
//            +(xOffset + yOffset + textRect!!.width()) * 2
//        }
//        setMeasuredDimension(600, 600)

    }


    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        //(1)绘制圆饼
//        val rectF = RectF(0 + centerPointRadius + (xOffset + yOffset + textRect!!.width()),
//                0 + centerPointRadius + (xOffset + yOffset + textRect!!.height()),
//                2 * radius + centerPointRadius + (xOffset + yOffset + textRect!!.width()),
//                2 * radius + centerPointRadius + (xOffset + yOffset + textRect!!.height()))

        val mPointList: MutableList<Point> = ArrayList()

        for (i in mRateList.indices) {
            mPaint?.style = Paint.Style.FILL
            mPaint?.color = mColorList[i]
            // 画弧长
            canvas.drawArc(rectF, startAngle.toFloat(), (mRateList[i] * 360) - offset, true, mPaint)

            //(2)处理每块圆饼弧的中心点，绘制折线，显示对应的文字
            if (isShowRateText) {
                // 绘制折线
                dealPoint(rectF, startAngle.toFloat(), (mRateList[i] * 360 - offset) / 2, mPointList)

                val point: Point = mPointList[i]
                mPaint?.color = Color.BLACK //点的绘制的颜色
                canvas.drawCircle(point.x.toFloat(), point.y.toFloat(), centerPointRadius.toFloat(), mPaint)

                // 绘制文字
                dealRateText(canvas, point, i, mPointList)
            }
            startAngle += (mRateList[i] * 360).toInt()
        }

        //(3)绘制内部中空的圆
//        mPaint?.setColor(ContextCompat.getColor(this!!.mContext!!, R.color.holo_purple))
//        mPaint?.setStyle(Paint.Style.FILL)
//        canvas.drawCircle(radius + centerPointRadius + (xOffset + yOffset + textRect!!.width()),
//                radius + centerPointRadius + (xOffset + yOffset + textRect!!.height()), radius / 1.5f, mPaint)
    }

    private var lastPoint: Point? = null

    private fun dealRateText(canvas: Canvas, point: Point, position: Int, pointList: List<Point>) {
        lastPoint = if (position == 0) {
            pointList[0]
        } else {
            pointList[position - 1]
        }
        val floats = FloatArray(8)
        floats[0] = point.x.toFloat()
        floats[1] = point.y.toFloat()
        //右半圆
        if (point.x >= radius + centerPointRadius + (xOffset + yOffset + textRect!!.width())) {
            mPaint?.setTextAlign(Paint.Align.LEFT)
            floats[6] = point.x + xOffset
            //防止相邻的圆饼绘制的文字重叠显示
//            if (lastPoint != null) {
//                int absX = Math.abs(point.x - lastPoint.x);
//                int absY = Math.abs(point.y - lastPoint.y);
//                if (absX > 0 && absX < 20 && absY > 0 && absY < 20) {
//                    floats[6] = point.x + xOffset - textRect.width() / 2;
//                    Log.e("TAG", "右半圆");
//                } else {
//                    floats[6] = point.x + xOffset;
//                }
//            } else {
//                floats[6] = point.x + xOffset;
//            }
            if (point.y <= radius + centerPointRadius + (xOffset + yOffset + textRect!!.height())) {
                //右上角
                floats[2] = point.x + yOffset
                floats[3] = point.y - yOffset
                floats[4] = point.x + yOffset
                floats[5] = point.y - yOffset
                floats[7] = point.y - yOffset
            } else {
                //右下角
                floats[2] = point.x + yOffset
                floats[3] = point.y + yOffset
                floats[4] = point.x + yOffset
                floats[5] = point.y + yOffset
                floats[7] = point.y + yOffset
            }
            //左半圆
        } else {
            mPaint?.setTextAlign(Paint.Align.RIGHT)
            floats[6] = point.x - xOffset
            //防止相邻的圆饼绘制的文字重叠显示
//            if (lastPoint != null) {
//                int absX = Math.abs(point.x - lastPoint.x);
//                int absY = Math.abs(point.y - lastPoint.y);
//                if (absX > 0 && absX < 20 && absY > 0 && absY < 20) {
//                    floats[6] = point.x - xOffset - textRect.width() / 2;
//                    Log.e("TAG", "左半圆");
//                } else {
//                    floats[6] = point.x - xOffset;
//                }
//            } else {
//                floats[6] = point.x - xOffset;
//            }
            if (point.y <= radius + centerPointRadius) {
                //左上角
                floats[2] = point.x - yOffset
                floats[3] = point.y - yOffset
                floats[4] = point.x - yOffset
                floats[5] = point.y - yOffset
                floats[7] = point.y - yOffset
            } else {
                //左下角
                floats[2] = point.x - yOffset
                floats[3] = point.y + yOffset
                floats[4] = point.x - yOffset
                floats[5] = point.y + yOffset
                floats[7] = point.y + yOffset
            }
        }
        //根据每块的颜色，绘制对应颜色的折线
//        mPaint.setColor(mRes.getColor(colorList.get(position)));
        mPaint?.setColor(ContextCompat.getColor(this!!.mContext!!, R.color.darker_gray))
        //画圆饼图每块边上的折线
        canvas.drawLines(floats, mPaint)
        mPaint?.setStyle(Paint.Style.STROKE)
        //绘制显示的文字,需要根据类型显示不同的文字
        if (mRateList.size > 0) {
            //Y轴：+ textRect.height() / 2 ,相对沿线居中显示
            canvas.drawText(getFormatPercentRate(mRateList[position] * 100) + "%", floats[6], floats[7] + textRect!!.height() / 2, mPaint)
        }
    }

    private fun dealPoint(rectF: RectF, startAngle: Float, endAngle: Float, pointList: MutableList<Point>) {
        val path = Path()
        //通过Path类画一个90度（180—270）的内切圆弧路径
        path.addArc(rectF, startAngle, endAngle)
        val measure = PathMeasure(path, false)
        // Log.e("路径的测量长度:", "" + measure.getLength());
        val coords = floatArrayOf(0f, 0f)
        //利用PathMeasure分别测量出各个点的坐标值coords
        val divisor = 1
        measure.getPosTan(measure.length / divisor, coords, null)
        //        Log.e("coords:", "x轴:" + coords[0] + " -- y轴:" + coords[1]);
        val x = coords[0]
        val y = coords[1]
        val point = Point(x.roundToInt(), y.roundToInt())
        pointList.add(point)
    }

    private fun updateDate(rateList: MutableList<Float>, colorList: MutableList<Int>, isShowRateText: Boolean) {
        this.isShowRateText = isShowRateText
        mRateList = rateList
        mColorList = colorList
        init()
        invalidate()
    }

    /**
     * 获取格式化的保留两位数的数
     */
    private fun getFormatPercentRate(dataValue: Float): String {
        val decimalFormat = DecimalFormat(".00") //构造方法的字符格式这里如果小数不足2位,会以0补足.
        return decimalFormat.format(dataValue)
    }

    private fun dip2px(context: Context, dp: Int): Float = dp * context.resources.displayMetrics.density

    private fun px2dp(context: Context, px: Float): Float = px / context.resources.displayMetrics.density

    /**
     * 获取屏幕宽度
     *
     */
    fun getScreenWidth(): Int {
        val wm = context.getSystemService(Context.WINDOW_SERVICE) as WindowManager
        val outMetrics = DisplayMetrics()
        wm.defaultDisplay.getMetrics(outMetrics)
        return outMetrics.widthPixels
    }
}

