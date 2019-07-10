package com.example.demoretrofit.view;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;
import com.example.demoretrofit.R;
import com.example.demoretrofit.adapter.AnswersAdapter;
import com.example.demoretrofit.data.model.Item;
import com.example.demoretrofit.data.model.SOAnswersResponse;
import com.example.demoretrofit.data.remote.ApiUtils;
import com.example.demoretrofit.data.remote.SOService;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import java.util.ArrayList;
import io.reactivex.disposables.CompositeDisposable;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private AnswersAdapter mAdapter;
    private RecyclerView mRecyclerView;
    private SOService mService;

    @NonNull
    private CompositeDisposable mDisposables;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mService = ApiUtils.getSOService();

        mRecyclerView = (RecyclerView) findViewById(R.id.rv_answers);
        mAdapter = new AnswersAdapter(this, new ArrayList<Item>(0),
                new AnswersAdapter.PostItemListener() {

                    @Override
                    public void onPostClick(long id) {
                        Toast.makeText(MainActivity.this, "Post id " + id, Toast.LENGTH_SHORT)
                                .show();
                    }
                });

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.setAdapter(mAdapter);
        RecyclerView.ItemDecoration itemDecoration =
                new DividerItemDecoration(this, DividerItemDecoration.VERTICAL_LIST);
        mRecyclerView.addItemDecoration(itemDecoration);

        mDisposables = new CompositeDisposable();

        loadAnswers();
    }

    public void loadAnswers() {
        mService.getAnswers().enqueue(new Callback<SOAnswersResponse>() {
            @Override
            public void onResponse(Call<SOAnswersResponse> call,
                    Response<SOAnswersResponse> response) {

                if (response.isSuccessful()) {
                    mAdapter.updateAnswers(response.body().getItems());
                    Log.d("loadAnswers", "loaded from API");
                } else {
                    int statusCode = response.code();
                    Log.d("loadAnswers", "error loading from API " + statusCode);
                }
            }

            @Override
            public void onFailure(Call<SOAnswersResponse> call, Throwable t) {
                showErrorMessage();
            }
        });
    }

    public void showErrorMessage() {
        Toast.makeText(this, "Error loading posts", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mDisposables.dispose();
    }
}
