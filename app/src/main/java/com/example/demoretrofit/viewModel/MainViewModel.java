package com.example.demoretrofit.viewModel;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import com.example.demoretrofit.Repository;
import com.example.demoretrofit.data.model.AnswersResponse;

public class MainViewModel extends ViewModel {

    private MutableLiveData<AnswersResponse> mLiveData;

    private Repository mRepository;

    public void init() {
        if (mLiveData != null) {
            return;
        }
        mRepository = Repository.getInstance();
        mLiveData = mRepository.getData();
    }
    public MutableLiveData<AnswersResponse> getLiveData() {
        return mLiveData;
    }
}