package app.pmarchini.com.androidmvpapicallwithcaching.ui.list;

import java.util.List;

import app.pmarchini.com.androidmvpapicallwithcaching.data.model.Record;
import app.pmarchini.com.androidmvpapicallwithcaching.ui.base.BaseViewMvp;

/**
 * Created by Pierre on 25/11/2017.
 */

public interface ListViewMvp extends BaseViewMvp {

    /**
     * Déclaration de la méthode devant procéder à l'update de la liste. En fait, dans l'architecture
     * MVP, cette méthode correspond à celle permettant l'affichage dans la View des données reçues par le presenter
     */
     void updateList (List<Record> list );


}
