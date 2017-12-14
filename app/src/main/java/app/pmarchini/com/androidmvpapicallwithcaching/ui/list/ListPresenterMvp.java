package app.pmarchini.com.androidmvpapicallwithcaching.ui.list;

import app.pmarchini.com.androidmvpapicallwithcaching.dependencies.PerActivity;
import app.pmarchini.com.androidmvpapicallwithcaching.ui.base.BasePresenterMvp;
import io.reactivex.disposables.Disposable;


/**
 * Created by Pierre on 25/11/2017.
 */

/**
 * Le presenter doit suivre le cycle de vie de l'activité à laquelle il est rattaché. Il aura donc
 * pour scope @PerActivity
 */
@PerActivity
public interface ListPresenterMvp<V extends ListViewMvp> extends BasePresenterMvp<V> {


    void presenterComputationFromApiForView();

    void presenterComputationFromDbForView();

    void cachingOnDb();

    /**
     * Il est nécessaire d'imposer un getter sur le disposable contenu au niveau de ListPresenter.
     * En effet, nous aurons besoin du disposable au niveau de ListActivity pour gérer les changements
     * de configuration. Or Dagger renvoie des instances typée du type de l'interface implémentée par la classe
     * représentant la dépendance, de ce fait, nous ne pouvons pas accéder au champs de ListPresenter
     * mais uniquement au méthodes présentées dans ListPresenterMvp. Il est donc nécessaire de déclarer
     * un getter du disposable au niveau de l'interface.
     */
    Disposable getApiDisposable();

    Disposable getDbDisposable();

    //Disposable getCachingDisposable();


}
