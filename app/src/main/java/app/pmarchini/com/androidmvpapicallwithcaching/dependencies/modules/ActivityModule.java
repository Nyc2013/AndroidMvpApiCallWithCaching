package app.pmarchini.com.androidmvpapicallwithcaching.dependencies.modules;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;

import java.util.ArrayList;

import app.pmarchini.com.androidmvpapicallwithcaching.data.model.Record;
import app.pmarchini.com.androidmvpapicallwithcaching.dependencies.ActivityContext;
import app.pmarchini.com.androidmvpapicallwithcaching.dependencies.PerActivity;
import app.pmarchini.com.androidmvpapicallwithcaching.ui.detail.DetailPresenter;
import app.pmarchini.com.androidmvpapicallwithcaching.ui.detail.DetailPresenterMvp;
import app.pmarchini.com.androidmvpapicallwithcaching.ui.detail.DetailViewMvp;
import app.pmarchini.com.androidmvpapicallwithcaching.ui.list.ListAdapter;
import app.pmarchini.com.androidmvpapicallwithcaching.ui.list.ListPresenter;
import app.pmarchini.com.androidmvpapicallwithcaching.ui.list.ListPresenterMvp;
import app.pmarchini.com.androidmvpapicallwithcaching.ui.list.ListViewMvp;
import dagger.Module;
import dagger.Provides;

/**
 * Created by Pierre on 25/11/2017.
 */

@Module
public class ActivityModule {


     private AppCompatActivity mActivity;

     public ActivityModule(AppCompatActivity activity) {
     this.mActivity = activity;
     }

     @Provides
     @ActivityContext
     Context provideContext() {
          return mActivity;
     }

     @Provides
     AppCompatActivity provideActivity() {
     return mActivity;
     }

     @Provides
     @PerActivity
     ListPresenterMvp<ListViewMvp> provideListPresenter (ListPresenter<ListViewMvp> presenter){
          return presenter;
     }

     @Provides
     @PerActivity
     DetailPresenterMvp<DetailViewMvp> provideDetailPresenter (DetailPresenter<DetailViewMvp> presenter) {
          return presenter;
     }

     @Provides
     ListAdapter provideListAdapter(){
          return new ListAdapter(new ArrayList<Record>());
     }

     @Provides
     LinearLayoutManager provideLinearLayoutManager(AppCompatActivity activity) {
     return new LinearLayoutManager(activity);
     }

}
