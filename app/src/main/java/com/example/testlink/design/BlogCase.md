定义: 也叫合成模式，将对象组合成树形结构以表示“部分-整体”的层次结构，使得用户对单个对象和组合对象的使用具体有一致性。是一种
结构型设计模式。
模型角色： 
* 抽象构件: 定义组合对象的共有方法和属性，规范默认接口。
* 叶子构件: “叶子对象”，其以下没有其他分支，声明参与组合对象的行为接口。
* 树枝构件: 参与组合对象、其下有分支的树枝对象。它的作用是将树枝和叶子组合成一个树形结构，并且定义出管理子对象的方法。

场景模拟: 
说到组合模式的一个很常见的例子就是我们电脑里的文件夹。在一个总文件夹里面有若干文件夹，若干文件，里面文件夹下又有文件、文件夹。
整体上呈现一种树形的层级结构。下面使用组合模式来编码。
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
叶子对象代码: 
```
单个对象-文本类文件(叶子构件)
public class TextFile extends AbstractFile {

    public TextFile(String name){
        super(name);
    }

    @Override
    public void showContent() {
        System.out.println("文件： "+name);
    }
}

单个对象-图片类文件(叶子构件)
public class ImageFile extends AbstractFile {
    public ImageFile(String name) {
        super(name);
    }

    @Override
    public void showContent() {
        System.out.println("name=  " + name);
        // dosomething ....
    }
}
```
组合对象代码(树枝构件):
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

    public void deteleteFile(AbstractFile file) {
        files.remove(file);
    }

    @Override
    public void showContent() {
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

   Folders folder = new Folders("e");
   folder.addFile(file1);
   folder.addFile(file2);
   // 单个对象使用
   file3.showContent();
   file4.showContent();
   //组合对象使用
   folder.showContent();
```
应用场景：
* 需要表示一个对象整体与部分的层次结构；
* 对单个对象和组合对象的使用要求具有一致性的时候；

优点： 
* 可以统一地处理单个对象或组合对象，而不需要关心自己处理的是单个对象，还是组合对象，简化代码；

缺点：
* 不容易控制树枝结构的类型；
* 不容易使用继承方式来增加新的行为；

**小结**  
这