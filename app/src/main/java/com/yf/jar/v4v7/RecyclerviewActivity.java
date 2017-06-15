package com.yf.jar.v4v7;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.yf.jar.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class RecyclerviewActivity extends Activity {

    @BindView(R.id.recycle_view)RecyclerView recycleView;
    List<String> mDatas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recyclerview);
        ButterKnife.bind(this);
        mDatas = new ArrayList<String>();
        for ( int i=0; i < 40; i++) {
            mDatas.add( "item"+i);
        }
        //布局
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        //设置布局管理器，一定要设置，不然会出错
        recycleView.setLayoutManager(layoutManager);
        recycleView.setAdapter(new RecyclerViewAdapter(this,mDatas));
    }
}
