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
线程执行的？答案就是开启了子线程/新进程执行。在ActivityThread的`main()`方法looper之前先创建了ActivityThread对象,
然后执行它的`attach(false,0)`方法。在这个方法内，有这样一段代码:
```
  final IActivityManager mgr = ActivityManager.getService();
  try {
      mgr.attachApplication(mAppThread, startSeq);
  } catch (RemoteException ex) {
      throw ex.rethrowFromSystemServer();
  }
```
看ActivityManager.getService()。翻阅代码可以查到，这个getService()返回的是ActivityManagerService的代理对象。
这里使用到了Binder通信。而作为service的就是ActivityManagerService了，也就是平常说的AMS。而ActivityThread则
作为它的client之一通过binder跨进程与ActivityManagerService通信。
```
public class ActivityManagerService extends IActivityManager.Stub 
        implements Watchdog.Monitor, BatteryStatsImpl.BatteryCallback {
        
       // ....省略代码
}
```
这里需要留意一下mAppThread，这个mAppThread是 ApplicationThread实例。它是ActivityThread的一个私有内部类。从类声
明上看，这里也是使用了binder通信。它作为IApplicationThread的service端。
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
回到`attach(false,0)`方法里，到AMS的`attachApplication()`方法看
```
 @Override
    public final void attachApplication(IApplicationThread thread, long startSeq) {
        synchronized (this) {
            int callingPid = Binder.getCallingPid();
            final int callingUid = Binder.getCallingUid();
            final long origId = Binder.clearCallingIdentity();
            attachApplicationLocked(thread, callingPid, callingUid, startSeq);
            Binder.restoreCallingIdentity(origId);
        }
    }
```
在`attachApplicationLocked(...)`方法内会通过传入的ApplicationThread实例thread执行到
`IApplicationThread #bindApplication(processName, appInfo,...,)`方法。这实际上已经是跨进程通信了，最终会
调用到身为服务端的`ApplicationThread#bindApplication()`里面。接着通过`sendMessage(H.BIND_APPLICATION, data);`
下面是H的相关代码：
```java
class H extends Handler {
    // ....省略代码
        public void handleMessage(Message msg) {
            if (DEBUG_MESSAGES) Slog.v(TAG, ">>> handling: " + codeToString(msg.what));
            switch (msg.what) {
                case BIND_APPLICATION:
                    Trace.traceBegin(Trace.TRACE_TAG_ACTIVITY_MANAGER, "bindApplication");
                    AppBindData data = (AppBindData)msg.obj;
                    handleBindApplication(data);
                    Trace.traceEnd(Trace.TRACE_TAG_ACTIVITY_MANAGER);
                    break;
                case EXIT_APPLICATION:
                    break;
            }
        }
 }
```
从上可以发现，H是一个Handler对象。sendMessage(H.BIND_APPLICATION, data)最终会调用`mH.sendMessage(msg);`。这个
mH就是H的实例。会由H的handleMessage(msg)进行处理。在`handleBindApplication(data)`方法内会创建Application实例并
调用onCreate()方法。    
回到AMS的`attachApplicationLocked()`的bindApplication(...)位置,有以下代码片段：
```
  // See if the top visible activity is waiting to run in this process...
    if (normalMode) {
     try {
        if (mStackSupervisor.attachApplicationLocked(app)) {
                  didSomething = true;
                }
        } catch (Exception e) {
                Slog.wtf(TAG, "Exception thrown launching activities in " + app, e);
                badApp = true;
            }
      }
```
mStackSupervisor是ActivityStackSupervisor的实例，


 app.thread.scheduleCreateService(r, r.serviceInfo,
                    mAm.compatibilityInfoForPackageLocked(r.serviceInfo.applicationInfo),
                    app.repProcState);

```
ClientLifecycleManager: 
    void scheduleTransaction(ClientTransaction transaction) throws RemoteException {
        final IApplicationThread client = transaction.getClient();
        transaction.schedule();
        if (!(client instanceof Binder)) {
            // If client is not an instance of Binder - it's a remote call and at this point it is
            // safe to recycle the object. All objects used for local calls will be recycled after
            // the transaction is executed on client in ActivityThread.
            transaction.recycle();
        }
    }

ClientTransaction :

    public void schedule() throws RemoteException {
        mClient.scheduleTransaction(this);
    }


    /** Target client. */
    private IApplicationThread mClient;

    /** Get the target client of the transaction. */
    public IApplicationThread getClient() {
        return mClient;
    }
    
 ActivityThread   
    
     /** Prepare and schedule transaction for execution. */
    void scheduleTransaction(ClientTransaction transaction) {
        transaction.preExecute(this);
        sendMessage(ActivityThread.H.EXECUTE_TRANSACTION, transaction);
       
```

api26: scheduleLaunchActivity()##sendMessage(H.BIND_SERVICE, s) -> handleLaunchActivity()





 

activity的创建： ActivityThread#startActivityNow() -> ActivityThread#performLaunchActivity();也有是调用
ActivityThread#handleLaunchActivity()的说法，但是后面被废弃了。


relaunchAllActivities -> scheduleRelaunchActivity# sendMessage(H.RELAUNCH_ACTIVITY, token);
收到消息后执行:  
 handleRelaunchActivityLocally((IBinder) msg.obj); -> 

 handleRelaunchActivity --> handleRelaunchActivityInner --> performPauseActivity


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