package app.pmarchini.com.androidmvpapicallwithcaching.dependencies.modules;

import android.app.Application;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.support.annotation.Nullable;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import javax.inject.Singleton;

import app.pmarchini.com.androidmvpapicallwithcaching.AppConfig;
import app.pmarchini.com.androidmvpapicallwithcaching.data.DataManager;
import app.pmarchini.com.androidmvpapicallwithcaching.data.DataManagerMvp;
import app.pmarchini.com.androidmvpapicallwithcaching.data.api.ApiManager;
import app.pmarchini.com.androidmvpapicallwithcaching.data.api.ApiManagerMvp;
import app.pmarchini.com.androidmvpapicallwithcaching.data.db.DbHelper;
import app.pmarchini.com.androidmvpapicallwithcaching.data.db.DbHelperMvp;
import app.pmarchini.com.androidmvpapicallwithcaching.data.db.DbManager;
import app.pmarchini.com.androidmvpapicallwithcaching.data.db.DbManagerMvp;
import app.pmarchini.com.androidmvpapicallwithcaching.dependencies.ApiEndPoint;
import app.pmarchini.com.androidmvpapicallwithcaching.dependencies.ApplicationContext;
import app.pmarchini.com.androidmvpapicallwithcaching.dependencies.InfoDbName;
import app.pmarchini.com.androidmvpapicallwithcaching.dependencies.InfoDbVersion;
import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Pierre on 25/11/2017.
 */

@Module
public class ApplicationModule {



     private final Application mApplication;

     public ApplicationModule(Application application) {
          mApplication = application;
     }

     @Provides
     @ApplicationContext
     Context provideContext() {
          return mApplication;
     }

     @Provides
     Application provideApplication() {
          return mApplication;
     }

     @Provides
     @ApiEndPoint
     String provideApiEndPoint() {
          return AppConfig.API_ENDPOINT;
     }

     @Provides
     @InfoDbName
     String provideBbName(){return AppConfig.DB_NAME;}

     @Provides
     @InfoDbVersion
     int provideDbVersion(){return AppConfig.DB_VERSION;}

    /**
     * Par défaut, Dagger ne permet pas l'injection de valeur nulle, ce qui est vérifié par
     * Preconditions.checkNotNull() du get() de la classe générée
     * Cependant, null doit être passé afin d'avoir le cursor standard et non un custom cursor
     * @Nullable est non recommandé par Dagger (car cela constitue un non sens à leurs yeux d'injecter une valeur nulle)
     * mais permise car chaque cas de figure d'une injection est difficilement anticipable et que cala
     * est apparu comme un besoin dans certain développement. Ce qui est clairement le cas pour nous, car
     * si nous souhaitons laisser le soin à Dagger d'instancier lui-même nos dépendances par simple délégation,
     * le fait que le CursorFactory doit être null doit lui être connu.
     * Le point d'injection (ds le constructeur de DbHelper) doit être également annoté @Nullable
     * Une discussion sur ce sujet de l'équique de développement de google est disponible sur le net
     */

     @Provides
     @Nullable
     SQLiteDatabase.CursorFactory provideDbCursorFactory(){return null;}

     @Provides
     @Singleton
     DataManagerMvp provideDataManager(DataManager dataManager) {
          return dataManager;
     }

     @Provides
     @Singleton
     DbHelperMvp provideDbHelper(DbHelper dbHelper) {
          return dbHelper;
     }

     @Provides
     @Singleton
     DbManagerMvp provideDbManager(DbManager dbManager){
          return dbManager;
     }

     @Provides
     @Singleton
     ApiManagerMvp provideApiManager(ApiManager apiManager) {
          return apiManager;
     }

     @Provides
     @Singleton
     Gson provideGson (){
          return new GsonBuilder()
                  .setLenient()
                  .create();
     }

     @Provides
     @Singleton
     Retrofit provideRetrofit(@ApiEndPoint String endPoint, Gson gson){
          return new Retrofit.Builder()
          .baseUrl(endPoint)
          .addConverterFactory(GsonConverterFactory.create(gson))
          .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
          .build();
     }


}
