Android 项目中asset目录和res的区别 
* res中存放可编译的资源文件,这种资源文件系统会在R.java里面自动生成该资源文件的ID,(除了raw,raw不会被编译);
* res下的文件夹都是有特殊含义的,如anim特指动画类,raw指音频类等等。不能顺便创建文件夹;
* res下的资源通过R.id.xx来访问;

* asset目录的打包后会原封不动的保存在apk包中，不会被编译成二进制;
* asset资源访问时使用AssetManager,以流形式操作;
* asset资源可以自由创建子文件夹，并且都有效，可以访问到;


已知“onSaveInstanceState 会在系统意外杀死 Activity 时调用，或者横纵屏切换的时候调用”。   
问：随着Android SDK版本的变化，这一方法的调用时机有哪些变化？     

Activity的onSaveInstanceState回调时机，取决于app的targetSdkVersion：    
* targetSdkVersion低于11的app，onSaveInstanceState方法会在Activity.onPause之前回调；
* targetSdkVersion低于28的app，则会在onStop之前回调；
* 28之后，onSaveInstanceState在onStop回调之后才回调r


retrofit 提交图片：
文件格式为：  
 map.put("map key(1)" + "\"; filename=\"" + '文件(2)'.getName(),
                    RequestBody.create(MediaType.parse("image/png"), '文件(3)'));  
 
其中，除了 1,2,3外,其他部分为固定情况




从项目说起
项目使用了websocket实现im功能，为什么使用websocket，有什么考量吗？

说一下性能优化做了哪些？
内存优化是怎么优化的？
oom是怎么线上跟踪的，跟踪了之后怎样解决？
说一下内存泄漏
为什么handler会导致内存泄漏
为什么内部类会导致内存泄漏

说一下handler机制
handler是怎样实现线程通信的
looper是怎样处理消息的
epoll机制是怎样实现阻塞的
postdelay是怎么实现的

说一下java多线程
synchronized原理
java CAS
说一下同步队列和等待队列
怎样实现三个线程顺序执行？
join方法的底层实现
如何用三个线程输出0-100？

Binder机制原理 (binder)
为什么Android要使用Binder机制？
Android底层有用到socket机制吗？
介绍一下AIDL

有用过什么插件化相关的技术吗？（没用过，就没往下问了）

Android开机后发生了什么？(启动流程)
应用的启动流程
启动流程里谁孵化了进程
启动流程里Application是谁启动的
Android程序本身也就是一个java程序，那么main方法是在哪里执行的

java虚拟机的内存模型有哪些 (jvm)
堆和栈的区别，堆里面放什么，栈里面放什么
说一下gc
gc算法有哪些

tcp和udp的区别，具体使用场景 

https和http的区别
说一下ssl握手，ssl握手和tcp握手的顺序
ssl原理
证书是怎样认证的

说一下HashMap
为什么要使用红黑树
说一下HashMap扩容

ams,pms,wms,apo,gradle,jvm,launcher








实用android studio 插件：   
codeGlace - 代码预览  
Gson - json解析  
android drawable preview 资源预览  
(黄油刀)Android ButterKnife Zelezny - 代码注解(findViewById)  
