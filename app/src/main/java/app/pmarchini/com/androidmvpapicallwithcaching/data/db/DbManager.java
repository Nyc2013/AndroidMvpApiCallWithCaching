package app.pmarchini.com.androidmvpapicallwithcaching.data.db;

import android.util.Log;

import java.util.List;
import java.util.concurrent.Callable;

import javax.inject.Inject;
import javax.inject.Singleton;

import app.pmarchini.com.androidmvpapicallwithcaching.data.model.Record;
import io.reactivex.Observable;

/**
 * Created by Pierre on 25/11/2017.
 */

/**
 * Le scope est celui de l'application
 */
@Singleton
public class DbManager implements DbManagerMvp {


    private DbHelperMvp mDbHelper;

    @Inject
    public DbManager(DbHelperMvp dbHelper) {
        this.mDbHelper = dbHelper;
    }


    @Override
    public int deleteRecords(){
        return mDbHelper.deleteAllRecords();
    }


    @Override
    public long insertRecord (final Record record){
        return mDbHelper.save(record);
    }

    @Override
    public Observable<List<Record>> getDbListRecords () {
        return Observable.fromCallable(new Callable<List<Record>>() {
            @Override
            public List<Record> call() throws Exception {

                List<Record> listRecords = mDbHelper.getAllRecords();
                Log.d("DEBUG", "DbManager/apl getDbListRecords/ nb item = "+ listRecords.size());
                return listRecords;
            }
        });
    }

    //ATTENTION code de debug utilisé dans les logs pour récupérer les données enregistrées dans la table
    @Override
    public List<Record> getNonObservableListRecords(){return mDbHelper.getAllRecords();}

}
