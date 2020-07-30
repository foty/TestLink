定义: 观察者模式也称发布/订阅模式。它定义对象间一种一对多的依赖关系，使得一个对象状态发生改变，所有依赖它
的对象都能够得到通知并自动更新。观察者模式是一种行为型设计模式。

模型角色： 
* 抽象主题角色: 即被观察对象，能添加，删除观察者,发出通知更新观察者。
* 抽象观察者角色: 该接口为所有观察者定义了一个更新自己的方法。
* 具体主题角色: 具体被观察者对象，保存了所有相关的观察者对象，当自身状态发生变化时，发出更新通知。
* 具体观察者角色: 实现抽象观察者更新方法，根据被观察者改变自动更新。

以一个时间+铃响的简单例子模拟这种设计模式。上代码  
抽象主题(抽象被观察者)，通常下，抽象被观察者都会定义一套注册，反注册观察者的接口，这是能通知到观察者的中药一环。
```
public interface Subject {
    void addObserver(Observer ob);

    void removeObserver(Observer ob);
    
     void notifyObserver();
}
```
抽象观察者：声明一个更新自己的方法。
```
public interface Observer {

    void update();
}
```
具体被观察者：实现抽象观察者具体接口，并且保存所有观察自己的观察者，发送通知
```
public class TimeSubject implements Subject {

    private List<Observer> observers = new ArrayList<>();

    @Override
    public void addObserver(Observer ob) {
        observers.add(ob);
    }

    @Override
    public void removeObserver(Observer ob) {
        observers.remove(ob);
    }

    @Override
    public void notifyObserver() {
        for (Observer ob:observers) {
            ob.update();
        }
    }
}
```
具体观察者：实现自己的更新自己的方法。
```
public class Clock implements Observer {

    @Override
    public void update() {
       System.out.println("咚~~~");
    }
}
```
对于类TimeSubject，实际上很少会这样直接使用，而是再次封装成另一种形态。为了贴近这种场景，声明一个新的类作为被观察者:
```
public class Timer {

    private TimeSubject subject;

    public Timer(){
        subject = new TimeSubject();
    }

    public void setObserver(Observer observer) {
        subject.addObserver(observer);
    }

    public void ring() {
        subject.notifyObserver();
    }
}
```
场景使用:
```
    Clock clock = new Clock();
    Timer timer = new Timer();
    timer.setObserver(clock);
    
    //通知铃响
    timer.ring();
```
应用场景：
* 关联行为。一个方面改变另一方面需要作出调整。
* 形成触发链，多级触发事件

优点： 
* 观察者与被观察者是抽象耦合的，这意味着具体主题与具体观察者更加容易拓展。
* 被观察者有着所有注册过的观察者，可以向所有的观察者发出通知，形成一条触发链，或者说一种触发机制。

缺点：
* 如果一个被观察者对象有很多观察者，通知到所有的观察者会花费很多时间；
* 线程安全问题。如果通知观察者是在另外的线程中操作时，需要考虑。
* 通知顺序问题。如果观察者、被观察者之前存在循环依赖关系，将会触发循环通知机制，产生严重后果。

**小结**  
观察者模式是一种应用广泛的设计模式，很多地方都能见到他的身影。例如ListView，RecyclerView。他的一大好处就是
充分解耦。只有最顶层抽象观察者与被观察者耦合(这也算是一个缺点吗?)，可以将这二者封装在独立的对象中，可以各自改变和
复用。像EventBus，Rx系列等等，个中妙处谁用谁知道。总之在联动行为场景，又想解耦，用观察者模式设计代码就对了。