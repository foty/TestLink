定义: 也叫合成模式，将对象组合成树形结构以表示“部分-整体”的层次结构，使得用户对单个对象和组合对象的使用具体有一致性。是一种
结构型设计模式。
模型角色： 
* 抽象构件: 定义组合对象的共有方法和属性，规范默认接口。
* 叶子构件: “叶子对象”，其以下没有其他分支，声明参与组合对象的行为接口。
* 树枝构件: 参与组合对象、其下有分支的树枝对象。它的作用是将树枝和叶子组合成一个树形结构，并且定义出管理子对象的方法。

场景模拟: 
说到组合模式的一个很常见的例子就是我们电脑里的文件夹。文件夹下面有文件，又有文件夹。整体结构就像是一个树，有一个很明显的层级结构。
下面使用组合模式来编码。

```
//抽象构建
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