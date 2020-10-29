
##### Handler
一个线程有几个looper，如果你说一个，他会问，如何保证looper唯一  
我们能在主线程直接new无参handler吗  
我们应该怎么样在子线程问handler  
为什么主线程不用调用looper.prepar和looper.looper   
我们的looper通过什么实现与线程关联  
为什么looper死循环应用(UI)不卡顿 (那你谈谈epoll机制)  
如果我们的子线程没有消息处理的情况下，我们如何优化looper   
message上限怎么办。