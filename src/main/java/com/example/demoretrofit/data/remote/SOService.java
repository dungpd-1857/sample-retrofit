package com.example.demoretrofit.data.remote;


import com.example.demoretrofit.data.model.SOAnswersResponse;
import io.reactivex.Observable;
import java.util.List;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface SOService {

    @GET("/answers?order=desc&sort=activity&site=stackoverflow")
    Call<SOAnswersResponse> getAnswers();

//    RxJava
//    @GET("/answers?order=desc&sort=activity&site=stackoverflow")
//    Observable<Response<SOAnswersResponse>> getAnswers();

//    @GET("/answers?order=desc&sort=activity&site=stackoverflow")
//    Call<SOAnswersResponse> getAnswers(@Query("tagged") String tags);

}
