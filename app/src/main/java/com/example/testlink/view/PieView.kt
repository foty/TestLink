package com.example.testlink.view

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.util.DisplayMetrics
import android.util.Log
import android.view.View
import android.view.WindowManager
import org.jetbrains.annotations.Nullable
import java.text.DecimalFormat
import java.util.*
import kotlin.math.abs
import kotlin.math.roundToInt


/**
 * Create by lxx
 * Date : 2020/9/4 14:57
 * Use by
 */
class PieView : View {

    private val mContext: Context = context

    private var mPaint = Paint(Paint.ANTI_ALIAS_FLAG)

    /**
     * 每块占比的绘制的颜色
     */
    private var mColorList: MutableList<Int> = ArrayList()

    /**
     * 圆弧占比的集合
     */
    private var mRateList: MutableList<Float> = ArrayList()

    /**
     * 是否展示文字
     */
    private var isShowRateText = true

    /**
     * 圆弧半径
     */
    private var radius = 0f
    private var startAngle = 0f

    /**
     * 圆心x坐标
     */
    private var centerX = 0f

    /**
     * 圆心y坐标
     */
    private var centerY = 0f

    private val rectF: RectF by lazy {
        RectF(centerX - radius, centerY - radius, centerX + radius, centerY + radius)
    }

    /**
     * 色块间留白
     */
    private val offset = 0

    /**
     * 圆弧中心点圆点的圆心半径
     */
    private val centerPointRadius by lazy {
        dip2px(mContext, 1)
    }

    private val textSize by lazy {
        dip2px(mContext, 8)
    }

    private var textRect = Rect()

    /**
     * 折线横向长度
     */
    private val xOffset by lazy {
        dip2px(mContext, 25)
    }

    /**
     * 折线偏Y方向长度
     */
    private val yOffset by lazy {
        dip2px(mContext, 8)
    }

    private var mPointList = ArrayList<Point>()
    private var lastPoint: Point? = null

    private var rby = 10
    private var lby = 10
    private var lty = 10
    private var rty = 10

    constructor(context: Context?) : this(context, null)

    constructor(context: Context?, @Nullable attrs: AttributeSet?) : this(context, attrs, 0)

    constructor(context: Context?, @Nullable attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
        initView()
        initData()
    }

    private fun initData() {
        val rate = floatArrayOf(12f, 1f, 1f, 1f, 10f, 7f, 1f, 1f, 1f, 15f, 13f, 1f, 1f, 1f, 9f, 17f, 1f, 1f, 1f, 5f)
        val colors = intArrayOf(Color.RED, Color.BLUE, Color.YELLOW, Color.GRAY, Color.GREEN,

                Color.BLACK, Color.parseColor("#ee34ff"), Color.parseColor("#653408"),
                Color.parseColor("#666666"), Color.parseColor("#e57822"),

                Color.parseColor("#2e45ee"), Color.parseColor("#e6c456"), Color.parseColor("#cc8802"),
                Color.parseColor("#999999"), Color.parseColor("#c6c6c6"),

                Color.parseColor("#FFA500"), Color.parseColor("#FFB46BC7"), Color.parseColor("#FF3F857F"),
                Color.parseColor("#FF14A01A"), Color.parseColor("#FFF06539"))

        for (i in rate.indices) {
            mRateList.add(rate[i] / 100)
            mColorList.add(colors[i])
        }
        textRect = Rect()
        if (mRateList.size > 0) {
            mPaint.getTextBounds(mRateList[0].toString() + "%", 0, (mRateList[0].toString() + "%").length, textRect)
        }
    }

