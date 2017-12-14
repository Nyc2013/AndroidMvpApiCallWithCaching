package app.pmarchini.com.androidmvpapicallwithcaching.data.db;

import java.util.List;

import app.pmarchini.com.androidmvpapicallwithcaching.data.model.Record;

/**
 * Created by Pierre on 25/11/2017.
 */

public interface DbHelperMvp {

    long save (Record record);

    int deleteAllRecords ();

    List<Record> getAllRecords ();

}
