package com.example.testlink.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;

import com.example.testlink.data_structure.tree.BinarySearchTree;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by lxx on 2019/5/22.
 * Use by
 */

public class TreeView extends View {

    public static final String TAG = "TreeView";
    /**
     * 默认node间边的x方向的距离
     */
    public static final int INTERVAL_X = 50;

    private Paint paint;

    private int midScreenWidth;

    private int node_margin_d = 150;

    /**
     * 整棵树的数据源
     */
    private List<Integer> list = new ArrayList();

    HashMap<Integer, Integer> deepMap = new HashMap<>();

    /**
     * 树的深度
     */
    private int tree_deep;

    /**
     * 重新计算树的距离
     */
    private boolean need_draw;
    /**
     * node间边的x方向的距离变量
     */
    private int node_margin_x = 51;
    /**
     * node间边的y方向的距离
     */
    private int node_margin_y = 30;
    /**
     * node的直径
     */
    private int node_diameter = 50;

    /**
     * 文字x方向开始点到node左边界的距离,取决于node的大小且只影响美观
     */
    private int text_x = 15;
    /**
     * 文字y方向开始点到node上边界的距离,取决于node的大小且只影响美观
     */
    private int text_y = 30;

    /**
     * 是否可以继续画下去，增加数的深度，主要是考虑设备屏幕的宽度大小，影响美观
     */
    private boolean stop_draw;

    private BinarySearchTree tree;

    public TreeView(Context context) {
        this(context, null);
    }

