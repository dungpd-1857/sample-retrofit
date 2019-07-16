package com.example.demoretrofit.view;

import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;
import com.example.demoretrofit.R;
import com.example.demoretrofit.adapter.AnswersAdapter;
import com.example.demoretrofit.data.model.Item;
import com.example.demoretrofit.databinding.ActivityMainBinding;
import com.example.demoretrofit.viewModel.MainViewModel;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements AnswersAdapter.PostItemListener {

    private AnswersAdapter mAdapter;
    private MainViewModel mMainViewModel;
    private ActivityMainBinding mainBingding;
    private List<Item> mItemList = new ArrayList<Item>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mainBingding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        initView();
        initData();
    }

    private void initView() {
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        mainBingding.rvAnswers.setLayoutManager(layoutManager);
        RecyclerView.ItemDecoration itemDecoration =
                new DividerItemDecoration(this, DividerItemDecoration.VERTICAL_LIST);
        mainBingding.rvAnswers.addItemDecoration(itemDecoration);
    }

    private void initData() {
        mMainViewModel = ViewModelProviders.of(this).get(MainViewModel.class);
        mMainViewModel.init();
        mMainViewModel.getLiveData().observe(this, res -> {
            List<Item> newsArticles = res.getItems();
            mItemList.addAll(newsArticles);
            mAdapter.notifyDataSetChanged();
        });
        mAdapter = new AnswersAdapter(this, mItemList);
        mainBingding.rvAnswers.setAdapter(mAdapter);
        mAdapter.setListener(this);
    }

    @Override
    public void onPostClick(Item id) {
        Toast.makeText(MainActivity.this, "Use " + id.getOwner().getDisplayName(),
                Toast.LENGTH_SHORT).show();
    }
}
