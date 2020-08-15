package com.example.testlink;

import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.BounceInterpolator;
import android.view.animation.RotateAnimation;
import android.view.animation.ScaleAnimation;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.testlink.data_structure.tree.BinarySearchTree;
import com.example.testlink.design.observer.Clock;
import com.example.testlink.design.observer.Timer;
import com.example.testlink.design.proxy.DynamicProxy;
import com.example.testlink.design.proxy.Subject;
import com.example.testlink.view.DialProgress;
import com.example.testlink.view.TreeView;

import java.io.File;
import java.lang.reflect.Proxy;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private TextView createTree;
    private TextView showTree;
    private LinearLayout llMain;
    private TextView tv_clear_tree;
    private TextView tv_insert_tree;
    private TextView tvAnimator;
    private TextView tvShowKeyBoard;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //去掉toolbar
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        //隐藏标题栏
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);


        setContentView(R.layout.activity_main);

        createTree = findViewById(R.id.tv_create_tree);
        showTree = findViewById(R.id.tv_show_tree);
        llMain = findViewById(R.id.ll_main);
        tv_clear_tree = findViewById(R.id.tv_clear_tree);
        tv_insert_tree = findViewById(R.id.tv_insert_tree);
        tvAnimator = findViewById(R.id.tv_animator);

        tvShowKeyBoard = findViewById(R.id.tvShowKeyBoard);

        tvShowKeyBoard.setOnClickListener(v -> {

            Intent intent = new Intent(this, KeyBoardActivity.class);
            startActivity(intent);
        });

        Random mRandom = new Random();
        DialProgress mDialProgress = findViewById(R.id.dial_progress_bar);
        mDialProgress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            }
        });


//        countDownTimer();

//        SiriView siriView = findViewById(R.id.sir_view);
//        siriView.start();


//        Create.timer();
//        Transform.window();
//        Filter.sample();
//        Combine.switchOnNext();
//        Supply.timeout();

//        TreeView treeView = new TreeView(this);
//        llMain.addView(treeView);
//        treeView(treeView);

