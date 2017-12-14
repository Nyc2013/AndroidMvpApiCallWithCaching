package app.pmarchini.com.androidmvpapicallwithcaching.ui.list;

import android.util.Log;
import java.util.List;
import javax.inject.Inject;

import app.pmarchini.com.androidmvpapicallwithcaching.data.DataManagerMvp;
import app.pmarchini.com.androidmvpapicallwithcaching.data.model.Record;
import app.pmarchini.com.androidmvpapicallwithcaching.ui.base.BasePresenter;

import io.reactivex.Observable;
import io.reactivex.CompletableObserver;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by Pierre on 25/11/2017.
 */

public class ListPresenter<V extends ListViewMvp> extends BasePresenter<V> implements ListPresenterMvp<V> {



    private Disposable disposableApi;
    private Disposable disposableDb;
    //private Disposable disposableStore;




    /**
     * contructeur : injection DataManangerMvp et appel au constructeur classe mère
     * On a pas de champs propre à la classe, car faisant appel au contructeur de BasePresenter,
     * l'instance DataManagerMvp sera référencée au niveau de la classe mère et disponible via
     * getDatamanager() (le champ étant private) de la classe mère BasePresenter
     */
    @Inject
    public ListPresenter(DataManagerMvp mDataManager) {
        super(mDataManager);

    }

    @Override
    public Disposable getApiDisposable(){
        return disposableApi;
    }

    @Override
    public Disposable getDbDisposable(){
        return disposableDb;
    }

    //@Override
    //public Disposable getStoreDisposable(){ return disposableStore; }


    /**
     * méthode à implémenter :
     * Cette méthode est appelée à partir de la View ( l'activité)
     * Elle a pour but de demander les données au DataManagerMvp, de les traiter et d'appeler la
     * méthode de la View qui permettera leur affichage updateList()
     */
    @Override
    public void presenterComputationFromApiForView(){
        getDataManager().getApiListRecords()
                .publish(
                        listNetwork-> 
                                Observable
                                .merge(
                                        listNetwork,
                                        getDataManager().getDbListRecords()
                                                .takeUntil(listNetwork)
                                )
                ).subscribeOn(Schedulers.io())
                .doAfterTerminate(new Action() { // On effectue le cache en appelant cachingOnDb() après l'appel à onComplete() ou onError()
                    @Override
                    public void run() throws Exception {
                        cachingOnDb();
                    }
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<List<Record>>() {

                    @Override
                    public void onSubscribe(@NonNull Disposable d) {
                        disposableApi = d;
                    }

                    @Override
                    public void onNext(@NonNull List<Record> records) {
                        Log.d("DEBUG", "ListPresenter/FromApi/subscribe()/onNext()/ contenu listRecords: "+records);
                        Log.d("DEBUG", "ListPresenter/FromApi/subscribe()/onNext()/ taille listRecords: "+records.size());

                        //&& listRecords.getData() != null
                        if (records != null ) {
                            Log.d("DEBUG", "ListPresenter/FromApi/subscribe()/onNext()/IF/update(): "+records);
                            getViewMvp().updateList(records);
                        }
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        e.printStackTrace();
                    }

                    @Override
                    public void onComplete() {
                        Log.d("Debug", "ListPresnter/subscribe()/onComplete()");
                    }

                });

    }

    /**
     * Méthode procédant à l'enregistrement des données en DB et servant de cache.
     * Cette méthode est appelée une fois le traitement de l'appel réseau terminé.
     * Cette méthode doit s'effectuée sur un thread séparé. Comme nous l'avons vu, le cache se fait
     * automatiquement lors d'un appel réseau et n'est pas laissé à l'appréciation de l'utilisateur.
     * Ce design fait que si l'utilsateur procéde à plusieurs rotation de l'écran, cela entrainera
     * un même nombre d'appel à cette méthode. Afin d'éviter, dans ce cas, une corruption de la table
     * par un accés concurrentiel à celle-ci par les diférents thread générés, nous utilisons un thread
     * unique au lieu d'un pool de thread (comme cela serait le cas avec Schedulers.io() ou .computation())
     */
    @Override
    public void cachingOnDb(){

        getDataManager()
                .storeInDb()
                .subscribeOn(Schedulers.single())
                .subscribe(new CompletableObserver() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onComplete() {
                        Log.d("DEBUG", "cachingOnDb/onComplete: Done!");
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        Log.d("DEBUG", "cachingOnDb/onError: "+e);
                    }
                });

    }

    /**
     * Méthode appélée si il n'y a pas de connexion
     */
    @Override
    public void presenterComputationFromDbForView(){
        getDataManager().getDbListRecords()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<List<Record>>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                        disposableDb = d;

                    }

                    @Override
                    public void onNext(List<Record> records) {
                        Log.d("DEBUG", "ListPresenter/FromDB/subscribe()/onNext()/ contenu listRecords: "+records);
                        Log.d("DEBUG", "ListPresenter/FromDB/subscribe()/onNext()/ taille listRecords: "+records.size());
                        if (records != null ) {
                            getViewMvp().updateList(records);
                        }
                    }

                    @Override
                    public void onError(Throwable t) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}
