Binder，一种IPC(Inter-process communication),也就是进程中间通信。Binder是Android提供的一
套进程间相互通信框架。
### Binder优点
* 快
* 安全

ipc | 数据拷贝次数 |
:---: | :---:
共享内存 | 0  |
Binder | 1  |
Soket、管道、Queue | 2|