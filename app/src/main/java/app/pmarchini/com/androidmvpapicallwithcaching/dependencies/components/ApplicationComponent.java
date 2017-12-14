package app.pmarchini.com.androidmvpapicallwithcaching.dependencies.components;

import android.app.Application;
import android.content.Context;

import javax.inject.Singleton;

import app.pmarchini.com.androidmvpapicallwithcaching.App;
import app.pmarchini.com.androidmvpapicallwithcaching.data.DataManagerMvp;
import app.pmarchini.com.androidmvpapicallwithcaching.dependencies.ApplicationContext;
import app.pmarchini.com.androidmvpapicallwithcaching.dependencies.modules.ApplicationModule;
import dagger.Component;

/**
 * Created by Pierre on 25/11/2017.
 */

@Singleton
@Component(modules = ApplicationModule.class)
public interface ApplicationComponent {

    void inject(App app);

    @ApplicationContext
    Context context();

    Application application();

    DataManagerMvp getDataManager();
}
