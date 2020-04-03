定义: 将请求封装成对象，使用不同的请求把客户端参数化，对请求排队或者记录请求日志，
可以提供命令的撤销或者恢复功能。命令模式是一种行为型设计模式。
角色模型:
* 抽象命令角色模型: 一个声明了所有具体命令类的抽象接口，定义需要执行的命令;
* 具体命令角色模型: 持有接收者实例，实现命令方法，在方法内调用接收者的行为；
* 调用者: 负责调用命令对象执行请求；
* 接收者: 负责具体执行请求；
下面是一个使用命令模式设计的简单例子:
场景需求: 在学生时期，有各种课程与若干课代表。现在需要检查所有课程的作业，班主任需要收集学生所有课程的作业情况,
由各课任老师上报各课程作业情况，课任老师就要求自己的课代表负责检查，统一上报检查情况。
分析: 这里可以分析出这么一个命令链: 班主任 -> 课任老师 -> 课代表 -> 普通学生。班主任要看作业情况，这是请求；检查
作业这是实现。最终场景与角色模型的对应关系就是：抽象命令是检查作业；具体命令是每一门课程的作业；调用者
是班主任；接收者是课代表。依次用代码设计出来:

```
//抽象命令角色(检查作业这一回事)
public interface Task {
    void checkTask();
}
```
因为在具体命令角色里会持有接收者实例，所以具体命令放到最后来定义，先把调用者跟接收者先创建出来:
```
//调用者(班主任)
public class HeadTeacher {

    private Task task;

    public HeadTeacher(Task task) {
        this.task = task;
    }

    /**
     *为了能够切换执行不同命令
     */
    public void setTask(Task task){
        this.task = task;
    }

    public void checkTask() {
        task.checkTask();
    }
}
```
```
//接收者(课代表)，实际执行请求的人
public class ClassRepresentatives {

    public void checkChinese(){
        Log.d("tag", "检查了语文作业");
    }

    public void checkMath(){
        Log.d("tag", "检查了数学作业");
    }

}
```
接下来分别是具体命令:
```
//检查语文作业
public class ChineseTask implements Task {

    private ClassRepresentatives representatives;

    public ChineseTask() {
        representatives = new ClassRepresentatives();
    }

    @Override
    public void checkTask() {
        representatives.checkChinese();
    }
}

//检查数学作业
public class MathTask implements Task {

    private ClassRepresentatives representatives;

    public MathTask() {
        representatives = new ClassRepresentatives();
    }

    @Override
    public void checkTask() {
        representatives.checkMath();
    }
}
```
场景使用:
```
 Task math = new MathTask();
 Task chinese = new ChineseTask();
 HeadTeacher teacher = new HeadTeacher(math);
 teacher.checkTask();
 //切换执行命令
 teacher.setTask(chinese);
 teacher.checkTask();
```
应用场景：
* 需要将请求调用者与请求接收者解耦时；
* 命令类不确定性高时，如频繁添加命令，删除命令；
* 对命令有额外要求时，如命令的撤销或者恢复。
优点： 
* 解耦。调用者与接收者没有任何依赖关系；
* 拓展性高。非常容易地添加或删除命令；
缺点：
* 会产生大量具体命令类。因为每一个具体操作都需要设计成一个具体命令类，这将增加系统的复杂性。

**小结**  
个人认为命令模式所带来的最大优势并不是将请求者与接收者解耦，而是在设计解耦过程中带来的可操作性。使用命令
模式可以很轻松地增加命令(虽然命令类多起来也会显得很重复累赘)，以及添加其他的操作，例如撤销，恢复(可以结合备忘录模式)。
如果没有使用命令模式设计代码，请求者直接调用接收者完成请求，这部分请求执行代码在多处使用就要多次创建，这会造成大量
重复代码；当需要额外操作时往往是在接收者加以各种逻辑，接收者代码可能会变得非常复杂，既包含各种请求的执行又包括各种执行条件，直接影响代码可读性。