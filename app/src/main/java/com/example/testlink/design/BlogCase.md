定义: 将一个类的接口变换成客户端所期望的另一种接口，使得原本因为接口不匹配而无法共同工作的两个类能够在一起工作。
适配器模式是一种结构型设计模式。
模型角色： 
* 目标角色: 要转换成的目标，通常声明为接口。
* 适配者角色: 需要被转换成目标角色的角色。
* 适配器角色: 将适配者转换成目标，是适配器模式的核心。通常使用继承或者类关联实现目标转换。

场景模拟: 市场上有些手机是有充电孔跟耳机孔的(米max)，有些是没有耳机孔只有充电孔的(米8)。现有一副耳机，对于有专门耳机孔的手机很容易
就可以使用。但是没有耳机孔的手机就需要一个转接头才能连上耳机。这个转接头的作用就是适配器的作用，他将米8手机适配成像米max
手机一用使用同一副耳机。下面使用代码实现。

```
//max手机
public interface MiMaxMold {
    /**
     * 有充电孔，有耳机孔
     */
    void chargeAndEar();
}

public class MiMax implements MiMaxMold {
    @Override
    public void chargeAndEar() {
        System.out.println("一边充电，一边听歌，真爽。");
    }
}

```
m8手机: 
```
public interface Mix8Mold {
    /**
     * 只有充电孔
     */
    void charge();
}

public class Mi8 implements Mix8Mold {
    @Override
    public void charge() {
        System.out.println("充电时好想听歌啊。");
    }
}
```
转接头(适配器)。注意的是适配器一定是使用适配者适配目标，顺序不能弄错。通俗就是:实现目标角色，持有适配者实例。
```
public class EarAdapter implements MiMaxMold {
    
    /**
    * 适配者实例
    */
    private Mix8Mold mi8;

    public EarAdapter(Mix8Mold mold) {
        mi8 = mold;
    }

    @Override
    public void chargeAndEar() {
        mi8.charge();
    }
}
```
到这其实适配器模式就已经写完了，为了考虑到将模拟场景上的所有角色都具体化，更加形象。所以添加一个耳机对象。
```
public class EarPhone {

    private MiMaxMold max;

    /**
     * 这个耳机是max的原装耳机，默认是max手机。
     */
    public EarPhone (){
        max = new MiMax();
    }

    /**
     * 耳机听歌或充电
     */
    public void listen() {
        max.chargeAndEar();
    }

    /**
     * 使用转接头
     * @param mold
     */
    public void setAdapter(MiMaxMold mold){
        max = mold;
    }
}
```
场景使用:
```
    //虚拟耳机对象的写法
    MiMax max = new MiMax();
    max.chargeAndEar();

    Mix8Mold mold1 = new Mi8();
    EarAdapter adapter = new EarAdapter(mold1);
    adapter.chargeAndEar();


    //使用耳机对象写法
    EarPhone earPhone = new EarPhone();
    earPhone.listen();

    Mix8Mold mold = new Mi8();
    earPhone.setAdapter(new EarAdapter(mold));
    earPhone.listen();
```
无论耳机对象参不参与，输出结果都是一样的:
>  一边充电，一边听歌，真爽。    
   充电时好想听歌啊。
   

应用场景：
* 需要开发的业务功能在现有系统已经存在，但待开发的功能与当前系统的接口规范不兼容，如果重新开发成本又很高，这时用适配器模式是最合适的。

优点： 
* 让两个毫无关系类能咋一起工作；
* 增加类的透明度；
* 提高类的复用性,增加代码灵活度；

缺点：
* 适配器模式本身并没有什么缺点，但是在后续功能变化中更换适配器过程可能会变复杂。

**小结**  
适配器模式可以算是一种比较特殊的设计模式。平常开发可能很少会用到。但在一些源码上会见到很多。适配器模式主要作用还是将
现有系统功能与待开发的不兼容现有系统功能的这两者能都联系到一起来工作。不改变现有功能就能兼容下新的功能(?我怎么想到
了装饰模式)，节约了开发成本。对于一些源码，sdk的开发升级来讲，若要破坏原有系统结构兼容新功能，开发成本以及风险是巨大的。
故使用适配器模式兼容还是很有必要的。
