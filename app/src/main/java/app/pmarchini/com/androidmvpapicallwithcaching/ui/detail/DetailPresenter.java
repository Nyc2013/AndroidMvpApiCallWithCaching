package app.pmarchini.com.androidmvpapicallwithcaching.ui.detail;

import javax.inject.Inject;

import app.pmarchini.com.androidmvpapicallwithcaching.data.DataManagerMvp;
import app.pmarchini.com.androidmvpapicallwithcaching.ui.base.BasePresenter;

/**
 * Created by Pierre on 25/11/2017.
 */

public class DetailPresenter<V extends DetailViewMvp> extends BasePresenter<V> implements DetailPresenterMvp<V> {

    @Inject
    public DetailPresenter(DataManagerMvp mDataManager) {
        super(mDataManager);
        //getViewMvp();
    }
}
