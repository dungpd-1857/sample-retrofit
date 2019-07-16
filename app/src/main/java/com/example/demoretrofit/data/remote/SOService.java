package com.example.demoretrofit.data.remote;


import com.example.demoretrofit.data.model.AnswersResponse;
import io.reactivex.Single;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface SOService {

//    @GET("/answers?order=desc&sort=activity&site=stackoverflow")
//    Call<AnswersResponse> getAnswers();

//    RxJava
    @GET("/answers?order=desc&sort=activity&site=stackoverflow")
    Single<Response<AnswersResponse>> getAnswers();

//    @GET("/answers?order=desc&sort=activity&site=stackoverflow")
//    Single<Response<AnswersResponse>> getAnswers(@Query("tagged") String tags);

}