//        animatorPart();
//        DataStructure();
//        glideSample();

        designMode();

        TextView player = findViewById(R.id.tv_play_sound);
        player.setOnClickListener(v -> voiceTest());

    }

    private void voiceTest() {
        MediaPlayer mediaPlayer = MediaPlayer.create(this, R.raw.pay_tip);


        mediaPlayer.setOnPreparedListener(mp -> {
            Log.d("MediaPlayerUtil", "onCompletion: 准备完成");//测试
            mediaPlayer.start();
        });
        mediaPlayer.prepareAsync();


        mediaPlayer.setOnCompletionListener(mp -> Log.d("LXX", "播放完毕"));
    }

    int index = 0;

    private void countDownTimer() {
        int count = 52;
        CountDownTimer timer = new CountDownTimer((count + 1) * 1000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                Log.d("LXX", "计时器= " + millisUntilFinished);
                if (index <= count) {
                    Log.d("LXX", "index= " + index);
                    index++;
                    Log.d("LXX", "发送指令窗口编号= " + index);
                } else {
                    Log.d("LXX", "这是最后一次  " + index);
                }
            }

            @Override
            public void onFinish() {
                Log.d("LXX", "完成了");
            }
        };
        timer.start();
    }

    public static void main(String[] args) {
        designMode();
    }

    /**
     * 设计模式
     */
    private static void designMode() {
        //1、原型模式
//        Student s1 = new Student();
//        s1.address = "广东";
//        s1.name = "哈哈";
//        s1.sex = 1;
//        s1.age = 2;
//        List<String> list = new ArrayList<>();
//        list.add("rap");
//        list.add("跳");
//        s1.setLikes(list);
//        Country country = new Country();
//        country.setName("XXC");
//        s1.setCountry(country);
//        Log.d("TAG", "s1: " + s1.toString());
//
//        Student s2 = (Student) s1.deepClone();
//        s2.getLikes().add("打篮球");
//        s2.getCountry().setName("OOP");
//        Log.d("TAG", "s2: " + s2.toString());
//        Log.d("TAG", "s1: " + s1.toString());

        //2、工厂模式
//        DrinksFactory factory = new AppleFactory();
//        Drinks drinks = factory.build();
//
//        DrinksFactory factory2 = new LemonFactory();
//        Drinks drinks2 = factory2.build();
//        drinks.make();
//        drinks2.make();

        //3、单例模式
//        Single single = Single.getInstance();

        //4、抽象工厂模式
//        DrinksFactory factory = new AppleFactory();
//        DrinksFactory factory2 = new LemonFactory();
//        factory.buyWhat();
//        factory2.buyWhat();

        //5、静态代理
//        Subject subject = new ProxySubject();
//        subject.buyIphoneX();
        //动态代理
//        RealSubject r1 = new RealSubject();
//        RealSubject2 r2 = new RealSubject2();
//        RealSubject3 r3 = new RealSubject3();
//        proxy(r1);
//        proxy(r2);
//        proxy(r3);

        //6、策略模式
//        BattleGround battle = new BattleGround();
//        battle.setStrategy(new RiotStrategy());
//        battle.startFight();
//
//        battle.setStrategy(new GunAbsStrategy());
//        battle.startFight();

        //7、建造者模式
//        Director dic = new Director();
//        Product product = dic.buildA();

        //8、享元模式
//        ChessBoard board = new ChessBoard();
//        Chess c1 = board.getChess(ChessBoard.BLACK_CHESS);
//        c1.showColor();
//        Chess c2 = board.getChess(ChessBoard.BLACK_CHESS);
//        c2.showColor();
//        Chess c3 = board.getChess(ChessBoard.BLACK_CHESS);
//        c3.showColor();
//        Chess c4 = board.getChess(ChessBoard.WHITE_CHESS);
//        c4.showColor();
//        Chess c5 = board.getChess(ChessBoard.WHITE_CHESS);
//        c5.showColor();
//
//        Log.d("TAG", "c1= " + c1 + " ,c2= " + c2 + " ,c3= " + c3);
//        Log.d("TAG", "c4= " + c4 + " ,c5= " + c5);

        //9、桥接模式
        //红、白色的玫瑰
//        Rose rose1 = new Rose(new Red());
//        Rose rose2 = new Rose(new White());
//        rose1.show();
//        rose2.show();
        //红、白色的月季
//        ChineseRose cRose1 = new ChineseRose(new Red());
//        ChineseRose cRose2 = new ChineseRose(new White());
//        cRose1.show();
//        cRose2.show();

        //10、装饰模式
//        Food f = new Bread();
//        f.make();
//        Food f2 = new AppleBread(f);
//        f2.make();
//        Food f3 = new Hamburger(f);
//        f3.make();

       /* //11、备忘录模式
        Statue demo = new Statue();
        //创建备忘录管理者
        CareTaker ck = new CareTaker();
        demo.setStatue(400);
        Log.d("MEMO", demo.getStatue() + "");
        //保存一个备忘录
        ck.setMemo(demo.save2Memo());
        demo.setStatue(500);
        Log.d("MEMO", demo.getStatue() + "");
        //恢复备忘录保存状态
        demo.restoreStatue(ck.getMemo());
        Log.d("MEMO", demo.getStatue() + "");*/

        //12、外观模式
//        BossManager manager = new BossManager();
//        manager.study();
//        manager.relax();
//        manager.play();

        //13、状态模式
//        Diary diary = new Diary();
//        diary.setDay(new Timer12());
//        diary.getDay().LookDoSomething();
//        diary.setDay(new Timer6());
//        diary.getDay().LookDoSomething();
//        diary.setDay(new Timer23());
//        diary.getDay().LookDoSomething();

        //14、命令模式
//        Task math = new MathTask();
//        Task chinese = new ChineseTask();
//        HeadTeacher teacher = new HeadTeacher(math);
//        teacher.checkTask();
//
//        teacher.setTask(chinese);
//        teacher.checkTask();

        //15、组合模式
//        AbstractFile file1 = new TextFile("aaa");
//        AbstractFile file2 = new TextFile("bbb");
//        AbstractFile file3 = new TextFile("ccc");
//        AbstractFile file4 = new TextFile("ddd");
//        AbstractFile image = new ImageFile("简约风格");
//
//        Folders folder = new Folders("e");
//        Folders child1 = new Folders("f");
//        Folders child2 = new Folders("g");
//        folder.addFile(file1);
//        folder.addFile(file2);
//        folder.addFile(image);
//        //
//        child1.addFile(file3);
//        folder.addFile(child1);
//        //
//        child2.addFile(file4);
//        folder.addFile(child2);
//
//        file3.showContent();
//        file4.showContent();
//        folder.showContent();

        // 16、适配器模式
        //虚拟耳机对象的用法
//        MiMax max = new MiMax();
//        max.chargeAndEar();
//
//        Mix8Mold mold1 = new Mi8();
//        EarAdapter adapter = new EarAdapter(mold1);
//        adapter.chargeAndEar();
//
//        //使用耳机对象用法
//        EarPhone earPhone = new EarPhone();
//        earPhone.listen();
//
//        Mix8Mold mold = new Mi8();
//        earPhone.setAdapter(new EarAdapter(mold));
//        earPhone.listen();

        // 17、观察者模式
        Clock clock = new Clock();
        Timer timer = new Timer();
        timer.setObserver(clock);
        //整点到了
        timer.ring();

    }

    /**
     * 代理方法
     *
     * @param r 真实代理对象
     */
    private void proxy(Subject r) {
        DynamicProxy d = new DynamicProxy(r);
        //获取classloader
        ClassLoader classLoader = r.getClass().getClassLoader();
        //获取代理对象
        Subject s = (Subject) Proxy.newProxyInstance(classLoader, r.getClass().getInterfaces(), d);
        s.buyIphoneX();
    }

    /**
     * glide
     */
    private void glideSample() {
        ImageView view = findViewById(R.id.iv_img);
        Glide.with(this)
                .load("https://raw.github.com/wiki/ReactiveX/RxJava/images/rx-operators/last.2.png")
                .into(view);

        Uri uri = parseUri("https://raw.github.com/wiki/ReactiveX/RxJava/images/rx-operators/last.2.png");
        Log.d("lxxxx", "onCreate: " + uri.toString());
        Log.d("lxxxx", "onCreate: " + uri.getScheme());
    }

    /**
     * 二叉树view可视化
     *
     * @param treeView
     */
    private void treeView(TreeView treeView) {
        StringBuffer bf = new StringBuffer();
        bf.append("  ");
        createTree.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int value = (int) (Math.random() * 100 + 1);
                Log.d("tree", "onCreate: " + value);
                bf.append(value + ",  ");
                treeView.setTreeData(value);

                showTree.setText(bf.toString());
            }
        });

        tv_clear_tree.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                treeView.reDraw();
                showTree.setText("");
                if (bf.length() > 0)
                    bf.delete(1, bf.length() - 1);
            }
        });

        tv_insert_tree.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                treeView.reDraw();
                if (bf.length() > 0)
                    bf.delete(1, bf.length() - 1);

                List list = new ArrayList();
                for (int j = 0; j < 14; j++) {
                    int value = (int) (Math.random() * 100 + 1);
                    Log.d("tree", "onCreate: " + value);
                    bf.append(value + ",  ");
                    list.add(value);
                }

                treeView.insertTree(list);
                showTree.setText(bf.toString());
            }
        });
    }

    /**
     * 数据结构
     */
    private void DataStructure() {
        //双向链表
//        DuplexingLinkedList list = new DuplexingLinkedList();
//        list.insertAtTail("a");
//        list.insertAtTail("b");
//        list.insertAtTail("c");
//        list.insertAtTail("d");
//        list.insertAtTail("e");
//        list.insertAtTail("f");
//        list.insertAtTail("g");
//
//        list.insertAtHead("3");
//        list.insertAtHead("2");
//        list.insertAtHead("1");
//        list.out();
//        list.insertAtPosition("opo", 3);
//        list.out();
//        list.delete(3);
//        list.out();

//        list.deleteHead();
//        list.out();
//        list.deleteTail();
//        list.out();
//        list.reverse();
//        list.out(list.reverse());

        //栈
//        LinkedStack stack = new LinkedStack();
//        stack.push("a");
//        stack.push("b");
//        stack.push("c");
//        stack.push("d");
//        stack.push("e");
//        stack.push("f");
//        stack.push("g");
//
//        Log.d("LinkedStack", "onCreate: " + stack.pop());
//        Log.d("LinkedStack", "onCreate: " + stack.pop());
//        Log.d("LinkedStack", "onCreate: " + stack.pop());
//        Log.d("LinkedStack", "onCreate: " + stack.pop());
//        Log.d("LinkedStack", "onCreate: " + stack.pop());
//        Log.d("LinkedStack", "onCreate: " + stack.pop());
//        Log.d("LinkedStack", "onCreate: " + stack.pop());
//
//        Log.d("LinkedStack", "onCreate: " + stack.pop());

        //树
        StringBuffer bf = new StringBuffer();
        BinarySearchTree tree = new BinarySearchTree();
        /*for (int i = 0; i < 10; i++) {
            int value = (int) (Math.random() * 100 + 1);

            tree.insert(value);
            bf.append(value + ",  ");
        }*/
        // showTree.setText(bf.toString());

        tree.insert(15);
        tree.insert(18);
        tree.insert(10);
        tree.insert(25);
        tree.insert(17);
        tree.insert(16);
        tree.insert(40);
        tree.insert(20);
        tree.insert(19);
        tree.insert(22);
        tree.disPlayCenter(tree.rootNode);
        Log.d("tree", "------------------------------------");
        tree.disPlayBehind(tree.rootNode);
        Log.d("tree", "------------------------------------");
        tree.disPlayFront(tree.rootNode);
        Log.d("tree", "------------------------------------");

        boolean b = tree.find(100);
        Log.d("tree", "是否找到100: " + b);
        Log.d("tree", "------------------------------------");

        tree.delete(25);
        tree.disPlayCenter(tree.rootNode);
    }

    /**
     * 动画
     */
    private void animatorPart() {
        tvAnimator.setVisibility(View.VISIBLE);
//        ObjectAnimator animator = ObjectAnimator.ofFloat(tvAnimator, "TranslationY", 1, 520);
//        animator.setDuration(2000);
//        animator.setRepeatCount(ValueAnimator.INFINITE);
//        animator.setRepeatMode(ValueAnimator.REVERSE);
//        animator.start();

        //补间动画
//        Animation animation = AnimationUtils.loadAnimation(this, R.anim.my_scale);
//        tvAnimator.startAnimation(animation);


        ScaleAnimation s = new ScaleAnimation(1.0f, 3.0f, 3.0f, 3.0f,
                Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        s.setDuration(2000);
        s.setRepeatCount(5);
        s.setRepeatMode(Animation.REVERSE);
        s.setFillAfter(true);
        s.setInterpolator(new BounceInterpolator());//插值器
        tvAnimator.startAnimation(s);//调用,开始动画

        RotateAnimation r = new RotateAnimation(0, 360, Animation.RELATIVE_TO_SELF,
                0.5f, Animation.RELATIVE_TO_SELF, 0.5f);


        AnimationSet set = new AnimationSet(true);
        set.addAnimation(s);
        set.addAnimation(r);
        set.setDuration(3000);
        tvAnimator.startAnimation(set);

//        set.setRepeatMode(Animation.RESTART);
//        set.setRepeatCount(3);
//        set.setFillAfter(true);
//        set.start();


        tvAnimator.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "123", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private static Uri parseUri(String model) {
        Uri uri;
        if (TextUtils.isEmpty(model)) {
            return null;
            // See https://pmd.github.io/pmd-6.0.0/pmd_rules_java_performance.html#simplifystartswith
        } else if (model.charAt(0) == '/') {
            uri = toFileUri(model);
        } else {
            uri = Uri.parse(model);
            String scheme = uri.getScheme();
            if (scheme == null) {
                uri = toFileUri(model);
            }
        }
        return uri;
    }

    private static Uri toFileUri(String path) {
        return Uri.fromFile(new File(path));
    }

}
