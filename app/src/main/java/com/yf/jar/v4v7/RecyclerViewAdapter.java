package com.yf.jar.v4v7;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.yf.jar.R;

import java.util.List;

/**
 * 类功能：
 * Created by lenovo on 2017/6/15 11:04.
 */
public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.MyViewHolder> {

    //这里需要注意的是，如果采用了自定的ViewHolder那么，需要在继承的时候用<RecyclerViewAdapter.MyViewHolder>泛型声明
    private List<String> mDatas;
    private Context mContext;

    public RecyclerViewAdapter(Context context, List<String> datas) {
        this. mContext=context;
        this. mDatas=datas;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // 实例化展示的view
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_rv_item, parent, false);
        // 实例化viewholder
        MyViewHolder viewHolder = new MyViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        //绑定数据
        holder.mTv.setText(mDatas.get(position));
    }

    @Override
    public int getItemCount() {
        return mDatas.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        TextView mTv;

        public MyViewHolder(View itemView) {
            super(itemView);
            mTv = (TextView) itemView.findViewById(R.id.item_tv);
        }
    }
}
