package com.example.demoretrofit;

import android.arch.lifecycle.MutableLiveData;
import android.util.Log;
import com.example.demoretrofit.data.model.AnswersResponse;
import com.example.demoretrofit.data.remote.ApiUtils;
import com.example.demoretrofit.data.remote.SOService;
import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import java.util.concurrent.Callable;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Repository {
    private static Repository mRepository;

    private SOService mService;
    private MutableLiveData<AnswersResponse> data = new MutableLiveData<>();


    public Repository() {
        mService = ApiUtils.getSOService();
    }

    public static Repository getInstance() {
        if (mRepository == null) {
            mRepository = new Repository();
        }
        return mRepository;
    }

    public MutableLiveData<AnswersResponse> getData() {
//                mService.getAnswers().enqueue(new Callback<AnswersResponse>() {
//                    @Override
//                    public void onResponse(Call<AnswersResponse> call,
//                            Response<AnswersResponse> response) {
//                        if (response.isSuccessful()) {
//                            data.setValue(response.body());
//                            Log.d("Repository getdata", "loaded from API");
//                        } else {
//                            int statusCode = response.code();
//                            Log.d("Repository getdata", "error loading from API " + statusCode);
//                        }
//                    }
//
//                    @Override
//                    public void onFailure(Call<AnswersResponse> call, Throwable t) {
//                          data.setValue(null);
//                          Log.d("Repository getdata", "error API ");
//                    }
//                });
        mService.getAnswers()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(abc -> {
                    if (abc.isSuccessful()) {
                        data.setValue(abc.body());
                        Log.d("Repository getdata", "loaded Success");
                    } else {
                        int statusCode = abc.code();
                        Log.d("Repository getdata", "error loading from API " + statusCode);
                    }
                });
        return data;
    }
}