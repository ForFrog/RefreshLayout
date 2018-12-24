package com.maxchen.refreshlayout;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.maxchen.lib.SimpleRefreshLayout;
import com.maxchen.refreshlayout.widgets.SimpleBottomView;
import com.maxchen.refreshlayout.widgets.SimpleLoadView;
import com.maxchen.refreshlayout.widgets.SimpleRefreshView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private SimpleRefreshLayout mSimpleRefreshLayout;
    private RecyclerView mRecyclerView;
    private Adapter mRecyclerAdapter;

    private List<String> mStringList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mSimpleRefreshLayout = (SimpleRefreshLayout) findViewById(R.id.simple_refresh);

        if (mSimpleRefreshLayout != null) {
            mSimpleRefreshLayout.setHeaderView(new SimpleRefreshView(this));
            mSimpleRefreshLayout.setFooterView(new SimpleLoadView(this));
            mSimpleRefreshLayout.setBottomView(new SimpleBottomView(this));
            mSimpleRefreshLayout.setOnSimpleRefreshListener(new SimpleRefreshLayout.OnSimpleRefreshListener() {
                @Override
                public void onRefresh() {
                    addRefreshData();
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            mSimpleRefreshLayout.onRefreshComplete();
                            mRecyclerAdapter.setmStringList(mStringList);
                            mRecyclerAdapter.notifyDataSetChanged();
                        }
                    }, 1000);
                }

                @Override
                public void onLoadMore() {
                    addLoadMoreData();
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            mSimpleRefreshLayout.onLoadMoreComplete();
                            mRecyclerAdapter.setmStringList(mStringList);
                            mRecyclerAdapter.notifyDataSetChanged();
                            if (mRecyclerAdapter.getItemCount() >= 30)
                                mSimpleRefreshLayout.showNoMore(true);
                        }
                    }, 1000);
                }
            });
        }

        initData();

        mRecyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setAdapter(mRecyclerAdapter = new Adapter(this));
    }

    private void addRefreshData() {
        mStringList.add(0, "新加刷新数据");
    }

    private void addLoadMoreData() {
        mStringList.add("新加加载数据");
    }

    private void initData() {
        for (int i = 0; i < 15; i++) {
            mStringList.add(String.format("第%s条数据", i));
        }
    }
}
