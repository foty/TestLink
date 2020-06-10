定义: 也叫合成模式，将对象组合成树形结构以表示“部分-整体”的层次结构，使得用户对单个对象和组合对象的使用具体有一致性。是一种
结构型设计模式。
模型角色： 
* 抽象构件: 定义组合对象的共有方法和属性，规范默认接口。
* 叶子构件: “叶子对象”，其以下没有其他分支(部分)，声明参与组合对象的行为接口。
* 树枝构件: 参与组合对象、其下有分支的树枝对象(可以看成是另一个整体)。它的作用是将树枝和叶子组合成一个树形结构，并且定义出管理子对象的方法。

场景模拟: 
一个很典型的例子就是电脑里的文件夹。在一个总文件夹里面有若干文件夹，若干文件，里面文件夹下又有文件、文件夹。
整体上呈现一种树形的层级结构。像这样的数据普通写法是遍历(递归)，判断是整体的话就再写一层遍历(继续递归)，直到所有部分、整体都访问一遍。
但是这样写在层级多的情况下会产生很多相同或类似的代码(使用递归会比遍历效果更好一些)。使用组合模式后这种情况就能得到很大的改善
(虽然本质还是遍历)。下面使用组合模式来完成文件夹-文件场景的编码。  
按照模型角色划分顺序编码，首先定义好抽象构建:
```
//抽象构件
public abstract class AbstractFile {

    /**
     * 公共属性- 文件名称
     */
    protected String name;

    /**
     * 公共方法- 显示内容
     */
    public abstract void showContent();

    AbstractFile(String name){
        this.name = name;
    }
}
```
叶子构件(部分)代码: 
```
单个对象-文本类文件(叶子构件)
public class TextFile extends AbstractFile {

    public TextFile(String name){
        super(name);
    }

    @Override
    public void showContent() {
        System.out.println("TextFile name= "+name);
    }
}

单个对象-图片类文件(叶子构件)
public class ImageFile extends AbstractFile {
    public ImageFile(String name) {
        super(name);
    }

    @Override
    public void showContent() {
         System.out.println("ImageFile name= " + name);
        // dosomething ....
    }
}
```
组合构件(mini版整体)代码(树枝构件):
```
public class Folders extends AbstractFile {

    private ArrayList<AbstractFile> files;

    public Folders(String name) {
        super(name);
        files = new ArrayList<>();
    }

    public void addFile(AbstractFile file) {
        files.add(file);
    }

    public void deleteFile(AbstractFile file) {
        files.remove(file);
    }

    @Override
    public void showContent() {
     System.out.println("文件夹 name= "+name);
        for (AbstractFile file : files) {
            file.showContent();
        }
    }
}
```
场景使用:
```
    AbstractFile file1 = new TextFile("aaa");
    AbstractFile file2 = new TextFile("bbb");
    AbstractFile file3 = new TextFile("ccc");
    AbstractFile file4 = new TextFile("ddd");
    AbstractFile image = new ImageFile("简约风格");

    Folders folder = new Folders("e");
    Folders child1 = new Folders("f");
    Folders child2 = new Folders("g");
    folder.addFile(file1);
    folder.addFile(file2);
    folder.addFile(image);
    //
    child1.addFile(file3);
    folder.addFile(child1);
    //
    child2.addFile(file4);
    folder.addFile(child2);

    file3.showContent();
    file4.showContent();
    folder.showContent();
```
输出结果:

从使用外表形式上看，对文件-文件夹这么一个对象的遍历就仅仅是调用一个方法。使用组合模式后更加直接的感受是无论我是file(部分)还是
Folder(整体)，我对它们的使用方式是没有区别的。或者说不用去处理我当前的对象是file还是folder。兼容性极高。

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

**小结**  
组合模式的终极目的就是让整体-部分具有一致性，对其可以使用同样操作(参考上面提到的例子)。当整体-部分具有了一致性的时候可以简
化很多步骤。例如商城类应用一般都有一个购物车。最终结算时要计算一个总价。在设计上可以将购物车-商品当做一个整体-部分来设计。计
算总价时直接使用抽象构件的获取价格的方法即可。