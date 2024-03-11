package com.example.testlink.calculate.dailyari.other;

import java.util.List;

public interface NestedInteger {

    // @return true if this NestedInteger holds a single integer, rather than a nested list.
    public boolean isInteger();

    // @return the single integer that this NestedInteger holds, if it holds a single integer
    // Return null if this NestedInteger holds a nested list
    public Integer getInteger();

    // @return the nested list that this NestedInteger holds, if it holds a nested list
    // Return null if this NestedInteger holds a single integer
    public List<NestedInteger> getList();


    public class A implements NestedInteger {

        int anInt;
        boolean isList;
        List<NestedInteger> list;

        public A(int i, boolean isList, List<NestedInteger> list) {
            anInt = i;
            this.isList = isList;
            this.list = list;
        }

        public A(int i) {
            anInt = i;
            this.isList = true;
            this.list = null;
        }

        @Override
        public boolean isInteger() {
            return isList;
        }

        @Override
        public Integer getInteger() {
            return anInt;
        }

        @Override
        public List<NestedInteger> getList() {
            return list;
        }
    }
}

