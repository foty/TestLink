package com.example;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

import com.example.testlink.ExpandChildListAdapter;
import com.example.testlink.R;

import java.util.List;

/**
 * Create by lxx
 * Date : 2020/9/29 10:05
 * Use by
 */
public class ExpandListAdapter extends BaseExpandableListAdapter {
    private List<ParentBean> list;

    public ExpandListAdapter(List<ParentBean> list) {
        this.list = list;
    }


    @Override
    public int getGroupCount() {
        return 3;
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return 1;
    }

    @Override
    public Object getGroup(int groupPosition) {
        return list.get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return list.get(groupPosition).getChildBeans().get(childPosition);
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public boolean hasStableIds() {
        return true;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
       View parentView = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_expand_parent,null);
        TextView textView = parent.findViewById(R.id.tvParentName);
        return parentView;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        View childView = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_expand_child,null);
        RecyclerView rv = childView.findViewById(R.id.recycler);
        rv.setLayoutManager(new GridLayoutManager(parent.getContext(),3));

        ExpandChildListAdapter rvAdapter = new ExpandChildListAdapter(list.get(groupPosition).getChildBeans());
        rv.setNestedScrollingEnabled(false);
        rv.setAdapter(rvAdapter);

        return childView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }
}
