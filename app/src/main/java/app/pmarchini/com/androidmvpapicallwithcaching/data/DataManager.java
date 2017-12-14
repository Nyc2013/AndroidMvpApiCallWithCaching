package app.pmarchini.com.androidmvpapicallwithcaching.data;

import android.util.Log;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import app.pmarchini.com.androidmvpapicallwithcaching.data.api.ApiManagerMvp;
import app.pmarchini.com.androidmvpapicallwithcaching.data.db.DbManagerMvp;
import app.pmarchini.com.androidmvpapicallwithcaching.data.model.Record;
import io.reactivex.Completable;
import io.reactivex.CompletableSource;
import io.reactivex.Observable;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Function;


/**
 * Created by Pierre on 25/11/2017.
 */

/**
 * Scope de l'application
 */
@Singleton
public class DataManager implements DataManagerMvp {

    private DbManagerMvp mDbManager;
    private ApiManagerMvp mApiManager;

    @Inject
    public DataManager(DbManagerMvp dbManager, ApiManagerMvp apiManager){
        this.mDbManager = dbManager;
        this.mApiManager = apiManager;
    }

    /**
     * implementation méthodes d'interface DbManagerMvp
     */
    @Override
    public Observable<List<Record>> getDbListRecords(){return mDbManager.getDbListRecords();}

    @Override
    public int deleteRecords(){return mDbManager.deleteRecords();}

    @Override
    public long insertRecord(Record record){return mDbManager.insertRecord(record);}

    /**
     * implémentation méthode d'interface ApiManagerMvp
     */
    @Override
    public Observable<List<Record>> getApiListRecords(){return mApiManager.getApiListRecords();}

    //ATTENTION code de debug utilisé dans les logs pour récupérer les données enregistrées dans la table
    @Override
    public List<Record> getNonObservableListRecords(){return mDbManager.getNonObservableListRecords();}

    /**
     * implémentation des méthodes d'interface DataManagerMvp
     *****************
     * Dans notre implémentation, le cache est délibérément effectué automatiquementlors de l'appel reseau.
     * De ce fait, d'une rotation procédera un nouvel appel à cachingOnDb(). A chaque fois que cela
     * arrive, on s'assure de d'abord de vider la liste. Si un problème survient (erreur 404, perte de
     * connexion) la callback onError() est appelé, ce sera le cache précédent qui sera affiché
     */
    @Override
    public Completable storeInDb(){
        return getApiListRecords()
                .flatMapCompletable(new Function<List<Record>, CompletableSource>() {
                    @Override
                    public CompletableSource apply(@NonNull List<Record> records) throws Exception {
                        deleteRecords();
                        Log.d("DEBUG", "ListPresenter/cachingOnDb()/efface table/ nb entrees db: " + getNonObservableListRecords().size());
                        for (int i=0; i < records.size(); i++) {
                            Record oneRecord = records.get(i);
                            insertRecord(oneRecord);
                        }
                        Log.d("DEBUG", "ListPresenter/cachingOnDb()/ taille listRecords stored: " + getNonObservableListRecords().size());
                        return Completable.complete();
                    }
                });
    }

}
