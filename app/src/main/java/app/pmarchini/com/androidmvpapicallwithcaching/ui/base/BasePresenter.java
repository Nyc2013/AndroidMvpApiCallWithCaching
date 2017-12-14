package app.pmarchini.com.androidmvpapicallwithcaching.ui.base;

import javax.inject.Inject;

import app.pmarchini.com.androidmvpapicallwithcaching.data.DataManagerMvp;

/**
 * Created by Pierre on 25/11/2017.
 */

public class BasePresenter<V extends BaseViewMvp> implements BasePresenterMvp<V> {


    private V mViewMvp;

    private final DataManagerMvp mDataManager;

    /**
     * Injection par constructeur de DataManagerMvp
     * Les classes filles feront appel au contructeur de la classe mère par super(...)
     */
    @Inject
    public BasePresenter (DataManagerMvp dataManager){
        this.mDataManager = dataManager;
    }


    /**
     * méthode à implémentée : public void attachViewOnPresenter(V viewMvp)
     * va récupérer la vue représentée par l'activité et l'affecter dans la référence la représentant
     * au niveau du presenter
     * Cette méthode sera appelée à partir de l'activité
     */
    @Override
    public void attachViewOnPresenter(V viewMvp){
        this.mViewMvp = viewMvp;
    }

    @Override
    public void onDetach() {

        mViewMvp = null;
    }
    /**
     * Cette méthode retourne la vue contenue au niveau de la référence du Presenter (donc la vue
     * qui lui est rattachée
     */
    public V getViewMvp(){
        return mViewMvp;
    }

    public DataManagerMvp getDataManager(){
        return mDataManager;
    }

    /**
     * Faisant des appels reseaux il sera nécessaire par la suite de gérer les erreurs liées au reseau
     * (annulation d'une requete, perte de connexion, accès interdit, etc...). c'est à ce niveau
     * qu'il faudra prévoir de le faire. Mais dans notre exemple, nous ne nous y intéressons pas car
     * cela sort de ce que l'on souhaite présenter
     * Le traitement s'effectue ici via une méthode qui checkera le type d'erreur et renverra le message
     * ou fera l'action adaptée. En effet, c'est au niveau des Presenter que la subscription est faite
     * et où donc sont traitées les erreurs (callback onError()). Les Presenter héritant de BasePresenter
     * ils auront tous accès à cette méthode de traitement
     */
}
