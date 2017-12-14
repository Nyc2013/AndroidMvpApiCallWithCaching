package app.pmarchini.com.androidmvpapicallwithcaching.data.api;

import android.util.Log;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import app.pmarchini.com.androidmvpapicallwithcaching.data.model.Record;
import io.reactivex.Observable;
import retrofit2.Retrofit;
import retrofit2.http.GET;

/**
 * Created by Pierre on 25/11/2017.
 */

@Singleton
public class ApiManager implements ApiManagerMvp {


    private Retrofit mRetrofit;

    @Inject
    public ApiManager(Retrofit retrofit) {
        this.mRetrofit = retrofit;
        Log.d("DEBUG", "ApiManager/constructeur ");
    }


    @Override
    public Observable<List<Record>> getApiListRecords(){
        Log.d("DEBUG", "ApiManager/apl  getApiListRecords renvoyant un Observable: ");
        return mRetrofit.create(RetrofitService.class).getListRecords();
    }

    public interface RetrofitService{

        @GET("/photos")
        Observable<List<Record>> getListRecords();
    }


}
