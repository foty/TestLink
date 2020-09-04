### binder 概念  
Binder，一种IPC(Inter-process communication),也就是进程中间通信。Binder是Android提供的一套进程间相互通信框架。
从Android应用层的角度来说， Binder是客户端和服务端进行通信的媒介。这里要引出另外俩种概念：IPC，AIDL.  

IPC：Inter Process Communication，跨进程间通信。  
AIDL：Android Interface Definition Language,即Android接口定义语言，用于生成可以在Android设备上两个进程之间进行进程间通信的代码。  
三者的关系：   
AIDL是基于Binder机制实现Android上的IPC。

### Binder优点
* 快:  要一次数据拷贝，性能上仅次于共享内存
* 安全:  为每个APP分配UID，进程的UID是鉴别进程身份的重要标志
* 稳定性好:  基于 C/S 架构，职责明确、架构清晰，因此稳定性好

各种ipc的传输效率：  

ipc | 数据拷贝次数 |
:---: | :---:
共享内存 | 0  |
Binder | 1  |
Soket、管道、Queue | 2|


Binder机制下的角色模型： 
* client进程
* service进程
* serviceManager进程
* binder驱动


linux 文件操作：
* open: 创建文件。
* mmap: 内存映射。就是将用户空间的一段内存区域映射到内核空间，映射成功后，用户对这段内存区域的修改可以直接反映到内核空间，同样，内核空
间对这段区域的修改也直接反映用户空间。那么对于内核空间<- ->用户空间两者之间需要大量数据传输等操作的话效率是非常高的。
* poll: 轮训文件描述符，查看描述符是否就绪。
* ioctl: 设备驱动程序中设备控制接口函数,通过调用ioctl可以修改驱动相关的内容。







