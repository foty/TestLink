创建型模式：单例、工厂方法、抽象工厂、建造者、原型   
结构型模式：代理、装饰、组合、桥梁(桥接)、外观、享元、适配器   
行为型模式(1)： 命令、策略、模板方法、责任链、迭代器   
行为型模式(2)：状态、备忘录、观察者、访问者、中介者、解释器     

<h3>1.单例模式</h3>

<h3>2.工厂模式</h3>

<h3>3.抽象工厂模式</h3>

<h3>4.原型模式</h3>

<h3>5.代理模式</h3>

<h3>6.策略模式</h3>

针对对一组算法，将每一个算法封装到具有公共接口的独立类中，使得他们可以相互替换，可以在不影响到客户端情况下发生改变。   
3个模型:
* 抽象策略模型：定义为公共接口，声明策略;
* 具体策略：实现抽象策略模型，提供具体的策略计算逻辑;
* 环境模型(策略持有者)：包含策略模型引用，策略方法的调用等，可以是独立的一个类也可以就是调用策略本身对象。

应用场景:
* 需要根据不同环境动态地在几种实现算法中选择一种时;
* 实现算法相互独立，并且要求隐藏具体算法的实现细节时;

优势:
* 可以避免使用多重条件语句;
* 策略类的继承或实现关系，使公共策略部分可以迁移到父类中，避免代码重复;
* 策略类可以相互替换，相互独立并且不会对调用者产生影响，灵活，安全;

劣势:
* 要理解所有策略算法的区别，以便能选择最合适策略类，增加时间开销;
* 可能会产生很多的具体策略类，
___


<h3>7.建造者模式 </h3> “色彩斑斓”

定义: 将一个复杂对象的构建与他的表示分离，使得可以使用同样的构建构成构成不同的表示。   
4种模型:
* 产品模型: 特定的产品，具有复杂的组成部分,需要建造的实例;
* 抽象建造者: 规范产品模型的各个组成部分，并进行抽象;
* 具体建造者: 实现抽象建造模型的所有方法，并且提供一个创建好的产品对象;
* 导演者(或生产者)模型: 负责产品部分建造的顺序;

应用场景:
* 相同的方法，不同的执行顺序产生不同的结果;
* 产品很复杂，多种部件(属性)，不同的组合方式产生不同的结果;

优点:
* 良好的封装性,隐藏内部细节，安全性提高;
* 部件独立组装，扩展性强;

缺点:
* 产品类型固定，限制产品层次的扩展;
* 有大量零件时，增加零件组装的复杂难度;
___


<h3>8.享元模式</h3>  “围棋少年”

定义: 使用共享对象可以有效地支持大量的细粒度的对象。享元对象做到共享的关键是区分内部状态和外部状态。可以降低大量重复的，细粒度的类在
内存中的开销。也是一种行为型模式。
4个模型角色:
* 抽象享元角色: 享元对象的父类，提供具体享元模型的公共逻辑接口。需要外部状态操作时可以通过参数的形式将外部状态传入;
* 具体享元角色: 实现抽象享元接口，提供具体的逻辑处理;
* 享元工厂: 负责享元角色的创建和管理，实质就是一个容器池,保存着享元角色实例。通常做成键值类型数据保存;
___
内部状态: 存储在享元对象内部，可以共享，不随外部环境改变而改变;
外部状态: 随环境改变而改变且不可以共享的对象，在享元对象创建后，如果需要使用再传入到享元对象内部;

应用场景:
* 系统中存在大量相同或相似的对象，这些对象耗费大量的内存资源;
* 细粒度对象都具备较接近的外部状态，而且内部状态与环境无关;

优点:
* 大幅度减少内存中的对象数量，降低内存的使用，提高性能;

缺点:
* 增加了系统的复杂性，要区分外部状态与内部状态;
---

<h3>9.桥接模式</h3> “白玫瑰红玫瑰”

定义: 将抽象与实现解耦，使得俩者可以独立的变化,行为型设计模式;
4个模型角色:
* 抽象化角色模型: 抽象角色，持有实现化角色引用,;
* 修正抽象化模型: 继承至抽象化角色，实现抽象化角色定义的方法逻辑并扩展自己本身的特性;
* 实现化角色模型: 通常声明为接口，提供对应的逻辑方法;
* 具体实现化角色模型: 对实现化角色中的逻辑方法提供具体的实现;
---
应用场景:
* 如果设计上需要使得抽象角色与具体角色之间增加更多的灵活性，减少之前的联系，可以使用这种模式;
* 会因为多重继承关系导致系统类的个数急剧增加时，可以使用桥接模式;
* 一个类会有多种可以独立变化的维度(或属性方法);

