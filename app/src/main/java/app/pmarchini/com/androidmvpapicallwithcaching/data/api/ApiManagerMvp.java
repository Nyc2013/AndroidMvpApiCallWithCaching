package app.pmarchini.com.androidmvpapicallwithcaching.data.api;

import java.util.List;

import app.pmarchini.com.androidmvpapicallwithcaching.data.model.Record;
import io.reactivex.Observable;


/**
 * Created by Pierre on 25/11/2017.
 */

public interface ApiManagerMvp {

    Observable<List<Record>> getApiListRecords ();
}
