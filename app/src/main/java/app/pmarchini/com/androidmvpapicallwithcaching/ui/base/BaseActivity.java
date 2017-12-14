package app.pmarchini.com.androidmvpapicallwithcaching.ui.base;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import app.pmarchini.com.androidmvpapicallwithcaching.App;
import app.pmarchini.com.androidmvpapicallwithcaching.dependencies.components.ActivityComponent;
import app.pmarchini.com.androidmvpapicallwithcaching.dependencies.components.DaggerActivityComponent;
import app.pmarchini.com.androidmvpapicallwithcaching.dependencies.modules.ActivityModule;

/**
 * Created by Pierre on 25/11/2017.
 */

public class BaseActivity extends AppCompatActivity implements BaseViewMvp {

    /**
     * Pas d'injection de BasePresenter ds BaseActivity puisqu'elle n'interagit pas directement avec un presenter
     * puisqu'elle n'affiche rien elle-même. Elle n'est qu'étendue par les autres activités.
     * Elle contient cependant ActivityComponent qu'elle "partagera" avec les activités étendues via
     * un getter.
     */


    private ActivityComponent mActivityComponent;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mActivityComponent = DaggerActivityComponent.builder()
                .activityModule(new ActivityModule(this))
                .applicationComponent(((App) getApplication()).getComponent())
                .build();

    }

    public ActivityComponent getActivityComponent() {
        return mActivityComponent;
    }


    /**
     * méthode retournant un booléen et vérifiant l'état de la connexion
     */
    @Override
    public boolean isConnected() {
        ConnectivityManager cm =
                (ConnectivityManager)this.getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        return activeNetwork != null && activeNetwork.isConnectedOrConnecting();
    }

}