优点:
* 抽象与实现分离，使得它们可以独立变化提高灵活性,扩展能力强;
* 降低耦合;
* 有助于对面向对象的设计原则的理解，形成正确的设计思想，培养良好的设计风格;
---

<h3>10.装饰模式</h3>

定义: 指在不改变现有对象结构的情况下，动态地增加对象额外功能。属于对象结构型设计模式。
模型角色:
* 抽象产品模型: 具体产品与抽象装饰的父类，一般使用接口定义;
* 具体产品模型: 实现抽象产品，提供抽象产品的具体实现,也是升级产品(具体装饰)基本模型;
* 抽象装饰模型: 实现抽象产品,持有需要升级的产品(具体产品)的实例;
* 具体装饰模型: 具体产品的升级版,有具体产品的功能。继承抽象装饰，提供额外的功能实现。

应用场景:
* 需要给一个现有类增加额外功能,而又不能使用继承的方法进行扩充时。
* 对类功能的不确定,能够灵活地增加修改功能。

优点:
* 可以动态的扩展功能，同时也可以选择不同的装饰器实现不同功能，结构灵活。
* 
缺点:
* 随着业务的庞大，装饰层过多的话，维护起来比较困难
---

<h3>11.备忘录模式</h3> 

定义:在不破坏封装性的前提下，捕获一个对象的内部状态，并在该对象之外保存这个状态，以便以后当需要时能将该对象恢复到原先保存的状态。

模型角色:
* 备忘角色: 本身需要恢复到某一时刻状态，提供创建备忘录和恢复备忘录的基本功能;
* 备忘录管理角色: 管理备忘录，保存或者返回备忘录;
* 备忘录角色: 备忘录对象，记录备忘角色的某种状态，提供这些状态;

应用场景:
* 需要用到保存数据并且能够恢复数据场景。比如在购物车里删除商品或者清空了要恢复到之前的状态时，需要用到类似“撤回”这种功能就
使用备忘录设计模式。

特点:
能够保存一个对象，能够恢复到之前保存的状态。就是类似撤销恢复了。
良好的封装性，除了备忘对象本身外，其他的对象不能够访问到备忘录，即使是备忘录管理者也不能修改备忘录。

缺点:
* 如果要保存的内部状态信息过多或者特别频繁,将会占用比较大的内存资源;
---

<h3>12.外观模式 </h3>

定义: 每个子系统的外部与其内部的通信必须通过同一个对象进行。即提供一个访问子系统的的接口，只有
通过这个接口才能访问子系统的行为。外观模式是一种结构型设计模式。

模型角色:
* 外观角色: 所有的访问子系统的接口，持有所有子系统实例;
* 子系统: 对应各个业务的类;

应用场景：
* 为复杂的模块或者子系统提供外界访问接口；
* 子系统独立，外界对子系统的访问只要黑箱操作；
* 预防风险扩散，控制访问操作；

优点：
* 使用方便，不需要直接管理子系统类，只需要管理一个外观类即可；
* 减少系统的相互依赖。所有系统都只对外观角色依赖，与子系统无关；
* 对于子系统而言提高了灵活性，不管子系统如何变化，只要外观类不变化就不会产生影响；
缺点：
* 不符合开闭原则(对修改关闭，对扩展开放)，通常修改了子系统，外观类也要相应修改；
---

<h3>13.状态模式</h3>

定义:当一个对象内在状态改变时允许改变行为，这个对象像是改变了其类型。状态模式是一种行为型设计模式
模型角色：
* 抽象状态角色：对环境角色的业务定义行为接口；
* 具体状态角色：实现一种状态下对应的具体行为；
* 环境角色：持有状态类的实例，返回当前状态并负责具体状态的切换，挺像一个管理器的嗷。

应用场景：
* 行为跟随状态变化而改变并且状态较多时；

优点： 
* 结构清晰
* 良好的封装性
缺点：
* 子类太多，管理复杂
---

<h3>14.命令模式</h3>

定义: 将请求封装成对象，使用不同的请求把客户端参数化，对请求排队或者记录请求日志，可以提供命令的撤销或者恢复功能。
模型角色:
* 抽象命令角色模型: 一个声明了所有具体命令类的抽象接口，定义需要执行的命令;
* 具体命令角色模型: 持有接收者实例，实现命令方法，在方法内调用接收者的行为；
* 调用者: 负责调用命令对象执行请求；
* 接收者: 负责具体执行请求；

