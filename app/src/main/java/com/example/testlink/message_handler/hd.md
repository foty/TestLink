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



在ActivityThread的`main()`方法looper之前先创建了ActivityThread对象,
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





##### Handler面试的几个问题
Handler 的基本原理  
子线程中怎么使用 Handler  
MessageQueue 获取消息是怎么等待  
为什么不用 wait 而用 epoll 呢？  
多个线程给 MessageQueue 发消息，如何保证线程安全  
非 UI 线程真的不能操作 View 吗    
一个线程有几个looper，几个handler，如果你说一个，他会问，如何保证looper唯一        
> 1个  
我们能在主线程直接new无参handler吗  
> 可以  
主线程是一直处于死循环状态，那么android中其他组件，比如activity的生命周期等式如何在主线程执行的？  
>开启了子线程/新进程执行  
子线程能new handler吗?我们应该怎么样在子线程new handler  
> 在特殊情况处理下可以在子线程new handler。  
为什么主线程不用调用looper.prepar和looper.looper   
我们的looper通过什么实现与线程关联  
为什么looper死循环应用(UI)不卡顿(anr) (那你谈谈epoll机制)  
如果我们的子线程没有消息处理的情况下，我们如何优化looper   
Handler 怎么进行线程通信，原理是什么？  
ThreadLocal 的原理，以及在 Looper 是如何应用的？  
Handler#post(Runnable) 是如何执行的  
Handler#sendMessage() 和 Handler#postDelay() 的区别？  
多个 Handler 发消息时，消息队列如何保证线程安全？
为什么 MessageQueue 不设置消息上限，message上限怎么办。  
Looper 死循环为什么不阻塞主线程？  
Handler内存泄漏的原因？  
Message.callback 与 Handler.callback 哪个优先？  
Handler.callback 和 handlemessage() 都存在，但 callback 返回 true，handleMessage() 还会执行么？  
IdleHandler 是什么？怎么使用，能解决什么问题？  
同步屏障问题  
Looper会一直消耗系统资源吗？  
android的Handle机制，Looper关系，主线程的Handler是怎么判断收到的消息是哪个Handler传来的？  
Handler机制流程、Looper中延迟消息谁来唤醒Looper？  
handler机制中如何确保Looper的唯一性？  
Handler 是如何能够线程切换，发送Message的？
