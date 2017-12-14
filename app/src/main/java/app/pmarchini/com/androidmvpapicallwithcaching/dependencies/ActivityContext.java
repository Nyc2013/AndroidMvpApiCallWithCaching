package app.pmarchini.com.androidmvpapicallwithcaching.dependencies;

/**
 * Created by Pierre on 29/11/2017.
 */

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import javax.inject.Qualifier;

/***
 * Annotation @Qualifer créée pour distinguer le context d'activité de celui de l'application.
 */
@Qualifier
@Retention(RetentionPolicy.RUNTIME)
public @interface ActivityContext {
}