应用场景：
* 需要将请求调用者与请求接收者解耦时；
* 命令类不确定性高时，如频繁添加命令，删除命令；
* 对命令有额外要求时，如命令的撤销或者恢复。
优点： 
* 解耦。调用者与接收者没有任何依赖关系；
* 拓展性高。非常容易地添加或删除命令；
缺点：
* 会产生大量具体命令类。因为每一个具体操作都需要设计成一个具体命令类，这将增加系统的复杂性。

<h3>15.组合模式</h3>
  
定义: 也叫合成模式，将对象组合成树形结构以表示“部分-整体”的层次结构，使得用户对单个对象和组合对象的使用具体有一致性。是一种
结构型设计模式。
模型角色： 
* 抽象构件: 定义组合对象的共有方法和属性，规范默认接口。
* 叶子构件: “叶子对象”，其以下没有其他分支，声明参与组合对象的行为接口。
* 树枝构件: 参与组合对象、其下有分支的树枝对象。它的作用是将树枝和叶子组合成一个树形结构，并且定义出管理子对象的方法。

应用场景：
* 需要表示一个对象整体与部分的层次结构；
* 对单个对象和组合对象的使用要求具有一致性的时候(方便、快捷)；

优点： 
* 可以统一地处理单个对象或组合对象，而不需要关心自己处理的是单个对象，还是组合对象，简化代码；
* 往组合体内添加新的对象类型时不会对当前代码产生影响，兼容性高；

缺点：
* 不容易控制树枝结构的类型。相对来说，兼容性高是优点也是缺点。
* 不容易使用继承方式来增加新的行为。构建组合关系的时候已经将通用属性行为定义好，由子类继承才使得部分-整体
拥有一致性，对于特性就很难再使用继承方式增加了。
* 设计复杂。考虑到部分-整体的一致性关系，要花费更多时间整理层次关系，甚至额外的特性。


<h3>16.适配器模式</h3>

定义: 将一个类的接口变换成客户端所期望的另一种接口，使得原本因为接口不匹配而无法共同工作的两个类能够在一起工作。
适配器模式是一种结构型设计模式。
模型角色： 
* 目标角色: 要转换成的目标，通常声明为接口。
* 适配者角色: 需要被转换成目标角色的角色。
* 适配器角色: 将适配者转换成目标，是适配器模式的核心。通常使用继承或者类关联实现目标转换。

应用场景：
* 需要开发的业务功能在现有系统已经存在，但待开发的功能与当前系统的接口规范不兼容，如果重新开发成本又很高，这时用适配器模式是最合适的。

优点： 
* 让两个毫无关系类能咋一起工作；
* 增加类的透明度；
* 提高类的复用性,增加代码灵活度；

缺点：
* 适配器模式本身并没有什么缺点，但是在后续功能变化中更换适配器过程可能会变复杂。


<h3>17.观察者模式</h3> 

定义: 观察者模式也称发布/订阅模式。它定义对象间一种一对多的依赖关系，使得一个对象状态发生改变，所有依赖它
的对象都能够得到通知并自动更新。观察者模式是一种行为型设计模式。

模型角色： 
* 抽象主题角色: 即被观察对象，能添加，删除观察者,发出通知更新观察者。
* 抽象观察者角色: 该接口为所有观察者定义了一个更新自己的方法。
* 具体主题角色: 具体被观察者对象，保存了所有相关的观察者对象，当自身状态发生变化时，发出更新通知。
* 具体观察者角色: 实现抽象观察者更新方法，根据被观察者改变自动更新。

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


<h3>18.责任链</h3>

定义: 责任链模式，多个对象处理请求时，为了避免请求的发送者和接收者之间的耦合关系，将这些对象连成一条链。并沿着这条链传递该请求，直到有对象处理它为止。
责任链模式的重点就在一个“链”字上，沿着这个条链处理这个请求，返回结果。观察者模式是一种行为型设计模式。

模型角色： 
* 抽象处理者角色:抽象处理逻辑，定义一个方法设定和返回下一个处理者；
* 具体处理者: 具体的处理请求逻辑。接到请求后可以选择处理掉请求，或者将请求传递给下一个处理者。

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


<h3>19.迭代器</h3>

<b>全模式对比</b>(15/23)
<策略、装饰、状态、桥接>
<备忘录>
<工厂、抽象工厂>
<外观>
<代理>
<享元>
<单例>
<建造者、责任链>
<原型>
<命令>
<组合>
<适配器>
<观察者>


<迭代器>
<模板方法>
<访问者>
<中介者>
<解释器>
