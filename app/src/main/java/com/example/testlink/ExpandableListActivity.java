package com.example.testlink;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.ExpandableListView;

import com.example.ExpandListAdapter;
import com.example.ParentBean;

import java.util.ArrayList;
import java.util.List;

/**
 * Create by lxx
 * Date : 2020/9/29 9:54
 * Use by
 */
public class ExpandableListActivity extends Activity {


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_expandable_list);


        ExpandableListView expandList = findViewById(R.id.expandList);

        List<ParentBean> pList = new ArrayList<>();
        List<ChildBean> cList = new ArrayList<>();

        cList.add(new ChildBean());
        cList.add(new ChildBean());
        cList.add(new ChildBean());
        cList.add(new ChildBean());
        cList.add(new ChildBean());
        cList.add(new ChildBean());

        ParentBean pbean = new ParentBean();
        pbean.setChildBeans(cList);
        pbean.setName("123123");

        pList.add(pbean);
        pList.add(pbean);
        pList.add(pbean);
        pList.add(pbean);
        pList.add(pbean);

        ExpandListAdapter adapter = new ExpandListAdapter(pList);
        expandList.setAdapter(adapter);

    }
}
