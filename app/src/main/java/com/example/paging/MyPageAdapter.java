package com.example.paging;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.paging.PagedListAdapter;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;

/**
 * 注意：这里继承的是 PagedListAdapter
 * 调用父类的构造方法，传入一个callback(判断是不是一模一样，判断是不是内容相等)
 */

public class MyPageAdapter extends PagedListAdapter<Student,MyPageAdapter.InnerViewHolder> {

    protected MyPageAdapter() {
        super(new DiffUtil.ItemCallback<Student>() {
            @Override
            public boolean areItemsTheSame(@NonNull Student oldItem, @NonNull Student newItem) {
                return oldItem.getId() == newItem.getId();
            }

            @Override
            public boolean areContentsTheSame(@NonNull Student oldItem, @NonNull Student newItem) {
                return oldItem.getStudentNumber() == newItem.getStudentNumber();
            }
        });
    }

    //根据我们定义的item视图创建viewHolder
    @NonNull
    @Override
    public InnerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.item_view, parent, false);
        return new InnerViewHolder(view);
    }

    //绑定数据
    @Override
    public void onBindViewHolder(@NonNull InnerViewHolder holder, int position) {
        Student student = getItem(position);
        if (student == null) {
            //容器有了，内容还没有的时候的显示
            holder.textView.setText("loading...");
        }else {
            holder.textView.setText(String.valueOf(student.getStudentNumber()));
        }
    }

    //自定义viewHolder
    public class InnerViewHolder extends  RecyclerView.ViewHolder{
        TextView textView;
        public InnerViewHolder(@NonNull View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.textView);
        }
    }
}
