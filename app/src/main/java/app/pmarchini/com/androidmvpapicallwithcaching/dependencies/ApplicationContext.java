package app.pmarchini.com.androidmvpapicallwithcaching.dependencies;

/**
 * Created by Pierre on 25/11/2017.
 */

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import javax.inject.Qualifier;

/***
 * Il s'agit ici en fait d'une annotation Qualifer qui est créée pour distinguer le context d'application
 * de celui d'activité. Il est possible d'utilise l'annotation @Named("application_context")mais c'est une façon plus
 * correcte de procéder pour qualifier les dépendances (cela évite de préciser en dur l'attribut de l'
 * annotation)
 */
@Qualifier
@Retention(RetentionPolicy.RUNTIME)
public @interface ApplicationContext {
}
