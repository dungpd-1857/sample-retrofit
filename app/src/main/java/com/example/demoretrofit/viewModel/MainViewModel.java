package com.example.demoretrofit.viewModel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.support.annotation.NonNull;
import android.util.Log;
import com.example.demoretrofit.adapter.AnswersAdapter;
import com.example.demoretrofit.data.model.Item;
import com.example.demoretrofit.data.model.SOAnswersResponse;
import com.example.demoretrofit.data.remote.ApiUtils;
import com.example.demoretrofit.data.remote.SOService;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import java.util.ArrayList;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainViewModel extends AndroidViewModel {

    private SOService mService;
    private List<Item> mItemList;
    private AnswersAdapter mAdapter;

    public MainViewModel(@NonNull Application application) {
        super(application);
        mService = ApiUtils.getSOService();
        mItemList = new ArrayList<Item>();
    }

    public List<Item> getItemList() {
        mService.getAnswers().enqueue(new Callback<SOAnswersResponse>() {
            @Override
            public void onResponse(Call<SOAnswersResponse> call,
                    Response<SOAnswersResponse> response) {

                if (response.isSuccessful()) {
                    mItemList = response.body().getItems();
                    Log.d("loadAnswers", "loaded from API");
                } else {
                    int statusCode = response.code();
                    Log.d("loadAnswers", "error loading from API " + statusCode);
                }
            }

            @Override
            public void onFailure(Call<SOAnswersResponse> call, Throwable t) {
                //                        showErrorMessage();
            }
        });
        //        mService.getAnswers()
        //                .subscribeOn(Schedulers.io())
        //                .observeOn(AndroidSchedulers.mainThread())
        //                .subscribe(abc -> {
        ////                    List<Item> mList  = ;
        //                    if (abc.isSuccessful() /*&& mList.size()!=0 && mList!=null*/) {
        //                        mAdapter.updateAnswers(abc.body().getItems());
        ////                        mItemList.addAll(mList);
        //                        Log.d("loadAnswers", "loaded from API Success");
        //                    } else {
        //                        int statusCode = abc.code();
        //                        Log.d("loadAnswers", "error loading from API " + statusCode);
        //                    }
        //                });
        return mItemList;
    }
}