    private fun initView() {

        radius = dip2px(mContext, 100)
        centerX = getScreenWidth() / 2.toFloat()
        centerY = radius + 80f

        mPaint.let {
            it.color = Color.RED
            it.isAntiAlias = true
            it.style = Paint.Style.FILL
            it.textSize = textSize
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
        Log.d("lxx", "onDraw")
        mPointList.clear()

        for (i in mRateList.indices) {
            mPaint.style = Paint.Style.FILL
            mPaint.color = mColorList[i]

            canvas.drawArc(rectF, startAngle, (mRateList[i] * 360) - offset, true, mPaint)

            if (isShowRateText) {
                dealPoint(rectF, startAngle, (mRateList[i] * 360 - offset) / 2, mPointList)

                mPaint.color = mColorList[i]

                canvas.drawCircle(mPointList[i].x.toFloat(), mPointList[i].y.toFloat(), centerPointRadius, mPaint)
                dealRateText(canvas, mPointList[i], i, mPointList)
            }

            startAngle += mRateList[i] * 360
        }
    }


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
        if (point.x >= centerX) {
            mPaint.textAlign = Paint.Align.LEFT

            if (point.y <= centerY) {
                //右上角
                floats[2] = point.x + yOffset
                floats[3] = point.y - yOffset
                floats[4] = point.x + yOffset
                floats[5] = point.y - yOffset

                floats[6] = point.x + xOffset
                floats[7] = point.y - yOffset

                if (offSetAngle(point, lastPoint)) {
                    floats[6] = point.x + xOffset + rty
                    floats[7] = point.y - yOffset + (rty / 10 * textRect.height())
                    rty += 10
                } else {
                    rty = 10
                }

            } else {
                //右下角
                floats[2] = point.x + yOffset
                floats[3] = point.y + yOffset
                floats[4] = point.x + yOffset
                floats[5] = point.y + yOffset

                floats[6] = point.x + xOffset
                floats[7] = point.y + yOffset

                if (offSetAngle(point, lastPoint)) {
                    floats[6] = point.x + xOffset + rby
                    floats[7] = point.y + yOffset + textRect.height()
                    rby += 5
                } else {
                    rby = 10
                }
            }

        } else { //左半圆
            mPaint.textAlign = Paint.Align.RIGHT

            if (point.y <= centerY) {
                //左上角
                floats[2] = point.x - yOffset
                floats[3] = point.y - yOffset
                floats[4] = point.x - yOffset
                floats[5] = point.y - yOffset

                floats[6] = point.x - xOffset
                floats[7] = point.y - yOffset

                if (offSetAngle(point, lastPoint)) {
                    floats[6] = point.x - xOffset - lty
                    floats[7] = point.y - yOffset - textRect.height()
                    lty += 5
                } else {
                    lty = 10
                }

            } else {
                //左下角
                floats[2] = point.x - yOffset
                floats[3] = point.y + yOffset
                floats[4] = point.x - yOffset
                floats[5] = point.y + yOffset

                floats[6] = point.x - xOffset
                floats[7] = point.y + yOffset

                if (offSetAngle(point, lastPoint)) {
                    floats[6] = point.x - xOffset - lby
                    floats[7] = point.y + yOffset - textRect.height()
                    lby += 6
                } else {
                    lby = 10
                }
            }
        }
        mPaint.textSize = 10f
        canvas.drawLines(floats, mPaint)

        mPaint.textSize = textSize
        mPaint.color = Color.BLACK
        mPaint.style = Paint.Style.STROKE

        if (mRateList.size > 0) {
            canvas.drawText(getFormatPercentRate(mRateList[position] * 100) + "%", floats[6],
                    floats[7] + textRect.height() / 2, mPaint)
        }
    }

    /**
     * PathMeasure: 测量一段path的长度
     * getPosTan: 返回指定距离的正切值。
     */
    private fun dealPoint(rectF: RectF, startAngle: Float, endAngle: Float, pointList: MutableList<Point>) {
        val path = Path()
        path.addArc(rectF, startAngle, endAngle)
        val measure = PathMeasure(path, false)

        val coords = floatArrayOf(0f, 0f)
        measure.getPosTan(measure.length / 1, coords, null)

        val point = Point(coords[0].roundToInt(), coords[1].roundToInt())
        pointList.add(point)
    }

    /**
     * 更新数据
     */
    private fun updateDate(rateList: MutableList<Float>, colorList: MutableList<Int>, isShowRateText: Boolean) {
        this.isShowRateText = isShowRateText

        mRateList.clear()
        for (i in rateList.indices) {
            mRateList.add(rateList[i] / 100)
        }

        mColorList.clear()
        mColorList.addAll(colorList)

        invalidate()
    }

    /**
     * 获取格式化的保留两位数的数
     */
    private fun getFormatPercentRate(dataValue: Float): String {
        val decimalFormat = DecimalFormat("0.00")
        return decimalFormat.format(dataValue)
    }

    private fun dip2px(context: Context, dp: Int): Float = dp * context.resources.displayMetrics.density

    private fun px2dp(context: Context, px: Float): Float = px / context.resources.displayMetrics.density

    /**
     *
     */
    private fun offSetAngle(currentP: Point, lastP: Point?): Boolean {
        if (lastP != null) {
            if (!halfArea(currentP, lastP)) return false

            val absX = abs(currentP.x - lastPoint!!.x)
            val absY = abs(currentP.y - lastPoint!!.y)
            if (absY in 1..15) {
                return true
            } else {
                rby = 10
                lby = 10
            }
        }
        return false
    }

    private fun halfArea(currentP: Point, lastP: Point?): Boolean {

        if (lastP != null) {
            return (currentP.x > centerX && lastP.x > centerX || currentP.x < centerX && lastP.x < centerX)
                    && (
                    currentP.y > centerY && lastP.y > centerY || currentP.y < centerY && lastP.y < centerY)
        }
        return true

    }

    /**
     * 获取屏幕宽度
     *
     */
    private fun getScreenWidth(): Int {
        val wm = context.getSystemService(Context.WINDOW_SERVICE) as WindowManager
        val outMetrics = DisplayMetrics()
        wm.defaultDisplay.getMetrics(outMetrics)
        return outMetrics.widthPixels
    }
}

