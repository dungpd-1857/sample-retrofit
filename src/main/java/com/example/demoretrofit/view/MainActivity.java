package com.example.demoretrofit.view;

import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;
import com.example.demoretrofit.R;
import com.example.demoretrofit.adapter.AnswersAdapter;
import com.example.demoretrofit.data.model.Item;
import com.example.demoretrofit.databinding.ActivityMainBinding;
import com.example.demoretrofit.viewModel.MainViewModel;
import io.reactivex.disposables.CompositeDisposable;

public class MainActivity extends AppCompatActivity implements AnswersAdapter.PostItemListener {

    private AnswersAdapter mAdapter;
    private MainViewModel mMainViewModel;
    private ActivityMainBinding mainBingding;

    @NonNull
    private CompositeDisposable mDisposables;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mainBingding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        mMainViewModel = ViewModelProviders.of(this).get(MainViewModel.class);
        mAdapter = new AnswersAdapter(this, mMainViewModel.getItemList());
        mAdapter.notifyDataSetChanged();
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        mainBingding.rvAnswers.setLayoutManager(layoutManager);
        mainBingding.rvAnswers.setAdapter(mAdapter);
        RecyclerView.ItemDecoration itemDecoration =
                new DividerItemDecoration(this, DividerItemDecoration.VERTICAL_LIST);
        mainBingding.rvAnswers.addItemDecoration(itemDecoration);
        mAdapter.setListener(this);
        mDisposables = new CompositeDisposable();

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mDisposables.dispose();
    }

    @Override
    public void onPostClick(Item id) {
        Toast.makeText(MainActivity.this, "Post id " + id.getOwner().getDisplayName(),
                Toast.LENGTH_SHORT).show();
    }
}
