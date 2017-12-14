package app.pmarchini.com.androidmvpapicallwithcaching.dependencies.components;

import app.pmarchini.com.androidmvpapicallwithcaching.dependencies.PerActivity;
import app.pmarchini.com.androidmvpapicallwithcaching.dependencies.modules.ActivityModule;
import app.pmarchini.com.androidmvpapicallwithcaching.ui.detail.DetailActivity;
import app.pmarchini.com.androidmvpapicallwithcaching.ui.list.ListActivity;
import dagger.Component;

/**
 * Created by Pierre on 25/11/2017.
 */

@PerActivity
@Component(dependencies = ApplicationComponent.class, modules = ActivityModule.class)
public interface ActivityComponent {

    void inject(ListActivity activity);

    void inject(DetailActivity activity);

}
