package com.example.demoretrofit.data.remote;


import com.example.demoretrofit.data.model.SOAnswersResponse;
import io.reactivex.Observable;
import retrofit2.Call;
import retrofit2.http.GET;

public interface SOService {

    @GET("/answers?order=desc&sort=activity&site=stackoverflow")
    Call<SOAnswersResponse> getAnswers();

//    RxJava
//    @GET("/answers?order=desc&sort=activity&site=stackoverflow")
//    Observable<SOAnswersResponse> getAnswers();

    /*@GET("/answers?order=desc&sort=activity&site=stackoverflow")
    Call<List<SOAnswersResponse>> getAnswers(@Query("tagged") String tags);*/

}
