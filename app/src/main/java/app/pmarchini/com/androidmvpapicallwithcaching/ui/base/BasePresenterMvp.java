package app.pmarchini.com.androidmvpapicallwithcaching.ui.base;

/**
 * Created by Pierre on 25/11/2017.
 */

public interface BasePresenterMvp<V extends BaseViewMvp> {

     void attachViewOnPresenter(V viewMvp );
     void onDetach();
}