    public TreeView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public TreeView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView();
    }

    /**
     * 初始化
     */
    private void initView() {
        paint = new Paint();
        paint.setColor(Color.parseColor("#000000"));
        paint.setAntiAlias(true);
        paint.setStyle(Paint.Style.STROKE);

        midScreenWidth = getScreenWidth();
        Log.d(TAG, "initView: " + midScreenWidth);

        //初始化tree
        tree = new BinarySearchTree();

        for (int j = 0; j < 7; j++) {
            int value = (int) (Math.random() * 100 + 1);
            Log.d("tree", "onCreate: " + value);
            list.add(value);
        }

    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        //循环画tree
        Log.d("tree", "onDraw: 开始调用onDraw()---  ");
        tree.rootNode = null;
        tree_deep = 0;
        if (list.size() == 0) {
            tree.insert(1);
            drawTree(canvas, 1);
        }

        for (int j = 0; j < list.size(); j++) {
            if (need_draw) {
                calculateInterval();
                if (/*!stop_draw*/true) {
                    invalidate();
                } else {
                    Log.d("LXX", "停止绘制");
                    break;
                }
//                invalidate();
                return;
            } else {
                // int value = (int) (Math.random() * 100 + 1);
                // Log.d("tree", "onCreate: " + value);
                drawTree(canvas, list.get(j));
            }
        }
    }

    /**
     * 获取屏幕宽度
     *
     * @return
     */
    public int getScreenWidth() {
        WindowManager wm = (WindowManager) getContext().getSystemService(
                Context.WINDOW_SERVICE);
        DisplayMetrics outMetrics = new DisplayMetrics();
        wm.getDefaultDisplay().getMetrics(outMetrics);
        return outMetrics.widthPixels;
    }

    /**
     * @param canvas
     * @param i      插入的数据值
     */
    public void drawTree(Canvas canvas, int i) {
        //创建节点
        BinarySearchTree.Node newNode = new BinarySearchTree.Node(i);
        //画节点
        if (tree.rootNode == null) {
            tree.rootNode = newNode;
            //画根节点
            Log.d("tree", "drawTree: 画根节点");
            drawRootNode(canvas, newNode);
        } else {
            BinarySearchTree.Node curNode = tree.rootNode;
            BinarySearchTree.Node father = null;

            tree_deep = 0;
            while (curNode != null) {
                father = curNode;
                Log.d(TAG, "drawTree: tree_deep=  " + tree_deep + "  是否= " + deepMap.get(tree_deep));

                tree_deep++;
                if (father.interval / 4 <= node_diameter && deepMap.get(tree_deep) == null) {
                    need_draw = true;
                    Log.d(TAG, "drawRootNode: 需要重新绘制");
                } else {
                    Log.d(TAG, "drawRootNode: 不需要重新绘制");
                    need_draw = false;
                }


                deepMap.put(tree_deep, tree_deep);

                if (i < curNode.data) {
                    curNode = curNode.left;
                    if (curNode == null) {
                        father.left = newNode;
                        //画左边
                        drawNode(canvas, true, newNode, father);
                    }
                } else if (i > curNode.data) {
                    curNode = curNode.right;
                    if (curNode == null) {
                        father.right = newNode;
                        //画右边
                        drawNode(canvas, false, newNode, father);
                    }
                } else {
                    Log.e(TAG, "drawTree: 插入值不符合" + i);
                    break;
                }
            }
        }
    }

    /**
     * 画根节点
     *
     * @param canvas
     * @param newNode
     */
    private void drawRootNode(Canvas canvas, BinarySearchTree.Node newNode) {
        int left = midScreenWidth / 2;
        int top = node_margin_y;
        int right = left + node_diameter;
        int bottom = top + node_diameter;
        //画节点
        RectF rectF = new RectF(left, top, right, bottom);
        canvas.drawOval(rectF, paint);
        //画数据
        canvas.drawText(newNode.data + "", left + text_x, top + text_y, paint);
        //为节点位置赋值
        newNode.leftx = left;
        newNode.topx = top;
        newNode.rightx = right;
        newNode.bootomx = bottom;
        newNode.interval = node_margin_x;
    }

    /**
     * 画子节点
     *
     * @param canvas
     * @param isLeft  是否为画做节点
     * @param newNode
     * @param father
     */
    private void drawNode(Canvas canvas, boolean isLeft,
                          BinarySearchTree.Node newNode, BinarySearchTree.Node father) {

        int startX = (int) ((father.rightx + father.leftx) / 2);
        int startY = (int) father.bootomx;
        int interval = father.interval / 2;

        int endX = isLeft ? startX - interval : startX + interval;
        Log.d(TAG, "drawNode: 变化距离为=" + interval);
        int endY = (int) (father.bootomx + node_margin_y);
        //画边
        canvas.drawLine(startX, startY, endX, endY, paint);
        //画子节点
        int left = endX - node_diameter / 2;
        int right = endX + node_diameter / 2;
        int bottom = endY + node_diameter;
        RectF rectF = new RectF(left, endY, right, bottom);
        canvas.drawOval(rectF, paint);
        //画数据
        canvas.drawText(newNode.data + "", left + text_x, endY + text_y, paint);
        //为节点位置赋值
        newNode.leftx = left;
        newNode.topx = endY;
        newNode.rightx = right;
        newNode.bootomx = bottom;
        newNode.interval = interval;
    }

    /**
     * 插入一个数字
     *
     * @param data
     */
    public void setTreeData(int data) {
        //if (!stop_draw) {
            list.add(data);
            invalidate();
        //}
    }

    /**
     * 插入一组数字
     *
     * @param list
     */
    public void insertTree(List<Integer> list) {
        this.list = list;
        node_margin_x = INTERVAL_X;
        deepMap.clear();
        stop_draw = false;
        invalidate();
    }

    /**
     * 清空树
     */
    public void reDraw() {
        list.clear();
        deepMap.clear();
        stop_draw = false;
        invalidate();
        node_margin_x = INTERVAL_X;
    }

    /**
     * 计算分树距离
     */
    public void calculateInterval() {
        Log.d(TAG, "calculateInterval: " + tree_deep);
//        node_margin_x = INTERVAL_X * (2 << tree_deep);
        node_margin_x = node_margin_x << 1;
        if (node_margin_x >= midScreenWidth / 4) {
            stop_draw = true;
        }
        need_draw = false;
        Log.d(TAG, "calculateInterval:增量为= " + (2 << tree_deep) + ", 新的node_margin_x= " + node_margin_x);
    }
}
