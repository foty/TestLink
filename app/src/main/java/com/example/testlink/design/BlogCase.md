定义: 责任链模式，多个对象处理请求时，为了避免请求的发送者和接收者之间的耦合关系，将这些对象连成一条链。并沿着这条链传递该请求，直到有对象处理它为止。
责任链模式的重点就在一个“链”字上，沿着这个条链处理这个请求，返回结果。观察者模式是一种行为型设计模式。

模型角色： 
* 抽象处理者角色:抽象处理逻辑，定义一个方法设定和返回下一个处理者；
* 具体处理者: 具体的处理请求逻辑。接到请求后可以选择处理掉请求，或者将请求传递给下一个处理者。

责任链设计模式在我印象中最深的就是OkHttp中拦截链的设计。下面是添加拦截器一个方法。
```
  Response getResponseWithInterceptorChain() throws IOException {
    // 添加拦截器，构建处理链
    List<Interceptor> interceptors = new ArrayList<>();
    interceptors.addAll(client.interceptors());
    interceptors.add(retryAndFollowUpInterceptor);
    interceptors.add(new BridgeInterceptor(client.cookieJar()));
    interceptors.add(new CacheInterceptor(client.internalCache()));
    interceptors.add(new ConnectInterceptor(client));
    if (!forWebSocket) {
      interceptors.addAll(client.networkInterceptors());
    }
    interceptors.add(new CallServerInterceptor(forWebSocket));

    Interceptor.Chain chain = new RealInterceptorChain(
        interceptors, null, null, null, 0, originalRequest);
    return chain.proceed(originalRequest);
  }
```
`Interceptor`就是抽象处理者
```
public interface Interceptor {
  Response intercept(Chain chain) throws IOException;
  interface Chain {
    Request request();
    Response proceed(Request request) throws IOException;
    @Nullable Connection connection();
  }
}
```
具体处理者1：
```
public final class BridgeInterceptor implements Interceptor {
  @Override public Response intercept(Chain chain) throws IOException {
    // 。。。省略部分代码逻辑
    Response networkResponse = chain.proceed(requestBuilder.build()); // 传递给下一个处理者
    // 。。。省略部分代码逻辑
    return responseBuilder.build();
  } 
```
具体处理者2：
```
public final class CacheInterceptor implements Interceptor {
 @Override public Response intercept(Chain chain) throws IOException {
 // 。。。省略部分代码逻辑
    Response networkResponse = null;
    try {
      networkResponse = chain.proceed(networkRequest);
    } finally {
      if (networkResponse == null && cacheCandidate != null) {
        closeQuietly(cacheCandidate.body());
      }
    }
  // 。。。省略部分代码逻辑 
 } 
} 
```
上面只列举了2个具体处理者，此外还有ConnectInterceptor、CallServerInterceptor等等以及自定义的拦截器。它们都处理一部分自己的逻辑，然后传递给下一个处理者。可
这能光看这几行代码看不出来哪里像责任链了。主要是OkHttp在处理方式上变化了一下，每个具体处理者都将传递给下个处理者的逻辑交给了一个类去处理(RealInterceptorChain)
注意是同个类，不是同一个对象。每个具体处理者`chain.proceed()`实际都是调用 RealInterceptorChain#proceed():
```
public final class RealInterceptorChain implements Interceptor.Chain {

  public Response proceed(Request request, StreamAllocation streamAllocation, HttpCodec httpCodec,
      RealConnection connection) throws IOException {
      //。。省略代码
      
      RealInterceptorChain next = new RealInterceptorChain( 
      interceptors, streamAllocation, httpCodec, connection, index + 1, request);
      Interceptor interceptor = interceptors.get(index); // 获取下一个处理者
      Response response = interceptor.intercept(next); // 下一个处理者的处理逻辑
      
      // 。。省略代码
  }
}
```
用个简单的公司请假流程例子模拟:假定员工请假大于3天需要部门经理同意，大于7天需要老板同意，此外只需要组长级别同意即可。整个请假流程大概是：
员工-> 组长 -> 经理 -> 老板。这个审批流程实际就是一条审批链。下面用代码实现：   
抽象处理者：定义出抽象审批方法，提供设置下一个处理者的方法：
```
public abstract class ApprovalHandler {

    private ApprovalHandler handler;
    
    public abstract void approval(int day);

    protected void setHandler(ApprovalHandler handler) {
        this.handler = handler;
    }
    public void next(int day) {
        if (handler == null) {
            Log.d("TAG", "审批结束");
        } else {
            handler.approval(day);
        }
    }
}
```
具体处理者：
```
// 1级领导
public class AHandler extends ApprovalHandler {

    @Override
    public void approval(int day) {
        Log.d("TAG", "组长：同意");
        if (day < 3) {
            Log.d("TAG", "审批结束，请假" + day + "天");
        } else {
            next(day);
        }
    }
}

// 2级领导
public class BHandler extends ApprovalHandler {

    @Override
    public void approval(int day) {
        if (day <= 7) {
            Log.d("TAG", "经理：同意");
            Log.d("TAG", "审批结束，请假" + day + "天");
        } else {
            next(day);
        }
    }
}

// 最高领导
public class CHandler extends ApprovalHandler {

    @Override
    public void approval(int day) {
        Log.d("TAG", "老板：同意");
        Log.d("TAG", "审批结束，请假" + day + "天");
    }
}
```
使用：
```
AHandler a = new AHandler();
BHandler b = new BHandler();
CHandler c = new CHandler();
a.setHandler(b);
b.setHandler(c);
a.approval(1);
// a.approval(10);
```
log输出:
>组长：同意  
审批结束，同意请假1天  

>组长：同意  
 经理：同意  
 老板：同意  
 审批结束，同意请假10天  

应用场景：
* 一个请求需要一系列的处理工作
* 业务流的处理
* 对系统进行补充扩展

优点： 
* 提高系统的灵活性；
* 将请求和处理者分隔开，降低耦合度，请求不知道是哪个处理者处理的，处理者不用知道请求的全貌；

缺点：
* 降低程序性能，需要从链头处理到链尾，整个链条很长的时候，性能大幅度下降。
* 调试困难，请求与处理者分隔开，无法直接命中是哪一个处理者完成的请求

**小结**  
责任链一般用于处理类似流程节点之类的业务场景中。从它的命名上就可以看出一点端倪，一是责任分离，大伙儿各司其职；二是"链"，按照
执行顺序，依次处理。责任链上的各个角色都有机会处理请求，却不一定都会去处理请求，甚至都不处理请求(不属于责任范围内)。此外，责任
链(包括其他设计模式)还有很多的变种写法。就比如前面例子1(okHttp)和例子2。Ok中的例子反而更像是一条“链”，例子2中的“链”流程形
态体现更加明显。无论哪种写法，只要符合责任链的核心思想(责任分工，形成了"链")它都是责任链设计模式。