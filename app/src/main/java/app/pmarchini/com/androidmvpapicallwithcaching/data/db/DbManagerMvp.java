package app.pmarchini.com.androidmvpapicallwithcaching.data.db;


import java.util.List;

import app.pmarchini.com.androidmvpapicallwithcaching.data.model.Record;
import io.reactivex.Observable;


/**
 * Created by Pierre on 25/11/2017.
 */

public interface DbManagerMvp {

    long insertRecord (final Record record);

    int deleteRecords();

    List<Record> getNonObservableListRecords();

    Observable<List<Record>> getDbListRecords ();

}
