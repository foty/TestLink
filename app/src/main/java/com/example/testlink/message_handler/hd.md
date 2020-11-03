### Android下的消息机制


##### ThreadLocal  
ThreadLocal 是用来存储指定线程的数据的。当某些数据的作用域是该指定线程并且该数据需要贯穿该线程的所有执行过程时就
可以使用ThreadLocal 存储数据。当某线程使用ThreadLocal存储数据后，只有该线程可以读取到存储的数据，除此线
程之外的其他线程是没办法读取到该数据的。就算是同一个ThreadLocal对象，任意线程对其的set()和get()方法的操作都是相
互独立互不影响的。 在创建handler对象的时候，在handler的构造方法内有一行`mLooper = Looper.myLooper();`代码用
来获取Looper对象。其中` Looper.myLooper()`内部就是使用 ThreadLocal来保存的一个Looper对象。ThreadLocal的谁
保存谁能访问的特性也是面试中 Handler能不能在子线程中创建的答案依据之一。


##### MessageQueue 
单向链表数据结构


##### Looper消费Message


##### 简述工作流程


##### ActivityThread
ActivityThread是Android应用程序的入口，也是任何一个进程的主线程入口。可能会有人理解为ActivityThread就是主线程。
但其实ActivityThread并不是线程，但可以理解为ActivityThread所在的线程就是主线程。下面是ActivityThread类的核心
方法。可以看到Android系统把每个应用程序当成java应用来看待：以`main(String[] args)`作为程序的入口。应用启动后会
执行它的`main(String[] args)`。除去log的输出，初始化外，核心代码就一句：`Looper.loop();`。loop()里就是维护着一
个无线循环，不断从自己的MessageQueue取出Message，然后分发出去。如果没有消息时，会进入阻塞状态。当消息来了又被唤醒
分发消息事件。中间阻塞与唤醒是通过`MessageQueue #nativePollOnce()`与 `MessageQueue #nativeWake()`来实现，
都是native方法。
```java
public final class ActivityThread extends ClientTransactionHandler {
    public static void main(String[] args) {
        Trace.traceBegin(Trace.TRACE_TAG_ACTIVITY_MANAGER, "ActivityThreadMain");
        // CloseGuard defaults to true and can be quite spammy.  We
        // disable it here, but selectively enable it later (via
        // StrictMode) on debug builds, but using DropBox, not logs.
        CloseGuard.setEnabled(false);
        Environment.initForCurrentUser();
        // Set the reporter for event logging in libcore
        EventLogger.setReporter(new EventLoggingReporter());
        // Make sure TrustedCertificateStore looks in the right place for CA certificates
        final File configDir = Environment.getUserConfigDirectory(UserHandle.myUserId());
        TrustedCertificateStore.setDefaultUserDirectory(configDir);
        Process.setArgV0("<pre-initialized>");
        Looper.prepareMainLooper();
        // Find the value for {@link #PROC_START_SEQ_IDENT} if provided on the command line.
        // It will be in the format "seq=114"
        long startSeq = 0;
        if (args != null) {
            for (int i = args.length - 1; i >= 0; --i) {
                if (args[i] != null && args[i].startsWith(PROC_START_SEQ_IDENT)) {
                    startSeq = Long.parseLong(
                            args[i].substring(PROC_START_SEQ_IDENT.length()));
                }
            }
        }
        ActivityThread thread = new ActivityThread();
        thread.attach(false, startSeq);
        if (sMainThreadHandler == null) {
            sMainThreadHandler = thread.getHandler();
        }
        if (false) {
            Looper.myLooper().setMessageLogging(new
                    LogPrinter(Log.DEBUG, "ActivityThread"));
        }
        // End of event ActivityThreadMain.
        Trace.traceEnd(Trace.TRACE_TAG_ACTIVITY_MANAGER);
        Looper.loop(); //
        throw new RuntimeException("Main thread loop unexpectedly exited");
    }
 }
```


##### ApplicationThread
通过ActivityThread了解到：主线程是一直处于死循环状态，那么android中其他组件，比如activity的生命周期等式如何在主
线程执行的？答案就是开启了子线程执行。在ActivityThread的`main()`方法looper之前先创建了ActivityThread对象,然后
执行它的`attach(false,0)`方法。在这个方法内，有这样一段代码:
```
  final IActivityManager mgr = ActivityManager.getService();
  try {
      mgr.attachApplication(mAppThread, startSeq);
  } catch (RemoteException ex) {
      throw ex.rethrowFromSystemServer();
  }
```
这里需要留意一下mAppThread，这个mAppThread是 ApplicationThread实例。它是ApplicationThread的一个私有内部类。它
是作为IApplicationThread的service端。从类声明上就能看出来。
```java
 private class ApplicationThread extends IApplicationThread.Stub {
        private static final String DB_INFO_FORMAT = "  %8s %8s %14s %14s  %s";

        private int mLastProcessState = -1;

        private void updatePendingConfiguration(Configuration config) {
            synchronized (mResourcesManager) {
                if (mPendingConfiguration == null ||
                        mPendingConfiguration.isOtherSeqNewer(config)) {
                    mPendingConfiguration = config;
                }
            }
        }
        // .....省略后续代码
}
```
这个 ActivityManager.getService()又是什么。翻阅代码可以查到，这个getService()返回的是ActivityManagerService
的Binder代理类，即Binder通信中的client。而作为service就是ActivityManagerService了，也就是AMS。


##### Handler面试的几个问题
一个线程有几个looper，如果你说一个，他会问，如何保证looper唯一  
> 1个


我们能在主线程直接new无参handler吗  
> 可以


子线程能new handler吗?我们应该怎么样在子线程new handler  
> 在特殊情况处理下可以在子线程new handler。

为什么主线程不用调用looper.prepar和looper.looper   
我们的looper通过什么实现与线程关联  
为什么looper死循环应用(UI)不卡顿 (那你谈谈epoll机制)  
如果我们的子线程没有消息处理的情况下，我们如何优化looper   
message上限怎么办。