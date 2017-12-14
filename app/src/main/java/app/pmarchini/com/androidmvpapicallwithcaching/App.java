package app.pmarchini.com.androidmvpapicallwithcaching;

import android.app.Application;

import javax.inject.Inject;

import app.pmarchini.com.androidmvpapicallwithcaching.data.DataManagerMvp;
import app.pmarchini.com.androidmvpapicallwithcaching.dependencies.components.ApplicationComponent;
import app.pmarchini.com.androidmvpapicallwithcaching.dependencies.components.DaggerApplicationComponent;
import app.pmarchini.com.androidmvpapicallwithcaching.dependencies.modules.ApplicationModule;

/**
 * Created by Pierre on 25/11/2017.
 */

public class App extends Application {

    @Inject
    DataManagerMvp mDataManager;

    private ApplicationComponent mApplicationComponent;

    @Override
    public void onCreate() {
        super.onCreate();

        mApplicationComponent = DaggerApplicationComponent.builder()
                .applicationModule(new ApplicationModule(this)).build();

        mApplicationComponent.inject(this);

    }

    public ApplicationComponent getComponent() {
        return mApplicationComponent;
    }

}
