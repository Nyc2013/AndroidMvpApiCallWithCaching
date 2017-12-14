package app.pmarchini.com.androidmvpapicallwithcaching.data;

import java.util.List;

import app.pmarchini.com.androidmvpapicallwithcaching.data.api.ApiManagerMvp;
import app.pmarchini.com.androidmvpapicallwithcaching.data.db.DbManagerMvp;
import app.pmarchini.com.androidmvpapicallwithcaching.data.model.Record;
import io.reactivex.Completable;


/**
 * Created by Pierre on 25/11/2017.
 */

/**
 * DataManagerMvp va étendre les interfaces ApiManagerMvp et DbManagerMvp, de ce fait la classe qui
 * implemente DataManagerMvp devra implémenter les méthodes de ces 2 interfaces (en plus de celles
 * de l'interface DataManagerMvp si des méthodes y sont déclarées
 */

public interface DataManagerMvp extends ApiManagerMvp, DbManagerMvp {
    /**
     * non returning Observable method getListRecords to check if list is empty (RxJava2 doesn't allow null value)
     */
    List<Record> getNonObservableListRecords();

    /**
     * méthode utilisée par cachingOnDb() pour le cache en DB des datas recoltées à partir du call
     * Api. Cette méthode ayant un besoin d'accés au DbManager et à l'ApiManager ,
     * il est plus judicieux de les relier au DataManager
     */

    Completable storeInDb();

}
