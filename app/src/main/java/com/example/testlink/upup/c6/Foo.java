package com.example.testlink.upup.c6;

/**
 * Create by lxx
 * Date : 2020/10/23 17:08
 * Use by
 */
public class Foo {
    public static void main(String[] args) {
        new Foo().print();
    }

    public void print(){
        int supHash = super.hashCode();
        System.out.println("sup= "+supHash);
        int thisCode = this.hashCode();
        System.out.println("this= "+thisCode);
    }


    public int hashCode(){
        return 111;
    }
}

/**
 * 1.查看字节码文件：
 * 使用javac 得到class文件
 * 使用javap 得到字节码文件
 *
 * eg 在目标文件目录下有个类叫A.java：
 *
 * >> javac A.java    ===>得到class文件
 * >> javap -c A.class  ===>得到了字节码文件。
 *
 *
 * 2.查看类中常量池的具体信息:
 * eg 在目标文件目录下有个类叫A.java：
 *
 * >> javap -v A      ===> 输出常量池的具体信息(如果没有输出，尝试先得到.class文件)
 */
