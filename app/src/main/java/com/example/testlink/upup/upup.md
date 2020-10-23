
##### 1、程序运行的内存分配

##### 2、GC回收机制与分代回收策略

##### 3、字节码层面看class文件结构

##### 4、编译插桩操纵字节码 (AsmLifeCycleDemo工程)

##### 5、ClassLoader的加载机制
1、 .class文件被加载到内存中：  
1. 调用类构造器
2. 调用类的静态变量或者静态方法  

2、 JVM中的3个类加载器：
1. BootstrapClassLoader: 启动类加载器。属于虚拟机一部分
2. ExtClassLoader(1.9后改名PlatformClassLoader): 扩展类加载
3. APPClassLoader : 面向用户的ClassLoader。加载包括有自己编写的类文件、使用的第三方jar等。

3、 ClassLoader如何知道使用那个类加载器去加载对应的类呢？-- 双亲委派模式。即：   
先委托父类加载器加载，当父类加载器没有找到指定资源时，才会由自己去执行类加载过程。当加载一个编写的java类时，首先
是APPClassLoader将加载任务委派给它的父加载器，即是ExtClassLoader。如果ExtClassLoader == null，则由
BootstrapClassLoader加载，当双亲都加载失败了才由APPClassLoader去加载。
```
    protected Class<?> loadClass(String name, boolean resolve) //首先是由APPClassLoader执行自己的loadClass方法
        throws ClassNotFoundException
    {
        synchronized (getClassLoadingLock(name)) {
            // First, check if the class has already been loaded // 检查是否被加载，若被加载过直接返回被加载
            Class<?> c = findLoadedClass(name);
            if (c == null) {
                long t0 = System.nanoTime();
                try {
                    if (parent != null) { // 双亲委托机制的体现 
                        c = parent.loadClass(name, false);
                    } else {
                        c = findBootstrapClassOrNull(name);
                    }
                } catch (ClassNotFoundException e) {
                    // ClassNotFoundException thrown if class not found
                    // from the non-null parent class loader
                }
                if (c == null) {
                    // If still not found, then invoke findClass in order
                    // to find the class.
                    long t1 = System.nanoTime();
                    c = findClass(name);  // 双亲都加载失败才会由自身尝试加载
                    
                    // this is the defining class loader; record the stats
                    sun.misc.PerfCounter.getParentDelegationTime().addTime(t1 - t0);
                    sun.misc.PerfCounter.getFindClassTime().addElapsedTimeFrom(t1);
                    sun.misc.PerfCounter.getFindClasses().increment();
                }
            }
            if (resolve) {
                resolveClass(c);
            }
            return c;
        }
    }
```

4、 案例:自定义ClassLoader(案例代码-DiskClassLoader)    
注意事项:ClassLoader只能识别.class文件，所以需要将java文件编译成.class文件。   
 .java 文件编译成.class文件流程：
 * 1、找到需要编译的.java文件目录下
 * 2、打开命令窗口(左shift + 右键 --> 选择‘在此处打开命令窗口’)
 * 3、运行'javac xxx.java'，便能生成.class文件


5、 android下的ClassLoader    
Android下的ClassLoader与传统JVM类似，也是由ClassLoader加载到内存，类加载器之间也符合双亲委派模型。但是加载细节却有一点不一样:Android虚拟机无法
直接运行.class文件，而是要转换成.dex文件。加载.dex文件的ClassLoader为BaseDexClassLoader。通常情况下只使用的它的2个子类：
* PathClassLoader: 加载系统apk和被安装到手机中的apk内的dex文件
* DexClassLoader: 除了系统apk和被安装到手机中的apk内的dex文件外，还可以从SD卡加载含有class.dex的jar与apk文件(这是插件化热修复的基础)

6、 案例: 使用DexClassLoader实现热修复。


##### 6、Class对象在执行引擎的初始化过程。


一个class文件被加载到内存需要3个步骤：  
 装载 --> 链接  --> 初始化。   
其中链接又可以分为3个步骤：  
 验证 --> 准备 --> 解析。

1、装载。  
 装载指的是java虚拟机查找class文件并且生成字节流并根据字节流创建JVAV对象的过程。